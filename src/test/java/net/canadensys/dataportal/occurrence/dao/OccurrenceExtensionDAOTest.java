package net.canadensys.dataportal.occurrence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.canadensys.databaseutils.H2Decode;
import net.canadensys.dataportal.occurrence.model.OccurrenceExtensionModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * This test is using a 'stub' hstore since hstore is PostgreSQL specific.
 * The trick to use it with h2 is available in src/test/resources/h2/h2setup.sql
 * Test Coverage :
 * -Insert extension data using jdbcTemplate
 * -Save OccurrenceExtensionModel
 * -Load OccurrenceExtensionModel from id
 * -Load OccurrenceExtensionModel list from extensionType, resourceUUID, dwcaID
 * 
 * @author canadensys
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-spring.xml" })
@TransactionConfiguration(transactionManager = "hibernateTransactionManager")
public class OccurrenceExtensionDAOTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private OccurrenceExtensionDAO occurrenceExtDAO;

	@Before
	public void setup() {
		// make sure the table is empty
		Map<String, String> map = H2Decode.toKeyValue("image_type=>png", "author=>darwin", "licence=>cc0");
		jdbcTemplate.update("DELETE FROM occurrence_extension");
		jdbcTemplate
				.update("INSERT INTO occurrence_extension (auto_id,dwca_id,sourcefileid,ext_type,ext_data) VALUES (1,'1','1111-1111','image', ?)",
						map);
	}

	@Test
	public void testSaveAndLoad() {

		OccurrenceExtensionModel occExtModel = new OccurrenceExtensionModel();
		occExtModel.setAuto_id(2l);
		occExtModel.setResource_id(2l);
		occExtModel.setDwcaid("2");
		occExtModel.setSourcefileid("source");
		occExtModel.setExt_type("image");
		Map<String, String> data = new HashMap<String, String>();
		data.put("licence", "cc-by");
		occExtModel.setExt_data(data);

		assertTrue(occurrenceExtDAO.save(occExtModel));

		// reload the model
		OccurrenceExtensionModel extModel = occurrenceExtDAO.load(2l);
		assertEquals("cc-by", extModel.getExt_data().get("licence"));

		// reload it by list
		List<OccurrenceExtensionModel> extModelList = occurrenceExtDAO.load("image", "source", "2");
		assertFalse(extModelList.isEmpty());
		assertEquals("cc-by", extModelList.get(0).getExt_data().get("licence"));
		assertEquals(2l, extModelList.get(0).getResource_id());

		// test that loading a non existing extension return an empty list and not null
		assertNotNull(occurrenceExtDAO.load("null", "????", "-7"));
	}

}
