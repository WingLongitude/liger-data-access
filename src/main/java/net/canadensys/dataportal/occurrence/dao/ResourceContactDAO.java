package net.canadensys.dataportal.occurrence.dao;

import net.canadensys.dataportal.occurrence.model.ResourceContactModel;

/**
 * Interface for accessing resource contact data.
 * 
 * @author canadensys
 */
public interface ResourceContactDAO {

	/**
	 * Save a ResourceContactModel
	 * 
	 * @param resourceContactModel
	 * @return success or not
	 */
	public boolean save(ResourceContactModel resourceContactModel);

	/**
	 * Load a ResourceContactModel from an id
	 * 
	 * @param id
	 * @return ResourceContactModel or null if nothing is found
	 */
	public ResourceContactModel load(Integer id);
}
