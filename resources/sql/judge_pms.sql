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

 Date: 03/10/2021 10:48:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pms_judge_result
-- ----------------------------
DROP TABLE IF EXISTS `pms_judge_result`;
CREATE TABLE `pms_judge_result`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `submit_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '对应的提交id',
  `judge_status` tinyint(4) NULL DEFAULT NULL COMMENT '提交状态',
  `execution_time` bigint(20) NULL DEFAULT NULL COMMENT '执行时常',
  `error_output` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '错误信息',
  `success` bit(1) NULL DEFAULT NULL COMMENT '是否成功',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 858 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pms_judge_result
-- ----------------------------
INSERT INTO `pms_judge_result` VALUES (663, 204, 8, 109, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (664, 204, 8, 107, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (665, 204, 8, 116, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (666, 204, 8, 106, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (667, 205, 8, 108, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (668, 205, 8, 111, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (669, 205, 8, 107, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (670, 205, 8, 122, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (671, 206, 8, 109, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (672, 206, 8, 101, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (673, 206, 8, 110, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (674, 206, 8, 113, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (675, 207, 6, 132, NULL, b'1');
INSERT INTO `pms_judge_result` VALUES (676, 207, 6, 143, NULL, b'1');
INSERT INTO `pms_judge_result` VALUES (677, 207, 6, 129, NULL, b'1');
INSERT INTO `pms_judge_result` VALUES (678, 207, 6, 134, NULL, b'1');
INSERT INTO `pms_judge_result` VALUES (679, 208, 8, 109, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (680, 208, 8, 107, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (681, 208, 8, 118, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (682, 208, 8, 116, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (683, 209, 8, 116, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (684, 209, 8, 118, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (685, 209, 8, 110, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (686, 209, 8, 112, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (687, 210, 8, 111, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (688, 210, 8, 115, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (689, 210, 8, 112, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (690, 210, 8, 195, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (691, 211, 8, 116, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (692, 211, 8, 109, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (693, 211, 8, 103, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (694, 211, 8, 113, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (695, 212, 8, 102, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (696, 212, 8, 106, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (697, 212, 8, 105, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (698, 212, 8, 101, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (699, 213, 8, 110, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (700, 213, 8, 111, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (701, 213, 8, 112, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (702, 213, 8, 111, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (703, 214, 8, 102, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (704, 214, 8, 104, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (705, 214, 8, 104, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (706, 214, 8, 103, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (707, 215, 8, 102, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (708, 215, 8, 104, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (709, 215, 8, 105, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (710, 215, 8, 112, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (711, 216, 8, 111, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (712, 216, 8, 111, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (713, 216, 8, 103, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (714, 216, 8, 105, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (715, 217, 8, 107, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (716, 217, 8, 107, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (717, 217, 8, 108, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (718, 217, 8, 104, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (719, 218, 8, 110, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (720, 218, 8, 111, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (721, 218, 8, 113, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (722, 218, 8, 103, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (723, 219, 8, 104, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (724, 219, 8, 109, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (725, 219, 8, 111, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (726, 219, 8, 104, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (727, 220, 8, 111, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (728, 220, 8, 118, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (729, 220, 8, 110, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (730, 220, 8, 110, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (731, 221, 8, 111, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (732, 221, 8, 134, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (733, 221, 8, 104, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (734, 221, 8, 103, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (735, 222, 8, 106, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (736, 222, 8, 110, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (737, 222, 8, 121, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (738, 222, 8, 114, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (739, 223, 8, 110, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (740, 223, 8, 111, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (741, 223, 8, 111, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (742, 223, 8, 113, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (743, 224, 8, 108, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (744, 224, 8, 115, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (745, 224, 8, 120, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (746, 224, 8, 117, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (747, 225, 8, 126, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (748, 225, 8, 111, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (749, 225, 8, 107, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (750, 225, 8, 109, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (751, 226, 8, 107, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (752, 226, 8, 114, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (753, 226, 8, 102, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (754, 226, 8, 108, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (755, 227, 8, 117, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (756, 227, 8, 113, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (757, 227, 8, 106, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (758, 227, 8, 117, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (759, 228, 8, 116, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (760, 228, 8, 106, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (761, 228, 8, 108, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (762, 228, 8, 114, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (763, 229, 8, 116, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (764, 229, 8, 108, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (765, 229, 8, 106, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (766, 229, 8, 107, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (767, 230, 8, 106, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (768, 230, 8, 111, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (769, 230, 8, 110, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (770, 230, 8, 122, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (771, 231, 8, 126, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (772, 231, 8, 117, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (773, 231, 8, 105, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (774, 231, 8, 106, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (775, 232, 8, 113, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (776, 232, 8, 104, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (777, 232, 8, 128, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (778, 232, 8, 115, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (779, 233, 8, 182, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (780, 233, 8, 282, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (781, 233, 8, 369, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (782, 233, 3, 356, 'java.lang.ClassFormatError: Truncated class file\r\n	at java.lang.ClassLoader.defineClass1(Native Method)\r\n	at java.lang.ClassLoader.defineClass(ClassLoader.java:763)\r\n	at java.security.SecureClassLoader.defineClass(SecureClassLoader.java:142)\r\n	at java.net.URLClassLoader.defineClass(URLClassLoader.java:467)\r\n	at java.net.URLClassLoader.access$100(URLClassLoader.java:73)\r\n	at java.net.URLClassLoader$1.run(URLClassLoader.java:368)\r\n	at java.net.URLClassLoader$1.run(URLClassLoader.java:362)\r\n	at java.security.AccessController.doPrivileged(Native Method)\r\n	at java.net.URLClassLoader.findClass(URLClassLoader.java:361)\r\n	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)\r\n	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:349)\r\n	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)\r\n	at sun.launcher.LauncherHelper.checkAndLoadMain(LauncherHelper.java:495)\r\nError: A JNI error has occurred, please check your installation and try again\r\nException in thread \"main\" ', b'0');
INSERT INTO `pms_judge_result` VALUES (783, 235, 8, 255, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (784, 235, 8, 309, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (785, 235, 8, 190, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (786, 235, 8, 178, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (787, 234, 8, 352, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (788, 234, 8, 189, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (789, 234, 8, 306, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (790, 234, 8, 165, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (791, 236, 8, 258, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (792, 236, 8, 344, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (793, 236, 8, 335, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (794, 236, 8, 357, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (795, 237, 8, 295, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (796, 237, 8, 251, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (797, 237, 8, 338, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (798, 237, 8, 225, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (799, 239, 8, 152, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (800, 239, 8, 204, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (801, 239, 8, 167, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (802, 239, 8, 153, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (803, 238, 8, 157, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (804, 238, 8, 153, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (805, 238, 8, 181, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (806, 238, 8, 147, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (807, 240, 8, 431, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (808, 240, 8, 344, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (809, 240, 8, 180, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (810, 240, 8, 171, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (811, 241, 8, 168, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (812, 241, 8, 145, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (813, 241, 8, 143, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (814, 241, 8, 131, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (815, 242, 8, 164, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (816, 242, 8, 132, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (817, 242, 8, 430, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (818, 242, 8, 148, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (819, 243, 8, 155, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (820, 243, 8, 172, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (821, 243, 8, 151, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (822, 243, 8, 126, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (823, 244, 8, 137, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (824, 244, 8, 130, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (825, 244, 8, 190, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (826, 244, 8, 111, NULL, b'0');
INSERT INTO `pms_judge_result` VALUES (827, 245, 2, 646, 'Main.java:5: 错误: 找不到符号\r\n        int a = in.nexeInt();\r\n                  ^\r\n  符号:   方法 nexeInt()\r\n  位置: 类型为Scanner的变量 in\r\n1 个错误\r\n', b'0');
INSERT INTO `pms_judge_result` VALUES (828, 246, 2, 763, 'Main.java:5: 错误: 找不到符号\r\n        int a = in.nexeInt();\r\n                  ^\r\n  符号:   方法 nexeInt()\r\n  位置: 类型为Scanner的变量 in\r\n1 个错误\r\n', b'0');
INSERT INTO `pms_judge_result` VALUES (829, 247, 2, 1014, 'Main.java:5: 错误: 找不到符号\r\n        int a = in.nexeInt();\r\n                  ^\r\n  符号:   方法 nexeInt()\r\n  位置: 类型为Scanner的变量 in\r\n1 个错误\r\n', b'0');
INSERT INTO `pms_judge_result` VALUES (830, 248, 2, 1056, 'Main.java:5: 错误: 找不到符号\r\n        int a = in.nexeInt();\r\n                  ^\r\n  符号:   方法 nexeInt()\r\n  位置: 类型为Scanner的变量 in\r\n1 个错误\r\n', b'0');
INSERT INTO `pms_judge_result` VALUES (831, 249, 2, 987, 'Main.java:5: 错误: 找不到符号\r\n        int a = in.nexeInt();\r\n                  ^\r\n  符号:   方法 nexeInt()\r\n  位置: 类型为Scanner的变量 in\r\n1 个错误\r\n', b'0');
INSERT INTO `pms_judge_result` VALUES (832, 250, 2, 1405, 'Main.java:5: 错误: 找不到符号\r\n        int a = in.nexeInt();\r\n                  ^\r\n  符号:   方法 nexeInt()\r\n  位置: 类型为Scanner的变量 in\r\n1 个错误\r\n', b'0');
INSERT INTO `pms_judge_result` VALUES (833, 251, 2, 1134, 'Main.java:5: 错误: 找不到符号\r\n        int a = in.nexeInt();\r\n                  ^\r\n  符号:   方法 nexeInt()\r\n  位置: 类型为Scanner的变量 in\r\n1 个错误\r\n', b'0');
INSERT INTO `pms_judge_result` VALUES (834, 252, 2, 1246, 'Main.java:5: 错误: 找不到符号\r\n        int a = in.nexeInt();\r\n                  ^\r\n  符号:   方法 nexeInt()\r\n  位置: 类型为Scanner的变量 in\r\n1 个错误\r\n', b'0');
INSERT INTO `pms_judge_result` VALUES (835, 253, 2, 1169, 'Main.java:5: 错误: 找不到符号\r\n        int a = in.nexeInt();\r\n                  ^\r\n  符号:   方法 nexeInt()\r\n  位置: 类型为Scanner的变量 in\r\n1 个错误\r\n', b'0');
INSERT INTO `pms_judge_result` VALUES (836, 254, 2, 877, 'Main.java:5: 错误: 找不到符号\r\n        int a = in.nexeInt();\r\n                  ^\r\n  符号:   方法 nexeInt()\r\n  位置: 类型为Scanner的变量 in\r\n1 个错误\r\n', b'0');
INSERT INTO `pms_judge_result` VALUES (837, 255, 2, 862, 'Main.java:5: 错误: 找不到符号\r\n        int a = in.nexeInt();\r\n                  ^\r\n  符号:   方法 nexeInt()\r\n  位置: 类型为Scanner的变量 in\r\n1 个错误\r\n', b'0');
INSERT INTO `pms_judge_result` VALUES (838, 257, 2, 668, 'Main.java:1: 错误: 需要class, interface或enum\r\n3 \r\n^\r\n1 个错误\r\n', b'0');
INSERT INTO `pms_judge_result` VALUES (839, 258, 2, 674, 'Main.java:1: 错误: 需要class, interface或enum\r\n3 \r\n^\r\n1 个错误\r\n', b'0');
INSERT INTO `pms_judge_result` VALUES (840, 259, 2, 694, 'Main.java:1: 错误: 需要class, interface或enum\r\n4 \r\n^\r\n1 个错误\r\n', b'0');
INSERT INTO `pms_judge_result` VALUES (841, 256, 2, 743, 'Main.java:1: 错误: 需要class, interface或enum\r\n4 \r\n^\r\n1 个错误\r\n', b'0');
INSERT INTO `pms_judge_result` VALUES (842, 260, 2, 614, 'Main.java:1: 错误: 需要class, interface或enum\r\n7 \r\n^\r\n1 个错误\r\n', b'0');
INSERT INTO `pms_judge_result` VALUES (843, 261, 2, 664, 'Main.java:1: 错误: 需要class, interface或enum\r\n8 \r\n^\r\n1 个错误\r\n', b'0');
INSERT INTO `pms_judge_result` VALUES (844, 263, 2, 680, 'Main.java:1: 错误: 需要class, interface或enum\r\n9 \r\n^\r\n1 个错误\r\n', b'0');
INSERT INTO `pms_judge_result` VALUES (845, 262, 2, 688, 'Main.java:1: 错误: 需要class, interface或enum\r\n9 \r\n^\r\n1 个错误\r\n', b'0');
INSERT INTO `pms_judge_result` VALUES (846, 264, 2, 414, 'Main.java:1: 错误: 需要class, interface或enum\r\n9 \r\n^\r\n1 个错误\r\n', b'0');
INSERT INTO `pms_judge_result` VALUES (847, 265, 2, 375, 'Main.java:1: 错误: 需要class, interface或enum\r\n9 \r\n^\r\n1 个错误\r\n', b'0');
INSERT INTO `pms_judge_result` VALUES (848, 267, 2, 594, 'Main.java:6: 错误: 需要class, interface或enum\r\n}0123\r\n ^\r\n1 个错误\r\n', b'0');
INSERT INTO `pms_judge_result` VALUES (849, 266, 2, 645, 'Main.java:6: 错误: 需要class, interface或enum\r\n}01234\r\n ^\r\n1 个错误\r\n', b'0');
INSERT INTO `pms_judge_result` VALUES (850, 268, 2, 653, 'Main.java:6: 错误: 需要class, interface或enum\r\n}012345\r\n ^\r\n1 个错误\r\n', b'0');
INSERT INTO `pms_judge_result` VALUES (851, 269, 2, 631, 'Main.java:6: 错误: 需要class, interface或enum\r\n}0123456\r\n ^\r\n1 个错误\r\n', b'0');
INSERT INTO `pms_judge_result` VALUES (852, 271, 2, 589, 'Main.java:6: 错误: 需要class, interface或enum\r\n}01234567\r\n ^\r\n1 个错误\r\n', b'0');
INSERT INTO `pms_judge_result` VALUES (853, 270, 2, 635, 'Main.java:6: 错误: 需要class, interface或enum\r\n}01234567\r\n ^\r\n1 个错误\r\n', b'0');
INSERT INTO `pms_judge_result` VALUES (854, 272, 2, 644, 'Main.java:6: 错误: 需要class, interface或enum\r\n}01234567\r\n ^\r\n1 个错误\r\n', b'0');
INSERT INTO `pms_judge_result` VALUES (855, 273, 2, 720, 'Main.java:6: 错误: 需要class, interface或enum\r\n}0123456789\r\n ^\r\n1 个错误\r\n', b'0');
INSERT INTO `pms_judge_result` VALUES (856, 275, 2, 447, 'Main.java:6: 错误: 需要class, interface或enum\r\n}0123456789\r\n ^\r\n1 个错误\r\n', b'0');
INSERT INTO `pms_judge_result` VALUES (857, 274, 2, 456, 'Main.java:6: 错误: 需要class, interface或enum\r\n}0123456789\r\n ^\r\n1 个错误\r\n', b'0');

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
  `input_describle` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '输入描述',
  `output_describle` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '输出描述',
  `collection_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '所属合集',
  `share` bit(1) NULL DEFAULT NULL COMMENT '是否与其他集合共享',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of pms_problem
