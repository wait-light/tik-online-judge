/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50736
Source Host           : 127.0.0.1:3306
Source Database       : judge_pms

Target Server Type    : MYSQL
Target Server Version : 50736
File Encoding         : 65001

Date: 2021-12-27 21:14:05
*/
CREATE DATABASE judge_pms;
USE judge_pms;
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for pms_collection_group
-- ----------------------------
DROP TABLE IF EXISTS `pms_collection_group`;
CREATE TABLE `pms_collection_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `collection_id` bigint(20) DEFAULT NULL COMMENT '集合id',
  `group_id` bigint(20) DEFAULT NULL COMMENT '群组id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of pms_collection_group
-- ----------------------------
INSERT INTO `pms_collection_group` VALUES ('3', '4', '15');

-- ----------------------------
-- Table structure for pms_judge_result
-- ----------------------------
DROP TABLE IF EXISTS `pms_judge_result`;
CREATE TABLE `pms_judge_result` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `submit_id` bigint(20) unsigned DEFAULT NULL COMMENT '对应的提交id',
  `judge_status` tinyint(4) DEFAULT NULL COMMENT '提交状态',
  `execution_time` bigint(20) DEFAULT NULL COMMENT '执行时常',
  `error_output` text COLLATE utf8mb4_unicode_ci COMMENT '错误信息',
  `success` bit(1) DEFAULT NULL COMMENT '是否成功',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of pms_judge_result
-- ----------------------------
INSERT INTO `pms_judge_result` VALUES ('1', '3', '8', '90', null, '\0');
INSERT INTO `pms_judge_result` VALUES ('2', '4', '8', '8', null, '\0');
INSERT INTO `pms_judge_result` VALUES ('3', '5', '8', '108', null, '\0');
INSERT INTO `pms_judge_result` VALUES ('4', '6', '8', '0', null, '\0');
INSERT INTO `pms_judge_result` VALUES ('5', '7', '8', '16', null, '\0');
INSERT INTO `pms_judge_result` VALUES ('6', '8', '6', '3', null, '');
INSERT INTO `pms_judge_result` VALUES ('7', '9', '7', '0', null, '\0');
INSERT INTO `pms_judge_result` VALUES ('8', '11', '8', '3', null, '\0');
INSERT INTO `pms_judge_result` VALUES ('9', '12', '8', '0', null, '\0');
INSERT INTO `pms_judge_result` VALUES ('10', '13', '8', '0', null, '\0');
INSERT INTO `pms_judge_result` VALUES ('11', '14', '8', '0', null, '\0');
INSERT INTO `pms_judge_result` VALUES ('12', '15', '8', '30', null, '\0');
INSERT INTO `pms_judge_result` VALUES ('13', '16', '8', '0', null, '\0');
INSERT INTO `pms_judge_result` VALUES ('14', '17', '6', '30', null, '');
INSERT INTO `pms_judge_result` VALUES ('15', '18', '4', '4611686018427387804', null, '\0');
INSERT INTO `pms_judge_result` VALUES ('16', '19', '4', '4611686018427387804', null, '\0');
INSERT INTO `pms_judge_result` VALUES ('17', '20', '4', '4611686018427387804', null, '\0');
INSERT INTO `pms_judge_result` VALUES ('18', '21', '4', '4611686018427387804', null, '\0');
INSERT INTO `pms_judge_result` VALUES ('19', '22', '4', '4611686018427387804', null, '\0');
INSERT INTO `pms_judge_result` VALUES ('20', '27', '8', '0', null, '\0');
INSERT INTO `pms_judge_result` VALUES ('21', '28', '7', '0', null, '\0');
INSERT INTO `pms_judge_result` VALUES ('22', '29', '6', '0', null, '');
INSERT INTO `pms_judge_result` VALUES ('23', '30', '6', '0', null, '');
INSERT INTO `pms_judge_result` VALUES ('24', '34', '4', '4611686018427387804', null, '\0');
INSERT INTO `pms_judge_result` VALUES ('25', '35', '6', '0', null, '');
INSERT INTO `pms_judge_result` VALUES ('26', '36', '7', '0', null, '\0');

-- ----------------------------
-- Table structure for pms_problem
-- ----------------------------
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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of pms_problem
-- ----------------------------
INSERT INTO `pms_problem` VALUES ('1', 'A + B问题', '1', 'a + b 问题。给你两个数，求出两个数的和。', '199 1', '200', '2021-11-08 15:21:05', '2021-11-08 15:21:05', '', 'a，b的长度在1~10000之间。', '输入两个数的和。', '1', '');

-- ----------------------------
-- Table structure for pms_problem_collection
-- ----------------------------
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of pms_problem_collection
-- ----------------------------
INSERT INTO `pms_problem_collection` VALUES ('1', '基础', '2021-11-17 16:41:49', '2021-11-17 16:41:49', '', '1', '', '2021-11-08 07:18:24', '2021-11-07 07:18:25');
INSERT INTO `pms_problem_collection` VALUES ('4', '18计算机科学与技术一班', '2021-11-20 15:04:13', '2021-11-20 15:04:13', '', null, '\0', '2021-11-20 15:04:13', '2021-11-20 15:04:13');

-- ----------------------------
-- Table structure for pms_problem_collection_item
-- ----------------------------
DROP TABLE IF EXISTS `pms_problem_collection_item`;
CREATE TABLE `pms_problem_collection_item` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `collection_id` bigint(20) unsigned DEFAULT NULL COMMENT '所属集合',
  `problem_id` bigint(20) unsigned DEFAULT NULL COMMENT '问题id',
  `status` bit(1) DEFAULT b'1' COMMENT '是否启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of pms_problem_collection_item
-- ----------------------------
INSERT INTO `pms_problem_collection_item` VALUES ('1', '1', '1', '');

-- ----------------------------
-- Table structure for pms_problem_data
-- ----------------------------
DROP TABLE IF EXISTS `pms_problem_data`;
CREATE TABLE `pms_problem_data` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `problem_id` bigint(10) unsigned DEFAULT NULL COMMENT '问题id',
  `input` text COLLATE utf8mb4_unicode_ci COMMENT '输入',
  `output` text COLLATE utf8mb4_unicode_ci COMMENT '输出',
  `create_user_id` bigint(10) unsigned DEFAULT NULL COMMENT '数据提供者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of pms_problem_data
-- ----------------------------
INSERT INTO `pms_problem_data` VALUES ('2', '1', '1 1', '2', '3');

-- ----------------------------
-- Table structure for pms_problem_tag
-- ----------------------------
DROP TABLE IF EXISTS `pms_problem_tag`;
CREATE TABLE `pms_problem_tag` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `problem_id` bigint(10) unsigned DEFAULT NULL COMMENT '问题id',
  `tag_id` bigint(10) unsigned DEFAULT NULL COMMENT '标签id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of pms_problem_tag
-- ----------------------------

-- ----------------------------
-- Table structure for pms_submit
-- ----------------------------
DROP TABLE IF EXISTS `pms_submit`;
CREATE TABLE `pms_submit` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `uid` bigint(10) unsigned DEFAULT NULL COMMENT '用户id',
  `language_type` tinyint(3) unsigned DEFAULT NULL COMMENT '语言类型',
  `content` text COLLATE utf8mb4_unicode_ci COMMENT '内容(编码内容)',
  `create_time` datetime DEFAULT NULL COMMENT '提交时间',
  `status` tinyint(4) DEFAULT NULL COMMENT '运行结果',
  `problem_id` bigint(20) unsigned DEFAULT NULL COMMENT '提交的问题',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of pms_submit
-- ----------------------------
INSERT INTO `pms_submit` VALUES ('4', '1', '0', 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-11-09 22:26:28', '8', '1');
INSERT INTO `pms_submit` VALUES ('5', '1', '0', 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        Scanner in = new Scanner(System.in);\n        System.out.println(in.nextInt() + in.nextInt());\n    }\n}', '2021-11-09 22:28:21', '8', '1');
INSERT INTO `pms_submit` VALUES ('6', '1', '0', 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        Scanner in = new Scanner(System.in);\n        System.out.print(in.nextInt() + in.nextInt());\n    }\n}', '2021-11-09 22:28:47', '8', '1');
INSERT INTO `pms_submit` VALUES ('7', '1', '0', 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        Scanner in = new Scanner(System.in);\n        System.out.print(in.nextInt() + in.nextInt());\n    }\n}', '2021-11-09 22:33:12', '8', '1');
INSERT INTO `pms_submit` VALUES ('8', '1', '0', 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        Scanner in = new Scanner(System.in);\n        System.out.print(in.nextInt() + in.nextInt());\n    }\n}', '2021-11-09 22:41:19', '6', '1');
INSERT INTO `pms_submit` VALUES ('9', '1', '0', 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        Scanner in = new Scanner(System.in);\n        System.out.println(in.nextInt() + in.nextInt());\n    }\n}', '2021-11-09 22:41:31', '7', '1');
INSERT INTO `pms_submit` VALUES ('10', '1', '0', 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-11-18 21:51:37', null, '1');
INSERT INTO `pms_submit` VALUES ('11', '1', '0', 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-11-18 22:09:53', '8', '1');
INSERT INTO `pms_submit` VALUES ('12', '1', '0', 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-11-18 22:10:15', '8', '1');
INSERT INTO `pms_submit` VALUES ('13', '1', '0', 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-11-18 22:10:18', '8', '1');
INSERT INTO `pms_submit` VALUES ('14', '1', '0', 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-11-18 22:10:21', '8', '1');
INSERT INTO `pms_submit` VALUES ('15', '1', '0', 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-11-20 21:14:14', '8', '1');
INSERT INTO `pms_submit` VALUES ('16', '3', '0', 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-11-20 21:17:20', '8', '1');
INSERT INTO `pms_submit` VALUES ('17', '3', '0', 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        Scanner in = new Scanner(System.in);\n        int a = in.nextInt();\n        int b = in.nextInt();\n        System.out.print(a + b);\n    }\n}', '2021-11-23 14:54:46', '6', '1');
INSERT INTO `pms_submit` VALUES ('18', '1', '0', 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-12-20 16:13:41', '4', '1');
INSERT INTO `pms_submit` VALUES ('19', '1', '0', 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-12-20 16:14:03', '4', '1');
INSERT INTO `pms_submit` VALUES ('20', '1', '0', 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-12-20 16:14:09', '4', '1');
INSERT INTO `pms_submit` VALUES ('21', '1', '0', 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-12-20 16:14:23', '4', '1');
INSERT INTO `pms_submit` VALUES ('22', '1', '0', 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-12-20 16:25:55', '4', '1');
INSERT INTO `pms_submit` VALUES ('23', '1', '0', 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-12-20 16:43:49', null, '1');
INSERT INTO `pms_submit` VALUES ('24', '1', '0', 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-12-20 16:44:17', null, '1');
INSERT INTO `pms_submit` VALUES ('25', '1', '0', 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-12-20 16:46:58', null, '1');
INSERT INTO `pms_submit` VALUES ('26', '1', '0', 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-12-20 16:48:04', null, '1');
INSERT INTO `pms_submit` VALUES ('27', '1', '0', 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-12-20 16:49:50', '8', '1');
INSERT INTO `pms_submit` VALUES ('28', '1', '0', 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        Scanner in = new Scanner(System.in);\n        System.out.println(in.nextInt() + in.nextInt());\n    }\n}', '2021-12-20 16:51:12', '7', '1');
INSERT INTO `pms_submit` VALUES ('29', '1', '0', 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        Scanner in = new Scanner(System.in);\n        System.out.print(in.nextInt() + in.nextInt());\n    }\n}', '2021-12-20 16:51:19', '6', '1');
INSERT INTO `pms_submit` VALUES ('30', '1', '0', 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        Scanner in = new Scanner(System.in);\n        System.out.print(in.nextInt() + in.nextInt());\n    }\n}', '2021-12-20 17:01:13', '6', '1');
INSERT INTO `pms_submit` VALUES ('31', '1', '1', '#include <iostream>\nusing namespace std;\nint main()\n{\n    int a,b;\n    cin >> a >> b;\n    cout << a + b;\n    return 0;\n}', '2021-12-20 17:03:40', null, '1');
INSERT INTO `pms_submit` VALUES ('32', '1', '1', '#include <iostream>\nusing namespace std;\nint main()\n{\n    int a,b;\n    cin >> a >> b;\n    cout << a + b;\n    return 0;\n}', '2021-12-20 17:06:11', null, '1');
INSERT INTO `pms_submit` VALUES ('33', '1', '1', '#include <iostream>\nusing namespace std;\nint main()\n{\n    \n    return 0;\n}', '2021-12-20 17:09:02', null, '1');
INSERT INTO `pms_submit` VALUES ('34', '1', '1', '#include <iostream>\nusing namespace std;\nint main()\n{\n    int a,b;\n    cin >> a >> b;\n    cout << a + b;\n    return 0;\n}', '2021-12-20 17:12:13', '4', '1');
INSERT INTO `pms_submit` VALUES ('35', '1', '1', '#include <iostream>\nusing namespace std;\nint main()\n{\n    int a,b;\n    cin >> a >> b;\n    cout << a + b;\n    return 0;\n}', '2021-12-20 17:17:39', '6', '1');
INSERT INTO `pms_submit` VALUES ('36', '1', '1', '#include <iostream>\nusing namespace std;\nint main()\n{\n    int a,b;\n    cin >> a >> b;\n    cout << a + b << endl;\n    return 0;\n}', '2021-12-20 17:17:52', '7', '1');

-- ----------------------------
-- Table structure for pms_tag
-- ----------------------------
DROP TABLE IF EXISTS `pms_tag`;
CREATE TABLE `pms_tag` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标签名',
  `create_user_id` bigint(20) unsigned DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of pms_tag
-- ----------------------------
