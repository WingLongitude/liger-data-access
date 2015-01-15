package net.canadensys.dataportal.occurrence.dao;

import java.util.List;

import net.canadensys.dataportal.occurrence.model.PublisherModel;

/**
 * Interface for accessing publisher information data.
 * 
 * @author Pedro Guimarães
 */
public interface PublisherDAO {

	/**
	 * Save a publisherModel
	 * 
	 * @param publisherModel
	 * @return success or not
	 */
	public boolean save(PublisherModel publisherModel);

	/**
	 * Load a publisherModel from an id
	 * 
	 * @param id
	 * @return publisherModel or null if nothing is found
	 */
	public PublisherModel load(Integer auto_id);
	
	/**
	 * Return all available publisher information records
	 * @return
	 */
	public List<PublisherModel> loadPublishers();

	/**
	 * Drop a publisher record from the table and all its associated publisherContacts
	 * 
	 * @param publisherModel
	 */
	public boolean delete(PublisherModel publisherModel);

}
