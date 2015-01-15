package net.canadensys.databaseutils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import net.canadensys.databaseutils.model.DBMetadata;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-spring.xml" })
@TransactionConfiguration(transactionManager = "hibernateTransactionManager")
public class DBMetadataTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private SessionFactory sessionFactory;

	@Test
	public void loadDBMetadata() {
		try {
			Criteria searchCriteria = sessionFactory.getCurrentSession().createCriteria(DBMetadata.class);
			DBMetadata currentDBMetadata = (DBMetadata) searchCriteria.uniqueResult();
			assertNotNull(currentDBMetadata);
		}
		catch (HibernateException hEx) {
			fail();
		}

	}

}
