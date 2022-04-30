/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : judge_ums

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 28/04/2022 22:44:12
*/
CREATE DATABASE IF NOT EXISTS judge_ums;
USE judge_ums;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ums_menu
-- ----------------------------
DROP TABLE IF EXISTS `ums_menu`;
CREATE TABLE `ums_menu`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '权限名称',
  `perms` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` tinyint NULL DEFAULT NULL COMMENT '类型(0:目录 1.接口 2.归纳)',
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '菜单地址',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图标',
  `order` int UNSIGNED NULL DEFAULT NULL COMMENT '排序',
  `parent_id` bigint UNSIGNED NULL DEFAULT 0 COMMENT '父级id',
  `request_method` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '请求方法',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 104 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ums_menu
-- ----------------------------
INSERT INTO `ums_menu` VALUES (50, '群组权限', NULL, 2, NULL, 'Document', 0, 0, NULL);
INSERT INTO `ums_menu` VALUES (51, 'createable', 'createable', 1, '/social/user-group', NULL, 0, 50, 'post');
INSERT INTO `ums_menu` VALUES (53, '后台目录', NULL, 2, NULL, 'Setting', 10, 0, NULL);
INSERT INTO `ums_menu` VALUES (54, '权限管理', NULL, 0, '/backstage/menu', 'Menu', 0, 55, '');
INSERT INTO `ums_menu` VALUES (55, '权限管理', NULL, 0, '/backstage?link=false', 'Cpu', 0, 53, NULL);
INSERT INTO `ums_menu` VALUES (56, '角色管理', NULL, 0, '/backstage/role', 'Place', 0, 55, '');
INSERT INTO `ums_menu` VALUES (57, '用户管理', NULL, 0, '/backstage/user', 'User', 0, 55, '');
INSERT INTO `ums_menu` VALUES (58, '申请管理', NULL, 0, '/backstage/ask', 'Message', 0, 55, '');
INSERT INTO `ums_menu` VALUES (59, '问题管理', NULL, 0, '/backstage?link=false', 'QuestionFilled', 0, 53, '');
INSERT INTO `ums_menu` VALUES (60, '问题管理', NULL, 0, '/backstage/problem', 'CircleClose', 0, 59, '');
INSERT INTO `ums_menu` VALUES (61, '问题集管理', NULL, 0, '/backstage/problemcollection', 'DocumentCopy', 0, 59, '');
INSERT INTO `ums_menu` VALUES (62, '匿名权限', NULL, 2, NULL, 'Hide', 0, 0, '');
INSERT INTO `ums_menu` VALUES (64, '邮箱登录', 'auth:anonymous:emailLogin', 1, '/auth/anonymous/index/email_login', NULL, 0, 62, 'post');
INSERT INTO `ums_menu` VALUES (65, '密码登录', 'auth:anonymous:username_login', 1, '/auth/anonymous/index/username_login', '', 0, 62, 'post');
INSERT INTO `ums_menu` VALUES (66, '获取验证码', 'auth:anonymous:verificationCode', 1, '/auth/anonymous/index/verificationCode/{email}', '', 0, 62, 'post');
INSERT INTO `ums_menu` VALUES (67, '密码修改（邮箱）', 'auth:anonymous:passwordResetByEmail', 1, '/auth/anonymous/index/password/email', '', 0, 62, 'put');
INSERT INTO `ums_menu` VALUES (68, '密码修改（用户名）', 'auth:anonymous:passwordResetByUsername', 1, '/auth/anonymous/index/password/username', '', 0, 62, 'put');
INSERT INTO `ums_menu` VALUES (69, '用户头像', 'auth:anonymous:usertitle', 1, '/auth/anonymous/index/{id}', '', 0, 62, 'get');
INSERT INTO `ums_menu` VALUES (70, '用户目录', 'auth:menu:directory', 1, '/auth/menu/directory', NULL, 0, 62, 'get');
INSERT INTO `ums_menu` VALUES (71, '后台权限', NULL, 2, NULL, 'Cloudy', 0, 0, '');
INSERT INTO `ums_menu` VALUES (72, '权限查看', 'auth:menu:view', 1, '/auth/menu/tree/', NULL, 0, 74, 'get');
INSERT INTO `ums_menu` VALUES (73, '权限添加', 'auth:menu:add', 1, '/auth/menu', NULL, 0, 74, 'post');
INSERT INTO `ums_menu` VALUES (74, '权限管理接口', '', 2, '', 'Lock', 0, 71, '');
INSERT INTO `ums_menu` VALUES (75, '权限修改', 'auth:menu:update', 1, '/auth/menu/{id}', '', 0, 74, 'put');
INSERT INTO `ums_menu` VALUES (76, '权限删除', 'auth:menu:delete', 1, '/auth/menu', NULL, 0, 74, 'delete');
INSERT INTO `ums_menu` VALUES (77, '用户权限集', 'auth:menu:interfaces', 1, '/auth/menu/interfaces', NULL, 0, 62, 'get');
INSERT INTO `ums_menu` VALUES (78, '角色管理接口', NULL, 2, NULL, 'Brush', 0, 71, '');
INSERT INTO `ums_menu` VALUES (79, '角色查看', 'auth:role:view', 2, '/auth/role/list', '', 0, 78, 'get');
INSERT INTO `ums_menu` VALUES (80, '角色添加', 'auth:role:add', 1, '/auth/role', '', 0, 78, 'post');
INSERT INTO `ums_menu` VALUES (81, '角色修改', 'auth:role:update', 1, '/auth/role/{id}', '', 0, 78, 'put');
INSERT INTO `ums_menu` VALUES (82, '角色删除', 'auth:menu:delete', 1, '/auth/role/{id}', '', 0, 78, 'delete');
INSERT INTO `ums_menu` VALUES (83, '用户管理接口', NULL, 2, NULL, 'Avatar', 0, 71, '');
INSERT INTO `ums_menu` VALUES (84, '用户查看', 'auth:user:view', 1, '/auth/user/list', '', 0, 83, 'get');
INSERT INTO `ums_menu` VALUES (85, '用户添加', 'auth:user:add', 1, '/auth/user', '', 0, 83, 'post');
INSERT INTO `ums_menu` VALUES (86, '用户修改', 'auth:user:update', 1, '/auth/user/{id}', '', 0, 83, 'put');
INSERT INTO `ums_menu` VALUES (87, '用户删除', 'auth:user:delete', 1, '/auth/user/{id}', '', 0, 83, 'delete');
INSERT INTO `ums_menu` VALUES (88, '申请管理接口', NULL, 2, NULL, 'Message', 0, 71, '');
INSERT INTO `ums_menu` VALUES (89, '申请查看', 'auth:ask:view', 1, '/auth/role-ask-admin/list', NULL, 0, 88, 'get');
INSERT INTO `ums_menu` VALUES (90, '申请处理', 'auth:ask:process', 1, '/auth/role-ask-admin/{id}/{status}', NULL, 0, 88, 'put');
INSERT INTO `ums_menu` VALUES (91, '问题管理接口', NULL, 2, NULL, 'ChatLineRound', 0, 71, '');
INSERT INTO `ums_menu` VALUES (92, '问题添加', 'executor:problem:view', 1, '/executor/problem/list', NULL, 0, 91, 'get');
INSERT INTO `ums_menu` VALUES (93, '问题添加', 'executor:problem:add', 1, '/executor/problem', NULL, 0, 91, 'post');
INSERT INTO `ums_menu` VALUES (94, '问题修改', 'executor:problem:update', 1, '/executor/problem/{id}', NULL, 0, 91, 'put');
INSERT INTO `ums_menu` VALUES (95, '问题删除', 'executor:problem:delete', 1, '/executor/problem/{id}', NULL, 0, 91, 'delete');
INSERT INTO `ums_menu` VALUES (96, '问题集管理接口', NULL, 2, NULL, 'IceDrink', 0, 71, '');
INSERT INTO `ums_menu` VALUES (97, '问题集查看', 'executor:problem-collection:view', 1, '/executor/problem-collection/list', '', 0, 96, 'get');
INSERT INTO `ums_menu` VALUES (98, '问题集添加', 'executor:problem-collection:add', 1, '/executor/problem-collection', '', 0, 96, 'post');
INSERT INTO `ums_menu` VALUES (99, '问题集修改', 'executor:problem-collection:update', 1, '/executor/problem-collection/{id}', '', 0, 96, 'put');
INSERT INTO `ums_menu` VALUES (100, '问题集删除', 'executor:problem-collection:delete', 1, '/executor/problem-collection/{id}', '', 0, 96, 'delete');
INSERT INTO `ums_menu` VALUES (101, '用户密码重置', 'auth:user:reset', 1, '/auth/user/resetPassword/{id}', NULL, 0, 83, 'put');
INSERT INTO `ums_menu` VALUES (102, '用户角色添加', 'auth:user:roleAdd', 1, '/auth/user-role', NULL, 0, 83, 'post');
INSERT INTO `ums_menu` VALUES (103, '问题数据添加', 'executor:problem:dateItemAdd', 1, '/executor/problem-data', NULL, 0, 91, 'post');
INSERT INTO `ums_menu` VALUES (104, '问题集项添加', 'executor:problem-collection:itemAdd', 1, '/executor/problem-collection', NULL, 0, 96, 'post');

-- ----------------------------
-- Table structure for ums_role
-- ----------------------------
DROP TABLE IF EXISTS `ums_role`;
CREATE TABLE `ums_role`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色名',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '创建用户id',
  `status` bit(1) NULL DEFAULT b'1' COMMENT '1启用/0关闭',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ums_role
-- ----------------------------
INSERT INTO `ums_role` VALUES (2, '群组创建人', '2022-01-03 16:52:26', '2022-03-19 17:12:06', '创建群组的人', 1, b'1');
INSERT INTO `ums_role` VALUES (11, '用户管理员', '2022-03-19 22:39:03', '2022-03-20 22:31:02', '用户管理员', 1, b'1');
INSERT INTO `ums_role` VALUES (12, '问题管理员', '2022-03-19 23:35:29', '2022-03-20 22:07:17', '问题管理员', 1, b'1');
INSERT INTO `ums_role` VALUES (13, 'anonymous', '2022-03-20 15:35:46', '2022-03-20 20:23:49', '匿名用户（未登录）', 1, b'1');
INSERT INTO `ums_role` VALUES (14, '权限管理员', '2022-03-20 19:08:44', '2022-03-20 21:33:27', '权限管理员', 1, b'1');
INSERT INTO `ums_role` VALUES (15, '角色管理员', '2022-03-20 21:29:23', '2022-03-20 21:33:33', '角色管理员', 1, b'1');
INSERT INTO `ums_role` VALUES (16, '角色申请管理员', '2022-03-20 21:51:47', '2022-03-20 21:51:47', '处理角色申请的管理员', 1, b'1');
INSERT INTO `ums_role` VALUES (17, '问题集管理员', '2022-03-20 22:09:29', '2022-03-20 23:10:41', '问题集管理员', 1, b'1');
INSERT INTO `ums_role` VALUES (18, 'Tester', '2022-03-20 23:12:24', '2022-03-20 23:13:34', 'Tester', 1, b'1');
INSERT INTO `ums_role` VALUES (19, 'Only User Update and Query', '2022-03-22 15:11:40', '2022-03-22 15:11:40', '测试', 1, b'1');

-- ----------------------------
-- Table structure for ums_role_ask
-- ----------------------------
DROP TABLE IF EXISTS `ums_role_ask`;
CREATE TABLE `ums_role_ask`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '角色名',
  `create_time` datetime NULL DEFAULT NULL COMMENT '申请时间',
  `uid` bigint UNSIGNED NULL DEFAULT NULL COMMENT '申请人',
  `handler` bigint UNSIGNED NULL DEFAULT NULL COMMENT '处理人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '处理时间',
  `status` tinyint(3) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '处理状态',
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_role_ask
-- ----------------------------
INSERT INTO `ums_role_ask` VALUES (7, '群组创建人', '2022-03-21 12:00:21', 3, 1, '2022-03-21 18:33:19', 001, '用于管理学生');

-- ----------------------------
-- Table structure for ums_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `ums_role_menu`;
CREATE TABLE `ums_role_menu`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '角色id',
  `menu_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '菜单权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 212 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ums_role_menu
