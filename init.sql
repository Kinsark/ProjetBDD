-- Creation de la BDD

CREATE SEQUENCE IdStage;
CREATE SEQUENCE IdPersonne;

CREATE TABLE Stage {
       IdStage INTEGER PRIMARY KEY,
       HeureDebut DATE,
       HeureFin DATE,
       Jour DATE
}

CREATE TABLE Sport {
       NomSport VARCHAR2(15) PRIMARY KEY,
       TarifBase NUMBER(4,2)
}

CREATE TABLE Terrain {
       NomTerrain VARCHAR2(15) PRIMARY KEY,
       HeureOuverture DATE,
       HeureFermeture DATE,
       Capacite INTEGER
}

CREATE TABLE TypeTerrain {
       Type VARCHAR2(15) PRIMARY KEY
}

CREATE TABLE Personne {
       IdPersonne INTEGER PRIMARY KEY,
       Nom VARCHAR2(15),
       Prenom VARCHAR2(15),
       Email VARCHAR2(15),
       Telephone INTEGER
}

CREATE TABLE Membre {
       IdMembre INTEGER PRIMARY KEY,
       DateNaissance DATE
}

CREATE TABLE Inscription {
       IdInscription INTEGER PRIMARY KEY,
       DateInscription DATE,
       Prix NUMBER(4,2)
}

CREATE TABLE Moniteur {
       IdMoniteur INTEGER PRIMARY KEY
}

CREATE TABLE Adresse {
        Num INTEGER PRIMARY KEY,
	Rue VARCHAR2(15) PRIMARY KEY
}

CREATE TABLE Commune {
       IdCommune INTEGER PRIMARY KEY
}

CREATE TABLE Affectation_Encadrement {
       IdMoniteur INTEGER PRIMARY KEY,
       IdStage INTEGER PRIMARY KEY
}

CREATE TABLE Affectation_Supervision {
       IdMoniteur INTEGER PRIMARY KEY,
       IdStage INTEGER PRIMARY KEY
 }

CREATE TABLE Affectation_Expertise {
       IdMoniteur INTEGER PRIMARY KEY,	
       NomSport VARCHAR2(15) PRIMARY KEY
}

CREATE TABLE Affectation_Habilitation {
       IdMoniteur INTEGER PRIMARY KEY,
       NomSport VARCHAR2(15) PRIMARY KEY
}

CREATE TABLE Affectation_Terrain {
       IdStage INTEGER PRIMARY KEY,
       NomTerrain VARCHAR2(15) PRIMARY KEY,
       NomCommune VARCHAR2(15) PRIMARY KEY
}

CREATE TABLE Possibilite_Terrain {
       NomSport VARCHAR2(15) PRIMARY KEY,
       TypeTerrain VARCHAR2(15) PRIMARY KEY
}

CREATE TABLE Affectation_Stage {
       IdMembre INTEGER PRIMARY KEY,
       IdInscription INTEGER PRIMARY KEY,
       DateInscription DATE,
       Prix NUMBER(4,2),
       IdStage INTEGER PRIMARY KEY
}
