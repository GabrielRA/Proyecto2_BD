/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionDB;


import Clases.Principales.Partido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author M Express
 */
public class PartidoDB {
    
    public ArrayList<Partido> getListaPartido() {
        ArrayList<Partido> part = new ArrayList<>();
        try {
            Connection cnx = DatabaseConnect.getConnection();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("SELECT NUMEROPARTIDO, ETAPA, FECHA, HORA, CANTIDAD_AFICIONADOS, MIN_REPOSICION_TIEMPO1, MIN_REPOSICION_TIEMPO2 FROM PARTIDO");
            while (rs.next()) {
                Partido conf = new Partido();
                conf.setNumeropartido(rs.getBigDecimal("NUMEROPARTIDO"));
                conf.setEtapa(rs.getString("ETAPA"));
                conf.setFecha(rs.getDate("FECHA"));
                conf.setHora(rs.getDate("HORA"));
                conf.setCantidadAficionados(rs.getBigDecimal("CANTIDAD_AFICIONADOS").toBigInteger());
                conf.setMinReposicionTiempo1(rs.getBigDecimal("MIN_REPOSICION_TIEMPO1").toBigInteger());
                conf.setMinReposicionTiempo2(rs.getBigDecimal("MIN_REPOSICION_TIEMPO2").toBigInteger());
                part.add(conf);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error en Listado");
        }

        return part;
    }
    
    //Obtener Partido
    public Partido getPartido(String numPartido) {
        Partido conf = null;
        PersonaDB pdb = new PersonaDB();
        try {
            Connection cnx = DatabaseConnect.getConnection();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("SELECT NUMEROPARTIDO, ETAPA, FECHA, HORA, CANTIDAD_AFICIONADOS, MIN_REPOSICION_TIEMPO1, MIN_REPOSICION_TIEMPO2 "
                    + "    FROM PARTIDO WHERE NUMEROPARTIDO= '" + numPartido + "'");
            while (rs.next()) {
                conf = new Partido();
                conf.setNumeropartido(rs.getBigDecimal("NUMEROPARTIDO"));
                conf.setEtapa(rs.getString("ETAPA"));
                conf.setFecha(rs.getDate("FECHA"));
                conf.setHora(rs.getDate("HORA"));
                conf.setCantidadAficionados(rs.getBigDecimal("CANTIDAD_AFICIONADOS").toBigInteger());
                conf.setMinReposicionTiempo1(rs.getBigDecimal("MIN_REPOSICION_TIEMPO1").toBigInteger());
                conf.setMinReposicionTiempo2(rs.getBigDecimal("MIN_REPOSICION_TIEMPO2").toBigInteger());
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en buscar");
        }
        return conf;
    }
    //Insertar DATOS en la DB

    public void InsertPartido(Partido part) {

        try {
            Connection cnx = DatabaseConnect.getConnection();
            PreparedStatement pst = cnx.prepareStatement("INSERT INTO PARTIDO(NUMEROPARTIDO, ETAPA, FECHA, HORA, CANTIDAD_AFICIONADOS, MIN_REPOSICION_TIEMPO1, MIN_REPOSICION_TIEMPO2)"
                    + " VALUES(?,?,?,?,?,?,?)");
            pst.setBigDecimal(1, part.getNumeropartido());
            pst.setString(2, part.getEtapa());
            pst.setDate(3, (java.sql.Date) part.getFecha());
            pst.setDate(4, (java.sql.Date) part.getHora());
            pst.setInt(5, part.getCantidadAficionados().intValue());
            pst.setInt(6, part.getMinReposicionTiempo1().intValue());
            pst.setInt(7, part.getMinReposicionTiempo2().intValue());
            pst.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en Ingresar datos");
        }

    }

    public void UpdatePartido(Partido part) {

        try {
            Connection cnx = DatabaseConnect.getConnection();
            PreparedStatement pst = cnx.prepareStatement(" UPDATE  PARTIDO SET "
                    + " ETAPA=?, FECHA=?, HORA=?, CANTIDAD_AFICIONADOS=?, MIN_REPOSICION_TIEMPO1=?, MIN_REPOSICION_TIEMPO2=? WHERE NUMEROPARTIDO,=? ");
            pst.setString(1, part.getEtapa());
            pst.setDate(2, (java.sql.Date) part.getFecha());
            pst.setDate(3, (java.sql.Date) part.getHora());
            pst.setInt(4, part.getCantidadAficionados().intValue());
            pst.setInt(5, part.getMinReposicionTiempo1().intValue());
            pst.setInt(6, part.getMinReposicionTiempo2().intValue());
            pst.setBigDecimal(7, part.getNumeropartido());
            pst.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en Ingresar datos");
        }

    }
//cod para Eliminar

    public void DeletePartido(String cod) {
        try {
            Connection cnx = DatabaseConnect.getConnection();
            PreparedStatement pst = cnx.prepareStatement("DELETE FROM PARTIDO "
                    + " WHERE NUMEROPARTIDO=?");
            pst.setString(1, cod);
            pst.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en Eliminar datos");
        }
    }
    
}
