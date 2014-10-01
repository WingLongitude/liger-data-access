package net.canadensys.dataportal.occurrence.dao;

import net.canadensys.dataportal.occurrence.model.ResourceInformationModel;

/**
 * Interface for accessing resource information data.
 * 
 * @author Pedro Guimarães
 */
public interface ResourceInformationDAO {

	/**
	 * Save a ResourceInformationModel
	 * 
	 * @param resourceInformationModel
	 * @return success or not
	 */
	public boolean save(ResourceInformationModel resourceInformationModel);

	/**
	 * Load a ResourceInformationModel from an id
	 * 
	 * @param id
	 * @return ResourceInformationModel or null if nothing is found
	 */
	public ResourceInformationModel load(Integer auto_id);

	/**
	 * Load a ResourceInformationModel from a resource_uuid
	 * 
	 * @param resource_uuid
	 * @return ResourceInformationModel list or an empty list if nothing is found
	 */
	public ResourceInformationModel load(String resource_uuid);

	/**
	 * Drop a ResourceInformation record from the table and all its associated resourceContacts;
	 * 
	 * @param resourceInformationModel
	 */
	public boolean delete(ResourceInformationModel resourceInformationModel);

}
