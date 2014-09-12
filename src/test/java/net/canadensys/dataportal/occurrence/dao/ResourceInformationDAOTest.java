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

	@Autowired
	private ResourceContactDAO resourceContactDAO;

	@Test
	public void testSaveLoadDelete() {
		// Create contact:
		ResourceContactModel testResourceContact = new ResourceContactModel();
		testResourceContact.setName("Test Name");
		testResourceContact.setEmail("a@a.com");
		assertTrue(resourceContactDAO.save(testResourceContact));

		int contactId = testResourceContact.getAuto_id();
		ResourceContactModel loadedResourceContact = resourceContactDAO.load(contactId);
		assertEquals("Test Name", loadedResourceContact.getName());
		assertEquals("a@a.com", loadedResourceContact.getEmail());

		// Add other contact
		ResourceContactModel testResourceContact2 = new ResourceContactModel();
		testResourceContact2.setName("Test Name 2");
		testResourceContact2.setEmail("a2@a2.com");
		assertTrue(resourceContactDAO.save(testResourceContact));

		// Test ResourceInformation model:
		ResourceInformationModel testResourceInformation = new ResourceInformationModel();
		testResourceInformation.set_abstract("This is the lorem ipsum abstract");
		testResourceInformation.setTitle("TitleTitleTitle");
		// Add contacts to the resource:
		Set<ResourceContactModel> contacts = new HashSet<ResourceContactModel>();
		contacts.add(loadedResourceContact);
		contacts.add(testResourceContact2);

		testResourceInformation.setContacts(contacts);
		assertTrue(resourceInformationDAO.save(testResourceInformation));

		int informationId = testResourceInformation.getAuto_id();
		ResourceInformationModel loadedInformation = resourceInformationDAO.load(informationId);
		assertEquals("This is the lorem ipsum abstract", loadedInformation.get_abstract());
		assertEquals("TitleTitleTitle", loadedInformation.getTitle());
		// Test contacts:
		assertEquals(contacts, loadedInformation.getContacts());

		// Test resourceInformation deletion:
		assertEquals(true, resourceInformationDAO.drop(loadedInformation));

		ResourceInformationModel deletedResourceInformation = resourceInformationDAO.load(informationId);
		assertEquals(null, deletedResourceInformation);

		// Test cascade deletion of all contacts after:
		ResourceContactModel deletedResourceContact = resourceContactDAO.load(contactId);
		// First contact:
		assertEquals(null, deletedResourceContact);
		// Second contact:
		deletedResourceContact = resourceContactDAO.load(testResourceContact2.getAuto_id());
		assertEquals(null, deletedResourceContact);
	}
}
