package net.canadensys.dataportal.occurrence.dao;

import java.util.List;

import net.canadensys.dataportal.occurrence.model.ResourceModel;

public interface ResourceDAO {

	/**
	 * Load all ResourceModel
	 * 
	 * @return ResourceModel list or null
	 */
	public List<ResourceModel> loadResources();

	/**
	 * Load a ResourceModel from a resource_uuid
	 * 
	 * @param resource_uuid
	 * @return ResourceModel or null if nothing is found
	 */
	public ResourceModel load(String resource_uuid);
	
	/**
	 * Load a ResourceModel from it's auto_id
	 * 
	 * @param resource_uuid
	 * @return ResourceModel or null if nothing is found
	 */
	public ResourceModel loadByAutoId(String auto_id);

	/**
	 * Save a ResourceModel
	 * 
	 * @param ResourceModel
	 * @return success or not
	 */
	public boolean save(ResourceModel resourceModel);

}
