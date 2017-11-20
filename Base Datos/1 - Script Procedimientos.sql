
//*************** consultas A ********************//
/*Todos los equipos por confederacion*/
create or replace procedure ParConf( p_cur out sys_refcursor) AS
begin
   open p_cur for
   select * from Equipo order by codigoConfederacion;
end ParConf;

/*muestra los esquipos de una confederacion dada*/
create or replace procedure EquiposParConf( codigo in Equipo.codigoConfederacion%Type, p_cur out sys_refcursor) AS
begin
   open p_cur for
   select * from Equipo where codigo = codigoConfederacion;
end EquiposParConf;

/***********************************/

/*insertarConfederacion*/
create or replace procedure insConf( codigo in varchar2, nomb in varchar2) AS
begin
  Insert into Confederacion(codigoConfederacion,nombre)
  values(codigo,nomb);
end insConf;

/******/

//************** Consultas B *************//
 /*Datos de partido*/ 
create or replace procedure datosPartido(p_numero_partido in Partido.numeropartido%TYPE ,p_etapa out Partido.etapa%TYPE, p_fecha out Partido.fecha%TYPE, p_hora out Partido.hora%TYPE,p_cantidad_aficionados out Partido.cantidad_aficionados%TYPE,p_min_reposicion_tiempo1 out Partido.min_reposicion_tiempo1%TYPE,p_min_reposicion_tiempo2 out Partido.min_reposicion_tiempo2%TYPE ) AS
begin
  select etapa,fecha,hora,cantidad_aficionados,min_reposicion_tiempo1,min_reposicion_tiempo2 into p_etapa,p_fecha,p_hora,p_cantidad_aficionados,p_min_reposicion_tiempo1,p_min_reposicion_tiempo2 from Partido where numeropartido = p_numero_partido;
end datosPartido;

 /*Titulares partido*/
create or replace procedure titularesPartidos( p_numero_partido in Partido.numeropartido%TYPE , respuesta out Persona.nombre%Type) AS
begin
  select p.nombre into respuesta from Persona p, Titulares t  where t.numeropartido = p_numero_partido and t.numeroPasaporte = p.numeropasaporte;
end titularesPartidos;

 /*Suplentes partido*/
create or replace procedure suplentesPartidos(  p_numero_partido in Partido.numeropartido%TYPE , respuesta out Persona.nombre%Type) AS
begin
  select p.nombre into respuesta from Persona p, Suplentes s  where s.numeropartido = p_numero_partido and s.numeropasaporte = p.numeropasaporte;
end suplentesPartidos;

/*Albritos partido*/
create or replace procedure supervisoresPartidos(  p_numero_partido in Partido.numeropartido%TYPE , p_tipo out supervisa.tipo%TYPE  ,nombre out Persona.nombre%Type) AS
begin
  select p.nombre, s.tipo into nombre , p_tipo from Persona p, Supervisa s where s.numeropartido = p_numero_partido and s.numeroPasaporte = p.numeroPasaporte;
end supervisoresPartidos;

/*Goles y tarjetas*/
create or replace procedure GolesTarjetas(  p_numero_partido in Partido.numeropartido%TYPE , p_tipo out Estadisticas.tipo%TYPE , p_minuto out Estadisticas.minuto%TYPE ,nombre out Persona.nombre%Type) AS
begin
  select p.nombre, E.tipo, E.minuto into nombre , p_tipo , p_minuto from Persona p, Estadisticas E  where E.numeropartido = p_numero_partido and E.numeroPasaporte = p.numeroPasaporte;
end GolesTarjetas;



//*********************** Consultas C ****************************//

create or replace procedure GrupClasi( grupo in Equipo.grupo%Type, p_cur out sys_refcursor) AS
begin
   open p_cur for
   
   select E.nombre , count( J.codigo_pais) "Goles" as partidos jugados
   
   from Equipo E, Estadisticas ES, Juegan J 
   where E.codigopais = J.codigo_pais and J.numeropartido = ES.numeropartido 
   order by E.grupo;
end GrupClasi;

/******************** Consulta D *****************************/

/******************** Consulta E *****************************/
create or replace procedure goleadores( nombre out varchar2,nombre2  out varchar2, gols out number) AS
begin
   select P.nombre, E.nombre , Count(S.tipo) "goles" into nombre, nombre2, gols
   from Estadisticas S, Equipo E, Persona P
   where S.tipo = 'gol' and S.numeropasaporte = P.numeropasaporte and P.codigopais = E.codigopais
   order by E.nombre;
end goleadores;