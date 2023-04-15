-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: comunique
-- ------------------------------------------------------
-- Server version	8.0.30
create database comunique default charset utf8mb4 default collate utf8mb4_general_ci;
use comunique;
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
-- Table structure for table `admins`
--

DROP TABLE IF EXISTS `admins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admins` (
  `id_admin` binary(16) NOT NULL,
  `nome` varchar(40) NOT NULL,
  `senha` varchar(50) NOT NULL,
  `instituicao` binary(16) NOT NULL,
  PRIMARY KEY (`id_admin`),
  UNIQUE KEY `nome` (`nome`),
  KEY `instituicao` (`instituicao`),
  CONSTRAINT `admins_ibfk_1` FOREIGN KEY (`instituicao`) REFERENCES `instituicoes` (`id_instituicao`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admins`
--

LOCK TABLES `admins` WRITE;
/*!40000 ALTER TABLE `admins` DISABLE KEYS */;
/*!40000 ALTER TABLE `admins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `adminsmaster`
--

DROP TABLE IF EXISTS `adminsmaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `adminsmaster` (
  `id_admin` binary(16) NOT NULL,
  `nome` varchar(40) NOT NULL,
  `senha` varchar(50) NOT NULL,
  PRIMARY KEY (`id_admin`),
  UNIQUE KEY `nome` (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adminsmaster`
--

LOCK TABLES `adminsmaster` WRITE;
/*!40000 ALTER TABLE `adminsmaster` DISABLE KEYS */;
INSERT INTO `adminsmaster` VALUES (_binary '  e נX  \   z\r','admin','81dc9bdb52d04dc20036dbd8313ed055');
/*!40000 ALTER TABLE `adminsmaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat`
--

DROP TABLE IF EXISTS `chat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat` (
  `id_chat` binary(16) NOT NULL,
  `id_usuario1` binary(16) NOT NULL,
  `id_usuario2` binary(16) NOT NULL,
  PRIMARY KEY (`id_chat`),
  KEY `id_usuario1` (`id_usuario1`),
  KEY `id_usuario2` (`id_usuario2`),
  CONSTRAINT `chat_ibfk_1` FOREIGN KEY (`id_usuario1`) REFERENCES `usuarios` (`id_usuario`),
  CONSTRAINT `chat_ibfk_2` FOREIGN KEY (`id_usuario2`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat`
--

LOCK TABLES `chat` WRITE;
/*!40000 ALTER TABLE `chat` DISABLE KEYS */;
/*!40000 ALTER TABLE `chat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cronograma`
--

DROP TABLE IF EXISTS `cronograma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cronograma` (
  `id_cronograma` binary(16) NOT NULL,
  `atividade` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `cor` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `data_atividade` datetime(6) NOT NULL,
  `prazo` int NOT NULL,
  `id_usuario` binary(16) DEFAULT NULL,
  PRIMARY KEY (`id_cronograma`),
  KEY `FKj91e90b1n9cin93k3solf95bk` (`id_usuario`),
  CONSTRAINT `FKj91e90b1n9cin93k3solf95bk` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cronograma`
--

LOCK TABLES `cronograma` WRITE;
/*!40000 ALTER TABLE `cronograma` DISABLE KEYS */;
/*!40000 ALTER TABLE `cronograma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instituicoes`
--

DROP TABLE IF EXISTS `instituicoes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `instituicoes` (
  `id_instituicao` binary(16) NOT NULL,
  `nome` varchar(40) NOT NULL,
  `senha` varchar(40) NOT NULL,
  `senha_professores` varchar(40) NOT NULL,
  PRIMARY KEY (`id_instituicao`),
  UNIQUE KEY `nome` (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instituicoes`
--

LOCK TABLES `instituicoes` WRITE;
/*!40000 ALTER TABLE `instituicoes` DISABLE KEYS */;
INSERT INTO `instituicoes` VALUES (_binary '\n\  \ \ \  B \0','teste','dasdas','fsdaasd'),(_binary '.X9f? D y ǂ_iY','ulmzCzR','7c0b7d6802d3a99036d5ff74d6e1ac9f','c621b38dacf6c44c1ccdf2d788fb9c6d'),(_binary '7$F@ǯKx \ j  .^k','xcPUYxz','ffdd803f5b66899ddc22809d9fde3834','452e8cd73bad41b9b7ce0fbf155e702e'),(_binary 'H\ 9 Fϛ\ $ I4 ','4TqEE38','0aff65b382487b7971a68e9a32c6ddf5','a192a1b3567c039c93a6dbee677631cd'),(_binary 'O  .  H$ \ dk D F','Th3bpa5','4f1172e206f4718e0de64646a5e87393','51ac180d73e434a3ba16e8b94caa8a2b'),(_binary '\\\ t\ \ O     ٖ*t','vIQ4Mjt','d585fc1574422fbb03b4ba7def1ecd95','0e152f1937659a5cff54d5715ac7f20c'),(_binary 'i^<$5Gݳ%   fu','Gviy5Bl','0d2a0737b601c24a40fe9a5cf0753203','4da467a502a9eb112af2067c67d6996d'),(_binary 'k-\Za H   \ 3^ R-','10ycSXa','b2d3a4313bc5d269a70ca2346a545d88','1e8bb7b3e38900a0d2f05df9dab585af'),(_binary ' \'  D@p  
ً   ','W1Ctrsk','32e2a1b35d6c4ecaf30959c93d1d7d5d','c2da52dbd561d108c2570dda612982d1'),(_binary '  | ˒Kh #hJ v~','98CW4iW','4a0b5e9fbd8a777b857878784cbecee9','48b6b60c78477b9ced914f66c6256acf'),(_binary ' h\ !1 I \  {i','XkL2rop','b2f491737ece250980d735c598f319df','b4cbdf269b30bb321e4b74a8094a5eb7'),(_binary '  4/ FL4 \ ݖ\\\ /T','YKqhER6','6d19bbe648b74fcaa1ac1a58d09380b1','8495bf0eb5d12f12689c5bc92f2a880d'),(_binary ' \ ?  D  3>:5\ \ ','4k6gFMa','673cddb8c6a0e302cdb78e7b93b71666','8f476c13091bad3407e8703d8dae17e0'),(_binary ' \   @ Oq E \ \ \ \ ','kRGvtOS','e61ecc8e5253ce3613b5238fa5a811b1','44ce74f39fc0cf3d100e0a6526a17e89'),(_binary '\ \ \ jI  \ \ w\'\ \ ','f90Vrze','c240966c527aab6e3286229438e72cc2','af378cc70c99f8b9143f53863032381a'),(_binary '\  2 	Fؒ\      ','9RvkEGZ','9c357fb35238516945fe6bc4b07642c0','d982909273f8a956d8852e284a1d1549'),(_binary '\ \ c #\'H[   \ U\0\rW','ZDIA7rr','3fa09b07a7cfdc4627ead48ef8a28b04','71857026cccbfd2932b889fd3644f9ce'),(_binary '\ ~ sIL   \ ^ : ','nOXtlWf','f697ddbe805a92b5ef6e9597ad6aec10','d45d731dc1ef9517af67a56a8fb2087d');
/*!40000 ALTER TABLE `instituicoes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mensagens`
--

DROP TABLE IF EXISTS `mensagens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mensagens` (
  `id_mensagens` binary(16) NOT NULL,
  `usuario_enviou` binary(16) NOT NULL,
  `mensagem` text COLLATE utf8mb4_general_ci NOT NULL,
  `data_mensagem` datetime NOT NULL,
  `chat` binary(16) NOT NULL,
  `lida` tinyint(1) NOT NULL,
  `entregue` tinyint(1) NOT NULL,
  `isfile` tinyint(1) NOT NULL,
  `deletada` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_mensagens`),
  KEY `chat` (`chat`),
  CONSTRAINT `mensagens_ibfk_1` FOREIGN KEY (`chat`) REFERENCES `chat` (`id_chat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mensagens`
--

LOCK TABLES `mensagens` WRITE;
/*!40000 ALTER TABLE `mensagens` DISABLE KEYS */;
/*!40000 ALTER TABLE `mensagens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `noticias`
--

DROP TABLE IF EXISTS `noticias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `noticias` (
  `id_noticia` binary(16) NOT NULL,
  `titulo` varchar(100) NOT NULL,
  `imagem` varchar(100) NOT NULL,
  `texto` text NOT NULL,
  `instituicao` binary(16) NOT NULL,
  PRIMARY KEY (`id_noticia`),
  KEY `instituicao` (`instituicao`),
  CONSTRAINT `noticias_ibfk_1` FOREIGN KEY (`instituicao`) REFERENCES `instituicoes` (`id_instituicao`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `noticias`
--

LOCK TABLES `noticias` WRITE;
/*!40000 ALTER TABLE `noticias` DISABLE KEYS */;
/*!40000 ALTER TABLE `noticias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questoes`
--

DROP TABLE IF EXISTS `questoes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `questoes` (
  `id_questao` binary(16) NOT NULL,
  `Titulo` text NOT NULL,
  `Resposta1` varchar(75) NOT NULL,
  `Resposta2` varchar(75) NOT NULL,
  `Resposta3` varchar(75) NOT NULL,
  `Resposta4` varchar(75) NOT NULL,
  `Resposta_correta` varchar(75) NOT NULL,
  `instituicao` binary(16) NOT NULL,
  PRIMARY KEY (`id_questao`),
  KEY `instituicao` (`instituicao`),
  CONSTRAINT `questoes_ibfk_1` FOREIGN KEY (`instituicao`) REFERENCES `instituicoes` (`id_instituicao`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questoes`
--

LOCK TABLES `questoes` WRITE;
/*!40000 ALTER TABLE `questoes` DISABLE KEYS */;
/*!40000 ALTER TABLE `questoes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id_usuario` binary(16) NOT NULL,
  `tipo_usuario` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `nome_usuario` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(60) COLLATE utf8mb4_general_ci NOT NULL,
  `senha` varchar(60) COLLATE utf8mb4_general_ci NOT NULL,
  `foto_perfil` varchar(150) COLLATE utf8mb4_general_ci NOT NULL,
  `usuario_online` tinyint(1) NOT NULL,
  `instituicao` binary(16) NOT NULL,
  `senha_provisoria` varchar(60) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `data_senha_provisoria` datetime DEFAULT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `email` (`email`),
  KEY `instituicao` (`instituicao`),
  CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`instituicao`) REFERENCES `instituicoes` (`id_instituicao`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (_binary '\ ^ \ \ \  B \0','ALUNO','teste','teste','teste','teste',1,_binary '\n\  \ \ \  B \0','81ad554f2ca4c90e61666ef4c53f5043','2023-04-14 13:18:30');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-14 18:02:08
