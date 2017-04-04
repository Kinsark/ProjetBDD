package fr.ensimag.jdbc;

import java.sql.*;

public class Connexion {
    static final String CONN_URL = "jdbc:oracle:thin:@ensioracle1.imag.fr:1521:ensioracle1";
    static final String USER = "garatc";
    static final String PASSWD = "garatc";
    
    private Connection conn;
    
    public Connection getConn(){
        return this.conn;
    }
    
    public Connexion() {
        try {
            // Enregistrement du driver Oracle
	    System.out.print("Loading Oracle driver... "); 
	    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            System.out.println("loaded");

	    // Etablissement de la connexion
	    System.out.print("Connecting to the database... "); 
	    this.conn = DriverManager.getConnection(CONN_URL, USER, PASSWD);
            System.out.println("connected");
            
        } catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace(System.err);
        }
    }
}
