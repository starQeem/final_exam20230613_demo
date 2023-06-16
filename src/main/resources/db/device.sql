/*
Navicat MySQL Data Transfer

Source Server         : 腾讯云
Source Server Version : 50736
Source Host           : 106.53.103.231:3306
Source Database       : device

Target Server Type    : MYSQL
Target Server Version : 50736
File Encoding         : 65001

Date: 2023-06-15 22:27:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for device
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(30) DEFAULT NULL COMMENT '名称',
  `label` varchar(30) DEFAULT NULL COMMENT '类型',
  `price` int(20) DEFAULT NULL COMMENT '价格',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of device
-- ----------------------------
INSERT INTO `device` VALUES ('2', '鼠标', '电脑配件', '200');
INSERT INTO `device` VALUES ('3', '显示器', '电脑配件', '1000');
INSERT INTO `device` VALUES ('4', '显卡', '电脑配件', '2000');
INSERT INTO `device` VALUES ('5', 'cpu', '电脑配件', '2500');
INSERT INTO `device` VALUES ('6', '主板', '电脑配件', '600');
INSERT INTO `device` VALUES ('13', '硬盘', '电脑配件', '500');
INSERT INTO `device` VALUES ('17', '空调', '家用电器', '5000');
INSERT INTO `device` VALUES ('18', '内存条', '电脑配件', '150');
INSERT INTO `device` VALUES ('19', '电视机', '家用电器', '5000');
INSERT INTO `device` VALUES ('20', '路由器', '网络产品', '500');
INSERT INTO `device` VALUES ('22', '交换机', '网络产品', '2000');
INSERT INTO `device` VALUES ('23', '笔记本电脑', '电脑整机', '6666');
INSERT INTO `device` VALUES ('24', '平板电脑', '电脑整机', '5000');
INSERT INTO `device` VALUES ('26', '散热器', '电脑配件', '300');
INSERT INTO `device` VALUES ('28', '电饭锅', '家用电器', '500');
INSERT INTO `device` VALUES ('39', '平板电脑', '电子设备', '3000');
INSERT INTO `device` VALUES ('40', '键盘侠', '电子宠物', '9');


