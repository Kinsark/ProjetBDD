package fr.ensimag.jdbc;

import java.sql.Connection;

public class InterfaceRequete {
    private int idCompt=0;
    
    
    public static String ajoutMoniteur(String idMoniteur){
        return "INSERT INTO MONITEUR(IDMONITEUR) VALUES('" + idMoniteur + "')";
    }
    
    public static String ajoutMembre(String idMembre, String dateN){
        return "INSERT INTO MEMBRE(IDMEMBRE, DATENAISSANCE)  VALUES('" + idMembre 
                + "',TO_DATE('" + dateN + "','dd/mm/yyyy'))";
    }
    
    public static String ajoutPersonne(String nom, String prenom, String email
            , String telephone, String num, String rue, String codePostal){
        return "INSERT INTO Personne(IDPERSONNE,NOM, PRENOM, EMAIL, TELEPHONE"
                + ", NUM, RUE, IDCOMMUNE) "
                + "VALUES(" +"IDPersonne.nextval" + ", '" + nom + "', '" + prenom + "', '" + email
                + "', '" + telephone + "', " + num + ", '" + rue + "'," + codePostal + ")";
    }
    
    public String getCommuneFromTerrain(String nomTerrain)
    {
        return "SELECT IDCOMMUNE FROM TERRAIN WHERE NOMTERRAIN = '" + nomTerrain + "'"; 
    }
    
    public static String ajoutStage(String heureDebut, String heureFin, String nomSport, 
            String nomTerrain, String idCommune, String idMoniteur, String jour){
        String debut = "TO_DATE('"+jour+":"+heureDebut+"', 'dd/mm/yyyy:hh24:mi')";
        String fin = "TO_DATE('"+jour+":"+heureFin+"', 'dd/mm/yyyy:hh24:mi')";
        return "INSERT INTO STAGE "
                + "VALUES (" + "IDSTAGE.nextval" + ", " + debut + ", " + fin + ", '"
                + nomSport + "', '" + nomTerrain + "', " + idCommune + ", " + idMoniteur + ")";
    }
    
    public static String ajoutCommune(String commune){
        return "INSERT INTO Commune(IDcommune) VALUES('" + commune + "')"; 
    } 
    
    public static String testCommune(String commune){
        return "SELECT IDCOMMUNE FROM COMMUNE WHERE IDCOMMUNE = '" + commune + " '";
    }
    
    public static String printMoniteur()
    {
        return "SELECT NOM,PRENOM FROM MONITEUR, PERSONNE WHERE IDMONITEUR = IDPERSONNE";
    }
    
    public static String printMembre()
    {
        return "SELECT NOM,PRENOM,DATENAISSANCE FROM MEMBRE, PERSONNE WHERE IDMEMBRE = IDPERSONNE";
    }
    
    public static String testPersonne(String nom, String prenom, String email, String telephone){
        return " Select idPersonne from Personne Where nom = '" + nom + "' and prenom = '" + prenom + "' and email ='" + email + "' and telephone = '" + telephone + "'";
    }
    
    public static String testMoniteur(String id){
       return "SELECT NOM, PRENOM from PERSONNE,MONITEUR where IDPERSONNE = IDMONITEUR AND idPERSONNE = " + id ;
    }
    
    public static String testStage(String hD, String hF, String jour, String terrain, String idCommune){
        return "Select idStage from Stage where HeureDebut = '" + hD 
                + "' and heureFin = '" + hF 
                + "' and Jour = '" + jour 
                + "' and NomTerrain = '" + terrain + "' AND IDCOMMUNE = "+idCommune+"" ;
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
        return "SELECT PRENOM,NOM FROM MONITEUR, PERSONNE WHERE IDPERSONNE = IDMONITEUR";
    }
    
