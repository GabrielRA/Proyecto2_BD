/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionDB;

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
public class PersonaDB {
    
    public ArrayList<Persona> getListaPersona() {
        ArrayList<Persona> person = new ArrayList<>();
        PaisDB pdb = new PaisDB();
        try {
            Connection cnx = DatabaseConnect.getConnection();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("SELECT NUMEROPASAPORTE, NOMBRE, APELLIDO1, APELLIDO2, FECHANACIMIENTO, CODIGOPAIS FROM PERSONA");
            while (rs.next()) {
                Persona conf = new Persona();
                conf.setNumeropasaporte(rs.getBigDecimal("NUMEROPASAPORTE"));
                conf.setNombre(rs.getString("NOMBRE"));
                conf.setApellido1(rs.getString("APELLIDO1"));
                conf.setApellido2(rs.getString("APELLIDO2"));
                conf.setFechanacimiento(rs.getDate("FECHANACIMIENTO"));
                conf.setCodigopais(pdb.getPais(rs.getString("CODIGOPAIS")));
                person.add(conf);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error en Listado");
        }

        return person;
    }
    
    //Obtener EquipoDB
    public Persona getPersona(String numPasaporte) {
        ConfederacionDB cf = new ConfederacionDB();
        Persona conf = null;
        PaisDB pdb = new PaisDB();
        try {
            Connection cnx = DatabaseConnect.getConnection();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("SELECT NUMEROPASAPORTE, NOMBRE, APELLIDO1, APELLIDO2, FECHANACIMIENTO, CODIGOPAIS"
                    + "    FROM PERSONA WHERE NUMEROPASAPORTE= '" + numPasaporte + "'");
            while (rs.next()) {
                conf = new Persona();
                conf.setNumeropasaporte(rs.getBigDecimal("NUMEROPASAPORTE"));
                conf.setNombre(rs.getString("NOMBRE"));
                conf.setApellido1(rs.getString("APELLIDO1"));
                conf.setApellido2(rs.getString("APELLIDO2"));
                conf.setFechanacimiento(rs.getDate("FECHANACIMIENTO"));
                conf.setCodigopais(pdb.getPais(rs.getString("CODIGOPAIS")));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en buscar");
        }
        return conf;
    }
    //Insertar DATOS en la DB

    public void InsertPersona(Persona person) {

        try {
            Connection cnx = DatabaseConnect.getConnection();
            PreparedStatement pst = cnx.prepareStatement("INSERT INTO PERSONA(NUMEROPASAPORTE, NOMBRE, APELLIDO1, APELLIDO2, FECHANACIMIENTO, CODIGOPAIS)"
                    + " VALUES(?,?,?,?,?,?)");
            pst.setBigDecimal(1, person.getNumeropasaporte());
            pst.setString(2, person.getNombre());
            pst.setString(3, person.getApellido1());
            pst.setString(4, person.getApellido2());
            pst.setDate(5, (java.sql.Date) person.getFechanacimiento());
            pst.setString(6, person.getCodigopais().getCodigopais());
            pst.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en Ingresar datos");
        }

    }

    public void UpdatePersona(Persona person) {

        try {
            Connection cnx = DatabaseConnect.getConnection();
            PreparedStatement pst = cnx.prepareStatement(" UPDATE PERSONA SET "
                    + " NOMBRE=?, APELLIDO1=?, APELLIDO2=?, FECHANACIMIENTO=?, CODIGOPAIS=? WHERE NUMEROPASAPORTE=? ");
            pst.setBigDecimal(1, person.getNumeropasaporte());
            pst.setString(2, person.getNombre());
            pst.setString(3, person.getApellido1());
            pst.setString(4, person.getApellido2());
            pst.setDate(5, (java.sql.Date) person.getFechanacimiento());
            pst.setString(6, person.getCodigopais().getCodigopais());
            pst.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en Ingresar datos");
        }

    }
//cod para Eliminar

    public void DeletePersona(String cod) {
        try {
            Connection cnx = DatabaseConnect.getConnection();
            PreparedStatement pst = cnx.prepareStatement("DELETE FROM PERSONA "
                    + " WHERE NUMEROPASAPORTE=?");
            pst.setString(1, cod);
            pst.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en Eliminar datos");
        }
    }
    
}
