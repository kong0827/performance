/*
 Navicat Premium Data Transfer

 Source Server         : xueyun
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : performance

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 10/05/2020 12:43:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for complaint
-- ----------------------------
DROP TABLE IF EXISTS `complaint`;
CREATE TABLE `complaint`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `submit_id` int(11) NULL DEFAULT NULL,
  `problem` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `states` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of complaint
-- ----------------------------
INSERT INTO `complaint` VALUES (2, 15, '录入有误', 1);

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `department_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (1, '部门一', 4);
INSERT INTO `department` VALUES (3, '部门二', NULL);
INSERT INTO `department` VALUES (4, '部门三', 3);
INSERT INTO `department` VALUES (5, '部门四', NULL);
INSERT INTO `department` VALUES (6, '部门五', NULL);

-- ----------------------------
-- Table structure for indicators
-- ----------------------------
DROP TABLE IF EXISTS `indicators`;
CREATE TABLE `indicators`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `define` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of indicators
-- ----------------------------
INSERT INTO `indicators` VALUES (4, '问题整理', '完全技术支持');
INSERT INTO `indicators` VALUES (5, '产品质量提升', '324234234');
INSERT INTO `indicators` VALUES (6, '文档体系', '122132312');
INSERT INTO `indicators` VALUES (7, '人才培养', '324324');
INSERT INTO `indicators` VALUES (8, '职业化工作态度', '234124');
INSERT INTO `indicators` VALUES (9, '知识全面性提升', '12312');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `menu_id` int(11) NULL DEFAULT NULL,
  `type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '用户管理', '/user', 7, 'user', NULL);
INSERT INTO `menu` VALUES (2, '角色管理', '/role', 7, 'role', NULL);
INSERT INTO `menu` VALUES (3, '组织管理', '/department', 7, 'department', NULL);
INSERT INTO `menu` VALUES (4, '绩效指标', '/indicators', 8, 'indicators', NULL);
INSERT INTO `menu` VALUES (5, '全部绩效目标', '/performances', 8, 'performances', NULL);
INSERT INTO `menu` VALUES (6, '报表', '/report', 8, 'report', NULL);
INSERT INTO `menu` VALUES (7, '系统管理', '#', 0, '#', 'fa-th-large');
INSERT INTO `menu` VALUES (8, '绩效管理', '#', 0, '#', 'fa-bar-chart-o');
INSERT INTO `menu` VALUES (9, '个人绩效目标', '/personal', 8, 'personal', NULL);
INSERT INTO `menu` VALUES (10, '发布任务', '/task', 8, 'task', NULL);
INSERT INTO `menu` VALUES (11, '审核发布', '/taskCheck', 8, 'taskCheck', NULL);
INSERT INTO `menu` VALUES (12, '任务列表', '/taskList', 8, 'taskList', NULL);
INSERT INTO `menu` VALUES (13, '绩效审核', '/submitList', 8, 'submitList', NULL);
INSERT INTO `menu` VALUES (14, '绩效审核', '/submitLists', 8, 'submitLists', NULL);
INSERT INTO `menu` VALUES (15, '绩效列表', '/submitListss', 8, 'submitListss', NULL);
INSERT INTO `menu` VALUES (16, '申诉', '/complaint', 8, 'complaint', NULL);
INSERT INTO `menu` VALUES (17, '申诉审核', '/complaintcheck', 8, 'complaintcheck', NULL);
INSERT INTO `menu` VALUES (18, '申诉列表', '/complaintList', 8, 'complaintList', NULL);
INSERT INTO `menu` VALUES (19, '绩效计划', '/plan', 8, 'plan', NULL);
INSERT INTO `menu` VALUES (20, '计划列表', '/planList', 8, 'planList', NULL);
INSERT INTO `menu` VALUES (21, '计划处理列表', '/plancheckList', 8, 'plancheckList', NULL);
INSERT INTO `menu` VALUES (22, '统计报表', '/totalList', 8, 'totalList', NULL);

-- ----------------------------
-- Table structure for performances
-- ----------------------------
DROP TABLE IF EXISTS `performances`;
CREATE TABLE `performances`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dimension` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `indicators_id` int(11) NULL DEFAULT NULL,
  `assessment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lack_of` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bottom_line` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `standard` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `challenge` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `weight` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `completion` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `score` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `superior_grade` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `superior_evaluation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  `audit` int(11) NULL DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `task_id` int(11) NULL DEFAULT NULL,
  `states` int(11) NULL DEFAULT NULL,
  `is_score` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of performances
-- ----------------------------
INSERT INTO `performances` VALUES (13, '服务目标', 1, 'werwe', '123', '123', '123', '123', '123', '123', '121', '10', '4564564', 6, NULL, NULL, 7, 1, 1);
INSERT INTO `performances` VALUES (14, '服务目标', 4, '34124', '12412', '4124', '2124', '1241', '124421', '345', '5345', '10', '10', 6, NULL, NULL, 7, 1, 1);
INSERT INTO `performances` VALUES (15, '服务目标', 5, '124124', '124', '124141', '24124', '341', '243414', '453', '453', '10', '10', 6, NULL, NULL, 7, 1, 1);
INSERT INTO `performances` VALUES (16, '职业化目标', 6, '1241', '24124', '124124', '1412', '414', '3412412', '453', '453453', '10', '10', 6, NULL, NULL, 7, 1, 1);
INSERT INTO `performances` VALUES (17, '职业化目标', 7, '4124', '14', '34124', '14', '3413', '414134', '453', '453', '10', '10', 6, NULL, NULL, 7, 1, 1);
INSERT INTO `performances` VALUES (18, '职业化目标', 8, '123', '23123', '123123', '1231', '23123', '123123', '453', '453453', '10', '10', 6, NULL, NULL, 7, 1, 1);
INSERT INTO `performances` VALUES (19, '能力提升', 9, '12312', '3123', '1231231', '23123', '12312', '123123', '453', '453453', '10', '10', 6, NULL, NULL, 7, 1, 1);

-- ----------------------------
-- Table structure for plan
-- ----------------------------
DROP TABLE IF EXISTS `plan`;
CREATE TABLE `plan`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `submit_id` int(11) NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `states` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of plan
-- ----------------------------
INSERT INTO `plan` VALUES (3, 15, '定制计划测试', 1);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `permissions` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '管理员', '1,2,3,4,5,6,9,10,11,12,13,14,15,16,17,18,19,20,21,22');
INSERT INTO `role` VALUES (3, '组员', '6,9,12,16,21');
INSERT INTO `role` VALUES (4, '人力', '6,10,15,18,19,20');
INSERT INTO `role` VALUES (5, '领导', '6,14,17');
INSERT INTO `role` VALUES (6, '项目经理', '5,11,13');
INSERT INTO `role` VALUES (7, 'xx', '1');

-- ----------------------------
-- Table structure for submit
-- ----------------------------
DROP TABLE IF EXISTS `submit`;
CREATE TABLE `submit`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `task_id` int(11) NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `states` int(11) NULL DEFAULT NULL,
  `is_state` int(11) NULL DEFAULT NULL,
  `score` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `numbers` decimal(11, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of submit
-- ----------------------------
INSERT INTO `submit` VALUES (15, 7, 6, '', 2, 1, 'C', 70.00);

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `start_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `end_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `template_id` int(11) NULL DEFAULT NULL,
  `states` int(11) NULL DEFAULT 0,
  `is_score` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES (7, '绩效', '月度', '2020-03-25', '2020-04-25', 1, 1, 1);
INSERT INTO `task` VALUES (10, '绩效任务', '月度', '2020-03-26', '2020-04-26', 1, 1, 1);

-- ----------------------------
-- Table structure for task_temp
-- ----------------------------
DROP TABLE IF EXISTS `task_temp`;
CREATE TABLE `task_temp`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `task_id` int(11) NULL DEFAULT NULL,
  `template_id` int(11) NULL DEFAULT NULL,
  `states` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of task_temp
-- ----------------------------

-- ----------------------------
-- Table structure for template
-- ----------------------------
DROP TABLE IF EXISTS `template`;
CREATE TABLE `template`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `identify` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of template
-- ----------------------------
INSERT INTO `template` VALUES (1, '个人绩效', 'templateOne');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `department_id` int(11) NULL DEFAULT NULL,
  `role_id` int(11) NULL DEFAULT NULL,
  `account` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pwd` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '人力', 5, 4, 'admin1', 'admin1');
INSERT INTO `user` VALUES (6, '组员', 1, 3, 'admin2', 'admin2');
INSERT INTO `user` VALUES (5, '项目经理', 4, 6, 'admin3', 'admin3');
INSERT INTO `user` VALUES (7, '领导', 3, 5, 'admin4', 'admin4');
INSERT INTO `user` VALUES (8, '管理员', 6, 1, 'admin', 'admin');

SET FOREIGN_KEY_CHECKS = 1;
