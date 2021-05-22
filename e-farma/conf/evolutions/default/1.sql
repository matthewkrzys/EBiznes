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
    "description" VARCHAR(200) not NULL
);

CREATE TABLE Preserves
(
    "id"      INTEGER     NOT NULL PRIMARY KEY AUTOINCREMENT,
    "name"    VARCHAR(45) not NULL,
    "quantity" INTEGER,
    "weight"  VARCHAR(45) not NULL,
    "price"   DOUBLE,
    "description" VARCHAR(200) not NULL
);

CREATE TABLE Flowers
(
    "id"      INTEGER     NOT NULL PRIMARY KEY AUTOINCREMENT,
    "name"    VARCHAR(45) not NULL,
    "quantity" INTEGER,
    "species"  VARCHAR(45) not NULL,
    "price"   DOUBLE,
    "description" VARCHAR(200) not NULL
);

CREATE TABLE Plants
(
    "id"      INTEGER     NOT NULL PRIMARY KEY AUTOINCREMENT,
    "name"    VARCHAR(45) not NULL,
    "quantity" INTEGER,
    "species"  VARCHAR(45) not NULL,
    "price"   DOUBLE,
    "description" VARCHAR(200) not NULL
);


CREATE TABLE Tools
(
    "id"      INTEGER     NOT NULL PRIMARY KEY AUTOINCREMENT,
    "name"    VARCHAR(45) not NULL,
    "quantity" INTEGER,
    "price"   DOUBLE,
    "description" VARCHAR(45) not NULL
);

CREATE TABLE Users
(
    "id"      INTEGER     NOT NULL PRIMARY KEY AUTOINCREMENT,
    "name"    VARCHAR(45) not NULL,
    "surname" VARCHAR(45) not NULL,
    "password"   VARCHAR(45) not NULL,
    "email"   VARCHAR(100) not NULL,
    "telephone"   VARCHAR(20) not NULL,
    "address"   VARCHAR(200) not NULL
);

CREATE TABLE History
(
    "id"      INTEGER     NOT NULL PRIMARY KEY AUTOINCREMENT,
    "product"    VARCHAR(45) not NULL,
    "description" VARCHAR(200) not NULL,
    "users"   INTEGER,
    FOREIGN KEY(Users) references Users(id)
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
drop table Users;
drop table History;
