package fr.ensimag.ihm;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class FenetreAjoutMoniteur extends FenetreAjoutPersonne {
    
    private JButton boutonValider = new JButton("Valider");
    public FenetreAjoutMoniteur(Connection conn) {
        super(conn);
		super.addLabelAndComponent(new JLabel("Valider"), this.boutonValider);
        this.boutonValider.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == this.boutonValider) {
            if (super.verifierFormulaire()) {
                // TODO : mettre dans la BDD
                super.getJOP().showMessageDialog(null, "Moniteur ajouté !", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
