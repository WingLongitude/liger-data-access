package net.canadensys.dataportal.occurrence.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Lighter model (than OccurrenceRawModel) to hold processed data
 * 
 * @author canadensys
 */
@Entity
@Table(name = "occurrence")
public class OccurrenceModel {

	@Id
	private int auto_id;

	// id used in the dwca
	@Column(name = "dwca_id")
	private String dwcaid;

	private Integer resource_id;
	private String sourcefileid;

	@JoinColumn(name = "auto_id")
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	private OccurrenceRawModel rawModel;

	private String basisofrecord;
	private String catalognumber;
	private String othercatalognumbers;
	private String occurrenceid;
	private String collectioncode;
	private String bibliographiccitation;
	private String associatedmedia;
	private String _references;

	private String country;
	private String county;
	private String municipality;
	private String datasetname;

	private String kingdom;
	private String phylum;
	private String _class;
	private String _order;
	private String family;
	private String genus;
	private String specificepithet;
	private String infraspecificepithet;
	private String species;

	private String continent;
	private String locality;
	private String recordedby;
	private String recordnumber;
	private String institutioncode;
	private String scientificname;
	private String rawscientificname;
	private String scientificnameauthorship;
	private String stateprovince;
	private String taxonrank;
	private String typestatus;

	private String eventdate;
	private Date starteventdate;
	private Date endeventdate;
	private Integer sday;
	private Integer smonth;
	private Integer syear;
	private Integer eday;
	private Integer emonth;
	private Integer eyear;
	private Integer decade;

	private String associatedsequences;

	private Double minimumelevationinmeters;
	private Double maximumelevationinmeters;
	private Integer averagealtituderounded;

	private Double decimallatitude;
	private Double decimallongitude;
	private String datageneralizations;
	private Boolean hascoordinates;

	private Boolean hasmedia;
	private Boolean hastypestatus;
	private Boolean hasassociatedsequences;

	private String verbatimelevation;
	private String habitat;

	// name of the institution for filtering
	private String publishername;

	// name of the resource for filtering
	private String resourcename;

	public int getAuto_id() {
		return auto_id;
	}

	public void setAuto_id(int auto_id) {
		this.auto_id = auto_id;
	}

	public OccurrenceRawModel getRawModel() {
		return rawModel;
	}

	public void setRawModel(OccurrenceRawModel rawModel) {
		this.rawModel = rawModel;
	}

	public String getBasisofrecord() {
		return basisofrecord;
	}

	public void setBasisofrecord(String basisofrecord) {
		this.basisofrecord = basisofrecord;
	}

	public String getCatalognumber() {
		return catalognumber;
	}

	public void setCatalognumber(String catalognumber) {
		this.catalognumber = catalognumber;
	}

	public String getOthercatalognumbers() {
		return othercatalognumbers;
	}

	public void setOthercatalognumbers(String othercatalognumbers) {
		this.othercatalognumbers = othercatalognumbers;
	}

	public String getOccurrenceid() {
		return occurrenceid;
	}

	public void setOccurrenceid(String occurrenceid) {
		this.occurrenceid = occurrenceid;
	}

	public String getCollectioncode() {
		return collectioncode;
	}

