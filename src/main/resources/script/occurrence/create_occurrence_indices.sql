
-- We use a HASH because we only use equality (=) operator on dwca_id field
CREATE INDEX occurrence_dwca_id_idx
  ON occurrence
  USING hash
  (dwca_id COLLATE pg_catalog."default");