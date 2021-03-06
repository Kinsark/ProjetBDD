package fr.ensimag.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Action {
    static Connection conn;
    int count = 1;

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
    
    public ArrayList<String> requeteSet(String requete, int nbArgs) {
        ArrayList<String> set = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(requete);
	    // Execution de la requete
            ResultSet rset = stmt.executeQuery();
                
            ResultSetMetaData rsetmd = rset.getMetaData();
            int i = rsetmd.getColumnCount();
            while (rset.next()) {
                for (int j = 1; j < nbArgs+1; j++) {
                if (rset.getString(j) != null) {
                    set.add(rset.getString(j));
                }
                }
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
    return set;
    }
    
    public Map<String,String> requeteMap(String requete) {
        Map<String,String> set = new HashMap<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(requete);
	    // Execution de la requete
            ResultSet rset = stmt.executeQuery();
                
            ResultSetMetaData rsetmd = rset.getMetaData();
            int i = rsetmd.getColumnCount();
            while (rset.next()) {
                if ((rset.getString(1) != null) && (rset.getString(2) != null))
                    set.put(rset.getString(1),rset.getString(2));    
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
    return set;
    }
    
    public void transaction(String requete) throws SQLException{
        count ++;
        Savepoint save = conn.setSavepoint();
        
        try {
            
          // Execution des requetes
         // conn.setTransactionIsolation(conn.TRANSACTION_READ_COMMITTED);
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
          conn.rollback(save);
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
