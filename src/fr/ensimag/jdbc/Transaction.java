package fr.ensimag.jdbc;

import java.sql.*;

public class Transaction {
    static final String CONN_URL = "jdbc:oracle:thin:@ensioracle1.imag.fr:1521:ensioracle1";
    static final String USER = "garatc";
    static final String PASSWD = "garatc";
    
  public void ExecuteTransaction(String requete) {
    try {
      // Enregistrement du driver Oracle
      System.out.println("Loading Oracle thin driver...");
      DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
      System.out.println("loaded.");
      
      // Etablissement de la connexion
      System.out.println("Connecting to the database...");
      Connection conn = DriverManager.getConnection(CONN_URL, USER, PASSWD);
      System.out.println("connected.");
      
      // Demarrage de la transaction (implicite dans notre cas)
      conn.setAutoCommit(false);
      conn.setTransactionIsolation(conn.TRANSACTION_SERIALIZABLE);
      
      // Execution des requetes
      PreparedStatement pstmt = conn.prepareStatement
        (requete);
	
      int rset = pstmt.executeUpdate();
      dumpResult(rset);
      
      
      // Terminaison de la transaction
      conn.commit();
      
      // Fermetures
      pstmt.close();
      conn.close();
    }
    catch (SQLException e) {
      System.err.println("failed !");
      e.printStackTrace();
    }
  }
    
  void dumpResult(int r) throws SQLException {
      System.out.println(r);
  }
}
