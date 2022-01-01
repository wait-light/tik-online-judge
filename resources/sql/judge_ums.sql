/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50736
Source Host           : 127.0.0.1:3306
Source Database       : judge_ums

Target Server Type    : MYSQL
Target Server Version : 50736
File Encoding         : 65001

Date: 2021-12-27 21:14:21
*/
CREATE DATABASE judge_ums;
USE judge_ums;
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ums_menu
-- ----------------------------
DROP TABLE IF EXISTS `ums_menu`;
CREATE TABLE `ums_menu` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '权限名称',
  `perms` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` tinyint(4) DEFAULT NULL COMMENT '类型(0:目录 1.菜单 2.按钮)',
  `url` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '菜单地址',
  `icon` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图标',
  `order` int(10) unsigned DEFAULT NULL COMMENT '排序',
  `parent_id` bigint(10) unsigned DEFAULT '0' COMMENT '父级id',
  `request_method` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '请求方法',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of ums_menu
-- ----------------------------

-- ----------------------------
-- Table structure for ums_role
-- ----------------------------
DROP TABLE IF EXISTS `ums_role`;
CREATE TABLE `ums_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint(20) unsigned DEFAULT NULL COMMENT '创建用户id',
  `status` bit(1) DEFAULT b'1' COMMENT '1启用/0关闭',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of ums_role
-- ----------------------------

-- ----------------------------
-- Table structure for ums_role_ask
-- ----------------------------
DROP TABLE IF EXISTS `ums_role_ask`;
CREATE TABLE `ums_role_ask` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '角色名',
  `create_time` datetime DEFAULT NULL COMMENT '申请时间',
  `uid` bigint(20) unsigned DEFAULT NULL COMMENT '申请人',
  `handler` bigint(20) unsigned DEFAULT NULL COMMENT '处理人',
  `update_time` datetime DEFAULT NULL COMMENT '处理时间',
  `status` tinyint(3) unsigned zerofill DEFAULT NULL COMMENT '处理状态',
  `reason` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of ums_role_ask
-- ----------------------------

-- ----------------------------
-- Table structure for ums_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `ums_role_menu`;
CREATE TABLE `ums_role_menu` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` bigint(20) unsigned DEFAULT NULL COMMENT '角色id',
  `menu_id` bigint(20) unsigned DEFAULT NULL COMMENT '菜单权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of ums_role_menu
-- ----------------------------
INSERT INTO `ums_role_menu` VALUES ('47', '14', '2');
INSERT INTO `ums_role_menu` VALUES ('48', '14', '5');
INSERT INTO `ums_role_menu` VALUES ('49', '14', '4');
INSERT INTO `ums_role_menu` VALUES ('50', '14', '3');
INSERT INTO `ums_role_menu` VALUES ('51', '14', '6');
INSERT INTO `ums_role_menu` VALUES ('52', '14', '7');

-- ----------------------------
-- Table structure for ums_social
-- ----------------------------
DROP TABLE IF EXISTS `ums_social`;
CREATE TABLE `ums_social` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '第三方软件的名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` bit(1) DEFAULT b'1' COMMENT '启用状态(1启用/0关闭)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of ums_social
-- ----------------------------

-- ----------------------------
-- Table structure for ums_user
-- ----------------------------
DROP TABLE IF EXISTS `ums_user`;
CREATE TABLE `ums_user` (
  `uid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` bit(1) DEFAULT b'1' COMMENT '状态(1启用/0禁用)',
  `nickname` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '昵称',
  `telephone` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '手机号',
  `email` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '电子邮箱',
  `avatar` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '头像',
  `admin` bit(1) DEFAULT b'0' COMMENT '是否管理员',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of ums_user
-- ----------------------------
INSERT INTO `ums_user` VALUES ('1', '915779941@qq.com', 'cdaa7da69d09439a0df7e350637eb50d', '2021-11-08 15:51:03', '2021-11-08 15:51:03', '', 'wait-light2', null, '915779941@qq.com', 'https://z3.ax1x.com/2021/09/11/4SEo8O.png', '');
INSERT INTO `ums_user` VALUES ('2', 'admin', 'admin', '2021-11-18 12:56:33', '2021-11-18 12:56:33', '', 'tik-admin', null, null, 'https://z3.ax1x.com/2021/09/11/4SEo8O.png', '\0');
INSERT INTO `ums_user` VALUES ('3', 'hy915779941@gmail.com', 'cdaa7da69d09439a0df7e350637eb50d', '2021-11-20 20:09:49', '2021-11-20 20:09:49', '', '说来实在嘲讽', null, 'hy915779941@gmail.com', 'https://tik-online-judge.oss-cn-hangzhou.aliyuncs.com/2021-12-21/b363d240-27a9-45c0-9c16-ce0afa8ce032_透明头像.png', '\0');

-- ----------------------------
-- Table structure for ums_user_role
-- ----------------------------
DROP TABLE IF EXISTS `ums_user_role`;
CREATE TABLE `ums_user_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `uid` bigint(20) unsigned DEFAULT NULL COMMENT '用户id',
  `role_id` bigint(20) unsigned DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of ums_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for ums_user_social
-- ----------------------------
DROP TABLE IF EXISTS `ums_user_social`;
CREATE TABLE `ums_user_social` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `uid` bigint(20) unsigned DEFAULT NULL COMMENT '用户id',
  `social_type` bigint(20) unsigned DEFAULT NULL COMMENT '社交账号类型',
  `user_social_id` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户社交账号的唯一id',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `user_social_id` (`user_social_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of ums_user_social
-- ----------------------------
