-- MySQL dump 10.13  Distrib 5.7.27, for Linux (x86_64)
--
-- Host: localhost    Database: CementAgent
-- ------------------------------------------------------
-- Server version	5.7.27-0ubuntu0.18.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Agent`
--

DROP TABLE IF EXISTS `Agent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Agent` (
  `agentId` varchar(10) NOT NULL,
  `agentCode` varchar(15) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `region` varchar(20) DEFAULT NULL,
  `territory` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`agentId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Agent`
--

LOCK TABLES `Agent` WRITE;
/*!40000 ALTER TABLE `Agent` DISABLE KEYS */;
INSERT INTO `Agent` VALUES ('A001','9100227','Dilantha Traders','Southern','Kaluthara');
/*!40000 ALTER TABLE `Agent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Cement`
--

DROP TABLE IF EXISTS `Cement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Cement` (
  `cementId` varchar(10) NOT NULL,
  `name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`cementId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Cement`
--

LOCK TABLES `Cement` WRITE;
/*!40000 ALTER TABLE `Cement` DISABLE KEYS */;
INSERT INTO `Cement` VALUES ('C001','OPC'),('C002','PPC');
/*!40000 ALTER TABLE `Cement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CementAgent`
--

DROP TABLE IF EXISTS `CementAgent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CementAgent` (
  `cementId` varchar(10) NOT NULL,
  `agentId` varchar(10) NOT NULL,
  `unitPrice` decimal(6,2) DEFAULT NULL,
  PRIMARY KEY (`cementId`,`agentId`),
  KEY `agentId` (`agentId`),
  CONSTRAINT `CementAgent_ibfk_1` FOREIGN KEY (`cementId`) REFERENCES `Cement` (`cementId`),
  CONSTRAINT `CementAgent_ibfk_2` FOREIGN KEY (`agentId`) REFERENCES `Agent` (`agentId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CementAgent`
--

LOCK TABLES `CementAgent` WRITE;
/*!40000 ALTER TABLE `CementAgent` DISABLE KEYS */;
INSERT INTO `CementAgent` VALUES ('C001','A001',880.00),('C002','A001',890.00);
/*!40000 ALTER TABLE `CementAgent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CementShop`
--

DROP TABLE IF EXISTS `CementShop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CementShop` (
  `cementId` varchar(10) NOT NULL,
  `shopId` varchar(10) NOT NULL,
  `unitPrice` decimal(6,2) DEFAULT NULL,
  PRIMARY KEY (`cementId`,`shopId`),
  KEY `shopId` (`shopId`),
  CONSTRAINT `CementShop_ibfk_1` FOREIGN KEY (`cementId`) REFERENCES `Cement` (`cementId`),
  CONSTRAINT `CementShop_ibfk_2` FOREIGN KEY (`shopId`) REFERENCES `Shop` (`shopId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CementShop`
--

LOCK TABLES `CementShop` WRITE;
/*!40000 ALTER TABLE `CementShop` DISABLE KEYS */;
INSERT INTO `CementShop` VALUES ('C001','S001',955.00),('C001','S002',835.00),('C001','S003',810.00),('C001','S004',505.00),('C001','S005',835.00),('C002','S001',915.00),('C002','S002',790.00),('C002','S003',795.00),('C002','S004',480.00),('C002','S005',815.00);
/*!40000 ALTER TABLE `CementShop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ChequePayment`
--

DROP TABLE IF EXISTS `ChequePayment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ChequePayment` (
  `chequePaymentId` varchar(11) NOT NULL,
  `shopPaymentId` varchar(11) NOT NULL,
  `chequeNo` varchar(10) DEFAULT NULL,
  `dDate` date DEFAULT NULL,
  `amount` decimal(10,2) DEFAULT NULL,
  `returned` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`chequePaymentId`),
  KEY `shopPaymentId` (`shopPaymentId`),
  CONSTRAINT `ChequePayment_ibfk_1` FOREIGN KEY (`shopPaymentId`) REFERENCES `ShopPayment` (`shopPaymentId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ChequePayment`
--

LOCK TABLES `ChequePayment` WRITE;
/*!40000 ALTER TABLE `ChequePayment` DISABLE KEYS */;
INSERT INTO `ChequePayment` VALUES ('CP001','SP004','321564891','2019-10-31',25000.00,'Submitted'),('CP002','SP005','123131165','2019-11-15',30000.00,'Pending');
/*!40000 ALTER TABLE `ChequePayment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Delivery`
--

DROP TABLE IF EXISTS `Delivery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Delivery` (
  `deliveryId` varchar(11) NOT NULL,
  `purchaseOrderId` varchar(11) NOT NULL,
  `type` varchar(10) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`deliveryId`),
  KEY `purchaseOrderId` (`purchaseOrderId`),
  KEY `type` (`type`),
  CONSTRAINT `Delivery_ibfk_1` FOREIGN KEY (`purchaseOrderId`) REFERENCES `PurchaseOrder` (`purchaseOrderId`),
  CONSTRAINT `Delivery_ibfk_2` FOREIGN KEY (`type`) REFERENCES `PurchaseOrder` (`cementId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Delivery`
--

LOCK TABLES `Delivery` WRITE;
/*!40000 ALTER TABLE `Delivery` DISABLE KEYS */;
INSERT INTO `Delivery` VALUES ('D001','PO006','C001','2019-10-15'),('D002','PO005','C001','2019-10-15'),('D003','PO003','C002','2019-10-15'),('D004','PO003','C002','2019-10-15'),('D005','PO005','C001','2019-10-15'),('D006','PO004','C002','2019-10-15');
/*!40000 ALTER TABLE `Delivery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PurchaseOrder`
--

DROP TABLE IF EXISTS `PurchaseOrder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
  KEY `agentId` (`agentId`),
  KEY `cementId` (`cementId`),
  CONSTRAINT `PurchaseOrder_ibfk_1` FOREIGN KEY (`agentId`) REFERENCES `Agent` (`agentId`),
  CONSTRAINT `PurchaseOrder_ibfk_2` FOREIGN KEY (`cementId`) REFERENCES `Cement` (`cementId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PurchaseOrder`
--

LOCK TABLES `PurchaseOrder` WRITE;
/*!40000 ALTER TABLE `PurchaseOrder` DISABLE KEYS */;
INSERT INTO `PurchaseOrder` VALUES ('PO001','A001','C001','2019-10-14',400,'123465789','2019-10-28',352000.00),('PO002','A001','C001','2019-10-14',400,'987654321','2019-10-29',352000.00),('PO003','A001','C002','2019-10-14',400,'456789123','2019-10-28',356000.00),('PO004','A001','C002','2019-10-14',400,'741852963','2019-10-28',356000.00),('PO005','A001','C001','2019-10-14',400,'123123123','2019-10-30',352000.00),('PO006','A001','C001','2019-10-14',400,'111111111','2019-10-24',352000.00),('PO007','A001','C001','2019-10-14',400,'777888999','2019-10-29',352000.00),('PO008','A001','C002','2019-10-14',400,'777222888','2019-10-30',356000.00),('PO009','A001','C001','2019-10-14',400,'1122334455','2019-10-28',352000.00),('PO010','A001','C001','2019-10-14',400,'2211455453','2019-10-29',352000.00),('PO011','A001','C001','2019-10-14',400,'1231231231','2019-10-31',352000.00),('PO012','A001','C002','2019-10-14',400,'321654987','2019-10-29',356000.00),('PO013','A001','C001','2019-10-15',250,'123321456','2019-10-29',220000.00);
/*!40000 ALTER TABLE `PurchaseOrder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Shop`
--

DROP TABLE IF EXISTS `Shop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Shop` (
  `shopId` varchar(10) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `telephone` int(10) DEFAULT NULL,
  PRIMARY KEY (`shopId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Shop`
--

LOCK TABLES `Shop` WRITE;
/*!40000 ALTER TABLE `Shop` DISABLE KEYS */;
INSERT INTO `Shop` VALUES ('S001','Pande Shop','Balangoda',711223344),('S002','Pera Shop','Panadura',711234567),('S003','Dilantha Traders','Horana',771234561),('S004','Singhe Hardware','Ingiriya',774522485),('S005','Supul Hardware','Gurugoda, Horana',771235875);
/*!40000 ALTER TABLE `Shop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ShopDelivery`
--

DROP TABLE IF EXISTS `ShopDelivery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ShopDelivery` (
  `deliveryId` varchar(11) NOT NULL,
  `shopId` varchar(11) NOT NULL,
  `type` varchar(10) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `qty` int(10) DEFAULT NULL,
  PRIMARY KEY (`deliveryId`,`shopId`),
  KEY `shopId` (`shopId`),
  KEY `type` (`type`),
  CONSTRAINT `ShopDelivery_ibfk_1` FOREIGN KEY (`deliveryId`) REFERENCES `Delivery` (`deliveryId`),
  CONSTRAINT `ShopDelivery_ibfk_2` FOREIGN KEY (`shopId`) REFERENCES `Shop` (`shopId`),
  CONSTRAINT `ShopDelivery_ibfk_3` FOREIGN KEY (`type`) REFERENCES `Delivery` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ShopDelivery`
--

LOCK TABLES `ShopDelivery` WRITE;
/*!40000 ALTER TABLE `ShopDelivery` DISABLE KEYS */;
INSERT INTO `ShopDelivery` VALUES ('D004','S001','C002','2019-10-15',15),('D004','S003','C002','2019-10-15',25),('D005','S001','C001','2019-10-15',50),('D005','S002','C001','2019-10-15',30),('D006','S001','C002','2019-10-15',15);
/*!40000 ALTER TABLE `ShopDelivery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ShopOrder`
--

DROP TABLE IF EXISTS `ShopOrder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ShopOrder` (
  `shopOrderId` varchar(11) NOT NULL,
  `shopId` varchar(11) NOT NULL,
  `type` varchar(10) DEFAULT NULL,
  `oDate` date DEFAULT NULL,
  `dDate` date DEFAULT NULL,
  `qty` int(10) DEFAULT NULL,
  PRIMARY KEY (`shopOrderId`),
  KEY `shopId` (`shopId`),
  CONSTRAINT `ShopOrder_ibfk_1` FOREIGN KEY (`shopId`) REFERENCES `Shop` (`shopId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ShopOrder`
--

LOCK TABLES `ShopOrder` WRITE;
/*!40000 ALTER TABLE `ShopOrder` DISABLE KEYS */;
INSERT INTO `ShopOrder` VALUES ('SO001','S001','OPC','2019-10-15','2019-10-16',50),('SO002','S002','PPC','2019-10-15','2019-10-17',40),('SO003','S003','PPC','2019-10-15','2019-10-18',25),('SO004','S001','PPC','2019-10-15','2019-10-16',15),('SO005','S002','OPC','2019-10-15','2019-10-18',30);
/*!40000 ALTER TABLE `ShopOrder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ShopPayment`
--

DROP TABLE IF EXISTS `ShopPayment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ShopPayment` (
  `shopPaymentId` varchar(11) NOT NULL,
  `shopId` varchar(11) NOT NULL,
  `type` varchar(10) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `amount` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`shopPaymentId`),
  KEY `shopId` (`shopId`),
  CONSTRAINT `ShopPayment_ibfk_1` FOREIGN KEY (`shopId`) REFERENCES `Shop` (`shopId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ShopPayment`
--

LOCK TABLES `ShopPayment` WRITE;
/*!40000 ALTER TABLE `ShopPayment` DISABLE KEYS */;
INSERT INTO `ShopPayment` VALUES ('SP001','S002','Cash','2019-10-14',15000.00),('SP002','S002','Cash','2019-10-15',25000.00),('SP003','S001','Cash','2019-10-16',15000.00),('SP004','S002','Cheque','2019-10-16',25000.00),('SP005','S005','Cheque','2019-10-16',30000.00);
/*!40000 ALTER TABLE `ShopPayment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Worker`
--

DROP TABLE IF EXISTS `Worker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Worker` (
  `workerId` varchar(10) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `telephone` int(10) DEFAULT NULL,
  `type` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`workerId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Worker`
--

LOCK TABLES `Worker` WRITE;
/*!40000 ALTER TABLE `Worker` DISABLE KEYS */;
INSERT INTO `Worker` VALUES ('W001','Amal',711223344,'Driver'),('W002','Kamal',777123456,'Worker'),('W003','Wimal ',711245678,'Worker'),('W004','Samal',771233212,'Worker');
/*!40000 ALTER TABLE `Worker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `WorkerPayment`
--

DROP TABLE IF EXISTS `WorkerPayment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `WorkerPayment` (
  `workerPaymentId` varchar(11) NOT NULL,
  `workerId` varchar(11) NOT NULL,
  `date` date DEFAULT NULL,
  `amount` decimal(6,2) DEFAULT NULL,
  PRIMARY KEY (`workerPaymentId`),
  KEY `workerId` (`workerId`),
  CONSTRAINT `WorkerPayment_ibfk_1` FOREIGN KEY (`workerId`) REFERENCES `Worker` (`workerId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `WorkerPayment`
--

LOCK TABLES `WorkerPayment` WRITE;
/*!40000 ALTER TABLE `WorkerPayment` DISABLE KEYS */;
INSERT INTO `WorkerPayment` VALUES ('WP001','W002','2019-10-13',1500.00),('WP002','W002','2019-10-13',1200.00),('WP003','W002','2019-10-13',2500.00),('WP004','W001','2019-10-13',1000.00),('WP005','W002','2019-10-15',1000.00);
/*!40000 ALTER TABLE `WorkerPayment` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-16 11:36:29
