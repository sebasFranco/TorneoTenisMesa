CREATE DATABASE  IF NOT EXISTS `torneotenismesa` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `torneotenismesa`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: torneotenismesa
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.16-MariaDB

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
-- Table structure for table `estructura`
--

DROP TABLE IF EXISTS `estructura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estructura` (
  `idEstructura` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`idEstructura`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estructura`
--

LOCK TABLES `estructura` WRITE;
/*!40000 ALTER TABLE `estructura` DISABLE KEYS */;
INSERT INTO `estructura` VALUES (1,'Arbol'),(2,'Cuadros');
/*!40000 ALTER TABLE `estructura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partido`
--

DROP TABLE IF EXISTS `partido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `partido` (
  `idPartido` int(11) NOT NULL AUTO_INCREMENT,
  `fechaHora` datetime NOT NULL,
  `idTorneo` int(11) NOT NULL,
  `idPartidoTorneo` int(11) NOT NULL,
  PRIMARY KEY (`idPartido`),
  KEY `fkPartidoTorneo_idx` (`idTorneo`),
  CONSTRAINT `fkPartidoTorneo` FOREIGN KEY (`idTorneo`) REFERENCES `torneo` (`idTorneo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partido`
--

LOCK TABLES `partido` WRITE;
/*!40000 ALTER TABLE `partido` DISABLE KEYS */;
INSERT INTO `partido` VALUES (1,'2017-04-23 16:00:00',1,1),(2,'2017-04-23 17:00:00',1,2);
/*!40000 ALTER TABLE `partido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `torneo`
--

DROP TABLE IF EXISTS `torneo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `torneo` (
  `idTorneo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `idEstructura` int(11) NOT NULL,
  `cantidadJugadores` int(11) NOT NULL,
  `cantidadMesas` int(11) DEFAULT NULL,
  PRIMARY KEY (`idTorneo`),
  KEY `fkEstructuraTorneo_idx` (`idEstructura`),
  CONSTRAINT `fkEstructuraTorneo` FOREIGN KEY (`idEstructura`) REFERENCES `estructura` (`idEstructura`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `torneo`
--

LOCK TABLES `torneo` WRITE;
/*!40000 ALTER TABLE `torneo` DISABLE KEYS */;
INSERT INTO `torneo` VALUES (1,'test',1,12,34),(2,'test',1,11,12),(3,'test1',1,2,2),(4,'test',1,11,22),(5,'test',1,1,2),(6,'test',1,1,2),(7,'test',1,11,22),(8,'test',1,1,2),(9,'test',1,11,22),(10,'test',1,11,22),(11,'test',1,11,22),(12,'test',1,12,12),(13,'ttt',1,1,2),(14,'prueba',1,11,22);
/*!40000 ALTER TABLE `torneo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(64) NOT NULL,
  `apellido` varchar(64) NOT NULL,
  `cedula` varchar(45) NOT NULL,
  `estado` tinyint(1) NOT NULL,
  `nombreUsuario` varchar(45) NOT NULL,
  `clave` varchar(45) NOT NULL,
  `tipo` enum('Administrador','Jugador','Arbitro','Apostador') NOT NULL,
  `telefono` varchar(64) NOT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'admin','admin','12345678',1,'admin','admin','Administrador','1234565699'),(2,'Fredy AA','Aunta','12345',1,'Fredy A Aunta Mll','12345','Administrador','1234567'),(3,'Fredy A','M','123',1,'Fredy A Aunta M','123','Jugador','3163980851'),(4,'','','123456789',3,'sdfgdfgd','123456789','Administrador','76543'),(5,'','','45678',3,'dfghnjm','45678','Administrador','2123456'),(6,'fghjk','vbnmk,','45678',4,'fghjkl','45678','Apostador','876543'),(7,'rtyujdd','vbn','gbn',1,'fff','fff','Jugador','1234567'),(8,'Arbitro S','Arbitro A','123456',1,'arb','123456','Arbitro','1234567'),(9,'a','a','1',1,'11','11','Apostador','1'),(10,'aa','aa','11',1,'qq','qq','Jugador','11');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuariopartido`
--

DROP TABLE IF EXISTS `usuariopartido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuariopartido` (
  `idPartido` int(11) NOT NULL,
  `idUsuario` int(11) NOT NULL,
  `resultado` int(11) DEFAULT NULL,
  PRIMARY KEY (`idPartido`,`idUsuario`),
  KEY `fk_Partido_has_Usuario_Usuario1_idx` (`idUsuario`),
  KEY `fk_Partido_has_Usuario_Partido1_idx` (`idPartido`),
  CONSTRAINT `fk_Partido_has_Usuario_Partido1` FOREIGN KEY (`idPartido`) REFERENCES `partido` (`idPartido`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Partido_has_Usuario_Usuario1` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuariopartido`
--

LOCK TABLES `usuariopartido` WRITE;
/*!40000 ALTER TABLE `usuariopartido` DISABLE KEYS */;
INSERT INTO `usuariopartido` VALUES (1,3,NULL),(1,7,NULL),(1,8,NULL),(2,7,NULL),(2,8,NULL),(2,10,NULL);
/*!40000 ALTER TABLE `usuariopartido` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-24 19:48:15


--Se agrega la tabla de apuestas
--drop table apuesta

create table apuesta
(
idApuesta int(11) not null auto_increment, 
idUsuario int(11) not null, 
idPartido int(11) not null,
estado enum('Abierta','Cerrada','Anulada'), 
valor int(20), 
fechaCreacion timestamp, 
fechaCierre datetime,
ganador varchar(50),
puntaje varchar(50),
PRIMARY KEY (idApuesta),
CONSTRAINT fkApuestaUsuario FOREIGN KEY (idUsuario) REFERENCES usuario (idUsuario) ON DELETE NO ACTION ON UPDATE NO ACTION,
CONSTRAINT fkApuestaPartido FOREIGN KEY (idPartido) REFERENCES partido (idPartido) ON DELETE NO ACTION ON UPDATE NO ACTION
);




delimiter ;
drop trigger cu12_asignacionPremio
;
/*Trigger para el CU AT-12*/


delimiter //
CREATE TRIGGER cu12_asignacionPremio BEFORE UPDATE ON usuariopartido
FOR EACH ROW
BEGIN
   IF NEW.resultado is not null THEN
	/*Cierre de todos los partidos asociados en las apuestas*/
	   update apuesta set fechaCierre = current_timestamp, estado = 'Cerrada' where idPartido = NEW.idPartido;
   END IF;
END;//
delimiter ;