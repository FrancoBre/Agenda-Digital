CREATE DATABASE agenda;
USE agenda;

CREATE USER 'user'@'localhost' IDENTIFIED BY '1234';
GRANT ALL PRIVILEGES ON agenda.personas TO 'user'@'localhost';
GRANT ALL PRIVILEGES ON agenda.tipo_contacto TO 'user'@'localhost';
GRANT ALL PRIVILEGES ON agenda.pais TO 'user'@'localhost';
GRANT ALL PRIVILEGES ON agenda.provincia TO 'user'@'localhost';
GRANT ALL PRIVILEGES ON agenda.localidad TO 'user'@'localhost';
GRANT ALL PRIVILEGES ON agenda.domicilio TO 'user'@'localhost';
GRANT ALL PRIVILEGES ON agenda.medio_transporte TO 'user'@'localhost';

CREATE TABLE tipo_contacto (
	idTipo_contacto int(11) NOT NULL AUTO_INCREMENT, 
	tipo varchar(45) NOT NULL, 
	PRIMARY KEY (idTipo_contacto)
); 

CREATE TABLE pais (
	idPais int(11) NOT NULL AUTO_INCREMENT,
	nombrePais varchar(45) NOT NULL,
	PRIMARY KEY (idPais)
);

CREATE TABLE provincia (
	idProvincia int(11) NOT NULL AUTO_INCREMENT,
	nombreProvincia varchar(45) NOT NULL,
	pais int(11) NOT NULL,
	PRIMARY KEY (idProvincia), 
	CONSTRAINT fk_pais FOREIGN KEY (pais) REFERENCES pais(idPais)
);

CREATE TABLE localidad (
	idLocalidad int(11) NOT NULL AUTO_INCREMENT,
	nombreLocalidad varchar(45) NOT NULL,
	provincia int(11),
	pais int(11), 
	PRIMARY KEY (idLocalidad), 
	CONSTRAINT fk_loc_pais FOREIGN KEY (pais) REFERENCES pais(idPais), 
	CONSTRAINT fk_loc_provincia FOREIGN KEY (provincia) REFERENCES provincia(idProvincia)
);

CREATE TABLE domicilio (
	idDomicilio int(11) NOT NULL AUTO_INCREMENT, 
	calle varchar(45) NOT NULL, 
	altura int(20) NOT NULL, 
	piso varchar(20), 
	depto varchar(20),
	Localidad int(11),
	PRIMARY KEY (idDomicilio), 
	CONSTRAINT fk_dom_localidad FOREIGN KEY (localidad) REFERENCES localidad(idLocalidad)
);

CREATE TABLE medio_transporte (
	idMedio_transporte int(11) NOT NULL AUTO_INCREMENT,
	nombreMedioTransporte varchar(45) NOT NULL,
	PRIMARY KEY (idMedio_transporte)
);

CREATE TABLE personas (
	idPersona int(11) NOT NULL AUTO_INCREMENT, 
	nombrePersona varchar(45) NOT NULL, 
	telefono varchar(20) NOT NULL,
	email nvarchar(45), 
	dominioEmail nvarchar(45),
	nacimiento date,
	domicilio int(11), 
	tipo_contacto int(11), 
	medio_transporte int(11),
	PRIMARY KEY (idPersona), 
	CONSTRAINT fk_per_domicilio FOREIGN KEY (domicilio) REFERENCES domicilio(idDomicilio), 
	CONSTRAINT fk_per_tipo FOREIGN KEY (tipo_contacto) REFERENCES tipo_contacto(idTipo_contacto),
	CONSTRAINT fk_per_mt FOREIGN KEY (medio_transporte) REFERENCES medio_transporte(idMedio_transporte)
);

INSERT INTO pais (idPais, nombrePais) VALUES (1, 'Argentina');

INSERT INTO pais (idPais, nombrePais) VALUES (2, 'Uruguay');

INSERT INTO pais (idPais, nombrePais) VALUES (3, 'Chile');

INSERT INTO provincia (idProvincia, nombreProvincia, pais) VALUES (1, 'Buenos Aires', 1);

INSERT INTO provincia (idProvincia, nombreProvincia, pais) VALUES (2, 'Santa Fe', 1);

