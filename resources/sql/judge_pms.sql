-- MySQL dump 10.13  Distrib 5.7.36, for Linux (x86_64)
--
-- Host: localhost    Database: judge_pms
-- ------------------------------------------------------
-- Server version	5.7.36

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
-- Table structure for table `pms_collection_group`
--

CREATE DATABASE IF NOT EXISTS `judge_pms`;
use judge_pms;
DROP TABLE IF EXISTS `pms_collection_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pms_collection_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `collection_id` bigint(20) DEFAULT NULL COMMENT '集合id',
  `group_id` bigint(20) DEFAULT NULL COMMENT '群组id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pms_collection_group`
--

LOCK TABLES `pms_collection_group` WRITE;
/*!40000 ALTER TABLE `pms_collection_group` DISABLE KEYS */;
INSERT INTO `pms_collection_group` VALUES (3,4,15);
/*!40000 ALTER TABLE `pms_collection_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pms_judge_result`
--

DROP TABLE IF EXISTS `pms_judge_result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pms_judge_result` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `submit_id` bigint(20) unsigned DEFAULT NULL COMMENT '对应的提交id',
  `judge_status` tinyint(4) DEFAULT NULL COMMENT '提交状态',
  `execution_time` bigint(20) DEFAULT NULL COMMENT '执行时常',
  `error_output` text COLLATE utf8mb4_unicode_ci COMMENT '错误信息',
  `success` bit(1) DEFAULT NULL COMMENT '是否成功',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pms_judge_result`
--

LOCK TABLES `pms_judge_result` WRITE;
/*!40000 ALTER TABLE `pms_judge_result` DISABLE KEYS */;
INSERT INTO `pms_judge_result` VALUES (1,3,8,90,NULL,_binary '\0'),(2,4,8,8,NULL,_binary '\0'),(3,5,8,108,NULL,_binary '\0'),(4,6,8,0,NULL,_binary '\0'),(5,7,8,16,NULL,_binary '\0'),(6,8,6,3,NULL,_binary ''),(7,9,7,0,NULL,_binary '\0'),(8,11,8,3,NULL,_binary '\0'),(9,12,8,0,NULL,_binary '\0'),(10,13,8,0,NULL,_binary '\0'),(11,14,8,0,NULL,_binary '\0'),(12,15,8,30,NULL,_binary '\0'),(13,16,8,0,NULL,_binary '\0'),(14,17,6,30,NULL,_binary '');
/*!40000 ALTER TABLE `pms_judge_result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pms_problem`
--

DROP TABLE IF EXISTS `pms_problem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pms_problem` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '问题名称',
  `uid` bigint(10) unsigned DEFAULT NULL COMMENT '上传人',
  `problem_describe` text COLLATE utf8mb4_unicode_ci COMMENT '问题描述',
  `input` text COLLATE utf8mb4_unicode_ci COMMENT '输入',
  `output` text COLLATE utf8mb4_unicode_ci COMMENT '输出',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` bit(1) DEFAULT b'1' COMMENT '状态',
  `input_describle` text COLLATE utf8mb4_unicode_ci COMMENT '输入描述',
  `output_describle` text COLLATE utf8mb4_unicode_ci COMMENT '输出描述',
  `collection_id` bigint(20) unsigned DEFAULT NULL COMMENT '所属合集',
  `share` bit(1) DEFAULT NULL COMMENT '是否与其他集合共享',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pms_problem`
--

LOCK TABLES `pms_problem` WRITE;
/*!40000 ALTER TABLE `pms_problem` DISABLE KEYS */;
INSERT INTO `pms_problem` VALUES (1,'A + B问题',1,'a + b 问题。给你两个数，求出两个数的和。','199 1','200','2021-11-08 15:21:05','2021-11-08 15:21:05',_binary '','a，b的长度在1~10000之间。','输入两个数的和。',1,_binary ''),(3,'测试',NULL,'奥术大师多','阿萨德dddd','阿达','2021-11-17 18:51:17','2021-11-17 18:57:23',_binary '','奥术大师多sdsdfsdsdfsdfassddtg','奥术大师多ddd',2,NULL),(4,'问题2',1,'问题2','问题2','问题2','2021-11-18 00:06:19','2021-11-18 00:06:19',_binary '','问题2','问题2',2,NULL),(5,'问题哦',1,'问题哦','问题哦','问题哦','2021-11-18 00:59:59','2021-11-18 00:59:59',_binary '','问题哦','问题哦',2,NULL),(6,'添加测试',3,'呵呵呵呵','1','1','2021-11-21 13:40:57','2021-11-21 13:40:57',_binary '','呵呵呵呵','asdasd',4,NULL),(7,'呵呵呵呵',3,'奥术大师多','啊上帝','阿萨德','2021-11-23 14:12:50','2021-11-23 14:12:50',_binary '\0','阿萨德','阿萨德',4,NULL),(8,'123',3,'奥术大师多','阿萨德','阿萨德','2021-11-23 14:12:58','2021-11-23 14:12:58',_binary '\0','阿萨德','阿萨德',4,NULL);
/*!40000 ALTER TABLE `pms_problem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pms_problem_collection`
--

DROP TABLE IF EXISTS `pms_problem_collection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pms_problem_collection` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` bit(1) DEFAULT NULL COMMENT '是否启用',
  `create_user_id` bigint(20) unsigned DEFAULT NULL COMMENT '创建人',
  `public_collection` bit(1) DEFAULT NULL COMMENT '是否公开集',
  `begin_time` datetime DEFAULT NULL COMMENT '开启时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pms_problem_collection`
--

LOCK TABLES `pms_problem_collection` WRITE;
/*!40000 ALTER TABLE `pms_problem_collection` DISABLE KEYS */;
INSERT INTO `pms_problem_collection` VALUES (1,'基础','2021-11-17 16:41:49','2021-11-17 16:41:49',_binary '',1,_binary '','2021-11-08 07:18:24','2021-11-07 07:18:25'),(4,'18计算机科学与技术一班','2021-11-20 15:04:13','2021-11-20 15:04:13',_binary '',NULL,_binary '\0','2021-11-20 15:04:13','2021-11-20 15:04:13');
/*!40000 ALTER TABLE `pms_problem_collection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pms_problem_collection_item`
--

DROP TABLE IF EXISTS `pms_problem_collection_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pms_problem_collection_item` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `collection_id` bigint(20) unsigned DEFAULT NULL COMMENT '所属集合',
  `problem_id` bigint(20) unsigned DEFAULT NULL COMMENT '问题id',
  `status` bit(1) DEFAULT b'1' COMMENT '是否启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pms_problem_collection_item`
--

LOCK TABLES `pms_problem_collection_item` WRITE;
/*!40000 ALTER TABLE `pms_problem_collection_item` DISABLE KEYS */;
INSERT INTO `pms_problem_collection_item` VALUES (1,1,1,_binary ''),(2,4,7,_binary '\0'),(3,4,8,_binary '\0');
/*!40000 ALTER TABLE `pms_problem_collection_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pms_problem_data`
--

DROP TABLE IF EXISTS `pms_problem_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pms_problem_data` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `problem_id` bigint(10) unsigned DEFAULT NULL COMMENT '问题id',
  `input` text COLLATE utf8mb4_unicode_ci COMMENT '输入',
  `output` text COLLATE utf8mb4_unicode_ci COMMENT '输出',
  `create_user_id` bigint(10) unsigned DEFAULT NULL COMMENT '数据提供者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pms_problem_data`
--

LOCK TABLES `pms_problem_data` WRITE;
/*!40000 ALTER TABLE `pms_problem_data` DISABLE KEYS */;
INSERT INTO `pms_problem_data` VALUES (2,1,'1 1','2',3);
/*!40000 ALTER TABLE `pms_problem_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pms_problem_tag`
--

DROP TABLE IF EXISTS `pms_problem_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pms_problem_tag` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `problem_id` bigint(10) unsigned DEFAULT NULL COMMENT '问题id',
  `tag_id` bigint(10) unsigned DEFAULT NULL COMMENT '标签id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pms_problem_tag`
--

LOCK TABLES `pms_problem_tag` WRITE;
/*!40000 ALTER TABLE `pms_problem_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `pms_problem_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pms_submit`
--

DROP TABLE IF EXISTS `pms_submit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pms_submit` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `uid` bigint(10) unsigned DEFAULT NULL COMMENT '用户id',
  `language_type` tinyint(3) unsigned DEFAULT NULL COMMENT '语言类型',
  `content` text COLLATE utf8mb4_unicode_ci COMMENT '内容(编码内容)',
  `create_time` datetime DEFAULT NULL COMMENT '提交时间',
  `status` tinyint(4) DEFAULT NULL COMMENT '运行结果',
  `problem_id` bigint(20) unsigned DEFAULT NULL COMMENT '提交的问题',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pms_submit`
--

LOCK TABLES `pms_submit` WRITE;
/*!40000 ALTER TABLE `pms_submit` DISABLE KEYS */;
INSERT INTO `pms_submit` VALUES (4,1,0,'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}','2021-11-09 22:26:28',8,1),(5,1,0,'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        Scanner in = new Scanner(System.in);\n        System.out.println(in.nextInt() + in.nextInt());\n    }\n}','2021-11-09 22:28:21',8,1),(6,1,0,'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        Scanner in = new Scanner(System.in);\n        System.out.print(in.nextInt() + in.nextInt());\n    }\n}','2021-11-09 22:28:47',8,1),(7,1,0,'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        Scanner in = new Scanner(System.in);\n        System.out.print(in.nextInt() + in.nextInt());\n    }\n}','2021-11-09 22:33:12',8,1),(8,1,0,'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        Scanner in = new Scanner(System.in);\n        System.out.print(in.nextInt() + in.nextInt());\n    }\n}','2021-11-09 22:41:19',6,1),(9,1,0,'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        Scanner in = new Scanner(System.in);\n        System.out.println(in.nextInt() + in.nextInt());\n    }\n}','2021-11-09 22:41:31',7,1),(10,1,0,'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}','2021-11-18 21:51:37',NULL,1),(11,1,0,'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}','2021-11-18 22:09:53',8,1),(12,1,0,'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}','2021-11-18 22:10:15',8,1),(13,1,0,'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}','2021-11-18 22:10:18',8,1),(14,1,0,'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}','2021-11-18 22:10:21',8,1),(15,1,0,'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}','2021-11-20 21:14:14',8,1),(16,3,0,'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}','2021-11-20 21:17:20',8,1),(17,3,0,'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        Scanner in = new Scanner(System.in);\n        int a = in.nextInt();\n        int b = in.nextInt();\n        System.out.print(a + b);\n    }\n}','2021-11-23 14:54:46',6,1);
/*!40000 ALTER TABLE `pms_submit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pms_tag`
--

DROP TABLE IF EXISTS `pms_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pms_tag` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标签名',
  `create_user_id` bigint(20) unsigned DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pms_tag`
--

LOCK TABLES `pms_tag` WRITE;
/*!40000 ALTER TABLE `pms_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `pms_tag` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-23 10:59:37
