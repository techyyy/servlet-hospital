DROP SCHEMA IF EXISTS hospital;
CREATE SCHEMA `hospital` DEFAULT CHARACTER SET utf8 ;
USE `hospital` ;

CREATE TABLE IF NOT EXISTS `hospital`.`login` (
                                                  `id` INT NOT NULL AUTO_INCREMENT,
                                                  `username` VARCHAR(45) NOT NULL,
                                                  `password` VARCHAR(999) NULL DEFAULT NULL,
                                                  `role` ENUM('DOCTOR', 'ADMIN', 'NURSE') NULL DEFAULT NULL,
                                                  PRIMARY KEY (`id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 16
    DEFAULT CHARACTER SET = utf8mb3;

LOCK TABLES `login` WRITE;
INSERT INTO `login` VALUES (1,'doctor','1A1DC91C907325C69271DDF0C944BC72','DOCTOR'),(2,'nurse','1A1DC91C907325C69271DDF0C944BC72','NURSE'),(3,'admin','1A1DC91C907325C69271DDF0C944BC72','ADMIN'),(7,'doctor2','1A1DC91C907325C69271DDF0C944BC72','DOCTOR'),(8,'nurse2','1A1DC91C907325C69271DDF0C944BC72','DOCTOR'),(9,'doctor3','1A1DC91C907325C69271DDF0C944BC72','DOCTOR'),(10,'doctor4','1A1DC91C907325C69271DDF0C944BC72','DOCTOR'),(11,'doctor5','1A1DC91C907325C69271DDF0C944BC72','DOCTOR'),(12,'doctor6','1A1DC91C907325C69271DDF0C944BC72','DOCTOR'),(13,'doctor8','1A1DC91C907325C69271DDF0C944BC72','DOCTOR'),(14,'doctor11','1A1DC91C907325C69271DDF0C944BC72','DOCTOR'),(15,'doctor12','5F4DCC3B5AA765D61D8327DEB882CF99','DOCTOR');
UNLOCK TABLES;

CREATE TABLE IF NOT EXISTS `hospital`.`admin` (
                                                  `id` INT NOT NULL AUTO_INCREMENT,
                                                  `firstName` VARCHAR(45) NOT NULL,
                                                  `lastName` VARCHAR(45) NOT NULL,
                                                  `login_id` INT NOT NULL,
                                                  PRIMARY KEY (`id`),
                                                  INDEX `fk_admin_login1_idx` (`login_id` ASC) VISIBLE,
                                                  CONSTRAINT `fk_admin_login1`
                                                      FOREIGN KEY (`login_id`)
                                                          REFERENCES `hospital`.`login` (`id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 2
    DEFAULT CHARACTER SET = utf8mb3;


CREATE TABLE IF NOT EXISTS `hospital`.`doctor` (
                                                   `id` INT NOT NULL AUTO_INCREMENT,
                                                   `firstName` VARCHAR(45) NOT NULL,
                                                   `lastName` VARCHAR(45) NOT NULL,
                                                   `position` VARCHAR(45) NOT NULL,
                                                   `login_id` INT NOT NULL,
                                                   PRIMARY KEY (`id`),
                                                   INDEX `fk_doctor_login1_idx` (`login_id` ASC) VISIBLE,
                                                   CONSTRAINT `fk_doctor_login1`
                                                       FOREIGN KEY (`login_id`)
                                                           REFERENCES `hospital`.`login` (`id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 12
    DEFAULT CHARACTER SET = utf8mb3;

LOCK TABLES `doctor` WRITE;
INSERT INTO `doctor` VALUES (1,'Doctor1','Doctor1LastName','D1Pos',1),(4,'D2','D2lastname','D2pos',7),(5,'d3','d3ln','d3pos',9),(6,'d4','d4ln','d4pos',10),(7,'d5','d5ln','d5pos',11),(8,'d6','d6ln','d6pos',12),(9,'d8','d8ln','d8pos',13),(10,'d11','d11ln','d11pos',14),(11,'d12','d12ln','d12pos',15);
UNLOCK TABLES;


CREATE TABLE IF NOT EXISTS `hospital`.`nurse` (
                                                  `id` INT NOT NULL AUTO_INCREMENT,
                                                  `firstName` VARCHAR(45) NOT NULL,
                                                  `lastName` VARCHAR(45) NOT NULL,
                                                  `login_id` INT NOT NULL,
                                                  PRIMARY KEY (`id`),
                                                  INDEX `fk_nurse_login_idx` (`login_id` ASC) VISIBLE,
                                                  CONSTRAINT `fk_nurse_login`
                                                      FOREIGN KEY (`login_id`)
                                                          REFERENCES `hospital`.`login` (`id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 2
    DEFAULT CHARACTER SET = utf8mb3;

LOCK TABLES `nurse` WRITE;
INSERT INTO `nurse` VALUES (1,'Nurse2','Nurse2lastname',8);
UNLOCK TABLES;

CREATE TABLE IF NOT EXISTS `hospital`.`patient` (
                                                    `id` INT NOT NULL AUTO_INCREMENT,
                                                    `firstName` VARCHAR(45) NOT NULL,
                                                    `lastName` VARCHAR(45) NOT NULL,
                                                    `diagnosis` VARCHAR(45) NULL DEFAULT NULL,
                                                    `birthDate` DATETIME NULL DEFAULT NULL,
                                                    `isDischarged` TINYINT(1) NOT NULL,
                                                    PRIMARY KEY (`id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 8;

LOCK TABLES `patient` WRITE;
INSERT INTO `patient` VALUES (1,'p1name','p1lastname','diagnosiss','2000-01-01 00:00:00',1),(2,'patientname2','patientlastname2','patient2diagnosis','2000-02-02 00:00:00',0),(3,'patientname3','patientlastname3','patient3diagnosis','2000-03-03 00:00:00',0),(4,'p4','p4lastname',NULL,'2003-03-18 00:00:00',0),(5,'p5','p5lastname',NULL,'2020-02-05 00:00:00',0),(6,'p6','p6ln','','2005-05-05 00:00:00',0),(7,'p10','p10ln','','2007-02-05 00:00:00',0);
UNLOCK TABLES;

CREATE TABLE IF NOT EXISTS `hospital`.`patient_has_doctor` (
                                                               `doctor_id` INT NOT NULL,
                                                               `patient_id` INT NOT NULL,
                                                               `treatment` VARCHAR(255) NULL DEFAULT NULL,
                                                               PRIMARY KEY (`doctor_id`, `patient_id`),
                                                               INDEX `fk_doctor_has_patient_patient1_idx` (`patient_id` ASC) VISIBLE,
                                                               INDEX `fk_doctor_has_patient_doctor1_idx` (`doctor_id` ASC) VISIBLE,
                                                               CONSTRAINT `fk_doctor_has_patient_doctor1`
                                                                   FOREIGN KEY (`doctor_id`)
                                                                       REFERENCES `hospital`.`doctor` (`id`),
                                                               CONSTRAINT `fk_doctor_has_patient_patient1`
                                                                   FOREIGN KEY (`patient_id`)
                                                                       REFERENCES `hospital`.`patient` (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;

LOCK TABLES `patient_has_doctor` WRITE;
INSERT INTO `patient_has_doctor` VALUES (1,1,'sleepe'),(1,2,'Sleep4'),(1,3,'SLEEP'),(1,7,''),(4,4,''),(4,5,'');
UNLOCK TABLES;

