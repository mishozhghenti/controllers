DROP DATABASE IF EXISTS gateways;
CREATE DATABASE gateways DEFAULT CHARACTER SET utf8;
USE gateways;


CREATE TABLE `gateways`.`master_devices` (
  `master_id` INT NOT NULL AUTO_INCREMENT,
  `serial_number` VARCHAR(45) NOT NULL,
  `name` NVARCHAR(45) NOT NULL,
  `ip_v4` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`master_id`),
  UNIQUE INDEX `serial_number_UNIQUE` (`serial_number` ASC) VISIBLE);


CREATE TABLE `gateways`.`peripheral_devices` (
  `device_id` INT NOT NULL AUTO_INCREMENT,
  `uid` INT NOT NULL,
  `vendor` NVARCHAR(45) NOT NULL,
  `date_created` DATETIME NOT NULL,
  `status` TINYINT NOT NULL,
  PRIMARY KEY (`device_id`));


CREATE TABLE `gateways`.`associated_devices` (
  `gateway_id` INT NULL,
  `device_id` INT NULL,
  INDEX `gateway_fk_idx` (`gateway_id` ASC) VISIBLE,
  INDEX `periph_fk_idx` (`device_id` ASC) VISIBLE,
  CONSTRAINT `gateway_fk`
    FOREIGN KEY (`gateway_id`)
    REFERENCES `gateways`.`master_devices` (`master_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `periph_fk`
    FOREIGN KEY (`device_id`)
    REFERENCES `gateways`.`peripheral_devices` (`device_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);