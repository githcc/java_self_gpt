/*
 Navicat Premium Data Transfer

 Source Server         : 119.91.23.137
 Source Server Type    : MySQL
 Source Server Version : 80300
 Source Host           : 119.91.23.137:3306
 Source Schema         : gpt

 Target Server Type    : MySQL
 Target Server Version : 80300
 File Encoding         : 65001

 Date: 25/05/2024 08:29:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for request
-- ----------------------------
DROP TABLE IF EXISTS `request`;
CREATE TABLE `request`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `mapping` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `content_blob` mediumblob NULL,
  `content_text` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 79811624 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;
