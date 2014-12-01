SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `user` (
  `userId` INT NOT NULL ,
  `username` VARCHAR(45) NOT NULL ,
  `password` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`userId`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `patients`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `patients` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(20) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bill`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bill` (
  `billId` INT NOT NULL AUTO_INCREMENT ,
  `patientId` INT(11) NOT NULL ,
  `dateCreated` DATETIME NOT NULL ,
  `datePaid` DATETIME NULL ,
  `consultationCost` INT NOT NULL ,
  PRIMARY KEY (`billId`) ,
  INDEX `fk_Bill_patients` (`patientId` ASC) ,
  CONSTRAINT `fk_Bill_patients`
    FOREIGN KEY (`patientId` )
    REFERENCES `patients` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `medicine`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `medicine` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(20) NOT NULL ,
  `cost` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `billItem`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `billItem` (
  `quantity` INT NOT NULL ,
  `billId` INT NOT NULL ,
  `medicineid` INT(11) NOT NULL ,
  INDEX `fk_billItem_Bill1` (`billId` ASC) ,
  INDEX `fk_billItem_medicine1` (`medicineid` ASC) ,
  CONSTRAINT `fk_billItem_Bill1`
    FOREIGN KEY (`billId` )
    REFERENCES `bill` (`billId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_billItem_medicine1`
    FOREIGN KEY (`medicineid` )
    REFERENCES `medicine` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `deletedPatient`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `deletedPatient` (
  `patientId` INT(11) NOT NULL AUTO_INCREMENT ,
  `removalDate` DATETIME NOT NULL ,
  PRIMARY KEY (`patientId`) ,
  CONSTRAINT `fk_deletedPatient_patients1`
    FOREIGN KEY (`patientId` )
    REFERENCES `patients` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `deletedMedicine`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `deletedMedicine` (
  `medicineId` INT(11) NOT NULL AUTO_INCREMENT ,
  `removalDate` DATETIME NOT NULL ,
  PRIMARY KEY (`medicineId`) ,
  CONSTRAINT `fk_deletedMedicine_medicine1`
    FOREIGN KEY (`medicineId` )
    REFERENCES `medicine` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