INSERT INTO provincia (idProvincia, nombreProvincia, pais) VALUES (3, 'Cordoba', 1);

INSERT INTO provincia (idProvincia, nombreProvincia, pais) VALUES (4, 'Artigas', 2);

INSERT INTO provincia (idProvincia, nombreProvincia, pais) VALUES (5, 'Canelones', 2);

INSERT INTO provincia (idProvincia, nombreProvincia, pais) VALUES (6, 'Colonia', 2);

INSERT INTO provincia (idProvincia, nombreProvincia, pais) VALUES (7, 'Arica', 3);

INSERT INTO provincia (idProvincia, nombreProvincia, pais) VALUES (8, 'Putre', 3);

INSERT INTO provincia (idProvincia, nombreProvincia, pais) VALUES (9, 'Iquique', 3);

INSERT INTO localidad (idLocalidad, nombreLocalidad, provincia, pais) VALUES (1, 'Moreno', 1, 1);

INSERT INTO localidad (idLocalidad, nombreLocalidad, provincia, pais) VALUES (2, 'San Miguel', 1, 1);

INSERT INTO localidad (idLocalidad, nombreLocalidad, provincia, pais) VALUES (3, 'Moron', 1, 1);

INSERT INTO localidad (idLocalidad, nombreLocalidad, provincia, pais) VALUES (4, 'Moreno', 1, 1);

INSERT INTO localidad (idLocalidad, nombreLocalidad, provincia, pais) VALUES (5, 'Acebal', 2, 1);

INSERT INTO localidad (idLocalidad, nombreLocalidad, provincia, pais) VALUES (6, 'Alcorta', 2, 1);

INSERT INTO localidad (idLocalidad, nombreLocalidad, provincia, pais) VALUES (7, 'Alejandra', 2, 1);

INSERT INTO localidad (idLocalidad, nombreLocalidad, provincia, pais) VALUES (8, 'Cordoba Capital', 3, 1);

INSERT INTO localidad (idLocalidad, nombreLocalidad, provincia, pais) VALUES (9, 'Cura Brochero', 3, 1);

INSERT INTO localidad (idLocalidad, nombreLocalidad, provincia, pais) VALUES (10, 'Carlos Paz', 3, 1);

INSERT INTO localidad (idLocalidad, nombreLocalidad, provincia, pais) VALUES (11, 'Bella Union', 4, 2);

INSERT INTO localidad (idLocalidad, nombreLocalidad, provincia, pais) VALUES (12, 'Araminda', 5, 2);

INSERT INTO localidad (idLocalidad, nombreLocalidad, provincia, pais) VALUES (13, 'Miguelete', 6, 2);

INSERT INTO localidad (idLocalidad, nombreLocalidad, provincia, pais) VALUES (14, 'Caleta Camarones', 7, 2);

INSERT INTO localidad (idLocalidad, nombreLocalidad, provincia, pais) VALUES (15, 'Chucuyo', 8, 2);

INSERT INTO localidad (idLocalidad, nombreLocalidad, provincia, pais) VALUES (16, 'Alto Hospicio', 9, 2);

INSERT INTO tipo_contacto (idTipo_contacto, tipo) VALUES (1, 'Amigo');

INSERT INTO tipo_contacto (idTipo_contacto, tipo) VALUES (2, 'Familiar');

INSERT INTO tipo_contacto (idTipo_contacto, tipo) VALUES (3, 'Trabajo');

INSERT INTO tipo_contacto (idTipo_contacto, tipo) VALUES (4, 'Futbol');

INSERT INTO medio_transporte (idMedio_transporte, nombreMedioTransporte) VALUES (1, 'Auto');

INSERT INTO medio_transporte (idMedio_transporte, nombreMedioTransporte) VALUES (2, 'Moto');

INSERT INTO medio_transporte (idMedio_transporte, nombreMedioTransporte) VALUES (3, 'Transporte publico');

INSERT INTO medio_transporte (idMedio_transporte, nombreMedioTransporte) VALUES (4, 'A pie');

INSERT INTO medio_transporte (idMedio_transporte, nombreMedioTransporte) VALUES (5, 'Bici');
