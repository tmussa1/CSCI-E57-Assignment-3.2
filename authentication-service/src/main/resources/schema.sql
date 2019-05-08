
CREATE TABLE users_tabl(
  user_name TEXT PRIMARY KEY NOT NULL,
  password TEXT NOT NULL,
  enabled boolean
);

CREATE TABLE user_roles_tabl(
  role_id TEXT PRIMARY KEY NOT NULL,
  user_name TEXT NOT NULL,
  role TEXT NOT NULL
);

CREATE TABLE user_company_tabl(
  user_name TEXT PRIMARY KEY NOT NULL,
  company_id NOT NULL
);

INSERT INTO users_tabl(user_name,password,enabled) VALUES ('tofik.mussa','11559301', true);
INSERT INTO users_tabl(user_name,password,enabled) VALUES ('risqua.mussa','11314321', true);

INSERT INTO user_roles_tabl (user_name, role) VALUES ('tofik.mussa', 'ROLE_USER');
INSERT INTO user_roles_tabl (user_name, role) VALUES ('risqua.mussa', 'ROLE_ADMIN');
INSERT INTO user_roles_tabl (user_name, role) VALUES ('risqua.mussa', 'ROLE_USER');

INSERT INTO user_company_tabl (company_id, user_name) VALUES ('562f5ed0-b0bf-40cf-85c1-e560631057b8', 'tofik.mussa');
INSERT INTO user_company_tabl (company_id, user_name) VALUES ('2f341f84-accd-43a6-abbc-70d41de2c9d3', 'risqua.mussa');
