/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.181.128
 Source Server Type    : MySQL
 Source Server Version : 50735
 Source Host           : 192.168.181.128:3306
 Source Schema         : judge_pms

 Target Server Type    : MySQL
 Target Server Version : 50735
 File Encoding         : 65001

 Date: 03/10/2021 10:48:16
*/
CREATE DATABASE judge_pms;
USE judge_pms;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
) ENGINE=InnoDB AUTO_INCREMENT=858 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

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
                                               PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

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
-- Table structure for table `pms_solution`
--

DROP TABLE IF EXISTS `pms_solution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pms_solution` (
                                `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                `uid` bigint(20) DEFAULT NULL,
                                `title` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
                                `problem_id` bigint(20) DEFAULT NULL,
                                `create_time` datetime DEFAULT NULL,
                                `update_time` datetime DEFAULT NULL,
                                `content` longtext COLLATE utf8_unicode_ci,
                                `status` bit(1) DEFAULT b'1',
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=276 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-02 14:03:57
