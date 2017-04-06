package fr.ensimag.jdbc;

public class Statistiques {
    // Nombre de stagiaires
    /*public String InscStage() {
        return "(SELECT COUNT(*) FROM Inscription)/(SELECT COUNT(*) FROM Stage)";
    }*/
    
    // Renvoie les terrains et leur nombre d'utilisations
    public String countTerrains() {
        return "SELECT NOMTERRAIN, COUNT(*)"
                + "FROM STAGE "
                + "GROUP BY NOMTERRAIN";
    }
    
    // Renvoie les stages et le nombre de participants pour chacun des stages
    public String countInscritsStage() {
        return "SELECT IDSTAGE, COUNT(*) "
                + "FROM PARTICIPE "
                + "GROUP BY IDSTAGE";
    }

    // Renvoie les moniteurs et le nombre de supervisions par moniteur
    public String countSupervision() {
        return "SELECT IDMONITEUR, COUNT(*) "
                + "FROM AFFECTATION_SUPERVISION "
                + "GROUP BY IDMONITEUR";
    }
    // On gèrera ensuite le cas où un moniteur n'apparaît que dans un des deux tuples 
    // son ratio sera 0 par exemple
    
    // Renvoie les moniteurs et le nombre d'encadrements par moniteur
    public String countEncadrement() {
        return "SELECT IDMONITEUR, COUNT(*)"
                + "FROM AFFECTATION_ENCADREMENT"
                + "GROUP BY IDMONITEUR";
    }
    
    // solution 2 : not sure
    public String statsMoniteurs() {
        return "SELECT A_E.IDMONITEUR, COUNT(A_E.IDMONITEUR), COUNT(A_S.IDMONITEUR)"
                + " FROM AFFECTATION_ENCADREMENT A_E, AFFECTATION_SUPERVISION A_S" 
                + " WHERE A_E.IDMEMBRE = A_S.IDMONITEUR"
                + " GROUP BY IDMONITEUR";
    }
    
    // Renvoie les recettes de l'asso pour l'année en cours
    public String recettes() {
        return "SELECT SUM(PRIX)"
                + "FROM INSCRIPTION"
                + "WHERE DATE <= '20171231'"
                + "AND DATE >= '20170101'";
    }
    
    

}
