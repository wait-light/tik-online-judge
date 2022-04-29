/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : judge_pms

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 28/04/2022 22:44:19
*/
CREATE DATABASE IF NOT EXISTS judge_pms
USE judge_pms
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pms_collection_group
-- ----------------------------
DROP TABLE IF EXISTS `pms_collection_group`;
CREATE TABLE `pms_collection_group`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `collection_id` bigint NULL DEFAULT NULL COMMENT '集合id',
  `group_id` bigint NULL DEFAULT NULL COMMENT '群组id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pms_collection_group
-- ----------------------------
INSERT INTO `pms_collection_group` VALUES (3, 4, 15);
INSERT INTO `pms_collection_group` VALUES (4, 7, 16);
INSERT INTO `pms_collection_group` VALUES (5, 8, 17);

-- ----------------------------
-- Table structure for pms_judge_result
-- ----------------------------
DROP TABLE IF EXISTS `pms_judge_result`;
CREATE TABLE `pms_judge_result`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `submit_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '对应的提交id',
  `judge_status` tinyint NULL DEFAULT NULL COMMENT '提交状态',
  `execution_time` int NULL DEFAULT NULL COMMENT '执行时常',
  `error_output` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '错误信息',
  `success` bit(1) NULL DEFAULT NULL COMMENT '是否成功',
  `runtime_memory` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2151 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of pms_judge_result
-- ----------------------------

-- ----------------------------
-- Table structure for pms_problem
-- ----------------------------
DROP TABLE IF EXISTS `pms_problem`;
CREATE TABLE `pms_problem`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '问题名称',
  `uid` bigint UNSIGNED NULL DEFAULT NULL COMMENT '上传人',
  `problem_describe` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '问题描述',
  `input` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '输入',
  `output` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '输出',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `status` bit(1) NULL DEFAULT b'1' COMMENT '状态',
  `input_describle` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '输入描述',
  `output_describle` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '输出描述',
  `collection_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '所属合集',
  `share` bit(1) NULL DEFAULT NULL COMMENT '是否与其他集合共享',
  `secret_key` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of pms_problem
