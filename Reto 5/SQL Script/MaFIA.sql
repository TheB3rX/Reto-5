USE mafia;
CREATE TABLE carrera(
    id_carrera int NOT NULL auto_increment,
    nombre varchar(40) NOT NULL,
    capacidad int NOT NULL default 0,
    dificultad float(4,1) NOT NULL,
    se_puede_correr bool NOT NULL default false,
    PRIMARY KEY(id_carrera)
);
insert into carrera (nombre, capacidad, dificultad, se_puede_correr)
values ('Zipatocan',8473,8.9,true);
insert into carrera (nombre, capacidad, dificultad, se_puede_correr)
values ('Quirachiquin',12332,7.3,true);
insert into carrera (nombre, dificultad)
values ('Vitaguata',1.9);

CREATE TABLE escuderia
(
    cod_escuderia    int         NOT NULL,
    nombre           varchar(40) NOT NULL,
    patrocinador     varchar(40) NOT NULL,
    carreras_ganadas int         NOT NULL,
    fecha_ingreso    date        NOT NULL,
    PRIMARY KEY (cod_escuderia)
);
INSERT INTO escuderia(cod_escuderia, nombre, patrocinador, carreras_ganadas, fecha_ingreso)
VALUES (232, 'Viejo Willy', 'Doña Cecilia', 38, '2001-12-12');
INSERT INTO escuderia(cod_escuderia, nombre, patrocinador, carreras_ganadas, fecha_ingreso)
VALUES (345, 'Severo Fierrari', 'Guión Burger', 45, '1998-03-07');
INSERT INTO escuderia(cod_escuderia, nombre, patrocinador, carreras_ganadas, fecha_ingreso)
VALUES (876, 'Mechas Plateadas', 'Corredor Gourmet', 42, '1999-08-30');

CREATE TABLE piloto
(
    id_piloto          int         NOT NULL,
    piloto_nombre      varchar(30) NOT NULL,
    piloto_millas      float(10, 2),
    piloto_combustible int,
    cod_escuderia      int         NOT NULL,
    FOREIGN KEY (cod_escuderia) REFERENCES escuderia (cod_escuderia),
    PRIMARY KEY (id_piloto)
);
insert into piloto (id_piloto, piloto_nombre, piloto_millas, piloto_combustible, cod_escuderia)
values (12, 'JuanPis', 27833.8, 9876456, (select cod_escuderia from escuderia where nombre like 'Viejo Willy'));
insert into piloto (id_piloto, piloto_nombre, piloto_millas, piloto_combustible, cod_escuderia)
values (872, 'Chumajer', null, null, (select cod_escuderia from escuderia where nombre like 'Severo Fierrari'));

CREATE TABLE participacion(
    id_piloto int NOT NULL,
    id_carrera int NOT NULL,
    fecha TIMESTAMP NOT NULL default CURRENT_TIMESTAMP,
    posicion int NOT NULL,
    FOREIGN KEY(id_piloto) REFERENCES piloto(id_piloto),
    FOREIGN KEY(id_carrera) REFERENCES carrera(id_carrera)
);
insert into participacion (id_piloto, id_carrera, fecha, posicion)
values (12,1,TIMESTAMP('2019-02-12 18:56:45'),1);
insert into participacion (id_piloto, id_carrera, fecha, posicion)
values (872,1,TIMESTAMP('2019-02-12 18:56:45'),2);
insert into participacion (id_piloto, id_carrera, posicion)
values (12,2,2);
insert into participacion (id_piloto, id_carrera, posicion)
values (872,2,1);

set SQL_SAFE_UPDATES = 0;

delete
from participacion
where true;

delete
from mafia.escuderia
where nombre like 'Mechas Plateadas';
update piloto
set piloto_millas      = 124009.2,
    piloto_combustible = 98737372
where piloto_nombre like 'Chumajer';
insert into participacion (id_piloto, id_carrera, posicion)
values (12,3,1);
update carrera
set capacidad = 10284,
    se_puede_correr = false,
    dificultad = 6.3
where nombre like 'Vitaguata';
