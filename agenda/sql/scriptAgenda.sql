CREATE DATABASE agenda;
USE agenda;

CREATE TABLE tipo_contacto (
idTipo_contacto int(11) NOT NULL AUTO_INCREMENT, 
Tipo enum('Amigo', 'Familiar', 'Trabajo', 'Futbol'), 
PRIMARY KEY (idTipo_contacto)); 

CREATE TABLE provincia (
idProvincia int(11) NOT NULL AUTO_INCREMENT,
Nombre varchar(45) NOT NULL,
Pais varchar(45) NOT NULL,
PRIMARY KEY (idProvincia), 
CONSTRAINT fk_pais FOREIGN KEY (Pais) REFERENCES pais(idPais)
);

CREATE TABLE pais (
idPais int(11) NOT NULL AUTO_INCREMENT,
Nombre varchar(45) NOT NULL,
PRIMARY KEY (idPais)
);

CREATE TABLE localidad (
idLocalidad int(11) NOT NULL AUTO_INCREMENT,
Nombre varchar(45) NOT NULL,
Provincia varchar(45),
Pais varchar(45), 
PRIMARY KEY (idLocalidad), 
CONSTRAINT fk_pais FOREIGN KEY (Pais) REFERENCES pais(idPais), 
CONSTRAINT fk_provincia FOREIGN KEY (Provincia) REFERENCES provincia(idProvincia)
);

CREATE TABLE domicilio (
idDomicilio int(11) NOT NULL AUTO_INCREMENT, 
Calle varchar(45) NOT NULL, 
Altura int(20) NOT NULL, 
Piso int(20), 
Depto int(20),
Localidad int(11),
PRIMARY KEY (idDomicilio), 
CONSTRAINT fk_localidad FOREIGN KEY (Localidad) REFERENCES localidad(idLocalidad)
);

CREATE TABLE personas (
idPersona int(11) NOT NULL AUTO_INCREMENT, 
Nombre varchar(45) NOT NULL, 
Telefono varchar(20) NOT NULL, 
Domicilio int(11), 
Tipo_contacto int(11), 
PRIMARY KEY (idPersona), 
CONSTRAINT fk_domicilio FOREIGN KEY (Domicilio) REFERENCES domicilio(idDomicilio), 
CONSTRAINT fk_tipo FOREIGN KEY (Tipo_contacto) REFERENCES tipo_contacto(idTipo_contacto));














