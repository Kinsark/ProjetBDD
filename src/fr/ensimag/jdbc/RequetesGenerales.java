/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.jdbc;

import java.sql.Connection;

/**
 *
 * @author simsim
 */
public class RequetesGenerales {
        private InterfaceRequete ir;
        private Action act;
        private Connection conn;
        
        public RequetesGenerales(Connection conn){
            this.ir = new InterfaceRequete();
            this.act = new Action(conn);
        }
    public void AjoutMoniteur(String nom, String prenom, String email, String telephone, String numero, String rue, String codePostal){
        String idS = act.requeteId(ir.testPersonne(nom,prenom,email,telephone));
                if (idS == null){
                
                    if (act.requete(ir.testCommune(codePostal)) == false){
                        act.transaction(ir.ajoutCommune(codePostal));
                     }
                    
                    act.transaction(ir.ajoutPersonne(nom,prenom,email,telephone,numero,rue,codePostal));
                    idS = act.requeteId(ir.testPersonne(nom,prenom,email,telephone));
                    act.transaction(ir.ajoutMoniteur(idS));
        }
                 else {
                    if (act.requete(ir.testMoniteur(idS)) == false){
                        act.transaction(ir.ajoutMoniteur(idS));
                    }
            
            
        }
       }

    public void AjoutMembre(String nom, String prenom, String email, String telephone, String numero, String rue, String codePostal, String dateN){
        String idS = act.requeteId(ir.testPersonne(nom,prenom,email,telephone));
                if (idS == null){
                
                    if (act.requete(ir.testCommune(codePostal)) == false){
                        act.transaction(ir.ajoutCommune(codePostal));
                     }
                    
                    act.transaction(ir.ajoutPersonne(nom,prenom,email,telephone,numero,rue, codePostal));
                    idS = act.requeteId(ir.testPersonne(nom,prenom,email,telephone));
                    act.transaction(ir.ajoutMembre(idS, dateN));
        }
                 else {
                    if (act.requete(ir.testMembre(idS)) == false){
                        act.transaction(ir.ajoutMembre(idS,dateN));
                    }
            
            
        }
       }
    
     public void AjoutStage(String heureDebut, String heureFin, String jour, String sport, String terrain){
            if (act.requete(ir.testStage(heureDebut, heureFin, terrain, jour)) == false){
                
            }
     }



}
