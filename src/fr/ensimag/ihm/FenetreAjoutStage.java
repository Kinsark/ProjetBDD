package fr.ensimag.ihm;

import fr.ensimag.jdbc.RequetesGenerales;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;

public class FenetreAjoutStage extends JPanel implements ActionListener {

    private Connection conn;
    private RequetesGenerales re;

    private static final DateFormat FORMAT_JOUR = new SimpleDateFormat("dd/MM/yyyy");
    private static final DateFormat FORMAT_HEURE = new SimpleDateFormat("HH:mm");

    private JOptionPane jop = new JOptionPane();
    private int yPos = 0;

    private JLabel labelHeureDebut = new JLabel("Heure de début (hh:mm)");
    private JFormattedTextField ftfHeureDebut = new JFormattedTextField(this.FORMAT_HEURE);

    private JLabel labelHeureFin = new JLabel("Heure de fin (hh:mm)");
    private JFormattedTextField ftfHeureFin = new JFormattedTextField(this.FORMAT_HEURE);

    private JLabel labelJour = new JLabel("Jour (jj/mm/aaaa)");
    private JFormattedTextField ftfJour = new JFormattedTextField(this.FORMAT_JOUR);

    protected JLabel labelCommune = new JLabel("Code postal");
    protected JTextField tfCommune = new JTextField(10);
    
    private JLabel labelTerrain = new JLabel("Terrain");
    private JComboBox comboTerrain;

    private JLabel labelSport = new JLabel("Sport");
    private JComboBox comboSport;

    private DualListBox dualStagiaires = new DualListBox();
    private DualListBox dualMoniteurs = new DualListBox();
    private DualListBox dualSuperviseur = new DualListBox();

    private JFrame frameStagiaires = new JFrame("Sélectionner les stagiaires");
    private JFrame frameMoniteurs = new JFrame("Sélectionner les moniteurs");
    private JFrame frameSuperviseur = new JFrame("Sélectionner le superviseur");

    private JButton boutonStagiaire = new JButton("Sélectionner les stagiaires");
    private JButton boutonMoniteur = new JButton("Sélectionner les moniteurs");
    private JButton boutonSuperviseur = new JButton("Sélectionner le superviseur");
    private JButton boutonValider = new JButton("Valider");
    
    private static final String REGEX_COMMUNE = "[0-9]{5}";

