package fr.ensimag.ihm;

import fr.ensimag.jdbc.*;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public FenetrePrincipale() {
        // parametres globaux
        this.setTitle("A bout de souffle !");
        this.setSize(500, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
            this.setContentPane(new FenetreAjoutMembre());
			this.pack();
        }
        if (arg0.getSource() == this.itemMoniteur) {
            this.setContentPane(new FenetreAjoutMoniteur());
			this.pack();
        }
        if (arg0.getSource() == this.itemStage) {
            this.setContentPane(new FenetreAjoutStage());
			this.pack();
        }
        if (arg0.getSource() == this.itemAPropos) {
            JOptionPane jop1 = new JOptionPane();
            jop1.showMessageDialog(null, "Projet bases de données\nCode réalisé par l'équipe 8.", "A propos", JOptionPane.INFORMATION_MESSAGE);
        }
        if (arg0.getSource() == this.itemAfficher) {
            this.setContentPane(new FenetreStatistiques());
			this.pack();
        }
        this.setVisible(true);
    }

    public static void main(String[] args) {
        FenetrePrincipale fp = new FenetrePrincipale();
        // A modif
        Connexion connexion = new Connexion();
        Action action = new Action(connexion.getConn());
        InterfaceRequete intReq = new InterfaceRequete();
    }
}
