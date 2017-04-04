package fr.ensimag.jdbc;

public class InterfaceRequete {
    int compteur=0;
    
    public static String ajoutMoniteur(int id,String nom, String prenom, String email
            , String telephone, String num, String rue){
        return "INSERT INTO Personne(IDPERSONNE,NOM, PRENOM, EMAIL, TELEPHONE"
                + ", NUM, RUE, IDCOMMUNE) "
                + "VALUES('" + id + "', '" + nom + "', '" + prenom + "', '" + email
                + "', '" + telephone + "', '" + num + "', '" + rue + "', '78160')";
    }
    
    public static String ajoutCommune(int commune){
        return "INSERT INTO Commune(IDcommune) VALUES('" + commune + "'"; 
    } 
    
    public String testCommune(int commune){
        return "SELECT IDCOMMUNE FROM COMMUNE WHERE IDCOMMUNE = '" + commune + " '";}
    /* Ajout moniteur */
    public static void main(String[] args){
        InterfaceRequete ir = new InterfaceRequete();
        Transaction Prem = new Transaction();
        Requete req = new Requete();
        boolean com = req.ExecuteRequete(ir.testCommune(78160));
        if (com == false){
            System.out.println("Nous n'avons pas la commune");
            Prem.ExecuteTransaction(ir.testCommune(78160));
            }
        
        else  {
            System.out.println("Nous avons deja la commune");}
        //Transaction Prem = new Transaction(ir.ajoutMoniteur(ir.compteur,"Jean", "Eude", "michel", "03254315", "18", "rue des jambons"));
        }
    }

