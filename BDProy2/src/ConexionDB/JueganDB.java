/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionDB;

import Clases.Principales.Juegan;
import Clases.Principales.JueganPK;
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
public class JueganDB {
    
    public ArrayList<Juegan> getListaJuegan() {
        ArrayList<Juegan> juega = new ArrayList<>();
        PartidoDB pardb = new PartidoDB();
        try {
            Connection cnx = DatabaseConnect.getConnection();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("SELECT NUMEROPARTIDO, CODIGO_PAIS FROM JUEGAN");
            while (rs.next()) {
                Juegan conf = new Juegan();
                conf.setPartido(pardb.getPartido("NUMEROPARTIDO"));
                JueganPK jpk = new JueganPK();
                jpk.setCodigoPais(rs.getBigDecimal("CODIGO_PAIS").toBigInteger());
                jpk.setNumeropartido(pardb.getPartido("NUMEROPARTIDO").getNumeropartido().toBigInteger());
                conf.setJueganPK(jpk);
                juega.add(conf);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error en Listado");
        }

        return juega;
    }
    
    //Obtener Jugador
    public Juegan getJuega(String numPartido, String codPais ) {
        Juegan conf = null;
        PartidoDB pardb = new PartidoDB();
        try {
            Connection cnx = DatabaseConnect.getConnection();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("SELECT NUMEROPARTIDO, CODIGO_PAIS "
                    + "    FROM JUEGAN WHERE NUMEROPARTIDO= '" + numPartido + "' AND CODIGO_PAIS= '" + codPais +"'");
            while (rs.next()) {
                conf = new Juegan();
                conf.setPartido(pardb.getPartido("NUMEROPARTIDO"));
                JueganPK jpk = new JueganPK();
                jpk.setCodigoPais(rs.getBigDecimal("CODIGO_PAIS").toBigInteger());
                jpk.setNumeropartido(pardb.getPartido("NUMEROPARTIDO").getNumeropartido().toBigInteger());
                conf.setJueganPK(jpk);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en buscar");
        }
        return conf;
    }
    //Insertar DATOS en la DB

    public void InsertJuega(Juegan juega) {

        try {
            Connection cnx = DatabaseConnect.getConnection();
            PreparedStatement pst = cnx.prepareStatement("INSERT INTO JUEGAN(NUMEROPARTIDO, CODIGO_PAIS)"
                    + " VALUES(?,?)");
            pst.setBigDecimal(1, juega.getPartido().getNumeropartido());
            pst.setString(2, juega.getJueganPK().getCodigoPais().toString(0));
            pst.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en Ingresar datos");
        }

    }

    public void UpdateJuega(Juegan juega) {

        try {
            Connection cnx = DatabaseConnect.getConnection();
            PreparedStatement pst = cnx.prepareStatement(" UPDATE  JUEGAN SET "
                    + "WHERE NUMEROPARTIDO=? AND CODIGO_PAIS=?");
            pst.setBigDecimal(1, juega.getPartido().getNumeropartido());
            pst.setString(2, juega.getJueganPK().getCodigoPais().toString(0));
            pst.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en Ingresar datos");
        }

    }
//cod para Eliminar

    public void DeleteJuega(String cod) {
        try {
            Connection cnx = DatabaseConnect.getConnection();
            PreparedStatement pst = cnx.prepareStatement("DELETE FROM JUEGAN "
                    + " WHERE NUMEROPARTIDO=? AND CODIGO_PAIS=?");
            pst.setString(1, cod);
            pst.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en Eliminar datos");
        }
    }
}
