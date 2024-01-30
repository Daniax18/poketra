
create sequence matiere_seq increment by 1 start with 1;
create sequence look_seq increment by 1 start with 1;
create sequence look_min_seq increment by 1 start with 1;
create sequence type_sac_seq increment by 1 start with 1;
create sequence taille_sac_seq increment by 1 start with 1;


create sequence poketra_seq increment by 1 start with 1;
create sequence poketra_taille_seq increment by 1 start with 1;
create sequence poketra_matiere_seq increment by 1 start with 1;
create sequence frn_seq increment by 1 start with 1;
create sequence achat_seq increment by 1 start with 1;


create sequence achat_mp_seq increment by 1 start with 1;
create sequence mouvement_mp_seq increment by 1 start with 1;
create sequence fabrication_seq increment by 1 start with 1;

create sequence pv_seq increment by 1 start with 1;


-- -------------------------------------------------------

create table matiere_premiere(
	id_matiere_premiere varchar(100) primary key not null,
	nom_matiere varchar(100)
);

-- ---------------------------------------------------------

create table look_sac(
	id_look_sac varchar(100) primary key not null,
	nom_look varchar(100),
        valeur numeric
);

insert into look_sac values('LO2', 'Standart', 1);
-- ----------------------------------------------------------
create table look_sac_min_heure(
	id_look_sac_min varchar(100) primary key not null,
        valeur numeric,
        date_attribution date
);

-- -------------------------------------------------------------

create table look_matiere(
	id_look_matiere varchar(100) primary key not null,
	id_matiere_premiere varchar(100),
	id_look_sac varchar(100),
	foreign key(id_matiere_premiere) references matiere_premiere(id_matiere_premiere),
	foreign key(id_look_sac) references look_sac(id_look_sac)	
);

-- ------------------------------------------------------------
-- Sac a main - Sac a dos
create table type_sac(
	id_type_sac varchar(100) primary key not null,
	nom varchar(100),
        valeur numeric
);

-- ------------------------------------------------------------
-- XL - Medium - xs
create table taille_sac(
    id_taille_sac varchar(100) primary key not null,
    nom varchar(100),
    valeur numeric,
);

-- ------------------------------------------------------------

create table poketra(
    id_poketra varchar(100) primary key not null,
    nom_poketra varchar(100),
    id_type_sac varchar(100),
    id_look_sac varchar(100),
    id_taille_sac varchar(100),
    foreign key(id_look_sac) references look_sac(id_look_sac),
    foreign key(id_type_sac) references type_sac(id_type_sac)
);

-- -------------------------------------------------------

create table poketra_matiere(
    id_poketra_matiere varchar(100) primary key not null,
    id_poketra varchar(100),
    id_matiere_premiere varchar(100),
    qty numeric,
    foreign key(id_poketra) references poketra (id_poketra),
    foreign key(id_matiere_premiere) references matiere_premiere(id_matiere_premiere)
);

-- ----------------------------------------------------

create table fournisseur(
    id_fournisseur varchar(100) primary key not null,
    nom_fournisseur varchar(100)
);

insert into fournisseur values('FRN1', 'JUMBO');

-- ----------------------------------------------------

create table achat_matiere(
    id_achat_matiere varchar(100) primary key not null,
    id_matiere_premiere varchar(100),
    id_fournisseur varchar(100),
    price_unit numeric,
    date_achat date,
    foreign key(id_fournisseur) references fournisseur (id_fournisseur),
    foreign key(id_matiere_premiere) references matiere_premiere(id_matiere_premiere)
);

insert into achat_matiere values('A1', 'MAT_2', 'FRN1', 20, '2024-01-05');

-- -------------------------------------------------

create table mouvement(
    id_mouvement varchar(100) primary key not null,
    id_matiere_premiere varchar(100),
    date_mouvement date,
    qty_mouvement numeric,
    status numeric,
    foreign key(id_matiere_premiere) references matiere_premiere(id_matiere_premiere)
);

-- --------------------------------------------------

