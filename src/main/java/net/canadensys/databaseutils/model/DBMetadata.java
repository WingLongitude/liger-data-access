package net.canadensys.databaseutils.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Model holding information about the database schema.
 * 
 * @author cgendreau
 * 
 */
@Entity
@Table(name = "db_metadata")
public class DBMetadata {

	@Id
	private String schema_version;
	private Date schema_version_date;

	public String getSchema_version() {
		return schema_version;
	}

	public void setSchema_version(String schema_version) {
		this.schema_version = schema_version;
	}

	public Date getSchema_version_date() {
		return schema_version_date;
	}

	public void setSchema_version_date(Date schema_version_date) {
		this.schema_version_date = schema_version_date;
	}

}
