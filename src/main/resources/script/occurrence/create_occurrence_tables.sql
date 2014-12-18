CREATE TABLE IF NOT EXISTS occurrence (
auto_id INTEGER NOT NULL,
associatedmedia TEXT,
associatedsequences TEXT,
basisofrecord TEXT,
bibliographiccitation TEXT,
catalogNumber TEXT,
occurrenceid TEXT,
_references TEXT,
collectionCode TEXT,
continent TEXT,
country TEXT,
county TEXT,
municipality TEXT,
datasetName TEXT,
habitat TEXT,
locality TEXT,
kingdom TEXT,
phylum TEXT,
_class TEXT,
_order TEXT,
family TEXT,
genus TEXT,
specificepithet TEXT,
infraspecificepithet TEXT,
species TEXT,
recordedBy TEXT,
recordNumber TEXT,
institutioncode TEXT,
scientificName TEXT,
rawscientificname TEXT,
scientificnameauthorship TEXT,
stateProvince TEXT,
typeStatus TEXT,
taxonRank TEXT,
verbatimElevation TEXT,
minimumelevationinmeters DOUBLE PRECISION,
maximumelevationinmeters DOUBLE PRECISION,
decimallatitude DOUBLE PRECISION,
decimallongitude DOUBLE PRECISION,
eventdate TEXT,
starteventdate DATE,
endeventdate DATE,
sday smallint,
smonth smallint,
syear smallint,
eday smallint,
emonth smallint,
eyear smallint,
decade smallint,
averagealtituderounded integer,
hascoordinates boolean,
hasmedia boolean,
hastypestatus boolean,
hasassociatedsequences boolean,
sourcefileid TEXT,
publishername TEXT,
resourcename TEXT,
dwcaid TEXT,
CONSTRAINT occurrence_pkey PRIMARY KEY (auto_id )
);

