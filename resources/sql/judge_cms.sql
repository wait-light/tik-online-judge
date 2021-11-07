/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.181.128
 Source Server Type    : MySQL
 Source Server Version : 50735
 Source Host           : 192.168.181.128:3306
 Source Schema         : judge_cms

 Target Server Type    : MySQL
 Target Server Version : 50735
 File Encoding         : 65001

 Date: 03/10/2021 10:48:08
*/
CREATE DATABASE judge_cms;
USE judge_cms;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
-- MySQL dump 10.13  Distrib 5.7.36, for Linux (x86_64)
--
-- Host: localhost    Database: judge_cms
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
-- Table structure for table `cms_comment`
--

DROP TABLE IF EXISTS `cms_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_comment` (
                               `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
                               `parent_id` bigint(20) unsigned DEFAULT NULL COMMENT '父id',
                               `solution_id` bigint(11) unsigned DEFAULT NULL COMMENT '题解id',
                               `uid` bigint(11) unsigned DEFAULT NULL COMMENT '评论人',
                               `floor_id` bigint(20) unsigned DEFAULT NULL COMMENT '层id',
                               `content` text COLLATE utf8mb4_unicode_ci COMMENT '评论内容',
                               `create_time` datetime DEFAULT NULL COMMENT '评论时间',
                               `status` bit(1) DEFAULT b'1' COMMENT '状态(1启用/0禁用)',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cms_group`
--

DROP TABLE IF EXISTS `cms_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_group` (
                             `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
                             `name` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '群组名称',
                             `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                             `create_user_id` bigint(20) unsigned DEFAULT NULL COMMENT '创建人id',
                             `status` tinyint(3) unsigned DEFAULT '1' COMMENT '状态',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cms_group_task`
--

DROP TABLE IF EXISTS `cms_group_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_group_task` (
                                  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
                                  `group_id` bigint(20) unsigned DEFAULT NULL COMMENT '群组id',
                                  `task_id` bigint(20) unsigned DEFAULT NULL COMMENT '任务id',
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cms_group_user`
--

DROP TABLE IF EXISTS `cms_group_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_group_user` (
                                  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
                                  `uid` bigint(20) unsigned DEFAULT NULL COMMENT '用户id',
                                  `group_id` bigint(20) unsigned DEFAULT NULL COMMENT '群组id',
                                  `user_type` tinyint(4) unsigned DEFAULT NULL COMMENT '群组用户类型',
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cms_solution`
--

DROP TABLE IF EXISTS `cms_solution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_solution` (
                                `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
                                `uid` bigint(20) unsigned DEFAULT NULL COMMENT '题解用户id',
                                `content` text COLLATE utf8mb4_unicode_ci COMMENT '题解内容',
                                `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                `status` bit(1) DEFAULT b'1' COMMENT '状态(1启用/0状态)',
                                `problem_id` bigint(20) DEFAULT NULL COMMENT '问题id',
                                `title` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标题',
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cms_task`
--

DROP TABLE IF EXISTS `cms_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_task` (
                            `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
                            `task_introduce` text COLLATE utf8mb4_unicode_ci COMMENT '任务介绍',
                            `create_user_id` bigint(20) unsigned DEFAULT NULL COMMENT '任务发布人',
                            `name` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '任务名称',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            `status` bit(1) DEFAULT b'1' COMMENT '状态',
                            `begin_time` datetime DEFAULT NULL COMMENT '开始时间',
                            `end_time` datetime DEFAULT NULL COMMENT '结束时间',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cms_task_item`
--

DROP TABLE IF EXISTS `cms_task_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_task_item` (
                                 `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
                                 `task_id` bigint(20) unsigned DEFAULT NULL COMMENT '任务id',
                                 `problem_id` bigint(20) unsigned DEFAULT NULL COMMENT '问题id',
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

-- Dump completed on 2021-11-04 12:41:25

SET FOREIGN_KEY_CHECKS = 1;
