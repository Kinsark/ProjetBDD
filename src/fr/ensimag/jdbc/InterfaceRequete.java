package fr.ensimag.jdbc;

import java.sql.Connection;

public class InterfaceRequete {
    private int idCompt=0;
    
    
    public static String ajoutMoniteur(String idMoniteur){
        return "INSERT INTO MONITEUR(IdMoniteur) VALUES('" + idMoniteur + "')";
    }
    
    public static String ajoutMembre(String idMembre, String dateN){
        return "INSERT INTO Membre(IdMembre, dateNaissance) VALUES('" + idMembre + "','" + dateN + "')";
    }
    
    public static String ajoutPersonne(String nom, String prenom, String email
            , String telephone, String num, String rue, String codePostal){
        return "INSERT INTO Personne(IDPERSONNE,NOM, PRENOM, EMAIL, TELEPHONE"
                + ", NUM, RUE, IDCOMMUNE) "
                + "VALUES('" + "'IDPersonne.nextval'" + "', '" + nom + "', '" + prenom + "', '" + email
                + "', '" + telephone + "', '" + num + "', '" + rue + "'," + codePostal + "')";
    }
    
    public static String ajoutCommune(String commune){
        return "INSERT INTO Commune(IDcommune) VALUES('" + commune + "'"; 
    } 
    
    public static String testCommune(String commune){
        return "SELECT IDCOMMUNE FROM COMMUNE WHERE IDCOMMUNE = '" + commune + " '";
    }
    
    public static String testPersonne(String nom, String prenom, String email, String telephone){
        return " Select idPersonne from Personne Where nom = '" + nom + "' and prenom = '" + prenom + "' and email ='" + email + "' and telephone = '" + telephone + "'";
    }
    
    public static String testMoniteur(String id){
        return "SELECT idMoniteur from Moniteur where idMoniteur = '" + id + "'" ;
    }
    
    public static String testStage(String hD, String hF, String jour, String terrain){
        return "Select idStage from Stage where HeureDebut = '" + hD 
                + "' and heureFin = '" + hF 
                + "' and Jour = '" + jour 
                + "' and NomTerrain = '" + terrain + "'";
    }
    
    public static String testOccupationTerrain(String jour, String hD, String hF){
        return "Select idStage from Stage where (HEUREDEBUT <= TO_DATE('"
                + jour + ","
                + hD + "':', 'dd/mm/yyyy:hh:mi') AND HEUREFIN > TO_DATE('"
                + jour + ","
                + hF + "':', 'dd/mm/yyyy:hh:mi')";
    }
    
    public static String testMembre(String id){
        return "SELECT idMembre from Membre where idMembre = '" + id + "'" ;
    }
    /* Ajout moniteur */
    /* Ordre : Verif Personne, puis verif Moniteur, puis verif commune puis ajout*/
    public static void main(String[] args){
        //Action act = new Action();
        //InterfaceRequete ir = new InterfaceRequete();
       // act.transaction(ajoutPersonne("111","A","B","C","D","3","rue des coquelicots"));
       // act.transaction(ajoutMembre("111","3 juin 1995"));
        //act.requete(testMembre("111"));
       }
}

