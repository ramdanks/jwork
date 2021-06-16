CREATE DATABASE jwork;

CREATE TABLE jobseeker (
	id SERIAL PRIMARY KEY,
	name CHAR(50) NOT NULL,
	email CHAR(50) NOT NULL UNIQUE,
	password CHAR(32) NOT NULL,
	creationtime TIMESTAMP NOT NULL DEFAULT NOW()::TIMESTAMP
);

CREATE TABLE recruiter (
	id SERIAL PRIMARY KEY,
	name CHAR(50) NOT NULL,
	email CHAR(50) NOT NULL,
	phone_number CHAR(32) NOT NULL,
	
	loc_province CHAR(32),
	loc_city CHAR(32),
	loc_desc CHAR(50)
);

CREATE TABLE job (
	id SERIAL PRIMARY KEY,
	name CHAR(50) NOT NULL,
	recruiter_id INT NOT NULL REFERENCES recruiter(id),
	fee INT NOT NULL,
	category CHAR(32) NOT NULL
);

CREATE TABLE bonus (
	id SERIAL PRIMARY KEY,
	referralcode CHAR(50) UNIQUE,
	extrafee INT,
	mintotalfee INT,
	active BOOLEAN
);

CREATE TABLE invoice (
	id SERIAL PRIMARY KEY,
	job_id INT REFERENCES job(id),
	date TIMESTAMP,
	totalfee INT,
	jobseeker_id INT REFERENCES recruiter(id),
	status CHAR(32),
	bonus_id INT REFERENCES bonus(id),
	adminfee INT,
	ptype CHAR(32)
);