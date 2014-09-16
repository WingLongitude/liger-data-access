ALTER TABLE occurrence_extension ADD COLUMN auto_id bigint NOT NULL;
ALTER TABLE occurrence_extension ADD COLUMN dwcaid character varying(75);
ALTER TABLE occurrence_extension ADD COLUMN sourcefileid character varying(50);
ALTER TABLE occurrence_extension ADD COLUMN resource_uuid character varying(50);

--This table should be empty but if not please, transfer the content of 'id' into 'auto_id' and then delete the 'id' column
ALTER TABLE occurrence_extension DROP COLUMN IF EXISTS id;

ALTER TABLE resource_management RENAME key TO resource_uuid;

-- Creating the resource_information table:
CREATE SEQUENCE resource_information_id_seq;
CREATE TABLE resource_information
(
	auto_id integer DEFAULT nextval('resource_information_id_seq') NOT NULL,
	resource_uuid character varying(50),
	resource_name character varying(100),
	alternate_identifier character varying(100),
	title character varying(100),
	publication_date date,
	language character varying(30),
	_abstract text,
	keyword character varying(100),
	keyword_thesaurus character varying(100),
	intellectual_rights text,
	citation character varying(200),
	hierarchy_level character varying(100),
	resource_logo_url character varying(150),
	parent_collection_identifier character varying(50),
	collection_identifier character varying(150),
	collection_name character varying(150),
	CONSTRAINT resource_information_pkey PRIMARY KEY (auto_id)
);

-- Updating resource_contact table to relate to resource_information:
ALTER TABLE resource_contact ADD COLUMN resource_information_fk integer references resource_information(id);

-- Removing fields from resource_contact that now belong in resource_information:
ALTER TABLE resource_contact DROP COLUMN sourcefileid;
ALTER TABLE resource_contact DROP COLUMN resource_name;
