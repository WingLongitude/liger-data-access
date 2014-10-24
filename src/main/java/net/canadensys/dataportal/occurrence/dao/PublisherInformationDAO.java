package net.canadensys.dataportal.occurrence.dao;

import java.util.List;

import net.canadensys.dataportal.occurrence.model.PublisherInformationModel;

/**
 * Interface for accessing publisher information data.
 * 
 * @author Pedro Guimarães
 */
public interface PublisherInformationDAO {

	/**
	 * Save a publisherInformationModel
	 * 
	 * @param publisherInformationModel
	 * @return success or not
	 */
	public boolean save(PublisherInformationModel publisherInformationModel);

	/**
	 * Load a publisherInformationModel from an id
	 * 
	 * @param id
	 * @return publisherInformationModel or null if nothing is found
	 */
	public PublisherInformationModel load(Integer auto_id);
	
	/**
	 * Return all available publisher information records
	 * @return
	 */
	public List<PublisherInformationModel> loadAll();

	/**
	 * Drop a publisherInformation record from the table and all its associated publisherContacts
	 * 
	 * @param publisherInformationModel
	 */
	public boolean delete(PublisherInformationModel publisherInformationModel);

}