CREATE TABLE IF NOT EXISTS occurrence_raw (
auto_id INTEGER NOT NULL,
dwcaid character varying(255),
sourcefileid character varying(255) NOT NULL,
acceptedNameUsage TEXT,
acceptedNameUsageID TEXT,
accessRights TEXT,
associatedMedia TEXT,
associatedOccurrences TEXT,
associatedReferences TEXT,
associatedSequences TEXT,
associatedTaxa TEXT,
basisOfRecord TEXT,
bed TEXT,
behavior TEXT,
bibliographicCitation TEXT,
catalogNumber TEXT,
_class TEXT,
collectionCode TEXT,
collectionID TEXT,
continent TEXT,
coordinatePrecision TEXT,
coordinateUncertaintyInMeters TEXT,
country TEXT,
countryCode TEXT,
county TEXT,
dataGeneralizations TEXT,
datasetID TEXT,
datasetName TEXT,
dateIdentified TEXT,
day TEXT,
decimalLatitude TEXT,
decimalLongitude TEXT,
disposition TEXT,
dynamicProperties TEXT,
earliestAgeOrLowestStage TEXT,
earliestEonOrLowestEonothem TEXT,
earliestEpochOrLowestSeries TEXT,
earliestEraOrLowestErathem TEXT,
earliestPeriodOrLowestSystem TEXT,
endDayOfYear TEXT,
establishmentMeans TEXT,
eventDate TEXT,
eventID TEXT,
eventRemarks TEXT,
eventTime TEXT,
family TEXT,
fieldNotes TEXT,
fieldNumber TEXT,
footprintSpatialFit TEXT,
footprintSRS TEXT,
footprintWKT TEXT,
formation TEXT,
genus TEXT,
geodeticDatum TEXT,
geologicalContextID TEXT,
georeferencedBy TEXT,
georeferencedDate TEXT,
georeferenceProtocol TEXT,
georeferenceRemarks TEXT,
georeferenceSources TEXT,
georeferenceVerificationStatus TEXT,
_group TEXT,
habitat TEXT,
higherClassification TEXT,
higherGeography TEXT,
higherGeographyID TEXT,
highestBiostratigraphicZone TEXT,
identificationID TEXT,
identificationQualifier TEXT,
identificationReferences TEXT,
identificationRemarks TEXT,
identificationVerificationStatus TEXT,
identifiedBy TEXT,
individualCount TEXT,
individualID TEXT,
informationWithheld TEXT,
infraspecificEpithet TEXT,
institutionCode TEXT,
institutionID TEXT,
island TEXT,
islandGroup TEXT,
kingdom TEXT,
language TEXT,
latestAgeOrHighestStage TEXT,
latestEonOrHighestEonothem TEXT,
latestEpochOrHighestSeries TEXT,
latestEraOrHighestErathem TEXT,
latestPeriodOrHighestSystem TEXT,
lifeStage TEXT,
lithostratigraphicTerms TEXT,
locality TEXT,
locationAccordingTo TEXT,
locationID TEXT,
locationRemarks TEXT,
lowestBiostratigraphicZone TEXT,
maximumDepthInMeters TEXT,
maximumDistanceAboveSurfaceInMeters TEXT,
maximumElevationInMeters TEXT,
member TEXT,
minimumDepthInMeters TEXT,
minimumDistanceAboveSurfaceInMeters TEXT,
minimumElevationInMeters TEXT,
modified TEXT,
month TEXT,
municipality TEXT,
nameAccordingTo TEXT,
nameAccordingToID TEXT,
namePublishedIn TEXT,
namePublishedInID TEXT,
namePublishedInYear TEXT,
nomenclaturalCode TEXT,
nomenclaturalStatus TEXT,
occurrenceID TEXT,
occurrenceRemarks TEXT,
occurrenceStatus TEXT,
_order TEXT,
originalNameUsage TEXT,
originalNameUsageID TEXT,
otherCatalogNumbers TEXT,
ownerInstitutionCode TEXT,
parentNameUsage TEXT,
parentNameUsageID TEXT,
phylum TEXT,
pointRadiusSpatialFit TEXT,
preparations TEXT,
previousIdentifications TEXT,
recordedBy TEXT,
recordNumber TEXT,
_references TEXT,
reproductiveCondition TEXT,
rights TEXT,
rightsHolder TEXT,
samplingEffort TEXT,
samplingProtocol TEXT,
scientificName TEXT,
scientificNameAuthorship TEXT,
scientificNameID TEXT,
sex TEXT,
specificEpithet TEXT,
startDayOfYear TEXT,
stateProvince TEXT,
subgenus TEXT,
taxonConceptID TEXT,
taxonID TEXT,
taxonomicStatus TEXT,
taxonRank TEXT,
taxonRemarks TEXT,
type TEXT,
typeStatus TEXT,
verbatimCoordinates TEXT,
verbatimCoordinateSystem TEXT,
verbatimDepth TEXT,
verbatimElevation TEXT,
verbatimEventDate TEXT,
verbatimLatitude TEXT,
verbatimLocality TEXT,
verbatimLongitude TEXT,
verbatimSRS TEXT,
verbatimTaxonRank TEXT,
vernacularName TEXT,
waterBody TEXT,
year TEXT,
CONSTRAINT occurrence_raw_pkey PRIMARY KEY (auto_id ),
CONSTRAINT occurrence_raw_dwcaid_sourcefileid_key UNIQUE (dwcaid , sourcefileid)
);


CREATE SEQUENCE IF NOT EXISTS unique_values_id_seq;
CREATE TABLE IF NOT EXISTS unique_values
(
  id integer DEFAULT nextval('unique_values_id_seq') NOT NULL,
  key TEXT NOT NULL,
  occurrence_count integer NOT NULL,
  value TEXT NOT NULL,
  unaccented_value TEXT,
  CONSTRAINT unique_values_pkey PRIMARY KEY (id )
);

CREATE SEQUENCE IF NOT EXISTS download_log_id_seq;
CREATE TABLE IF NOT EXISTS download_log
(
  id integer DEFAULT nextval('download_log_id_seq') NOT NULL,
  event_date timestamp,
  search_criteria text,
  number_of_records integer,
  email TEXT,
  CONSTRAINT download_log_pkey PRIMARY KEY (id )
);