-- ----------------------------
INSERT INTO `pms_problem` VALUES (1, 'A+B的问题', 1, '给你两个整数a，b(0<=a<=1,000)，计算a+b的值。', '1 1', '2', '2021-09-21 10:00:36', '2021-09-21 10:00:39', b'1', '输入两个整数a，b。', '输出a+b的值。', 1, b'1');

-- ----------------------------
-- Table structure for pms_problem_collection
-- ----------------------------
DROP TABLE IF EXISTS `pms_problem_collection`;
CREATE TABLE `pms_problem_collection`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `status` bit(1) NULL DEFAULT NULL COMMENT '是否启用',
  `create_user_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '创建人',
  `public_collection` bit(1) NULL DEFAULT NULL COMMENT '是否公开集',
  `begin_time` datetime(0) NULL DEFAULT NULL COMMENT '开启时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pms_problem_collection
-- ----------------------------
INSERT INTO `pms_problem_collection` VALUES (1, '基础集', '2021-09-22 23:39:04', '2021-09-22 23:39:06', b'1', 1, b'1', '2021-09-22 23:39:34', '2021-09-22 23:39:34');
INSERT INTO `pms_problem_collection` VALUES (2, '提高集', '2021-09-23 16:55:57', '2021-09-23 16:56:01', b'1', 1, b'1', '2021-09-23 16:56:08', '2021-09-26 16:56:10');

-- ----------------------------
-- Table structure for pms_problem_collection_item
-- ----------------------------
DROP TABLE IF EXISTS `pms_problem_collection_item`;
CREATE TABLE `pms_problem_collection_item`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `collection_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '所属集合',
  `problem_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '问题id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pms_problem_collection_item
-- ----------------------------
INSERT INTO `pms_problem_collection_item` VALUES (1, 1, 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of pms_problem_data
-- ----------------------------
INSERT INTO `pms_problem_data` VALUES (1, 1, '1 2', '3', 1);
INSERT INTO `pms_problem_data` VALUES (2, 1, '100 200', '300', 1);
INSERT INTO `pms_problem_data` VALUES (3, 1, '1 3', '4', 1);
INSERT INTO `pms_problem_data` VALUES (4, 1, '0 1', '1', 1);

-- ----------------------------
-- Table structure for pms_problem_tag
-- ----------------------------
DROP TABLE IF EXISTS `pms_problem_tag`;
CREATE TABLE `pms_problem_tag`  (
  `id` bigint(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `problem_id` bigint(10) UNSIGNED NULL DEFAULT NULL COMMENT '问题id',
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
  `language_type` tinyint(3) UNSIGNED NULL DEFAULT NULL COMMENT '语言类型',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '内容(编码内容)',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '提交时间',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '运行结果',
  `problem_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '提交的问题',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 276 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of pms_submit
