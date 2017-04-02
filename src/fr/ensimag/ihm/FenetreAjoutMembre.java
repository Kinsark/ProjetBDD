package fr.ensimag.ihm;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.Box;

public class FenetreAjoutMembre extends FenetreAjoutPersonne {
    
    private JLabel labelDateNaissance= new JLabel("Date de naissance");
    private JTextField tfDateNaissance = new JTextField(10);
    
    public FenetreAjoutMembre() {
        super();
        Box bv = super.getBoxVerticale();
        // date de naissance
        Box boxDateNaissance = Box.createHorizontalBox();
        boxDateNaissance.add(this.labelDateNaissance);
        boxDateNaissance.add(this.tfDateNaissance);
        bv.add(boxDateNaissance);
    }
}
