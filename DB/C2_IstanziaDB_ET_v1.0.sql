/*
Creazione del DB 'englishvalidation' se non esiste
*/
DROP DATABASE IF EXISTS englishvalidation;
CREATE DATABASE englishvalidation COLLATE 'utf8_general_ci';
USE englishvalidation;

/*
CREATE_TABLE 'englishvalidation' 
*/
CREATE TABLE USER (
EMAIL varchar(50) not null,
NAME varchar(50) not null,
SURNAME varchar(50) not null,
SEX char not null,
PASSWORD varchar(50) not null,
USER_TYPE tinyint(1) not null,
primary key (EMAIL)
);

CREATE TABLE SYSTEM_ATTRIBUTE (
SLUG varchar(50) not null, 
VALUE varchar(100) not null, 
FK_USER varchar(50) not null,
primary key (SLUG),
foreign key (FK_USER) references USER(EMAIL)
);


CREATE TABLE REQUEST (
ID_REQUEST int(20) not null AUTO_INCREMENT,
CERTIFICATE_SERIAL VARCHAR(50) not null,
LEVEL varchar(7) not null,
RELEASE_DATE date not null, 
EXPIRY_DATE date not null, 
YEAR year not null, 
REQUESTED_CFU tinyint(2) not null, 
SERIAL int(10) not null, 
VALIDATED_CFU tinyint(2) not null, 
FK_USER varchar(50) not null,
FK_CERTIFIER int(20) not null, 
FK_STATE int(20) not null, 
primary key(ID_REQUEST)
);

CREATE TABLE ATTACHED (
ID_ATTACHED int(20) not null AUTO_INCREMENT,
FILENAME varchar(50) not null,
FK_REQUEST int(20) not null,
FK_USER varchar(50) not null,
primary key(ID_ATTACHED)
);


CREATE TABLE ENTE (
ID_ENTE int(20) not null AUTO_INCREMENT,
EMAIL varchar(50) not null,
NAME varchar(100) not null,
SITE varchar(50) not null,
STATO TINYINT not null DEFAULT 0,
primary key (ID_ENTE)
);

CREATE TABLE STATE (
ID_STATE int(20) not null AUTO_INCREMENT, 
DESCRIPTION varchar(100) not null,
primary key (ID_STATE)
);

/*
ALTER_TABEL 'englishvalidation' 
*/
ALTER TABLE REQUEST 
ADD foreign key(FK_USER) references USER(EMAIL);

ALTER TABLE REQUEST 
ADD foreign key(FK_STATE) references STATE(ID_STATE);

ALTER TABLE REQUEST 
ADD foreign key(FK_CERTIFIER) references ENTE(ID_ENTE);

ALTER TABLE ATTACHED
ADD foreign key(FK_REQUEST) references REQUEST(ID_REQUEST);

ALTER TABLE ATTACHED
ADD foreign key(FK_USER) references USER(EMAIL);

/*
CREATE_TABLE 'Easy-Traineeship' 
*/
CREATE TABLE ENTECONVENZIONATO (
PARTITAIVA varchar(12) not null,
SEDE varchar(64) not null,
RAPPRESENTANTE varchar(64) not null,
TELEFONO varchar(10),
DIPENDENTI tinyint(4) not null,
DOTRIFERIMENTO varchar(64) not null,
REFERENTE varchar(64) not null,
DATANASCITA varchar(64),
DESCRIZIONEATTIVITA longtext,
EMAIL varchar(50),
primary key (PARTITAIVA),
foreign key (EMAIL) references USER(EMAIL)
on update cascade
on delete cascade
);


CREATE TABLE TIROCINANTE (
MATRICOLA bigint(10) not null,
DATANASCITA date not null,
LUOGONASCITA varchar(64) not null,
CITTADINANZA varchar(64) not null,
RESIDENZA varchar(64) not null,
CODICEFISCALE varchar(16) not null,
TELEFONO varchar(10),
EMAIL varchar(50),
primary key (MATRICOLA),
foreign key (EMAIL) references USER(EMAIL)
on update cascade
on delete cascade
);


