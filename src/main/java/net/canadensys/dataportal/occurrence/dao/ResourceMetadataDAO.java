package net.canadensys.dataportal.occurrence.dao;

import net.canadensys.dataportal.occurrence.model.ResourceMetadataModel;

/**
 * Interface for accessing resource information data.
 * 
 * @author Pedro Guimarães
 * @author cgendreau
 */
public interface ResourceMetadataDAO {

	/**
	 * Possible values for ContactModel role attribute
	 * 
	 * @author cgendreau
	 * 
	 */
	public enum ContactRole {
		CONTACT("contact"),
		AGENT("agent"),
		METADATA_PROVIDER("metadata_provider"),
		RESOURCE_CREATOR("resource_creator");

		private String key;

		private ContactRole(String key) {
			this.key = key;
		}

		public String getKey() {
			return key;
		}

		/**
		 * Return the matching ContactRole or null.
		 * The matching is case insensitive.
		 * 
		 * @param key
		 * @return
		 */
		public static ContactRole fromKey(String key) {
			for (ContactRole curr : ContactRole.values()) {
				if (curr.key.equalsIgnoreCase(key)) {
					return curr;
				}
			}
			return null;
		}
	};

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
	public ResourceMetadataModel load(Integer resource_id);

	/**
	 * Load a ResourceMetadataModel from a gbif_package_id
	 * 
	 * @param gbif_package_id
	 * @return ResourceMetadataModel list or an empty list if nothing is found
	 */
	public ResourceMetadataModel load(String gbif_package_id);

	/**
	 * Drop a ResourceMetadata record from the table and all its associated resourceContacts;
	 * 
	 * @param resourceMetadataModel
	 */
	public boolean remove(ResourceMetadataModel resourceMetadataModel);

}
