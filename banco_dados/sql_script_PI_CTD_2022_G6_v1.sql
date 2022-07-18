-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema hostel_bnb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema hostel_bnb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `hostel_bnb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Cidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Cidade` (
  `idCidade` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `country` VARCHAR(70) NOT NULL,
  PRIMARY KEY (`idCidade`),
  UNIQUE INDEX `idCidade_UNIQUE` (`idCidade` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Locador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Locador` (
  `idLocador` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  `sobrenome` VARCHAR(45) NOT NULL,
  `email` VARCHAR(70) NOT NULL,
  `senha_hash` VARCHAR(70) NOT NULL,
  PRIMARY KEY (`idLocador`),
  UNIQUE INDEX `idLocador_UNIQUE` (`idLocador` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  UNIQUE INDEX `senha_hash_UNIQUE` (`senha_hash` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Imagem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Imagem` (
  `idImagem` INT NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(50) NOT NULL,
  `url` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`idImagem`),
  UNIQUE INDEX `idImagem_UNIQUE` (`idImagem` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Categorias`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Categorias` (
  `idCategorias` INT NOT NULL AUTO_INCREMENT,
  `Imagem_idImagem` INT NOT NULL,
  PRIMARY KEY (`idCategorias`),
  UNIQUE INDEX `idCategorias_UNIQUE` (`idCategorias` ASC),
  INDEX `fk_Categorias_Imagem1_idx` (`Imagem_idImagem` ASC),
  CONSTRAINT `fk_Categorias_Imagem1`
    FOREIGN KEY (`Imagem_idImagem`)
    REFERENCES `mydb`.`Imagem` (`idImagem`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Produto` (
  `idProduto` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `descricao` VARCHAR(255) NOT NULL,
  `Locador_idLocador` INT NOT NULL,
  `Cidade_idCidade` INT NOT NULL,
  `Categorias_idCategorias` INT NOT NULL,
  PRIMARY KEY (`idProduto`),
  UNIQUE INDEX `idProduto_UNIQUE` (`idProduto` ASC),
  INDEX `fk_Produto_Cidade1_idx` (`Cidade_idCidade` ASC),
  INDEX `fk_Produto_Locador1_idx` (`Locador_idLocador` ASC),
  INDEX `fk_Produto_Categorias1_idx` (`Categorias_idCategorias` ASC),
  CONSTRAINT `fk_Produto_Cidade1`
    FOREIGN KEY (`Cidade_idCidade`)
    REFERENCES `mydb`.`Cidade` (`idCidade`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Produto_Locador1`
    FOREIGN KEY (`Locador_idLocador`)
    REFERENCES `mydb`.`Locador` (`idLocador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Produto_Categorias1`
    FOREIGN KEY (`Categorias_idCategorias`)
    REFERENCES `mydb`.`Categorias` (`idCategorias`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Usuario` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `lastname` VARCHAR(50) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `senha_hash` VARCHAR(255) NULL,
  PRIMARY KEY (`idUsuario`),
  UNIQUE INDEX `idLocatario_UNIQUE` (`idUsuario` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  UNIQUE INDEX `senha_UNIQUE` (`senha_hash` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`funcoes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`funcoes` (
  `idFuncoes` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `Usuario_idLocatario` INT NOT NULL,
  PRIMARY KEY (`idFuncoes`),
  UNIQUE INDEX `idFuncoes_UNIQUE` (`idFuncoes` ASC),
  INDEX `fk_funcoes_Usuario1_idx` (`Usuario_idLocatario` ASC),
  CONSTRAINT `fk_funcoes_Usuario1`
    FOREIGN KEY (`Usuario_idLocatario`)
    REFERENCES `mydb`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Reserva`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Reserva` (
  `idReserva` INT NOT NULL AUTO_INCREMENT,
  `horaInicioReserva` TIME NOT NULL,
  `dataInicialReserva` DATE NOT NULL,
  `dataFinalReserva` DATE NOT NULL,
  `Produto_idProduto` INT NOT NULL,
  `Usuario_idLocatario` INT NOT NULL,
  `funcoes_idFuncoes` INT NOT NULL,
  PRIMARY KEY (`idReserva`),
  UNIQUE INDEX `idReserva_UNIQUE` (`idReserva` ASC),
  INDEX `fk_Reserva_Produto1_idx` (`Produto_idProduto` ASC),
  INDEX `fk_Reserva_Usuario1_idx` (`Usuario_idLocatario` ASC),
  INDEX `fk_Reserva_funcoes1_idx` (`funcoes_idFuncoes` ASC),
  CONSTRAINT `fk_Reserva_Produto1`
    FOREIGN KEY (`Produto_idProduto`)
    REFERENCES `mydb`.`Produto` (`idProduto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Reserva_Usuario1`
    FOREIGN KEY (`Usuario_idLocatario`)
    REFERENCES `mydb`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Reserva_funcoes1`
    FOREIGN KEY (`funcoes_idFuncoes`)
    REFERENCES `mydb`.`funcoes` (`idFuncoes`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Caracteristica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Caracteristica` (
  `idCaracteristica` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `icone` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`idCaracteristica`),
  UNIQUE INDEX `idCaracteristica_UNIQUE` (`idCaracteristica` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Caracteristica_has_Produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Caracteristica_has_Produto` (
  `Caracteristica_idCaracteristica` INT NOT NULL,
  `Produto_idProduto` INT NOT NULL,
  PRIMARY KEY (`Caracteristica_idCaracteristica`, `Produto_idProduto`),
  INDEX `fk_Caracteristica_has_Produto_Produto1_idx` (`Produto_idProduto` ASC),
  INDEX `fk_Caracteristica_has_Produto_Caracteristica_idx` (`Caracteristica_idCaracteristica` ASC),
  CONSTRAINT `fk_Caracteristica_has_Produto_Caracteristica`
    FOREIGN KEY (`Caracteristica_idCaracteristica`)
    REFERENCES `mydb`.`Caracteristica` (`idCaracteristica`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Caracteristica_has_Produto_Produto1`
    FOREIGN KEY (`Produto_idProduto`)
    REFERENCES `mydb`.`Produto` (`idProduto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `hostel_bnb` ;

-- -----------------------------------------------------
-- Table `hostel_bnb`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hostel_bnb`.`category` (
  `categoryId` INT NOT NULL AUTO_INCREMENT,
  `classification` VARCHAR(50) NULL DEFAULT NULL,
  `description` VARCHAR(300) NULL DEFAULT NULL,
  `url_imagem` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`categoryId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
