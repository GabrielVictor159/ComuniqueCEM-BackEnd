-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: comunique
-- ------------------------------------------------------
-- Server version	8.0.32

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
Create Database comunique Default Charset utf8mb4 Default Collate utf8m4_general_ci;
use comunique;
DROP TABLE IF EXISTS `admins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admins` (
  `id_admin` binary(16) NOT NULL,
  `nome` varchar(40) COLLATE utf8mb4_general_ci NOT NULL,
  `senha` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
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
  `nome` varchar(40) COLLATE utf8mb4_general_ci NOT NULL,
  `senha` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id_admin`),
  UNIQUE KEY `nome` (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adminsmaster`
--

LOCK TABLES `adminsmaster` WRITE;
/*!40000 ALTER TABLE `adminsmaster` DISABLE KEYS */;
INSERT INTO `adminsmaster` VALUES (_binary '  e נX     z\r','admin','81dc9bdb52d04dc20036dbd8313ed055');
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
  `nome` varchar(40) COLLATE utf8mb4_general_ci NOT NULL,
  `senha` varchar(40) COLLATE utf8mb4_general_ci NOT NULL,
  `senha_professores` varchar(40) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id_instituicao`),
  UNIQUE KEY `nome` (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instituicoes`
--

LOCK TABLES `instituicoes` WRITE;
/*!40000 ALTER TABLE `instituicoes` DISABLE KEYS */;
INSERT INTO `instituicoes` VALUES (_binary 't٘\ D\  B \0','teste','65a74ae52043d252b8cf82edbe0d1878','2dcbf9e39d332dbf62df3b1524f8d755');
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
  `mensagem` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
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
  `titulo` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `imagem` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `texto` text COLLATE utf8mb4_general_ci NOT NULL,
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
  `Titulo` text COLLATE utf8mb4_general_ci NOT NULL,
  `Resposta1` varchar(75) COLLATE utf8mb4_general_ci NOT NULL,
  `Resposta2` varchar(75) COLLATE utf8mb4_general_ci NOT NULL,
  `Resposta3` varchar(75) COLLATE utf8mb4_general_ci NOT NULL,
  `Resposta4` varchar(75) COLLATE utf8mb4_general_ci NOT NULL,
  `Resposta_correta` varchar(75) COLLATE utf8mb4_general_ci NOT NULL,
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
  `tipo_usuario` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `nome_usuario` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `senha` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `foto_perfil` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `usuario_online` tinyint(1) NOT NULL,
  `instituicao` binary(16) NOT NULL,
  `senha_provisoria` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
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
INSERT INTO `usuarios` VALUES (_binary '  9 \ ^HM  
g) ~\ ','PROFESSOR','testando','gabrielvictor159487@gmail.com','1641010d54565fea92e8546cfbc49705','userIcon.png',1,_binary 't٘\ D\  B \0',NULL,NULL);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios_solicitacoes`
--

DROP TABLE IF EXISTS `usuarios_solicitacoes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios_solicitacoes` (
  `id` binary(16) NOT NULL,
  `tipo_usuario` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `nome_usuario` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `senha` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `foto_perfil` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `usuario_online` tinyint(1) NOT NULL,
  `instituicao` binary(16) NOT NULL,
  `senha_provisoria` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `data_senha_provisoria` datetime DEFAULT NULL,
  `data_solicitacao` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  KEY `instituicao` (`instituicao`),
  CONSTRAINT `usuarios_solicitacoes_ibfk_1` FOREIGN KEY (`instituicao`) REFERENCES `instituicoes` (`id_instituicao`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios_solicitacoes`
--

LOCK TABLES `usuarios_solicitacoes` WRITE;
/*!40000 ALTER TABLE `usuarios_solicitacoes` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuarios_solicitacoes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-04  9:49:13
