/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionDB;
import Clases.Principales.Supervisa;
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
public class SupervisaDB {
    
    public ArrayList<Supervisa> getListaSupervisa() {
        ArrayList<Supervisa> sede = new ArrayList<>();
        PersonaDB perdb = new PersonaDB();
        PartidoDB pdb = new PartidoDB();
        try {
            Connection cnx = DatabaseConnect.getConnection();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("SELECT NUMEROPARTIDO, NUMEROPASAPORTE, TIPO FROM SUPERVISA");
            while (rs.next()) {
                Supervisa conf = new Supervisa();
                conf.setNumeropartido(rs.getBigDecimal("NUMEROPARTIDO"));
                conf.setTipo(rs.getString("TIPO"));
                conf.setNumeropasaporte(perdb.getPersona(rs.getString("NUMEROPASAPORTE")));
                conf.setPartido(pdb.getPartido(rs.getString("NUMEROPARTIDO")));
                sede.add(conf);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error en Listado");
        }

        return sede;
    }
    
    //Obtener Arbitro
    public Supervisa getSupervisor(String numPartido) {
        Supervisa conf = null;
        PersonaDB perdb = new PersonaDB();
        PartidoDB pdb = new PartidoDB();
        try {
            Connection cnx = DatabaseConnect.getConnection();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("SELECT NUMEROPARTIDO, NUMEROPASAPORTE, TIPO FROM SUPERVISA"
                    + "    FROM SUPERVISA WHERE NUMEROPARTIDO= '" + numPartido + "'");
            while (rs.next()) {
                conf = new Supervisa();
                conf.setNumeropartido(rs.getBigDecimal("NUMEROPARTIDO"));
                conf.setTipo(rs.getString("TIPO"));
                conf.setNumeropasaporte(perdb.getPersona(rs.getString("NUMEROPASAPORTE")));
                conf.setPartido(pdb.getPartido(rs.getString("NUMEROPARTIDO")));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en buscar");
        }
        return conf;
    }
    //Insertar DATOS en la DB

    public void InsertSupervisa(Supervisa supv) {

        try {
            Connection cnx = DatabaseConnect.getConnection();
            PreparedStatement pst = cnx.prepareStatement("INSERT INTO SUPERVISA(NUMEROPARTIDO, NUMEROPASAPORTE, TIPO)"
                    + " VALUES(?,?,?)");
            pst.setBigDecimal(1, supv.getNumeropartido());
            pst.setBigDecimal(2, supv.getNumeropasaporte().getNumeropasaporte());
            pst.setString(3, supv.getTipo());
            pst.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en Ingresar datos");
        }

    }

    public void UpdateSupervisa(Supervisa supv) {

        try {
            Connection cnx = DatabaseConnect.getConnection();
            PreparedStatement pst = cnx.prepareStatement(" UPDATE SUPERVISA SET "
                    + " NUMEROPASAPORTE=?, TIPO=? WHERE NUMEROPARTIDO=? ");
            pst.setBigDecimal(1, supv.getNumeropartido());
            pst.setBigDecimal(2, supv.getNumeropasaporte().getNumeropasaporte());
            pst.setString(3, supv.getTipo());
            pst.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en Ingresar datos");
        }

    }
//cod para Eliminar

    public void DeleteSupervisa(String cod) {
        try {
            Connection cnx = DatabaseConnect.getConnection();
            PreparedStatement pst = cnx.prepareStatement("DELETE FROM SUPERVISA "
                    + " WHERE NUMEROPARTIDO=?");
            pst.setString(1, cod);
            pst.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en Eliminar datos");
        }
    }
}
