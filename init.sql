CREATE SEQUENCE IdStage;
CREATE SEQUENCE IdPersonne;

-- rajouter les ON DELETE ..... pour les mises a jour
CREATE TABLE Sport (
       NomSport VARCHAR2(15) PRIMARY KEY,
       TarifBase NUMBER(4,2) CONSTRAINT ch_tarif_base CHECK(TarifBase > 0)
);

CREATE TABLE TypeTerrain (
       TypeTerrain VARCHAR2(15) PRIMARY KEY
);

CREATE TABLE Commune (
       IdCommune INTEGER PRIMARY KEY
);
CREATE TABLE Terrain (
       NomTerrain VARCHAR2(15),
       IdCommune INTEGER FOREIGN KEY REFERENCES Commune(IdCommune),
       HeureOuverture DATE,
       HeureFermeture DATE,
       Capacite INTEGER CONSTRAINT ch_capacite CHECK(Capacite > 0),
       TypeTerrain VARCHAR2(15) FOREIGN KEY REFERENCES TypeTerrain(TypeTerrain),
       CONSTRAINT pk PRIMARY KEY (NomTerrain,IdCommune),
       CONSTRAINT ch_heure CHECK(HeureOuverture < HeureFermeture)
);

CREATE TABLE Personne (
       IdPersonne INTEGER PRIMARY KEY,
       Nom VARCHAR2(15),
       Prenom VARCHAR2(15),
       Email VARCHAR2(15),
       Telephone INTEGER,
       Num INTEGER,
       Rue VARCHAR2(15),
       IdCommune INTEGER FOREIGN KEY REFERENCES Commune(IdCommune)
);

CREATE TABLE Membre (
       IdMembre INTEGER PRIMARY KEY REFERENCES Personne(IdPersonne),
       DateNaissance DATE
);

CREATE TABLE Inscription (
       IdInscription INTEGER PRIMARY KEY,
       DateInscription DATE,
       Prix NUMBER(4,2)
);

CREATE TABLE Moniteur (
       IdMoniteur INTEGER PRIMARY KEY REFERENCES Personne(IdPersonne)
);

CREATE TABLE Stage (
       IdStage INTEGER PRIMARY KEY,
       HeureDebut DATE,
       HeureFin DATE,
       Jour DATE, -- pas utile car on a le jour dans les heures
       CONSTRAINT ch_heure CHECK(HeureDebut < HeureFin),
       NomSport VARCHAR2(15) FOREIGN KEY REFERENCES Sport(NomSport),
       NomTerrain VARCHAR2(15) FOREIGN KEY REFERENCES Terrain(NomTerrain),
       IdMoniteur INTEGER FOREIGN KEY REFERENCES Moniteur(IdMoniteur)       
);

CREATE TABLE Affectation_Encadrement (
       IdMoniteur INTEGER FOREIGN KEY REFERENCES Moniteur(IdMoniteur),
       IdStage INTEGER FOREIGN KEY REFERENCES Stage(IdStage)
);

CREATE TABLE Affectation_Supervision (
       IdMoniteur INTEGER FOREIGN KEY REFERENCES Moniteur(IdMoniteur),
       IdStage INTEGER FOREIGN KEY REFERENCES Stage(IdStage)
 );

CREATE TABLE Affectation_Expertise (
       IdMoniteur INTEGER FOREIGN KEY REFERENCES Moniteur(IdMoniteur),	
       NomSport VARCHAR2(15) FOREIGN KEY REFERENCES Sport(NomSport)
);

CREATE TABLE Affectation_Habilitation (
       IdMoniteur INTEGER FOREIGN KEY REFERENCES Moniteur(IdMoniteur),
       NomSport VARCHAR2(15) FOREIGN KEY REFERENCES Sport(NomSport)
);

CREATE TABLE Possibilite_Pratiquer (
       NomSport VARCHAR2(15) FOREIGN KEY REFERENCES Sport(NomSport)
       TypeTerrain VARCHAR2(15) FOREIGN KEY REFERENCES TypeTerrain(TypeTerrain)
);

CREATE TABLE Participe (
       IdMembre INTEGER FOREIGN KEY REFERENCES Membre(IdMembre),
       IdInscription INTEGER FOREIGN KEY REFERENCES Inscription(IdInscription),
       DateInscription DATE,
       Prix NUMBER(4,2),
       IdStage INTEGER FOREIGN KEY REFERENCES Stage(IdStage)
);
