package fr.ensimag.jdbc;

public class InterfaceRequete {
    
    public String ajoutMoniteur(String nom, String prenom, String email
            , String telephone, String num, String rue){
        return "INSERT INTO PERSONNE(IDPERSONNE,NOM, PRENOM, EMAIL, TELEPHONE"
                + ", NUM, RUE, IDCOMMUNE) "
                + "VALUES(36, " + nom + ", " + prenom + ", " + email
                + ", " + telephone + ", " + num + ", " + rue + ", 36);";
    }
    
    public static void main(String[] args){
        InterfaceRequete ir = new InterfaceRequete();
        Requete Prem = new Requete(ir.ajoutMoniteur("poirot", "hercule", "laokiofk", "65465315", "6", "glagla"));
    }
}
