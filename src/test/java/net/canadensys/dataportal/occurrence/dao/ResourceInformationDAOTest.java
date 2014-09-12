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
 * @author Pedro Guimarães
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-spring.xml" })
@TransactionConfiguration(transactionManager="hibernateTransactionManager")
public class ResourceInformationDAOTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	private ResourceInformationDAO resourceInformationDAO;
	
	@Autowired
	private ResourceContactDAO resourceContactDAO;
	
	@Test
	public void testSaveLoadDelete(){
		// Create contact:
		ResourceContactModel testResourceContact = new ResourceContactModel();
		testResourceContact.setName("Test Name");
		testResourceContact.setEmail("a@a.com");
		assertTrue(resourceContactDAO.save(testResourceContact));
		
		int contactId = testResourceContact.getId();		
		ResourceContactModel loadedResourceContact = resourceContactDAO.load(contactId);
		assertEquals("Test Name",loadedResourceContact.getName());
		assertEquals("a@a.com",loadedResourceContact.getEmail());
		
		// Test ResourceInformation model:
		ResourceInformationModel testResourceInformation = new ResourceInformationModel();
		testResourceInformation.set_abstract("This is the lorem ipsum abstract");
		testResourceInformation.setTitle("TitleTitleTitle");
		// Add contact to the resource:
		Set<ResourceContactModel> contacts = new HashSet<ResourceContactModel>();
		contacts.add(loadedResourceContact);
		
		testResourceInformation.setContacts(contacts);
		assertTrue(resourceInformationDAO.save(testResourceInformation));
		
		int informationId = testResourceInformation.getId();
		ResourceInformationModel loadedInformation = resourceInformationDAO.load(informationId);
		assertEquals("This is the lorem ipsum abstract",loadedInformation.get_abstract());
		assertEquals("TitleTitleTitle",loadedInformation.getTitle());
		// Test contacts:
		assertEquals(contacts, loadedInformation.getContacts());
		
		// Test resourceInformation deletion:
		assertEquals(true, resourceInformationDAO.drop(loadedInformation));
		ResourceInformationModel deletedResourceInformation = resourceInformationDAO.load(informationId);
		assertEquals(null, deletedResourceInformation);
		
		// Test resourceContacts deletion:
		ResourceContactModel deletedResourceContact = resourceContactDAO.load(contactId);
		assertEquals(null, deletedResourceContact);
		
	}
}