-- ----------------------------
INSERT INTO `pms_problem` VALUES (1, 'A + B问题', 1, 'a + b 问题。给你两个数，求出两个数的和。', '199 1', '200', '2021-11-08 15:21:05', '2022-03-17 19:36:58', b'1', 'a，b的长度在1~10000之间。', '输入两个数的和。', 1, b'1', 'qvAc7x1YTCt3pv1dVRIgPtS5tBvcBZ3b');
INSERT INTO `pms_problem` VALUES (2, '测试', 1, 'sadasd', '1 1\n1 1', '2', '2022-01-27 23:34:31', '2022-03-18 13:04:36', b'0', 'asdasd', 'asdasd', 4, NULL, 'renslf4p1rw18wse74k8x064hl4q8vnn');
INSERT INTO `pms_problem` VALUES (3, '修改测试', 1, '阿斯蒂芬', 'a b', 'c', '2022-03-17 14:16:25', '2022-03-17 14:16:25', b'1', '阿斯蒂芬', '阿斯蒂芬', 4, NULL, 'aytfmh5ntpy8dll2ex208tzps0b3y8wi');
INSERT INTO `pms_problem` VALUES (17, '寻找两个正序数组的中位数', 1, '给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。\n\n算法的时间复杂度应该为 `O(log (m+n)) `。\n', '1 3\n2', '2.00000', '2022-03-17 20:04:57', '2022-03-18 13:00:46', b'1', '数据范围\n\nnums1.length == m\n\nnums2.length == n\n\n0 <= m <= 1000\n\n0 <= n <= 1000\n\n1 <= m + n <= 2000\n\n-106 <= nums1[i], nums2[i] <= 106\n\n', '', NULL, b'1', 'a11srazu9xpw4v7vvbxoooh3knguuqmw');
INSERT INTO `pms_problem` VALUES (19, '【NOI2020】命运', 1, '提示：我们在题目描述的最后一段提供了一份简要的、形式化描述的题面。\n\n在遥远的未来，物理学家终于发现了时间和因果的自然规律。即使在一个人出生前，我们也可以通过理论分析知晓他或她人生的一些信息，换言之，物理学允许我们从一定程度上“预言”一个人的“命运”。\n\n简单来说，一个人的命运是一棵由时间点构成的有根树 T：树的根结点代表着出生，而叶结点代表着死亡。每个非叶结点 u 都有一个或多个孩子 v1,v2,⋯,vcu，表示这个人 在 u 所代表的时间点做出的 cu 个不同的选择可以导向的不同的可能性。形式化的，一个选择就是树上的一条边 (u,vi)，其中 u 是 vi 的父结点。\n\n一个人的一生是从出生（即根结点）到死亡（即某一个叶子结点）的一条不经过重复结点的路径，这条路径上任何一个包含至少一条边的子路径都是这个人的一段人生经历，而他或她以所有可能的方式度过一生，从而拥有的所有人生经历，都被称为潜在的人生经历。换言之，所有潜在的人生经历就是所有 u 到 v 的路径，满足 u,v∈T，u≠v， 并且 u 是 v 的祖先。在数学上，这样一个潜在的人生经历被记作有序对 (u,v)，树 T 所有潜在的人生经历的集合记作 PT。\n\n物理理论不仅允许我们观测代表命运的树，还能让我们分析一些潜在的人生经历是否是“重要”的。一个人所作出的每一个选择——即树上的每一条边 —— 都可能是重要或不重要的。一段潜在的人生经历被称为重要的，当且仅当其对应的路径上存在一条边是重要的。我们可以观测到一些潜在的人生经历是重要的：换言之，我们可以观测得到 一个集合 Q⊆PT，满足其中的所有潜在的人生经历 (u,v)∈Q 都是重要的。\n\n树 T 的形态早已被计算确定，集合 Q 也早已被观测得到，一个人命运的不确定性 已经大大降低了。但不确定性仍然是巨大的 —— 来计算一下吧，对于给定的树 T 和集合 Q，存在多少种不同的方案确定每条边是否是重要的，使之满足所观测到的 Q 所对应的限制：即对于任意 (u,v)∈Q，都存在一条 u 到 v 路径上的边被确定为重要的。\n\n形式化的：给定一棵树 T=(V,E) 和点对集合 Q⊂V×V ，满足对于所有 (u,v)∈Q， 都有 u≠v，并且 u 是 v 在树 T 上的祖先。其中 V 和 E 分别代表树 T 的结点集和边集。求有多少个不同的函数 f:E→0,1（将每条边 e∈E 的 f(e) 值置为 0 或 1），满足对于任何 (u,v)∈Q，都存在 u 到 v 路径上的一条边 e 使得 f(e)=1。由于答案可能非常大，你只需要输出结果对 998,244,353（一个素数）取模的结果。', '5\n1 2\n2 3\n3 4\n3 5\n2\n1 3\n2 5', '10', '2022-03-20 14:25:46', '2022-03-20 14:25:46', b'1', '第一行包含一个正整数 n，表示树 T 的大小，树上结点从 1 到 n 编号，1 号结点为根结点；\n\n接下来 n−1 行每行包含空格隔开的两个数 xi,yi，满足 1≤xi,yi≤n，表示树 上的结点 xi 和 yi 之间存在一条边，但并不保证这条边的方向；\n\n接下来一行包含一个非负整数 m，表示所观测得到信息的条数。\n\n接下来 m 行每行包含空格隔开的两个数 ui,vi，表示 (ui,vi)∈Q。请注意：输入数据可能包含重复的信息，换言之可能存在 i≠j，满足 ui=uj 且 vi=vj。\n\n输入数据规模和限制参见本题末尾的表格。\n', '输出仅一行一个整数，表示方案数对 998,244,353 取模的结果。', NULL, b'1', '5ve6oakqto4mvbgsycy2fdmu3di0q3is');
INSERT INTO `pms_problem` VALUES (20, '【美团杯2020】分形之美', 1, '蒜斜一直怀疑自己和北大算协是高维空间中某个分形的两个分支。支撑这个观点的证据有很多。其一，他们都叫蒜斜（算协）；其二，他们都低调、奢华、有内涵，帅气、阳光、有魅力；其三，他们的好朋友都叫镁团（美团）。\n\n蒜斜也常常思考，在高维空间里，他和北大算协之间有什么关系呢？这个问题过于高端，让我们先来看一个二维的版本吧。\n\n定义如下的分形：\n\n1. 0 级的 o 是一个 1×1 的字符矩阵，里面包含了一个字符 o。\n2. 0 级的 x 是一个 1×1 的字符矩阵，里面包含了一个字符 x。\n3. i 级的 x 和 o 均是 3i×3i 的字符矩阵，它们通过将 i−1 级别的 x,o 通过如下方式排列得到（左侧表示 x，右侧表示 o）：\n\n```\nxox ooo\noxo oxo\nxox ooo\n```\n举例来说，二级的 x 和 o 分别为：\n\n```\nxoxoooxox ooooooooo\noxooxooxo oxooxooxo\nxoxoooxox ooooooooo\noooxoxooo oooxoxooo\noxooxooxo oxooxooxo\noooxoxooo oooxoxooo\nxoxoooxox ooooooooo\noxooxooxo oxooxooxo\nxoxoooxox ooooooooo\n```\n\n一个人穷极一生，接触到的也只不过是世界的一部分。在这个二维的版本中，这个范围可以表示成 n 级别的 x 中的一个子矩形：它包含第 xl 行至第 xr 行的第 yl 列至 yr 列。\n\n人一生中接触到的一些事物是连续的，就像这个分形中的 o 一样；而又有一些事物是突然发生的，就像这个分形中的 x 一样。而这些突然发生的事物又把连续的一生给划分成了一块一块丰富多彩的篇章。在这题中，蒜斜希望你计算 n 级 x 的某一个子矩形中，o 被分割成了多少个四连通块。\n\n给定一个矩阵，两个字符 o 在同一个四连通块里，当且仅当从一个 o 出发，不停的向上下左右移动，可以在只经过 o 的情况下到达另一个 o。', '5\n1 1 3 1 3\n2 1 9 1 9\n2 3 5 4 8\n2 1 6 2 7\n35 1 50031545098999707 1 50031545098999707', '4\n12\n3\n5\n679477107', '2022-03-20 14:30:42', '2022-03-20 14:30:42', b'1', '输入第一行是一个整数 t(1≤t≤50)，表示数据组数。\n\n对于每组数据，输入包含一行五个整数 n,xl,xr,yl,yr(1≤n≤35,1≤xl≤xr≤3n,1≤yl≤yr≤3n)。', '对于每组数据，输出一行一个整数表示 o 的四连通块个数。答案可能很大，你只需要输出对 998244353 取模后的值。', NULL, b'1', 'kcqhuca1va2eqpg9c1zl9cf1034az5lo');