CREATE TABLE TIROCINIO (
CODTIROCINIO INT auto_increment,
DATAINIZIOTIROCINO date not null,
CFUPREVISTI int(3) not null,
COMPETENZE longtext not null,
COMPETENZEACQUISIRE longtext not null,
ATTIVITAPREVISTE longtext not null,
SVOLGIMENTOTIROCINIO longtext not null,
STATOTIROCINIO varchar(64) not null,
PROGETTOFORMATIVO varchar(128) not null,
DESCRIZIONEENTE  varchar(256) not null,
MATRICOLA bigint(10) not null,
PARTITAIVA varchar(12),
primary key (CODTIROCINIO),
foreign key (MATRICOLA) references TIROCINANTE(MATRICOLA)
on update cascade
on delete cascade,
foreign key (PARTITAIVA) references ENTECONVENZIONATO(PARTITAIVA)
on update cascade
on delete cascade
);

/*
Insert Into 'englishvalidation'
*/
INSERT INTO user
VALUES('segreteria@unisa.it','Segreteria','Studenti','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b','1'); 

INSERT INTO user
VALUES('fferrucci@unisa.it','Filomena','Ferrucci','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b','2');

INSERT INTO STATE 
VALUES (1,'Parzialmente Completata');
INSERT INTO STATE 
VALUES (2,'In elaborazione dalla Segreteria');
INSERT INTO STATE 
VALUES (3,'In elaborazione dall&quot; Amministratore');
INSERT INTO STATE 
VALUES (4,'Accettata e In elaborazione dal Consiglio Didattico');
INSERT INTO STATE 
VALUES (5,'Rifiutata e In elaborazione dal Consiglio Didattico');
INSERT INTO STATE 
VALUES (6,'Conclusa e Accettata');
INSERT INTO STATE 
VALUES (7,'Conclusa e Rifiutata');

INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-partially-completed', '1','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-working-secretary', '2','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-working-admin', '3','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-working-educational-advice-1', '4','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-working-educational-advice-2', '5','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-accepted', '6','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-refused', '7','fferrucci@unisa.it');

INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-number-max-upload', '2','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-allowed-extension-upload', '.pdf','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-matriculation-year-range', '5','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-min-cfu', '1','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-max-cfu', '12','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-upload-path', 'C://apache-tomcat-9.0.16//webapps//easy-traineeship//uploads//','fferrucci@unisa.it');

INSERT INTO ENTE
VALUES ('1', '','Cambridge Assessment English','', 1);
INSERT INTO ENTE
VALUES ('2', '','City and Guilds (Pitman)','', 1);
INSERT INTO ENTE
VALUES ('3', '','Edexcel /Pearson Ltd','', 1);
INSERT INTO ENTE
VALUES ('4', '','Educational Testing Service (ETS)','', 1);
INSERT INTO ENTE
VALUES ('5', '','English Speaking Board (ESB)','', 1);
INSERT INTO ENTE
VALUES ('6', '','International English Language Testing System (IELTS)','', 1);
INSERT INTO ENTE
VALUES ('7', '','Pearson - LCCI','', 1);
INSERT INTO ENTE
VALUES ('8', '','Pearson - EDI','', 1);
INSERT INTO ENTE
VALUES ('9', '','Trinity College London (TCL)','', 1);
INSERT INTO ENTE
VALUES ('10', '','Department of English, Faculty of Arts - University of Malta','', 1);
INSERT INTO ENTE
VALUES ('11', '','NQAI - ACELS','', 1);
INSERT INTO ENTE
VALUES ('12', '','Ascentis','', 1);
INSERT INTO ENTE
VALUES ('13', '','AIM Awards','', 1);
INSERT INTO ENTE
VALUES ('14', '','Learning Resource Network (LRN)','', 1);
INSERT INTO ENTE
VALUES ('15', '','British Institutes','', 1);
INSERT INTO ENTE
VALUES ('16', '','Gatehouse Awards Ltd','', 1);
INSERT INTO ENTE
VALUES ('17', '','LanguageCert','', 1);
