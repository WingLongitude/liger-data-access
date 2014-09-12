package net.canadensys.dataportal.occurrence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "resource_contact")
@SequenceGenerator(name = "resource_contact_id_seq", sequenceName = "resource_contact_id_seq", allocationSize=1)
public class ResourceContactModel {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "resource_contact_id_seq")
	private Integer id;
	
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
	
	/** Getters and setters: **/
	
	/**
	 * @return the id
	 */
	@Id
	@Column(name = "id")
	
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the position_name
	 */
	public String getPosition_name() {
		return position_name;
	}
	/**
	 * @param position_name the position_name to set
	 */
	public void setPosition_name(String position_name) {
		this.position_name = position_name;
	}
	/**
	 * @return the organization_name
	 */
	public String getOrganization_name() {
		return organization_name;
	}
	/**
	 * @param organization_name the organization_name to set
	 */
	public void setOrganization_name(String organization_name) {
		this.organization_name = organization_name;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the administrative_area
	 */
	public String getAdministrative_area() {
		return administrative_area;
	}
	/**
	 * @param administrative_area the administrative_area to set
	 */
	public void setAdministrative_area(String administrative_area) {
		this.administrative_area = administrative_area;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the postal_code
	 */
	public String getPostal_code() {
		return postal_code;
	}
	/**
	 * @param postal_code the postal_code to set
	 */
	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the resourceInformationModel
	 */
}
