package net.canadensys.dataportal.occurrence.dao.impl;

import java.util.List;

import net.canadensys.dataportal.occurrence.dao.ResourceInformationDAO;
import net.canadensys.dataportal.occurrence.model.OccurrenceFieldConstants;
import net.canadensys.dataportal.occurrence.model.ResourceInformationModel;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Pedro Guimarães
 * 
 */
@Repository("resourceInformationDAO")
public class HibernateResourceInformationDAO implements ResourceInformationDAO {

	// get log4j handler
	private static final Logger LOGGER = Logger.getLogger(HibernateResourceInformationDAO.class);
	private static final String MANAGED_ID = "auto_id";

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean save(ResourceInformationModel resourceInformationModel) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(resourceInformationModel);
		}
		catch (HibernateException e) {
			LOGGER.fatal("Couldn't save ResourceInformationModel", e);
			return false;
		}
		return true;
	}

	@Override
	public ResourceInformationModel load(Integer auto_id) {
		Criteria searchCriteria = sessionFactory.getCurrentSession().createCriteria(ResourceInformationModel.class);
		searchCriteria.add(Restrictions.eq(MANAGED_ID, auto_id));
		return (ResourceInformationModel) searchCriteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ResourceInformationModel> load(String resource_uuid) {
		Criteria searchCriteria = sessionFactory.getCurrentSession().createCriteria(ResourceInformationModel.class);
		searchCriteria.add(Restrictions.eq(OccurrenceFieldConstants.RESOURCE_UUID, resource_uuid));
		return searchCriteria.list();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean drop(ResourceInformationModel resourceInformationModel) {
		// Delete record:
		try {
			sessionFactory.getCurrentSession().delete(resourceInformationModel);
		}
		catch (HibernateException e) {
			LOGGER.fatal("Couldn't delete ResourceInformationModel" + resourceInformationModel.toString(), e);
			return false;
		}
		return true;
	}
}
