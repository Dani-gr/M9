-- MySQL Script generated by MySQL Workbench
-- Mon Apr 24 13:45:47 2023
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
-- Table `BancoDB`.`camb_divisa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BancoDB`.`camb_divisa` ;

CREATE TABLE IF NOT EXISTS `BancoDB`.`camb_divisa` (
  `operacion` INT NOT NULL,
  `origen` VARCHAR(45) NOT NULL,
  `destino` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`operacion`),
  CONSTRAINT `operacion_fk_camb_divisa`
    FOREIGN KEY (`operacion`)
    REFERENCES `BancoDB`.`operacion` (`id_operacion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BancoDB`.`chat`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BancoDB`.`chat` ;

CREATE TABLE IF NOT EXISTS `BancoDB`.`chat` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `asistente_id` INT NOT NULL,
  `cliente_id_cliente` INT NOT NULL,
  INDEX `fk_Asistente_has_Cliente_Cliente1_idx` (`cliente_id_cliente` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  INDEX `fk_Asistente_idx` (`asistente_id` ASC) VISIBLE,
  CONSTRAINT `fk_asistente_has_cliente_cliente1`
    FOREIGN KEY (`cliente_id_cliente`)
    REFERENCES `BancoDB`.`cliente` (`id_cliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_asistente`
    FOREIGN KEY (`asistente_id`)
    REFERENCES `BancoDB`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BancoDB`.`cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BancoDB`.`cliente` ;

CREATE TABLE IF NOT EXISTS `BancoDB`.`cliente` (
  `id_cliente` INT NOT NULL AUTO_INCREMENT,
  `direccion` INT NOT NULL,
  PRIMARY KEY (`id_cliente`),
  INDEX `Direccion_FK_idx` (`direccion` ASC) VISIBLE,
  CONSTRAINT `direccion_fk`
    FOREIGN KEY (`direccion`)
    REFERENCES `BancoDB`.`direccion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BancoDB`.`clientes_empresa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BancoDB`.`clientes_empresa` ;

CREATE TABLE IF NOT EXISTS `BancoDB`.`clientes_empresa` (
  `id_empresa` INT NOT NULL,
  `id_cliente` INT NOT NULL,
  PRIMARY KEY (`id_empresa`, `id_cliente`),
  INDEX `FK_Cliente_idx` (`id_cliente` ASC) VISIBLE,
  CONSTRAINT `fk_empresa_clientes`
    FOREIGN KEY (`id_empresa`)
    REFERENCES `BancoDB`.`empresa` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cliente`
    FOREIGN KEY (`id_cliente`)
    REFERENCES `BancoDB`.`cliente` (`id_cliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BancoDB`.`cuenta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BancoDB`.`cuenta` ;

CREATE TABLE IF NOT EXISTS `BancoDB`.`cuenta` (
  `num_cuenta` INT NOT NULL AUTO_INCREMENT,
  `cliente` INT NOT NULL,
  `saldo` FLOAT NULL,
  PRIMARY KEY (`num_cuenta`),
  CONSTRAINT `cliente_cuenta_fk`
    FOREIGN KEY (`cliente`)
    REFERENCES `BancoDB`.`cliente` (`id_cliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BancoDB`.`direccion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BancoDB`.`direccion` ;

CREATE TABLE IF NOT EXISTS `BancoDB`.`direccion` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `calle` VARCHAR(45) NOT NULL,
  `numero` INT NOT NULL,
  `planta_puerta_oficina` VARCHAR(45) NOT NULL,
  `ciudad` VARCHAR(45) NOT NULL,
  `region` VARCHAR(45) NULL,
  `pais` VARCHAR(45) NOT NULL,
  `codpostal` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BancoDB`.`empresa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BancoDB`.`empresa` ;

CREATE TABLE IF NOT EXISTS `BancoDB`.`empresa` (
  `id` INT NOT NULL,
  `cif` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `Cliente_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `CIF_UNIQUE` (`cif` ASC) VISIBLE,
  CONSTRAINT `id_fk`
    FOREIGN KEY (`id`)
    REFERENCES `BancoDB`.`cliente` (`id_cliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BancoDB`.`extraccion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BancoDB`.`extraccion` ;

CREATE TABLE IF NOT EXISTS `BancoDB`.`extraccion` (
  `operacion` INT NOT NULL,
  `cantidad` FLOAT NOT NULL,
  PRIMARY KEY (`operacion`),
  CONSTRAINT `operacion_fk_extraccion`
    FOREIGN KEY (`operacion`)
    REFERENCES `BancoDB`.`operacion` (`id_operacion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BancoDB`.`mensaje`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BancoDB`.`mensaje` ;

CREATE TABLE IF NOT EXISTS `BancoDB`.`mensaje` (
  `id_mensaje` INT NOT NULL AUTO_INCREMENT,
  `fecha_hora` DATE NOT NULL,
  `chat` INT NOT NULL,
  `contenido` VARCHAR(500) NOT NULL,
  `emisor` INT NOT NULL,
  PRIMARY KEY (`id_mensaje`),
  INDEX `chat_FK_idx` (`chat` ASC) VISIBLE,
  INDEX `emisor_FK_idx` (`emisor` ASC) VISIBLE,
  CONSTRAINT `chat_fk`
    FOREIGN KEY (`chat`)
    REFERENCES `BancoDB`.`chat` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `emisor_fk`
    FOREIGN KEY (`emisor`)
    REFERENCES `BancoDB`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BancoDB`.`operacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BancoDB`.`operacion` ;

CREATE TABLE IF NOT EXISTS `BancoDB`.`operacion` (
  `id_operacion` INT NOT NULL AUTO_INCREMENT,
  `cuenta_realiza` INT NOT NULL,
  `fecha` DATE NOT NULL,
  PRIMARY KEY (`id_operacion`),
  INDEX `Cuenta_FK_idx` (`cuenta_realiza` ASC) VISIBLE,
  CONSTRAINT `cuenta_fk`
    FOREIGN KEY (`cuenta_realiza`)
    REFERENCES `BancoDB`.`cuenta` (`num_cuenta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BancoDB`.`rol`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BancoDB`.`rol` ;

CREATE TABLE IF NOT EXISTS `BancoDB`.`rol` (
  `idrol` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idrol`),
  UNIQUE INDEX `nombre_UNIQUE` (`nombre` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BancoDB`.`rolusuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BancoDB`.`rolusuario` ;

CREATE TABLE IF NOT EXISTS `BancoDB`.`rolusuario` (
  `idrol` INT NOT NULL,
  `idusuario` INT NOT NULL,
  `idempresa` INT NULL,
  PRIMARY KEY (`idrol`, `idusuario`),
  INDEX `fk_idUsuario_idx` (`idusuario` ASC) VISIBLE,
  INDEX `fk_empresa_idx` (`idempresa` ASC) VISIBLE,
  CONSTRAINT `fk_idrol`
    FOREIGN KEY (`idrol`)
    REFERENCES `BancoDB`.`rol` (`idrol`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_idusuario`
    FOREIGN KEY (`idusuario`)
    REFERENCES `BancoDB`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_empresa_rol`
    FOREIGN KEY (`idempresa`)
    REFERENCES `BancoDB`.`empresa` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BancoDB`.`transferencia`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BancoDB`.`transferencia` ;

CREATE TABLE IF NOT EXISTS `BancoDB`.`transferencia` (
  `operacion` INT NOT NULL,
  `cuenta_destino` INT NOT NULL,
  `cantidad` FLOAT NOT NULL,
  PRIMARY KEY (`operacion`),
  INDEX `CuentaDestino_idx` (`cuenta_destino` ASC) VISIBLE,
  CONSTRAINT `operacion_fk_transferencia`
    FOREIGN KEY (`operacion`)
    REFERENCES `BancoDB`.`operacion` (`id_operacion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `cuenta_destino_FK`
    FOREIGN KEY (`cuenta_destino`)
    REFERENCES `BancoDB`.`cuenta` (`num_cuenta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BancoDB`.`usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BancoDB`.`usuario` ;

CREATE TABLE IF NOT EXISTS `BancoDB`.`usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nif` VARCHAR(10) NOT NULL,
  `primer_nombre` VARCHAR(45) NOT NULL,
  `segundo_nombre` VARCHAR(45) NULL,
  `primer_apellido` VARCHAR(45) NOT NULL,
  `segundo_apellido` VARCHAR(45) NULL,
  `fecha_nacimiento` DATE NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `cliente` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  INDEX `Cliente_FK_idx` (`cliente` ASC) VISIBLE,
  CONSTRAINT `cliente_usuario_fk`
    FOREIGN KEY (`cliente`)
    REFERENCES `BancoDB`.`cliente` (`id_cliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO rol(nombre) VALUES ('gestor');
INSERT INTO rol(nombre) VALUES ('asistente');
INSERT INTO rol(nombre) VALUES ('socio');
INSERT INTO rol(nombre) VALUES ('autorizado');
INSERT INTO rol(nombre) VALUES ('cliente');
