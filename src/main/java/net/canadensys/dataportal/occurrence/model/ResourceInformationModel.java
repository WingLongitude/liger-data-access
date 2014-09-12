package net.canadensys.dataportal.occurrence.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "resource_information")
@SequenceGenerator(name = "resource_information_id_seq", sequenceName = "resource_information_id_seq", allocationSize=1)
public class ResourceInformationModel {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "resource_information_id_seq")
	private Integer id;
	
	private String sourcefileid;
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

	@JoinColumn(name="id")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<ResourceContactModel> contacts = new HashSet(0);
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * @return the sourceFileId
	 */
	public String getSourcefileid() {
		return sourcefileid;
	}
	/**
	 * @param the sourceFileId to set
	 */
	public void setSourcefileid(String sourcefileid) {
		this.sourcefileid = sourcefileid;
	}
	
	public String getResource_name() {
		return resource_name;
	}
	public void setResource_name(String resource_name) {
		this.resource_name = resource_name;
	}
	/**
	 * @return the alternate_identifier
	 */
	public String getAlternate_identifier() {
		return alternate_identifier;
	}
	/**
	 * @param alternate_identifier the alternate_identifier to set
	 */
	public void setAlternate_identifier(String alternate_identifier) {
		this.alternate_identifier = alternate_identifier;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the pub_date
	 */
	@Temporal(TemporalType.DATE)
    @Column(name = "publication_date")
	public Date getPublication_date() {
		return publication_date;
	}
	/**
	 * @param pub_date the pub_date to set
	 */
	public void setPublication_date(Date publication_date) {
		this.publication_date = publication_date;
	}
	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}
	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	/**
	 * @return the _abstract
	 */
	public String get_abstract() {
		return _abstract;
	}
	/**
	 * @param _abstract the _abstract to set
	 */
	public void set_abstract(String _abstract) {
		this._abstract = _abstract;
	}
	/**
	 * @return the keyword
	 */
	public String getKeyword() {
		return keyword;
	}
	/**
	 * @param keyword the keyword to set
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	/**
	 * @return the keyword_thesaurus
	 */
	public String getKeyword_thesaurus() {
		return keyword_thesaurus;
	}
	/**
	 * @param keyword_thesaurus the keyword_thesaurus to set
	 */
	public void setKeyword_thesaurus(String keyword_thesaurus) {
		this.keyword_thesaurus = keyword_thesaurus;
	}
	/**
	 * @return the intellectual_rights
	 */
	public String getIntellectual_rights() {
		return intellectual_rights;
	}
	/**
	 * @param intellectual_rights the intellectual_rights to set
	 */
	public void setIntellectual_rights(String intellectual_rights) {
		this.intellectual_rights = intellectual_rights;
	}
	/**
	 * @return the citation
	 */
	public String getCitation() {
		return citation;
	}
	/**
	 * @param citation the citation to set
	 */
	public void setCitation(String citation) {
		this.citation = citation;
	}
	/**
	 * @return the hirarchy_level
	 */
	public String getHirarchy_level() {
		return hierarchy_level;
	}
	/**
	 * @param hirarchy_level the hirarchy_level to set
	 */
	public void setHirarchy_level(String hirarchy_level) {
		this.hierarchy_level = hirarchy_level;
	}
	/**
	 * @return the parent_collection_identifier
	 */
	public String getParent_collection_identifier() {
		return parent_collection_identifier;
	}
	/**
	 * @param parent_collection_identifier the parent_collection_identifier to set
	 */
	public void setParent_collection_identifier(String parent_collection_identifier) {
		this.parent_collection_identifier = parent_collection_identifier;
	}
	/**
	 * @return the collection_name
	 */
	public String getCollection_name() {
		return collection_name;
	}
	/**
	 * @param collection_name the collection_name to set
	 */
	public void setCollection_name(String collection_name) {
		this.collection_name = collection_name;
	}
	/**
	 * @return the hierarchy_level
	 */
	public String getHierarchy_level() {
		return hierarchy_level;
	}
	/**
	 * @param hierarchy_level the hierarchy_level to set
	 */
	public void setHierarchy_level(String hierarchy_level) {
		this.hierarchy_level = hierarchy_level;
	}
	/**
	 * @return the resource_logo_url
	 */
	public String getResource_logo_url() {
		return resource_logo_url;
	}
	/**
	 * @param resource_logo_url the resource_logo_url to set
	 */
	public void setResource_logo_url(String resource_logo_url) {
		this.resource_logo_url = resource_logo_url;
	}
	/**
	 * @return the collection_identifier
	 */
	public String getCollection_identifier() {
		return collection_identifier;
	}
	/**
	 * @param collection_identifier the collection_identifier to set
	 */
	public void setCollection_identifier(String collection_identifier) {
		this.collection_identifier = collection_identifier;
	}
	/**
	 * @return the contacts
	 */
	public Set<ResourceContactModel> getContacts() {
		return contacts;
	}
	/**
	 * @param contacts the contacts to set
	 */
	public void setContacts(Set<ResourceContactModel> contacts) {
		this.contacts = contacts;
	}
	
}
