-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: hr-project
-- ------------------------------------------------------
-- Server version	5.6.27-log

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
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `idDepartment` int(11) NOT NULL,
  `Adress` varchar(45) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `Name` varchar(45) NOT NULL,
  PRIMARY KEY (`idDepartment`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'sjöcronas','Boss@gmail.com','VD');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employment`
--

DROP TABLE IF EXISTS `employment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employment` (
  `userlogin_SSN` int(11) NOT NULL,
  `Salary` int(11) NOT NULL,
  `Employment` varchar(45) NOT NULL,
  `Status` varchar(45) NOT NULL,
  `EmploymentDate` varchar(45) NOT NULL,
  `LastEmploymentDate` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`userlogin_SSN`),
  CONSTRAINT `fk_Salary_Login1` FOREIGN KEY (`userlogin_SSN`) REFERENCES `userlogin` (`SSN`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employment`
--

LOCK TABLES `employment` WRITE;
/*!40000 ALTER TABLE `employment` DISABLE KEYS */;
/*!40000 ALTER TABLE `employment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `performance history`
--

DROP TABLE IF EXISTS `performance history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `performance history` (
  `userlogin_SSN` int(11) NOT NULL,
  `idSkill` int(11) NOT NULL,
  `Performance history` varchar(45) NOT NULL,
  PRIMARY KEY (`userlogin_SSN`,`idSkill`),
  KEY `fk_Performance history_userlogin_has_Skills1_idx` (`idSkill`),
  CONSTRAINT `fk_Performance history_userlogin_has_Skills1` FOREIGN KEY (`idSkill`) REFERENCES `userlogin_has_skills` (`idSkill`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `performance history`
--

LOCK TABLES `performance history` WRITE;
/*!40000 ALTER TABLE `performance history` DISABLE KEYS */;
/*!40000 ALTER TABLE `performance history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personal phone`
--

DROP TABLE IF EXISTS `personal phone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personal phone` (
  `userlogin_SSN` int(11) NOT NULL,
  `PhoneNr` varchar(45) NOT NULL,
  PRIMARY KEY (`userlogin_SSN`),
  KEY `fk_Personal phone_userlogin1_idx` (`userlogin_SSN`),
  CONSTRAINT `fk_Personal phone_userlogin1` FOREIGN KEY (`userlogin_SSN`) REFERENCES `userlogin` (`SSN`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personal phone`
--

LOCK TABLES `personal phone` WRITE;
/*!40000 ALTER TABLE `personal phone` DISABLE KEYS */;
/*!40000 ALTER TABLE `personal phone` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phones`
--

DROP TABLE IF EXISTS `phones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phones` (
  `idDepartment` int(11) NOT NULL,
  `PhoneNr` varchar(45) NOT NULL,
  PRIMARY KEY (`idDepartment`),
  CONSTRAINT `fk_Phones_Department1` FOREIGN KEY (`idDepartment`) REFERENCES `department` (`idDepartment`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phones`
--

LOCK TABLES `phones` WRITE;
/*!40000 ALTER TABLE `phones` DISABLE KEYS */;
/*!40000 ALTER TABLE `phones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salary history`
--

DROP TABLE IF EXISTS `salary history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `salary history` (
  `userlogin_SSN` int(11) NOT NULL,
  `Salary` int(11) NOT NULL,
  `Date` date NOT NULL,
  PRIMARY KEY (`userlogin_SSN`),
  CONSTRAINT `fk_Salary history_Salary1` FOREIGN KEY (`userlogin_SSN`) REFERENCES `employment` (`userlogin_SSN`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salary history`
--

LOCK TABLES `salary history` WRITE;
/*!40000 ALTER TABLE `salary history` DISABLE KEYS */;
/*!40000 ALTER TABLE `salary history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `skills`
--

DROP TABLE IF EXISTS `skills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `skills` (
  `idSkill` int(11) NOT NULL AUTO_INCREMENT,
  `Skill` varchar(45) NOT NULL,
  `Level` varchar(45) NOT NULL,
  `Skillcategory` varchar(45) NOT NULL,
  PRIMARY KEY (`idSkill`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `skills`
--

LOCK TABLES `skills` WRITE;
/*!40000 ALTER TABLE `skills` DISABLE KEYS */;
/*!40000 ALTER TABLE `skills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `timestamp`
--

DROP TABLE IF EXISTS `timestamp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `timestamp` (
  `userlogin_SSN` int(11) NOT NULL,
  `Start` varchar(45) DEFAULT NULL,
  `Stop` varchar(45) DEFAULT NULL,
  `Working day` date NOT NULL,
  PRIMARY KEY (`userlogin_SSN`),
  CONSTRAINT `fk_Timestamp_userlogin1` FOREIGN KEY (`userlogin_SSN`) REFERENCES `userlogin` (`SSN`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `timestamp`
--

LOCK TABLES `timestamp` WRITE;
/*!40000 ALTER TABLE `timestamp` DISABLE KEYS */;
/*!40000 ALTER TABLE `timestamp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userlogin`
--

DROP TABLE IF EXISTS `userlogin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userlogin` (
  `SSN` int(11) NOT NULL,
  `idDepartment` int(11) NOT NULL,
  `Password` varchar(45) NOT NULL,
  `Access` varchar(45) NOT NULL,
  `Name` varchar(45) NOT NULL,
  `Lastname` varchar(45) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `Adress` varchar(45) NOT NULL,
  PRIMARY KEY (`SSN`,`idDepartment`),
  KEY `fk_Login_Department1_idx` (`idDepartment`),
  CONSTRAINT `fk_Login_Department1` FOREIGN KEY (`idDepartment`) REFERENCES `department` (`idDepartment`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userlogin`
--

LOCK TABLES `userlogin` WRITE;
/*!40000 ALTER TABLE `userlogin` DISABLE KEYS */;
INSERT INTO `userlogin` VALUES (1111111111,1,'admin','Admin','Martin',' Månsson','hr-project@gmail.com','sjöcronas'),(1112223333,1,'martin','user','Martin','Månsson','mackan_4000@hotmail.com','lägervägen'),(1212123333,1,'shpat','user','Shpat','Krasniqi','shpat@live.se','tollarp?');
/*!40000 ALTER TABLE `userlogin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userlogin_has_skills`
--

DROP TABLE IF EXISTS `userlogin_has_skills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userlogin_has_skills` (
  `userlogin_SSN` int(11) NOT NULL,
  `idSkill` int(11) NOT NULL,
  `Experience` varchar(45) NOT NULL,
  `Performance` varchar(45) NOT NULL,
  PRIMARY KEY (`userlogin_SSN`,`idSkill`),
  KEY `fk_userlogin_has_Skills_userlogin1_idx` (`userlogin_SSN`),
  KEY `fk_userlogin_has_Skills_Skills1_idx` (`idSkill`),
  CONSTRAINT `fk_userlogin_has_Skills_Skills1` FOREIGN KEY (`idSkill`) REFERENCES `skills` (`idSkill`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_userlogin_has_Skills_userlogin1` FOREIGN KEY (`userlogin_SSN`) REFERENCES `userlogin` (`SSN`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userlogin_has_skills`
--

LOCK TABLES `userlogin_has_skills` WRITE;
/*!40000 ALTER TABLE `userlogin_has_skills` DISABLE KEYS */;
/*!40000 ALTER TABLE `userlogin_has_skills` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-24 16:46:35