-- ----------------------------
INSERT INTO `ums_role_menu` VALUES (114, 2, 50);
INSERT INTO `ums_role_menu` VALUES (115, 2, 51);
INSERT INTO `ums_role_menu` VALUES (120, 11, 53);
INSERT INTO `ums_role_menu` VALUES (121, 11, 55);
INSERT INTO `ums_role_menu` VALUES (122, 11, 57);
INSERT INTO `ums_role_menu` VALUES (123, 12, 53);
INSERT INTO `ums_role_menu` VALUES (124, 12, 59);
INSERT INTO `ums_role_menu` VALUES (125, 12, 60);
INSERT INTO `ums_role_menu` VALUES (128, 13, 62);
INSERT INTO `ums_role_menu` VALUES (129, 13, 70);
INSERT INTO `ums_role_menu` VALUES (130, 13, 69);
INSERT INTO `ums_role_menu` VALUES (131, 13, 68);
INSERT INTO `ums_role_menu` VALUES (132, 13, 67);
INSERT INTO `ums_role_menu` VALUES (133, 13, 66);
INSERT INTO `ums_role_menu` VALUES (134, 13, 65);
INSERT INTO `ums_role_menu` VALUES (135, 13, 64);
INSERT INTO `ums_role_menu` VALUES (136, 14, 71);
INSERT INTO `ums_role_menu` VALUES (137, 14, 74);
INSERT INTO `ums_role_menu` VALUES (142, 14, 53);
INSERT INTO `ums_role_menu` VALUES (143, 14, 55);
INSERT INTO `ums_role_menu` VALUES (144, 14, 54);
INSERT INTO `ums_role_menu` VALUES (145, 13, 77);
INSERT INTO `ums_role_menu` VALUES (146, 14, 72);
INSERT INTO `ums_role_menu` VALUES (148, 14, 76);
INSERT INTO `ums_role_menu` VALUES (149, 14, 75);
INSERT INTO `ums_role_menu` VALUES (150, 14, 73);
INSERT INTO `ums_role_menu` VALUES (151, 15, 53);
INSERT INTO `ums_role_menu` VALUES (152, 15, 55);
INSERT INTO `ums_role_menu` VALUES (153, 15, 56);
INSERT INTO `ums_role_menu` VALUES (154, 15, 71);
INSERT INTO `ums_role_menu` VALUES (155, 15, 78);
INSERT INTO `ums_role_menu` VALUES (159, 15, 79);
INSERT INTO `ums_role_menu` VALUES (160, 15, 82);
INSERT INTO `ums_role_menu` VALUES (162, 15, 80);
INSERT INTO `ums_role_menu` VALUES (163, 15, 81);
INSERT INTO `ums_role_menu` VALUES (164, 16, 53);
INSERT INTO `ums_role_menu` VALUES (165, 16, 55);
INSERT INTO `ums_role_menu` VALUES (166, 16, 58);
INSERT INTO `ums_role_menu` VALUES (167, 16, 71);
INSERT INTO `ums_role_menu` VALUES (168, 16, 88);
INSERT INTO `ums_role_menu` VALUES (169, 16, 89);
INSERT INTO `ums_role_menu` VALUES (170, 16, 90);
INSERT INTO `ums_role_menu` VALUES (171, 11, 71);
INSERT INTO `ums_role_menu` VALUES (172, 11, 83);
INSERT INTO `ums_role_menu` VALUES (173, 11, 84);
INSERT INTO `ums_role_menu` VALUES (174, 11, 85);
INSERT INTO `ums_role_menu` VALUES (175, 11, 86);
INSERT INTO `ums_role_menu` VALUES (176, 11, 87);
INSERT INTO `ums_role_menu` VALUES (177, 12, 71);
INSERT INTO `ums_role_menu` VALUES (178, 12, 91);
INSERT INTO `ums_role_menu` VALUES (179, 12, 92);
INSERT INTO `ums_role_menu` VALUES (180, 12, 93);
INSERT INTO `ums_role_menu` VALUES (181, 12, 94);
INSERT INTO `ums_role_menu` VALUES (182, 12, 95);
INSERT INTO `ums_role_menu` VALUES (183, 17, 53);
INSERT INTO `ums_role_menu` VALUES (184, 17, 59);
INSERT INTO `ums_role_menu` VALUES (185, 17, 61);
INSERT INTO `ums_role_menu` VALUES (186, 17, 71);
INSERT INTO `ums_role_menu` VALUES (187, 17, 96);
INSERT INTO `ums_role_menu` VALUES (188, 17, 97);
INSERT INTO `ums_role_menu` VALUES (189, 17, 98);
INSERT INTO `ums_role_menu` VALUES (190, 17, 99);
INSERT INTO `ums_role_menu` VALUES (191, 17, 100);
INSERT INTO `ums_role_menu` VALUES (192, 11, 101);
INSERT INTO `ums_role_menu` VALUES (193, 11, 102);
INSERT INTO `ums_role_menu` VALUES (194, 17, 104);
INSERT INTO `ums_role_menu` VALUES (195, 18, 53);
INSERT INTO `ums_role_menu` VALUES (196, 18, 59);
INSERT INTO `ums_role_menu` VALUES (197, 18, 60);
INSERT INTO `ums_role_menu` VALUES (198, 18, 61);
INSERT INTO `ums_role_menu` VALUES (199, 18, 71);
INSERT INTO `ums_role_menu` VALUES (200, 18, 96);
INSERT INTO `ums_role_menu` VALUES (201, 18, 97);
INSERT INTO `ums_role_menu` VALUES (202, 18, 99);
INSERT INTO `ums_role_menu` VALUES (203, 18, 100);
INSERT INTO `ums_role_menu` VALUES (204, 18, 98);
INSERT INTO `ums_role_menu` VALUES (206, 19, 53);
INSERT INTO `ums_role_menu` VALUES (207, 19, 55);
INSERT INTO `ums_role_menu` VALUES (208, 19, 54);
INSERT INTO `ums_role_menu` VALUES (209, 19, 71);
INSERT INTO `ums_role_menu` VALUES (210, 19, 74);
INSERT INTO `ums_role_menu` VALUES (211, 19, 72);
INSERT INTO `ums_role_menu` VALUES (212, 19, 75);

