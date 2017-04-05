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
    public void AjoutMoniteur(String id,String nom, String prenom, String email, String telephone, String numero, String rue, String codePostal){
        String idS = act.requeteId(ir.testPersonne(nom,prenom,email,telephone));
                if (idS == null){
                
                    if (act.requete(ir.testCommune(codePostal)) == false){
                        act.transaction(ir.ajoutCommune(codePostal));
                     }
                    
                    act.transaction(ir.ajoutPersonne(codePostal,nom,prenom,email,telephone,numero,rue));
                    act.transaction(ir.ajoutMoniteur(id));
        }
                 else {
                    if (act.requete(ir.testMoniteur(idS)) == false){
                        act.transaction(ir.ajoutMoniteur(idS));
                    }
            
            
        }
       }

    public void AjoutMembre(String id,String nom, String prenom, String email, String telephone, String numero, String rue, String codePostal, String dateN){
        String idS = act.requeteId(ir.testPersonne(nom,prenom,email,telephone));
                if (idS == null){
                
                    if (act.requete(ir.testCommune(codePostal)) == false){
                        act.transaction(ir.ajoutCommune(codePostal));
                     }
                    
                    act.transaction(ir.ajoutPersonne(codePostal,nom,prenom,email,telephone,numero,rue));
                    act.transaction(ir.ajoutMembre(id, dateN));
        }
                 else {
                    if (act.requete(ir.testMembre(idS)) == false){
                        act.transaction(ir.ajoutMembre(idS,dateN));
                    }
            
            
        }
       }



}
