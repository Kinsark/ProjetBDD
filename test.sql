CREATE TABLE Commune (
       IdCommune INTEGER PRIMARY KEY
);

INSERT INTO Commune VALUES(78160);
INSERT INTO Commune VALUES(38000);
INSERT INTO Commune VALUES(92240);
INSERT INTO Commune VALUES(38100);
INSERT INTO Commune VALUES(75014);

CREATE SEQUENCE IdPersonne;

CREATE TABLE Personne (
       IdPersonne INTEGER PRIMARY KEY,
       Nom VARCHAR2(15),
       Prenom VARCHAR2(15),
       Email VARCHAR2(15),
       Telephone VARCHAR2(10),
       Num INTEGER,
       Rue VARCHAR2(15),
       IdCommune INTEGER, 
       FOREIGN KEY (IdCommune) REFERENCES Commune(IdCommune)
);

INSERT INTO Personne VALUES(IdPersonne.nextval, 'Copin', 'Mathieu', 'mat@gmail.com', '0633556060', 1, 'Colonel Dumont', 38000);

