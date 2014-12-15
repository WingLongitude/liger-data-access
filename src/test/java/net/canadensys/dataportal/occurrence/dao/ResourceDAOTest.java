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
 * -Save ResourceModel
 * -Load ResourceModel from resource_uuid
 * -Load ResourceModel from auto_id
 * 
 * @author canadensys
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-spring.xml" })
@TransactionConfiguration(transactionManager = "hibernateTransactionManager")
public class ResourceDAOTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private DwcaResourceDAO resourceDAO;

	@Test
	public void testSaveAndLoad() {
		DwcaResourceModel testModel = new DwcaResourceModel();
		testModel.setSourcefileid("test_sourcefileid");
		assertTrue(resourceDAO.save(testModel));

		int id = testModel.getId();

		DwcaResourceModel loadedModel = resourceDAO.loadBySourceFileId("test_sourcefileid");
		assertEquals(id, loadedModel.getId().intValue());
		assertEquals("test_sourcefileid", loadedModel.getSourcefileid());

		DwcaResourceModel loadedById = resourceDAO.load(id);
		assertEquals(loadedModel, loadedById);
	}
}