	public void setCollectioncode(String collectioncode) {
		this.collectioncode = collectioncode;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDatasetname() {
		return datasetname;
	}

	public String getBibliographiccitation() {
		return bibliographiccitation;
	}

	public void setBibliographiccitation(String bibliographiccitation) {
		this.bibliographiccitation = bibliographiccitation;
	}

	public void setDatasetname(String datasetname) {
		this.datasetname = datasetname;
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

	public String getDatageneralizations() {
		return datageneralizations;
	}

	public void setDatageneralizations(String datageneralizations) {
		this.datageneralizations = datageneralizations;
	}

	public String getEventdate() {
		return eventdate;
	}

	public void setEventdate(String eventdate) {
		this.eventdate = eventdate;
	}

	public Date getStarteventdate() {
		return starteventdate;
	}

	public void setStarteventdate(Date starteventdate) {
		this.starteventdate = starteventdate;
	}

	public Date getEndeventdate() {
		return endeventdate;
	}

	public void setEndeventdate(Date endeventdate) {
		this.endeventdate = endeventdate;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getKingdom() {
		return kingdom;
	}

	public void setKingdom(String kingdom) {
		this.kingdom = kingdom;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String get_order() {
		return _order;
	}

	public void set_order(String _order) {
		this._order = _order;
	}

	public String getRecordedby() {
		return recordedby;
	}

	public void setRecordedby(String recordedby) {
		this.recordedby = recordedby;
	}

	public String getRecordnumber() {
		return recordnumber;
	}

	public void setRecordnumber(String recordnumber) {
		this.recordnumber = recordnumber;
	}

	public String getScientificname() {
		return scientificname;
	}

	public void setScientificname(String scientificname) {
		this.scientificname = scientificname;
	}

	public String getStateprovince() {
		return stateprovince;
	}

	public void setStateprovince(String stateprovince) {
		this.stateprovince = stateprovince;
	}

	public String getTaxonrank() {
		return taxonrank;
	}

	public void setTaxonrank(String taxonrank) {
		this.taxonrank = taxonrank;
	}

	public String getTypestatus() {
		return typestatus;
	}

	public void setTypestatus(String typestatus) {
		this.typestatus = typestatus;
	}

	public Double getMinimumelevationinmeters() {
		return minimumelevationinmeters;
	}

	public void setMinimumelevationinmeters(Double minimumelevationinmeters) {
		this.minimumelevationinmeters = minimumelevationinmeters;
	}

	public Double getMaximumelevationinmeters() {
		return maximumelevationinmeters;
	}

	public void setMaximumelevationinmeters(Double maximumelevationinmeters) {
		this.maximumelevationinmeters = maximumelevationinmeters;
	}

	public String getVerbatimelevation() {
		return verbatimelevation;
	}

	public void setVerbatimelevation(String verbatimelevation) {
		this.verbatimelevation = verbatimelevation;
	}

	public String getHabitat() {
		return habitat;
	}

	public void setHabitat(String habitat) {
		this.habitat = habitat;
	}

	public Integer getSday() {
		return sday;
	}

	public void setSday(Integer sday) {
		this.sday = sday;
	}

	public Integer getSmonth() {
		return smonth;
	}

	public void setSmonth(Integer smonth) {
		this.smonth = smonth;
	}

	public Integer getSyear() {
		return syear;
	}

	public void setSyear(Integer syear) {
		this.syear = syear;
	}

	public Integer getEday() {
		return eday;
	}

	public void setEday(Integer eday) {
		this.eday = eday;
	}

	public Integer getEmonth() {
		return emonth;
	}

	public void setEmonth(Integer emonth) {
		this.emonth = emonth;
	}

	public Integer getEyear() {
		return eyear;
	}

	public void setEyear(Integer eyear) {
		this.eyear = eyear;
	}

	public String getAssociatedsequences() {
		return associatedsequences;
	}

	public void setAssociatedsequences(String associatedsequences) {
		this.associatedsequences = associatedsequences;
	}

	public String getPhylum() {
		return phylum;
	}

	public void setPhylum(String phylum) {
		this.phylum = phylum;
	}

	public String get_class() {
		return _class;
	}

	public void set_class(String _class) {
		this._class = _class;
	}

	public String getGenus() {
		return genus;
	}

	public void setGenus(String genus) {
		this.genus = genus;
	}

	public String getSpecificepithet() {
		return specificepithet;
	}

	public void setSpecificepithet(String specificepithet) {
		this.specificepithet = specificepithet;
	}

	public String getInfraspecificepithet() {
		return infraspecificepithet;
	}

	public void setInfraspecificepithet(String infraspecificepithet) {
		this.infraspecificepithet = infraspecificepithet;
	}

	public String getAssociatedmedia() {
		return associatedmedia;
	}

	public void setAssociatedmedia(String associatedmedia) {
		this.associatedmedia = associatedmedia;
	}

	public String getScientificnameauthorship() {
		return scientificnameauthorship;
	}

	public void setScientificnameauthorship(String scientificnameauthorship) {
		this.scientificnameauthorship = scientificnameauthorship;
	}

	public Boolean getHascoordinates() {
		return hascoordinates;
	}

	public void setHascoordinates(Boolean hascoordinates) {
		this.hascoordinates = hascoordinates;
	}

	public Boolean getHasmedia() {
		return hasmedia;
	}

	public void setHasmedia(Boolean hasmedia) {
		this.hasmedia = hasmedia;
	}

	public Boolean getHastypestatus() {
		return hastypestatus;
	}

	public void setHastypestatus(Boolean hastypestatus) {
		this.hastypestatus = hastypestatus;
	}

	public Boolean getHasassociatedsequences() {
		return hasassociatedsequences;
	}

	public void setHasassociatedsequences(Boolean hasassociatedsequences) {
		this.hasassociatedsequences = hasassociatedsequences;
	}

	public String getInstitutioncode() {
		return institutioncode;
	}

	public void setInstitutioncode(String institutioncode) {
		this.institutioncode = institutioncode;
	}

	public String getRawscientificname() {
		return rawscientificname;
	}

	public void setRawscientificname(String rawscientificname) {
		this.rawscientificname = rawscientificname;
	}

	public String get_references() {
		return _references;
	}

	public void set_references(String _references) {
		this._references = _references;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getMunicipality() {
		return municipality;
	}

	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public Integer getDecade() {
		return decade;
	}

	public void setDecade(Integer decade) {
		this.decade = decade;
	}

	public Integer getAveragealtituderounded() {
		return averagealtituderounded;
	}

	public void setAveragealtituderounded(Integer averagealtituderounded) {
		this.averagealtituderounded = averagealtituderounded;
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

	public String getPublishername() {
		return publishername;
	}

	public void setPublishername(String publishername) {
		this.publishername = publishername;
	}

	public String getResourcename() {
		return resourcename;
	}

	public void setResourcename(String resourcename) {
		this.resourcename = resourcename;
	}

	public Integer getResource_id() {
		return resource_id;
	}

	public void setResource_id(Integer resource_id) {
		this.resource_id = resource_id;
	}
}
