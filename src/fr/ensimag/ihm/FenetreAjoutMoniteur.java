package fr.ensimag.ihm;

import fr.ensimag.jdbc.RequetesGenerales;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class FenetreAjoutMoniteur extends FenetreAjoutPersonne {

    private RequetesGenerales re;
    
    private DualListBox dualHabilitation = new DualListBox();
    private DualListBox dualExpertise = new DualListBox();
    
    private JFrame frameHabilitation = new JFrame("Sélectionner les stagiaires");
    private JFrame frameExpertise = new JFrame("Sélectionner son expertise");
    
    private JButton boutonHabilitation = new JButton("Sélectionner les habilitations");
    private JButton boutonExpertise = new JButton("Sélectionner son expertise");
    private JButton boutonValider = new JButton("Valider");

    public FenetreAjoutMoniteur(Connection conn) {
        super(conn);
        re = new RequetesGenerales(conn);
        initFrameHabilitation();
        super.addLabelAndComponent(new JLabel("Habilitation"), this.boutonHabilitation);
        super.addLabelAndComponent(new JLabel("Expertise"), this.boutonExpertise);
        super.addLabelAndComponent(new JLabel("Valider"), this.boutonValider);
        this.boutonHabilitation.addActionListener(this);
        this.boutonExpertise.addActionListener(this);
        this.boutonValider.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == this.boutonHabilitation) {
            this.frameHabilitation.setVisible(true);
        }
        if (arg0.getSource() == this.boutonExpertise) {
            if (this.dualHabilitation.getSelectedNumber() == 0) {
                super.getJOP().showMessageDialog(null, "Veuillez sélectionner les habilitations du moniteurs.", "Erreur", JOptionPane.ERROR_MESSAGE);
            } else {
                this.frameExpertise.setVisible(true);
            }
        }
        if (arg0.getSource() == this.boutonValider) {
            if (this.verifierFormulaire()) {
                String nom = tfNom.getText();
                String prenom = tfPrenom.getText();
                String email = tfEmail.getText();
                String telephone = tfTelephone.getText();
                String numeroVoie = tfNumeroVoie.getText();
                String nomVoie = tfNomVoie.getText();
                String commune = tfCommune.getText();
                Iterator itHabilitation = dualHabilitation.destinationIterator();
                Iterator itExpertise = dualExpertise.destinationIterator();

                RequetesGenerales reqGe = new RequetesGenerales(conn);
                boolean notInBDD = reqGe.AjoutMoniteur(nom, prenom, email, telephone, numeroVoie, nomVoie, commune);
                if (notInBDD) {
                    String idMoniteur = reqGe.getMoniteurId(nom, prenom, email, telephone, numeroVoie, nomVoie, commune);
                    while (itHabilitation.hasNext()) {
                        reqGe.ajoutHabilitation(itHabilitation.next().toString(), idMoniteur);
                    }
                    while (itExpertise.hasNext()) {
                        reqGe.ajoutExpertise(itExpertise.next().toString(), idMoniteur);
                    }
                    super.getJOP().showMessageDialog(null, "Moniteur ajouté !", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    super.getJOP().showMessageDialog(null, "Moniteur déjà inscrit !", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        }
    }
    
    private void initFrameHabilitation() {
        ArrayList<String> set = re.getAllSports();
        dualHabilitation.setSourceChoicesTitle("Liste des sports");
        dualHabilitation.setDestinationChoicesTitle("Habilitations");
        dualHabilitation.clearSourceListModel();
        for (int i = 0, size = set.size(); i < size; i += 1) {
            dualHabilitation.addSourceElements(new String[]{set.get(i)});
        }
        this.frameHabilitation.getContentPane().add(dualHabilitation, BorderLayout.CENTER);
        this.frameHabilitation.setSize(600, 300);
        this.frameHabilitation.setLocationRelativeTo(null);
        this.frameHabilitation.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.frameHabilitation.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                initFrameExpertise();
            }
        });
    }
    
    private void initFrameExpertise() {
        dualExpertise.setSourceChoicesTitle("Liste des habilitation");
        dualExpertise.setDestinationChoicesTitle("Expertise");
        dualExpertise.clearSourceListModel();
        dualExpertise.clearDestinationListModel();
        Iterator itHabilitation = dualHabilitation.destinationIterator();
        while (itHabilitation.hasNext()) {
            dualExpertise.addSourceElements(new Object[]{itHabilitation.next()});
        }
        this.frameExpertise.getContentPane().add(dualExpertise, BorderLayout.CENTER);
        this.frameExpertise.setSize(600, 300);
        this.frameExpertise.setLocationRelativeTo(null);
        this.frameExpertise.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
    
    @Override
    protected boolean verifierFormulaire() {
        if (super.verifierFormulaire()) {
            if (!(this.dualHabilitation.getSelectedNumber() > 0)) {
                super.getJOP().showMessageDialog(null, "Veuillez sélectionner les habilitations du moniteurs.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (this.dualExpertise.getSelectedNumber() != 1) {
                super.getJOP().showMessageDialog(null, "Un moniteur ne peut être expert que dans un sport !", "Erreur", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else {
            return false;
        }
        return true;
    }
}
