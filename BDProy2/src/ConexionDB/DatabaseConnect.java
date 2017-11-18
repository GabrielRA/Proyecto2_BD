/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author M Express
 */

public class DatabaseConnect {
  public static Connection getConnection() {
      
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");  //DRIVER DE SQL DEVELOPER
            String myDB = "jdbc:oracle:thin:@localhost:1521:XE";//URL DE SQL DEVELOPER
            
           // Class.forName("org.apache.derby.jdbc.ClientDriver");//DRIVER DE JAVADB
           // String myDB = "jdbc:derby://localhost:1527/DBTRASPORTE";//URL DE JAVADB
            
            String usuario="bdproy2";
            String password="bdproy2";
            Connection cnx = DriverManager.getConnection(myDB,usuario,password);
            
            return cnx;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }      
}










