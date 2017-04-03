package fr.ensimag.ihm;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

public class FenetreAjoutStage extends JPanel implements ActionListener {

	private static final DateFormat formatJour = new SimpleDateFormat("dd/MM/yyyy");
	private static final DateFormat formatHeure = new SimpleDateFormat("HH:mm");

	private JOptionPane jop = new JOptionPane();
	private Box bv = Box.createVerticalBox();

	private JLabel labelHeureDebut = new JLabel("Heure de début (hh:mm)");
	private JFormattedTextField ftfHeureDebut = new JFormattedTextField(this.formatHeure);

	private JLabel labelHeureFin = new JLabel("Heure de fin (hh:mm)");
	private JFormattedTextField ftfHeureFin = new JFormattedTextField(this.formatHeure);

	private JLabel labelJour = new JLabel("Jour (jj/mm/aaaa)");
	private JFormattedTextField ftfJour = new JFormattedTextField(this.formatJour);

	private JLabel labelTerrain = new JLabel("Terrain");
	private JComboBox comboTerrain;

	private JLabel labelSport = new JLabel("Sport");
	private JComboBox comboSport;

	private DualListBox dualStagiaires = new DualListBox();
	private DualListBox dualMoniteurs = new DualListBox();

	private JButton boutonStagiaire = new JButton("Sélectionner les stagiaires");
	private JButton boutonMoniteur = new JButton("Sélectionner les moniteurs");
	private JButton boutonEncadrant = new JButton("Sélectionner l'encadrant");
	private JButton boutonValider = new JButton("Valider");

	public FenetreAjoutStage() {
		this.add(bv);
		// heure de debut
		Box boxHeureDebut = Box.createHorizontalBox();
		boxHeureDebut.add(this.labelHeureDebut);
		boxHeureDebut.add(this.ftfHeureDebut);
		try {
			MaskFormatter dateMask = new MaskFormatter("##:##");
			dateMask.install(ftfHeureDebut);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.bv.add(boxHeureDebut);

		// heure de fin
		Box boxHeureFin = Box.createHorizontalBox();
		boxHeureFin.add(this.labelHeureFin);
		boxHeureFin.add(this.ftfHeureFin);
		try {
			MaskFormatter dateMask = new MaskFormatter("##:##");
			dateMask.install(ftfHeureFin);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.bv.add(boxHeureFin);

		// jour
		Box boxJour = Box.createHorizontalBox();
		boxJour.add(this.labelJour);
		boxJour.add(this.ftfJour);
		try {
			MaskFormatter dateMask = new MaskFormatter("##/##/####");
			dateMask.install(ftfJour);
		} catch (ParseException e) {
			e.printStackTrace();
		}
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
			// trouver tous les stagiaires qui ne participent pas au stage
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
			if (this.verifierFormulaire()) {
				// mettre dans la BDD
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
		int countStagiaires = 0;
		int countMoniteurs = 0;
		if ((heureDebutH > heureFinH) || (heureDebutH == heureFinH) && (heureDebutM > heureFinM)) {
			jop.showMessageDialog(null, "L'heure de début et l'heure de fin sont incompatibles !", "Erreur", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		// faire le test avec les horaires du stade
		// utiliser this.comboSport.getSelectedItem().toString()
		Iterator itStagiaires = dualStagiaires.destinationIterator();
		while (itStagiaires.hasNext()) {
			countStagiaires++;
			itStagiaires.next();
		}
		if (countStagiaires == 0) {
			jop.showMessageDialog(null, "Vous n'avez pas sélectionné de stagiaire !", "Erreur", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		Iterator itMoniteurs = dualMoniteurs.destinationIterator();
		while (itMoniteurs.hasNext()) {
			countMoniteurs++;
			itMoniteurs.next();
		}
		if (countMoniteurs == 0) {
			jop.showMessageDialog(null, "Vous n'avez pas sélectionné de moniteur !", "Erreur", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
}
