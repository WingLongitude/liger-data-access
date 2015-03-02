package net.canadensys.dataportal.occurrence.dao.impl;

import net.canadensys.dataportal.occurrence.dao.ResourceMetadataDAO;
import net.canadensys.dataportal.occurrence.model.OccurrenceFieldConstants;
import net.canadensys.dataportal.occurrence.model.ResourceMetadataModel;

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
@Repository("resourceMetadataDAO")
public class HibernateResourceMetadataDAO implements ResourceMetadataDAO {

	// get log4j handler
	private static final Logger LOGGER = Logger.getLogger(HibernateResourceMetadataDAO.class);
	// the string "id" always points to the column annotated with @Id
	private static final String MANAGED_ID = "id";

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean save(ResourceMetadataModel resourceInformationModel) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(resourceInformationModel);
		}
		catch (HibernateException e) {
			LOGGER.fatal("Couldn't save ResourceMetadataModel", e);
			return false;
		}
		return true;
	}

	@Override
	public ResourceMetadataModel load(Integer dwca_resource_id) {
		Criteria searchCriteria = sessionFactory.getCurrentSession().createCriteria(ResourceMetadataModel.class);
		searchCriteria.add(Restrictions.eq(MANAGED_ID, dwca_resource_id));
		return (ResourceMetadataModel) searchCriteria.uniqueResult();
	}

	@Override
	public ResourceMetadataModel load(String gbif_package_id) {
		Criteria searchCriteria = sessionFactory.getCurrentSession().createCriteria(ResourceMetadataModel.class);
		searchCriteria.add(Restrictions.eq(OccurrenceFieldConstants.GBIF_PACKAGE_ID, gbif_package_id));
		return (ResourceMetadataModel) searchCriteria.uniqueResult();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean delete(ResourceMetadataModel resourceInformationModel) {
		// Delete record:
		try {
			sessionFactory.getCurrentSession().delete(resourceInformationModel);
		}
		catch (HibernateException e) {
			LOGGER.fatal("Couldn't delete ResourceMetadataModel" + resourceInformationModel.toString(), e);
			return false;
		}
		return true;
	}
}
