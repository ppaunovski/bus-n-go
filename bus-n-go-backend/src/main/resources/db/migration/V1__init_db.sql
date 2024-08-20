-- set search_path = project_new;

drop table if exists Se_simnuva_na;
--drop table if exists Se_validira;
drop table if exists KaznaZaRegistriran;
drop table if exists KaznaZaNeregistriran;
drop table if exists Kazna;
drop table if exists Kontroli;
drop table if exists Vozenje;
drop table if exists Bilet;
drop table if exists TipBilet;
drop table if exists PostojkaNaLinija;
drop table if exists Postojka;
drop table if exists Mesto;
drop table if exists InstancaNaLinija;
drop table if exists Linija;
drop table if exists Avtobus;
--drop table if exists Dokument;
drop table if exists Kondukter;
drop table if exists Vozac;
drop table if exists Vraboten;
drop table if exists Patnik;
drop table if exists KorisnikRole;
drop table if exists Korisnik;
drop table if exists Role;


--drop domain if exists string_dolg;
--drop domain if exists string_kratok;

--drop schema if exists project_new;

--create schema project_new;

--set search_path = project_new;

--create domain string_dolg AS character varying(4000);
--create domain string_kratok AS character varying(500);



create table Korisnik(
                         is_admin boolean,
                         id bigserial primary key,
                         adresa character varying(255),
                         email character varying(255),
                         ime character varying(255),
                         lozinka character varying(255),
                         telefon character varying(255)
);

create table Role (
                      id bigserial primary key,
                      description character varying(255),
                      name character varying(255)
);

create table Korisnik_Role (
                               id bigserial primary key,
                               korisnik_id bigint,
                               role_id bigint,
                               foreign key (role_id) references role (id),
                               foreign key (korisnik_id) references korisnik (id)
);

create table Patnik(
                       id bigserial primary key,
                       korisnik_id bigint,
                       foreign key (korisnik_id) references korisnik (id)
);
--	*k_id referencira od Korisnik(k_id)


create table Vraboten(
                         datum_na_vrabotuvanje date,
                         datum_prekin_vrabotuvanje date,
                         plata double precision,
                         id bigserial primary key,
                         korisnik_id bigint,
                         foreign key (korisnik_id) references korisnik (id)
);
--	*k_id referencira od Korisnik(k_id)


create table Vozac(
                      id bigserial primary key,
                      vraboten_id bigint,
                      foreign key (vraboten_id) references vraboten (id)
);
--	*k_id referencira od Korisnik(k_id)

create table Kondukter(
                          id bigserial primary key,
                          vraboten_id bigint,
                          foreign key (vraboten_id) references vraboten (id)
);
--	*k_id referencira od Korisnik(k_id)

--create table Dokument(
--	d_broj_na_dokumnet string_kratok primary key,
--	d_datum_na_izdavanje date not null,
--	d_datum_ist date not null,
--	d_koj_go_izdal string_kratok not null,
--	k_id bigint not null,
--	constraint dokument_za_covek foreign key (k_id) references Korisnik(k_id)
--);

create table Avtobus(
                        broj_sedishta smallint,
                        id bigserial primary key,
                        registracija character varying(255),
                        seriski_broj character varying(255)
);

create table Linija(
                       id serial primary key,
                       ime character varying(255)
);

create table Pravec (
                      id bigserial primary key,
                      pravec character varying(255),
                      opis character varying(255)
);

create table linija_pravec (
                      id bigserial primary key,
                      pravec_id bigint,
                      linija_id integer,
                      foreign key (pravec_id) references Pravec (id),
                      foreign key (linija_id) references Linija (id)
);

create table Instanca_Na_Linija (
                                  linija_id integer,
                                  pravec_id bigint,
                                  avtobus_id bigint,
                                  end_date timestamp(6) without time zone,
                                  id bigserial primary key,
                                  start_date timestamp(6) without time zone,
                                  vozac_id bigint,
                                  foreign key (avtobus_id) references avtobus (id),
                                  foreign key (linija_id) references linija (id),
                                  foreign key (vozac_id) references vozac (id)
);
--	*k_id referencira od Korisnik(k_id)
--	^a_registracija referncira od Avtobus(a_registracija)
--	#li_id referencira od Linija(li_id)

