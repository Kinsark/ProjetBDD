package fr.ensimag.jdbc;

import static fr.ensimag.jdbc.Requete.CONN_URL;
import java.sql.*;

public class Action {
    static Connection conn;

    public Action(Connection conn) {
        this.conn = conn;
    }

    public Connection getConnection()
    {
        return conn;
    }
    
    public boolean requete(String requete){
        boolean i = false;
        try {
            //DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            //this.conn = DriverManager.getConnection(CONN_URL, USER, PASSWD);
            // Creation de la requete
            PreparedStatement stmt = conn.prepareStatement(requete);
            
            // Execution de la requete
            ResultSet rset = stmt.executeQuery();
            if (rset.next()){
                
                i = true;
            }
            // Affichage du resultat
            System.out.println("Results:");
            dumpResultRequete(rset);
            System.out.println("");

            // Fermeture 
            rset.close();
            stmt.close();
            //conn.close();
        } catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace(System.err);
        }
    return i;}
    
    public String requeteId(String requete) {
        String idString = null;
        try {
	    //DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            //this.conn = DriverManager.getConnection(CONN_URL, USER, PASSWD);
	    // Creation de la requete
            PreparedStatement stmt = conn.prepareStatement(requete);
	    // Execution de la requete
            ResultSet rset = stmt.executeQuery();
            if(rset.next()){
            idString = rset.getString(1);}
	    // Affichage du resultat
            System.out.println("Results:");
            dumpResultRequete(rset);
            System.out.println("");

	    // Fermeture 
	    rset.close();
            stmt.close();
            //conn.close();

        } catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace(System.err);
        }
    
    return idString ;}
    
    public void transaction(String requete){
        try {
            // Demarrage de la transaction (implicite dans notre cas)
          conn.setAutoCommit(false);
          conn.setTransactionIsolation(conn.TRANSACTION_SERIALIZABLE);

          // Execution des requetes
          PreparedStatement pstmt = conn.prepareStatement
            (requete);

          int rset = pstmt.executeUpdate();
          dumpTransaction(rset);

          // Terminaison de la transaction
          conn.commit();

          // Fermetures
          pstmt.close();
          //conn.close();
        }
        catch (SQLException e) {
          System.err.println("failed !");
          e.printStackTrace();
        }
    }

    private void dumpResultRequete(ResultSet rset) throws SQLException {
        ResultSetMetaData rsetmd = rset.getMetaData();
        int i = rsetmd.getColumnCount();
        while (rset.next()) {
            for (int j = 1; j <= i; j++) {
                System.out.print(rset.getString(j) + "\t");
            }
            System.out.println();
        }
    }
    
    private void dumpTransaction(int r) throws SQLException {
      System.out.println(r);
    }
}
