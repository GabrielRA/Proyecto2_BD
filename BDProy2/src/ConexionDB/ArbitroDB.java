/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionDB;

import Clases.Principales.Arbrito;
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
public class ArbitroDB {
    public ArrayList<Arbrito> getListaArbitros() {
        ArrayList<Arbrito> arbitro = new ArrayList<>();
        PersonaDB pdb = new PersonaDB();
        try {
            Connection cnx = DatabaseConnect.getConnection();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("SELECT NUMEROPASAPORTE, FECHAINICIO FROM ARBRITO");
            while (rs.next()) {
                Arbrito conf = new Arbrito();
                conf.setNumeropasaporte(rs.getBigDecimal("NUMEROPASAPORTE"));
                conf.setFechainicio(rs.getDate("FECHAINICIO"));
                conf.setPersona(pdb.getPersona(rs.getString("NUMEROPASAPORTE")));
                arbitro.add(conf);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error en Listado");
        }

        return arbitro;
    }
    
    //Obtener Arbitro
    public Arbrito getArbitro(String numPasaporte) {
        Arbrito conf = null;
        PersonaDB pdb = new PersonaDB();
        try {
            Connection cnx = DatabaseConnect.getConnection();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("SELECT NUMEROPASAPORTE, FECHAINICIO"
                    + "    FROM ARBRITO WHERE NUMEROPASAPORTE= '" + numPasaporte + "'");
            while (rs.next()) {
                conf = new Arbrito();
                conf.setNumeropasaporte(rs.getBigDecimal("NUMEROPASAPORTE"));
                conf.setFechainicio(rs.getDate("FECHAINICIO"));
                conf.setPersona(pdb.getPersona(rs.getString("NUMEROPASAPORTE")));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en buscar");
        }
        return conf;
    }
    
    //Insertar DATOS en la DB

    public void InsertArbitros(Arbrito arbitro) {

        try {
            Connection cnx = DatabaseConnect.getConnection();
            PreparedStatement pst = cnx.prepareStatement("INSERT INTO ARBRITO(NUMEROPASAPORTE, FECHAINICIO)"
                    + " VALUES(?,?)");
            pst.setBigDecimal(1, arbitro.getNumeropasaporte());
            pst.setDate(2, (java.sql.Date) arbitro.getFechainicio());
            pst.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en Ingresar datos");
        }

    }

    public void UpdateArbitro(Arbrito arbitro) {

        try {
            Connection cnx = DatabaseConnect.getConnection();
            PreparedStatement pst = cnx.prepareStatement(" UPDATE  ARBRITO SET "
                    + " FECHAINICIO=? WHERE NUMEROPASAPORTE=? ");
            pst.setBigDecimal(1, arbitro.getNumeropasaporte());
            pst.setDate(2, (java.sql.Date) arbitro.getFechainicio());

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
            PreparedStatement pst = cnx.prepareStatement("DELETE FROM ARBRITO "
                    + " WHERE NUMEROPASAPORTE=?");
            pst.setString(1, cod);
            pst.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en Eliminar datos");
        }
    }
}