create table achat_mp(
    id_achat_mp varchar(100) primary key not null,
    id_matiere_premiere varchar(100),
    id_fournisseur varchar(100),
    date_achat_mp date,
    qty numeric,
    foreign key(id_fournisseur) references fournisseur (id_fournisseur),
    foreign key(id_matiere_premiere) references matiere_premiere(id_matiere_premiere)
);

-- ------------------------------------------------------

create table fabrication(
    id_fabrication varchar(100) primary key not null,
    id_poketra varchar(100),
    date_fabrication date,
    qty_fabrication numeric,
    foreign key(id_poketra) references poketra(id_poketra)
);


-- ---------------------------------------------------
-- delete from mouvement;
-- delete from achat_mp;

-- ---------------------------------------------------


create table prix_vente(
	id_pv varchar(100) primary key not null,
        id_poketra varchar(100),
        price numeric,
        date_prix date,
        foreign key(id_poketra) references poketra(id_poketra)
);


insert into prix_vente values('PRIX1', 'POK_4', 10000, (SELECT NOW()));
insert into prix_vente values('PRIX2', 'POK_5', 15000, (SELECT NOW()));
insert into prix_vente values('PRIX3', 'POK_6', 12000, (SELECT NOW()));

 -- ---------------------------------------------------------------------------
CREATE SEQUENCE Employer_seq INCREMENT BY 1 START WITH 1; 
CREATE TABLE Employer(
    id VARCHAR(30) PRIMARY KEY,
    Nom VARCHAR(50),
    Prenom VARCHAR(50),
    Date_naissance DATE,
    Genre VARCHAR(30),
    date_embauche date
);
INSERT INTO Employer VALUES('emp_1','Rasoa','malala','1900-02-02', 'h', '2023-03-24');
INSERT INTO Employer VALUES('emp_2','Rakoto','malala','1900-02-02', 'h', '1995-03-24');

INSERT INTO Employer VALUES('emp_3','Rajoelina','malala','1900-02-02', 'h', '2021-03-24');
INSERT INTO Employer VALUES('emp_4','Siteny','malala','1900-02-02', 'h', '2018-03-24');
-- -----------------------------------------------------------------------------
-- TYPE DE POSTE
CREATE SEQUENCE poste_seq INCREMENT BY 1 START WITH 1; 
create table poste(
	id_poste varchar(100) primary key not null,
	nom varchar(100),
);

INSERT into poste values('PO3', 'EXPERT');

-- ---------------------------------------------------------------------

-- ATTRIBUTION NBR PERSONNEL PAR TAILLE PAR TYPE DE POSTE
create sequence taille_sac_emp_seq increment by 1 start with 1;

create table taille_sac_emp(
	id_taille_sac_emp varchar(100) primary key not null,
        id_poste varchar(100),
        valeur numeric,
        date_attribution date,
        foreign key(id_poste) references poste(id_poste)
);

-- ---------------------------------------------------------

-- --------------------------------------------------
create sequence taux_seq increment by 1 start with 1;

create table taux_horaire(
	id_taux varchar(100) primary key not null,
        id_poste varchar(100),
        salaire numeric,
        date_attribution date
);

-- ------------------------------------------------
-- ATTRIBUTION NBR PERSONNEL PAR TAILLE PAR TYPE DE POSTE
create sequence type_sac_emp_seq increment by 1 start with 1;
create table type_sac_emp(
	id_type_sac_emp varchar(100) primary key not null,
        id_type_sac varchar(100),
        valeur numeric,
        date_attribution date,
        foreign key(id_type_sac) references type_sac(id_type_sac)
);


-- --------------------------------------------------
-- POURCENTAGE BENEFICE / CHAQUE POKETRA
create sequence rate_benefice_seq increment by 1 start with 1;

create table benefice_rate(
    id_benefice varchar(100) primary key not null,
    id_poketra  varchar(100),
    rate numeric,
    date_rate date,
    foreign key(id_poketra) references poketra(id_poketra)
);

