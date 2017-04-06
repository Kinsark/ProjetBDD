package fr.ensimag.ihm;

import fr.ensimag.jdbc.RequetesGenerales;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

public class FenetreAjoutMembre extends FenetreAjoutPersonne {
    
    private static final DateFormat FORMAT_JOUR = new SimpleDateFormat("dd/MM/yyyy");

    private JLabel labelDateNaissance = new JLabel("Date de naissance");
    private JFormattedTextField ftfDateNaissance = new JFormattedTextField(this.FORMAT_JOUR);
    private JButton boutonValider = new JButton("Valider");

    public FenetreAjoutMembre(Connection conn) {
        super(conn);
        // date de naissance
        super.addLabelAndComponent(labelDateNaissance, ftfDateNaissance);
        try {
            MaskFormatter dateMask = new MaskFormatter("##/##/####");
            dateMask.install(ftfDateNaissance);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.addLabelAndComponent(new JLabel("Valider"), this.boutonValider);
        this.boutonValider.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == this.boutonValider) {
            if (this.verifierFormulaire()) {
                String nom = tfNom.getText();
                String prenom = tfPrenom.getText();
                String email = tfEmail.getText();
                String telephone = tfTelephone.getText();
                String numeroVoie = tfNumeroVoie.getText();
                String nomVoie = tfNomVoie.getText();
                String commune = tfCommune.getText();
                String dateN = ftfDateNaissance.getText();
                
                RequetesGenerales reqGe = new RequetesGenerales(conn);
                boolean notInBDD = reqGe.AjoutMembre(nom,prenom,email,telephone,numeroVoie,nomVoie,commune,dateN);
                if (notInBDD)
                    super.getJOP().showMessageDialog(null, "Membre ajouté !", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                else
                    super.getJOP().showMessageDialog(null, "Membre déjà inscrit !", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    @Override
    protected boolean verifierFormulaire() {
        if (super.verifierFormulaire()) {
            String[] date = this.ftfDateNaissance.getText().split("/");
            int jour = 0, mois = 0, annee = 0;
            try {
                jour = Integer.parseInt(date[0]);
                mois = Integer.parseInt(date[1]);
                annee = Integer.parseInt(date[2]);
            } catch (NumberFormatException e) {
                super.getJOP().showMessageDialog(null, "La date de naissance est mal saisie !", "Erreur", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }
}
