-- MySQL Script generated by MySQL Workbench
-- Mon Apr 24 11:46:52 2023
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema BancoDB
-- -----------------------------------------------------
-- Base de datos para el sistema web de gestión bancaria.
DROP SCHEMA IF EXISTS `BancoDB` ;

-- -----------------------------------------------------
-- Schema BancoDB
--
-- Base de datos para el sistema web de gestión bancaria.
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `BancoDB` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;
USE `BancoDB` ;

-- -----------------------------------------------------
-- Table `BancoDB`.`CambDivisa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BancoDB`.`CambDivisa` ;

CREATE TABLE IF NOT EXISTS `BancoDB`.`CambDivisa` (
  `Operacion` INT NOT NULL,
  `origen` VARCHAR(45) NOT NULL,
  `destino` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Operacion`),
  CONSTRAINT `Operacion_FK_CambDivisa`
    FOREIGN KEY (`Operacion`)
    REFERENCES `BancoDB`.`Operacion` (`idOperacion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BancoDB`.`Chat`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BancoDB`.`Chat` ;

CREATE TABLE IF NOT EXISTS `BancoDB`.`Chat` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Asistente_ID` INT NOT NULL,
  `Cliente_ID_Cliente` INT NOT NULL,
  INDEX `fk_Asistente_has_Cliente_Cliente1_idx` (`Cliente_ID_Cliente` ASC) ,
  PRIMARY KEY (`ID`),
  INDEX `fk_Asistente_idx` (`Asistente_ID` ASC) ,
  CONSTRAINT `fk_Asistente_has_Cliente_Cliente1`
    FOREIGN KEY (`Cliente_ID_Cliente`)
    REFERENCES `BancoDB`.`Cliente` (`ID_Cliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Asistente`
    FOREIGN KEY (`Asistente_ID`)
    REFERENCES `BancoDB`.`Usuario` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BancoDB`.`Cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BancoDB`.`Cliente` ;

CREATE TABLE IF NOT EXISTS `BancoDB`.`Cliente` (
  `ID_Cliente` INT NOT NULL AUTO_INCREMENT,
  `Direccion` INT NOT NULL,
  PRIMARY KEY (`ID_Cliente`),
  INDEX `Direccion_FK_idx` (`Direccion` ASC) ,
  CONSTRAINT `Direccion_FK`
    FOREIGN KEY (`Direccion`)
    REFERENCES `BancoDB`.`Direccion` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BancoDB`.`ClientesEmpresa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BancoDB`.`ClientesEmpresa` ;

CREATE TABLE IF NOT EXISTS `BancoDB`.`ClientesEmpresa` (
  `ID_Empresa` INT NOT NULL,
  `ID_Cliente` INT NOT NULL,
  PRIMARY KEY (`ID_Empresa`, `ID_Cliente`),
  INDEX `FK_Cliente_idx` (`ID_Cliente` ASC) ,
  CONSTRAINT `FK_Empresa_Clientes`
    FOREIGN KEY (`ID_Empresa`)
    REFERENCES `BancoDB`.`Empresa` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_Cliente`
    FOREIGN KEY (`ID_Cliente`)
    REFERENCES `BancoDB`.`Cliente` (`ID_Cliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BancoDB`.`Cuenta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BancoDB`.`Cuenta` ;

CREATE TABLE IF NOT EXISTS `BancoDB`.`Cuenta` (
  `numCuenta` INT NOT NULL AUTO_INCREMENT,
  `cliente` INT NOT NULL,
  `saldo` FLOAT NULL,
  PRIMARY KEY (`numCuenta`),
  CONSTRAINT `Cliente_Cuenta_FK`
    FOREIGN KEY (`cliente`)
    REFERENCES `BancoDB`.`Cliente` (`ID_Cliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BancoDB`.`Direccion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BancoDB`.`Direccion` ;

CREATE TABLE IF NOT EXISTS `BancoDB`.`Direccion` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `calle` VARCHAR(45) NOT NULL,
  `numero` INT NOT NULL,
  `planta_puerta_oficina` VARCHAR(45) NOT NULL,
  `ciudad` VARCHAR(45) NOT NULL,
  `region` VARCHAR(45) NULL,
  `pais` VARCHAR(45) NOT NULL,
  `codPostal` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BancoDB`.`Empresa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BancoDB`.`Empresa` ;

CREATE TABLE IF NOT EXISTS `BancoDB`.`Empresa` (
  `ID` INT NOT NULL,
  `CIF` INT NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `Cliente_UNIQUE` (`ID` ASC) ,
  UNIQUE INDEX `CIF_UNIQUE` (`CIF` ASC) ,
  CONSTRAINT `ID_FK`
    FOREIGN KEY (`ID`)
    REFERENCES `BancoDB`.`Cliente` (`ID_Cliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BancoDB`.`Extraccion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BancoDB`.`Extraccion` ;

CREATE TABLE IF NOT EXISTS `BancoDB`.`Extraccion` (
  `Operacion` INT NOT NULL,
  `Cantidad` FLOAT NOT NULL,
  PRIMARY KEY (`Operacion`),
  CONSTRAINT `Operacion_FK_Extraccion`
    FOREIGN KEY (`Operacion`)
    REFERENCES `BancoDB`.`Operacion` (`idOperacion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BancoDB`.`Mensaje`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BancoDB`.`Mensaje` ;

CREATE TABLE IF NOT EXISTS `BancoDB`.`Mensaje` (
  `ID_mensaje` INT NOT NULL AUTO_INCREMENT,
  `Fecha_hora` DATE NOT NULL,
  `chat` INT NOT NULL,
  `Contenido` VARCHAR(500) NOT NULL,
  `emisor` INT NOT NULL,
  PRIMARY KEY (`ID_mensaje`),
  INDEX `chat_FK_idx` (`chat` ASC) ,
  INDEX `emisor_FK_idx` (`emisor` ASC) ,
  CONSTRAINT `chat_FK`
    FOREIGN KEY (`chat`)
    REFERENCES `BancoDB`.`Chat` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `emisor_FK`
    FOREIGN KEY (`emisor`)
    REFERENCES `BancoDB`.`Usuario` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BancoDB`.`Operacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BancoDB`.`Operacion` ;

CREATE TABLE IF NOT EXISTS `BancoDB`.`Operacion` (
  `idOperacion` INT NOT NULL AUTO_INCREMENT,
  `CuentaRealiza` INT NOT NULL,
  `Fecha` DATE NOT NULL,
  PRIMARY KEY (`idOperacion`),
  INDEX `Cuenta_FK_idx` (`CuentaRealiza` ASC) ,
  CONSTRAINT `Cuenta_FK`
    FOREIGN KEY (`CuentaRealiza`)
    REFERENCES `BancoDB`.`Cuenta` (`numCuenta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BancoDB`.`Rol`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BancoDB`.`Rol` ;

CREATE TABLE IF NOT EXISTS `BancoDB`.`Rol` (
  `idRol` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idRol`),
  UNIQUE INDEX `nombre_UNIQUE` (`nombre` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BancoDB`.`RolUsuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BancoDB`.`RolUsuario` ;

CREATE TABLE IF NOT EXISTS `BancoDB`.`RolUsuario` (
  `idRol` INT NOT NULL,
  `idUsuario` INT NOT NULL,
  `idEmpresa` INT NULL,
  PRIMARY KEY (`idRol`, `idUsuario`),
  INDEX `fk_idUsuario_idx` (`idUsuario` ASC) ,
  INDEX `fk_empresa_idx` (`idEmpresa` ASC) ,
  CONSTRAINT `fk_idRol`
    FOREIGN KEY (`idRol`)
    REFERENCES `BancoDB`.`Rol` (`idRol`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_idUsuario`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `BancoDB`.`Usuario` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_empresa_Rol`
    FOREIGN KEY (`idEmpresa`)
    REFERENCES `BancoDB`.`Empresa` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BancoDB`.`Transferencia`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BancoDB`.`Transferencia` ;

CREATE TABLE IF NOT EXISTS `BancoDB`.`Transferencia` (
  `Operacion` INT NOT NULL,
  `CuentaDestino` INT NOT NULL,
  `cantidad` FLOAT NOT NULL,
  PRIMARY KEY (`Operacion`),
  INDEX `CuentaDestino_idx` (`CuentaDestino` ASC) ,
  CONSTRAINT `Operacion_FK_Transferencia`
    FOREIGN KEY (`Operacion`)
    REFERENCES `BancoDB`.`Operacion` (`idOperacion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `CuentaDestino`
    FOREIGN KEY (`CuentaDestino`)
    REFERENCES `BancoDB`.`Cuenta` (`numCuenta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BancoDB`.`Usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BancoDB`.`Usuario` ;

CREATE TABLE IF NOT EXISTS `BancoDB`.`Usuario` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NIF` VARCHAR(10) NOT NULL,
  `primerNombre` VARCHAR(45) NOT NULL,
  `segundoNombre` VARCHAR(45) NULL,
  `primerApellido` VARCHAR(45) NOT NULL,
  `segundoApellido` VARCHAR(45) NULL,
  `fechaNacimiento` DATE NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `cliente` INT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) ,
  INDEX `Cliente_FK_idx` (`cliente` ASC) ,
  CONSTRAINT `Cliente_Usuario_FK`
    FOREIGN KEY (`cliente`)
    REFERENCES `BancoDB`.`Cliente` (`ID_Cliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO ROL(nombre) VALUES('Gestor');
INSERT INTO ROL(nombre) VALUES('Cliente');
INSERT INTO ROL(nombre) VALUES('Socio');
INSERT INTO ROL(nombre) VALUES('Autorizado');
INSERT INTO ROL(nombre) VALUES('Asistente');