-- ---------------------------------------------------
-- REMISE
create sequence remise_seq increment by 1 start with 1;

create table remise (
    id_remise varchar(100) primary key not null,
    nom_remise varchar(100)
);

-- ----------------------------------------------------

create sequence remise_value_seq increment by 1 start with 1;

create table remise_value (
    id_remise_value varchar(100) primary key not null,
    id_remise varchar(100),
    value_remise numeric,
    date_remise timestamp,
    foreign key(id_remise) references remise(id_remise)
);




-- ---------------------------------------------------
-- CLIENT
create sequence client_seq increment by 1 start with 1;

create table client (
    id_client varchar(100) primary key not null,
    nom_client varchar(100),
    prenom_client varchar(100),
    dtn date,
    genre numeric
);


-- --------------------------------------------------

create sequence facture_seq increment by 1 start with 1;

create table facture (
    id_facture varchar(100) primary key not null,
    id_client varchar(100),
    date_facture date,
    id_remise varchar(100),
    montant_total numeric,
    status_facture numeric, -- CREER -- ENREGISTRER -- PAYEE
    foreign key(id_remise) references remise(id_remise),
    foreign key(id_client) references client(id_client)    
);

-- --------------------------------------------------------

create sequence fille_seq increment by 1 start with 1;

create table facture_fille(
    id_fille varchar(100) primary key not null,
    id_facture varchar(100),
    id_poketra varchar(100),
    pu_poketra numeric,
    qty numeric,
    montant numeric,
    foreign key(id_facture) references facture(id_facture),
    foreign key(id_poketra) references poketra(id_poketra)   
);

-- ------------------------------------------------------------

create or replace view v_vente_ratio as(
    select
        facture_fille.id_poketra,
        client.genre,
        sum(facture_fille.qty)
    from
    facture_fille
    join facture on facture.id_facture = facture_fille.id_facture
    join client on client.id_client = facture.id_client
    where facture.status_facture = 1
    group by  facture_fille.id_poketra, client.genre
);

-- ----------------------------------------------------------
-- MIN
create sequence min_seq increment by 1 start with 1;

create table taux_min (
    id_taux varchar(100) primary key not null,
    taux numeric,
    date_taux date
);

insert into taux_min values('TX1', 1000, '2023-12-12');

-- --------------------------------------------------------------
create sequence coefficient_seq increment by 1 start with 1;

create table coefficient_poste (
    id_coefficient varchar(100) primary key not null,
    id_poste varchar(100),
    ancienete numeric,
    coefficient numeric,
    date_coeff date,
    foreign key(id_poste) references poste(id_poste)
);

insert into coefficient_poste values('C1', 'POSTE_1', 0, 1, '2023-12-12');
insert into coefficient_poste values('C2', 'POSTE_2', 2, 2, '2023-12-12');
insert into coefficient_poste values('C3', 'PO3', 5, 3, '2023-12-12');
insert into coefficient_poste values('C4', 'PO3', 99, 3, '2023-12-12');

-- delete from coefficient_poste;

-- ----------------------------------------------------------------
create or replace view v_anciennete as(
SELECT 
    *,
    EXTRACT(DAY FROM (SELECT ((SELECT NOW()) - date_embauche) AS difference_en_jours)::interval) AS nombre_de_jours
FROM Employer
);

-- --------------------------------------------------------------
create sequence paiement_seq increment by 1 start with 1;

create table paiement(
    id_paiement varchar(100) primary key not null,
    id_facture varchar(100),
    date_paiement date,
    montant numeric,
    foreign key(id_facture) references facture(id_facture)
);


-- ---------------------------------------------------------------
create sequence mouvement_pok_seq increment by 1 start with 1;

create table mouvement_poketra(
    id_mouvement_poketra varchar(100) primary key not null,
    id_poketra varchar(100),
    date_mouvement date,
    qty_entree numeric,
    qty_sortie numeric,
    foreign key(id_poketra) references poketra(id_poketra)
);

-- --------------------------------------------------------------