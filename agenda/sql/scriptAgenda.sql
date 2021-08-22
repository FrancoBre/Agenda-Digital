CREATE DATABASE agenda;
USE agenda;

CREATE TABLE tipo_contacto (
idTipo_contacto int(11) NOT NULL AUTO_INCREMENT, 
Tipo enum('Amigo', 'Familiar', 'Trabajo', 'Futbol'), 
PRIMARY KEY (idTipo_contacto)); 

CREATE TABLE pais (
idPais int(11) NOT NULL AUTO_INCREMENT,
Nombre varchar(45) NOT NULL,
PRIMARY KEY (idPais)
);

CREATE TABLE provincia (
idProvincia int(11) NOT NULL AUTO_INCREMENT,
Nombre varchar(45) NOT NULL,
Pais int(11) NOT NULL,
PRIMARY KEY (idProvincia), 
CONSTRAINT fk_pais FOREIGN KEY (Pais) REFERENCES pais(idPais)
);

CREATE TABLE localidad (
idLocalidad int(11) NOT NULL AUTO_INCREMENT,
Nombre varchar(45) NOT NULL,
Provincia int(11),
Pais int(11), 
PRIMARY KEY (idLocalidad), 
CONSTRAINT fk_loc_pais FOREIGN KEY (Pais) REFERENCES pais(idPais), 
CONSTRAINT fk_loc_provincia FOREIGN KEY (Provincia) REFERENCES provincia(idProvincia)
);

CREATE TABLE domicilio (
idDomicilio int(11) NOT NULL AUTO_INCREMENT, 
Calle varchar(45) NOT NULL, 
Altura int(20) NOT NULL, 
Piso int(20), 
Depto int(20),
Localidad int(11),
PRIMARY KEY (idDomicilio), 
CONSTRAINT fk_dom_localidad FOREIGN KEY (Localidad) REFERENCES localidad(idLocalidad)
);

CREATE TABLE personas (
idPersona int(11) NOT NULL AUTO_INCREMENT, 
Nombre varchar(45) NOT NULL, 
Telefono varchar(20) NOT NULL, 
Domicilio int(11), 
Tipo_contacto int(11), 
PRIMARY KEY (idPersona), 
CONSTRAINT fk_per_domicilio FOREIGN KEY (Domicilio) REFERENCES domicilio(idDomicilio), 
CONSTRAINT fk_per_tipo FOREIGN KEY (Tipo_contacto) REFERENCES tipo_contacto(idTipo_contacto));

INSERT INTO pais (idPais, Nombre) VALUES (1, 'Argentina');

INSERT INTO pais (idPais, Nombre) VALUES (2, 'Uruguay');

INSERT INTO pais (idPais, Nombre) VALUES (3, 'Chile');

INSERT INTO provincia (idProvincia, Nombre, Pais) VALUES (1, 'Buenos Aires', 1);

INSERT INTO provincia (idProvincia, Nombre, Pais) VALUES (2, 'Santa Fe', 1);

INSERT INTO provincia (idProvincia, Nombre, Pais) VALUES (3, 'Cordoba', 1);

INSERT INTO provincia (idProvincia, Nombre, Pais) VALUES (4, 'Artigas', 2);

INSERT INTO provincia (idProvincia, Nombre, Pais) VALUES (5, 'Canelones', 2);

INSERT INTO provincia (idProvincia, Nombre, Pais) VALUES (6, 'Colonia', 2);

INSERT INTO provincia (idProvincia, Nombre, Pais) VALUES (7, 'Arica', 3);

INSERT INTO provincia (idProvincia, Nombre, Pais) VALUES (8, 'Putre', 3);

INSERT INTO provincia (idProvincia, Nombre, Pais) VALUES (9, 'Iquique', 3);

INSERT INTO localidad (idLocalidad, Nombre, Provincia, Pais) VALUES (1, 'Moreno', 1, 1);

INSERT INTO localidad (idLocalidad, Nombre, Provincia, Pais) VALUES (2, 'San Miguel', 1, 1);

INSERT INTO localidad (idLocalidad, Nombre, Provincia, Pais) VALUES (3, 'Moron', 1, 1);

INSERT INTO localidad (idLocalidad, Nombre, Provincia, Pais) VALUES (4, 'Moreno', 1, 1);

INSERT INTO localidad (idLocalidad, Nombre, Provincia, Pais) VALUES (5, 'Acebal', 2, 1);

INSERT INTO localidad (idLocalidad, Nombre, Provincia, Pais) VALUES (6, 'Alcorta', 2, 1);

INSERT INTO localidad (idLocalidad, Nombre, Provincia, Pais) VALUES (7, 'Alejandra', 2, 1);

INSERT INTO localidad (idLocalidad, Nombre, Provincia, Pais) VALUES (8, 'Cordoba Capital', 3, 1);

INSERT INTO localidad (idLocalidad, Nombre, Provincia, Pais) VALUES (9, 'Cura Brochero', 3, 1);

INSERT INTO localidad (idLocalidad, Nombre, Provincia, Pais) VALUES (10, 'Carlos Paz', 3, 1);

INSERT INTO localidad (idLocalidad, Nombre, Provincia, Pais) VALUES (11, 'Bella Union', 4, 2);

INSERT INTO localidad (idLocalidad, Nombre, Provincia, Pais) VALUES (12, 'Araminda', 5, 2);

INSERT INTO localidad (idLocalidad, Nombre, Provincia, Pais) VALUES (13, 'Miguelete', 6, 2);

INSERT INTO localidad (idLocalidad, Nombre, Provincia, Pais) VALUES (14, 'Caleta Camarones', 7, 2);

INSERT INTO localidad (idLocalidad, Nombre, Provincia, Pais) VALUES (15, 'Chucuyo', 8, 2);

INSERT INTO localidad (idLocalidad, Nombre, Provincia, Pais) VALUES (16, 'Alto Hospicio', 9, 2);
