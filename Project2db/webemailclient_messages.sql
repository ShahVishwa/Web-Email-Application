CREATE DATABASE  IF NOT EXISTS `webemailclient` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `webemailclient`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: webemailclient
-- ------------------------------------------------------
-- Server version	5.5.39

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
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messages` (
  `messageID` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `date` datetime NOT NULL,
  `userId` int(11) DEFAULT NULL,
  `email_status` varchar(255) DEFAULT NULL,
  `receiver` varchar(255) DEFAULT NULL,
  `sender` varchar(255) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`messageID`),
  UNIQUE KEY `date_UNIQUE` (`date`),
  KEY `FK_jq6nsa94has6a72i0d94hjg6d` (`userId`),
  CONSTRAINT `FK_jq6nsa94has6a72i0d94hjg6d` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=1111 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (2,'<p dir=\"ltr\">Test</p>\r\n','2015-04-21 02:54:58',1,'INBOX','emailclient2015@gmail.com','Yash Sehgal <yashscool@gmail.com>','Hi'),(3,'<p dir=\"ltr\">Hi, </p>\r\n<p dir=\"ltr\">I&#39;m trying your email client, I think it&#39;s amazing, finally I have one place to access all of my unofficial email address. </p>\r\n<p dir=\"ltr\">Thank you guys, keep up the work. </p>\r\n<p dir=\"ltr\">Sincerely, </p>\r','2015-04-23 20:48:47',1,'INBOX','Email Client <Emailclient2015@gmail.com>','Abdulmnem Ben aiad <benaiad@gmail.com>','Where are you'),(4,'<div dir=\"ltr\"><br></div>\r\n','2015-04-17 07:25:00',1,'Drafts','abd:;','Email Client <emailclient2015@gmail.com>',NULL),(5,'<div dir=\"ltr\">okay</div>\r\n','2015-04-17 07:25:52',1,'Drafts',NULL,'Email Client <emailclient2015@gmail.com>','okay'),(6,'<div dir=\"ltr\">testing</div>\r\n','2015-04-17 07:26:11',1,'Sent Mail','dalalk21@gmail.com','Email Client <emailclient2015@gmail.com>',NULL),(7,'test','2015-04-20 03:40:49',1,'Sent Mail','shubhampats@gmail.com','emailclient2015@gmail.com','test'),(8,'testing...','2015-04-20 23:20:34',1,'Sent Mail','vish.240493@gmail.com','emailclient2015@gmail.com','vishwa test'),(9,'','2015-04-22 09:14:46',1,'Sent Mail','benaiad@gmail.com','emailclient2015@gmail.com','Naeem'),(10,'','2015-04-22 09:16:00',1,'Sent Mail','dalalk21@gmail.com','emailclient2015@gmail.com','GM'),(11,'test2','2015-04-22 13:31:22',1,'Sent Mail','vish.240493@gmail.com','emailclient2015@gmail.com','test2@gmail.com'),(12,'test2','2015-04-22 13:31:49',1,'Sent Mail','vish.240493@gmail.com','emailclient2015@gmail.com','test2@gmail.com'),(13,'test','2015-04-22 19:49:35',1,'Sent Mail','yashvsehgal@gmail.com','emailclient2015@gmail.com','test'),(14,'test','2015-04-22 19:59:45',1,'Sent Mail','yashvsehgal@gmail.com','emailclient2015@gmail.com','test'),(15,'wr','2015-04-22 20:27:20',1,'Sent Mail','yashvsehgal@gmail.com','emailclient2015@gmail.com','ty'),(16,'velocity test','2015-04-23 13:41:18',1,'Sent Mail','vish.240493@gmail.com','emailclient2015@gmail.com','test'),(17,'velocity test2','2015-04-23 13:47:08',1,'Sent Mail','vish.240493@gmail.com','emailclient2015@gmail.com','test'),(18,'','2015-04-23 14:20:08',1,'Sent Mail','shruti.chanage09@gmail.com','emailclient2015@gmail.com','hi'),(19,'test2','2015-04-23 15:40:44',1,'Sent Mail','vish.240493@gmail.com','emailclient2015@gmail.com','test'),(20,'test2','2015-04-23 15:42:16',1,'Sent Mail','vish.240493@gmail.com','emailclient2015@gmail.com','test'),(21,'','2015-04-23 17:16:12',1,'Sent Mail','emailclient2015@yahoo.com','emailclient2015@gmail.com','hey from gmail'),(22,'test3','2015-04-23 19:47:05',1,'Sent Mail','vish.240493@gmail.com','emailclient2015@gmail.com','test'),(23,'','2015-04-24 00:00:57',1,'Sent Mail','mehta_199312@yahoo.com','emailclient2015@gmail.com',NULL),(24,'','2015-04-24 00:21:15',1,'Sent Mail','jaiminrptl@gmail.com','emailclient2015@gmail.com','hey hey hey'),(25,'test','2015-04-24 05:45:20',1,'Sent Mail','vish.240493@gmail.com','emailclient2015@gmail.com','test'),(26,'','2015-04-24 11:35:57',1,'Sent Mail','dalalk21@gmail.com','emailclient2015@gmail.com','hi'),(27,'.jlgfg','2015-04-24 11:53:11',1,'Sent Mail','shubhampats@gmail.com','emailclient2015@gmail.com','ljvfg'),(28,'mhf','2015-04-24 12:20:25',1,'Sent Mail','shubhampats@gmail.com','emailclient2015@gmail.com','mhf'),(29,'.kjbg.jvg','2015-04-24 14:31:21',1,'Sent Mail','shubhampats@gmail.com','emailclient2015@gmail.com','test4'),(30,'.kjbg.jvg','2015-04-24 14:34:17',1,'Sent Mail','shubhampats@gmail.com','emailclient2015@gmail.com','test4'),(31,',jvgn,hgku','2015-04-24 14:34:42',1,'Sent Mail','shubhampats@gmail.com','emailclient2015@gmail.com','mhcfmgnd'),(32,'bmcvmbc','2015-04-24 14:41:50',1,'Sent Mail','shubhampats@gmail.com','emailclient2015@gmail.com',',hf'),(33,'test mail from application','2015-04-24 14:45:27',1,'Sent Mail','puneetspathak@gmail.com','emailclient2015@gmail.com','test mail'),(34,'hi<span></span>\r\n','2015-04-17 06:15:39',1,'Trash','emailclient2015@gmail.com','Karishma Dalal <dalalk21@gmail.com>','Hi'),(35,'','2015-04-17 05:40:47',1,'Trash','emailclient2015@gmail.com','Karishma Dalal <dalalk21@gmail.com>','Hey you!'),(36,'Hello, this is sample for to check send email using JavaMailAPI ','2015-04-15 18:03:13',1,'Trash','emailclient2015@gmail.com','shubhampats@gmail.com','Testing Subject'),(37,'Hello, this is sample for to check send email using JavaMailAPI ','2015-04-13 22:26:48',1,'Trash','emailclient2015@gmail.com','shubhampats@gmail.com','Testing Subject'),(38,'','2015-04-13 22:24:43',1,'Trash','emailclient2015@gmail.com','Karishma Dalal <dalalk21@gmail.com>','hey!'),(944,'new test','2015-04-24 16:22:04',1,'Sent Mail','vish.240493@gmail.com','emailclient2015@gmail.com','test'),(1066,'Web Email Client test','2015-04-24 16:34:48',1,'Sent Mail','mananjayeshshah@gmail.com','emailclient2015@gmail.com','test');
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-04-24 16:46:58