CREATE SEQUENCE IF NOT EXISTS import_log_id_seq;
CREATE TABLE IF NOT EXISTS import_log
(
  id integer DEFAULT nextval('import_log_id_seq') NOT NULL,
  sourcefileid TEXT,
  resource_uuid TEXT,
  record_quantity integer,
  updated_by TEXT,
  import_process_duration_ms integer,
  event_end_date_time timestamp,
  CONSTRAINT import_log_pkey PRIMARY KEY (id )
);


/* Structure to persist publisher information: */
CREATE SEQUENCE IF NOT EXISTS publisher_id_seq;
CREATE TABLE IF NOT EXISTS publisher
(
	auto_id integer DEFAULT nextval('publisher_id_seq') NOT NULL,
	name TEXT,
	description TEXT,
	address TEXT,
	city TEXT,
	administrative_area TEXT,
	postal_code TEXT,
	homepage TEXT,
	email TEXT,
	phone TEXT,
	logo_url TEXT,
	decimallatitude DOUBLE PRECISION,
	decimallongitude DOUBLE PRECISION,
	record_count integer,
	CONSTRAINT publisher_pkey PRIMARY KEY (auto_id)
);

CREATE SEQUENCE IF NOT EXISTS dwca_resource_id_seq;
CREATE TABLE IF NOT EXISTS dwca_resource
(
  id integer DEFAULT nextval('dwca_resource_id_seq') NOT NULL,
  name TEXT,
  sourcefileid character varying(255),
  resource_uuid TEXT,
  archive_url TEXT,
  record_count integer,
  publisher_fkey integer references publisher(auto_id),
  CONSTRAINT dwca_resource_pkey PRIMARY KEY ( id ),
  CONSTRAINT dwca_resource_source_file_id_key UNIQUE (sourcefileid)
);

/* Structure to persist resource metadata information: */
CREATE TABLE IF NOT EXISTS resource_metadata
(
	dwca_resource_id integer NOT NULL REFERENCES dwca_resource(id),
	resource_uuid TEXT,
	resource_name TEXT,
	alternate_identifier TEXT,
	title TEXT,
	publication_date date,
	language TEXT,
	_abstract TEXT,
	keyword TEXT,
	keyword_thesaurus TEXT,
	intellectual_rights text,
	citation TEXT,
	hierarchy_level TEXT,
	resource_logo_url TEXT,
	parent_collection_identifier TEXT,
	collection_identifier TEXT,
	collection_name TEXT,
	CONSTRAINT resource_metadata_pkey PRIMARY KEY (dwca_resource_id)
);

/* Structure to persist contacts information: */
CREATE SEQUENCE IF NOT EXISTS contact_id_seq;
CREATE TABLE IF NOT EXISTS contact
(
	auto_id integer DEFAULT nextval('contact_id_seq') NOT NULL,
	name TEXT,
	position_name TEXT,
	organization_name TEXT,
	address TEXT,
	city TEXT,
	administrative_area TEXT,
	country TEXT,
	postal_code TEXT,
	phone TEXT,
	email TEXT,
	role TEXT,
	publisher_fkey integer references publisher(auto_id),
	resource_metadata_fkey integer references resource_metadata(dwca_resource_id),
	CONSTRAINT contact_pkey PRIMARY KEY (auto_id)
);

CREATE TABLE IF NOT EXISTS occurrence_extension
(
	auto_id bigint NOT NULL,
	dwcaid TEXT,
	resource_uuid TEXT,
	sourcefileid TEXT,
	ext_type TEXT, 
	ext_version TEXT, 
	ext_data hstore,
	CONSTRAINT occurrence_extension_pkey PRIMARY KEY (auto_id)
);

