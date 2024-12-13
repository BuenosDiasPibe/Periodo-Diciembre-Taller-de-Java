CREATE database IF NOT EXISTS `recuperacion` DEFAULT CHARACTER SET utf8mb4
 COLLATE utf8mb4_0900_ai_ci ;
 USE `recuperacion`;

DROP TABLE IF EXISTS `recuperacion`.`estudios`;
 CREATE TABLE IF NOT EXISTS `recuperacion`.`estudios` (
 `idestudio` INT NOT NULL,
 `nombre` VARCHAR(45) NOT NULL,

 PRIMARY KEY (`idestudio`));

CREATE TABLE IF NOT EXISTS `recuperacion`.`personas` (
 `idpersona` INT NOT NULL AUTO_INCREMENT,
 `nombre` VARCHAR(45) NULL DEFAULT NULL,
 `nacimiento` DATE NULL DEFAULT NULL,
 `hijoas` BIT(1) NULL DEFAULT NULL,
 `idestudio` INT NULL DEFAULT NULL,

 PRIMARY KEY (`idpersona`),
 INDEX `fk_estudio_idx` (`idestudio` ASC),
 CONSTRAINT `fk_estudio`
 FOREIGN KEY (`idestudio`)
 REFERENCES `recuperacion`.`estudios` (`idestudio`));

 INSERT INTO `recuperacion`.`estudios` (`idestudio`, `nombre`) VALUES (1, 'Secundario
 incompleto');
 INSERT INTO `recuperacion`.`estudios` (`idestudio`, `nombre`) VALUES (2, 'Secundario
 completo');
 INSERT INTO `recuperacion`.`estudios` (`idestudio`, `nombre`) VALUES (3, 'Terciario no
 universitario');
 INSERT INTO `recuperacion`.`estudios` (`idestudio`, `nombre`) VALUES (4, 'Universitario');
 INSERT INTO `recuperacion`.`estudios` (`idestudio`, `nombre`) VALUES (5, 'Universitario
 incompleto');