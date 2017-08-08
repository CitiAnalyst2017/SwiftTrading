/*
 Navicat MySQL Data Transfer

 Source Server         : du
 Source Server Version : 50715
 Source Host           : localhost
 Source Database       : swifttraing

 Target Server Version : 50715
 File Encoding         : utf-8

 Date: 08/08/2017 16:49:30 PM
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
  `Name` varchar(50) NOT NULL,
  `Description` varchar(30) NOT NULL,
  `SecurityName` varchar(10) NOT NULL,
  `ExitPoint` double NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `Symbol` (`SecurityName`),
  CONSTRAINT `Symbol` FOREIGN KEY (`SecurityName`) REFERENCES `security` (`NameAbbreviation`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `trade`
-- ----------------------------
DROP TABLE IF EXISTS `trade`;
CREATE TABLE `trade` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SecurityName` char(12) NOT NULL,
  `Quantity` bigint(20) NOT NULL,
  `BuyPrice` double NOT NULL,
  `SellPrice` double DEFAULT '0' COMMENT '0 represents no deal',
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
  PRIMARY KEY (`ID`),
  KEY `Code` (`SecurityName`),
  KEY `ccy` (`ccy`),
  KEY `StrategyID` (`StrategyID`),
  CONSTRAINT `strategy` FOREIGN KEY (`StrategyID`) REFERENCES `strategy` (`ID`),
  CONSTRAINT `trade_ibfk_2` FOREIGN KEY (`ccy`) REFERENCES `currency` (`ISO`),
  CONSTRAINT `trade_ibfk_3` FOREIGN KEY (`SecurityName`) REFERENCES `security` (`NameAbbreviation`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

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