    public String countTerrains() {
        return "SELECT NOMTERRAIN, COUNT(*) AS THECOUNT "
                + "FROM STAGE "
                + "GROUP BY NOMTERRAIN "
                + "ORDER BY THECOUNT DESC";
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
     
     public String printTerrainsParSport(String sport) {
        return "SELECT DISTINCT t.NOMTERRAIN "
               + "FROM TERRAIN t, POSSIBILITE_PRATIQUER p, SPORT s "
                + "WHERE t.TYPETERRAIN = p.TYPETERRAIN "
                + "AND p.NOMSPORT = " + sport + " AND p.NOMSPORT = s.NOMSPORT";
    }
         
    public String printSports() {
        return "SELECT NOMSPORT FROM SPORT";
    }
    
    public String printMembresDisponibles(String heureDebut, String heureFin, String jour) {
        String debut = "TO_DATE('" + jour + " " + heureDebut + "', 'dd/mm/yyyy hh24:mi')";
        String fin = "TO_DATE('" + jour + " " + heureFin + "', 'dd/mm/yyyy hh24:mi')";
        return "(SELECT DISTINCT p.NOM, p.PRENOM, IDPERSONNE FROM MEMBRE m, PERSONNE p WHERE p.IDPERSONNE = m.IDMEMBRE) " 
                + "MINUS "
                + "(SELECT DISTINCT pers.NOM, pers.PRENOM, IDPERSONNE FROM MEMBRE m, STAGE s, PARTICIPE p, PERSONNE pers "
                + "WHERE m.IDMEMBRE = p.IDMEMBRE AND p.IDSTAGE = s.IDSTAGE AND pers.IDPERSONNE = m.IDMEMBRE "
                + "AND (" 
                + "(" + debut + " < " + "s.HEUREDEBUT" +  " AND " + "s.HEUREDEBUT" + " < " + fin + ")"
                + " OR "
                + "(" + debut + " < " + "s.HEUREFIN" +  " AND " + "s.HEUREFIN" + " < " + fin + ")"
                + " OR "
                + "(" + "s.HEUREDEBUT" + " <= " + debut +  " AND " + fin + " <= " + "s.HEUREFIN" + ")"
                + ")"
                + ")";
    }
     
    public String printMoniteursDispos(String heureDebut, String heureFin, String jour, String sport)
    {
        String debut = jour + ":" + heureDebut;
        String fin = jour + ":" + heureFin;
        return "(SELECT DISTINCT p.NOM, p.PRENOM, IDPERSONNE FROM PERSONNE p, MONITEUR m, HABILITE h "
                + "WHERE m.IDMONITEUR = h.IDMONITEUR AND h.NOMSPORT = '" + sport + "' "
                + "AND p.IDPERSONNE = m.IDMONITEUR ) " 
                + "MINUS "
                + "(SELECT DISTINCT p.NOM, p.PRENOM, IDPERSONNE FROM PERSONNE p, MONITEUR m, STAGE s, HABILITE h "
                + "WHERE m.IDMONITEUR = s.IDMONITEUR AND p.IDPERSONNE = m.IDMONITEUR AND s.IDMONITEUR = h.IDMONITEUR "
                + "AND NOT " 
                + "(TO_CHAR(s.HEUREDEBUT,'dd/mm/yyyy:hh24:mi') >= '" + fin + 
                "'  OR TO_CHAR(s.HEUREFIN,'dd/mm/yyyy:hh24:mi') <=  '" + debut + "' ))";
    }
    
    public String moniteurEncadre(String idStage, String idMoniteur)
    {
        return "INSERT INTO ENCADRE VALUES(" + idStage + ", " + idMoniteur + ")";
    }

    
    public String ajouterHabilitation(String nomSport, String idMoniteur) {
        return "INSERT INTO HABILITE VALUES('" + nomSport + "', " + idMoniteur + ")";
    }
    
    public String ajouterExpertise(String nomSport, String idMoniteur) {
        return "INSERT INTO EXPERT VALUES('" + nomSport + "', " + idMoniteur + ")";
    }
    
    public String getMoniteurId(String nom, String prenom, String email, String telephone, String numero, String rue, String codePostal) {
        return "SELECT IDMONITEUR FROM MONITEUR m, PERSONNE p WHERE p.NOM = '" + nom + "' AND p.PRENOM = '" + prenom + "' AND p.EMAIL = '" + email + "' AND " +
                "p.TELEPHONE = '" + telephone + "' AND p.NUM = " + numero + " AND p.RUE = '" + rue + "' AND p.IDCOMMUNE = " + codePostal + "AND m.IDMONITEUR = p.IDPERSONNE";
    }

    
    public String ajoutStagiaire(String prix, String idMembre, String idStage)
    {
        return "INSERT INTO PARTICIPE VALUES(GETDATE(), " + prix + ", " + idMembre + ", " + idStage + " )";
    }
    
    public String isLocal(String idMembre)
    {
        return "SELECT IDCOMMUNE FROM PERSONNE,MEMBRE WHERE IDMEMBRE = IDPERSONNE AND IDMEMBRE = " + idMembre;
    }    
    
    public String getPrixSport(String sport)
    {
        return "SELECT TARIFBASE FROM SPORT WHERE NOMSPORT = '" + sport ;
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

