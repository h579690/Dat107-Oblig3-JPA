DROP SCHEMA IF EXISTS oblig3 CASCADE;
SET search_path TO oblig3;

CREATE SCHEMA oblig3;

create table avdeling
(
	id SERIAL,
	navn VARCHAR,
	sjef_Id INTEGER not null,
	CONSTRAINT avdeling_pk PRIMARY KEY (id)
);


CREATE TABLE ansatt
(
	id SERIAL,
    bnavn VARCHAR,
    fnavn VARCHAR NOT NULL,
    enavn VARCHAR NOT NULL,
    ansettelsesdato DATE,
    stilling VARCHAR,
    maanedslonn INTEGER,
    avd_Id INTEGER not null,
  CONSTRAINT ansatt_pk PRIMARY KEY (id),
  constraint avdeling_fk foreign key (avd_Id) references avdeling(id)
);

create table prosjekt
(
	id serial,
	navn varchar,
	beskrivelse varchar,
	constraint prosjekt_pk primary key (id)
);

create table prosjektdeltagelse
(
	ansatt_id integer,
	prosjekt_id integer,
	timer float,
	rolle varchar,
	constraint prosjektdeltagelse_pk primary key (ansatt_id, prosjekt_id),
	constraint ansatt_fk foreign key (ansatt_id) references ansatt(id),
	constraint prosjekt_fk foreign key (prosjekt_id) references prosjekt(id)
);

INSERT INTO
  avdeling(navn, sjef_Id)
VALUES
    ('kontor', 1),
    ('kjokken', 2),
    ('stall', 3);

INSERT INTO
  ansatt(bnavn, fnavn, enavn, ansettelsesdato, stilling, maanedslonn, avd_Id)
values
    ('klp', 'Ola', 'Sil', '2000-10-17', 'sjef', 4000, 2),
    ('mos', 'Morten', 'Skogmus', '1990-11-11', 'sjef', 100, 3), 
   ('ppo', 'Pelle', 'Politi', '2019-04-11', 'undermann', 5500, 1),
    ('kkl', 'Klara', 'Klegg', '2000-10-17', 'vaskehjelp', 4000, 2),
    ('olc', 'Ola', 'Conny', '1990-11-11', 'vareplasserer', 100, 3),
   ('kro', 'Kriss', 'Ola', '1990-11-11', 'pensjonist', 100, 1),
   ('skf', 'Skilpadden', 'Franklin', '2019-04-11', 'undermann', 5500, 2),
    ('fin', 'Fisken', 'Nemo', '2000-10-17', 'vaskehjelp', 4000, 3)
   
insert into
	prosjekt(navn, beskrivelse)
values
	('renovering', 'nytt kjøkken'),
	('stimuli', 'utvide avdelingen med et stimuli rom');

INSERT INTO
  prosjektdeltagelse(ansatt_Id, prosjekt_Id, timer, rolle)
VALUES
  (4, 1, 50, 'vakthold'),
  (5, 1, 100, 'maler'),
  (6, 2, 150, 'arkitekt'),
  (7, 1, 200, 'møblerer'),
  (8, 2, 250, 'skribent'),
  (9, 1, 300, 'data-ansvarlig');
   
ALTER TABLE avdeling ADD CONSTRAINT sjef_FK FOREIGN KEY (sjef_Id) REFERENCES ansatt(id); 