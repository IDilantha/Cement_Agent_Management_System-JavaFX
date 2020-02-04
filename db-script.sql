
CREATE TABLE `Agent` (
  `agentId` varchar(10) NOT NULL,
  `agentCode` varchar(15) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `region` varchar(20) DEFAULT NULL,
  `territory` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`agentId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `Shop` (
  `shopId` varchar(10) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `telephone` int(10) DEFAULT NULL,
  PRIMARY KEY (`shopId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `Worker` (
  `workerId` varchar(10) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `telephone` int(10) DEFAULT NULL,
  `type` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`workerId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `Cement` (
  `cementId` varchar(10) NOT NULL,
  `name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`cementId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `CementAgent` (
  `cementId` varchar(10) NOT NULL,
  `agentId` varchar(10) NOT NULL,
  `unitPrice` decimal(6,2) DEFAULT NULL,
  PRIMARY KEY (`cementId`,`agentId`),
  CONSTRAINT FOREIGN KEY (`cementId`) REFERENCES `Cement` (`cementId`),
  CONSTRAINT FOREIGN KEY (`agentId`) REFERENCES `Agent` (`agentId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `CementShop` (
  `cementId` varchar(10) NOT NULL,
  `shopId` varchar(10) NOT NULL,
  `unitPrice` decimal(6,2) DEFAULT NULL,
  PRIMARY KEY (`cementId`,`shopId`),
  CONSTRAINT FOREIGN KEY (`cementId`) REFERENCES `Cement` (`cementId`),
  CONSTRAINT FOREIGN KEY (`shopId`) REFERENCES `Shop` (`shopId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `ShopOrder` (
  `shopOrderId` varchar(11) NOT NULL,
  `shopId` varchar(11) NOT NULL,
  `type` varchar(10) DEFAULT NULL,
  `oDate` date DEFAULT NULL,
  `dDate` date DEFAULT NULL,
  `qty` int(10) DEFAULT NULL,
  PRIMARY KEY (`shopOrderId`),
  CONSTRAINT FOREIGN KEY (`shopId`) REFERENCES `Shop` (`shopId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `PurchaseOrder` (
  `purchaseOrderId` varchar(11) NOT NULL,
  `agentId` varchar(11) NOT NULL,
  `cementId` varchar(11) NOT NULL,
  `oDate` date DEFAULT NULL,
  `qty` int(10) DEFAULT NULL,
  `chequeNumber` varchar(15) DEFAULT NULL,
  `dDate` date DEFAULT NULL,
  `amount` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`purchaseOrderId`),
  CONSTRAINT FOREIGN KEY (`agentId`) REFERENCES `Agent` (`agentId`),
  CONSTRAINT FOREIGN KEY (`cementId`) REFERENCES `Cement` (`cementId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `Delivery` (
  `deliveryId` varchar(11) NOT NULL,
  `purchaseOrderId` varchar(11) NOT NULL,
  `type` varchar(10) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`deliveryId`),
  CONSTRAINT FOREIGN KEY (`purchaseOrderId`) REFERENCES `PurchaseOrder` (`purchaseOrderId`),
  CONSTRAINT FOREIGN KEY (`type`) REFERENCES `PurchaseOrder` (`cementId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `ShopDelivery` (
  `deliveryId` varchar(11) NOT NULL,
  `shopId` varchar(11) NOT NULL,
  `type` varchar(10) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `qty` int(10) DEFAULT NULL,
  PRIMARY KEY (`deliveryId`,`shopId`),
  CONSTRAINT FOREIGN KEY (`deliveryId`) REFERENCES `Delivery` (`deliveryId`),
  CONSTRAINT FOREIGN KEY (`shopId`) REFERENCES `Shop` (`shopId`),
  CONSTRAINT FOREIGN KEY (`type`) REFERENCES `Delivery` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `ShopPayment` (
  `shopPaymentId` varchar(11) NOT NULL,
  `shopId` varchar(11) NOT NULL,
  `type` varchar(10) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `amount` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`shopPaymentId`),
  CONSTRAINT FOREIGN KEY (`shopId`) REFERENCES `Shop` (`shopId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `ChequePayment` (
  `chequePaymentId` varchar(11) NOT NULL,
  `shopPaymentId` varchar(11) NOT NULL,
  `chequeNo` varchar(10) DEFAULT NULL,
  `dDate` date DEFAULT NULL,
  `amount` decimal(10,2) DEFAULT NULL,
  `returned` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`chequePaymentId`),
  CONSTRAINT FOREIGN KEY (`shopPaymentId`) REFERENCES `ShopPayment` (`shopPaymentId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `WorkerPayment` (
  `workerPaymentId` varchar(11) NOT NULL,
  `workerId` varchar(11) NOT NULL,
  `date` date DEFAULT NULL,
  `amount` decimal(6,2) DEFAULT NULL,
  PRIMARY KEY (`workerPaymentId`),
  CONSTRAINT FOREIGN KEY (`workerId`) REFERENCES `Worker` (`workerId`)  
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


