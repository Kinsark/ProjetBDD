package fr.ensimag.ihm;

import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class FenetreAjoutMoniteur extends FenetreAjoutPersonne {
    
    private JButton boutonValider = new JButton("Valider");
    
    public FenetreAjoutMoniteur() {
        super();
        Box bv = super.getBoxVerticale();
        bv.add(this.boutonValider);
        this.boutonValider.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == this.boutonValider) {
            if (super.verifierFormulaire()) {
                // TODO : mettre dans la BDD
                super.getJOP().showMessageDialog(null, "OK", "Moniteur ajouté !", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
