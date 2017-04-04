package fr.ensimag.jdbc;

public class Statistiques {
    // Nombre moyen d'inscrits par stage
    public String InscStage() {
        return "(SELECT COUNT(*) FROM Inscription)/(SELECT COUNT(*) FROM Stage)";
    }
    // Nombre d'inscrit au stage
}
