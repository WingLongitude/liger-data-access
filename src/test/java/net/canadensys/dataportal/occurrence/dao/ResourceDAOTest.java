package net.canadensys.dataportal.occurrence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import net.canadensys.dataportal.occurrence.model.ResourceModel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * Test Coverage : 
 * -Save ResourceModel
 * -Load ResourceModel from resource_uuid
 * @author canadensys
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-spring.xml" })
@TransactionConfiguration(transactionManager="hibernateTransactionManager")
public class ResourceDAOTest extends AbstractTransactionalJUnit4SpringContextTests{

	@Autowired
	private ResourceDAO resourceDAO;
	
	@Test
	public void testSaveAndLoad(){
		ResourceModel testModel = new ResourceModel();
		testModel.setResource_uuid("test_resource_uuid");
		assertTrue(resourceDAO.save(testModel));
		
		int id = testModel.getId();
		
		ResourceModel loadedModel = resourceDAO.load("test_resource_uuid");
		assertEquals(id,loadedModel.getId().intValue());
		assertEquals("test_resource_uuid",loadedModel.getResource_uuid());
	}
}
