package fr.ensimag.ihm;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public abstract class FenetreAjoutPersonne extends JPanel implements ActionListener {

    private static final String REGEX_NOM = "[a-zA-Z]+";
    private static final String REGEX_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String REGEX_TELEPHONE = "[0-9]{10}";
	private static final String REGEX_NUMERO_VOIE = "[1-9][0-9]*";
	private static final String REGEX_NOM_VOIE = "[a-zA-Z ]+";
	private static final String REGEX_COMMUNE = "[0-9]{5}";
	
    private JOptionPane jop = new JOptionPane();
	private int yPos = 0;
    
    protected JLabel labelNom = new JLabel("Nom");
    protected JTextField tfNom = new JTextField(20);
    protected JLabel labelPrenom = new JLabel("Prenom");
    protected JTextField tfPrenom = new JTextField(20);
    protected JLabel labelEmail = new JLabel("Email");
    protected JTextField tfEmail = new JTextField(20);
    protected JLabel labelTelephone = new JLabel("Téléphone");
    protected JTextField tfTelephone = new JTextField(10);
	protected JLabel labelNumeroVoie = new JLabel("Numéro dans la voie");
    protected JTextField tfNumeroVoie = new JTextField(10);
	protected JLabel labelNomVoie = new JLabel("Nom de la voie");
    protected JTextField tfNomVoie = new JTextField(10);
	protected JLabel labelCommune = new JLabel("Code postal");
    protected JTextField tfCommune = new JTextField(10);
    
    protected Connection conn;
    
    public FenetreAjoutPersonne(Connection conn) {
		Border border = this.getBorder();
		Border margin = new EmptyBorder(10, 10, 10, 10);
		this.setBorder(new CompoundBorder(border, margin));
                this.conn = conn;

		GridBagLayout panelGridBagLayout = new GridBagLayout();
		panelGridBagLayout.columnWidths = new int[]{86, 86, 0};
		panelGridBagLayout.rowHeights = new int[]{20, 20, 20, 20, 20, 0};
		panelGridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panelGridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		this.setLayout(panelGridBagLayout);
		this.addLabelAndComponent(labelNom, tfNom);
		this.addLabelAndComponent(labelPrenom, tfPrenom);
		this.addLabelAndComponent(labelEmail, tfEmail);
		this.addLabelAndComponent(labelTelephone, tfTelephone);
		this.addLabelAndComponent(labelNumeroVoie, tfNumeroVoie);
		this.addLabelAndComponent(labelNomVoie, tfNomVoie);
		this.addLabelAndComponent(labelCommune, tfCommune);
    }
    
    public void actionPerformed(ActionEvent arg0){};
    
    protected JOptionPane getJOP() {
        return this.jop;
    } 
    
    protected boolean verifierFormulaire() {
        if (!this.tfNom.getText().matches(this.REGEX_NOM)) {
            jop.showMessageDialog(null, "Un nom est uniquement composé de lettres !", "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!this.tfPrenom.getText().matches(this.REGEX_NOM)) {
            jop.showMessageDialog(null, "Un prénom est uniquement composé de lettres !", "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!this.tfEmail.getText().matches(this.REGEX_EMAIL)) {
            jop.showMessageDialog(null, "Adresse email invalide !", "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!this.tfTelephone.getText().matches(this.REGEX_TELEPHONE)) {
            jop.showMessageDialog(null, "Téléphone invalide (il faut 10 chiffres) !", "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        }
		if (!this.tfNumeroVoie.getText().matches(this.REGEX_NUMERO_VOIE)) {
            jop.showMessageDialog(null, "Numéro de la voie invalide !", "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        }
		if (!this.tfNomVoie.getText().matches(this.REGEX_NOM_VOIE)) {
            jop.showMessageDialog(null, "Nom de la voie invalide !", "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        }
		if (!this.tfCommune.getText().matches(this.REGEX_COMMUNE)) {
            jop.showMessageDialog(null, "Code postal invalide !", "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
	
	protected void addLabelAndComponent(JPanel pan, JLabel label, Component comp) {
		GridBagConstraints gridBagConstraintForLabel = new GridBagConstraints();
		gridBagConstraintForLabel.fill = GridBagConstraints.BOTH;
		gridBagConstraintForLabel.insets = new Insets(0, 0, 5, 5);
		gridBagConstraintForLabel.gridx = 0;
		gridBagConstraintForLabel.gridy = this.yPos;
		pan.add(label, gridBagConstraintForLabel);

		GridBagConstraints gridBagConstraintForTextField = new GridBagConstraints();
		gridBagConstraintForTextField.fill = GridBagConstraints.BOTH;
		gridBagConstraintForTextField.insets = new Insets(0, 0, 5, 0);
		gridBagConstraintForTextField.gridx = 1;
		gridBagConstraintForTextField.gridy = this.yPos;
		pan.add(comp, gridBagConstraintForTextField);
		
		this.yPos++;
	}
	
	protected void addLabelAndComponent(JLabel label, Component comp) {
		this.addLabelAndComponent(this, label, comp);
	}
}
