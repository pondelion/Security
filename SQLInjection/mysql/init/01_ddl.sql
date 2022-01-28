CREATE DATABASE IF NOT EXISTS testdb;

USE testdb;

CREATE TABLE IF NOT EXISTS kv (
    id  INT NOT NULL AUTO_INCREMENT,
    k   CHAR(255) UNIQUE,
    v   TEXT,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS secrets (
    id  INT NOT NULL AUTO_INCREMENT,
    data    TEXT,
    PRIMARY KEY (id)
);
