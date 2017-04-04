package fr.ensimag.ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public abstract class FenetreAjoutPersonne extends JPanel implements ActionListener {

    private static final String REGEX_LETTRES = "[a-zA-Z]+";
    private static final String REGEX_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String REGEX_TELEPHONE = "[0-9]{10}";

    
    private Box bv = Box.createVerticalBox();
    private JOptionPane jop = new JOptionPane();
    
    private JLabel labelNom = new JLabel("Nom");
    private JTextField tfNom = new JTextField(20);
    private JLabel labelPrenom = new JLabel("Prenom");
    private JTextField tfPrenom = new JTextField(20);
    private JLabel labelEmail = new JLabel("Email");
    private JTextField tfEmail = new JTextField(20);
    private JLabel labelTelephone = new JLabel("Téléphone");
    private JTextField tfTelephone = new JTextField(10);
    
    
    public FenetreAjoutPersonne() {
        this.add(bv);
        // nom
        Box boxNom = Box.createHorizontalBox();
        boxNom.add(this.labelNom);
        boxNom.add(this.tfNom);
        this.bv.add(boxNom);
        // prenom
        Box boxPrenom = Box.createHorizontalBox();
        boxPrenom.add(this.labelPrenom);
        boxPrenom.add(this.tfPrenom);
        this.bv.add(boxPrenom);
        // email
        Box boxEmail = Box.createHorizontalBox();
        boxEmail.add(this.labelEmail);
        boxEmail.add(this.tfEmail);
        this.bv.add(boxEmail);
        // telephone
        Box boxTelephone = Box.createHorizontalBox();
        boxTelephone.add(this.labelTelephone);
        boxTelephone.add(this.tfTelephone);
        this.bv.add(boxTelephone);
    }
    
    public void actionPerformed(ActionEvent arg0){};
    
    protected Box getBoxVerticale() {
        return this.bv;
    }
    
    protected JOptionPane getJOP() {
        return this.jop;
    } 
    
    protected boolean verifierFormulaire() {
        if (!this.tfNom.getText().matches(this.REGEX_LETTRES)) {
            jop.showMessageDialog(null, "Un nom est uniquement composé de lettres !", "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!this.tfPrenom.getText().matches(this.REGEX_LETTRES)) {
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
        return true;
    }
}
