package net.canadensys.dataportal.occurrence.model;

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

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "publisher_information")
@SequenceGenerator(name = "publisher_information_id_seq", sequenceName = "publisher_information_id_seq", allocationSize = 1)
public class PublisherInformationModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "publisher_information_id_seq")
	private Integer auto_id;
	
	private String name;
	private String description;
	private String address;
	private String city;
	private String administrative_area;
	private String postal_code;
	private String homepage;
	private String email;
	private String phone;
	private String logo_url;
	private Double decimallatitude;
	private Double decimallongitude;

	/**
	 * FetchType.EAGER will make contacts be always loaded. TODO: Add deepLoad condition to load() in DAO
	 */
	@JsonManagedReference
	@OneToMany(mappedBy = "publisherInformation", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<PublisherContactModel> contacts;

	public PublisherInformationModel() {
		contacts = new HashSet<PublisherContactModel>();
	}

	/**
	 * Add a publisherContactModel to the contact list.
	 * The reference to this publisherInformationModel instance will be set on the provided contact.
	 * 
	 * @param contact
	 */
	public void addContact(PublisherContactModel contact) {
		contact.setPublisherInformation(this);
		contacts.add(contact);
	}

	/** Getters and setters: **/

	public Integer getAuto_id() {
		return auto_id;
	}

	public void setAuto_id(Integer auto_id) {
		this.auto_id = auto_id;
	}

	public Set<PublisherContactModel> getContacts() {
		return contacts;
	}

	/**
	 * Contact list setter mainly used for Java Beans compliance.
	 * If you want to add contact, consider using addContact(publisherContactModel)
	 * 
	 * @param contacts
	 */
	public void setContacts(Set<PublisherContactModel> contacts) {
		this.contacts = contacts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAdministrative_area() {
		return administrative_area;
	}

	public void setAdministrative_area(String administrative_area) {
		this.administrative_area = administrative_area;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getLogo_url() {
		return logo_url;
	}

	public void setLogo_url(String logo_url) {
		this.logo_url = logo_url;
	}

	public Double getDecimallatitude() {
		return decimallatitude;
	}

	public void setDecimallatitude(Double decimallatitude) {
		this.decimallatitude = decimallatitude;
	}

	public Double getDecimallongitude() {
		return decimallongitude;
	}

	public void setDecimallongitude(Double decimallongitude) {
		this.decimallongitude = decimallongitude;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
