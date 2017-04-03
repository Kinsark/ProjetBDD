package fr.ensimag.jdbc;
import java.sql.*;

public class Requete {

    static final String CONN_URL = "jdbc:oracle:thin:@ensioracle1.imag.fr:1521:ensioracle1";
    static final String USER = "garatc";
    static final String PASSWD = "garatc";

    public Requete(String requete) {
        try {
	    // Enregistrement du driver Oracle
	    System.out.print("Loading Oracle driver... "); 
	    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            System.out.println("loaded");

	    // Etablissement de la connexion
	    System.out.print("Connecting to the database... "); 
	    Connection conn = DriverManager.getConnection(CONN_URL, USER, PASSWD);
            System.out.println("connected");

	    // Creation de la requete
            PreparedStatement stmt = conn.prepareStatement(requete);
	    // Execution de la requete
            ResultSet rset = stmt.executeQuery();
	    // Affichage du resultat
            System.out.println("Results:");
            dumpResultSet(rset);
            System.out.println("");

	    // Fermeture 
	    rset.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace(System.err);
        }
    }

    private void dumpResultSet(ResultSet rset) throws SQLException {
        ResultSetMetaData rsetmd = rset.getMetaData();
        int i = rsetmd.getColumnCount();
        while (rset.next()) {
            for (int j = 1; j <= i; j++) {
                System.out.print(rset.getString(j) + "\t");
	    }
	    System.out.println();
        }
    }

}
