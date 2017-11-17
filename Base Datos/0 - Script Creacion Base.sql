CREATE DATABASE BDPROY2;

USE BDPROY2;  

CREATE TABLE Pais(
    codigoPais varchar(3) NOT NULL,
    nombre varchar(50) NOT NULL,
    
    PRIMARY KEY (codigoPais)
 ); 
 
CREATE TABLE Confederacion(
    codigoConfederacion varchar(10) NOT NULL,
    nombre varchar(100) NOT NULL,
    
    PRIMARY KEY (codigoConfederacion)
 );
 
CREATE TABLE Equipo(
    codigoPais varchar(3) NOT NULL,
    nombre varchar(40) NOT NULL,
    codigoConfederacion varchar(10) NOT NULL,
    grupo varchar(1) NOT NULL,
    
    PRIMARY KEY (codigoPais),
    FOREIGN KEY (codigoConfederacion) REFERENCES Confederacion (codigoConfederacion) 
);
 
CREATE TABLE Persona(
    numeroPasaporte INT NOT NULL,
    nombre varchar(15) NOT NULL,
	apellido1 varchar(15) NOT NULL,
    apellido2 varchar(15) NOT NULL,
	fechaNacimiento DATE NOT NULL, 
    codigoPais varchar(3) NOT NULL,
    
    PRIMARY KEY (numeroPasaporte),
    FOREIGN KEY (codigoPais) REFERENCES Pais (codigoPais) 
 ); 
 
CREATE TABLE Entrenador(
    numeroPasaporte INT NOT NULL,
    fechaInicio date NOT NULL,
    
    PRIMARY KEY (numeroPasaporte),
    FOREIGN KEY (numeroPasaporte) REFERENCES Persona (numeroPasaporte)
 );  
 
 CREATE TABLE Arbrito(
    numeroPasaporte INT NOT NULL,
    fechaInicio date NOT NULL,
    
    PRIMARY KEY (numeroPasaporte),
    FOREIGN KEY (numeroPasaporte) REFERENCES Persona (numeroPasaporte)
 ); 
 
 CREATE TABLE Federativos(
    numeroPasaporte INT NOT NULL,
    fechaInicio date NOT NULL,
    puesto varchar(50) NOT NULL,
    
    PRIMARY KEY (numeroPasaporte),
    FOREIGN KEY (numeroPasaporte) REFERENCES Persona (numeroPasaporte)
 );
 
  CREATE TABLE Jugador(
    numeroPasaporte INT NOT NULL,
    camisa INT NOT NULL,
    
    PRIMARY KEY (numeroPasaporte,camisa),
    FOREIGN KEY (numeroPasaporte) REFERENCES Persona (numeroPasaporte)
 ); 
 
 CREATE TABLE Sedes(
    nombreEstadio varchar(50) not null,
	nombreSede varchar(50) not null,
    capacidad int not null,
    PRIMARY KEY (nombreEstadio)
 );
 
 CREATE TABLE Partido(
    numeroPartido int not null,
    etapa varchar(50) not null,
    fecha date not null,
    hora timestamp not null,
    cantidad_aficionados int not null,
    min_reposicion_tiempo1 int not null,
    min_reposicion_tiempo2 int not null,
    PRIMARY KEY (numeroPartido)
 );
 
CREATE TABLE Disputa(
    numeroPartido int not null,
    nombreSede varchar(50) not null,
    PRIMARY KEY (numeroPartido,nombreSede),
    FOREIGN KEY (numeroPartido) REFERENCES Partido (numeroPartido),
	FOREIGN KEY (nombreSede) REFERENCES Sedes (nombreSede)
 );
 
 CREATE TABLE Estadisticas(
    numeroPartido int not null,
    numeroPasaporte int not null,
    minuto int not null,
    tipo varchar(50) not null,
    PRIMARY KEY (numeroPartido),
    FOREIGN KEY (numeroPartido) REFERENCES Partido (numeroPartido),
    FOREIGN KEY (numeroPasaporte) REFERENCES Persona (numeroPasaporte)
 );
 
 CREATE TABLE Supervisa(
    numeroPartido int not null,
    numeroPasaporte int not null,
    tipo varchar(50) not null,
    PRIMARY KEY (numeroPartido),
    FOREIGN KEY (numeroPartido) REFERENCES Partido (numeroPartido),
    FOREIGN KEY (numeroPasaporte) REFERENCES Persona (numeroPasaporte)
 );
 
CREATE TABLE Juegan(
    numeroPartido int not null,
    codigo_pais int not null,
    PRIMARY KEY (numeroPartido,codigo_pais),
    FOREIGN KEY (numeroPartido) REFERENCES Partido (numeroPartido)
 );
 
CREATE TABLE Medico(
    numeroPartido int not null,
    numeroPasaporte int not null,
    PRIMARY KEY (numeroPartido,numeroPasaporte),
    FOREIGN KEY (numeroPartido) REFERENCES Partido (numeroPartido),
    FOREIGN KEY (numeroPasaporte) REFERENCES Persona (numeroPasaporte)
 );
 
CREATE TABLE Delegado(
    numeroPartido int not null,
    numeroPasaporte int not null,
    PRIMARY KEY (numeroPartido,numeroPasaporte),
    FOREIGN KEY (numeroPartido) REFERENCES Partido (numeroPartido),
    FOREIGN KEY (numeroPasaporte) REFERENCES Persona (numeroPasaporte)
 );
 
 CREATE TABLE Titulares(
    numeroPartido int not null,
    numeroPasaporte int not null,
    PRIMARY KEY (numeroPartido,numeroPasaporte),
    FOREIGN KEY (numeroPartido) REFERENCES Partido (numeroPartido),
    FOREIGN KEY (numeroPasaporte) REFERENCES Persona (numeroPasaporte)
 );
 
CREATE TABLE Suplentes(
    numeroPartido int not null,
    numeroPasaporte int not null,
    PRIMARY KEY (numeroPartido,numeroPasaporte),
    FOREIGN KEY (numeroPartido) REFERENCES Partido (numeroPartido),
    FOREIGN KEY (numeroPasaporte) REFERENCES Persona (numeroPasaporte)
 );
 
CREATE TABLE Cambios(
    numeroPartido int not null,
    numeroPasaporte int not null,
    PRIMARY KEY (numeroPartido,numeroPasaporte),
    FOREIGN KEY (numeroPartido) REFERENCES Partido (numeroPartido),
    FOREIGN KEY (numeroPasaporte) REFERENCES Persona (numeroPasaporte)
 );