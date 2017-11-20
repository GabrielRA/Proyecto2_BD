/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionDB;

import Clases.Principales.Pais;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 *
 * @author M Express
 */
public class PaisDB {
    
    public ArrayList<Pais> getListaPais() {
        ArrayList<Pais> pais = new ArrayList<>();
        try {
            Connection cnx = DatabaseConnect.getConnection();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("SELECT CODIGOPAIS, NOMBRE FROM PAIS");
            while (rs.next()) {
                Pais conf = new Pais();
                conf.setCodigopais(rs.getString("CODIGOPAIS"));
                conf.setNombre(rs.getString("NOMBRE"));
                pais.add(conf);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error en Listado");
        }

        return pais;
    }
    
    public Pais getPais(String codPais) {
        ConfederacionDB cf = new ConfederacionDB();
        Pais conf = null;
        try {
            Connection cnx = DatabaseConnect.getConnection();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("SELECT CODIGOPAIS, NOMBRE "
                    + "    FROM PAIS WHERE CODIGOPAIS= '" + codPais + "'");
            while (rs.next()) {
                conf = new Pais();
                conf.setCodigopais(rs.getString("CodigoPais"));
                conf.setNombre(rs.getString("NOMBRE"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en buscar");
        }
        return conf;
    }
    //Insertar DATOS en la DB

    public void InsertPais(Pais pais) {

        try {
            Connection cnx = DatabaseConnect.getConnection();
            PreparedStatement pst = cnx.prepareStatement("INSERT INTO PAIS(CODIGOPAIS, NOMBRE)"
                    + " VALUES(?,?)");
            pst.setString(1, pais.getCodigopais());
            pst.setString(2, pais.getNombre());
            pst.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en Ingresar datos");
        }

    }

    public void UpdatePais(Pais pais) {

        try {
            Connection cnx = DatabaseConnect.getConnection();
            PreparedStatement pst = cnx.prepareStatement(" UPDATE  PAIS SET "
                    + " NOMBRE=? WHERE CODIGOPAIS=? ");
            pst.setString(1, pais.getNombre());
            pst.setString(2, pais.getCodigopais());
            pst.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en Ingresar datos");
        }

    }
//cod para Eliminar

    public void DeletePais(String cod) {
        try {
            Connection cnx = DatabaseConnect.getConnection();
            PreparedStatement pst = cnx.prepareStatement("DELETE FROM PAIS "
                    + " WHERE CODIGOPAIS=?");
            pst.setString(1, cod);
            pst.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en Eliminar datos");
        }
    }
}
