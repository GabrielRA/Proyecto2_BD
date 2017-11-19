/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionDB;

import Clases.Principales.Arbitro;
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
    public ArrayList<Arbitro> getListaArbitros() {
        ArrayList<Arbitro> arbitro = new ArrayList<>();
        PersonaDB pdb = new PersonaDB();
        try {
            Connection cnx = DatabaseConnect.getConnection();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("SELECT NUMEROPASAPORTE, FECHAINICIO FROM ARBITRO");
            while (rs.next()) {
                Arbitro conf = new Arbitro();
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
    public Arbitro getArbitro(String numPasaporte) {
        Arbitro conf = null;
        PersonaDB pdb = new PersonaDB();
        try {
            Connection cnx = DatabaseConnect.getConnection();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("SELECT NUMEROPASAPORTE, FECHAINICIO"
                    + "    FROM ARBITRO WHERE NUMEROPASAPORTE= '" + numPasaporte + "'");
            while (rs.next()) {
                conf = new Arbitro();
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

    public void InsertArbitros(Arbitro arbitro) {

        try {
            Connection cnx = DatabaseConnect.getConnection();
            PreparedStatement pst = cnx.prepareStatement("INSERT INTO ARBITRO(NUMEROPASAPORTE, FECHAINICIO)"
                    + " VALUES(?,?)");
            pst.setBigDecimal(1, arbitro.getNumeropasaporte());
            pst.setDate(2, (java.sql.Date) arbitro.getFechainicio());
            pst.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en Ingresar datos");
        }

    }

    public void UpdateArbitro(Arbitro arbitro) {

        try {
            Connection cnx = DatabaseConnect.getConnection();
            PreparedStatement pst = cnx.prepareStatement(" UPDATE  ARBITRO SET "
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
            PreparedStatement pst = cnx.prepareStatement("DELETE FROM ARBITRO "
                    + " WHERE NUMEROPASAPORTE=?");
            pst.setString(1, cod);
            pst.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en Eliminar datos");
        }
    }
}