-- ----------------------------
-- Table structure for ums_social
-- ----------------------------
DROP TABLE IF EXISTS `ums_social`;
CREATE TABLE `ums_social`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '第三方软件的名称',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `status` bit(1) NULL DEFAULT b'1' COMMENT '启用状态(1启用/0关闭)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ums_social
-- ----------------------------

-- ----------------------------
-- Table structure for ums_user
-- ----------------------------
DROP TABLE IF EXISTS `ums_user`;
CREATE TABLE `ums_user`  (
  `uid` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `status` bit(1) NULL DEFAULT b'1' COMMENT '状态(1启用/0禁用)',
  `nickname` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '昵称',
  `telephone` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '电子邮箱',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `admin` bit(1) NULL DEFAULT b'0' COMMENT '是否管理员',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ums_user
-- ----------------------------
INSERT INTO `ums_user` VALUES (1, '915779941@qq.com', 'cdaa7da69d09439a0df7e350637eb50d', '2021-11-08 15:51:03', '2021-11-08 15:51:03', b'1', 'wait-light', NULL, '915779941@qq.com', 'https://tik-online-judge.oss-cn-hangzhou.aliyuncs.com/2022-03-18/1ab9bb46-e8b7-4974-96ea-af3634cc713d_06051452309791.jpg', b'1');
INSERT INTO `ums_user` VALUES (2, 'admin', 'cdaa7da69d09439a0df7e350637eb50d', '2021-11-18 12:56:33', '2021-11-18 12:56:33', b'1', 'I\'am a VIP', '18888888888', 'asdasdasd@qq.com', 'https://tik-online-judge.oss-cn-hangzhou.aliyuncs.com/2022-02-16/28b33b70-eaa6-4f6f-8ec9-b7babbd1c03a_massimiliano-morosinotto-MljwsnGwdOY-unsplash.jpg', b'0');
INSERT INTO `ums_user` VALUES (3, 'hy915779941@gmail.com', 'cdaa7da69d09439a0df7e350637eb50d', '2021-11-20 20:09:49', '2021-11-20 20:09:49', b'1', '说来实在嘲讽', '18888888888', 'hy915779941@gmail.com', 'https://tik-online-judge.oss-cn-hangzhou.aliyuncs.com/2021-12-21/b363d240-27a9-45c0-9c16-ce0afa8ce032_透明头像.png', b'0');
INSERT INTO `ums_user` VALUES (5, 'asdasd', NULL, '2022-02-22 19:08:46', '2022-02-22 19:08:46', b'1', 'asdasd', 'asdasd', 'asdasdasd', 'https://tik-online-judge.oss-cn-hangzhou.aliyuncs.com/2022-02-22/abd61910-4162-4228-8a26-d2281bcf65c0_willian-justen-de-vasconcellos-ASKGjAeIY_U-unsplash.jpg', b'0');
INSERT INTO `ums_user` VALUES (6, 'addAuser', 'cdaa7da69d09439a0df7e350637eb50d', '2022-03-21 18:49:22', '2022-03-21 18:49:22', b'1', '呵呵呵', '18888888888', '654654564@qq.com', 'https://tik-online-judge.oss-cn-hangzhou.aliyuncs.com/2022-03-21/8985abd1-d03f-40da-a9fc-357a8441b09a_06051452309781.jpg', b'0');

-- ----------------------------
-- Table structure for ums_user_role
-- ----------------------------
DROP TABLE IF EXISTS `ums_user_role`;
CREATE TABLE `ums_user_role`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `uid` bigint UNSIGNED NULL DEFAULT NULL COMMENT '用户id',
  `role_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ums_user_role
-- ----------------------------
INSERT INTO `ums_user_role` VALUES (8, 3, 9);
INSERT INTO `ums_user_role` VALUES (15, 3, 2);
INSERT INTO `ums_user_role` VALUES (16, 3, 19);

-- ----------------------------
-- Table structure for ums_user_social
-- ----------------------------
DROP TABLE IF EXISTS `ums_user_social`;
CREATE TABLE `ums_user_social`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `uid` bigint UNSIGNED NULL DEFAULT NULL COMMENT '用户id',
  `social_type` bigint UNSIGNED NULL DEFAULT NULL COMMENT '社交账号类型',
  `user_social_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户社交账号的唯一id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_social_id`(`user_social_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ums_user_social
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
