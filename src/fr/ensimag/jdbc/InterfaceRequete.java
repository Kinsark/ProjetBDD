package fr.ensimag.jdbc;

import java.sql.Connection;

public class InterfaceRequete {
    private int idCompt=0;
    
    
    public static String ajoutMoniteur(String nom, String prenom, String email, String telephone, String numero, String rue, String codePostal,String idMoniteur){
        return "INSERT INTO MONITEUR VALUES('" + nom + "', '" + prenom + "', '" + email
                + "', '" + telephone + "', " + numero + ", '" + rue + "'," + codePostal + "," + idMoniteur + ")";
    }
    
    public static String ajoutMembre(String nom, String prenom, String email, String telephone, String numero, String rue, String codePostal,String idMembre, String dateN){
        return "INSERT INTO MEMBRE VALUES('" + nom + "', '" + prenom + "', '" + email
                + "', '" + telephone + "', " + numero + ", '" + rue + "'," + codePostal + "," + idMembre + ",TO_DATE('" + dateN + "','dd/mm/yyyy'))";
    }
    
    public static String ajoutPersonne(String nom, String prenom, String email
            , String telephone, String num, String rue, String codePostal){
        return "INSERT INTO Personne(IDPERSONNE,NOM, PRENOM, EMAIL, TELEPHONE"
                + ", NUM, RUE, IDCOMMUNE) "
                + "VALUES(" +"IDPersonne.nextval" + ", '" + nom + "', '" + prenom + "', '" + email
                + "', '" + telephone + "', " + num + ", '" + rue + "'," + codePostal + ")";
    }
    
    public static String ajoutCommune(String commune){
        return "INSERT INTO Commune(IDcommune) VALUES('" + commune + "')"; 
    } 
    
    public static String testCommune(String commune){
        return "SELECT IDCOMMUNE FROM COMMUNE WHERE IDCOMMUNE = '" + commune + " '";
    }
    
    public static String printMoniteur()
    {
        return "SELECT NOM,PRENOM FROM MONITEUR";
    }
    
    public static String printMembre()
    {
        return "SELECT NOM,PRENOM,DATENAISSANCE FROM MEMBRE";
    }
    
    public static String testPersonne(String nom, String prenom, String email, String telephone){
        return " Select idPersonne from Personne Where nom = '" + nom + "' and prenom = '" + prenom + "' and email ='" + email + "' and telephone = '" + telephone + "'";
    }
    
    public static String testMoniteur(String id){
       return "SELECT NOM, PRENOM from Moniteur where idMoniteur = " + id ;
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
    
    /* 
            STATISTIQUES
    
    */
    
    public static String seekMoniteurs()
    {
        return "SELECT PRENOM,NOM FROM MONITEUR";
    }
    
    public String countTerrains() {
        return "SELECT NOMTERRAIN, COUNT(*)"
                + "FROM STAGE "
                + "GROUP BY NOMTERRAIN";
    }
    
    public String printTerrains()
    {
        return "SELECT NOMTERRAIN FROM TERRAIN";
    }
    
    public String printStages()
    {
        return "SELECT IDSTAGE FROM STAGE";
    }

    
     public String countSupervision() {
        return "SELECT IDMONITEUR, COUNT(*) "
                + "FROM STAGE "
                + "GROUP BY IDMONITEUR";
     }
     
     public String countEncadrement() {
        return "SELECT IDMONITEUR, COUNT(*)"
                + "FROM ENCADRE " 
                + "GROUP BY IDMONITEUR";
    }
     
     public String printPersonne() 
     {
         return "SELECT * FROM PERSONNE";
     }
     
     
     public String recettes() {
        return "SELECT SUM(PRIX) "
                + "FROM PARTICIPE "
                + "WHERE DATEINSCRIPTION <= TO_DATE('2017/12/31','yyyy/mm/dd') "
                + "AND DATEINSCRIPTION >= TO_DATE('2017/01/01','yyyy/mm/dd')";
    }
     
     public String nbStagiaires() {
        return "SELECT COUNT(DISTINCT IDMEMBRE) FROM PARTICIPE";
    }
     
     public String nbStages()
     {
         return "SELECT COUNT(*) FROM STAGE";
     }
     
     public String nbInscriptions()
     {
         return "SELECT COUNT(*) FROM PARTICIPE";
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

