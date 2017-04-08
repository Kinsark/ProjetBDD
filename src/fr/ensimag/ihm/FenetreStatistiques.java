package fr.ensimag.ihm;

import fr.ensimag.jdbc.RequetesGenerales;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class FenetreStatistiques extends JPanel implements ActionListener {
    
    private JLabel LabelNbStagiaires = new JLabel("Nombre de stagiaires : ");
    private JLabel LabelSqlStagiaires = new JLabel("Indéfini");
    private JLabel LabelRecettes = new JLabel("Total des recettes de l'association pour l'année en cours : ");
    private JLabel LabelSqlRecettes = new JLabel("Indéfini");
    private JLabel LabelNbMoyenInsc = new JLabel("Nombre moyen d'inscrits par stage : ");
    private JLabel LabelSqlNbMoyenInsc = new JLabel("Indéfini");
    private Box bv = Box.createVerticalBox();
    
    // boutons
    private JButton boutonTerrains = new JButton("Terrains les plus utilisés");
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
        
        
        // nombre moyen d'inscrits par stage
        Box boxIns= Box.createHorizontalBox();
        boxIns.add(this.LabelNbMoyenInsc);
        boxIns.add(this.LabelSqlNbMoyenInsc);
        this.bv.add(boxIns);
                
        // recettes de l'association
        Box boxRecettes = Box.createHorizontalBox();
        boxRecettes.add(this.LabelRecettes);
        boxRecettes.add(this.LabelSqlRecettes);
        this.bv.add(boxRecettes);

        
        this.boutonTerrains.addActionListener(this);
        this.boutonRatio.addActionListener(this);
        this.boutonActualiser.addActionListener(this);
        
        this.bv.add(boutonTerrains);
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
        
        if (arg0.getSource() == this.boutonRatio) {
            JFrame f = new JFrame("Ratio supervision/encadrement des moniteurs");
            
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

            int height = screenSize.height * 1/2;
            int width = screenSize.width * 1/8;
            f.setPreferredSize(new Dimension(width, height));
            
            
            RequetesGenerales reqG = new RequetesGenerales(conn);
            
            Map<String,String> supervisions = reqG.CountSupervision();
            Map<String,String> encadrements = reqG.CountEncadrement();
            int max = supervisions.size();
            if (encadrements.size() > max) max = encadrements.size();
            String[] data = new String[max];
            int i = 0;
            for (String id : supervisions.keySet())
            {
                if (encadrements.containsKey(id))
                {
                    System.out.println("coucou");
                    ArrayList<String> temp = reqG.getMoniteur(id);
                    System.out.println(temp.get(0) + temp.get(1));
                    data[i] = temp.get(0)+ " " + temp.get(1) + " : " + (double)Integer.parseInt(supervisions.get(id))/Integer.parseInt(encadrements.get(id));
                    
                }
                i++;
            }
            
            
            f.add(new JList(data));
            f.pack();
            f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        }
        
        if (arg0.getSource() == this.boutonActualiser) {
            RequetesGenerales reqG = new RequetesGenerales(conn);
            
            String recettes = reqG.recettes();
            LabelSqlRecettes.setText(recettes);
            
            String nbStagiaires = reqG.NbStagiaires();
            LabelSqlStagiaires.setText(nbStagiaires);
            
            String nbStages = reqG.NbStages();
            String nbInsc = reqG.NbInscriptions();
            double nbMoy=0;
            if (Integer.parseInt(nbStages) != 0) {
                nbMoy = (double) Integer.parseInt(nbInsc)/Integer.parseInt(nbStages);
            }
            LabelSqlNbMoyenInsc.setText(nbMoy+"");
        }
        
       
        this.setVisible(true);
    }
}
