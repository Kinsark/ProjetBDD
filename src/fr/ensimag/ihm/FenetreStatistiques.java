package fr.ensimag.ihm;

import fr.ensimag.jdbc.RequetesGenerales;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class FenetreStatistiques extends JPanel implements ActionListener {
    
    private JLabel LabelNbStagiaires = new JLabel("Nombre de stagiaires : ");
    private JLabel LabelSqlStagiaires = new JLabel("1");
    private JLabel LabelRecettes = new JLabel("Total des recettes de l'association pour l'année en cours : ");
    private JLabel LabelSqlRecettes = new JLabel("1 €");
    private Box bv = Box.createVerticalBox();
    
    // boutons
    private JButton boutonTerrains = new JButton("Terrains les plus utilisés");
    private JButton boutonInscrits = new JButton("Nombre moyen d'inscrits par stage");
    private JButton boutonRatio= new JButton("Ratio supervision/encadrement des moniteurs");
    private JButton boutonActualiser = new JButton("Actualiser");
    
    private Connection conn;
    
    public FenetreStatistiques(Connection conn) {
        this.add(bv);
        
        this.conn = conn;
        // nombre de stagiaires
        /*
            Requête SQL pour attraper le nombre de stagiaires
        */
        Box boxNbStagiaires = Box.createHorizontalBox();
        boxNbStagiaires.add(this.LabelNbStagiaires);
        boxNbStagiaires.add(this.LabelSqlStagiaires);
        this.bv.add(boxNbStagiaires);
        
                
        // recettes de l'association
        Box boxRecettes = Box.createHorizontalBox();
        boxRecettes.add(this.LabelRecettes);
        boxRecettes.add(this.LabelSqlRecettes);
        this.bv.add(boxRecettes);

        
        this.boutonTerrains.addActionListener(this);
        this.boutonInscrits.addActionListener(this);
        this.boutonRatio.addActionListener(this);
        
        this.bv.add(boutonTerrains);
        this.bv.add(boutonInscrits);
        this.bv.add(boutonRatio);
        // bouton "Actualiser", qui appelle un fonction qui recalcule les variables importantes
        this.bv.add(boutonActualiser);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == this.boutonTerrains) {
            JFrame f = new JFrame("Terrains les plus utilisés");

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

            int height = screenSize.height * 1/2;
            int width = screenSize.width * 1/8;
            f.setPreferredSize(new Dimension(width, height));
            
            

            RequetesGenerales reqG = new RequetesGenerales(conn);
            ArrayList<String> set = reqG.CountTerrains();
            
            String[] data;
            if (set != null) {
                data = new String[set.size()/2];
                for (int i = 0; i < set.size()-1 ; i+=2){
                data[i] =  set.get(i) + " : " + set.get(i+1);
                 }
            }
            else
            {
                data = new String[2];
                data[0] = "Terrain 1";
            }
            
            f.add(new JList(data));
            f.pack();
            f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        }
        if (arg0.getSource() == this.boutonInscrits) {
            JFrame f = new JFrame("Nombre moyen d'inscrits par stage");
            
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

            int height = screenSize.height * 1/2;
            int width = screenSize.width * 1/8;
            f.setPreferredSize(new Dimension(width, height));

            String stage = new String("Stage");
            
            String[] data = new String[15];
            for (int i = 0; i < 10 ; i++){
                data[i] = stage + i + " : nbInscrit";
            }
            
            f.add(new JList(data));
            f.pack();
            f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        }
        if (arg0.getSource() == this.boutonRatio) {
            JFrame f = new JFrame("Ratio supervision/encadrement des moniteurs");
            
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

            int height = screenSize.height * 1/2;
            int width = screenSize.width * 1/8;
            f.setPreferredSize(new Dimension(width, height));
            
            String moniteur = new String("Moniteur");
            
            String[] data = new String[15];
            for (int i = 0; i < 10 ; i++){
                data[i] = moniteur + i + " : ratio";
            }
          
            f.add(new JList(data));
            f.pack();
            f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        }
        
       
        this.setVisible(true);
    }
}
