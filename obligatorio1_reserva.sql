-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: obligatorio1
-- ------------------------------------------------------
-- Server version	8.0.39

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `reserva`
--

DROP TABLE IF EXISTS `reserva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reserva` (
  `idReserva` int NOT NULL,
  `idhuesped` int DEFAULT NULL,
  `idhabitacion` int DEFAULT NULL,
  `fechaReserva` date DEFAULT NULL,
  `fechaEntrada` date DEFAULT NULL,
  `fechaSalida` date DEFAULT NULL,
  `pagada` tinyint(1) DEFAULT '0',
  `costoTotal` decimal(10,2) DEFAULT '0.00',
  PRIMARY KEY (`idReserva`),
  KEY `idhuesped` (`idhuesped`),
  KEY `idhabitacion` (`idhabitacion`),
  CONSTRAINT `reserva_ibfk_1` FOREIGN KEY (`idhuesped`) REFERENCES `huesped` (`idhuesped`),
  CONSTRAINT `reserva_ibfk_2` FOREIGN KEY (`idhabitacion`) REFERENCES `habitacion` (`idHabitacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserva`
--

LOCK TABLES `reserva` WRITE;
/*!40000 ALTER TABLE `reserva` DISABLE KEYS */;
INSERT INTO `reserva` VALUES (1,1,1,'2024-10-26','2024-11-01','2024-11-06',1,0.00),(2,1,1,'2024-10-30','2024-10-30','2024-11-01',0,0.00),(3,1,2,'2024-11-01','2024-11-01','2024-11-02',0,0.00),(5,1,2,'2024-10-30','2024-11-01','2024-11-03',0,0.00),(6,1,2,'2024-10-30','2024-11-01','2024-11-10',0,15320.00),(7,1,2,'2024-10-30','2024-11-30','2024-12-30',1,15320.00),(20,1,1,'2024-10-31','2024-11-30','2024-12-15',1,0.00),(24,1,1,'2024-10-31','2024-10-30','2024-11-01',1,6200.00),(29,1,1,'2024-11-11','2024-11-11','2024-11-12',0,0.00),(33,1,1,'2024-10-31','2024-11-01','2024-12-01',0,0.00),(54,1,1,'2024-11-11','2024-11-11','2024-11-15',1,175.00);
/*!40000 ALTER TABLE `reserva` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-04 22:42:21
