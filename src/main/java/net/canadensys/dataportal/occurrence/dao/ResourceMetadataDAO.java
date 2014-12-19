package net.canadensys.dataportal.occurrence.dao;

import net.canadensys.dataportal.occurrence.model.ResourceMetadataModel;

/**
 * Interface for accessing resource information data.
 * 
 * @author Pedro Guimarães
 */
public interface ResourceMetadataDAO {

	/**
	 * Save a ResourceMetadataModel
	 * 
	 * @param ResourceMetadataModel
	 * @return success or not
	 */
	public boolean save(ResourceMetadataModel resourceMetadataModel);

	/**
	 * Load a ResourceMetadataModel from an id
	 * 
	 * @param id
	 * @return ResourceMetadataModel or null if nothing is found
	 */
	public ResourceMetadataModel load(Integer dwca_resource_id);

	/**
	 * Load a ResourceMetadataModel from a resource_uuid
	 * 
	 * @param resource_uuid
	 * @return ResourceMetadataModel list or an empty list if nothing is found
	 */
	public ResourceMetadataModel load(String resource_uuid);

	/**
	 * Drop a ResourceMetadata record from the table and all its associated resourceContacts;
	 * 
	 * @param resourceMetadataModel
	 */
	public boolean delete(ResourceMetadataModel resourceMetadataModel);

}
