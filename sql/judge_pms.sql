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

 Date: 16/09/2021 18:05:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pms_language
-- ----------------------------
DROP TABLE IF EXISTS `pms_language`;
CREATE TABLE `pms_language`  (
  `id` bigint(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '编程语言名称',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `status` bit(1) NULL DEFAULT b'1' COMMENT '状态(1启动/0关闭)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of pms_language
-- ----------------------------

-- ----------------------------
-- Table structure for pms_problem
-- ----------------------------
DROP TABLE IF EXISTS `pms_problem`;
CREATE TABLE `pms_problem`  (
  `id` bigint(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '问题名称',
  `uid` bigint(10) UNSIGNED NULL DEFAULT NULL COMMENT '上传人',
  `problem_describe` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '问题描述',
  `input` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '输入',
  `output` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '输出',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `status` bit(1) NULL DEFAULT b'1' COMMENT '状态',
  `input_describle` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `output_describle` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of pms_problem
-- ----------------------------

-- ----------------------------
-- Table structure for pms_problem_data
-- ----------------------------
DROP TABLE IF EXISTS `pms_problem_data`;
CREATE TABLE `pms_problem_data`  (
  `id` bigint(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `problem_id` bigint(10) UNSIGNED NULL DEFAULT NULL COMMENT '问题id',
  `input` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '输入',
  `output` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '输出',
  `create_user_id` bigint(10) UNSIGNED NULL DEFAULT NULL COMMENT '数据提供者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of pms_problem_data
-- ----------------------------

-- ----------------------------
-- Table structure for pms_problem_tag
-- ----------------------------
DROP TABLE IF EXISTS `pms_problem_tag`;
CREATE TABLE `pms_problem_tag`  (
  `id` bigint(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `problem` bigint(10) UNSIGNED NULL DEFAULT NULL COMMENT '问题id',
  `tag_id` bigint(10) UNSIGNED NULL DEFAULT NULL COMMENT '标签id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of pms_problem_tag
-- ----------------------------

-- ----------------------------
-- Table structure for pms_submit
-- ----------------------------
DROP TABLE IF EXISTS `pms_submit`;
CREATE TABLE `pms_submit`  (
  `id` bigint(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `uid` bigint(10) UNSIGNED NULL DEFAULT NULL COMMENT '用户id',
  `language_id` bigint(10) UNSIGNED NULL DEFAULT NULL COMMENT '语言类型',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '内容(编码内容)',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '提交时间',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '运行结果',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of pms_submit
-- ----------------------------

-- ----------------------------
-- Table structure for pms_tag
-- ----------------------------
DROP TABLE IF EXISTS `pms_tag`;
CREATE TABLE `pms_tag`  (
  `id` bigint(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标签名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of pms_tag
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
