/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.jdbc;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author simsim
 */
public class RequetesGenerales {

    private InterfaceRequete ir;
    private Action act;
    private Connection conn;

    public RequetesGenerales(Connection conn) {
        this.ir = new InterfaceRequete();
        this.act = new Action(conn);
    }

    public boolean AjoutMoniteur(String nom, String prenom, String email, String telephone, String numero, String rue, String codePostal) {
        String idS = act.requeteId(ir.testPersonne(nom, prenom, email, telephone));
        boolean bool = (idS == null);
        if (idS == null) {

            if (act.requete(ir.testCommune(codePostal)) == false) {
                act.transaction(ir.ajoutCommune(codePostal));
            }

            act.transaction(ir.ajoutPersonne(nom, prenom, email, telephone, numero, rue, codePostal));
            idS = act.requeteId(ir.testPersonne(nom, prenom, email, telephone));
            act.transaction(ir.ajoutMoniteur(nom, prenom, email, telephone, numero, rue, codePostal, idS));
        } else {
            if (act.requete(ir.testMoniteur(idS)) == false) {
                act.transaction(ir.ajoutMoniteur(nom, prenom, email, telephone, numero, rue, codePostal, idS));
            }

        }
        // à decommenter si vous voulez verifier l'etat de la table
        act.requete(ir.printMoniteur());
        return bool;
    }

    public boolean AjoutMembre(String nom, String prenom, String email, String telephone, String numero, String rue, String codePostal, String dateN) {
        String idS = act.requeteId(ir.testPersonne(nom, prenom, email, telephone));
        boolean bool = (idS == null);
        if (idS == null) {

            if (act.requete(ir.testCommune(codePostal)) == false) {
                act.transaction(ir.ajoutCommune(codePostal));
            }

            act.transaction(ir.ajoutPersonne(nom, prenom, email, telephone, numero, rue, codePostal));
            idS = act.requeteId(ir.testPersonne(nom, prenom, email, telephone));
            act.transaction(ir.ajoutMembre(nom, prenom, email, telephone, numero, rue, codePostal, idS, dateN));
        } else {
            if (act.requete(ir.testMembre(idS)) == false) {
                act.transaction(ir.ajoutMembre(nom, prenom, email, telephone, numero, rue, codePostal, idS, dateN));
            }

        }
        // à decommenter si vous voulez verifier l'etat de la table
        act.requete(ir.printMembre());
        return bool;
    }

    public void AjoutStage(String heureDebut, String heureFin, String jour, String sport, String terrain) {
        if (act.requete(ir.testStage(heureDebut, heureFin, terrain, jour)) == false) {

        }
<<<<<<< HEAD
                // à decommenter si vous voulez verifier l'etat de la table
                act.requete(ir.printMembre());
                return bool;
       }
    
     public void AjoutStage(String heureDebut, String heureFin, String jour, String sport, String terrain){
            if (act.requete(ir.testStage(heureDebut, heureFin, terrain, jour)) == false){
                
            }
     }

     public ArrayList<String> SeekMoniteurs()
     {  
         act.requete(ir.printMoniteur());
         return act.requeteSet(ir.seekMoniteurs(),2);
     }

     public ArrayList<String> CountTerrains()
     {
         act.requete(ir.printTerrains());
         return act.requeteSet(ir.countTerrains(),2);
     }
     
     public Map<String,String> CountSupervision()
     {
         return act.requeteMap(ir.countSupervision());
     }
     
     public Map<String,String> CountEncadrement()
     {
         return act.requeteMap(ir.countEncadrement());
     }
     
     public String recettes()
     {
         return act.requeteId(ir.recettes());
     }

     public ArrayList<String> getMoniteur(String id)
     {
         return act.requeteSet(ir.testMoniteur(id),2);
     }
     
     public String NbStagiaires()
     {
         return act.requeteId(ir.nbStagiaires());
     }
     
     public String NbStages()
     {
         return act.requeteId(ir.nbStages());
     }
     
     public String NbInscriptions()
     {
         return act.requeteId(ir.nbInscriptions());
     }
     
     public ArrayList<String> getTerrainsParSport(String sport) {
         return act.requeteSet(ir.printTerrainsParSport(sport),1);
     }
     
     public ArrayList<String> getAllSports() {
         return act.requeteSet(ir.printSports(), 1);
     }
     
     public ArrayList<String> getMembresDisponibles(String heureDebut, String heureFin, String jour) {
         return act.requeteSet(ir.printMembresDisponibles(heureDebut, heureFin, jour), 2);
     }
     
     public ArrayList<String> getMoniteursDispos(String heureDebut, String heureFin, String jour, String sport) {
         return act.requeteSet(ir.printMoniteursDispos(heureDebut, heureFin, jour, sport), 2);
     }
     
     
=======
    }

    public ArrayList<String> SeekMoniteurs() {
        act.requete(ir.printMoniteur());
        return act.requeteSet(ir.seekMoniteurs(), 2);
    }

    public ArrayList<String> CountTerrains() {
        act.requete(ir.printTerrains());
        return act.requeteSet(ir.countTerrains(), 2);
    }

    public Map<String, String> CountSupervision() {
        return act.requeteMap(ir.countSupervision());
    }

    public Map<String, String> CountEncadrement() {
        return act.requeteMap(ir.countEncadrement());
    }

    public String recettes() {
        return act.requeteId(ir.recettes());
    }

    public ArrayList<String> getMoniteur(String id) {
        return act.requeteSet(ir.testMoniteur(id), 2);
    }

    public String NbStagiaires() {
        return act.requeteId(ir.nbStagiaires());
    }

    public String NbStages() {
        return act.requeteId(ir.nbStages());
    }

    public String NbInscriptions() {
        return act.requeteId(ir.nbInscriptions());
    }

    public ArrayList<String> getTerrainsParSport(String sport) {
        return act.requeteSet(ir.printTerrainsParSport(sport), 1);
    }

    public ArrayList<String> getAllSports() {
        return act.requeteSet(ir.printSports(), 1);
    }

    public ArrayList<String> getMembresDisponibles(String heureDebut, String heureFin, String jour) {
        return act.requeteSet(ir.printMembresDisponibles(heureDebut, heureFin, jour), 2);
    }

>>>>>>> 353bd9879e676a6f6f85ce11d287355130d5eeae
}
