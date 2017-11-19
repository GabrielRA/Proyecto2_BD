/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionDB;

import Clases.Principales.Federativos;
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
public class FederativosDB {
       
    public ArrayList<Federativos> getListaFederativos() {
        ArrayList<Federativos> federativo = new ArrayList<>();
        PersonaDB pdb = new PersonaDB();
        try {
            Connection cnx = DatabaseConnect.getConnection();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("SELECT NUMEROPASAPORTE, FECHAINICIO, PUESTO FROM FEDERATIVOS");
            while (rs.next()) {
                Federativos conf = new Federativos();
                conf.setNumeropasaporte(rs.getBigDecimal("NUMEROPASAPORTE"));
                conf.setFechainicio(rs.getDate("FECHAINICIO"));
                conf.setPuesto(rs.getString("PUESTO"));
                conf.setPersona(pdb.getPersona(rs.getString("NUMEROPASAPORTE")));
                federativo.add(conf);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error en Listado");
        }

        return federativo;
    }
    
    //Obtener Federativos
    public Federativos getFederativos(String numPasaporte) {
        Federativos conf = null;
        PersonaDB pdb = new PersonaDB();
        try {
            Connection cnx = DatabaseConnect.getConnection();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("SELECT NUMEROPASAPORTE, FECHAINICIO, PUESTO "
                    + "    FROM FEDERATIVOS WHERE NUMEROPASAPORTE= '" + numPasaporte + "'");
            while (rs.next()) {
                conf = new Federativos();
                conf.setNumeropasaporte(rs.getBigDecimal("NUMEROPASAPORTE"));
                conf.setFechainicio(rs.getDate("FECHAINICIO"));
                conf.setPuesto(rs.getString("PUESTO"));
                conf.setPersona(pdb.getPersona(rs.getString("NUMEROPASAPORTE")));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en buscar");
        }
        return conf;
    }
    //Insertar DATOS en la DB

    public void InsertArbitros(Federativos federativo) {

        try {
            Connection cnx = DatabaseConnect.getConnection();
            PreparedStatement pst = cnx.prepareStatement("INSERT INTO FEDERATIVOS(NUMEROPASAPORTE, FECHAINICIO, PUESTO)"
                    + " VALUES(?,?,?)");
            pst.setBigDecimal(1, federativo.getNumeropasaporte());
            pst.setDate(2, (java.sql.Date) federativo.getFechainicio());
            pst.setString(3, federativo.getPuesto());
            pst.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en Ingresar datos");
        }

    }

    public void UpdateArbitro(Federativos federativo) {

        try {
            Connection cnx = DatabaseConnect.getConnection();
            PreparedStatement pst = cnx.prepareStatement(" UPDATE  FEDERATIVOS SET "
                    + " FECHAINICIO=?, PUESTO=? WHERE NUMEROPASAPORTE=? ");
            pst.setBigDecimal(1, federativo.getNumeropasaporte());
            pst.setDate(2, (java.sql.Date) federativo.getFechainicio());
            pst.setString(3, federativo.getPuesto());
            pst.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en Ingresar datos");
        }

    }
//cod para Eliminar

    public void DeleteArbitro(String cod) {
        try {
            Connection cnx = DatabaseConnect.getConnection();
            PreparedStatement pst = cnx.prepareStatement("DELETE FROM FEDERATIVOS "
                    + " WHERE NUMEROPASAPORTE=?");
            pst.setString(1, cod);
            pst.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en Eliminar datos");
        }
    }
    
}
