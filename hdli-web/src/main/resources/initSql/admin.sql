/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : shriotest

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 30/01/2021 23:35:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录用户名',
  `user_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '昵称',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `salt` char(6) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码加盐',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号',
  `valid` bit(1) NOT NULL DEFAULT b'1' COMMENT '有效:是/否',
  `removed` bit(1) NOT NULL DEFAULT b'0' COMMENT '逻辑删除',
  `created_user_id` bigint(20) NOT NULL COMMENT '创建者id',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `updated_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_un`(`user_code`) USING BTREE COMMENT '登陆名必须唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', '超级管理员', '23848eb9a7de2a1070103eb74ac20d0aa11ab05bc0b3cecc304de37233467d7e', 'aaabbb', 'admin@example.com', '17699999999', b'1', b'0', 0, '2019-08-15 18:35:19', '2019-11-22 17:04:11');
INSERT INTO `admin` VALUES (2, 'hdli', '飞云之下', '23848eb9a7de2a1070103eb74ac20d0aa11ab05bc0b3cecc304de37233467d7e', 'aaabbb', NULL, NULL, b'1', b'0', 1, '2019-08-15 23:22:03', '2019-11-22 17:04:14');

-- ----------------------------
-- Table structure for admin_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色映射表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_role
-- ----------------------------
INSERT INTO `admin_role` VALUES (1, 1, 1, '2019-08-15 23:25:20');
INSERT INTO `admin_role` VALUES (2, 2, 2, '2019-08-15 23:25:31');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '类型:0-目录;1-菜单;2-按钮',
  `icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单图标',
  `sort_no` int(11) DEFAULT NULL COMMENT '排序',
  `deleted` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '是否已删除：0-否，1-是',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `updated_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, 0, '系统管理', '', '', '0', NULL, 0, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (2, 1, '用户管理', NULL, NULL, '0', NULL, 1, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (3, 1, '订单查询', NULL, NULL, '0', NULL, 2, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (4, 1, '配置管理', NULL, NULL, '0', NULL, 3, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (5, 1, '对账管理', NULL, NULL, '0', NULL, 4, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (6, 1, '权限管理', NULL, NULL, '0', NULL, 5, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (7, 1, '系统工具', NULL, NULL, '0', NULL, 6, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (8, 1, '企业户维护', NULL, NULL, '0', NULL, 7, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (10, 2, '基础信息', 'api/user', NULL, '1', NULL, 1, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (11, 2, '充值记录', 'api/recharge', NULL, '1', NULL, 2, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (12, 3, '机票', 'api/flight', NULL, '1', NULL, 1, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (13, 3, '火车票', 'api/train', NULL, '1', NULL, 2, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (14, 3, '酒店', 'api/hotel', NULL, '1', NULL, 3, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (15, 3, '差旅归集', 'api/repayment', NULL, '1', NULL, 4, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (16, 4, '服务费', 'api/serviceFee', NULL, '1', NULL, 1, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (17, 4, '差旅归集', 'api/repaymentFee', NULL, '1', NULL, 2, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (18, 4, '广告管理', 'api/banner', NULL, '1', NULL, 3, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (19, 5, '钱包自动对账异常', NULL, NULL, '1', NULL, 1, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (20, 5, '会计自对账异常', NULL, NULL, '1', NULL, 2, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (21, 5, '支付系统自对账异常', NULL, NULL, '1', NULL, 3, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (22, 5, '系统多应用对账结果', NULL, NULL, '1', NULL, 3, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (23, 5, '支付渠道对账结果', NULL, NULL, '1', NULL, 3, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (24, 6, '用户管理', 'bms/admin', NULL, '1', NULL, 1, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (25, 6, '角色管理', 'bms/role', NULL, '1', NULL, 2, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (26, 6, '菜单管理', 'bms/menu', NULL, '1', NULL, 3, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (27, 7, '火车票回调查询', 'api/train', NULL, '1', NULL, 0, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (28, 8, '基本信息', NULL, NULL, '1', NULL, 0, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (29, 10, '查看', NULL, 'api:user:list,api:user:info', '2', NULL, 0, '0', '2019-08-15 18:35:19', '2019-08-19 17:31:33');
INSERT INTO `menu` VALUES (30, 10, '修改', NULL, 'api:user:update', '2', NULL, 0, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (31, 11, '查询', NULL, 'api:recharge:list', '2', NULL, 0, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (32, 11, '导出', NULL, 'api:recharge:export', '2', NULL, 0, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (33, 12, '查看', NULL, 'api:flight:list,api:flight:info', '2', NULL, 0, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (34, 13, '查看', NULL, 'api:train:list,api:train:info', '2', NULL, 0, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (35, 14, '查看', NULL, 'api:hotel:list,api:hotel:info', '2', NULL, 0, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (36, 15, '查看', NULL, 'api:repayment:list,api:repayment:info', '2', NULL, 0, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (37, 15, '修改', NULL, 'api:repayment:update', '2', NULL, 0, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (38, 15, '导出', NULL, 'api:repayment:export', '2', NULL, 0, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (39, 16, '查看', NULL, 'api:serviceFee:list,api:serviceFee:info', '2', NULL, 0, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (40, 16, '修改', NULL, 'api:serviceFee:update', '2', NULL, 0, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (41, 16, '刷新', NULL, 'api:serviceFee:refresh', '2', NULL, 0, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (42, 17, '查看', NULL, 'api:repaymentFee:list,api:repaymentFee:info', '2', NULL, 0, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (43, 17, '修改', NULL, 'api:repaymentFee:update', '2', NULL, 0, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (44, 17, '刷新', NULL, 'api:repaymentFee:refresh', '2', NULL, 0, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (45, 18, '查看', NULL, 'api:banner:list,api:banner:info', '2', NULL, 0, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (46, 18, '新增', NULL, 'api:banner:save', '2', NULL, 0, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (47, 18, '修改', NULL, 'api:banner:update', '2', NULL, 0, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (48, 18, '删除', NULL, 'api:banner:delete', '2', NULL, 0, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (49, 18, '刷新', NULL, 'api:banner:refresh', '2', NULL, 0, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (50, 24, '查看', NULL, 'bms:admin:list,bms:admin:info', '2', NULL, 0, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (51, 24, '新增', NULL, 'bms:admin:save', '2', NULL, 0, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (52, 24, '修改', NULL, 'bms:admin:update', '2', NULL, 0, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (53, 24, '删除', NULL, 'bms:admin:delete', '2', NULL, 0, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (54, 25, '查看', NULL, 'bms:role:list,bms:role:info', '2', NULL, 0, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (55, 25, '新增', NULL, 'bms:role:save', '2', NULL, 0, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (56, 25, '修改', NULL, 'bms:role:update', '2', NULL, 0, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (57, 25, '删除', NULL, 'bms:role:delete', '2', NULL, 0, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (58, 26, '查看', NULL, 'bms:menu:list,bms:menu:info', '2', NULL, 0, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (59, 26, '新增', NULL, 'bms:menu:save', '2', NULL, 0, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (60, 26, '修改', NULL, 'bms:menu:update', '2', NULL, 0, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (61, 26, '删除', NULL, 'bms:menu:delete', '2', NULL, 0, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');
INSERT INTO `menu` VALUES (62, 27, '查看', NULL, 'api:train:info', '2', NULL, 0, '0', '2019-08-15 18:35:19', '2019-08-15 18:35:19');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `product_price` decimal(10, 2) DEFAULT NULL,
  `category_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, 'iphone手机', 8000.00, '手机', '手机撒娇沙克健身卡健身卡就杀了就是拉上拉链', '151651156161.png');
INSERT INTO `product` VALUES (2, '洒洒水', 5000.00, '手机', '阿三ADSFDSSQ实打实大大是', '449445511616.png');
INSERT INTO `product` VALUES (3, '健身分期', 1000.00, '健身', '飒飒撒撒去问问恶趣味', '1551515151.png');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `role_cd` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色标识',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `created_user_id` bigint(20) DEFAULT NULL COMMENT '创建者',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `updated_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '管理员', 'admin', NULL, NULL, '2019-08-15 23:23:16', '2019-08-15 23:23:16');
INSERT INTO `role` VALUES (2, '研发', 'aaa', NULL, 1, '2019-08-15 23:24:35', '2019-08-15 23:24:35');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色菜单映射表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES (7, 2, 29, '2019-08-15 23:26:50');

SET FOREIGN_KEY_CHECKS = 1;
