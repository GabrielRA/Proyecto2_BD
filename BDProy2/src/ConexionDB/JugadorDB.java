/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionDB;

import Clases.Principales.Jugador;
import Clases.Principales.JugadorPK;
import Clases.Principales.Persona;
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
public class JugadorDB {
     public ArrayList<Jugador> getListaJugador() {
        ArrayList<Jugador> arbitro = new ArrayList<>();
        PersonaDB pdb = new PersonaDB();
        try {
            Connection cnx = DatabaseConnect.getConnection();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("SELECT NUMEROPASAPORTE, CAMISA FROM JUGADOR");
            while (rs.next()) {
                Jugador conf = new Jugador();
                JugadorPK jpk = new JugadorPK();
                jpk.setNumeropasaporte(rs.getBigDecimal("NUMEROPASAPORTE").toBigInteger());
                jpk.setCamisa(rs.getBigDecimal("CAMISA").toBigInteger());
                conf.setJugadorPK(jpk);
                conf.setPersona(pdb.getPersona(rs.getString("NUMEROPASAPORTE")));
                arbitro.add(conf);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error en Listado");
        }

        return arbitro;
    }
     
    //Obtener Jugador
    public Jugador getJugador(String numPasaporte, String camisa) {
        Jugador conf = null;
        PersonaDB pdb = new PersonaDB();
        try {
            Connection cnx = DatabaseConnect.getConnection();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("SELECT NUMEROPASAPORTE, CAMISA "
                    + "    FROM JUGADOR WHERE NUMEROPASAPORTE= '" + numPasaporte + "' AND CAMISA= '"+ camisa +"'");
            while (rs.next()) {
                JugadorPK jpk = new JugadorPK();
                jpk.setNumeropasaporte(rs.getBigDecimal("NUMEROPASAPORTE").toBigInteger());
                jpk.setCamisa(rs.getBigDecimal("CAMISA").toBigInteger());
                conf.setJugadorPK(jpk);
                conf.setPersona(pdb.getPersona(rs.getString("NUMEROPASAPORTE")));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en buscar");
        }
        return conf;
    }
     
    //Insertar DATOS en la DB

    public void InsertJugador(Jugador jugador) {

        try {
            Connection cnx = DatabaseConnect.getConnection();
            PreparedStatement pst = cnx.prepareStatement("INSERT INTO JUGADOR(NUMEROPASAPORTE, CAMISA)"
                    + " VALUES(?,?)");
            pst.setInt(1, jugador.getJugadorPK().getNumeropasaporte().intValue());
            pst.setInt(2, jugador.getJugadorPK().getCamisa().intValue());
            pst.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en Ingresar datos");
        }

    }

    public void UpdateJugador(Jugador jugador) {

        try {
            Connection cnx = DatabaseConnect.getConnection();
            PreparedStatement pst = cnx.prepareStatement(" UPDATE  JUGADOR SET "
                    + "  WHERE NUMEROPASAPORTE=? AND CAMISA=?");
            pst.setInt(1, jugador.getJugadorPK().getNumeropasaporte().intValue());
            pst.setInt(2, jugador.getJugadorPK().getCamisa().intValue());
            pst.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en Ingresar datos");
        }

    }
//cod para Eliminar

    public void DeleteJugador(String cod) {
        try {
            Connection cnx = DatabaseConnect.getConnection();
            PreparedStatement pst = cnx.prepareStatement("DELETE FROM JUGADOR "
                    + " WHERE NUMEROPASAPORTE=? AND CAMISA=?");
            pst.setString(1, cod);
            pst.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en Eliminar datos");
        }
    }
}
