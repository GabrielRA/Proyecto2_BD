/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionDB;

import Clases.Principales.Equipo;
import Clases.Principales.Confederacion;
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
public class EquipoDB {
    
     public ArrayList<Equipo> getListaEquipos() {
        ConfederacionDB cf = new ConfederacionDB();
        Confederacion confed = null;
        ArrayList<Equipo> equip = new ArrayList<>();
        try {
            Connection cnx = DatabaseConnect.getConnection();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("SELECT CODIGOPAIS, NOMBRE, CODIGOCONFEDERACION, GRUPO FROM EQUIPO");
            while (rs.next()) {
                Equipo conf = new Equipo();
                confed = new Confederacion();
                //cf.setCodigoconfederacion(rs.getString("CODIGOCONFEDERACION"));
                confed = cf.getConfederacion(rs.getString("CODIGOCONFEDERACION"));
                conf.setCodigopais(rs.getString("CODIGOPAIS"));
                conf.setNombre(rs.getString("NOMBRE"));
                conf.setCodigoconfederacion(confed);
                conf.setGrupo(rs.getString("Grupo"));
                equip.add(conf);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error en Listado");
        }

        return equip;
    }
    
     //Obtener EquipoDB
     public Equipo getEquipo(String codPais) {
        ConfederacionDB cf = new ConfederacionDB();
        Equipo conf = null;
        try {
            Connection cnx = DatabaseConnect.getConnection();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("SELECT CODIGOPAIS, NOMBRE, CODIGOCONFEDERACION, GRUPO "
                    + "    FROM EQUIPO WHERE CODIGOPAIS= '" + codPais + "'");
            while (rs.next()) {
                conf = new Equipo();
                conf.setCodigopais(rs.getString("CODIGOPAIS"));
                conf.setNombre(rs.getString("NOMBRE"));
                conf.setCodigoconfederacion(cf.getConfederacion(rs.getString("CODIGOCONFEDERACION")));
                conf.setGrupo(rs.getString("GRUPO"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en buscar");
        }
        return conf;
    }
    
     
    //Insertar DATOS en la DB

    public void InsertEquipo(Equipo equip) {

        try {
            Connection cnx = DatabaseConnect.getConnection();
            PreparedStatement pst = cnx.prepareStatement("INSERT INTO EQUIPO(CODIGOPAIS, NOMBRE, CODIGOCONFEDERACION, GRUPO)"
                    + " VALUES(?,?,?,?)");
            pst.setString(1, equip.getCodigopais());
            pst.setString(2, equip.getNombre());
            pst.setString(3, equip.getCodigoconfederacion().getCodigoconfederacion());
            pst.setString(4, equip.getGrupo());
            pst.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en Ingresar datos");
        }

    }

    public void UpdateEquipo(Equipo equip) {

        try {
            Connection cnx = DatabaseConnect.getConnection();
            PreparedStatement pst = cnx.prepareStatement(" UPDATE  EQUIPO SET "
                    + " NOMBRE=? , CODIGOCONFEDERACION=? , GRUPO=?  WHERE CODIGOPAIS=? ");
            pst.setString(1, equip.getCodigopais());
            pst.setString(2,equip.getNombre());
            pst.setString(3, equip.getCodigoconfederacion().getCodigoconfederacion());
            pst.setString(4,equip.getGrupo());
            pst.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en Ingresar datos");
        }

    }
//cod para Eliminar

    public void DeleteEquipo(String cod) {
        try {
            Connection cnx = DatabaseConnect.getConnection();
            PreparedStatement pst = cnx.prepareStatement("DELETE FROM EQUIPO "
                    + " WHERE CODIGOPAIS=?");
            pst.setString(1, cod);
            pst.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en Eliminar datos");
        }
    }
}
