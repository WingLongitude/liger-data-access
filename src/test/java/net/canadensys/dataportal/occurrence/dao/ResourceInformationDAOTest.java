package net.canadensys.dataportal.occurrence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import net.canadensys.dataportal.occurrence.model.ResourceContactModel;
import net.canadensys.dataportal.occurrence.model.ResourceInformationModel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * Test Coverage :
 * -Save ResourceInformationModel
 * -Get generated id
 * -Load ResourceInformationModel from id
 * 
 * @author Pedro Guimarães
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-spring.xml" })
@TransactionConfiguration(transactionManager = "hibernateTransactionManager")
public class ResourceInformationDAOTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private ResourceInformationDAO resourceInformationDAO;

	@Test
	public void testSaveLoadDelete() {
		// Test ResourceInformation model:
		ResourceInformationModel testResourceInformation = new ResourceInformationModel();
		testResourceInformation.set_abstract("This is the lorem ipsum abstract");
		testResourceInformation.setTitle("TitleTitleTitle");

		// Add contacts to the resource:
		Set<ResourceContactModel> contacts = new HashSet<ResourceContactModel>();

		// Create contact:
		ResourceContactModel testResourceContact = new ResourceContactModel();
		testResourceContact.setName("Test Name");
		testResourceContact.setEmail("a@a.com");
		contacts.add(testResourceContact);
		
		// Add other contact
		ResourceContactModel testResourceContact2 = new ResourceContactModel();
		testResourceContact2.setName("Test Name 2");
		testResourceContact2.setEmail("a2@a2.com");
		contacts.add(testResourceContact2);
		
		// Add contacts to information and save information
		testResourceInformation.setContacts(contacts);
		assertTrue(resourceInformationDAO.save(testResourceInformation));
		
		// Fetch ids from added objects:
		int informationId = testResourceInformation.getAuto_id();
		int contact1Id = testResourceContact.getAuto_id();
		int contact2Id = testResourceContact2.getAuto_id();
		
		ResourceInformationModel loadedInformation = resourceInformationDAO.load(informationId);
		assertEquals("This is the lorem ipsum abstract", loadedInformation.get_abstract());
		assertEquals("TitleTitleTitle", loadedInformation.getTitle());
		// Test contacts:
		assertEquals(contacts, loadedInformation.getContacts());

		// Test resourceInformation deletion:
		assertEquals(true, resourceInformationDAO.delete(loadedInformation));

		// Test contacts load:
		Set<ResourceContactModel> resourceContacts = loadedInformation.getContacts();
		for (ResourceContactModel contact : resourceContacts) {
			if (contact.getAuto_id() == contact1Id) {
				assertEquals("Test Name", contact.getName());
				assertEquals("a@a.com", contact.getEmail());
			}
			else if (contact.getAuto_id() == contact2Id) {
				assertEquals("Test Name 2", contact.getName());
				assertEquals("a2@a2.com", contact.getEmail());
			}
		}
		// Test cascade deletion of information and all contacts after information deletion:
		resourceInformationDAO.delete(loadedInformation);
		ResourceInformationModel deletedInformation = resourceInformationDAO.load(informationId);
		// Assert information was deleted:
		assertEquals(null, deletedInformation);
	}
}
