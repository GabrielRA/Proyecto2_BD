
 /*Datos de partido*/ 
create or replace procedure datosPartido(p_numero_partido in Partido.numero_Partido%TYPE ,p_etapa out Partido.etapa%TYPE, p_fecha out Partido.fecha%TYPE, p_hora out Partido.hora%TYPE,p_cantidad_aficionados out Partido.cantidad_aficionados%TYPE,p_min_reposicion_tiempo1 out Partido.min_reposicion_tiempo1%TYPE,p_min_reposicion_tiempo2 out Partido.min_reposicion_tiempo2%TYPE ) AS
begin
  select etapa,fecha,hora,cantidad_aficionados,min_reposicion_tiempo1,min_reposicion_tiempo2 into p_etapa,p_fecha,p_hora,p_cantidad_aficionados,p_min_reposicion_tiempo1,p_min_reposicion_tiempo2 from Partido where numero_Partido = p_numero_partido;
end datosPartido;

 /*Titulares partido*/
create or replace procedure titularesPartidos( p_numero_partido in Titulares.numero_Partido%TYPE , respuesta out Persona.nombre%Type) AS
begin
  select p.nombre into respuesta from Personas p, Titulares t  where p.numero_partido = p_numero_partido and t.numeroPasaporte = p.numeroPasaporte;
end titularesPartidos;

 /*Suplentes partido*/
create or replace procedure suplentesPartidos( p_numero_partido in Suplentes.numero_Partido%TYPE , respuesta out Persona.nombre%Type) AS
begin
  select p.nombre into respuesta from Personas p, Suplentes s  where p.numero_partido = p_numero_partido and s.numeroPasaporte = p.numeroPasaporte;
end suplentesPartidos;

/*Albritos partido*/
create or replace procedure supervisoresPartidos( p_numero_partido in Supervisores.numero_Partido%TYPE , p_tipo in Supervisores.tipo%TYPE  ,nombre out Persona.nombre%Type) AS
begin
  select p.nombre, s.tipo into nombre , p_tipo from Personas p, Supervisa  where p.numero_partido = p_numero_partido and s.numeroPasaporte = p.numeroPasaporte;
end supervisoresPartidos;

/*Goles y tarjetas*/
create or replace procedure GolesTarjetas( p_numero_partido in Estadisticas.numero_Partido%TYPE , p_tipo out Estadisticas.tipo%TYPE , p_minuto out Estadisticas.minuto%TYPE ,nombre out Persona.nombre%Type) AS
begin
  select p.nombre, E.tipo, E.minuto into nombre , p_tipo , p_minuto from Personas p, Estadisticas E  where E.numero_partido = p_numero_partido and E.numeroPasaporte = p.numeroPasaporte;
end GolesTarjetas;


/*muestra los esquipos de una confederacion dada*/
create or replace procedure EquiposParConf( codigo in Equipo.codigoConfederacion%Type, respuesta out Equipo.nombre%Type) AS
begin
  select nombre into respuesta from Equipo where codigo = codigoConfederacion;
end EquiposParConf;


/*muestra todos los equipos por confederacion*/
create view VistaEquiposParticipantes 
  As
		select nombre from Equipo
    order by codigoConfederacion
end VistaEquiposParticipantes;

/*insertarConfederacion*/
create or replace procedure insConf( codigo in varchar2, nomb in varchar2) AS
begin
  Insert into Confederacion(codigoConfederacion,nombre)
  values(codigo,nomb);
end insConf;
 
