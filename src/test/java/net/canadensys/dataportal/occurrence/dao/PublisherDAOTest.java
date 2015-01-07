package net.canadensys.dataportal.occurrence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import net.canadensys.dataportal.occurrence.model.ContactModel;
import net.canadensys.dataportal.occurrence.model.DwcaResourceModel;
import net.canadensys.dataportal.occurrence.model.PublisherModel;

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
 * -Save publisherModel
 * -Get generated id
 * -Load publisherModel from id
 * -Delete publisherModel
 * -CRUD for PublisherContact and ResourceModel
 * 
 * @author Pedro Guimarães
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-spring.xml" })
@TransactionConfiguration(transactionManager = "hibernateTransactionManager")
public class PublisherDAOTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private PublisherDAO publisherDAO;

	private JdbcTemplate jdbcTemplate;

	@Override
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Test
	public void testSaveLoadDelete() {

		// Clear previous data:
		jdbcTemplate.update("DELETE FROM contact;");
		jdbcTemplate.update("DELETE FROM dwca_resource;");
		jdbcTemplate.update("DELETE FROM publisher;");

		// Test publisher model:
		PublisherModel testpublisher = new PublisherModel();
		testpublisher.setDescription("This is the lorem ipsum abstract");
		testpublisher.setName("TitleTitleTitle");

		// Create contact:
		ContactModel testPublisherContact = new ContactModel();
		testPublisherContact.setName("Test Name");
		testPublisherContact.setEmail("a@a.com");
		testpublisher.addContact(testPublisherContact);

		// Add other contact
		ContactModel testPublisherContact2 = new ContactModel();
		testPublisherContact2.setName("Test Name 2");
		testPublisherContact2.setEmail("a2@a2.com");
		testpublisher.addContact(testPublisherContact2);

		// Add resource:
		DwcaResourceModel testResource1 = new DwcaResourceModel();
		testResource1.setName("Test resource 1");
		testResource1.setRecord_count(0);
		testResource1.setPublisher(testpublisher);
		testResource1.setSourcefileid("12345");
		testpublisher.addResource(testResource1);

		// Add resource:
		DwcaResourceModel testResource2 = new DwcaResourceModel();
		testResource2.setName("Test resource 2");
		testResource2.setRecord_count(100);
		testResource2.setPublisher(testpublisher);
		testResource2.setSourcefileid("54321");
		testpublisher.addResource(testResource2);

		// Save information
		assertTrue(publisherDAO.save(testpublisher));

		// Fetch ids from saved objects:
		Integer publisherId = testpublisher.getAuto_id();

		int contact1Id = testPublisherContact.getAuto_id();
		int contact2Id = testPublisherContact2.getAuto_id();

		int resource1Id = testResource1.getId();
		int resource2Id = testResource2.getId();

		PublisherModel loadedInformation = publisherDAO.load(publisherId);
		assertEquals("This is the lorem ipsum abstract", loadedInformation.getDescription());
		assertEquals("TitleTitleTitle", loadedInformation.getName());

		// Test contacts load:
		Set<ContactModel> publisherContacts = loadedInformation.getContacts();
		ContactModel loadedContact1 = null;
		ContactModel loadedContact2 = null;

		for (ContactModel contact : publisherContacts) {
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

		// Assert publisher_fkey is being filled:
		Integer fkey = jdbcTemplate.queryForObject("SELECT publisher_fkey FROM contact WHERE name =\'Test Name\'", Integer.class);
		assertEquals(fkey, publisherId);

		assertEquals("Test Name 2", loadedContact2.getName());
		assertEquals("a2@a2.com", loadedContact2.getEmail());

		// Assert publisher_fkey is being filled:
		fkey = jdbcTemplate.queryForObject("SELECT publisher_fkey FROM contact WHERE name =\'Test Name 2\'", Integer.class);
		assertEquals(fkey, publisherId);

		// Test resources load:
		Set<DwcaResourceModel> resources = loadedInformation.getResources();
		DwcaResourceModel loadedResource1 = null;
		DwcaResourceModel loadedResource2 = null;

		for (DwcaResourceModel resource : resources) {
			Integer id = resource.getId();
			if (id == resource1Id) {
				loadedResource1 = resource;
			}
			else if (id == resource2Id) {
				loadedResource2 = resource;
			}
			else {
				fail("Unknown resource for id: " + id);
			}
		}

		assertEquals("Test resource 1", loadedResource1.getName());
		assertEquals(new Integer(0), loadedResource1.getRecord_count());
		assertEquals(publisherId, loadedResource1.getPublisher().getAuto_id());
		// Assert publisher_fkey is being filled:
		Integer fkey1 = jdbcTemplate.queryForObject("SELECT publisher_fkey FROM dwca_resource WHERE name =\'Test resource 1\'", Integer.class);
		assertEquals(fkey1, publisherId);

		assertEquals("Test resource 2", loadedResource2.getName());
		assertEquals(new Integer(100), loadedResource2.getRecord_count());
		assertEquals(publisherId, loadedResource2.getPublisher().getAuto_id());
		// Assert publisher_fkey is being filled:
		fkey1 = jdbcTemplate.queryForObject("SELECT publisher_fkey FROM dwca_resource WHERE name =\'Test resource 2\'", Integer.class);
		assertEquals(fkey1, publisherId);

		// Test cascade deletion of information and all contacts after information deletion:
		assertTrue(publisherDAO.delete(loadedInformation));
		// Assert information was deleted:
		assertNull(publisherDAO.load(publisherId));

		// Assert contacts were also deleted
		Long contactCount = jdbcTemplate.queryForObject("SELECT count(*) FROM contact WHERE auto_id=" + contact1Id + " OR auto_id ="
				+ contact2Id, Long.class);
		assertEquals(0, contactCount.intValue());

		// Assert resource were !not! deleted
		Long resourceCount = jdbcTemplate.queryForObject("SELECT count(*) FROM dwca_resource WHERE id=" + resource1Id + " OR id ="
				+ resource2Id, Long.class);
		assertTrue(resourceCount.intValue() == 0);
	}

	@Test
	public void testLoadAll() {
		// Test publisher model:
		PublisherModel publisher1 = new PublisherModel();
		publisher1.setDescription("This is the lorem ipsum abstract1111");
		publisher1.setName("TitleTitleTitle1111");

		// Test publisher model:
		PublisherModel publisher2 = new PublisherModel();
		publisher2.setDescription("This is the lorem ipsum abstract2222");
		publisher2.setName("TitleTitleTitle222222");

		List<PublisherModel> publishers = new ArrayList<PublisherModel>();
		publishers.add(publisher1);
		publishers.add(publisher2);

		// Save information
		assertTrue(publisherDAO.save(publisher1));
		assertTrue(publisherDAO.save(publisher2));

		List<PublisherModel> loadedPublishers = publisherDAO.loadPublishers();
		assertTrue(publishers.equals(loadedPublishers));
	}

	@Test
	public void testLoadAllJDBC() {
		jdbcTemplate.update("DELETE FROM dwca_resource");
		jdbcTemplate.update("DELETE FROM publisher");

		// add controlled publisher information rows
		jdbcTemplate
				.update("INSERT INTO publisher (auto_id, name, description, phone, email) VALUES (1, 'Institution 1', 'The first institution', '123456789', 'mail@inst1.com')");
		jdbcTemplate
				.update("INSERT INTO publisher (auto_id, name, description, phone, email) VALUES (2, 'Institution 2', 'The second institution', '987654321', 'mail@inst2.com')");
		// add resources related to the publisher_informaiton
		jdbcTemplate
				.update("INSERT INTO dwca_resource (id, sourcefileid, name, record_count, publisher_fkey) VALUES (1, 's1', 'Resource 1', 123456789, 1)");
		jdbcTemplate
				.update("INSERT INTO dwca_resource (id, sourcefileid, name, record_count, publisher_fkey) VALUES (2, 's2', 'Resource 2', 23489, 1)");
		jdbcTemplate
				.update("INSERT INTO dwca_resource (id, sourcefileid, name, record_count, publisher_fkey) VALUES (3, 's3', 'Resource 3', 290, 2)");
		jdbcTemplate.update("INSERT INTO dwca_resource (id, sourcefileid, name, record_count, publisher_fkey) VALUES (4, 's4', 'Resource 4', 89, 2)");
		jdbcTemplate
				.update("INSERT INTO dwca_resource (id, sourcefileid, name, record_count, publisher_fkey) VALUES (5, 's5', 'Resource 5', 52089, 2)");
		List<Map<String, Object>> results = jdbcTemplate.queryForList("SELECT * FROM publisher");
		assertEquals(results.size(), 2);
		assertEquals(results.get(0).get("name"), "Institution 1");
		assertEquals(results.get(1).get("email"), "mail@inst2.com");
		List<PublisherModel> publishers = publisherDAO.loadPublishers();
		assertTrue(publishers.size() == 2);
	}
}