-- ----------------------------
-- Table structure for pms_problem_collection
-- ----------------------------
DROP TABLE IF EXISTS `pms_problem_collection`;
CREATE TABLE `pms_problem_collection`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `status` bit(1) NULL DEFAULT NULL COMMENT '是否启用',
  `create_user_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '创建人',
  `public_collection` bit(1) NULL DEFAULT NULL COMMENT '是否公开集',
  `begin_time` datetime NULL DEFAULT NULL COMMENT '开启时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of pms_problem_collection
-- ----------------------------
INSERT INTO `pms_problem_collection` VALUES (1, '基础', '2021-11-17 16:41:49', '2021-11-17 16:41:49', b'1', 1, b'1', '2021-11-08 07:18:24', '2021-11-07 07:18:25');
INSERT INTO `pms_problem_collection` VALUES (4, '18计算机科学与技术一班', '2021-11-20 15:04:13', '2021-11-20 15:04:13', b'1', NULL, b'0', '2021-11-20 15:04:13', '2021-11-20 15:04:13');
INSERT INTO `pms_problem_collection` VALUES (5, '提升集', NULL, NULL, b'1', NULL, b'1', '2022-03-21 17:50:47', '2022-03-20 00:00:00');
INSERT INTO `pms_problem_collection` VALUES (6, '另一个公开集合', NULL, NULL, b'1', NULL, b'1', '2022-03-21 17:55:24', '2022-03-20 00:00:00');
INSERT INTO `pms_problem_collection` VALUES (7, 'ACM培训班', '2022-03-21 18:35:10', '2022-03-21 18:35:10', b'1', NULL, b'0', '2022-03-21 18:35:10', '2022-03-21 18:35:10');
INSERT INTO `pms_problem_collection` VALUES (8, '暑期提高班', '2022-03-22 15:34:19', '2022-03-22 15:34:19', b'1', NULL, b'0', '2022-03-22 15:34:19', '2022-03-22 15:34:19');

-- ----------------------------
-- Table structure for pms_problem_collection_item
-- ----------------------------
DROP TABLE IF EXISTS `pms_problem_collection_item`;
CREATE TABLE `pms_problem_collection_item`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `collection_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '所属集合',
  `problem_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '问题id',
  `status` bit(1) NULL DEFAULT b'1' COMMENT '是否启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of pms_problem_collection_item
-- ----------------------------
INSERT INTO `pms_problem_collection_item` VALUES (1, 1, 1, b'1');
INSERT INTO `pms_problem_collection_item` VALUES (2, 4, 2, b'0');
INSERT INTO `pms_problem_collection_item` VALUES (3, 4, 3, b'1');
INSERT INTO `pms_problem_collection_item` VALUES (8, 1, 17, b'1');
INSERT INTO `pms_problem_collection_item` VALUES (9, 1, 19, b'1');
INSERT INTO `pms_problem_collection_item` VALUES (10, 1, 20, b'1');

-- ----------------------------
-- Table structure for pms_problem_data
-- ----------------------------
DROP TABLE IF EXISTS `pms_problem_data`;
CREATE TABLE `pms_problem_data`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `problem_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '问题id',
  `input` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '输入',
  `output` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '输出',
  `create_user_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '数据提供者',
  `time_limit` int NULL DEFAULT NULL,
  `memory_limit` bigint NULL DEFAULT NULL,
  `score` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of pms_problem_data
-- ----------------------------
INSERT INTO `pms_problem_data` VALUES (2, 1, '1 1', '2', 3, 5000, 131072, 1);
INSERT INTO `pms_problem_data` VALUES (3, 2, '1 1', '2', 1, 5000, 131072, 1);
INSERT INTO `pms_problem_data` VALUES (4, 1, '2 2', '4', 1, NULL, NULL, NULL);
INSERT INTO `pms_problem_data` VALUES (5, 17, '1 3\n2', '2.00000', 1, NULL, NULL, NULL);
INSERT INTO `pms_problem_data` VALUES (6, 17, '1 2\n3 4', '2.50000', 1, NULL, NULL, NULL);
INSERT INTO `pms_problem_data` VALUES (7, 19, '5\n1 2\n2 3\n3 4\n3 5\n2\n1 3\n2 5', '10', 1, NULL, NULL, NULL);
INSERT INTO `pms_problem_data` VALUES (8, 19, '15\n2 1\n3 1\n4 3\n5 2\n6 3\n7 6\n8 4\n9 5\n10 7\n11 5\n12 10\n13 3\n14 9\n15 8\n6\n3 12\n5 11\n2 5\n3 13\n8 15\n1 13', '960', 1, NULL, NULL, NULL);
INSERT INTO `pms_problem_data` VALUES (9, 20, '5\n1 1 3 1 3\n2 1 9 1 9\n2 3 5 4 8\n2 1 6 2 7\n35 1 50031545098999707 1 50031545098999707', '4\n12\n3\n5\n679477107', 1, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for pms_problem_tag
-- ----------------------------
DROP TABLE IF EXISTS `pms_problem_tag`;
CREATE TABLE `pms_problem_tag`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `problem_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '问题id',
  `tag_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '标签id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of pms_problem_tag
-- ----------------------------

-- ----------------------------
-- Table structure for pms_submit
-- ----------------------------
DROP TABLE IF EXISTS `pms_submit`;
CREATE TABLE `pms_submit`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `uid` bigint UNSIGNED NULL DEFAULT NULL COMMENT '用户id',
  `language_type` tinyint UNSIGNED NULL DEFAULT NULL COMMENT '语言类型',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '内容(编码内容)',
  `create_time` datetime NULL DEFAULT NULL COMMENT '提交时间',
  `status` tinyint NULL DEFAULT NULL COMMENT '运行结果',
  `problem_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '提交的问题',
  `runtime` int NULL DEFAULT NULL,
  `runtime_memory` bigint NULL DEFAULT NULL,
  `score` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1171 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of pms_submit
