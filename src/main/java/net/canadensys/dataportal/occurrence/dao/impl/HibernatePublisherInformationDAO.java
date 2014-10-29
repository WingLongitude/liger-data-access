package net.canadensys.dataportal.occurrence.dao.impl;

import java.util.List;

import net.canadensys.dataportal.occurrence.dao.PublisherInformationDAO;
import net.canadensys.dataportal.occurrence.model.PublisherInformationModel;

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
@Repository("publisherInformationDAO")
public class HibernatePublisherInformationDAO implements PublisherInformationDAO {

	// get log4j handler
	private static final Logger LOGGER = Logger.getLogger(HibernatePublisherInformationDAO.class);
	private static final String MANAGED_ID = "auto_id";

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<PublisherInformationModel> loadPublishers() {
		Criteria searchCriteria = sessionFactory.getCurrentSession().createCriteria(PublisherInformationModel.class);
		return searchCriteria.list();
	}
	
	@Override
	public boolean save(PublisherInformationModel PublisherInformationModel) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(PublisherInformationModel);
		}
		catch (HibernateException e) {
			LOGGER.fatal("Couldn't save PublisherInformationModel", e);
			return false;
		}
		return true;
	}

	@Override
	public PublisherInformationModel load(Integer auto_id) {
		Criteria searchCriteria = sessionFactory.getCurrentSession().createCriteria(PublisherInformationModel.class);
		searchCriteria.add(Restrictions.eq(MANAGED_ID, auto_id));
		return (PublisherInformationModel) searchCriteria.uniqueResult();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean delete(PublisherInformationModel PublisherInformationModel) {
		// Delete record:
		try {
			sessionFactory.getCurrentSession().delete(PublisherInformationModel);
		}
		catch (HibernateException e) {
			LOGGER.fatal("Couldn't delete PublisherInformationModel" + PublisherInformationModel.toString(), e);
			return false;
		}
		return true;
	}
}