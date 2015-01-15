package net.canadensys.dataportal.occurrence.dao;

import java.util.List;

import net.canadensys.dataportal.occurrence.model.DwcaResourceModel;

public interface DwcaResourceDAO {

	/**
	 * Load all ResourceModel
	 * 
	 * @return ResourceModel list or null
	 */
	public List<DwcaResourceModel> loadResources();

	/**
	 * Load a ResourceModel from its auto_id
	 * 
	 * @param auto_id
	 * @return ResourceModel or null if nothing is found
	 */
	public DwcaResourceModel load(Integer auto_id);

	/**
	 * Load a DwcaResourceModel from a sourceFileId
	 * 
	 * @param sourceFileId
	 * @return ResourceModel or null if nothing is found
	 */
	public DwcaResourceModel loadBySourceFileId(String sourceFileId);

	/**
	 * Load a DwcaResourceModel from a resource_uuid
	 * 
	 * @param resource_uuid
	 * @return ResourceModel or null if nothing is found
	 */
	public DwcaResourceModel loadByResourceUUID(String resource_uuid);

	/**
	 * Save a ResourceModel
	 * 
	 * @param DwcaResourceModel
	 * @return success or not
	 */
	public boolean save(DwcaResourceModel resourceModel);

}
