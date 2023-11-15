/*
Navicat MySQL Data Transfer

Source Server         : mysql2
Source Server Version : 50621
Source Host           : 127.0.0.1:3306
Source Database       : db_trade

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2023-11-16 01:22:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for carts
-- ----------------------------
DROP TABLE IF EXISTS `carts`;
CREATE TABLE `carts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(11) DEFAULT NULL,
  `price` double DEFAULT NULL COMMENT '小计',
  `uid` int(11) DEFAULT NULL COMMENT '用户',
  `sid` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL COMMENT '商品',
  `quantity` int(11) DEFAULT NULL COMMENT '数量',
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`),
  KEY `pid` (`pid`),
  CONSTRAINT `carts_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `users` (`id`),
  CONSTRAINT `carts_ibfk_2` FOREIGN KEY (`pid`) REFERENCES `goods` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of carts
-- ----------------------------
INSERT INTO `carts` VALUES ('55', '1', '10', '3', '2', '7', '1');
INSERT INTO `carts` VALUES ('57', '1', '11111', '2', '3', '21', '1');
INSERT INTO `carts` VALUES ('58', '1', '11111', '2', '3', '22', '1');
INSERT INTO `carts` VALUES ('63', '1', '50', '4', '3', '29', '1');
INSERT INTO `carts` VALUES ('64', '0', '1000', '3', '2', '1', '3');
INSERT INTO `carts` VALUES ('65', '0', '500', '3', '4', '15', '2');
INSERT INTO `carts` VALUES ('66', '0', '1000', '3', '2', '3', '1');
INSERT INTO `carts` VALUES ('70', '0', '222', '2', '4', '8', '1');
INSERT INTO `carts` VALUES ('71', '0', '1000', '2', '4', '13', '1');
INSERT INTO `carts` VALUES ('72', '0', '200', '2', '3', '28', '3');
INSERT INTO `carts` VALUES ('73', '0', '500', '2', '4', '12', '1');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `sid` int(4) DEFAULT NULL COMMENT '商家',
  `cid` int(4) DEFAULT NULL COMMENT '产品类型',
  `name` varchar(255) DEFAULT NULL COMMENT '产品名称',
  `price` double DEFAULT NULL COMMENT '价格',
  `quantity` int(4) DEFAULT NULL COMMENT '数量',
  `pic` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL COMMENT '描述',
  `type` int(11) DEFAULT NULL COMMENT '售卖?0:1',
  `isdel` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sid` (`sid`),
  KEY `cid` (`cid`),
  CONSTRAINT `goods_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `users` (`id`),
  CONSTRAINT `goods_ibfk_2` FOREIGN KEY (`cid`) REFERENCES `goodstype` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', '2', '4', '家具', '1000', '41', '32.jpg', '不错', '0', '0');
INSERT INTO `goods` VALUES ('2', '2', '5', '灯', '1000', '7', '6.jpg', '不错', '0', '0');
INSERT INTO `goods` VALUES ('3', '2', '3', '项链', '1000', '10', '11.jpg', '不错', '0', '0');
INSERT INTO `goods` VALUES ('4', '3', '3', '西装', '1000', '2', '26.jpg', '不错', '0', '0');
INSERT INTO `goods` VALUES ('5', '3', '4', '灯', '1000', '0', '9.jpg', '不错<p><br></p>', '0', '0');
INSERT INTO `goods` VALUES ('6', '3', '3', '马甲', '1000', '6', '25.jpg', '不错', '1', '1');
INSERT INTO `goods` VALUES ('7', '2', '1', '电子钟', '10', '4', '34.jpg', '<p>很好</p>', '1', '1');
INSERT INTO `goods` VALUES ('8', '4', '3', '衬衫', '222', '10', '7.jpg', '<p>衣服很好</p>', '0', '0');
INSERT INTO `goods` VALUES ('9', '3', '1', '1', '1', '1', '5.png', '<p>1</p>', '0', '1');
INSERT INTO `goods` VALUES ('10', '4', '3', '鞋子', '11111', '1111', '21.jpg', '<p>鞋子很好</p>', '1', '1');
INSERT INTO `goods` VALUES ('11', '3', '5', '皮鞋', '120', '19', '22.jpg', '<p>这是皮鞋</p>', '1', '1');
INSERT INTO `goods` VALUES ('12', '4', '3', '运动鞋', '500', '320', '8.jpg', '<p>这是</p>', '0', '0');
INSERT INTO `goods` VALUES ('13', '4', '4', '沙发', '1000', '19', '33.jpg', '<p>这是餐桌</p>', '0', '0');
INSERT INTO `goods` VALUES ('14', '4', '3', '项链', '2000', '20', '10.jpg', '<p>这是项链</p>', '0', '0');
INSERT INTO `goods` VALUES ('15', '4', '4', '桌子', '500', '17', '30.jpg', '<p>这是桌子</p>', '0', '0');
INSERT INTO `goods` VALUES ('16', '3', '3', '长袖', '11111', '1110', '20.jpg', '<p>我要出售</p>', '0', '0');
INSERT INTO `goods` VALUES ('17', '3', '4', '柜子', '11111', '1111', '31.jpg', '<p>我要购买</p>', '1', '1');
INSERT INTO `goods` VALUES ('18', '3', '5', '新华网', '11111', '1111', '8.jpg', '<p>111</p>', '0', '1');
INSERT INTO `goods` VALUES ('19', '3', '2', '新华网', '11111', '1111', '9.jpg', '<p>2222</p>', '1', '1');
INSERT INTO `goods` VALUES ('20', '3', '1', '1', '1', '1', '', '', '1', '1');
INSERT INTO `goods` VALUES ('21', '3', '1', '新华网', '11111', '1111', '4.png', '<p>我要卖鞋子</p>', '1', '1');
INSERT INTO `goods` VALUES ('22', '3', '3', '新华网', '11111', '1111', 'news1.jpg', '<p>aaa</p>', '1', '1');
INSERT INTO `goods` VALUES ('23', '3', '1', '新华网', '11111', '1111', '5.jpg', '<p>111</p>', '0', '1');
INSERT INTO `goods` VALUES ('24', '2', '3', '皮鞋', '100', '20', '5.png', '<p>我要买皮鞋</p>', '1', '1');
INSERT INTO `goods` VALUES ('25', '2', '1', 'aa', '11', '11', '3.jpg', '<p>11</p>', '0', '1');
INSERT INTO `goods` VALUES ('26', '3', '2', '222', '222', '222', '6.jpg', '<p>222</p>', '0', '1');
INSERT INTO `goods` VALUES ('27', '3', '1', '笔记本', '2000', '10', 'news1.jpg', '<p>我要买电脑</p>', '1', '1');
INSERT INTO `goods` VALUES ('28', '3', '3', '马丁靴', '200', '7', '23.jpg', '<p>鞋子很好</p>', '0', '0');
INSERT INTO `goods` VALUES ('29', '3', '3', '衬衫', '50', '10', 'news1.jpg', '<p>我要白色衬衫</p>', '1', '1');
INSERT INTO `goods` VALUES ('30', '3', '3', '11', '11', '11', 'news1.jpg', '<p>111222</p>', '1', '1');
INSERT INTO `goods` VALUES ('31', '4', '1', '笔记本', '5000', '10', '4.png', '<p>我要戴尔的笔记本</p>', '1', '1');
INSERT INTO `goods` VALUES ('32', '4', '1', '11', '11', '11', '3.png', '<p>111</p>', '0', '1');
INSERT INTO `goods` VALUES ('33', '3', '2', 'LOL', '500', '2', '3.png', '<p>我要买账号</p>', '1', '1');
INSERT INTO `goods` VALUES ('34', '2', '1', '笔记本', '2000', '4', '5.png', '<p>我要买笔记本</p>', '1', '0');
INSERT INTO `goods` VALUES ('35', '2', '3', '帽子', '50', '2', '5.png', '<p>我还要买帽子</p>', '1', '0');
INSERT INTO `goods` VALUES ('36', '3', '4', '笔记本', '20', '2', '5.png', '<p>我要买笔记本</p>', '1', '1');
INSERT INTO `goods` VALUES ('37', '4', '2', 'LOL', '50', '2', '4.png', '<p>我买LOL账号</p>', '1', '0');
INSERT INTO `goods` VALUES ('38', '3', '1', '111', '11111', '1111', '5.png', '<p>1111<br></p>', '1', '1');
INSERT INTO `goods` VALUES ('39', '3', '1', '222', '222', '222222', '5.png', '<p>222</p>', '1', '1');
INSERT INTO `goods` VALUES ('40', '8', '1', '新华网', '11111', '222', '9.jpg', '<p><img src=\"/trade/images/4.jpg\" style=\"max-width:100%;\">啊啊<br></p>', '0', '1');
INSERT INTO `goods` VALUES ('41', '8', '2', '新华网', '11111', '1111', '5.png', '<p>求购</p>', '1', '1');
INSERT INTO `goods` VALUES ('42', '3', '1', 'aa', '11', '111', '5.png', '<p>aa</p>', '1', '2');

-- ----------------------------
-- Table structure for goodstype
-- ----------------------------
DROP TABLE IF EXISTS `goodstype`;
CREATE TABLE `goodstype` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '产品类型',
  `isdel` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goodstype
-- ----------------------------
INSERT INTO `goodstype` VALUES ('1', '电子产品', '0');
INSERT INTO `goodstype` VALUES ('2', '游戏', '0');
INSERT INTO `goodstype` VALUES ('3', '服装', '0');
INSERT INTO `goodstype` VALUES ('4', '日用品', '0');
INSERT INTO `goodstype` VALUES ('5', '其他', '0');
INSERT INTO `goodstype` VALUES ('6', '新增产品类1', '1');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL COMMENT '用户',
  `pid` int(11) DEFAULT NULL COMMENT '商品',
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  `optime` varchar(255) DEFAULT NULL COMMENT '评论时间',
  `isdel` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`),
  KEY `pid` (`pid`),
  CONSTRAINT `message_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `users` (`id`),
  CONSTRAINT `message_ibfk_2` FOREIGN KEY (`pid`) REFERENCES `goods` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1', '2', '1', '很不错，很划算1', '2020/5/4', '1');
INSERT INTO `message` VALUES ('2', '2', '2', '很不错，很划算2', '2020/5/4', '1');
INSERT INTO `message` VALUES ('3', '3', '3', '很不错，很划算3', '2020/5/4', '1');
INSERT INTO `message` VALUES ('4', '3', '3', '很不错，很划算4', '2020/5/4', '1');
INSERT INTO `message` VALUES ('5', '2', '1', '我要留言\r\n', '2020-05-14 11:59:57', '1');
INSERT INTO `message` VALUES ('6', '2', '1', '我还要留言', '2020-05-14 12:00:07', '1');
INSERT INTO `message` VALUES ('7', '2', '1', '留言1', '2020-05-14 12:00:14', '1');
INSERT INTO `message` VALUES ('8', '2', '6', '我要留言', '2020-05-15 20:06:41', '1');
INSERT INTO `message` VALUES ('9', '4', '7', '我要留言', '2020-05-16 13:44:39', '1');
INSERT INTO `message` VALUES ('10', '3', '1', '我留言了', '2020-05-16 13:50:34', '1');
INSERT INTO `message` VALUES ('11', '4', '1', '我要留言333', '2020-05-16 13:52:39', '1');
INSERT INTO `message` VALUES ('12', '4', '4', '我要留言', '2020-05-16 14:01:40', '1');
INSERT INTO `message` VALUES ('13', '4', '7', '我要留言', '2020-05-16 14:08:50', '1');
INSERT INTO `message` VALUES ('14', '4', '7', '我要留言1', '2020-05-16 14:09:12', '1');
INSERT INTO `message` VALUES ('15', '4', '6', '留言1', '2020-05-16 14:25:39', '1');
INSERT INTO `message` VALUES ('16', '4', '12', '我要留言', '2020-05-16 18:03:56', '1');
INSERT INTO `message` VALUES ('17', '4', '13', '我要留言', '2020-05-17 10:37:37', '1');
INSERT INTO `message` VALUES ('18', '3', '29', '真的吗\r\n', '2020-05-20 17:29:27', '1');
INSERT INTO `message` VALUES ('19', '3', '31', '我也要笔记本', '2020-05-20 17:29:54', '1');
INSERT INTO `message` VALUES ('20', '3', '31', '我卖笔记本', '2020-05-20 17:38:04', '1');
INSERT INTO `message` VALUES ('21', '4', '31', '我卖笔记本', '2020-05-20 17:38:22', '1');
INSERT INTO `message` VALUES ('22', '4', '31', '亲亲搜索戴尔笔记本', '2020-05-20 18:02:37', '1');
INSERT INTO `message` VALUES ('23', '3', '29', '美女我有衬衫卖', '2020-05-20 20:42:43', '1');
INSERT INTO `message` VALUES ('24', '3', '29', '我要回复', '2020-05-20 20:43:02', '1');
INSERT INTO `message` VALUES ('25', '3', '29', '我要回复111', '2020-05-20 20:43:07', '1');
INSERT INTO `message` VALUES ('26', '3', '29', '我要回复111', '2020-05-20 20:43:24', '1');
INSERT INTO `message` VALUES ('27', '3', '29', '我要留言', '2020-05-20 20:46:06', '1');
INSERT INTO `message` VALUES ('28', '2', '31', '我也来留言', '2020-05-20 21:38:09', '1');
INSERT INTO `message` VALUES ('29', '3', '29', '我给衬衫留言', '2020-05-20 22:36:45', '1');
INSERT INTO `message` VALUES ('30', '3', '31', '我给笔记本留言', '2020-05-20 22:37:32', '1');
INSERT INTO `message` VALUES ('31', '3', '31', '再留', '2020-05-20 22:37:41', '1');
INSERT INTO `message` VALUES ('32', '3', '31', '我有卖', '2020-05-21 14:03:47', '1');
INSERT INTO `message` VALUES ('33', '4', '36', '我有笔记本卖', '2020-05-21 14:08:03', '1');
INSERT INTO `message` VALUES ('34', '4', '36', '亲亲联系我', '2020-05-21 14:10:20', '1');
INSERT INTO `message` VALUES ('35', '3', '4', '亲亲联系我哦', '2020-05-21 14:17:06', '1');
INSERT INTO `message` VALUES ('36', '3', '1', '我留言1', '2020-05-21 18:33:09', '1');
INSERT INTO `message` VALUES ('37', '3', '1', '我留言1', '2020-05-21 18:34:14', '1');
INSERT INTO `message` VALUES ('38', '3', '34', '我留言2', '2020-05-21 18:34:29', '1');
INSERT INTO `message` VALUES ('39', '3', '36', '我留言3', '2020-05-21 18:34:45', '1');
INSERT INTO `message` VALUES ('40', '3', '36', '我留言3', '2020-05-21 18:37:19', '1');
INSERT INTO `message` VALUES ('41', '3', '36', '我留', '2020-05-21 18:37:26', '1');
INSERT INTO `message` VALUES ('42', '3', '36', '我留', '2020-05-21 18:39:10', '1');
INSERT INTO `message` VALUES ('43', '3', '36', '我留哈哈哈', '2020-05-21 18:39:21', '1');
INSERT INTO `message` VALUES ('44', '3', '36', '我留哈哈哈', '2020-05-21 18:39:30', '1');
INSERT INTO `message` VALUES ('45', '3', '37', '我卖LOL', '2020-05-21 18:41:55', '1');
INSERT INTO `message` VALUES ('46', '3', '36', 'aaaaa', '2020-05-21 18:53:56', '1');
INSERT INTO `message` VALUES ('47', '3', '38', '我留言哈哈', '2020-05-21 19:28:37', '1');
INSERT INTO `message` VALUES ('48', '3', '38', '我还留', '2020-05-21 19:28:42', '1');
INSERT INTO `message` VALUES ('49', '3', '39', '我再留', '2020-05-21 19:28:55', '1');
INSERT INTO `message` VALUES ('50', '3', '34', '我有卖', '2020-05-21 20:04:06', '1');
INSERT INTO `message` VALUES ('51', '8', '16', '我要留言', '2020-05-25 15:11:43', '1');
INSERT INTO `message` VALUES ('52', '8', '34', '我有笔记本', '2020-05-25 15:12:34', '0');
INSERT INTO `message` VALUES ('53', '3', '34', '我有电脑卖', '2020-05-25 15:17:48', '0');
INSERT INTO `message` VALUES ('54', '2', '34', '我要留言', '2020-09-22 23:09:11', '0');
INSERT INTO `message` VALUES ('55', '2', '37', '我留言', '2020-09-22 23:09:39', '0');
INSERT INTO `message` VALUES ('56', '2', '28', '1111', '2020-09-23 10:21:50', '0');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `sid` int(11) DEFAULT NULL COMMENT '商家',
  `no` varchar(255) DEFAULT NULL COMMENT '订单号',
  `totalprice` double DEFAULT NULL COMMENT '总价',
  `optime` varchar(255) DEFAULT NULL COMMENT '交易时间',
  `status` varchar(255) DEFAULT NULL COMMENT '交易状态',
  `isdel` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sid` (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('1', '2', '2', '202005141534101', '1000', '2020-05-14 15:34:10', '未发货', '1');
INSERT INTO `orders` VALUES ('2', '2', '3', '202005141534102', '1000', '2020-05-14 15:34:10', '未发货', '1');
INSERT INTO `orders` VALUES ('3', '2', '2', '20200514154252956', '1111', '2020-05-14 15:42:52', '未发货', '1');
INSERT INTO `orders` VALUES ('4', '2', '2', '20200514154820103', '1111', '2020-05-14 15:48:20', '未发货', '1');
INSERT INTO `orders` VALUES ('5', '2', '3', '20200514154820195', '1111', '2020-05-14 15:48:20', '未发货', '1');
INSERT INTO `orders` VALUES ('6', '2', '2', '20200514172220615', '2000', '2020-05-14 17:22:20', '未发货', '1');
INSERT INTO `orders` VALUES ('7', '2', '3', '20200514172220800', '2000', '2020-05-14 17:22:20', '已发货', '1');
INSERT INTO `orders` VALUES ('8', '3', '2', '20200514225314779', '7000', '2020-05-14 22:53:14', '未发货', '1');
INSERT INTO `orders` VALUES ('9', '3', '3', '20200514225314813', '2000', '2020-05-14 22:53:14', '未发货', '1');
INSERT INTO `orders` VALUES ('10', '2', '2', '20200514234356935', '18000', '2020-05-14 23:43:56', '未发货', '1');
INSERT INTO `orders` VALUES ('11', '2', '3', '20200514234356978', '5000', '2020-05-14 23:43:56', '未发货', '1');
INSERT INTO `orders` VALUES ('12', '2', '3', '20200515171154393', '6000', '2020-05-15 17:11:54', '已发货', '1');
INSERT INTO `orders` VALUES ('13', '2', '3', '20200515171753776', '3000', '2020-05-15 17:17:53', '已发货', '1');
INSERT INTO `orders` VALUES ('14', '2', '3', '20200515175810240', '2000', '2020-05-15 17:58:10', '未发货', '1');
INSERT INTO `orders` VALUES ('15', '2', '3', '20200515180101996', '1000', '2020-05-15 18:01:01', '未发货', '1');
INSERT INTO `orders` VALUES ('16', '2', '3', '20200515180321796', '1000', '2020-05-15 18:03:21', '未发货', '1');
INSERT INTO `orders` VALUES ('17', '2', '3', '20200515180356151', '1000', '2020-05-15 18:03:56', '未发货', '1');
INSERT INTO `orders` VALUES ('18', '2', '3', '20200515183058048', '2000', '2020-05-15 18:30:58', '未发货', '1');
INSERT INTO `orders` VALUES ('19', '2', '3', '20200515183155902', '2000', '2020-05-15 18:31:55', '未发货', '1');
INSERT INTO `orders` VALUES ('20', '2', '3', '20200515200715038', '2000', '2020-05-15 20:07:15', '未发货', '1');
INSERT INTO `orders` VALUES ('21', '2', '3', '20200515200834045', '1000', '2020-05-15 20:08:34', '未发货', '1');
INSERT INTO `orders` VALUES ('22', '2', '3', '20200515200859849', '1000', '2020-05-15 20:08:59', '未发货', '1');
INSERT INTO `orders` VALUES ('23', '2', '3', '20200515201444903', '2000', '2020-05-15 20:14:44', '未发货', '1');
INSERT INTO `orders` VALUES ('24', '2', '3', '20200515201817614', '2000', '2020-05-15 20:18:17', '未发货', '1');
INSERT INTO `orders` VALUES ('25', '3', '2', '20200515202940488', '1000', '2020-05-15 20:29:40', '已发货', '1');
INSERT INTO `orders` VALUES ('26', '2', '3', '20200515202957406', '2000', '2020-05-15 20:29:57', '已发货', '1');
INSERT INTO `orders` VALUES ('27', '3', '2', '20200515204025637', '10000', '2020-05-15 20:40:25', '已发货', '1');
INSERT INTO `orders` VALUES ('28', '3', '2', '20200515204238932', '1000', '2020-05-15 20:42:38', '已发货', '1');
INSERT INTO `orders` VALUES ('29', '3', '2', '20200515204302086', '9000', '2020-05-15 20:43:02', '已发货', '1');
INSERT INTO `orders` VALUES ('30', '2', '3', '20200515204324559', '6000', '2020-05-15 20:43:24', '已发货', '1');
INSERT INTO `orders` VALUES ('31', '3', '2', '20200515204428754', '1000', '2020-05-15 20:44:28', '已发货', '1');
INSERT INTO `orders` VALUES ('32', '4', '2', '20200516134327820', '2000', '2020-05-16 13:43:27', '已发货', '1');
INSERT INTO `orders` VALUES ('33', '4', '3', '20200516134327878', '2000', '2020-05-16 13:43:27', '已发货', '1');
INSERT INTO `orders` VALUES ('34', '2', '4', '20200516134402020', '20', '2020-05-16 13:44:02', '已发货', '1');
INSERT INTO `orders` VALUES ('35', '4', '3', '20200516142812628', '2000', '2020-05-16 14:28:12', '已发货', '1');
INSERT INTO `orders` VALUES ('36', '3', '4', '20200516142849806', '2000', '2020-05-16 14:28:49', '已发货', '1');
INSERT INTO `orders` VALUES ('37', '4', '2', '20200516173552845', '1000', '2020-05-16 17:35:52', '已发货', '1');
INSERT INTO `orders` VALUES ('38', '3', '4', '20200516173601994', '1000', '2020-05-16 17:36:01', '已发货', '1');
INSERT INTO `orders` VALUES ('39', '4', '3', '20200516180127174', '1000', '2020-05-16 18:01:27', '已发货', '1');
INSERT INTO `orders` VALUES ('40', '2', '4', '20200516180219314', '10', '2020-05-16 18:02:19', '已发货', '1');
INSERT INTO `orders` VALUES ('41', '3', '4', '20200516180219352', '1120', '2020-05-16 18:02:19', '已发货', '1');
INSERT INTO `orders` VALUES ('42', '3', '2', '20200519224136081', '1000', '2020-05-19 22:41:36', '已发货', '1');
INSERT INTO `orders` VALUES ('43', '2', '3', '20200519224143837', '30', '2020-05-19 22:41:43', '已发货', '1');
INSERT INTO `orders` VALUES ('44', '3', '2', '20200520173016406', '1000', '2020-05-20 17:30:16', '已发货', '0');
INSERT INTO `orders` VALUES ('45', '3', '4', '20200520173016449', '1000', '2020-05-20 17:30:16', '已发货', '0');
INSERT INTO `orders` VALUES ('46', '2', '3', '20200525103834579', '2000', '2020-05-25 10:38:34', '未发货', '0');
INSERT INTO `orders` VALUES ('47', '2', '4', '20200525103834644', '1000', '2020-05-25 10:38:34', '已发货', '1');
INSERT INTO `orders` VALUES ('48', '2', '3', '20200525104054160', '200', '2020-05-25 10:40:54', '未发货', '0');
INSERT INTO `orders` VALUES ('49', '4', '2', '20200525105145255', '3000', '2020-05-25 10:51:45', '已发货', '0');
INSERT INTO `orders` VALUES ('50', '4', '3', '20200525105145313', '200', '2020-05-25 10:51:45', '未发货', '0');
INSERT INTO `orders` VALUES ('51', '4', '2', '20200525105506591', '1000', '2020-05-25 10:55:06', '已发货', '0');
INSERT INTO `orders` VALUES ('52', '8', '3', '20200525151242126', '11311', '2020-05-25 15:12:42', '未发货', '0');
INSERT INTO `orders` VALUES ('53', '8', '4', '20200525151242235', '500', '2020-05-25 15:12:42', '未发货', '0');
INSERT INTO `orders` VALUES ('54', '8', '2', '20200525151316542', '1000', '2020-05-25 15:13:16', '已发货', '0');
INSERT INTO `orders` VALUES ('55', '2', '3', '20200922230803555', '4000', '2020-09-22 23:08:03', '未发货', '0');
INSERT INTO `orders` VALUES ('56', '2', '3', '20200923101843714', '1000', '2020-09-23 10:18:43', '未发货', '0');

-- ----------------------------
-- Table structure for ordertail
-- ----------------------------
DROP TABLE IF EXISTS `ordertail`;
CREATE TABLE `ordertail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `oid` int(11) DEFAULT NULL COMMENT '订单',
  `pid` int(11) DEFAULT NULL COMMENT '商品',
  `quantity` int(11) DEFAULT NULL COMMENT '数量',
  PRIMARY KEY (`id`),
  KEY `oid` (`oid`),
  KEY `pid` (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ordertail
-- ----------------------------
INSERT INTO `ordertail` VALUES ('1', '1', '1', '1');
INSERT INTO `ordertail` VALUES ('2', '1', '3', '1');
INSERT INTO `ordertail` VALUES ('3', '2', '4', '1');
INSERT INTO `ordertail` VALUES ('4', '2', '6', '1');
INSERT INTO `ordertail` VALUES ('5', '3', '1', '1');
INSERT INTO `ordertail` VALUES ('6', '4', '1', '1');
INSERT INTO `ordertail` VALUES ('7', '4', '3', '1');
INSERT INTO `ordertail` VALUES ('8', '5', '4', '1');
INSERT INTO `ordertail` VALUES ('9', '5', '6', '1');
INSERT INTO `ordertail` VALUES ('10', '6', '1', '1');
INSERT INTO `ordertail` VALUES ('11', '6', '3', '1');
INSERT INTO `ordertail` VALUES ('12', '7', '4', '1');
INSERT INTO `ordertail` VALUES ('13', '7', '6', '1');
INSERT INTO `ordertail` VALUES ('14', '8', '1', '3');
INSERT INTO `ordertail` VALUES ('15', '8', '3', '4');
INSERT INTO `ordertail` VALUES ('16', '9', '4', '1');
INSERT INTO `ordertail` VALUES ('17', '9', '6', '1');
INSERT INTO `ordertail` VALUES ('18', '10', '1', '10');
INSERT INTO `ordertail` VALUES ('19', '10', '3', '4');
INSERT INTO `ordertail` VALUES ('20', '10', '2', '4');
INSERT INTO `ordertail` VALUES ('21', '11', '4', '1');
INSERT INTO `ordertail` VALUES ('22', '11', '6', '2');
INSERT INTO `ordertail` VALUES ('23', '11', '5', '2');
INSERT INTO `ordertail` VALUES ('24', '12', '4', '3');
INSERT INTO `ordertail` VALUES ('25', '12', '5', '3');
INSERT INTO `ordertail` VALUES ('26', '13', '6', '3');
INSERT INTO `ordertail` VALUES ('27', '14', '4', '1');
INSERT INTO `ordertail` VALUES ('28', '14', '5', '1');
INSERT INTO `ordertail` VALUES ('29', '15', '6', '1');
INSERT INTO `ordertail` VALUES ('30', '16', '6', '1');
INSERT INTO `ordertail` VALUES ('31', '17', '4', '1');
INSERT INTO `ordertail` VALUES ('32', '18', '5', '1');
INSERT INTO `ordertail` VALUES ('33', '18', '4', '1');
INSERT INTO `ordertail` VALUES ('34', '19', '4', '1');
INSERT INTO `ordertail` VALUES ('35', '19', '5', '1');
INSERT INTO `ordertail` VALUES ('36', '20', '6', '2');
INSERT INTO `ordertail` VALUES ('37', '21', '6', '1');
INSERT INTO `ordertail` VALUES ('38', '22', '4', '1');
INSERT INTO `ordertail` VALUES ('39', '23', '6', '2');
INSERT INTO `ordertail` VALUES ('40', '24', '6', '2');
INSERT INTO `ordertail` VALUES ('41', '25', '6', '1');
INSERT INTO `ordertail` VALUES ('42', '26', '4', '1');
INSERT INTO `ordertail` VALUES ('43', '26', '5', '1');
INSERT INTO `ordertail` VALUES ('44', '27', '6', '10');
INSERT INTO `ordertail` VALUES ('45', '28', '6', '1');
INSERT INTO `ordertail` VALUES ('46', '29', '6', '9');
INSERT INTO `ordertail` VALUES ('47', '30', '5', '6');
INSERT INTO `ordertail` VALUES ('48', '31', '6', '1');
INSERT INTO `ordertail` VALUES ('49', '32', '1', '1');
INSERT INTO `ordertail` VALUES ('50', '32', '2', '1');
INSERT INTO `ordertail` VALUES ('51', '33', '4', '1');
INSERT INTO `ordertail` VALUES ('52', '33', '5', '1');
INSERT INTO `ordertail` VALUES ('53', '34', '7', '2');
INSERT INTO `ordertail` VALUES ('54', '35', '4', '2');
INSERT INTO `ordertail` VALUES ('55', '36', '6', '2');
INSERT INTO `ordertail` VALUES ('56', '37', '2', '1');
INSERT INTO `ordertail` VALUES ('57', '38', '6', '1');
INSERT INTO `ordertail` VALUES ('58', '39', '5', '1');
INSERT INTO `ordertail` VALUES ('59', '40', '7', '1');
INSERT INTO `ordertail` VALUES ('60', '41', '6', '1');
INSERT INTO `ordertail` VALUES ('61', '41', '11', '1');
INSERT INTO `ordertail` VALUES ('62', '42', '1', '1');
INSERT INTO `ordertail` VALUES ('63', '43', '7', '3');
INSERT INTO `ordertail` VALUES ('64', '44', '1', '1');
INSERT INTO `ordertail` VALUES ('65', '45', '15', '2');
INSERT INTO `ordertail` VALUES ('66', '46', '4', '1');
INSERT INTO `ordertail` VALUES ('67', '46', '5', '1');
INSERT INTO `ordertail` VALUES ('68', '47', '13', '1');
INSERT INTO `ordertail` VALUES ('69', '48', '28', '1');
INSERT INTO `ordertail` VALUES ('70', '49', '1', '2');
INSERT INTO `ordertail` VALUES ('71', '49', '2', '1');
INSERT INTO `ordertail` VALUES ('72', '50', '28', '1');
INSERT INTO `ordertail` VALUES ('73', '51', '1', '1');
INSERT INTO `ordertail` VALUES ('74', '52', '16', '1');
INSERT INTO `ordertail` VALUES ('75', '52', '28', '1');
INSERT INTO `ordertail` VALUES ('76', '53', '15', '1');
INSERT INTO `ordertail` VALUES ('77', '54', '1', '1');
INSERT INTO `ordertail` VALUES ('78', '55', '5', '1');
INSERT INTO `ordertail` VALUES ('79', '55', '4', '3');
INSERT INTO `ordertail` VALUES ('80', '56', '4', '1');

-- ----------------------------
-- Table structure for reply
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mid` int(11) DEFAULT NULL COMMENT '回复',
  `uid` int(11) DEFAULT NULL COMMENT '用户',
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  `optime` varchar(255) DEFAULT NULL COMMENT '回复时间',
  PRIMARY KEY (`id`),
  KEY `mid` (`mid`),
  KEY `uid` (`uid`),
  CONSTRAINT `reply_ibfk_1` FOREIGN KEY (`mid`) REFERENCES `message` (`id`),
  CONSTRAINT `reply_ibfk_2` FOREIGN KEY (`uid`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reply
-- ----------------------------
INSERT INTO `reply` VALUES ('3', '3', '2', '哈哈3', '2020/5/4');
INSERT INTO `reply` VALUES ('5', '7', '2', '我要回复', '2020-05-14 12:00:27');
INSERT INTO `reply` VALUES ('6', '7', '2', '我也要回复\r\n', '2020-05-14 13:53:56');
INSERT INTO `reply` VALUES ('7', '8', '2', '我要回复', '2020-05-15 20:06:51');
INSERT INTO `reply` VALUES ('21', '8', '1', '回复1', '2020-05-16 16:13:30');
INSERT INTO `reply` VALUES ('32', '21', '4', '你卖笔记本是吧', '2020-05-20 17:38:36');
INSERT INTO `reply` VALUES ('40', '22', '3', '我有电脑卖', '2020-05-20 21:26:25');
INSERT INTO `reply` VALUES ('41', '22', '3', '我卖', '2020-05-20 21:27:09');
INSERT INTO `reply` VALUES ('42', '5', '3', '33', '2020-05-20 21:30:11');
INSERT INTO `reply` VALUES ('43', '28', '4', '回复你了', '2020-05-20 21:38:39');
INSERT INTO `reply` VALUES ('46', '6', '4', '333', '2020-05-20 21:58:19');
INSERT INTO `reply` VALUES ('47', '20', '4', '111', '2020-05-20 22:23:25');
INSERT INTO `reply` VALUES ('50', '20', '4', '333', '2020-05-20 22:34:51');
INSERT INTO `reply` VALUES ('53', '30', '4', '哪里卖哟', '2020-05-20 22:39:13');
INSERT INTO `reply` VALUES ('54', '32', '4', '真的吗 怎么联系', '2020-05-21 14:04:21');
INSERT INTO `reply` VALUES ('55', '33', '3', '怎么联系呢亲\r\n', '2020-05-21 14:08:36');
INSERT INTO `reply` VALUES ('56', '34', '3', '行吧', '2020-05-21 14:17:47');
INSERT INTO `reply` VALUES ('58', '34', '3', 'OKK', '2020-05-21 14:18:13');
INSERT INTO `reply` VALUES ('60', '34', '3', '???', '2020-05-21 15:13:07');
INSERT INTO `reply` VALUES ('61', '33', '3', '???', '2020-05-21 15:17:05');
INSERT INTO `reply` VALUES ('62', '4', '3', '嗯哼', '2020-05-21 16:36:21');
INSERT INTO `reply` VALUES ('63', '4', '3', '嗯哼\r\n', '2020-05-21 16:38:11');
INSERT INTO `reply` VALUES ('64', '4', '3', '弄啥类', '2020-05-21 16:38:29');
INSERT INTO `reply` VALUES ('65', '4', '3', '弄啥类', '2020-05-21 16:38:41');
INSERT INTO `reply` VALUES ('66', '4', '3', '那行吧', '2020-05-21 16:39:15');
INSERT INTO `reply` VALUES ('67', '10', '3', '我也留言了\r\n', '2020-05-21 16:39:46');
INSERT INTO `reply` VALUES ('69', '33', '3', '你买笔记本是吧', '2020-05-21 17:18:48');
INSERT INTO `reply` VALUES ('70', '33', '4', '是的呢', '2020-05-21 17:23:55');
INSERT INTO `reply` VALUES ('71', '33', '3', '行吧', '2020-05-21 17:28:39');
INSERT INTO `reply` VALUES ('72', '1', '3', '我回复1', '2020-05-21 18:33:19');
INSERT INTO `reply` VALUES ('75', '37', '3', '我还回复你', '2020-05-21 18:35:30');
INSERT INTO `reply` VALUES ('77', '38', '3', '嗯哼', '2020-05-21 18:36:00');
INSERT INTO `reply` VALUES ('78', '38', '3', '嗯哈哈', '2020-05-21 18:37:41');
INSERT INTO `reply` VALUES ('79', '41', '3', '111', '2020-05-21 18:38:47');
INSERT INTO `reply` VALUES ('80', '43', '3', '你越惆怅 我越高尚', '2020-05-21 18:40:07');
INSERT INTO `reply` VALUES ('81', '43', '3', '？？？', '2020-05-21 18:40:11');
INSERT INTO `reply` VALUES ('82', '45', '4', '听说你卖LOL？', '2020-05-21 18:42:21');
INSERT INTO `reply` VALUES ('83', '45', '3', '是的呢亲', '2020-05-21 18:42:44');
INSERT INTO `reply` VALUES ('84', '45', '4', '那赶紧联系吧', '2020-05-21 18:43:03');
INSERT INTO `reply` VALUES ('85', '46', '3', 'aaaa', '2020-05-21 18:54:02');
INSERT INTO `reply` VALUES ('87', '46', '3', 'aaa', '2020-05-21 18:54:13');
INSERT INTO `reply` VALUES ('88', '49', '3', '我回复', '2020-05-21 19:29:02');
INSERT INTO `reply` VALUES ('90', '47', '3', '叼你吗', '2020-05-21 19:29:25');
INSERT INTO `reply` VALUES ('91', '49', '3', '我回复', '2020-05-21 19:30:12');
INSERT INTO `reply` VALUES ('92', '6', '2', '嗯  不错', '2020-05-25 10:43:38');
INSERT INTO `reply` VALUES ('95', '50', '3', '我还回复', '2020-05-25 10:49:01');
INSERT INTO `reply` VALUES ('96', '51', '8', '我要回复', '2020-05-25 15:12:07');
INSERT INTO `reply` VALUES ('98', '52', '2', '回复', '2020-05-25 15:18:08');
INSERT INTO `reply` VALUES ('99', '52', '2', '111', '2020-05-25 15:18:29');
INSERT INTO `reply` VALUES ('100', '52', '2', '222', '2020-05-25 15:18:30');
INSERT INTO `reply` VALUES ('101', '53', '2', '我是111', '2020-05-25 15:18:42');
INSERT INTO `reply` VALUES ('102', '53', '3', '收到', '2020-05-25 15:18:50');
INSERT INTO `reply` VALUES ('103', '54', '2', '我要回复', '2020-09-22 23:09:18');
INSERT INTO `reply` VALUES ('104', '54', '2', '今天是10/8\r\n', '2020-10-08 09:19:33');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `pic` varchar(255) DEFAULT NULL COMMENT '头像',
  `role` int(11) DEFAULT NULL COMMENT '管理员权限',
  `phone` varchar(255) DEFAULT NULL COMMENT '电话',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `name` varchar(255) DEFAULT NULL COMMENT '昵称',
  `sex` varchar(255) DEFAULT NULL COMMENT '性别',
  `grade` varchar(255) DEFAULT NULL COMMENT '年级',
  `school` varchar(255) DEFAULT NULL COMMENT '学校',
  `major` varchar(255) DEFAULT NULL COMMENT '专业',
  `isdel` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'news1.jpg', '1', 'admin', 'admin', 'admin', '', '', '', '', '0');
INSERT INTO `users` VALUES ('2', '5.png', '0', '111', '111', '111', '男', '大二', '东湖', '计算机', '0');
INSERT INTO `users` VALUES ('3', '5.png', '0', '222', '222', '222', '女', '大一', '汉口', '英语', '0');
INSERT INTO `users` VALUES ('4', '4.png', '0', '333', '333', '333', '男', '大二', '华中', '日语', '0');
INSERT INTO `users` VALUES ('5', '', '0', '444', '444', '河马', '男', '大二', '汉口学院', '日语', '1');
INSERT INTO `users` VALUES ('6', '', '0', '444', 'aaa', 'aaaa', '男', 'aaa', 'aaa', 'aaa', '1');
INSERT INTO `users` VALUES ('7', '', '0', 'aaa', 'aaaa', 'a', '男', 'a', 'a', 'a', '1');
INSERT INTO `users` VALUES ('8', '5.png', '0', '444', '444', '小啊', '女', '大二', 'DON', '日语', '1');
INSERT INTO `users` VALUES ('9', '', '0', '555', '555', '中华', '男', '大二', '东湖', '语言', '1');
