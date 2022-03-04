SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

SET NAMES utf8mb4;

DROP TABLE IF EXISTS `ums_menu`;
CREATE TABLE `ums_menu` (
                            `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
                            `name` varchar(120) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '权限名称',
                            `perms` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
                            `type` tinyint(4) DEFAULT NULL COMMENT '类型(0:目录 1.菜单 2.按钮)',
                            `url` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '菜单地址',
                            `icon` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图标',
                            `order` int(10) unsigned DEFAULT NULL COMMENT '排序',
                            `parent_id` bigint(10) unsigned DEFAULT '0' COMMENT '父级id',
                            `request_method` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '请求方法',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

INSERT INTO `ums_menu` (`id`, `name`, `perms`, `type`, `url`, `icon`, `order`, `parent_id`, `request_method`) VALUES
(1,	'createable',	'createable',	2,	NULL,	NULL,	1,	0,	NULL),
(4,	'SystemAutoGenerator',	'SystemAutoGenerator',	0,	NULL,	'Sunrise',	1,	0,	NULL),
(42,	'anonymous',	'anonymous',	2,	NULL,	NULL,	NULL,	4,	NULL),
(43,	'anonymous:POST:/auth/anonymous/index/email_login',	'anonymous:POST:/auth/anonymous/index/email_login',	2,	'/auth/anonymous/index/email_login',	NULL,	0,	42,	'POST'),
(44,	'anonymous:POST:/auth/anonymous/index/username_login',	'anonymous:POST:/auth/anonymous/index/username_login',	2,	'/auth/anonymous/index/username_login',	NULL,	0,	42,	'POST'),
(45,	'anonymous:POST:/auth/anonymous/index/verificationCode/{email}',	'anonymous:POST:/auth/anonymous/index/verificationCode/{email}',	2,	'/auth/anonymous/index/verificationCode/{email}',	NULL,	0,	42,	'POST'),
(46,	'anonymous:PUT:/auth/anonymous/index/password/email',	'anonymous:PUT:/auth/anonymous/index/password/email',	2,	'/auth/anonymous/index/password/email',	NULL,	0,	42,	'PUT'),
(47,	'anonymous:PUT:/auth/anonymous/index/password/username',	'anonymous:PUT:/auth/anonymous/index/password/username',	2,	'/auth/anonymous/index/password/username',	NULL,	0,	42,	'PUT'),
(48,	'anonymous:GET:/auth/anonymous/index/{id}',	'anonymous:GET:/auth/anonymous/index/{id}',	2,	'/auth/anonymous/index/{id}',	NULL,	0,	42,	'GET');

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

INSERT INTO `ums_role` (`id`, `name`, `create_time`, `update_time`, `remark`, `create_user_id`, `status`) VALUES
(2,	'group-creator',	'2022-01-03 16:52:26',	'2022-02-22 19:23:31',	'创建群组的权限',	1,	CONV('1', 2, 10) + 0),
(9,	'anonymous',	'2022-01-14 15:31:24',	'2022-01-14 15:31:24',	'系统生成',	1,	CONV('1', 2, 10) + 0),
(10,	'judge',	'2022-01-17 15:04:56',	'2022-01-17 15:04:56',	'系统生成',	1,	CONV('1', 2, 10) + 0);

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

INSERT INTO `ums_role_ask` (`id`, `name`, `create_time`, `uid`, `handler`, `update_time`, `status`, `reason`) VALUES
(1,	'group-create',	'2022-01-03 15:29:55',	3,	1,	'2022-01-04 14:14:47',	001,	'测试');

DROP TABLE IF EXISTS `ums_role_menu`;
CREATE TABLE `ums_role_menu` (
                                 `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
                                 `role_id` bigint(20) unsigned DEFAULT NULL COMMENT '角色id',
                                 `menu_id` bigint(20) unsigned DEFAULT NULL COMMENT '菜单权限id',
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

INSERT INTO `ums_role_menu` (`id`, `role_id`, `menu_id`) VALUES
(59,	6,	1),
(60,	6,	3),
(100,	9,	42),
(101,	9,	43),
(102,	9,	44),
(103,	9,	45),
(104,	9,	46),
(105,	9,	47),
(106,	9,	48),
(107,	10,	49),
(108,	10,	50),
(109,	10,	51),
(110,	10,	52),
(111,	2,	1);

DROP TABLE IF EXISTS `ums_social`;
CREATE TABLE `ums_social` (
                              `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
                              `name` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '第三方软件的名称',
                              `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                              `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                              `status` bit(1) DEFAULT b'1' COMMENT '启用状态(1启用/0关闭)',
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;


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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

INSERT INTO `ums_user` (`uid`, `username`, `password`, `create_time`, `update_time`, `status`, `nickname`, `telephone`, `email`, `avatar`, `admin`) VALUES
(1,	'915779941@qq.com',	'cdaa7da69d09439a0df7e350637eb50d',	'2021-11-08 15:51:03',	'2021-11-08 15:51:03',	CONV('1', 2, 10) + 0,	'wait-light2',	NULL,	'915779941@qq.com',	'https://z3.ax1x.com/2021/09/11/4SEo8O.png',	CONV('1', 2, 10) + 0),
(2,	'admin',	'cdaa7da69d09439a0df7e350637eb50d',	'2021-11-18 12:56:33',	'2021-11-18 12:56:33',	CONV('1', 2, 10) + 0,	'tik-admin',	'18888888888',	'asdasdasd@qq.com',	'https://tik-online-judge.oss-cn-hangzhou.aliyuncs.com/2022-02-16/28b33b70-eaa6-4f6f-8ec9-b7babbd1c03a_massimiliano-morosinotto-MljwsnGwdOY-unsplash.jpg',	CONV('0', 2, 10) + 0),
(3,	'hy915779941@gmail.com',	'cdaa7da69d09439a0df7e350637eb50d',	'2021-11-20 20:09:49',	'2021-11-20 20:09:49',	CONV('1', 2, 10) + 0,	'说来实在嘲讽',	'18888888888',	'hy915779941@gmail.com',	'https://tik-online-judge.oss-cn-hangzhou.aliyuncs.com/2021-12-21/b363d240-27a9-45c0-9c16-ce0afa8ce032_透明头像.png',	CONV('0', 2, 10) + 0),
(5,	'asdasd',	NULL,	'2022-02-22 19:08:46',	'2022-02-22 19:08:46',	CONV('1', 2, 10) + 0,	'asdasd',	'asdasd',	'asdasdasd',	'https://tik-online-judge.oss-cn-hangzhou.aliyuncs.com/2022-02-22/abd61910-4162-4228-8a26-d2281bcf65c0_willian-justen-de-vasconcellos-ASKGjAeIY_U-unsplash.jpg',	CONV('0', 2, 10) + 0);

DROP TABLE IF EXISTS `ums_user_role`;
CREATE TABLE `ums_user_role` (
                                 `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
                                 `uid` bigint(20) unsigned DEFAULT NULL COMMENT '用户id',
                                 `role_id` bigint(20) unsigned DEFAULT NULL COMMENT '角色id',
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

INSERT INTO `ums_user_role` (`id`, `uid`, `role_id`) VALUES
(1,	3,	2),
(2,	1,	2);

DROP TABLE IF EXISTS `ums_user_social`;
CREATE TABLE `ums_user_social` (
                                   `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
                                   `uid` bigint(20) unsigned DEFAULT NULL COMMENT '用户id',
                                   `social_type` bigint(20) unsigned DEFAULT NULL COMMENT '社交账号类型',
                                   `user_social_id` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户社交账号的唯一id',
                                   PRIMARY KEY (`id`) USING BTREE,
                                   KEY `user_social_id` (`user_social_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;