-- ----------------------------
INSERT INTO `pms_submit` VALUES (204, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-09-25 17:35:32', 8, 1);
INSERT INTO `pms_submit` VALUES (205, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-09-25 17:37:25', 8, 1);
INSERT INTO `pms_submit` VALUES (206, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-09-25 17:39:16', 8, 1);
INSERT INTO `pms_submit` VALUES (207, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        Scanner in = new Scanner(System.in);\n        int a = in.nextInt();\n        int b = in.nextInt();\n        System.out.print(a + b);\n    }\n}', '2021-09-25 17:43:39', 6, 1);
INSERT INTO `pms_submit` VALUES (208, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-09-25 23:04:00', 8, 1);
INSERT INTO `pms_submit` VALUES (209, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-09-25 23:04:10', 8, 1);
INSERT INTO `pms_submit` VALUES (210, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-09-25 23:04:10', 8, 1);
INSERT INTO `pms_submit` VALUES (211, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-09-25 23:04:11', 8, 1);
INSERT INTO `pms_submit` VALUES (212, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-09-25 23:04:11', 8, 1);
INSERT INTO `pms_submit` VALUES (213, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-09-25 23:04:11', 8, 1);
INSERT INTO `pms_submit` VALUES (214, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-09-25 23:04:11', 8, 1);
INSERT INTO `pms_submit` VALUES (215, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-09-25 23:04:11', 8, 1);
INSERT INTO `pms_submit` VALUES (216, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-09-25 23:04:12', 8, 1);
INSERT INTO `pms_submit` VALUES (217, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-09-25 23:04:12', 8, 1);
INSERT INTO `pms_submit` VALUES (218, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-09-25 23:04:12', 8, 1);
INSERT INTO `pms_submit` VALUES (219, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-09-25 23:04:12', 8, 1);
INSERT INTO `pms_submit` VALUES (220, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-09-25 23:04:12', 8, 1);
INSERT INTO `pms_submit` VALUES (221, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-09-25 23:04:12', 8, 1);
INSERT INTO `pms_submit` VALUES (222, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-09-25 23:04:12', 8, 1);
INSERT INTO `pms_submit` VALUES (223, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-09-25 23:04:13', 8, 1);
INSERT INTO `pms_submit` VALUES (224, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-09-25 23:04:13', 8, 1);
INSERT INTO `pms_submit` VALUES (225, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-09-25 23:04:13', 8, 1);
INSERT INTO `pms_submit` VALUES (226, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-09-25 23:04:13', 8, 1);
INSERT INTO `pms_submit` VALUES (227, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-09-25 23:04:13', 8, 1);
INSERT INTO `pms_submit` VALUES (228, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-09-25 23:04:13', 8, 1);
INSERT INTO `pms_submit` VALUES (229, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-09-25 23:04:13', 8, 1);
INSERT INTO `pms_submit` VALUES (230, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-09-25 23:04:14', 8, 1);
INSERT INTO `pms_submit` VALUES (231, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-09-25 23:42:16', 8, 1);
INSERT INTO `pms_submit` VALUES (232, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-09-25 23:44:42', 8, 1);
INSERT INTO `pms_submit` VALUES (233, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-09-25 23:44:48', 8, 1);
INSERT INTO `pms_submit` VALUES (234, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-09-25 23:44:48', 8, 1);
INSERT INTO `pms_submit` VALUES (235, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-09-25 23:44:48', 8, 1);
INSERT INTO `pms_submit` VALUES (236, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-09-25 23:44:48', 8, 1);
INSERT INTO `pms_submit` VALUES (237, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-09-25 23:44:49', 8, 1);
INSERT INTO `pms_submit` VALUES (238, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-09-25 23:44:49', 8, 1);
INSERT INTO `pms_submit` VALUES (239, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-09-25 23:44:49', 8, 1);
INSERT INTO `pms_submit` VALUES (240, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-09-25 23:44:49', 8, 1);
INSERT INTO `pms_submit` VALUES (241, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-09-25 23:44:49', 8, 1);
INSERT INTO `pms_submit` VALUES (242, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-09-25 23:44:49', 8, 1);
INSERT INTO `pms_submit` VALUES (243, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-09-25 23:44:50', 8, 1);
INSERT INTO `pms_submit` VALUES (244, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}', '2021-09-25 23:44:50', 8, 1);
INSERT INTO `pms_submit` VALUES (245, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        Scanner in = new Scanner(System.in);\n        int a = in.nexeInt();\n        int b = in.nextInt();\n        System.out.print(a + b);\n    }\n}', '2021-09-25 23:46:09', 2, 1);
INSERT INTO `pms_submit` VALUES (246, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        Scanner in = new Scanner(System.in);\n        int a = in.nexeInt();\n        int b = in.nextInt();\n        System.out.print(a + b);\n    }\n}', '2021-09-25 23:46:09', 2, 1);
INSERT INTO `pms_submit` VALUES (247, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        Scanner in = new Scanner(System.in);\n        int a = in.nexeInt();\n        int b = in.nextInt();\n        System.out.print(a + b);\n    }\n}', '2021-09-25 23:46:09', 2, 1);
INSERT INTO `pms_submit` VALUES (248, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        Scanner in = new Scanner(System.in);\n        int a = in.nexeInt();\n        int b = in.nextInt();\n        System.out.print(a + b);\n    }\n}', '2021-09-25 23:46:10', 2, 1);
INSERT INTO `pms_submit` VALUES (249, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        Scanner in = new Scanner(System.in);\n        int a = in.nexeInt();\n        int b = in.nextInt();\n        System.out.print(a + b);\n    }\n}', '2021-09-25 23:46:10', 2, 1);
INSERT INTO `pms_submit` VALUES (250, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        Scanner in = new Scanner(System.in);\n        int a = in.nexeInt();\n        int b = in.nextInt();\n        System.out.print(a + b);\n    }\n}', '2021-09-25 23:46:10', 2, 1);
INSERT INTO `pms_submit` VALUES (251, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        Scanner in = new Scanner(System.in);\n        int a = in.nexeInt();\n        int b = in.nextInt();\n        System.out.print(a + b);\n    }\n}', '2021-09-25 23:46:10', 2, 1);
INSERT INTO `pms_submit` VALUES (252, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        Scanner in = new Scanner(System.in);\n        int a = in.nexeInt();\n        int b = in.nextInt();\n        System.out.print(a + b);\n    }\n}', '2021-09-25 23:46:10', 2, 1);
INSERT INTO `pms_submit` VALUES (253, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        Scanner in = new Scanner(System.in);\n        int a = in.nexeInt();\n        int b = in.nextInt();\n        System.out.print(a + b);\n    }\n}', '2021-09-25 23:46:10', 2, 1);
INSERT INTO `pms_submit` VALUES (254, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        Scanner in = new Scanner(System.in);\n        int a = in.nexeInt();\n        int b = in.nextInt();\n        System.out.print(a + b);\n    }\n}', '2021-09-25 23:46:10', 2, 1);
INSERT INTO `pms_submit` VALUES (255, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        Scanner in = new Scanner(System.in);\n        int a = in.nexeInt();\n        int b = in.nextInt();\n        System.out.print(a + b);\n    }\n}', '2021-09-25 23:46:11', 2, 1);
INSERT INTO `pms_submit` VALUES (256, 1, 1, '0 ', '2021-09-25 23:48:02', 2, 1);
INSERT INTO `pms_submit` VALUES (257, 1, 1, '1 ', '2021-09-25 23:48:02', 2, 1);
INSERT INTO `pms_submit` VALUES (258, 1, 1, '2 ', '2021-09-25 23:48:02', 2, 1);
INSERT INTO `pms_submit` VALUES (259, 1, 1, '3 ', '2021-09-25 23:48:02', 2, 1);
INSERT INTO `pms_submit` VALUES (260, 1, 1, '4 ', '2021-09-25 23:48:02', 2, 1);
INSERT INTO `pms_submit` VALUES (261, 1, 1, '5 ', '2021-09-25 23:48:02', 2, 1);
INSERT INTO `pms_submit` VALUES (262, 1, 1, '6 ', '2021-09-25 23:48:02', 2, 1);
INSERT INTO `pms_submit` VALUES (263, 1, 1, '7 ', '2021-09-25 23:48:02', 2, 1);
INSERT INTO `pms_submit` VALUES (264, 1, 1, '8 ', '2021-09-25 23:48:02', 2, 1);
INSERT INTO `pms_submit` VALUES (265, 1, 1, '9 ', '2021-09-25 23:48:02', 2, 1);
INSERT INTO `pms_submit` VALUES (266, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}0', '2021-09-25 23:48:48', 2, 1);
INSERT INTO `pms_submit` VALUES (267, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}01', '2021-09-25 23:48:48', 2, 1);
INSERT INTO `pms_submit` VALUES (268, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}012', '2021-09-25 23:48:48', 2, 1);
INSERT INTO `pms_submit` VALUES (269, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}0123', '2021-09-25 23:48:48', 2, 1);
INSERT INTO `pms_submit` VALUES (270, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}01234', '2021-09-25 23:48:48', 2, 1);
INSERT INTO `pms_submit` VALUES (271, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}012345', '2021-09-25 23:48:48', 2, 1);
INSERT INTO `pms_submit` VALUES (272, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}0123456', '2021-09-25 23:48:48', 2, 1);
INSERT INTO `pms_submit` VALUES (273, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}01234567', '2021-09-25 23:48:48', 2, 1);
INSERT INTO `pms_submit` VALUES (274, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}012345678', '2021-09-25 23:48:48', 2, 1);
INSERT INTO `pms_submit` VALUES (275, 1, 1, 'import java.util.Scanner;\npublic class Main{\n    public static void main(String[] args){\n        \n    }\n}0123456789', '2021-09-25 23:48:48', 2, 1);

-- ----------------------------
-- Table structure for pms_tag
-- ----------------------------
DROP TABLE IF EXISTS `pms_tag`;
CREATE TABLE `pms_tag`  (
  `id` bigint(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标签名',
  `create_user_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of pms_tag
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
