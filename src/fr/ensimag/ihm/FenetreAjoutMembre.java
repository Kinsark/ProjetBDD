package fr.ensimag.ihm;

import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

public class FenetreAjoutMembre extends FenetreAjoutPersonne {

    private static final DateFormat FORMAT_JOUR = new SimpleDateFormat("dd/MM/yyyy");

    private JLabel labelDateNaissance = new JLabel("Date de naissance");
    private JFormattedTextField ftfDateNaissance = new JFormattedTextField(this.FORMAT_JOUR);
    private JButton boutonValider = new JButton("Valider");

    public FenetreAjoutMembre() {
        super();
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
                // TODO : mettre dans la BDD
                super.getJOP().showMessageDialog(null, "Membre ajouté !", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
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