--create table Mesto(
--	m_id serial primary key,
--	m_grad string_kratok not null,
--	m_opstina string_kratok not null,
--	m_ulica string_kratok not null
--);

create table Postojka (
                          id serial primary key,
                          lat double precision,
                          lon double precision,
                          ime character varying(255),
                          opis character varying(255)
);
--*m_id referncira od Mesto(m_id)


create table Postojka_Na_Linija (
                                  linija_id integer,
                                  postojka_id integer,
                                  pravec_id bigint,
                                  reden_broj smallint,
                                  id bigserial primary key,
                                  foreign key (postojka_id) references postojka (id),
                                  foreign key (linija_id) references linija (id)
);
--	*li_id referencira od Linija(li_id)
--	^p_id referencira od Postojka(p_id)

create table instanca_na_linija_postojka_na_linija (
                                  id bigserial primary key,
                                  instanca_na_linija_id bigint not null,
                                  postojka_na_linija_id bigint not null,
                                  timestamp timestamp not null,
                                  foreign key (instanca_na_linija_id) references instanca_na_linija(id),
                                  foreign key (postojka_na_linija_id) references postojka_na_linija(id)
);

create table TipBilet (
                          cena real,
                          id bigserial primary key,
                          trajnost bigint,
                          ime character varying(255)
);



create table Bilet (
                       datum_kupuvanje timestamp(6) without time zone,
                       datum_aktivacija timestamp(6) without time zone,
                       id bigserial primary key,
                       patnik_id bigint,
                       tip_id bigint,
                       status character varying(255),
                       foreign key (patnik_id) references patnik (id),
                       foreign key (tip_id) references tipbilet (id)
);


create table Vozenje (
                         bilet_id bigint,
                         end_date timestamp(6) without time zone,
                         id bigserial primary key,
                         instanca_na_linija_id bigint,
                         patnik_id bigint,
                         postojka_na_linija_start_id bigint,
                         start_date timestamp(6) without time zone,
                         status character varying(255),
                         foreign key (patnik_id) references patnik (id),
                         foreign key (bilet_id) references bilet (id),
                         foreign key (instanca_na_linija_id) references instanca_na_linija (id),
                         foreign key (postojka_na_linija_start_id) references postojka_na_linija (id)
);


create table Kontrola (
                          date_created timestamp(6) without time zone,
                          id bigserial primary key,
                          instanca_na_linija_id bigint,
                          kondukter_id bigint,
                          foreign key (instanca_na_linija_id) references instanca_na_linija (id),
                          foreign key (kondukter_id) references kondukter (id)
);

create table Kazna (
                       iznos double precision,
                       plateno boolean,
                       date_created timestamp(6) without time zone,
                       date_payed timestamp(6) without time zone,
                       id bigserial primary key,
                       kondukter_id bigint,
                       kontrola_id bigint,
                       dokument character varying(255),
                       foreign key (kondukter_id) references Kondukter (id),
                       foreign key (kontrola_id) references kontrola (id)
);


create table Kazna_Za_Registriran(
                                   id bigserial primary key,
                                   kazna_id bigint,
                                   patnik_id bigint,
                                   foreign key (patnik_id) references patnik (id),
                                   foreign key (kazna_id) references kazna (id)
);

create table kazna_za_neregistriran(
                                     id bigserial primary key,
                                     kazna_id bigint,
                                     adresa character varying(255),
                                     ime character varying(255),
                                     telefon character varying(255),
                                     foreign key (kazna_id) references kazna (id)
);



--create table Se_validira (
--    vozenje_id bigint not null,
--    b_id bigint not null,
--    primary key (vozenje_id, b_id),
--    foreign key (vozenje_id) references Vozenje(vozenje_id),
--    foreign key (b_id) references Bilet(b_id)
--);
----	* vozenje_id referencira od Vozenje(vozenje_id)
----	^ b_id referencira od Bilet(b_id)


create table se_simnuva_na (
                               id bigserial primary key,
                               postojka_na_linija_id bigint,
                               vozenje_id bigint,
                               foreign key (vozenje_id) references vozenje (id),
                               foreign key (postojka_na_linija_id) references postojka_na_linija (id)
);