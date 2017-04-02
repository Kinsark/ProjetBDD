package fr.ensimag.ihm;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JFrame;

public class FenetreAjoutStage extends JPanel implements ActionListener {

    private Box bv = Box.createVerticalBox();
    private JLabel labelHeureDebut = new JLabel("Heure de début");
    private JTextField tfHeureDebut = new JTextField(4);
    private JLabel labelHeureFin = new JLabel("Heure de fin");
    private JTextField tfHeureFin = new JTextField(4);
    private JLabel labelJour = new JLabel("Jour");
    private JTextField tfJour = new JTextField(4);
    private JLabel labelTerrain = new JLabel("Terrain");
    private JComboBox comboTerrain;
    private JLabel labelSport = new JLabel("Sport");
    private JComboBox comboSport;
    private DualListBox dualStagiaires = new DualListBox();
    private DualListBox dualMoniteurs = new DualListBox();
    private JButton boutonStagiaire = new JButton("Sélectionner les stagiaires");
    private JButton boutonMoniteur = new JButton("Sélectionner les moniteurs");
    private JButton boutonValider = new JButton("Valider");

    public FenetreAjoutStage() {
        this.add(bv);
        // heure de debut
        Box boxHeureDebut = Box.createHorizontalBox();
        boxHeureDebut.add(this.labelHeureDebut);
        boxHeureDebut.add(this.tfHeureDebut);
        this.bv.add(boxHeureDebut);

        // heure de fin
        Box boxHeureFin = Box.createHorizontalBox();
        boxHeureFin.add(this.labelHeureFin);
        boxHeureFin.add(this.tfHeureFin);
        this.bv.add(boxHeureFin);

        // jour
        Box boxJour = Box.createHorizontalBox();
        boxJour.add(this.labelJour);
        boxJour.add(this.tfJour);
        this.bv.add(boxJour);

        // sport
        Box boxSport = Box.createHorizontalBox();
        boxSport.add(this.labelSport);
        // exemple illustratif, faire un getSport
        String[] sports = {"Sport 1", "Sport 2", "Sport 3"};
        this.comboSport = new JComboBox(sports);
        boxSport.add(this.comboSport);
        this.bv.add(boxSport);

        // terrain
        Box boxTerrain = Box.createHorizontalBox();
        boxTerrain.add(this.labelTerrain);
        // exemple illustratif, faire un getTerrain
        String[] terrains = {"Terrain 1", "Terrain 2", "Terrain 3"};
        this.comboTerrain = new JComboBox(terrains);
        boxTerrain.add(this.comboTerrain);
        this.bv.add(boxTerrain);

        this.boutonStagiaire.addActionListener(this);
        this.boutonMoniteur.addActionListener(this);
        this.boutonValider.addActionListener(this);
        
        this.bv.add(this.boutonStagiaire);
        this.bv.add(this.boutonMoniteur);
        this.bv.add(this.boutonValider);
    }

    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == this.boutonStagiaire) {
            JFrame f = new JFrame("Sélectionner les stagiaires");
            
            dualStagiaires.addSourceElements(new String[]{"One", "Two", "Three"});
            dualStagiaires.addSourceElements(new String[]{"Four", "Five", "Six"});
            dualStagiaires.addSourceElements(new String[]{"Seven", "Eight", "Nine"});
            dualStagiaires.addSourceElements(new String[]{"Ten", "Eleven", "Twelve"});
            dualStagiaires.addSourceElements(new String[]{"Thirteen", "Fourteen",
                "Fifteen"});
            dualStagiaires.addSourceElements(new String[]{"Sixteen", "Seventeen",
                "Eighteen"});
            dualStagiaires.addSourceElements(new String[]{"Nineteen", "Twenty", "Thirty"});
            f.getContentPane().add(dualStagiaires, BorderLayout.CENTER);
            f.setSize(600, 300);
            f.setVisible(true);
            f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        }
        if (arg0.getSource() == this.boutonMoniteur) {
            JFrame f = new JFrame("Sélectionner les moniteurs");
            
            dualMoniteurs.addSourceElements(new String[]{"One", "Two", "Three"});
            dualMoniteurs.addSourceElements(new String[]{"Four", "Five", "Six"});
            dualMoniteurs.addSourceElements(new String[]{"Seven", "Eight", "Nine"});
            dualMoniteurs.addSourceElements(new String[]{"Ten", "Eleven", "Twelve"});
            dualMoniteurs.addSourceElements(new String[]{"Thirteen", "Fourteen",
                "Fifteen"});
            dualMoniteurs.addSourceElements(new String[]{"Sixteen", "Seventeen",
                "Eighteen"});
            dualMoniteurs.addSourceElements(new String[]{"Nineteen", "Twenty", "Thirty"});
            f.getContentPane().add(dualMoniteurs, BorderLayout.CENTER);
            f.setSize(600, 300);
            f.setVisible(true);
            f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        }
        if (arg0.getSource() == this.boutonValider) {
            Iterator itStagiaires = dualStagiaires.destinationIterator();
            System.out.println("Stagiaires :");
            while (itStagiaires.hasNext()) {
                System.out.println(itStagiaires.next().toString());
            }
            Iterator itMoniteurs = dualMoniteurs.destinationIterator();
            System.out.println("Moniteurs :");
            while (itMoniteurs.hasNext()) {
                System.out.println(itMoniteurs.next().toString());
            }
        }
        this.setVisible(true);
    }
}
