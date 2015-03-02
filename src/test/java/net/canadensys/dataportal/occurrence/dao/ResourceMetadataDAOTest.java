package net.canadensys.dataportal.occurrence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Set;

import javax.sql.DataSource;

import net.canadensys.dataportal.occurrence.model.ContactModel;
import net.canadensys.dataportal.occurrence.model.DwcaResourceModel;
import net.canadensys.dataportal.occurrence.model.ResourceMetadataModel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * Test Coverage :
 * -Save ResourceInformationModel
 * -Get generated id
 * -Load ResourceInformationModel from id
 * -Delete ResourceInformationModel
 * 
 * @author Pedro Guimarães
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-spring.xml" })
@TransactionConfiguration(transactionManager = "hibernateTransactionManager")
public class ResourceMetadataDAOTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private DwcaResourceDAO resourceDAO;

	@Autowired
	private ResourceMetadataDAO resourceMetadataDAO;

	private JdbcTemplate jdbcTemplate;

	@Override
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Test
	public void testSaveLoadDelete() {

		// Save a parent DwcaResourceModel first
		String gbif_package_id = "42843f95-6fe3-47e4-bd0c-f4fcadca232f";
		DwcaResourceModel testDwcaResourceModel = new DwcaResourceModel();
		testDwcaResourceModel.setSourcefileid(gbif_package_id);
		assertTrue(resourceDAO.save(testDwcaResourceModel));
		assertTrue(testDwcaResourceModel.getId() != null);

		// Test ResourceInformation model:
		ResourceMetadataModel testResourceMetadata = new ResourceMetadataModel();
		testResourceMetadata.setDwca_resource_id(testDwcaResourceModel.getId());
		testResourceMetadata.set_abstract("This is the lorem ipsum abstract");
		testResourceMetadata.setTitle("TitleTitleTitle");

		testResourceMetadata.setGbif_package_id(gbif_package_id);

		// Create contact:
		ContactModel testResourceContact = new ContactModel();
		testResourceContact.setName("Test Name");
		testResourceContact.setEmail("a@a.com");
		testResourceMetadata.addContact(testResourceContact);

		// Add other contact
		ContactModel testResourceContact2 = new ContactModel();
		testResourceContact2.setName("Test Name 2");
		testResourceContact2.setEmail("a2@a2.com");
		testResourceMetadata.addContact(testResourceContact2);

		// Save information
		assertTrue(resourceMetadataDAO.save(testResourceMetadata));

		// Fetch ids from saved objects:
		Integer resourceInformationPk = testResourceMetadata.getDwca_resource_id();
		int contact1Id = testResourceContact.getAuto_id();
		int contact2Id = testResourceContact2.getAuto_id();

		assertTrue(contact1Id >= 0);
		assertTrue(contact2Id != contact1Id);

		ResourceMetadataModel loadedMetadata = resourceMetadataDAO.load(gbif_package_id);
		assertEquals("This is the lorem ipsum abstract", loadedMetadata.get_abstract());
		assertEquals("TitleTitleTitle", loadedMetadata.getTitle());

		// Test contacts load:
		Set<ContactModel> resourceContacts = loadedMetadata.getContacts();
		ContactModel loadedContact1 = null;
		ContactModel loadedContact2 = null;

		for (ContactModel contact : resourceContacts) {
			Integer autoId = contact.getAuto_id();
			if (autoId == contact1Id) {
				loadedContact1 = contact;
			}
			else if (autoId == contact2Id) {
				loadedContact2 = contact;
			}
			else {
				fail("Unknown contact for auto_id: " + autoId);
			}
		}

		assertEquals("Test Name", loadedContact1.getName());
		assertEquals("a@a.com", loadedContact1.getEmail());

		// Assert resource_metadata_fkey is being filled:
		Integer fkey = jdbcTemplate.queryForObject("SELECT resource_metadata_fkey FROM contact WHERE name =\'Test Name\'", Integer.class);
		assertEquals(fkey, resourceInformationPk);

		assertEquals("Test Name 2", loadedContact2.getName());
		assertEquals("a2@a2.com", loadedContact2.getEmail());

		// Assert resource_metadata_fkey is being filled:
		fkey = jdbcTemplate.queryForObject("SELECT resource_metadata_fkey FROM contact WHERE name =\'Test Name 2\'", Integer.class);
		assertEquals(fkey, resourceInformationPk);

		// Test cascade deletion of information and all contacts after information deletion:
		assertTrue(resourceMetadataDAO.delete(loadedMetadata));
		// Assert information was deleted:
		assertNull(resourceMetadataDAO.load(resourceInformationPk));

		// Assert contacts were also deleted
		Long contactCount = jdbcTemplate.queryForObject("SELECT count(*) FROM contact WHERE auto_id=" + contact1Id + " OR auto_id =" + contact2Id,
				Long.class);
		assertEquals(0, contactCount.intValue());
	}
}
