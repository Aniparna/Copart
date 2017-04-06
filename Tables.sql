CREATE TABLE `world`.`copart_facility` (
  `YARD` INT NOT NULL,
  `STATE` VARCHAR(120) NULL,
  `CITY` VARCHAR(120) NULL,
  `ADDRESS` VARCHAR(120) NULL,
  `ZIP` VARCHAR(120) NULL,
  `PHONE` VARCHAR(120) NULL,
  `FAX` VARCHAR(120) NULL,
  PRIMARY KEY (`YARD`));

  
  CREATE TABLE `world`.`customer_details` (
  `TRANSACTION_ID` INT NOT NULL,
  `CUST_ID` INT NULL,
  `SELLER_ID` INT NULL,
  `AUTOMOBILE` VARCHAR(45) NULL,
  `OUT_OF_STATE` VARCHAR(120) NULL,
  PRIMARY KEY (`TRANSACTION_ID`));

  