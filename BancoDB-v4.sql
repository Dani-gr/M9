-- MySQL Script generated by MySQL Workbench
-- Thu May  4 21:56:13 2023
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema bancodb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `bancodb` ;

-- -----------------------------------------------------
-- Schema bancodb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bancodb` ;
USE `bancodb` ;

-- -----------------------------------------------------
-- Table `bancodb`.`direccion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bancodb`.`direccion` ;

CREATE TABLE IF NOT EXISTS `bancodb`.`direccion` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `calle` VARCHAR(45) COLLATE 'utf8mb3_bin' NOT NULL,
  `numero` INT NOT NULL,
  `planta_puerta_oficina` VARCHAR(45) COLLATE 'utf8mb3_bin' NOT NULL,
  `ciudad` VARCHAR(45) COLLATE 'utf8mb3_bin' NOT NULL,
  `region` VARCHAR(45) NULL,
  `pais` VARCHAR(45) COLLATE 'utf8mb3_bin' NOT NULL,
  `codpostal` VARCHAR(45) COLLATE 'utf8mb3_bin' NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bancodb`.`cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bancodb`.`cliente` ;

CREATE TABLE IF NOT EXISTS `bancodb`.`cliente` (
  `id_cliente` INT NOT NULL AUTO_INCREMENT,
  `direccion` INT NOT NULL,
  PRIMARY KEY (`id_cliente`),
  INDEX `Direccion_FK_idx` (`direccion` ASC) VISIBLE,
  CONSTRAINT `direccion_fk`
    FOREIGN KEY (`direccion`)
    REFERENCES `bancodb`.`direccion` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bancodb`.`cuenta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bancodb`.`cuenta` ;

CREATE TABLE IF NOT EXISTS `bancodb`.`cuenta` (
  `num_cuenta` INT NOT NULL AUTO_INCREMENT,
  `cliente` INT NOT NULL,
  `saldo` FLOAT NULL,
  `activa` TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (`num_cuenta`),
  INDEX `cliente_cuenta_fk` (`cliente` ASC) VISIBLE,
  CONSTRAINT `cliente_cuenta_fk`
    FOREIGN KEY (`cliente`)
    REFERENCES `bancodb`.`cliente` (`id_cliente`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bancodb`.`operacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bancodb`.`operacion` ;

CREATE TABLE IF NOT EXISTS `bancodb`.`operacion` (
  `id_operacion` INT NOT NULL AUTO_INCREMENT,
  `cuenta_realiza` INT NOT NULL,
  `fecha` DATE NOT NULL,
  PRIMARY KEY (`id_operacion`),
  INDEX `Cuenta_FK_idx` (`cuenta_realiza` ASC) VISIBLE,
  CONSTRAINT `cuenta_fk`
    FOREIGN KEY (`cuenta_realiza`)
    REFERENCES `bancodb`.`cuenta` (`num_cuenta`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bancodb`.`camb_divisa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bancodb`.`camb_divisa` ;

CREATE TABLE IF NOT EXISTS `bancodb`.`camb_divisa` (
  `operacion` INT NOT NULL,
  `origen` VARCHAR(45) COLLATE 'utf8mb3_bin' NOT NULL,
  `destino` VARCHAR(45) COLLATE 'utf8mb3_bin' NOT NULL,
  `cantidad` FLOAT NOT NULL,
  PRIMARY KEY (`operacion`),
  CONSTRAINT `operacion_fk_camb_divisa`
    FOREIGN KEY (`operacion`)
    REFERENCES `bancodb`.`operacion` (`id_operacion`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bancodb`.`usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bancodb`.`usuario` ;

CREATE TABLE IF NOT EXISTS `bancodb`.`usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nif` VARCHAR(10) COLLATE 'utf8mb3_bin' NOT NULL,
  `primer_nombre` VARCHAR(45) COLLATE 'utf8mb3_bin' NOT NULL,
  `segundo_nombre` VARCHAR(45) NULL,
  `primer_apellido` VARCHAR(45) COLLATE 'utf8mb3_bin' NOT NULL,
  `segundo_apellido` VARCHAR(45) COLLATE 'utf8mb3_bin' NULL,
  `fecha_nacimiento` DATE NOT NULL,
  `email` VARCHAR(45) COLLATE 'utf8mb3_bin' NOT NULL,
  `password` VARCHAR(45) COLLATE 'utf8mb3_bin' NOT NULL,
  `cliente` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  INDEX `Cliente_FK_idx` (`cliente` ASC) VISIBLE,
  CONSTRAINT `cliente_usuario_fk`
    FOREIGN KEY (`cliente`)
    REFERENCES `bancodb`.`cliente` (`id_cliente`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bancodb`.`chat`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bancodb`.`chat` ;

CREATE TABLE IF NOT EXISTS `bancodb`.`chat` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `asistente_id` INT NOT NULL,
  `cliente_id_cliente` INT NOT NULL,
  `activo` TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  INDEX `fk_Asistente_has_Cliente_Cliente1_idx` (`cliente_id_cliente` ASC) VISIBLE,
  INDEX `fk_Asistente_idx` (`asistente_id` ASC) VISIBLE,
  CONSTRAINT `fk_asistente`
    FOREIGN KEY (`asistente_id`)
    REFERENCES `bancodb`.`usuario` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_asistente_has_cliente_cliente1`
    FOREIGN KEY (`cliente_id_cliente`)
    REFERENCES `bancodb`.`cliente` (`id_cliente`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bancodb`.`empresa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bancodb`.`empresa` ;

CREATE TABLE IF NOT EXISTS `bancodb`.`empresa` (
  `id` INT NOT NULL,
  `cif` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `id_fk`
    FOREIGN KEY (`id`)
    REFERENCES `bancodb`.`cliente` (`id_cliente`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bancodb`.`clientes_empresa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bancodb`.`clientes_empresa` ;

CREATE TABLE IF NOT EXISTS `bancodb`.`clientes_empresa` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_empresa` INT NOT NULL,
  `id_cliente` INT NOT NULL,
  INDEX `FK_Cliente_idx` (`id_cliente` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_cliente`
    FOREIGN KEY (`id_cliente`)
    REFERENCES `bancodb`.`cliente` (`id_cliente`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_empresa_clientes`
    FOREIGN KEY (`id_empresa`)
    REFERENCES `bancodb`.`empresa` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bancodb`.`extraccion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bancodb`.`extraccion` ;

CREATE TABLE IF NOT EXISTS `bancodb`.`extraccion` (
  `operacion` INT NOT NULL,
  `cantidad` FLOAT NOT NULL,
  PRIMARY KEY (`operacion`),
  CONSTRAINT `operacion_fk_extraccion`
    FOREIGN KEY (`operacion`)
    REFERENCES `bancodb`.`operacion` (`id_operacion`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bancodb`.`mensaje`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bancodb`.`mensaje` ;

CREATE TABLE IF NOT EXISTS `bancodb`.`mensaje` (
  `id_mensaje` INT NOT NULL AUTO_INCREMENT,
  `fecha_hora` DATE NOT NULL,
  `chat` INT NOT NULL,
  `contenido` VARCHAR(500) COLLATE 'utf8mb3_bin' NOT NULL,
  `emisor` INT NOT NULL,
  PRIMARY KEY (`id_mensaje`),
  INDEX `chat_FK_idx` (`chat` ASC) VISIBLE,
  INDEX `emisor_FK_idx` (`emisor` ASC) VISIBLE,
  CONSTRAINT `chat_fk`
    FOREIGN KEY (`chat`)
    REFERENCES `bancodb`.`chat` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `emisor_fk`
    FOREIGN KEY (`emisor`)
    REFERENCES `bancodb`.`usuario` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bancodb`.`rol`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bancodb`.`rol` ;

CREATE TABLE IF NOT EXISTS `bancodb`.`rol` (
  `idrol` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) COLLATE 'utf8mb3_bin' NOT NULL,
  PRIMARY KEY (`idrol`),
  UNIQUE INDEX `nombre_UNIQUE` (`nombre` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 8;


-- -----------------------------------------------------
-- Table `bancodb`.`rolusuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bancodb`.`rolusuario` ;

CREATE TABLE IF NOT EXISTS `bancodb`.`rolusuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idrol` INT NOT NULL,
  `idusuario` INT NOT NULL,
  `idempresa` INT NULL,
  `bloqueado` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `fk_idUsuario_idx` (`idusuario` ASC) VISIBLE,
  INDEX `fk_empresa_idx` (`idempresa` ASC) VISIBLE,
  CONSTRAINT `fk_empresa_rol`
    FOREIGN KEY (`idempresa`)
    REFERENCES `bancodb`.`empresa` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_idrol`
    FOREIGN KEY (`idrol`)
    REFERENCES `bancodb`.`rol` (`idrol`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_idusuario`
    FOREIGN KEY (`idusuario`)
    REFERENCES `bancodb`.`usuario` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bancodb`.`transferencia`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bancodb`.`transferencia` ;

CREATE TABLE IF NOT EXISTS `bancodb`.`transferencia` (
  `operacion` INT NOT NULL,
  `cuenta_destino` INT NULL,
  `cantidad` FLOAT NOT NULL,
  `IBAN_destino` VARCHAR(24) NULL,
  PRIMARY KEY (`operacion`),
  INDEX `CuentaDestino_idx` (`cuenta_destino` ASC) VISIBLE,
  CONSTRAINT `cuenta_destino_FK`
    FOREIGN KEY (`cuenta_destino`)
    REFERENCES `bancodb`.`cuenta` (`num_cuenta`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `operacion_fk_transferencia`
    FOREIGN KEY (`operacion`)
    REFERENCES `bancodb`.`operacion` (`id_operacion`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bancodb`.`cuentas_sospechosas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bancodb`.`cuentas_sospechosas` ;

CREATE TABLE IF NOT EXISTS `bancodb`.`cuentas_sospechosas` (
  `idcuentas_sospechosas` INT NOT NULL AUTO_INCREMENT,
  `IBAN` VARCHAR(24) NOT NULL,
  PRIMARY KEY (`idcuentas_sospechosas`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO rol(nombre) VALUES ('gestor');
INSERT INTO rol(nombre) VALUES ('asistente');
INSERT INTO rol(nombre) VALUES ('socio');
INSERT INTO rol(nombre) VALUES ('autorizado');
INSERT INTO rol(nombre) VALUES ('cliente');

SET FOREIGN_KEY_CHECKS = 0;
