package net.canadensys.dataportal.occurrence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Model of the contact of a specific resource.
 * 
 * @author Christian Gendreau, Pedro Guimarães
 */
@Entity
@Table(name = "contact")
@SequenceGenerator(name = "contact_id_seq", sequenceName = "contact_id_seq", allocationSize = 1)
public class ContactModel {

	public static final String CONTACT_TYPE_PUBLISHER = "publisher"; 
	public static final String CONTACT_TYPE_RESOURCE = "resource";
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contact_id_seq")
	private Integer auto_id;

	private String name;
	private String position_name;
	private String organization_name;
	private String address;
	private String city;
	private String administrative_area;
	private String country;
	private String postal_code;
	private String phone;
	private String email;
	// Tells if the contact is related to publisher or resource: 
	private String contact_type;
	// The role of the contact within its context (e.g. metadata_provider, resource_creator, agent, contact):
	private String role;
	
	/** Getters and setters: **/

	public Integer getAuto_id() {
		return auto_id;
	}

	public void setAuto_id(Integer auto_id) {
		this.auto_id = auto_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition_name() {
		return position_name;
	}

	public void setPosition_name(String position_name) {
		this.position_name = position_name;
	}

	public String getOrganization_name() {
		return organization_name;
	}

	public void setOrganization_name(String organization_name) {
		this.organization_name = organization_name;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact_type() {
		return contact_type;
	}

	public void setContact_type(String contact_type) {
		this.contact_type = contact_type;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}