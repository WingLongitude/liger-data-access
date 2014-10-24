package net.canadensys.dataportal.occurrence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import net.canadensys.dataportal.occurrence.model.OccurrenceModel;
import net.canadensys.dataportal.occurrence.model.OccurrenceRawModel;
import net.canadensys.dataportal.occurrence.model.PublisherContactModel;
import net.canadensys.dataportal.occurrence.model.PublisherInformationModel;
import net.canadensys.query.QueryOperatorEnum;
import net.canadensys.query.SearchQueryPart;
import net.canadensys.query.TestSearchableFieldBuilder;

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
 * -Save PublisherInformationModel
 * -Get generated id
 * -Load PublisherInformationModel from id
 * -Delete PublisherInformationModel
 * 
 * @author Pedro Guimarães
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-spring.xml" })
@TransactionConfiguration(transactionManager = "hibernateTransactionManager")
public class PublisherInformationDAOTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private PublisherInformationDAO publisherInformationDAO;

	private JdbcTemplate jdbcTemplate;

	@Override
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Test
	public void testSaveLoadDelete() {

		// Test PublisherInformation model:
		PublisherInformationModel testPublisherInformation = new PublisherInformationModel();
		testPublisherInformation.setDescription("This is the lorem ipsum abstract");
		testPublisherInformation.setName("TitleTitleTitle");

		// Create contact:
		PublisherContactModel testPublisherContact = new PublisherContactModel();
		testPublisherContact.setName("Test Name");
		testPublisherContact.setEmail("a@a.com");
		testPublisherInformation.addContact(testPublisherContact);

		// Add other contact
		PublisherContactModel testPublisherContact2 = new PublisherContactModel();
		testPublisherContact2.setName("Test Name 2");
		testPublisherContact2.setEmail("a2@a2.com");
		testPublisherContact2.setPublisherInformation(testPublisherInformation);
		testPublisherInformation.addContact(testPublisherContact2);

		// Save information
		assertTrue(publisherInformationDAO.save(testPublisherInformation));

		// Fetch ids from saved objects:
		Integer publisherInformationId = testPublisherInformation.getAuto_id();
		int contact1Id = testPublisherContact.getAuto_id();
		int contact2Id = testPublisherContact2.getAuto_id();

		PublisherInformationModel loadedInformation = publisherInformationDAO.load(publisherInformationId);
		assertEquals("This is the lorem ipsum abstract", loadedInformation.getDescription());
		assertEquals("TitleTitleTitle", loadedInformation.getName());

		// Test contacts load:
		Set<PublisherContactModel> PublisherContacts = loadedInformation.getContacts();
		PublisherContactModel loadedContact1 = null;
		PublisherContactModel loadedContact2 = null;

		for (PublisherContactModel contact : PublisherContacts) {
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
		assertEquals(publisherInformationId, loadedContact1.getPublisherInformation().getAuto_id());
		// Assert Publisher_information_fkey is being filled:
		Integer fkey = jdbcTemplate.queryForObject("SELECT publisher_information_fkey FROM publisher_contact WHERE name =\'Test Name\'", Integer.class);
		assertEquals(fkey, publisherInformationId);

		assertEquals("Test Name 2", loadedContact2.getName());
		assertEquals("a2@a2.com", loadedContact2.getEmail());
		assertEquals(publisherInformationId, loadedContact2.getPublisherInformation().getAuto_id());
		// Assert Publisher_information_fkey is being filled:
		fkey = jdbcTemplate.queryForObject("SELECT publisher_information_fkey FROM publisher_contact WHERE name =\'Test Name 2\'", Integer.class);
		assertEquals(fkey, publisherInformationId);

		// Test cascade deletion of information and all contacts after information deletion:
		assertTrue(publisherInformationDAO.delete(loadedInformation));
		// Assert information was deleted:
		assertNull(publisherInformationDAO.load(publisherInformationId));

		// Assert contacts were also deleted
		Long contactCount = jdbcTemplate.queryForObject("SELECT count(*) FROM publisher_contact WHERE auto_id=" + contact1Id + " OR auto_id ="
				+ contact2Id, Long.class);
		assertEquals(0, contactCount.intValue());
	}
	
	@Test
	public void testLoadAll() {
		// Test PublisherInformation model:
		PublisherInformationModel publisher1 = new PublisherInformationModel();
		publisher1.setDescription("This is the lorem ipsum abstract1111");
		publisher1.setName("TitleTitleTitle1111");
		
		// Test PublisherInformation model:
		PublisherInformationModel publisher2 = new PublisherInformationModel();
		publisher2.setDescription("This is the lorem ipsum abstract2222");
		publisher2.setName("TitleTitleTitle222222");

		List<PublisherInformationModel> publishers = new ArrayList<PublisherInformationModel>();
		publishers.add(publisher1);
		publishers.add(publisher2);
		
		// Save information
		assertTrue(publisherInformationDAO.save(publisher1));
		assertTrue(publisherInformationDAO.save(publisher2));
		
		List<PublisherInformationModel> loadedPublishers = publisherInformationDAO.loadAll();
		assertTrue(publishers.equals(loadedPublishers));
	}
	
	@Test
	public void testLoadAllJDBC() {
		jdbcTemplate.update("DELETE FROM publisher_information");
		// add controlled rows
		jdbcTemplate.update("INSERT INTO publisher_information (auto_id, name, description, phone, email) VALUES (1, 'Institution 1', 'The first institution', '123456789', 'mail@inst1.com')");
		jdbcTemplate.update("INSERT INTO publisher_information (auto_id, name, description, phone, email) VALUES (2, 'Institution 2', 'The second institution', '987654321', 'mail@inst2.com')");
		
		List<Map<String,Object>> results = jdbcTemplate.queryForList("SELECT * FROM PUBLISHER_INFORMATION");
		assertEquals(results.size(), 2);
		assertEquals(results.get(0).get("name"), "Institution 1");
		assertEquals(results.get(1).get("email"), "mail@inst2.com");
	}
}