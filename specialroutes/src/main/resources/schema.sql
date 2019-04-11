CREATE TABLE abtesting(
  service_name    TEXT PRIMARY KEY NOT NULL,
  active          TEXT NOT NULL,
  endpoint        TEXT NOT NULL,
  weight          TEXT NOT NULL);
