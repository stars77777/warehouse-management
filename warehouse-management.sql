/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50550
 Source Host           : localhost:3306
 Source Schema         : warehouse-management

 Target Server Type    : MySQL
 Target Server Version : 50550
 File Encoding         : 65001

 Date: 28/06/2023 01:54:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `goods_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `specification` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `price` decimal(10, 2) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`goods_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, '111', '大', 222.00, '2023-06-20 20:40:03');
INSERT INTO `goods` VALUES (2, '222', '大', 222.00, '2023-06-27 20:36:59');

-- ----------------------------
-- Table structure for goods_stores
-- ----------------------------
DROP TABLE IF EXISTS `goods_stores`;
CREATE TABLE `goods_stores`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) NOT NULL COMMENT '商品id',
  `store_id` int(11) NOT NULL COMMENT '门店id',
  `quantity` int(11) NOT NULL COMMENT '数量',
  `sum_price` decimal(10, 2) NOT NULL COMMENT '总价',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of goods_stores
-- ----------------------------
INSERT INTO `goods_stores` VALUES (2, 1, 1, 2, 444.00);

-- ----------------------------
-- Table structure for inbound_ledger
-- ----------------------------
DROP TABLE IF EXISTS `inbound_ledger`;
CREATE TABLE `inbound_ledger`  (
  `ledger_id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) NOT NULL,
  `store_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `inbound_date` date NULL DEFAULT NULL,
  PRIMARY KEY (`ledger_id`) USING BTREE,
  INDEX `goods_id`(`goods_id`) USING BTREE,
  INDEX `store_id`(`store_id`) USING BTREE,
  CONSTRAINT `inbound_ledger_ibfk_1` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `inbound_ledger_ibfk_2` FOREIGN KEY (`store_id`) REFERENCES `stores` (`store_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of inbound_ledger
-- ----------------------------
INSERT INTO `inbound_ledger` VALUES (5, 1, 1, 1, '2023-06-26');
INSERT INTO `inbound_ledger` VALUES (6, 1, 1, 1, '2023-06-27');
INSERT INTO `inbound_ledger` VALUES (7, 1, 1, 1, '2023-06-27');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `identity` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户标识',
  `out_time` datetime NOT NULL COMMENT '出库时间',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '信息文本',
  `read_status` int(11) UNSIGNED NOT NULL COMMENT '阅读状态（0未读，1已读）',
  `create_time` datetime NOT NULL COMMENT ' 消息创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of message
-- ----------------------------

-- ----------------------------
-- Table structure for outbound_ledger
-- ----------------------------
DROP TABLE IF EXISTS `outbound_ledger`;
CREATE TABLE `outbound_ledger`  (
  `ledger_id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) NOT NULL,
  `store_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `outbound_date` date NULL DEFAULT NULL,
  PRIMARY KEY (`ledger_id`) USING BTREE,
  INDEX `goods_id`(`goods_id`) USING BTREE,
  INDEX `store_id`(`store_id`) USING BTREE,
  CONSTRAINT `outbound_ledger_ibfk_1` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `outbound_ledger_ibfk_2` FOREIGN KEY (`store_id`) REFERENCES `stores` (`store_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of outbound_ledger
-- ----------------------------
INSERT INTO `outbound_ledger` VALUES (2, 1, 1, 1, '2023-06-27');

-- ----------------------------
-- Table structure for stores
-- ----------------------------
DROP TABLE IF EXISTS `stores`;
CREATE TABLE `stores`  (
  `store_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`store_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of stores
-- ----------------------------
INSERT INTO `stores` VALUES (1, '嗷嗷嗷');

SET FOREIGN_KEY_CHECKS = 1;
