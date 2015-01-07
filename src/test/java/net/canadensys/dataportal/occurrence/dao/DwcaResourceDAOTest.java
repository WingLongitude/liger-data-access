package net.canadensys.dataportal.occurrence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import net.canadensys.dataportal.occurrence.model.DwcaResourceModel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * Test Coverage :
 * -Save DwcaResourceModel
 * -Load DwcaResourceModel from resource_uuid
 * -Load DwcaResourceModel from auto_id
 * 
 * @author canadensys
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-spring.xml" })
@TransactionConfiguration(transactionManager = "hibernateTransactionManager")
public class DwcaResourceDAOTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private DwcaResourceDAO resourceDAO;

	@Test
	public void testSaveAndLoad() {

		String resource_uuid = "42843f95-6fe3-47e4-bd0c-f4fcadca232f";

		DwcaResourceModel testModel = new DwcaResourceModel();
		testModel.setResource_uuid(resource_uuid);
		testModel.setSourcefileid(resource_uuid);
		assertTrue(resourceDAO.save(testModel));

		int id = testModel.getId();

		DwcaResourceModel loadedModel = resourceDAO.loadBySourceFileId(resource_uuid);
		assertEquals(resource_uuid, loadedModel.getSourcefileid());

		DwcaResourceModel loadedById = resourceDAO.load(id);
		assertEquals(loadedModel, loadedById);
	}
}
