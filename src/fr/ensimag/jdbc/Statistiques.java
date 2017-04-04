package fr.ensimag.jdbc;

public class Statistiques {
    // Nombre de stagiaires
    public String InscStage() {
        return "(SELECT COUNT(*) FROM Inscription)/(SELECT COUNT(*) FROM Stage)";
    }
    
    // Renvoie les terrains et leur nombre d'utilisations
    public String countTerrains() {
        return "SELECT NOMTERRAIN, COUNT(*)"
                + "FROM STAGE "
                + "GROUP BY NOMTERRAIN";
    }
    
    // Renvoie les stages et le nombre de participants pour chacun des stages
    public String countInscritsStage() {
        return "SELECT IDSTAGE, COUNT(*)"
                + "FROM PARTICIPE"
                + "GROUPE BY IDSTAGE";
    }

    // Renvoie les moniteurs et le nombre de supervisions par moniteur
    public String countSupervision() {
        return "SELECT IDMONITEUR, COUNT(*)"
                + "FROM AFFECTATION_SUPERVISION"
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
    
    // Renvoie les recettes de l'asso pour l'année en cours
    public String recettes() {
        return "SELECT SUM(PRIX)"
                + "FROM INSCRIPTION"
                + "WHERE DATE <= '20171231'"
                + "AND DATE >= '20170101'";
    }
    
    

}
