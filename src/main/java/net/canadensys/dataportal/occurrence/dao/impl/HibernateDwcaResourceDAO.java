package net.canadensys.dataportal.occurrence.dao.impl;

import java.util.List;

import net.canadensys.dataportal.occurrence.dao.DwcaResourceDAO;
import net.canadensys.dataportal.occurrence.model.DwcaResourceModel;
import net.canadensys.dataportal.occurrence.model.OccurrenceFieldConstants;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dwcaResourceDAO")
public class HibernateDwcaResourceDAO implements DwcaResourceDAO {

	// get log4j handler
	private static final Logger LOGGER = Logger.getLogger(HibernateDwcaResourceDAO.class);
	private static final String GBIF_PACKAGE_ID = "gbif_package_id";

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<DwcaResourceModel> loadResources() {
		Criteria searchCriteria = sessionFactory.getCurrentSession().createCriteria(DwcaResourceModel.class);
		return searchCriteria.list();
	}

	@Override
	public DwcaResourceModel load(Integer auto_id) {
		Criteria searchCriteria = sessionFactory.getCurrentSession().createCriteria(DwcaResourceModel.class);
		searchCriteria.add(Restrictions.eq("id", auto_id));
		return (DwcaResourceModel) searchCriteria.uniqueResult();
	}

	@Override
	public DwcaResourceModel loadBySourceFileId(String sourceFileId) {
		Criteria searchCriteria = sessionFactory.getCurrentSession().createCriteria(DwcaResourceModel.class);
		searchCriteria.add(Restrictions.eq(OccurrenceFieldConstants.SOURCE_FILE_ID, sourceFileId));
		return (DwcaResourceModel) searchCriteria.uniqueResult();
	}

	public DwcaResourceModel loadByResourceUUID(String gbif_package_id) {
		Criteria searchCriteria = sessionFactory.getCurrentSession().createCriteria(DwcaResourceModel.class);
		searchCriteria.add(Restrictions.eq(GBIF_PACKAGE_ID, gbif_package_id));
		return (DwcaResourceModel) searchCriteria.uniqueResult();
	}

	@Override
	public boolean save(DwcaResourceModel resourceModel) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(resourceModel);
		}
		catch (HibernateException hEx) {
			LOGGER.fatal("Couldn't save DwcaResourceModel", hEx);
			return false;
		}
		return true;
	}

	@Override
	public boolean remove(DwcaResourceModel resourceModel) {
		try {
			sessionFactory.getCurrentSession().delete(resourceModel);
		}
		catch (HibernateException hEx) {
			LOGGER.fatal("Couldn't delete DwcaResourceModel", hEx);
			return false;
		}
		return true;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
