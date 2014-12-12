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
	 * Load a ResourceModel from its auto_id
	 * 
	 * @param auto_id
	 * @return ResourceModel or null if nothing is found
	 */
	public ResourceModel load(Integer auto_id);
	
	/**
	 * Load a ResourceModel from a resource_uuid
	 * 
	 * @param resource_uuid
	 * @return ResourceModel or null if nothing is found
	 */
	public ResourceModel loadBySourceFileId(String resource_uuid);

	/**
	 * Save a ResourceModel
	 * 
	 * @param ResourceModel
	 * @return success or not
	 */
	public boolean save(ResourceModel resourceModel);

}
