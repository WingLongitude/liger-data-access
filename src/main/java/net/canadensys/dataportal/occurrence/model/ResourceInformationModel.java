package net.canadensys.dataportal.occurrence.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "resource_information")
@SequenceGenerator(name = "resource_information_id_seq", sequenceName = "resource_information_id_seq", allocationSize = 1)
public class ResourceInformationModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resource_information_id_seq")
	private Integer auto_id;

	private String resource_uuid;
	private String resource_name;
	private String alternate_identifier;
	private String title;
	private Date publication_date;
	private String language;
	private String _abstract;
	private String keyword;
	private String keyword_thesaurus;
	private String intellectual_rights;
	private String citation;
	private String hierarchy_level;
	private String resource_logo_url;
	private String collection_identifier;
	private String parent_collection_identifier;
	private String collection_name;

	/**
	 * FetchType.EAGER will make contacts be always loaded. TODO: Add deepLoad condition to load() in DAO
	 */
	@OneToMany(mappedBy = "resourceInformation", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<ResourceContactModel> contacts;

	public ResourceInformationModel() {
		contacts = new HashSet<ResourceContactModel>();
	}

	/**
	 * Add a ResourceContactModel to the contact list.
	 * The reference to this ResourceInformationModel instance will be set on the provided contact.
	 * 
	 * @param contact
	 */
	public void addContact(ResourceContactModel contact) {
		contact.setResourceInformation(this);
		contacts.add(contact);
	}

	/** Getters and setters: **/

	public Integer getAuto_id() {
		return auto_id;
	}

	public void setAuto_id(Integer auto_id) {
		this.auto_id = auto_id;
	}

	public String getResource_uuid() {
		return resource_uuid;
	}

	public void setResource_uuid(String resource_uuid) {
		this.resource_uuid = resource_uuid;
	}

	public String getResource_name() {
		return resource_name;
	}

	public void setResource_name(String resource_name) {
		this.resource_name = resource_name;
	}

	public String getAlternate_identifier() {
		return alternate_identifier;
	}

	public void setAlternate_identifier(String alternate_identifier) {
		this.alternate_identifier = alternate_identifier;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getPublication_date() {
		return publication_date;
	}

	public void setPublication_date(Date publication_date) {
		this.publication_date = publication_date;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String get_abstract() {
		return _abstract;
	}

	public void set_abstract(String _abstract) {
		this._abstract = _abstract;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getKeyword_thesaurus() {
		return keyword_thesaurus;
	}

	public void setKeyword_thesaurus(String keyword_thesaurus) {
		this.keyword_thesaurus = keyword_thesaurus;
	}

	public String getIntellectual_rights() {
		return intellectual_rights;
	}

	public void setIntellectual_rights(String intellectual_rights) {
		this.intellectual_rights = intellectual_rights;
	}

	public String getCitation() {
		return citation;
	}

	public void setCitation(String citation) {
		this.citation = citation;
	}

	public String getHierarchy_level() {
		return hierarchy_level;
	}

	public void setHierarchy_level(String hierarchy_level) {
		this.hierarchy_level = hierarchy_level;
	}

	public String getResource_logo_url() {
		return resource_logo_url;
	}

	public void setResource_logo_url(String resource_logo_url) {
		this.resource_logo_url = resource_logo_url;
	}

	public String getCollection_identifier() {
		return collection_identifier;
	}

	public void setCollection_identifier(String collection_identifier) {
		this.collection_identifier = collection_identifier;
	}

	public String getParent_collection_identifier() {
		return parent_collection_identifier;
	}

	public void setParent_collection_identifier(String parent_collection_identifier) {
		this.parent_collection_identifier = parent_collection_identifier;
	}

	public String getCollection_name() {
		return collection_name;
	}

	public void setCollection_name(String collection_name) {
		this.collection_name = collection_name;
	}

	public Set<ResourceContactModel> getContacts() {
		return contacts;
	}

	/**
	 * Contact list setter mainly used for Java Beans compliance.
	 * If you want to add contact, consider using addContact(ResourceContactModel)
	 * 
	 * @param contacts
	 */
	public void setContacts(Set<ResourceContactModel> contacts) {
		this.contacts = contacts;
	}
}
