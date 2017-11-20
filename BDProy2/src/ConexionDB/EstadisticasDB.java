package ConexionDB;

import Clases.Principales.Confederacion;
import Clases.Principales.Persona;
import Clases.Principales.Estadisticas;
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
public class EstadisticasDB {
    
    public ArrayList<Estadisticas> getListaEquipos() {
        Estadisticas cf = new Estadisticas();
        ArrayList<Estadisticas> estad = new ArrayList<>();
        PersonaDB pdb = new PersonaDB();
        PartidoDB pardb = new PartidoDB();
        try {
            Connection cnx = DatabaseConnect.getConnection();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("SELECT NUMEROPARTIDO, NUMEROPASAPORTE, MINUTO, TIPO FROM ESTADISTICAS");
            while (rs.next()) {
                Estadisticas conf = new Estadisticas();
                conf.setNumeropartido(rs.getBigDecimal("NUMEROPARTIDO"));
                conf.setNumeropasaporte(pdb.getPersona(rs.getString("NUMEROPASAPORTE")));
                conf.setMinuto(rs.getBigDecimal("MINUTO").toBigInteger());
                conf.setTipo(rs.getString("TIPO"));
                conf.setPartido(pardb.getPartido(rs.getString("NUMEROPARTIDO")));
                estad.add(conf);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error en Listado");
        }

        return estad;
    }
    
    //Obtener Partido
    public Estadisticas getEstadisticas(String numPartido) {
        Estadisticas conf = null;
        PersonaDB pdb = new PersonaDB();
        PartidoDB pardb = new PartidoDB();
        try {
            Connection cnx = DatabaseConnect.getConnection();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("SELECT NUMEROPARTIDO, NUMEROPASAPORTE, MINUTO, TIPO "
                    + "    FROM ESTADISTICAS WHERE NUMEROPARTIDO= '" + numPartido + "'");
            while (rs.next()) {
                conf = new Estadisticas();
                conf.setNumeropartido(rs.getBigDecimal("NUMEROPARTIDO"));
                conf.setNumeropasaporte(pdb.getPersona(rs.getString("NUMEROPASAPORTE")));
                conf.setMinuto(rs.getBigDecimal("MINUTO").toBigInteger());
                conf.setTipo(rs.getString("TIPO"));
                conf.setPartido(pardb.getPartido(rs.getString("NUMEROPARTIDO")));
               }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en buscar");
        }
        return conf;
    }
    //Insertar DATOS en la DB

    public void InsertEquipo(Estadisticas estad) {

        try {
            Connection cnx = DatabaseConnect.getConnection();
            PreparedStatement pst = cnx.prepareStatement("INSERT INTO ESTADISTICAS(NUMEROPARTIDO, NUMEROPASAPORTE, MINUTO, TIPO)"
                    + " VALUES(?,?,?,?)");
            pst.setBigDecimal(1, estad.getNumeropartido());
            pst.setBigDecimal(2, estad.getNumeropasaporte().getNumeropasaporte());
            pst.setString(3, estad.getMinuto().toString());
            pst.setString(4, estad.getTipo());
            pst.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en Ingresar datos");
        }

    }

    public void UpdateEquipo(Estadisticas estad) {

        try {
            Connection cnx = DatabaseConnect.getConnection();
            PreparedStatement pst = cnx.prepareStatement(" UPDATE  ESTADISTICAS SET "
                    + " NUMEROPASAPORTE=? , MINUTO=? , TIPO=?  WHERE NUMEROPARTIDO=? ");
            pst.setBigDecimal(1, estad.getNumeropasaporte().getNumeropasaporte());
            pst.setString(2, estad.getMinuto().toString());
            pst.setString(3, estad.getTipo());
            pst.setBigDecimal(4, estad.getNumeropartido());
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
            PreparedStatement pst = cnx.prepareStatement("DELETE FROM ESTADISTICAS "
                    + " WHERE NUMEROPARTIDO=?");
            pst.setString(1, cod);
            pst.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en Eliminar datos");
        }
    }
}
