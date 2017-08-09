/*
 Navicat MySQL Data Transfer

 Source Server         : du
 Source Server Version : 50715
 Source Host           : localhost
 Source Database       : swifttraing

 Target Server Version : 50715
 File Encoding         : utf-8

 Date: 08/09/2017 20:12:54 PM
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
--  Table structure for `strategy`
-- ----------------------------
DROP TABLE IF EXISTS `strategy`;
CREATE TABLE `strategy` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) DEFAULT NULL,
  `Description` varchar(30) DEFAULT NULL,
  `SecuritySymbol` varchar(10) NOT NULL,
  `ExitPoint` double NOT NULL,
  `LongPeriod` int(10) DEFAULT NULL,
  `ShortPeriod` int(10) DEFAULT NULL,
  `Period` int(10) DEFAULT NULL,
  `Std` double DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `Symbol` (`SecuritySymbol`),
  KEY `Name` (`Name`),
  CONSTRAINT `Security` FOREIGN KEY (`SecuritySymbol`) REFERENCES `security` (`NameAbbreviation`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `trade`
-- ----------------------------
DROP TABLE IF EXISTS `trade`;
CREATE TABLE `trade` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SecuritySymbol` char(12) NOT NULL,
  `Quantity` bigint(20) NOT NULL,
  `BuyPrice` double DEFAULT NULL,
  `SalePrice` double DEFAULT '0' COMMENT '0 represents no deal',
  `Expiration` datetime NOT NULL,
  `StartTime` datetime NOT NULL,
  `EndTime` datetime DEFAULT NULL,
  `Status` char(10) NOT NULL,
  `TradeType` char(10) NOT NULL,
  `ccy` char(3) DEFAULT NULL,
  `position` varchar(20) NOT NULL,
  `StrategyID` int(10) DEFAULT NULL,
  `ProfitPrice` double NOT NULL,
  `LossPrice` double NOT NULL,
  `BuyPriceReal` double DEFAULT NULL,
  `SalePriceReal` double DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `Code` (`SecuritySymbol`),
  KEY `ccy` (`ccy`),
  KEY `StrategyID` (`StrategyID`),
  CONSTRAINT `trade_ibfk_2` FOREIGN KEY (`ccy`) REFERENCES `currency` (`ISO`),
  CONSTRAINT `trade_ibfk_3` FOREIGN KEY (`SecuritySymbol`) REFERENCES `security` (`NameAbbreviation`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8;

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

SET FOREIGN_KEY_CHECKS = 1;
