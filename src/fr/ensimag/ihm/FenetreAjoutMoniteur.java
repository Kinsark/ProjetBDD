package fr.ensimag.ihm;

import fr.ensimag.jdbc.RequetesGenerales;
import java.awt.event.ActionEvent;
import java.sql.Connection;
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
                String nom = tfNom.getText();
                String prenom = tfPrenom.getText();
                String email = tfEmail.getText();
                String telephone = tfTelephone.getText();
                String numeroVoie = tfNumeroVoie.getText();
                String nomVoie = tfNomVoie.getText();
                String commune = tfCommune.getText();
                
                RequetesGenerales reqGe = new RequetesGenerales(conn);
                reqGe.AjoutMoniteur(nom,prenom,email,telephone,numeroVoie,nomVoie,commune);
                
                super.getJOP().showMessageDialog(null, "Moniteur ajouté !", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                
            }
        }
    }
}
