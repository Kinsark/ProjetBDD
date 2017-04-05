package fr.ensimag.ihm;

import fr.ensimag.jdbc.*;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class FenetrePrincipale extends JFrame implements ActionListener {

    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuAjout = new JMenu("Ajouter");
    private JMenu menuPersonne = new JMenu("Personne");
    private JMenu menuStatistiques = new JMenu("Statistiques");
    private JMenu menuAPropos = new JMenu("A propos");

    private JMenuItem itemStage = new JMenuItem("Stage");
    private JMenuItem itemMembre = new JMenuItem("Membre");
    private JMenuItem itemMoniteur = new JMenuItem("Moniteur");
    private JMenuItem itemAfficher = new JMenuItem("Afficher");
    private JMenuItem itemAPropos = new JMenuItem("A propos...");
    
    private Connection conn;
    
    public Connection getConn()
    {
        return conn;
    }

    public FenetrePrincipale(Connection conn) {
        // parametres globaux
        this.setTitle("A bout de souffle !");
        this.setSize(500, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.conn = conn;
        // gestion du menu
        this.menuAjout.add(this.itemStage);
        this.menuAjout.addSeparator();
        this.menuAjout.add(this.menuPersonne);
        this.menuPersonne.add(this.itemMembre);
        this.menuPersonne.add(this.itemMoniteur);

        this.menuStatistiques.add(this.itemAfficher);

        this.menuAPropos.add(this.itemAPropos);

        // affichage de la barre du menu
        this.menuBar.add(this.menuAjout);
        this.menuBar.add(this.menuStatistiques);
        this.menuBar.add(this.menuAPropos);
        this.setJMenuBar(this.menuBar);

        // gestion des events
        this.itemMembre.addActionListener(this);
        this.itemMoniteur.addActionListener(this);
        this.itemAPropos.addActionListener(this);
        this.itemStage.addActionListener(this);
        this.itemAfficher.addActionListener(this);

        this.setVisible(true);
    }

    // gestion des evenements lies aux boutons du menu
    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == this.itemMembre) {
            this.setContentPane(new FenetreAjoutMembre(conn));
			this.pack();
        }
        if (arg0.getSource() == this.itemMoniteur) {
            this.setContentPane(new FenetreAjoutMoniteur(conn));
			this.pack();
        }
        if (arg0.getSource() == this.itemStage) {
            this.setContentPane(new FenetreAjoutStage(conn));
			this.pack();
        }
        if (arg0.getSource() == this.itemAPropos) {
            JOptionPane jop1 = new JOptionPane();
            jop1.showMessageDialog(null, "Projet bases de données\nCode réalisé par l'équipe 8.", "A propos", JOptionPane.INFORMATION_MESSAGE);
        }
        if (arg0.getSource() == this.itemAfficher) {
            this.setContentPane(new FenetreStatistiques(conn));
			this.pack();
        }
        this.setVisible(true);
    }

    public static void main(String[] args) throws SQLException {
        Connexion connec = new Connexion();
        
        FenetrePrincipale fp = new FenetrePrincipale(connec.getConn());
        // A modif
        
        Action action = new Action(fp.getConn());
        InterfaceRequete intReq = new InterfaceRequete();
        
        // on ferme la connexion quand l'user quitte l'application
        // pb : si l'app crash la connexion ne ferme pas
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                fp.getConn().close();
                System.out.println("disconnected");
            } catch (SQLException ex) {
                Logger.getLogger(FenetrePrincipale.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }));
    }
}
