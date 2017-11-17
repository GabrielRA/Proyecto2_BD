
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
 
/*Datos de partido*/ 
create or replace procedure datosPartido(p_etapa out Etapa.etapa%TYPE) AS
begin
  select etapa,fecha,hora,cantidad_aficionados,min_reposicion_tiempo1,min_reposicion_tiempo2 into respuesta from partido where partido = numero_partido;
end datosPartido;