    public FenetreAjoutStage(Connection conn) {
        Border border = this.getBorder();
        Border margin = new EmptyBorder(10, 10, 10, 10);
        this.setBorder(new CompoundBorder(border, margin));

        this.conn = conn;
        re = new RequetesGenerales(conn);

        GridBagLayout panelGridBagLayout = new GridBagLayout();
        panelGridBagLayout.columnWidths = new int[]{86, 86, 0};
        panelGridBagLayout.rowHeights = new int[]{20, 20, 20, 20, 20, 0};
        panelGridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        panelGridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        this.setLayout(panelGridBagLayout);

        // heure de debut
        this.labelHeureDebut.setLabelFor(this.ftfHeureDebut);
        try {
            MaskFormatter dateMask = new MaskFormatter("##:##");
            dateMask.install(ftfHeureDebut);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // heure de fin
        this.labelHeureFin.setLabelFor(this.ftfHeureFin);
        try {
            MaskFormatter dateMask = new MaskFormatter("##:##");
            dateMask.install(ftfHeureFin);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // jour
        try {
            MaskFormatter dateMask = new MaskFormatter("##/##/####");
            dateMask.install(ftfJour);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // sport
        this.comboSport = new JComboBox(re.getAllSports().toArray());

        // terrain
        tfCommune.setText("00000");
        this.comboTerrain = new JComboBox(re.getTerrainsParSport('\'' + comboSport.getSelectedItem().toString() + '\'', tfCommune.getText()).toArray());

        this.comboSport.addActionListener(this);
        this.boutonStagiaire.addActionListener(this);
        this.boutonMoniteur.addActionListener(this);
        this.boutonValider.addActionListener(this);
        this.boutonSuperviseur.addActionListener(this);

        //this.initFrameStagiaires();
        this.initFrameMoniteurs();

        this.addLabelAndComponent(labelHeureDebut, ftfHeureDebut);
        this.addLabelAndComponent(labelHeureFin, ftfHeureFin);
        this.addLabelAndComponent(labelJour, ftfJour);
        this.addLabelAndComponent(labelCommune, tfCommune);
        this.addLabelAndComponent(labelSport, comboSport);
        this.addLabelAndComponent(labelTerrain, comboTerrain);
        this.addLabelAndComponent(new JLabel("Sélectionner les stagiaires"), boutonStagiaire);
        this.addLabelAndComponent(new JLabel("Sélectionner les moniteurs"), boutonMoniteur);
        this.addLabelAndComponent(new JLabel("Sélectionner le superviseur"), boutonSuperviseur);
        this.addLabelAndComponent(new JLabel("Valider"), boutonValider);
    }

    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == this.comboSport) {
            DefaultComboBoxModel model = new DefaultComboBoxModel(re.getTerrainsParSport('\'' + comboSport.getSelectedItem().toString() + '\'', tfCommune.getText()).toArray());
            comboTerrain.setModel(model);
        }
        if (arg0.getSource() == this.boutonStagiaire) {
            this.initFrameStagiaires();
            this.frameStagiaires.setVisible(true);
        }
        if (arg0.getSource() == this.boutonMoniteur) {
            if ((ftfHeureFin.getText().equals("  :  ")) || (ftfHeureDebut.getText().equals("  :  ")) || 
                (ftfJour.getText().equals("  /  /    ")))
                jop.showMessageDialog(null, "Vous devez sélectionner un créneau !", "Erreur", JOptionPane.ERROR_MESSAGE);
            else
                this.frameMoniteurs.setVisible(true);
        }
        if (arg0.getSource() == this.boutonSuperviseur) {
            if (this.dualMoniteurs.getSelectedNumber() == 0) {
                jop.showMessageDialog(null, "Veuillez sélectionner un ou plusieurs moniteurs.", "Erreur", JOptionPane.ERROR_MESSAGE);
            } else {
                // bug quand on ajoute un moniteur dans frameMoniteur
                this.frameSuperviseur.setVisible(true);
            }
        }
        if (arg0.getSource() == this.boutonValider) {
            if (this.verifierFormulaire()) {
                // TODO : mettre dans la BDD 
                String idCommune = re.GetCommuneFromTerrain(comboTerrain.getSelectedItem().toString());
                Iterator itMoniteurs = dualSuperviseur.destinationIterator();
                
                String infosMoniteur = itMoniteurs.next().toString();
                String[] infos = infosMoniteur.split(" ");
                String idMoniteur = infos[2];
                
                re.AjoutStage(idMoniteur, idCommune, ftfHeureDebut.getText(), ftfHeureFin.getText(), ftfJour.getText(),
                        comboSport.getSelectedItem().toString(), comboTerrain.getSelectedItem().toString());
                
                // ajout des encadrants
                itMoniteurs = dualMoniteurs.destinationIterator();
                while (itMoniteurs.hasNext()) {
                    String[] inf = itMoniteurs.next().toString().split(" ");
                    idMoniteur = inf[2];
                    re.AjouterEncadrant(idCommune, idMoniteur);
                } 
                
                jop.showMessageDialog(null, "Le stage a été créé", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        this.setVisible(true);
    }

    // Retourne true si les saisies de l'utilisateur sont correctes, false sinon
    private boolean verifierFormulaire() {
        String[] heureDebut = this.ftfHeureDebut.getText().split(":");
        String[] heureFin = this.ftfHeureFin.getText().split(":");
        int heureDebutH = 0, heureDebutM = 0, heureFinH = 0, heureFinM = 0;
        String[] date = this.ftfJour.getText().split("/");
        int jour = 0, mois = 0, annee = 0;
        try {
            heureDebutH = Integer.parseInt(heureDebut[0]);
            heureDebutM = Integer.parseInt(heureDebut[1]);
        } catch (NumberFormatException e) {
            jop.showMessageDialog(null, "L'heure de début est mal saisie !", "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            heureFinH = Integer.parseInt(heureFin[0]);
            heureFinM = Integer.parseInt(heureFin[1]);
        } catch (NumberFormatException e) {
            jop.showMessageDialog(null, "L'heure de fin est mal saisie !", "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if ((heureDebutH > heureFinH) || (heureDebutH == heureFinH) && (heureDebutM > heureFinM)) {
            jop.showMessageDialog(null, "L'heure de début et l'heure de fin sont incompatibles !", "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            jour = Integer.parseInt(date[0]);
            mois = Integer.parseInt(date[1]);
            annee = Integer.parseInt(date[2]);
        } catch (NumberFormatException e) {
            jop.showMessageDialog(null, "Le jour est mal saisi !", "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // TODO : faire le test avec les horaires du stade
        // utiliser this.comboSport.getSelectedItem().toString()
        if (dualStagiaires.getSelectedNumber() == 0) {
            jop.showMessageDialog(null, "Vous n'avez pas sélectionné de stagiaire !", "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (dualMoniteurs.getSelectedNumber() == 0) {
            jop.showMessageDialog(null, "Vous n'avez pas sélectionné de moniteur !", "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (dualSuperviseur.getSelectedNumber() != 1) {
            jop.showMessageDialog(null, "Il faut sélectionner un superviseur !", "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!this.tfCommune.getText().matches(this.REGEX_COMMUNE)) {
            jop.showMessageDialog(null, "Code postal invalide !", "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void initFrameStagiaires() {
        // TODO : trouver tous les stagiaires qui ne participent pas au stage
//        dualStagiaires.addSourceElements(new String[]{"One", "Two", "Three"});
//        dualStagiaires.addSourceElements(new String[]{"Four", "Five", "Six"});
//        dualStagiaires.addSourceElements(new String[]{"Seven", "Eight", "Nine"});
//        dualStagiaires.addSourceElements(new String[]{"Ten", "Eleven", "Twelve"});
//        dualStagiaires.addSourceElements(new String[]{"Thirteen", "Fourteen",
//            "Fifteen"});
//        dualStagiaires.addSourceElements(new String[]{"Sixteen", "Seventeen",
//            "Eighteen"});
//        dualStagiaires.addSourceElements(new String[]{"Nineteen", "Twenty", "Thirty"});
        ArrayList<String> set = re.getMembresDisponibles(ftfHeureDebut.getText(), ftfHeureFin.getText(), ftfJour.getText());
        dualStagiaires.clearSourceListModel();
        for (int i = 0, size = set.size(); i < size; i += 2) {
            dualStagiaires.addSourceElements(new String[]{set.get(i) + " " + set.get(i + 1)});
        }
        this.frameStagiaires.getContentPane().add(dualStagiaires, BorderLayout.CENTER);
        this.frameStagiaires.setSize(600, 300);
        this.frameStagiaires.setLocationRelativeTo(null);
        this.frameStagiaires.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    private void initFrameMoniteurs() {
        ArrayList<String> set = new ArrayList<>();
        dualMoniteurs.setSourceChoicesTitle("Liste des moniteurs");
        dualMoniteurs.setDestinationChoicesTitle("Moniteurs sélectionnés");
        // TODO : parmi les moniteurs, verifier ceux qui peuvent encadrer ce stage
        
        

        set = re.getMoniteursDispos(ftfHeureDebut.getText(), ftfHeureFin.getText(), ftfJour.getText(), 
                comboSport.getSelectedItem().toString());

        if (set != null) {
            int setSize = set.size();
            for (int j = 0; j < setSize - 1; j += 3) {
                dualMoniteurs.addSourceElements(new String[]{set.get(j) + " " + set.get(j + 1) + " " + set.get(j+2)});
            }
        }

        this.frameMoniteurs.getContentPane().add(dualMoniteurs, BorderLayout.CENTER);
        this.frameMoniteurs.setSize(600, 300);
        this.frameMoniteurs.setLocationRelativeTo(null);
        this.frameMoniteurs.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.frameMoniteurs.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                initFrameSuperviseur();
            }
        });
    }

    private void initFrameSuperviseur() {
        Iterator itMoniteurs = dualMoniteurs.destinationIterator();
        while (itMoniteurs.hasNext()) {
            dualSuperviseur.addSourceElements(new Object[]{itMoniteurs.next()});
        }
        dualSuperviseur.setSourceChoicesTitle("Liste des moniteurs participants");
        dualSuperviseur.setDestinationChoicesTitle("Moniteur superviseur");
        this.frameSuperviseur.getContentPane().add(dualSuperviseur, BorderLayout.CENTER);
        this.frameSuperviseur.setSize(600, 300);
        this.frameSuperviseur.setLocationRelativeTo(null);
        this.frameSuperviseur.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    private void addLabelAndComponent(JLabel label, Component comp) {
        GridBagConstraints gridBagConstraintForLabel = new GridBagConstraints();
        gridBagConstraintForLabel.fill = GridBagConstraints.BOTH;
        gridBagConstraintForLabel.insets = new Insets(0, 0, 5, 5);
        gridBagConstraintForLabel.gridx = 0;
        gridBagConstraintForLabel.gridy = this.yPos;
        this.add(label, gridBagConstraintForLabel);

        GridBagConstraints gridBagConstraintForTextField = new GridBagConstraints();
        gridBagConstraintForTextField.fill = GridBagConstraints.BOTH;
        gridBagConstraintForTextField.insets = new Insets(0, 0, 5, 0);
        gridBagConstraintForTextField.gridx = 1;
        gridBagConstraintForTextField.gridy = this.yPos;
        this.add(comp, gridBagConstraintForTextField);

        this.yPos++;
    }
}
