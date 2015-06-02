package net.canadensys.dataportal.occurrence.model;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import net.canadensys.databaseutils.hibernate.KeyValueType;

import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

/**
 * Model containing data related to a DarwinCore occurrence extension. Data are stored in a key/value map allowing to abstract specific extension
 * columns.
 * 
 * @author cgendreau
 * 
 */
@Entity
@Table(name = "occurrence_extension")
@TypeDefs({ @TypeDef(name = "KeyValueType", typeClass = KeyValueType.class) })
public class OccurrenceExtensionModel {

	@Id
	private long auto_id;

	@NaturalId
	@Column(name = "dwca_id")
	private String dwcaid;
	@NaturalId
	private String sourcefileid;
	@NaturalId
	private String gbif_package_id;

	private String ext_type;
	private String ext_version;

	@Type(type = "KeyValueType")
	private Map<String, String> ext_data;

	public long getAuto_id() {
		return auto_id;
	}

	public void setAuto_id(long auto_id) {
		this.auto_id = auto_id;
	}

	public String getDwcaid() {
		return dwcaid;
	}

	public void setDwcaid(String dwcaid) {
		this.dwcaid = dwcaid;
	}

	public String getSourcefileid() {
		return sourcefileid;
	}

	public void setSourcefileid(String sourcefileid) {
		this.sourcefileid = sourcefileid;
	}

	public String getGbif_package_id() {
		return gbif_package_id;
	}

	public void setGbif_package_id(String gbif_package_id) {
		this.gbif_package_id = gbif_package_id;
	}

	public String getExt_type() {
		return ext_type;
	}

	public void setExt_type(String ext_type) {
		this.ext_type = ext_type;
	}

	public String getExt_version() {
		return ext_version;
	}

	public void setExt_version(String ext_version) {
		this.ext_version = ext_version;
	}

	public Map<String, String> getExt_data() {
		return ext_data;
	}

	public void setExt_data(Map<String, String> ext_data) {
		this.ext_data = ext_data;
	}

}