CREATE OR REPLACE VIEW complete_occurrence_view AS 
SELECT occ.catalognumber AS p_catalognumber, occ.collectioncode AS p_collectioncode, occ.continent AS p_continent, occ.country AS p_country, occ.datasetname AS p_datasetname, occ.family AS p_family, occ.habitat AS p_habitat, occ.kingdom AS p_kingdom, occ.locality AS p_locality, occ._order AS p_order, occ.recordedby AS p_recordedby, occ.recordnumber AS p_recordnumber, occ.scientificname AS p_scientificname, occ.stateprovince AS p_stateprovince, occ.taxonrank AS p_taxonrank, occ.verbatimelevation AS p_verbatimelevation, occ.minimumelevationinmeters AS p_minimumelevationinmeters, occ.maximumelevationinmeters AS p_maximumelevationinmeters, occ.decimallatitude AS p_decimallatitude, occ.decimallongitude AS p_decimallongitude, occ.eventdate AS p_eventdate, occ.starteventdate AS p_starteventdate, occ.endeventdate AS p_endeventdate, occ.sday AS p_sday, occ.smonth AS p_smonth, occ.syear AS p_syear, occ.eday AS p_eday, occ.emonth AS p_emonth, occ.eyear AS p_eyear, occ.phylum AS p_phylum, occ._class AS p_class, occ.genus AS p_genus, occ.specificepithet AS p_specificepithet, occ.infraspecificepithet AS p_infraspecificepithet, occ.associatedmedia AS p_associatedmedia, occ.scientificnameauthorship AS p_scientificnameauthorship, occ.institutioncode AS p_institutioncode, occ.hascoordinates AS p_hascoordinates, occ.hasmedia AS p_hasmedia, occ.rawscientificname AS p_rawscientificname, occ._references AS p_reference, occ.county AS p_county, occ.municipality AS p_municipality, occ.decade AS p_decade, occ.averagealtituderounded AS p_averagealtituderounded, occ.dwcaid AS p_dwcaid, raw.acceptednameusage, raw.associatedmedia, raw.associatedoccurrences, raw.associatedreferences, raw.associatedsequences, raw.associatedtaxa, raw.basisofrecord, raw.behavior, raw.bibliographiccitation, raw.catalognumber, raw.collectioncode, raw.collectionid, raw.continent, raw.coordinateuncertaintyinmeters, raw.country, raw.countrycode, raw.county, raw.datasetid, raw.datasetname, raw.dateidentified, raw.decimallatitude, raw.decimallongitude, raw.disposition, raw.dynamicproperties, raw.establishmentmeans, raw.eventdate, raw.eventremarks, raw.eventtime, raw.family, raw.footprintwkt, raw.genus, raw.geodeticdatum, raw.georeferencedby, raw.georeferenceprotocol, raw.georeferenceremarks, raw.georeferencesources, raw.georeferenceverificationstatus, raw.habitat, raw.higherclassification, raw.identificationqualifier, raw.identificationremarks, raw.identifiedby, raw.individualcount, raw.infraspecificepithet, raw.institutioncode, raw.kingdom, raw.language, raw.lifestage, raw.locality, raw.locationremarks, raw.maximumelevationinmeters, raw.minimumelevationinmeters, raw.modified, raw.municipality, raw.nomenclaturalcode, raw.occurrenceid, raw.occurrenceremarks, raw.othercatalognumbers, raw.ownerinstitutioncode, raw.phylum, raw.preparations, raw.previousidentifications, raw.recordedby, raw.recordnumber, raw.reproductivecondition, raw.rights, raw.rightsholder, raw.samplingprotocol, raw.scientificname, raw.scientificnameauthorship, raw.sex, raw.specificepithet, raw.stateprovince, raw.taxonrank, raw.taxonremarks, raw.type, raw.typestatus, raw.verbatimcoordinates, raw.verbatimcoordinatesystem, raw.verbatimdepth, raw.verbatimelevation, raw.verbatimeventdate, raw.verbatimlatitude, raw.verbatimlongitude, raw.verbatimsrs, raw.waterbody 
FROM occurrence occ, occurrence_raw raw
WHERE occ.auto_id = raw.auto_id;
