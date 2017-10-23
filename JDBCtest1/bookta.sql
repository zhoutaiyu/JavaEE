/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : book

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2017-09-18 05:51:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `bookta`
-- ----------------------------
DROP TABLE IF EXISTS `bookta`;
CREATE TABLE `bookta` (
  `isbn` varchar(16) NOT NULL,
  `title` varchar(30) NOT NULL,
  `type` varchar(20) NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bookta
-- ----------------------------
INSERT INTO `bookta` VALUES ('9101748', '法布尔昆虫记', '童书', '99');
INSERT INTO `bookta` VALUES ('9203153', '青蛙弗洛格的成长故事', '童书', '57.6');
INSERT INTO `bookta` VALUES ('9787214068828', '一问一世界', '传记', '32');
INSERT INTO `bookta` VALUES ('9787505142169', '繁华落尽，素心不改', '传记', '36');
