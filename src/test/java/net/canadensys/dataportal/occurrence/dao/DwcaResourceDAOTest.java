package net.canadensys.dataportal.occurrence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import net.canadensys.dataportal.occurrence.model.DwcaResourceModel;

import org.hibernate.SessionFactory;
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

	@Autowired
	private SessionFactory sessionFactory;

	@Test
	public void testSaveLoadRemove() {

		String sourceFileId = "source-file";
		String gbif_package_id = "42843f95-6fe3-47e4-bd0c-f4fcadca232ff";

		DwcaResourceModel testModel = new DwcaResourceModel();
		testModel.setGbif_package_id(gbif_package_id);
		testModel.setSourcefileid(sourceFileId);
		assertTrue(resourceDAO.save(testModel));

		int id = testModel.getId();

		DwcaResourceModel loadedModel = resourceDAO.loadBySourceFileId(sourceFileId);
		assertEquals(id, loadedModel.getId().intValue());

		loadedModel = resourceDAO.loadByResourceUUID(gbif_package_id);
		assertEquals(id, loadedModel.getId().intValue());

		DwcaResourceModel loadedById = resourceDAO.load(id);
		assertEquals(loadedModel, loadedById);

		// Test removal of the dwca_resource object
		assertTrue(resourceDAO.remove(loadedModel));

		// Synchronize session state with the database
		sessionFactory.getCurrentSession().flush();

		Long count = jdbcTemplate.queryForObject("SELECT count(*) FROM dwca_resource WHERE gbif_package_id = '" + gbif_package_id + "'",
				Long.class);
		assertEquals(0, count.intValue());
	}
}
