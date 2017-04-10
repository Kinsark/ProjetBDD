CREATE TABLE Commune (
       IdCommune INTEGER PRIMARY KEY
);

CREATE SEQUENCE IdPersonne;
CREATE SEQUENCE IdStage;

CREATE TABLE Personne (
       IdPersonne INTEGER PRIMARY KEY,
       Nom VARCHAR2(20),
       Prenom VARCHAR2(20),
       Email VARCHAR2(40),
       Telephone VARCHAR2(10),
       Num INTEGER,
       Rue VARCHAR2(100),
       IdCommune INTEGER,
       FOREIGN KEY (IdCommune) REFERENCES Commune(IdCommune)
);

CREATE TABLE TypeTerrain (
       TypeTerrain VARCHAR2(50) PRIMARY KEY
);

CREATE TABLE Terrain (
       NomTerrain VARCHAR2(40),
       IdCommune INTEGER,
       FOREIGN KEY (IdCommune) REFERENCES Commune(IdCommune),
       HeureOuverture DATE,
       HeureFermeture DATE,
       Capacite INTEGER CONSTRAINT ch_capacite CHECK(Capacite > 0),
       TypeTerrain VARCHAR2(50),
       FOREIGN KEY (TypeTerrain) REFERENCES TypeTerrain(TypeTerrain),
       CONSTRAINT pk PRIMARY KEY (NomTerrain,IdCommune),
       CONSTRAINT ch_heure CHECK(HeureOuverture < HeureFermeture)
);

CREATE TABLE Membre ( 
       IdMembre INTEGER PRIMARY KEY REFERENCES Personne(IdPersonne),
       DateNaissance DATE 
);

CREATE TABLE Moniteur (Nom VARCHAR2(20),
       IdMoniteur INTEGER PRIMARY KEY REFERENCES Personne(IdPersonne) 
);


CREATE TABLE Sport (
       NomSport VARCHAR2(30) PRIMARY KEY,
       TarifBase NUMBER(6,2) CONSTRAINT ch_tarif_base CHECK(TarifBase > 0)
);



CREATE TABLE Stage (
       IdStage INTEGER PRIMARY KEY,
       HeureDebut DATE,
       HeureFin DATE,
       CONSTRAINT ch_heure_stage CHECK(HeureDebut < HeureFin),
       NomSport VARCHAR2(30),
 FOREIGN KEY (NomSport)  REFERENCES Sport(NomSport),
       NomTerrain VARCHAR2(50),
       IdCommune INTEGER,
       FOREIGN KEY (NomTerrain, IdCommune) REFERENCES Terrain(NomTerrain, IdCommune),
       IdMoniteur INTEGER,
       FOREIGN KEY (IdMoniteur) REFERENCES Moniteur(IdMoniteur)       
);


CREATE TABLE Participe (
       DateInscription DATE,
       Prix NUMBER(6,2) CONSTRAINT ch_prix check(prix>0),
       IdMembre INTEGER,
       IdStage INTEGER,
       FOREIGN KEY (IdMembre) REFERENCES Membre(IdMembre),
       FOREIGN KEY (IdStage) REFERENCES Stage(IdStage),
       PRIMARY KEY (IdMembre, IdStage)
);


CREATE TABLE Encadre (
       IdMoniteur INTEGER,
       FOREIGN KEY (IdMoniteur) REFERENCES Moniteur(IdMoniteur),
       IdStage INTEGER, 
       FOREIGN KEY (IdStage) REFERENCES Stage(IdStage),
       PRIMARY KEY (IdMoniteur, IdStage)
);



CREATE TABLE Expert (
    NomSport VARCHAR2(30),
    IdMoniteur INTEGER,
    FOREIGN KEY (IdMoniteur) REFERENCES Moniteur(IdMoniteur),    
    FOREIGN KEY (NomSport) REFERENCES Sport(NomSport),
    PRIMARY KEY (NomSport, IdMoniteur)
);

CREATE TABLE Habilite (
    NomSport VARCHAR2(30),
    IdMoniteur INTEGER,
    FOREIGN KEY (IdMoniteur) REFERENCES Moniteur(IdMoniteur),    
    FOREIGN KEY (NomSport) REFERENCES Sport(NomSport),
    PRIMARY KEY (NomSport, IdMoniteur)
);


CREATE TABLE Possibilite_Pratiquer (
    TypeTerrain VARCHAR2(50),
    NomSport VARCHAR2(30),
    FOREIGN KEY (NomSport) REFERENCES Sport(NomSport),
    FOREIGN KEY (TypeTerrain) REFERENCES TypeTerrain(TypeTerrain),
    PRIMARY KEY (TypeTerrain, NomSport)
);


