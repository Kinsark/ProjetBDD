package fr.ensimag.ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class FenetreAjoutPersonne extends JPanel {

    private Box bv = Box.createVerticalBox();
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
    
    public Box getBoxVerticale() {
        return this.bv;
    }
}
