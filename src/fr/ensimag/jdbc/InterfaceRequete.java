package fr.ensimag.jdbc;

public class InterfaceRequete {
    private int idCompt=0;
    
    
    public static String ajoutMoniteur(int idMoniteur){
        return "INSERT INTO MONITEUR(IdMoniteur) VALUES('" + idMoniteur + "')";
    }
    
    public static String ajoutPersonne(int id,String nom, String prenom, String email
            , String telephone, String num, String rue){
        return "INSERT INTO Personne(IDPERSONNE,NOM, PRENOM, EMAIL, TELEPHONE"
                + ", NUM, RUE, IDCOMMUNE) "
                + "VALUES('" + id + "', '" + nom + "', '" + prenom + "', '" + email
                + "', '" + telephone + "', '" + num + "', '" + rue + "', '78160')";
    }
    
    public static String ajoutCommune(int commune){
        return "INSERT INTO Commune(IDcommune) VALUES('" + commune + "'"; 
    } 
    
    public static String testCommune(int commune){
        return "SELECT IDCOMMUNE FROM COMMUNE WHERE IDCOMMUNE = '" + commune + " '";
    }
    
    public static String testPersonne(String nom, String prenom, String email, String telephone){
        return " Select nom, prenom from Personne Where nom = '" + nom + " ' and prenom = '" + prenom + "'";//+ "' and email ='" + email + "' and telephone = '" + telephone + "'";
    }
    
    /* Ajout moniteur */
    /* Ordre : Verif Personne, puis verif Moniteur, puis verif commune puis ajout*/
    public static void main(String[] args){
        InterfaceRequete ir = new InterfaceRequete();
        Transaction Prem = new Transaction();
        Requete req = new Requete();
        if (req.ExecuteRequete(ir.testPersonne("Dupont","Josephine","michel","03254315")) == false){
             System.out.println("Ajout de personnes");
              if (req.ExecuteRequete(ir.testCommune(78160)) == false){
                    System.out.println("Nous n'avons pas la commune");
                    Prem.ExecuteTransaction(ir.ajoutCommune(78160));
                   // Transaction Prem = new Transaction(ir.ajoutMoniteur(ir.compteur,"Jean", "Eude", "michel", "03254315", "18", "rue des jambons"));
              }
        
              else  {
                    System.out.println("Nous avons deja la commune");}
        
                    
               Prem.ExecuteTransaction(ajoutPersonne(1070,"Dupont","Josephine","michel","03254315","3","rue des coquelicots"));
               Prem.ExecuteTransaction(ajoutMoniteur(1070));
        }
        else {
            System.out.println("Personne deja dns la base mais Moniteur?");
        }
       }
}

