package net.canadensys.dataportal.occurrence.dao.impl;

import java.util.List;

import net.canadensys.dataportal.occurrence.dao.PublisherDAO;
import net.canadensys.dataportal.occurrence.model.PublisherModel;

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
@Repository("publisherDAO")
public class HibernatePublisherDAO implements PublisherDAO {

	// get log4j handler
	private static final Logger LOGGER = Logger.getLogger(HibernatePublisherDAO.class);
	private static final String MANAGED_ID = "auto_id";

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<PublisherModel> loadPublishers() {
		Criteria searchCriteria = sessionFactory.getCurrentSession().createCriteria(PublisherModel.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return searchCriteria.list();
	}

	@Override
	public boolean save(PublisherModel PublisherInformationModel) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(PublisherInformationModel);
		}
		catch (HibernateException e) {
			LOGGER.fatal("Couldn't save PublisherModel", e);
			return false;
		}
		return true;
	}

	@Override
	public PublisherModel load(Integer auto_id) {
		Criteria searchCriteria = sessionFactory.getCurrentSession().createCriteria(PublisherModel.class);
		searchCriteria.add(Restrictions.eq(MANAGED_ID, auto_id));
		return (PublisherModel) searchCriteria.uniqueResult();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean delete(PublisherModel PublisherInformationModel) {
		// Delete record:
		try {
			sessionFactory.getCurrentSession().delete(PublisherInformationModel);
		}
		catch (HibernateException e) {
			LOGGER.fatal("Couldn't delete PublisherModel" + PublisherInformationModel.toString(), e);
			return false;
		}
		return true;
	}
}