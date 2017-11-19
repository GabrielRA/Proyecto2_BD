/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionDB;

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
public class ConfederacionDB {
    public ArrayList<Confederacion> getListarConfederaciones() {
        ArrayList<Confederacion> confederacion = new ArrayList<>();
        try {
            Connection cnx = DatabaseConnect.getConnection();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("SELECT CODIGOCONFEDERACION, NOMBRE FROM CONFEDERACION");
            while (rs.next()) {
                Confederacion conf = new Confederacion();
                conf.setCodigoconfederacion(rs.getString("CODIGOCONFEDERACION"));
                conf.setNombre(rs.getString("NOMBRE"));
                confederacion.add(conf);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error en Listado");
        }

        return confederacion;
    }
    //Insertar DATOS en la DB

    public void InsertCursos(Confederacion confederacion) {

        try {
            Connection cnx = DatabaseConnect.getConnection();
            PreparedStatement pst = cnx.prepareStatement("INSERT INTO CONFEDERACION(CODIGOCONFEDERACION, NOMBRE)"
                    + " VALUES(?,?)");
            pst.setString(1, confederacion.getCodigoconfederacion());
            pst.setString(2, confederacion.getNombre());
            pst.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en Ingresar datos");
        }

    }

    public void UpdatetDistrito(Confederacion conf) {

        try {
            Connection cnx = DatabaseConnect.getConnection();
            PreparedStatement pst = cnx.prepareStatement(" UPDATE  CONFEDERACION SET "
                    + " NOMBRE=? WHERE CODIGOCONFEDERACION=? ");
            pst.setString(1, conf.getCodigoconfederacion());
            pst.setString(2, conf.getNombre());

            pst.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en Ingresar datos");
        }

    }
//cod para Eliminar

    public void DeleteCursos(String cod) {
        try {
            Connection cnx = DatabaseConnect.getConnection();
            PreparedStatement pst = cnx.prepareStatement("DELETE FROM CONFEDERACION "
                    + " WHERE CODIGOCONFEDERACION=?");
            pst.setString(1, cod);
            pst.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en Eliminar datos");
        }
}
}
