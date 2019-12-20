USE englishvalidation;


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
CFUPREVISTI tinyint(3) not null,
COMPETENZE longtext not null,
COMPETENZEACQUISIRE longtext not null,
ATTIVITAPREVISTE longtext not null,
SVOLGIMENTOTIROCINIO longtext not null,
STATOTIROCINIO varchar(64) not null,
PROGETTOFORMATIVO varchar(128) not null,
DESCRIZIONEENTE  varchar(256) not null,
MATRICOLA bigint(10) not null,
PARTITAIVA varchar(12) not null,
primary key (CODTIROCINIO),
foreign key (MATRICOLA) references TIROCINANTE(MATRICOLA)
on update cascade
on delete cascade,
foreign key (PARTITAIVA) references ENTECONVENZIONATO(PARTITAIVA)
on update cascade
on delete cascade
);