SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';


DROP TABLE IF EXISTS `mirthdb`.`hl7_q` ;
DROP TABLE IF EXISTS `mirthdb`.`patient`;
DROP TABLE IF EXISTS `mirthdb`.`visit`;
DROP TABLE IF EXISTS `mirthdb`.`Provider`;
DROP TABLE IF EXISTS `mirthdb`.`Lab_Orders`;
DROP TABLE IF EXISTS `mirthdb`.`Lab_Results`;

CREATE SCHEMA IF NOT EXISTS `mirthdb` DEFAULT CHARACTER SET latin1 ;
USE `mirthdb` ;

-- -----------------------------------------------------
-- Table `mirthdb`.`hl7_q`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mirthdb`.`hl7_q` (
  `msg_seq` INT(11) NOT NULL AUTO_INCREMENT ,
  `msg_date` DATETIME NOT NULL ,
  `msg_text` LONGTEXT NOT NULL ,
  `read_flag` CHAR(1) NULL DEFAULT NULL ,
  PRIMARY KEY (`msg_seq`) )
ENGINE = InnoDB
AUTO_INCREMENT = 24
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `mirthdb`.`patient`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mirthdb`.`patient` (
  `pid` INT(11) NOT NULL AUTO_INCREMENT ,
  `mrn` VARCHAR(15) NOT NULL ,
  `last_name` VARCHAR(80) NOT NULL ,
  `first_name` VARCHAR(80) NOT NULL ,
  `birth_date` VARCHAR(8) NULL DEFAULT NULL ,
  `sex_code` CHAR(1) NULL DEFAULT NULL ,
  `ethnic_code` VARCHAR(15) NULL DEFAULT NULL ,
  `address_line1` VARCHAR(80) NULL DEFAULT NULL ,
  `address_line2` VARCHAR(80) NULL DEFAULT NULL ,
  `address_city` VARCHAR(80) NULL DEFAULT NULL ,
  `address_state` VARCHAR(2) NULL DEFAULT NULL ,
  `address_zip` VARCHAR(15) NULL DEFAULT NULL ,
  `home_phone` VARCHAR(15) NULL DEFAULT NULL ,
  `work_phone` VARCHAR(15) NULL DEFAULT NULL ,
  `marital_code` CHAR(1) NULL DEFAULT NULL ,
  `religion_code` VARCHAR(15) NULL DEFAULT NULL ,
  `ssn` VARCHAR(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`pid`) )
ENGINE = InnoDB
AUTO_INCREMENT = 24
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `mirthdb`.`visit`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mirthdb`.`visit` (
  `vid` INT(11) NOT NULL AUTO_INCREMENT ,
  `patient_class` CHAR(1) NOT NULL ,
  `admission_type` VARCHAR(15) NULL DEFAULT NULL ,
  `location` VARCHAR(40) NULL DEFAULT NULL ,
  `prior_location` VARCHAR(40) NULL DEFAULT NULL ,
  `attending_provider_number` INT(11) NULL DEFAULT NULL ,
  `attending_provider_name` VARCHAR(80) NULL DEFAULT NULL ,
  `hospital_service` VARCHAR(15) NULL DEFAULT NULL ,
  `visit_number` INT(11) NULL DEFAULT NULL ,
  `admit_date` VARCHAR(12) NULL DEFAULT NULL ,
  `discharge_date` VARCHAR(12) NULL DEFAULT NULL ,
  `patient_pid` INT(11) NOT NULL ,
  PRIMARY KEY (`vid`, `patient_pid`) ,
INDEX `fk_visit_patient1_idx` (`patient_pid` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 17
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `mirthdb`.`Provider`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mirthdb`.`Provider` (
  `providerID` INT NOT NULL ,
  `last_name` VARCHAR(45) NULL ,
  `first_name` VARCHAR(45) NULL ,
  PRIMARY KEY (`providerID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mirthdb`.`Lab_Orders`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mirthdb`.`Lab_Orders` (
  `labOrderNum` INT(11) NOT NULL AUTO_INCREMENT,
  `pid` INT(11) NOT NULL ,
  `placerNum` VARCHAR(45) NULL ,
  `visit_vid` INT(11) NOT NULL ,
  `visit_patient_pid` INT(11) NOT NULL ,
  `labOrderControl` VARCHAR(45) NULL ,
  `fillerOrderNum` VARCHAR(45) NULL ,
  `dateTransaction` VARCHAR(45) NULL ,
  `serviceIdentifier` VARCHAR(45) NULL ,
  `Provider_providerID` INT NOT NULL ,
  PRIMARY KEY (`labOrderNum`, `visit_vid`, `visit_patient_pid`, `Provider_providerID`) ,
  INDEX `fk_Lab_Orders_visit1_idx` (`visit_vid` ASC, `visit_patient_pid` ASC) ,
  INDEX `fk_Lab_Orders_Provider1_idx` (`Provider_providerID` ASC))
ENGINE = InnoDB;
AUTO_INCREMENT = 1


-- -----------------------------------------------------
-- Table `mirthdb`.`Lab_Results`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mirthdb`.`Lab_Results` (
  `labResultsNum` INT NOT NULL ,
  `labStatus` VARCHAR(45) NULL ,
  `labResults` VARCHAR(45) NULL ,
  `Lab_Orders_labOrderNum` INT(11) NOT NULL ,
  `Lab_Orders_visit_vid` INT(11) NOT NULL ,
  `Lab_Orders_visit_patient_pid` INT(11) NOT NULL ,
  `valueType` VARCHAR(45) NULL ,
  `Lab_Resultscol` VARCHAR(45) NULL ,
  PRIMARY KEY (`labResultsNum`, `Lab_Orders_labOrderNum`, `Lab_Orders_visit_vid`, `Lab_Orders_visit_patient_pid`) ,
  INDEX `fk_Lab_Results_Lab_Orders1_idx` (`Lab_Orders_labOrderNum` ASC, `Lab_Orders_visit_vid` ASC, `Lab_Orders_visit_patient_pid` ASC))
ENGINE = InnoDB;

USE `mirthdb` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
