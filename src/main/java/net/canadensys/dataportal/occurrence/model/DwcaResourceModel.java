package net.canadensys.dataportal.occurrence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Model to keep info about resources. Resource represents the source archive.
 * 
 * @author canadensys
 * 
 */
@Entity
@Table(name = "dwca_resource")
@SequenceGenerator(name = "dwca_resource_id_seq", sequenceName = "dwca_resource_id_seq", allocationSize = 1)
public class DwcaResourceModel {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dwca_resource_id_seq")
	private Integer id;
	private String name;
	private String sourcefileid;
	private String resource_uuid;
	private String archive_url;
	private Integer record_count;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "publisher_fkey")
	private PublisherModel publisher;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getResource_uuid() {
		return resource_uuid;
	}

	public void setResource_uuid(String resource_uuid) {
		this.resource_uuid = resource_uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArchive_url() {
		return archive_url;
	}

	public void setArchive_url(String archive_url) {
		this.archive_url = archive_url;
	}

	public String getSourcefileid() {
		return sourcefileid;
	}

	public void setSourcefileid(String sourcefileid) {
		this.sourcefileid = sourcefileid;
	}

	public Integer getRecord_count() {
		return record_count;
	}

	public void setRecord_count(Integer record_count) {
		this.record_count = record_count;
	}

	public PublisherModel getPublisher() {
		return publisher;
	}

	public void setPublisher(PublisherModel publisher) {
		this.publisher = publisher;
	}
}