-- ----------------------------

-- ----------------------------
-- Table structure for pms_tag
-- ----------------------------
DROP TABLE IF EXISTS `pms_tag`;
CREATE TABLE `pms_tag`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标签名',
  `create_user_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of pms_tag
-- ----------------------------

-- ----------------------------
-- Table structure for pms_task_rank
-- ----------------------------
DROP TABLE IF EXISTS `pms_task_rank`;
CREATE TABLE `pms_task_rank`  (
  `task_id` bigint NOT NULL,
  `uid` bigint NOT NULL,
  `score` int NULL DEFAULT NULL,
  `penalty` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`task_id`, `uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pms_task_rank
-- ----------------------------

-- ----------------------------
-- Table structure for pms_task_submit
-- ----------------------------
DROP TABLE IF EXISTS `pms_task_submit`;
CREATE TABLE `pms_task_submit`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `task_id` bigint NULL DEFAULT NULL,
  `submit_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pms_task_submit
-- ----------------------------

-- ----------------------------
-- Table structure for pms_task_submit_item
-- ----------------------------
DROP TABLE IF EXISTS `pms_task_submit_item`;
CREATE TABLE `pms_task_submit_item`  (
  `task_id` bigint NOT NULL,
  `uid` bigint NOT NULL,
  `problem_id` bigint NOT NULL,
  `score` int NULL DEFAULT NULL,
  PRIMARY KEY (`task_id`, `uid`, `problem_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pms_task_submit_item
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
