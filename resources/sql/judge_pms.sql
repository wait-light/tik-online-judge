
SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

DROP TABLE IF EXISTS `pms_collection_group`;
CREATE TABLE `pms_collection_group` (
                                        `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                        `collection_id` bigint(20) DEFAULT NULL COMMENT '集合id',
                                        `group_id` bigint(20) DEFAULT NULL COMMENT '群组id',
                                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `pms_collection_group` (`id`, `collection_id`, `group_id`) VALUES
(3,	4,	15);

SET NAMES utf8mb4;

DROP TABLE IF EXISTS `pms_judge_result`;
CREATE TABLE `pms_judge_result` (
                                    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
                                    `submit_id` bigint(20) unsigned DEFAULT NULL COMMENT '对应的提交id',
                                    `judge_status` tinyint(4) DEFAULT NULL COMMENT '提交状态',
                                    `execution_time` int(20) DEFAULT NULL COMMENT '执行时常',
                                    `error_output` text COLLATE utf8mb4_unicode_ci COMMENT '错误信息',
                                    `success` bit(1) DEFAULT NULL COMMENT '是否成功',
                                    `runtime_memory` bigint(20) DEFAULT NULL,
                                    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

INSERT INTO `pms_judge_result` (`id`, `submit_id`, `judge_status`, `execution_time`, `error_output`, `success`, `runtime_memory`) VALUES
(94,	116,	8,	0,	NULL,	CONV('0', 2, 10) + 0,	3264),
(95,	117,	6,	0,	NULL,	CONV('1', 2, 10) + 0,	3348),
(96,	118,	8,	80,	NULL,	CONV('0', 2, 10) + 0,	24364),
(97,	119,	8,	90,	NULL,	CONV('0', 2, 10) + 0,	24724);

DROP TABLE IF EXISTS `pms_problem`;
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
                               `secret_key` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

INSERT INTO `pms_problem` (`id`, `name`, `uid`, `problem_describe`, `input`, `output`, `create_time`, `update_time`, `status`, `input_describle`, `output_describle`, `collection_id`, `share`, `secret_key`) VALUES
(1,	'A + B问题',	1,	'a + b 问题。给你两个数，求出两个数的和。',	'199 1',	'200',	'2021-11-08 15:21:05',	'2021-11-08 15:21:05',	CONV('1', 2, 10) + 0,	'a，b的长度在1~10000之间。',	'输入两个数的和。',	1,	CONV('1', 2, 10) + 0,	'qvAc7x1YTCt3pv1dVRIgPtS5tBvcBZ3b'),
(2,	'测试',	1,	'sadasd',	'1 1',	'2',	'2022-01-27 23:34:31',	'2022-01-27 23:34:31',	CONV('0', 2, 10) + 0,	'asdasd',	'asdasd',	4,	NULL,	'renslf4p1rw18wse74k8x064hl4q8vnn');

DROP TABLE IF EXISTS `pms_problem_collection`;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

INSERT INTO `pms_problem_collection` (`id`, `name`, `create_time`, `update_time`, `status`, `create_user_id`, `public_collection`, `begin_time`, `end_time`) VALUES
(1,	'基础',	'2021-11-17 16:41:49',	'2021-11-17 16:41:49',	CONV('1', 2, 10) + 0,	1,	CONV('1', 2, 10) + 0,	'2021-11-08 07:18:24',	'2021-11-07 07:18:25'),
(4,	'18计算机科学与技术一班',	'2021-11-20 15:04:13',	'2021-11-20 15:04:13',	CONV('1', 2, 10) + 0,	NULL,	CONV('0', 2, 10) + 0,	'2021-11-20 15:04:13',	'2021-11-20 15:04:13');

DROP TABLE IF EXISTS `pms_problem_collection_item`;
CREATE TABLE `pms_problem_collection_item` (
                                               `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
                                               `collection_id` bigint(20) unsigned DEFAULT NULL COMMENT '所属集合',
                                               `problem_id` bigint(20) unsigned DEFAULT NULL COMMENT '问题id',
                                               `status` bit(1) DEFAULT b'1' COMMENT '是否启用',
                                               PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

INSERT INTO `pms_problem_collection_item` (`id`, `collection_id`, `problem_id`, `status`) VALUES
(1,	1,	1,	CONV('1', 2, 10) + 0),
(2,	4,	2,	CONV('0', 2, 10) + 0);

DROP TABLE IF EXISTS `pms_problem_data`;
CREATE TABLE `pms_problem_data` (
                                    `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
                                    `problem_id` bigint(10) unsigned DEFAULT NULL COMMENT '问题id',
                                    `input` text COLLATE utf8mb4_unicode_ci COMMENT '输入',
                                    `output` text COLLATE utf8mb4_unicode_ci COMMENT '输出',
                                    `create_user_id` bigint(10) unsigned DEFAULT NULL COMMENT '数据提供者',
                                    `time_limit` int(20) DEFAULT NULL,
                                    `memory_limit` bigint(20) DEFAULT NULL,
                                    `score` int(20) DEFAULT NULL,
                                    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

INSERT INTO `pms_problem_data` (`id`, `problem_id`, `input`, `output`, `create_user_id`, `time_limit`, `memory_limit`, `score`) VALUES
(2,	1,	'1 1',	'2',	3,	5000,	131072,	1),
(3,	2,	'1 1',	'2',	1,	5000,	131072,	1);

DROP TABLE IF EXISTS `pms_problem_tag`;
CREATE TABLE `pms_problem_tag` (
                                   `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
                                   `problem_id` bigint(10) unsigned DEFAULT NULL COMMENT '问题id',
                                   `tag_id` bigint(10) unsigned DEFAULT NULL COMMENT '标签id',
                                   PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;


DROP TABLE IF EXISTS `pms_submit`;
CREATE TABLE `pms_submit` (
                              `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
                              `uid` bigint(10) unsigned DEFAULT NULL COMMENT '用户id',
                              `language_type` tinyint(3) unsigned DEFAULT NULL COMMENT '语言类型',
                              `content` text COLLATE utf8mb4_unicode_ci COMMENT '内容(编码内容)',
                              `create_time` datetime DEFAULT NULL COMMENT '提交时间',
                              `status` tinyint(4) DEFAULT NULL COMMENT '运行结果',
                              `problem_id` bigint(20) unsigned DEFAULT NULL COMMENT '提交的问题',
                              `runtime` int(20) DEFAULT NULL,
                              `runtime_memory` bigint(20) DEFAULT NULL,
                              `score` int(11) DEFAULT NULL,
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

INSERT INTO `pms_submit` (`id`, `uid`, `language_type`, `content`, `create_time`, `status`, `problem_id`, `runtime`, `runtime_memory`, `score`) VALUES
(116,	1,	1,	'#include <iostream>\nusing namespace std;\nint main()\n{\n    \n    return 0;\n}',	'2022-01-28 00:18:12',	8,	1,	0,	3264,	0),
(117,	1,	1,	'#include <iostream>\nusing namespace std;\nint main()\n{\n    int a,b;\n    cin >> a >> b;\n    cout << a+b;\n    return 0;\n}',	'2022-01-28 00:18:55',	6,	1,	0,	3348,	1),
(118,	3,	0,	'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}',	'2022-01-28 00:20:42',	8,	1,	80,	24364,	0),
(119,	3,	0,	'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}',	'2022-01-28 12:58:12',	8,	1,	90,	24724,	0);

DROP TABLE IF EXISTS `pms_tag`;
CREATE TABLE `pms_tag` (
                           `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
                           `name` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标签名',
                           `create_user_id` bigint(20) unsigned DEFAULT NULL COMMENT '创建人id',
                           `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;


DROP TABLE IF EXISTS `pms_task_rank`;
CREATE TABLE `pms_task_rank` (
                                 `task_id` bigint(20) NOT NULL,
                                 `uid` bigint(20) NOT NULL,
                                 `score` int(11) DEFAULT NULL,
                                 `penalty` bigint(20) DEFAULT NULL,
                                 PRIMARY KEY (`task_id`,`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `pms_task_rank` (`task_id`, `uid`, `score`, `penalty`) VALUES
(6,	1,	1,	31392),
(6,	3,	0,	31542),
(7,	3,	0,	28662);

DROP TABLE IF EXISTS `pms_task_submit`;
CREATE TABLE `pms_task_submit` (
                                   `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
                                   `task_id` bigint(20) DEFAULT NULL,
                                   `submit_id` bigint(20) DEFAULT NULL,
                                   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `pms_task_submit` (`id`, `task_id`, `submit_id`) VALUES
(23,	6,	116),
(24,	6,	117),
(25,	6,	118),
(26,	7,	119);

DROP TABLE IF EXISTS `pms_task_submit_item`;
CREATE TABLE `pms_task_submit_item` (
                                        `task_id` bigint(20) NOT NULL,
                                        `uid` bigint(20) NOT NULL,
                                        `problem_id` bigint(20) NOT NULL,
                                        `score` int(11) DEFAULT NULL,
                                        PRIMARY KEY (`task_id`,`uid`,`problem_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `pms_task_submit_item` (`task_id`, `uid`, `problem_id`, `score`) VALUES
(6,	1,	1,	1),
(6,	3,	1,	0),
(7,	3,	1,	0);