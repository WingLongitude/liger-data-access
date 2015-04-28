/* Create a group for www read-only */
CREATE ROLE wwwreadonly NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE NOREPLICATION;

/* Create a user for www read-only */
CREATE USER wwwuser WITH PASSWORD 'CHANGE_ME';

GRANT wwwreadonly TO wwwuser;

GRANT SELECT ON TABLE occurrence TO wwwreadonly;
GRANT SELECT ON TABLE occurrence_extension TO wwwreadonly;
GRANT SELECT ON TABLE occurrence_raw TO wwwreadonly;
GRANT SELECT ON TABLE unique_values TO wwwreadonly;

GRANT SELECT ON TABLE contact TO wwwreadonly;
GRANT SELECT ON TABLE resource_metadata TO wwwreadonly;
GRANT SELECT ON TABLE dwca_resource TO wwwreadonly;
GRANT SELECT ON TABLE publisher TO wwwreadonly;

GRANT SELECT ON TABLE import_log TO wwwreadonly;

/* download_log is the only exception since we need to record the download event */
GRANT SELECT(id), INSERT(id) ON download_log TO wwwreadonly;
GRANT UPDATE(event_date), INSERT(event_date) ON download_log TO wwwreadonly;
GRANT UPDATE(search_criteria), INSERT(search_criteria) ON download_log TO wwwreadonly;
GRANT UPDATE(number_of_records), INSERT(number_of_records) ON download_log TO wwwreadonly;
GRANT UPDATE(email), INSERT(email) ON download_log TO wwwreadonly;