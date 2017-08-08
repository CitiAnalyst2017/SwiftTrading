
-- ----------------------------
-- Table structure for `currency`
-- ----------------------------
DROP TABLE IF EXISTS `currency`;
CREATE TABLE `currency` (
  `ISO` char(3) NOT NULL,
  `Description` varchar(30) NOT NULL,
  PRIMARY KEY (`ISO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- ----------------------------
-- Table structure for `security`
-- ----------------------------
DROP TABLE IF EXISTS `security`;
CREATE TABLE `security` (
  `NameAbbreviation` varchar(20) NOT NULL,
  `SecurityName` varchar(30) NOT NULL,
  PRIMARY KEY (`NameAbbreviation`),
  KEY `NameAbbreviation` (`NameAbbreviation`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of security
-- ----------------------------
INSERT INTO `security` VALUES ('A', 'Agilent Technologies');
INSERT INTO `security` VALUES ('AA', 'AIcoa Inc');
INSERT INTO `security` VALUES ('AAL', 'ANERICAN AIRLINES GR');
INSERT INTO `security` VALUES ('AAP', 'Advance Audo Parts Inc');
INSERT INTO `security` VALUES ('ABBV', 'AbbVie');
INSERT INTO `security` VALUES ('ABT', 'Abbott Laboratories');
INSERT INTO `security` VALUES ('ACN', 'Accenture');
INSERT INTO `security` VALUES ('ADBE', 'Adobe');
INSERT INTO `security` VALUES ('ADS', 'Alliance Data System');
INSERT INTO `security` VALUES ('AES', 'The AES Corporation');
INSERT INTO `security` VALUES ('AET', 'Aetna Inc');
INSERT INTO `security` VALUES ('AFL', 'AFLAC Inc');
INSERT INTO `security` VALUES ('AGN', 'Allergan Inc');
INSERT INTO `security` VALUES ('AIG', 'American Internation');
INSERT INTO `security` VALUES ('AKAM', 'Akamai');
INSERT INTO `security` VALUES ('ALLE', 'Allegion plc Ordinar');
INSERT INTO `security` VALUES ('ALTR', 'Altera Corporation');
INSERT INTO `security` VALUES ('ALXN', 'Alexion Pharmaceutic');
INSERT INTO `security` VALUES ('AMG', 'Affiliated Managers');
INSERT INTO `security` VALUES ('APD', 'Air Products&Chemi');
INSERT INTO `security` VALUES ('AXP', 'American Express Com');
INSERT INTO `security` VALUES ('GAS', 'AGL Resources Inc');
INSERT INTO `security` VALUES ('MO', 'Altria Group Inc');

-- ----------------------------
-- Table structure for `strategy`
-- ----------------------------
DROP TABLE IF EXISTS `strategy`;
CREATE TABLE `strategy` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(20) NOT NULL,
  `Description` varchar(30) NOT NULL,
  `TradeID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `TradeID` (`TradeID`),
  CONSTRAINT `strategy_ibfk_1` FOREIGN KEY (`TradeID`) REFERENCES `trade` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- ----------------------------
-- Table structure for `trade`
-- ----------------------------
DROP TABLE IF EXISTS `trade`;
CREATE TABLE `trade` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Symbol` char(12) NOT NULL,
  `Quantity` bigint(20) NOT NULL,
  `BuyPrice` double NOT NULL,
  `SellPrice` double NOT NULL DEFAULT '0' COMMENT '0 represents no deal',
  `Expiration` char(30) NOT NULL,
  `StartTime` datetime NOT NULL,
  `EndTime` datetime NOT NULL,
  `Status` int(3) NOT NULL,
  `LossPrice` double NOT NULL,
  `ProfitPrice` double NOT NULL,
  `TradeType` char(2) NOT NULL,
  `ccy` char(3) NOT NULL,
  `Side` char(2) NOT NULL,
  `position` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `Code` (`Symbol`),
  KEY `ccy` (`ccy`),
  CONSTRAINT `trade_ibfk_2` FOREIGN KEY (`ccy`) REFERENCES `currency` (`ISO`),
  CONSTRAINT `trade_ibfk_3` FOREIGN KEY (`Symbol`) REFERENCES `security` (`NameAbbreviation`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(30) NOT NULL,
  `Email` varchar(30) NOT NULL,
  `PhoneNumber` char(11) NOT NULL,
  `Address` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


