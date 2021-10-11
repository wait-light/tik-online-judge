
/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.181.128
 Source Server Type    : MySQL
 Source Server Version : 50735
 Source Host           : 192.168.181.128:3306
 Source Schema         : judge_ums

 Target Server Type    : MySQL
 Target Server Version : 50735
 File Encoding         : 65001

 Date: 03/10/2021 10:48:28
*/
CREATE DATABASE judge_ums;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
USE judge_ums;

-- ----------------------------
-- Table structure for ums_menu
-- ----------------------------
DROP TABLE IF EXISTS `ums_menu`;
CREATE TABLE `ums_menu`  (
  `id` bigint(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '权限名称',
  `perms` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` tinyint(4) NULL DEFAULT NULL COMMENT '类型(0:目录 1.菜单 2.按钮)',
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '菜单地址',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图标',
  `order` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '排序',
  `parent_id` bigint(10) UNSIGNED NULL DEFAULT 0 COMMENT '父级id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ums_menu
-- ----------------------------
INSERT INTO `ums_menu` VALUES (1, '权限管理', 'system:menu:list', 1, '/backstage/menu', 'DocumentDelete', 1, 0);
INSERT INTO `ums_menu` VALUES (2, '角色管理', 'system:role:list', 0, '/backstage/role', 'Baseball', 5, 0);
INSERT INTO `ums_menu` VALUES (3, '角色查询', 'system:role:query', 2, NULL, NULL, NULL, 2);
INSERT INTO `ums_menu` VALUES (4, '角色添加', 'system:role:add', 2, NULL, NULL, 2, 2);
INSERT INTO `ums_menu` VALUES (5, '角色删除', 'system:role:remove', 2, NULL, NULL, 5, 2);
INSERT INTO `ums_menu` VALUES (6, '角色修改', 'system:role:edit', 2, NULL, NULL, NULL, 2);
INSERT INTO `ums_menu` VALUES (7, '角色导出', 'system:role:export', 2, NULL, NULL, NULL, 2);

-- ----------------------------
-- Table structure for ums_role
-- ----------------------------
DROP TABLE IF EXISTS `ums_role`;
CREATE TABLE `ums_role`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色名',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '创建用户id',
  `status` bit(1) NULL DEFAULT b'1' COMMENT '1启用/0关闭',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ums_role
-- ----------------------------
INSERT INTO `ums_role` VALUES (14, 'asdasd', '2021-09-15 16:30:53', '2021-09-15 16:30:53', 'asdasd', NULL, b'1');

-- ----------------------------
-- Table structure for ums_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `ums_role_menu`;
CREATE TABLE `ums_role_menu`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '角色id',
  `menu_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '菜单权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ums_role_menu
-- ----------------------------
INSERT INTO `ums_role_menu` VALUES (47, 14, 2);
INSERT INTO `ums_role_menu` VALUES (48, 14, 5);
INSERT INTO `ums_role_menu` VALUES (49, 14, 4);
INSERT INTO `ums_role_menu` VALUES (50, 14, 3);
INSERT INTO `ums_role_menu` VALUES (51, 14, 6);
INSERT INTO `ums_role_menu` VALUES (52, 14, 7);

-- ----------------------------
-- Table structure for ums_social
-- ----------------------------
DROP TABLE IF EXISTS `ums_social`;
CREATE TABLE `ums_social`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '第三方软件的名称',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `status` bit(1) NULL DEFAULT b'1' COMMENT '启用状态(1启用/0关闭)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ums_social
-- ----------------------------

-- ----------------------------
-- Table structure for ums_user
-- ----------------------------
DROP TABLE IF EXISTS `ums_user`;
CREATE TABLE `ums_user`  (
  `uid` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `status` bit(1) NULL DEFAULT b'1' COMMENT '状态(1启用/0禁用)',
  `nickname` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '昵称',
  `telephone` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '电子邮箱',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ums_user
-- ----------------------------
INSERT INTO `ums_user` VALUES (1, 'admin', 'admin', '2021-09-11 22:22:36', '2021-09-11 22:22:36', b'1', 'tik-admin', NULL, NULL, 'https://imgtu.com/i/4SEo8O');

-- ----------------------------
-- Table structure for ums_user_role
-- ----------------------------
DROP TABLE IF EXISTS `ums_user_role`;
CREATE TABLE `ums_user_role`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `uid` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '用户id',
  `role_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ums_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for ums_user_social
-- ----------------------------
DROP TABLE IF EXISTS `ums_user_social`;
CREATE TABLE `ums_user_social`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `uid` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '用户id',
  `social_type` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '社交账号类型',
  `user_social_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户社交账号的唯一id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_social_id`(`user_social_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ums_user_social
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
