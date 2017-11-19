/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionDB;

import Clases.Principales.Entrenador;
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
public class EntrenadorDB {
    public ArrayList<Entrenador> getListaEntrenadores() {
        ArrayList<Entrenador> entrenador = new ArrayList<>();
        PersonaDB pdb = new PersonaDB();
        try {
            Connection cnx = DatabaseConnect.getConnection();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("SELECT NUMEROPASAPORTE, FECHAINICIO FROM ENTRENADOR");
            while (rs.next()) {
                Entrenador conf = new Entrenador();
                conf.setNumeropasaporte(rs.getBigDecimal("NUMEROPASAPORTE"));
                conf.setFechainicio(rs.getDate("FECHAINICIO"));
                conf.setPersona(pdb.getPersona(rs.getString("NUMEROPASAPORTE")));
                entrenador.add(conf);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error en Listado");
        }

        return entrenador   ;
    }
    
    //Obtener una confederacion
    public Entrenador getEntrenador(String numPasaporte) {
        Entrenador conf = null;
        PersonaDB pdb = new PersonaDB();
        try {
            Connection cnx = DatabaseConnect.getConnection();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("SELECT NUMEROPASAPORTE, FECHAINICIO "
                    + "   FROM ENTRENADOR WHERE NUMEROPASAPORTE= '" + numPasaporte + "'");
            while (rs.next()) {
                conf = new Entrenador();
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

    public void InsertEntrenadores(Entrenador entrenador) {

        try {
            Connection cnx = DatabaseConnect.getConnection();
            PreparedStatement pst = cnx.prepareStatement("INSERT INTO ENTRENADOR(NUMEROPASAPORTE, FECHAINICIO)"
                    + " VALUES(?,?)");
            pst.setBigDecimal(1, entrenador.getNumeropasaporte());
            pst.setDate(2, (java.sql.Date) entrenador.getFechainicio());
            pst.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en Ingresar datos");
        }

    }

    public void UpdateEntrenador(Entrenador entrenador) {

        try {
            Connection cnx = DatabaseConnect.getConnection();
            PreparedStatement pst = cnx.prepareStatement(" UPDATE  ENTRENADOR SET "
                    + " FECHAINICIO=? WHERE NUMEROPASAPORTE=? ");
            pst.setBigDecimal(1, entrenador.getNumeropasaporte());
            pst.setDate(2, (java.sql.Date) entrenador.getFechainicio());

            pst.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en Ingresar datos");
        }

    }
//cod para Eliminar

    public void DeleteEntrenador(String cod) {
        try {
            Connection cnx = DatabaseConnect.getConnection();
            PreparedStatement pst = cnx.prepareStatement("DELETE FROM ENTRENADOR "
                    + " WHERE NUMEROPASAPORTE=?");
            pst.setString(1, cod);
            pst.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en Eliminar datos");
        }
    }
}
