package net.canadensys.dataportal.occurrence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.UUID;

import net.canadensys.dataportal.occurrence.model.ImportLogModel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * Test Coverage :
 * -Save ImportLogModel
 * -Get generated id
 * -Load ImportLogModel from id
 * 
 * @author canadensys
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-spring.xml" })
@TransactionConfiguration(transactionManager = "hibernateTransactionManager")
public class ImportLogDAOTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private ImportLogDAO importLogDAO;

	@Test
	public void testSaveAndLoad() {
		String gbif_package_id = UUID.randomUUID().toString();
		ImportLogModel testModel = new ImportLogModel();
		Date now = new Date();
		testModel.setGbif_package_id(gbif_package_id);
		testModel.setUpdated_by("me");
		testModel.setEvent_end_date_time(now);
		assertTrue(importLogDAO.save(testModel));

		int id = testModel.getId();

		ImportLogModel loadedModel = importLogDAO.load(id);
		assertEquals(gbif_package_id, loadedModel.getGbif_package_id());
		assertEquals("me", loadedModel.getUpdated_by());
		assertEquals(now, loadedModel.getEvent_end_date_time());

		// test importLogDAO.loadLastFrom
		testModel = new ImportLogModel();
		now = new Date();
		testModel.setGbif_package_id(gbif_package_id);
		testModel.setUpdated_by("me");
		testModel.setEvent_end_date_time(now);
		importLogDAO.save(testModel);

		loadedModel = importLogDAO.loadLastFromPackageId(gbif_package_id);
		assertEquals(now, loadedModel.getEvent_end_date_time());
	}
}
