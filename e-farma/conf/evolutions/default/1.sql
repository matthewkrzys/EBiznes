-- Fruits schema

-- !Ups
CREATE TABLE Fruits
(
    "id"      INTEGER     NOT NULL PRIMARY KEY AUTOINCREMENT,
    "name"    VARCHAR(45) not NULL,
    "quantity" INTEGER,
    "weight"  VARCHAR(45) not NULL,
    "price"   DOUBLE
);

CREATE TABLE Honeys
(
    "id"      INTEGER     NOT NULL PRIMARY KEY AUTOINCREMENT,
    "name"    VARCHAR(45) not NULL,
    "quantity" INTEGER,
    "weight"  VARCHAR(45) not NULL,
    "price"   DOUBLE
);

CREATE TABLE Vegetables
(
    "id"      INTEGER     NOT NULL PRIMARY KEY AUTOINCREMENT,
    "name"    VARCHAR(45) not NULL,
    "quantity" INTEGER,
    "weight"  VARCHAR(45) not NULL,
    "price"   DOUBLE
);

CREATE TABLE Seeds
(
    "id"      INTEGER     NOT NULL PRIMARY KEY AUTOINCREMENT,
    "name"    VARCHAR(45) not NULL,
    "quantity" INTEGER,
    "weight"  VARCHAR(45) not NULL,
    "price"   DOUBLE,
    "description" VARCHAR(45) not NULL
);

CREATE TABLE Preserves
(
    "id"      INTEGER     NOT NULL PRIMARY KEY AUTOINCREMENT,
    "name"    VARCHAR(45) not NULL,
    "quantity" INTEGER,
    "weight"  VARCHAR(45) not NULL,
    "price"   DOUBLE,
    "description" VARCHAR(45) not NULL
);

CREATE TABLE Flowers
(
    "id"      INTEGER     NOT NULL PRIMARY KEY AUTOINCREMENT,
    "name"    VARCHAR(45) not NULL,
    "quantity" INTEGER,
    "species"  VARCHAR(45) not NULL,
    "price"   DOUBLE,
    "description" VARCHAR(45) not NULL
);

CREATE TABLE Plants
(
    "id"      INTEGER     NOT NULL PRIMARY KEY AUTOINCREMENT,
    "name"    VARCHAR(45) not NULL,
    "quantity" INTEGER,
    "species"  VARCHAR(45) not NULL,
    "price"   DOUBLE,
    "description" VARCHAR(45) not NULL
);


CREATE TABLE Tools
(
    "id"      INTEGER     NOT NULL PRIMARY KEY AUTOINCREMENT,
    "name"    VARCHAR(45) not NULL,
    "quantity" INTEGER,
    "price"   DOUBLE,
    "description" VARCHAR(45) not NULL
);
-- !Downs
drop table Fruits;
drop table Honeys;
drop table Vegetables;
drop table Seeds;
drop table Preserves;
drop table Flowers;
drop table Plants;
drop table Tools;
