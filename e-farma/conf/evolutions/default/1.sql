-- Fruits schema

-- !Ups
CREATE TABLE Fruits
(
    `id`      INTEGER     NOT NULL PRIMARY KEY AUTOINCREMENT,
    `name`    VARCHAR(45) not NULL,
    "quanity" INTEGER,
    "weight"  VARCHAR(45) not NULL,
    "price"   DOUBLE
);
-- !Downs
drop table Fruits;