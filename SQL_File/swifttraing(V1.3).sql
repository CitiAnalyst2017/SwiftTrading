/*
 Navicat MySQL Data Transfer

 Source Server         : du
 Source Server Version : 50715
 Source Host           : localhost
 Source Database       : swifttraing

 Target Server Version : 50715
 File Encoding         : utf-8

 Date: 08/08/2017 12:15:42 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `currency`
-- ----------------------------
DROP TABLE IF EXISTS `currency`;
CREATE TABLE `currency` (
  `ISO` char(3) NOT NULL,
  `Description` varchar(30) NOT NULL,
  PRIMARY KEY (`ISO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `currency`
-- ----------------------------
BEGIN;
INSERT INTO `currency` VALUES ('CNY', 'CNYYYYYYY'), ('USD', 'USDDDDDDD');
COMMIT;

-- ----------------------------
--  Table structure for `security`
-- ----------------------------
DROP TABLE IF EXISTS `security`;
CREATE TABLE `security` (
  `NameAbbreviation` varchar(20) NOT NULL,
  `SecurityName` varchar(30) NOT NULL,
  PRIMARY KEY (`NameAbbreviation`),
  KEY `NameAbbreviation` (`NameAbbreviation`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `security`
-- ----------------------------
BEGIN;
INSERT INTO `security` VALUES ('A', 'Agilent Technologies'), ('AA', 'AIcoa Inc'), ('AAL', 'ANERICAN AIRLINES GR'), ('AAP', 'Advance Audo Parts Inc'), ('ABBV', 'AbbVie'), ('ABT', 'Abbott Laboratories'), ('ACN', 'Accenture'), ('ADBE', 'Adobe'), ('ADS', 'Alliance Data System'), ('AES', 'The AES Corporation'), ('AET', 'Aetna Inc'), ('AFL', 'AFLAC Inc'), ('AGN', 'Allergan Inc'), ('AIG', 'American Internation'), ('AKAM', 'Akamai'), ('ALLE', 'Allegion plc Ordinar'), ('ALTR', 'Altera Corporation'), ('ALXN', 'Alexion Pharmaceutic'), ('AMG', 'Affiliated Managers'), ('APD', 'Air Products&Chemi'), ('AXP', 'American Express Com'), ('GAS', 'AGL Resources Inc'), ('MO', 'Altria Group Inc');
COMMIT;

-- ----------------------------
--  Table structure for `strategy`
-- ----------------------------
DROP TABLE IF EXISTS `strategy`;
CREATE TABLE `strategy` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(20) NOT NULL,
  `Description` varchar(30) NOT NULL,
  `TradeID` int(11) NOT NULL,
  `Symbol` varchar(10) NOT NULL,
  `Exit` double NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `Symbol` (`Symbol`),
  CONSTRAINT `Symbol` FOREIGN KEY (`Symbol`) REFERENCES `security` (`NameAbbreviation`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `strategy`
-- ----------------------------
BEGIN;
INSERT INTO `strategy` VALUES ('1', 'Name1', 'desc', '1', 'A', '0.3');
COMMIT;

-- ----------------------------
--  Table structure for `trade`
-- ----------------------------
DROP TABLE IF EXISTS `trade`;
CREATE TABLE `trade` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Symbol` char(12) NOT NULL,
  `Quantity` bigint(20) NOT NULL,
  `BuyPrice` double NOT NULL,
  `SellPrice` double NOT NULL DEFAULT '0' COMMENT '0 represents no deal',
  `Expiration` int(30) NOT NULL,
  `StartTime` datetime NOT NULL,
  `EndTime` datetime NOT NULL,
  `Status` char(10) NOT NULL,
  `LossPrice` double NOT NULL,
  `ProfitPrice` double NOT NULL,
  `TradeType` char(10) NOT NULL,
  `ccy` char(3) NOT NULL,
  `Side` char(2) NOT NULL,
  `position` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `Code` (`Symbol`),
  KEY `ccy` (`ccy`),
  CONSTRAINT `trade_ibfk_2` FOREIGN KEY (`ccy`) REFERENCES `currency` (`ISO`),
  CONSTRAINT `trade_ibfk_3` FOREIGN KEY (`Symbol`) REFERENCES `security` (`NameAbbreviation`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `trade`
-- ----------------------------
BEGIN;
INSERT INTO `trade` VALUES ('1', 'A', '1000', '10.5', '11.5', '15', '2017-08-08 09:47:55', '2017-08-10 09:47:57', 'CLOSED', '9.5', '11.5', 'MARKET', 'CNY', 'B', 'LONG'), ('2', 'A', '1000', '10.5', '0', '15', '2017-08-07 22:05:40', '2017-08-07 22:05:40', 'CREATED', '9.5', '11.5', 'MARKET', 'CNY', 'B', 'LONG'), ('4', 'AA', '1000', '10.5', '0', '15', '2017-08-07 22:13:19', '2017-08-07 22:13:19', 'CREATED', '9.5', '11.5', 'MARKET', 'CNY', 'B', 'LONG');
COMMIT;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(30) NOT NULL,
  `Email` varchar(30) NOT NULL,
  `PhoneNumber` char(11) NOT NULL,
  `Address` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('1', 'Smith', '831276323@qq.com', '18739490544', 'Gold Road No.1');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
