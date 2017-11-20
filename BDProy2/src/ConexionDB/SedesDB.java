/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionDB;

import Clases.Principales.Sedes;
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
public class SedesDB {
    
    public ArrayList<Sedes> getListaSedes() {
        ArrayList<Sedes> sede = new ArrayList<>();
        try {
            Connection cnx = DatabaseConnect.getConnection();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("SELECT NOMBREESTADIO, NOMBRESEDE, CAPACIDAD FROM SEDES");
            while (rs.next()) {
                Sedes conf = new Sedes();
                conf.setNombreestadio(rs.getString("NOMBREESTADIO"));
                conf.setNombresede(rs.getString("NOMBRESEDE"));
                conf.setCapacidad(rs.getBigDecimal("CAPACIDAD").toBigInteger());
                sede.add(conf);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error en Listado");
        }

        return sede;
    }
    
    //Obtener Jugador
    public Sedes getSede(String nomEstadio) {
        Sedes conf = null;
        PersonaDB pdb = new PersonaDB();
        try {
            Connection cnx = DatabaseConnect.getConnection();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("SELECT NOMBREESTADIO, NOMBRESEDE, CAPACIDAD  "
                    + "    FROM SEDES WHERE NOMBREESTADIO= '" + nomEstadio + "'");
            while (rs.next()) {
                conf = new Sedes();
                conf.setNombreestadio(rs.getString("NOMBREESTADIO"));
                conf.setNombresede(rs.getString("NOMBRESEDE"));
                conf.setCapacidad(rs.getBigDecimal("CAPACIDAD").toBigInteger());
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en buscar");
        }
        return conf;
    }
    //Insertar DATOS en la DB

    public void InsertSedes(Sedes sede) {

        try {
            Connection cnx = DatabaseConnect.getConnection();
            PreparedStatement pst = cnx.prepareStatement("INSERT INTO SEDES(NOMBREESTADIO, NOMBRESEDE, CAPACIDAD)"
                    + " VALUES(?,?,?)");
            pst.setString(1, sede.getNombreestadio());
            pst.setString(2, sede.getNombresede());
            pst.setInt(3, sede.getCapacidad().intValue());
            pst.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en Ingresar datos");
        }

    }

    public void UpdateSedes(Sedes sede) {

        try {
            Connection cnx = DatabaseConnect.getConnection();
            PreparedStatement pst = cnx.prepareStatement(" UPDATE SEDES SET "
                    + " NOMBRESEDE=?, CAPACIDAD=? WHERE NOMBREESTADIO=? ");
            pst.setString(1, sede.getNombresede());
            pst.setInt(2, sede.getCapacidad().intValue());
            pst.setString(3, sede.getNombreestadio());
            pst.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en Ingresar datos");
        }

    }
//cod para Eliminar

    public void DeleteSedes(String cod) {
        try {
            Connection cnx = DatabaseConnect.getConnection();
            PreparedStatement pst = cnx.prepareStatement("DELETE FROM SEDES "
                    + " WHERE NUMEROPASAPORTE=?");
            pst.setString(1, cod);
            pst.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en Eliminar datos");
        }
    }
    
}
