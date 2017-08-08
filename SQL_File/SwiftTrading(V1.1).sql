
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
  `ISIN` varchar(12) NOT NULL,
  `SecurityName` varchar(30) NOT NULL,
  `NameAbbreviation` varchar(20) NOT NULL,
  `isFavorite` int(2) NOT NULL DEFAULT '0' COMMENT 'dislike',
  PRIMARY KEY (`ISIN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- ----------------------------
-- Table structure for `strategy`
-- ----------------------------
DROP TABLE IF EXISTS `strategy`;
CREATE TABLE `strategy` (
  `ID` int(11) NOT NULL,
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
  `ID` int(11) NOT NULL,
  `Code` varchar(12) NOT NULL,
  `Quantity` bigint(20) NOT NULL,
  `Price` double NOT NULL DEFAULT '0' COMMENT '0 represents no deal',
  `Expiration` char(30) NOT NULL,
  `StartTime` datetime NOT NULL,
  `EndTime` datetime NOT NULL,
  `Status` int(3) NOT NULL,
  `LossPrice` double NOT NULL,
  `ProfitPrice` double NOT NULL,
  `TradeType` char(2) NOT NULL,
  `ccy` char(3) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `Code` (`Code`),
  KEY `ccy` (`ccy`),
  CONSTRAINT `trade_ibfk_1` FOREIGN KEY (`Code`) REFERENCES `security` (`ISIN`),
  CONSTRAINT `trade_ibfk_2` FOREIGN KEY (`ccy`) REFERENCES `currency` (`ISO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `ID` int(11) NOT NULL,
  `Name` varchar(30) NOT NULL,
  `Email` varchar(30) NOT NULL,
  `PhoneNumber` char(11) NOT NULL,
  `Address` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

