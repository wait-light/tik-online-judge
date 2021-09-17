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

 Date: 16/09/2021 18:04:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cms_comment
-- ----------------------------
DROP TABLE IF EXISTS `cms_comment`;
CREATE TABLE `cms_comment`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `parent_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '父id',
  `solution_id` bigint(11) UNSIGNED NULL DEFAULT NULL COMMENT '题解id',
  `uid` bigint(11) UNSIGNED NULL DEFAULT NULL COMMENT '评论人',
  `floor_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '层id',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '评论内容',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '评论时间',
  `status` bit(1) NULL DEFAULT b'1' COMMENT '状态(1启用/0禁用)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cms_comment
-- ----------------------------

-- ----------------------------
-- Table structure for cms_group
-- ----------------------------
DROP TABLE IF EXISTS `cms_group`;
CREATE TABLE `cms_group`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '群组名称',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '创建人id',
  `status` tinyint(3) UNSIGNED NULL DEFAULT 1 COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cms_group
-- ----------------------------

-- ----------------------------
-- Table structure for cms_group_task
-- ----------------------------
DROP TABLE IF EXISTS `cms_group_task`;
CREATE TABLE `cms_group_task`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `group_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '群组id',
  `task_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '任务id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cms_group_task
-- ----------------------------

-- ----------------------------
-- Table structure for cms_group_user
-- ----------------------------
DROP TABLE IF EXISTS `cms_group_user`;
CREATE TABLE `cms_group_user`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `uid` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '用户id',
  `group_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '群组id',
  `user_type` tinyint(4) UNSIGNED NULL DEFAULT NULL COMMENT '群组用户类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cms_group_user
-- ----------------------------

-- ----------------------------
-- Table structure for cms_solution
-- ----------------------------
DROP TABLE IF EXISTS `cms_solution`;
CREATE TABLE `cms_solution`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `uid` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '题解用户id',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '题解内容',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `status` bit(1) NULL DEFAULT b'1' COMMENT '状态(1启用/0状态)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cms_solution
-- ----------------------------

-- ----------------------------
-- Table structure for cms_task
-- ----------------------------
DROP TABLE IF EXISTS `cms_task`;
CREATE TABLE `cms_task`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `task_introduce` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '任务介绍',
  `create_user_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '任务发布人',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '任务名称',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `status` bit(1) NULL DEFAULT b'1' COMMENT '状态',
  `begin_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cms_task
-- ----------------------------

-- ----------------------------
-- Table structure for cms_task_item
-- ----------------------------
DROP TABLE IF EXISTS `cms_task_item`;
CREATE TABLE `cms_task_item`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `task_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '任务id',
  `problem_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '问题id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cms_task_item
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
