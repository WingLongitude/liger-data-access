package net.canadensys.dataportal.occurrence.dao;

import java.util.List;

import net.canadensys.dataportal.occurrence.model.ResourceInformationModel;

/**
 * Interface for accessing resource information data.
 * @author Pedro Guimarães
 */
public interface ResourceInformationDAO {
	
	/**
	 * Save a ResourceInformationModel
	 * @param resourceInformationModel
	 * @return success or not
	 */
	public boolean save(ResourceInformationModel resourceInformationModel);
	
	/**
	 * Load a ResourceInformationModel from an id
	 * @param id
	 * @return ResourceInformationModel or null if nothing is found
	 */
	public ResourceInformationModel load(Integer id);
	
	/**
	 * Load a list of ResourceInformationModel for a sourcefileid.
	 * More than one Information could be linked to the same sourcefileid.
	 * @param sourcefileid
	 * @return ResourceInformationModel list or an empty list if nothing is found
	 */
	public List<ResourceInformationModel> load(String sourcefileid);
	
	/**
	 * Drop a ResourceInformation record from the table and all its associated resourceContacts;
	 * @param resourceInformationModel
	 */
	public boolean drop(ResourceInformationModel resourceInformationModel);

}
