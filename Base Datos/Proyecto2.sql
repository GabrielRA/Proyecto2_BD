 
 
 CREATE TABLE Confederacion(
    codigoConfederacion varchar(3) NOT NULL,
    nombre varchar(15) NOT NULL,
    
    CONSTRAINT pk_Confe PRIMARY KEY (codigoConfederacion)
 );
 
 ALTER TABLE Confederacion MODIFY codigoConfederacion varchar(10) NOT NULL;
 
  CREATE TABLE Equipo(
    codigoPais varchar(3) NOT NULL,
    nombre varchar(15) NOT NULL,
    codigoConfederacion varchar(10) NOT NULL,
    grupo varchar(1) NOT NULL,
    
    CONSTRAINT pk_Equipo PRIMARY KEY (codigoPais),
    CONSTRAINT fk_EquipoConfederacion FOREIGN KEY (codigoConfederacion) REFERENCES Confederacion (codigoConfederacion) 
 );
 