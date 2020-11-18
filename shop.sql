DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(25) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '用户名',
  `password` varchar(25) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '密码',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '邮箱',
  `name` varchar(25) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '姓名',
  `age` int(0) NULL DEFAULT NULL COMMENT '年龄',
  `gender` varchar(5) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '性别',
  `code` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '激活码',
  `flag` int(0) NOT NULL COMMENT '激活状态',
  `role` int(0) NOT NULL COMMENT '角色',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;


INSERT INTO `user` VALUES (1, 'admin', '888', '2602341707@qq.com', '张宝旭', 21, '男', '', 1, 0);
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE `types` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类别的主键id',
  `t_name` varchar(20) DEFAULT NULL COMMENT '类别的名称',
  `t_info` varchar(200) DEFAULT NULL COMMENT '类别的描述',
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='商品模块的类别实体';
BEGIN;
INSERT INTO `types` VALUES (1, '手机', '小米手机，为发烧而生！');
INSERT INTO `types` VALUES (2, '配件', '小米手机专用配件，爱护你的手机！');
COMMIT;

CREATE TABLE `goods` (
  `g_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品的唯一主键',
  `t_id` int(11) DEFAULT NULL COMMENT '类别的主键id',
  `g_name` varchar(50) DEFAULT NULL COMMENT '商品的名称',
  `g_time` date DEFAULT NULL COMMENT '商品的上市时间',
  `g_image` varchar(100) DEFAULT NULL COMMENT '商品图片的路径',
  `g_price` decimal(12,2) DEFAULT NULL COMMENT '商品的价格',
  `g_state` int(11) DEFAULT NULL COMMENT '商品的热门指数',
  `g_info` varchar(200) DEFAULT NULL COMMENT '商品的描述',
  PRIMARY KEY (`g_id`),
  KEY `FK_t_p_fk` (`t_id`),
  CONSTRAINT `FK_t_p_fk` FOREIGN KEY (`t_id`) REFERENCES `types` (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='商品模块的商品实体';

BEGIN;
INSERT INTO `goods` VALUES (1, 1, '红米4', '1990-01-01', 'image/liebiao_hongmin4.jpg', 1999.00, 4, '红米4手机，主打性价比！吸引年轻');
INSERT INTO `goods` VALUES (2, 1, '红米4', '1990-01-01', 'image/liebiao_hongmin4.jpg', 1999.00, 4, '红米4手机，主打性价比！吸引年轻');
INSERT INTO `goods` VALUES (3, 1, '红米4', '1990-01-01', 'image/liebiao_hongmin4.jpg', 1999.00, 4, '红米4手机，主打性价比！吸引年轻');
INSERT INTO `goods` VALUES (4, 1, '红米4', '1990-01-01', 'image/liebiao_hongmin4.jpg', 1999.00, 4, '红米4手机，主打性价比！吸引年轻');
INSERT INTO `goods` VALUES (5, 1, '红米4', '1990-01-01', 'image/liebiao_hongmin4.jpg', 1999.00, 4, '红米4手机，主打性价比！吸引年轻');
INSERT INTO `goods` VALUES (6, 1, '红米4', '1990-01-01', 'image/liebiao_hongmin4.jpg', 1999.00, 4, '红米4手机，主打性价比！吸引年轻');
INSERT INTO `goods` VALUES (7, 1, '红米4', '1990-01-01', 'image/liebiao_hongmin4.jpg', 1999.00, 4, '红米4手机，主打性价比！吸引年轻');
INSERT INTO `goods` VALUES (8, 1, '红米4', '1990-01-01', 'image/liebiao_hongmin4.jpg', 1999.00, 4, '红米4手机，主打性价比！吸引年轻');
INSERT INTO `goods` VALUES (9, 1, '红米4', '1990-01-01', 'image/liebiao_hongmin4.jpg', 1999.00, 4, '红米4手机，主打性价比！吸引年轻');
INSERT INTO `goods` VALUES (10, 1, '红米4', '1990-01-01', 'image/liebiao_hongmin4.jpg', 1999.00, 4, '红米4手机，主打性价比！吸引年轻');
INSERT INTO `goods` VALUES (11, 1, '红米4', '1990-01-01', 'image/liebiao_hongmin4.jpg', 1999.00, 4, '红米4手机，主打性价比！吸引年轻');
INSERT INTO `goods` VALUES (12, 1, '红米4', '1990-01-01', 'image/liebiao_hongmin4.jpg', 1999.00, 4, '红米4手机，主打性价比！吸引年轻');
INSERT INTO `goods` VALUES (13, 2, '红米4手机壳', '1990-01-01', 'image/peijian2.jpg', 20.00, 5, '红米4手机壳，用心保护你的手机');
INSERT INTO `goods` VALUES (14, 2, '红米4手机壳', '1990-01-01', 'image/peijian2.jpg', 20.00, 5, '红米4手机壳，用心保护你的手机');
INSERT INTO `goods` VALUES (15, 2, '红米4手机壳', '1990-01-01', 'image/peijian2.jpg', 20.00, 5, '红米4手机壳，用心保护你的手机');
INSERT INTO `goods` VALUES (16, 2, '红米4手机壳', '1990-01-01', 'image/peijian2.jpg', 20.00, 5, '红米4手机壳，用心保护你的手机');
INSERT INTO `goods` VALUES (17, 2, '红米4手机壳', '1990-01-01', 'image/peijian2.jpg', 20.00, 5, '红米4手机壳，用心保护你的手机');
INSERT INTO `goods` VALUES (18, 2, '红米4手机壳', '1990-01-01', 'image/peijian2.jpg', 20.00, 5, '红米4手机壳，用心保护你的手机');
INSERT INTO `goods` VALUES (19, 2, '红米4手机壳', '1990-01-01', 'image/peijian2.jpg', 20.00, 5, '红米4手机壳，用心保护你的手机');
INSERT INTO `goods` VALUES (20, 2, '红米4手机壳', '1990-01-01', 'image/peijian2.jpg', 20.00, 5, '红米4手机壳，用心保护你的手机');
INSERT INTO `goods` VALUES (21, 2, '红米4手机壳', '1990-01-01', 'image/peijian2.jpg', 20.00, 5, '红米4手机壳，用心保护你的手机');
INSERT INTO `goods` VALUES (22, 2, '红米4手机壳', '1990-01-01', 'image/peijian2.jpg', 20.00, 5, '红米4手机壳，用心保护你的手机');
INSERT INTO `goods` VALUES (23, 2, '红米4手机壳', '1990-01-01', 'image/peijian2.jpg', 20.00, 5, '红米4手机壳，用心保护你的手机');
INSERT INTO `goods` VALUES (24, 2, '红米4手机壳', '1990-01-01', 'image/peijian2.jpg', 20.00, 5, '红米4手机壳，用心保护你的手机');
INSERT INTO `goods` VALUES (25, 2, '红米4手机壳', '1990-01-01', 'image/peijian2.jpg', 20.00, 5, '红米4手机壳，用心保护你的手机');
INSERT INTO `goods` VALUES (26, 2, '红米4手机壳', '1990-01-01', 'image/peijian2.jpg', 20.00, 5, '红米4手机壳，用心保护你的手机');
COMMIT;

CREATE TABLE `cart` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '购物车的唯一标识',
  `u_id` int(11) DEFAULT NULL COMMENT '用户实体的主键属性',
  `g_id` int(11) DEFAULT NULL COMMENT '商品的唯一主键',
  `c_count` decimal(12,2) DEFAULT NULL COMMENT '购物车小计',
  `c_num` int(11) DEFAULT NULL COMMENT '购物车商品数量',
  PRIMARY KEY (`c_id`),
  KEY `FK_p_c_fk` (`g_id`),
  KEY `FK_u_c_fk` (`u_id`),
  CONSTRAINT `FK_p_c_fk` FOREIGN KEY (`g_id`) REFERENCES `goods` (`g_id`),
  CONSTRAINT `FK_u_c_fk` FOREIGN KEY (`u_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='购物车实体';

CREATE TABLE `address` (
  `a_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '地址实体的唯一主键列',
  `u_id` int(11) DEFAULT NULL COMMENT '用户实体的主键属性',
  `a_name` varchar(30) DEFAULT NULL COMMENT '地址的收件人',
  `a_phone` varchar(14) DEFAULT NULL COMMENT '收件人电话',
  `a_detail` varchar(200) DEFAULT NULL COMMENT '收货人详细地址',
  `a_state` int(11) DEFAULT NULL COMMENT '是否是默认地址 0 不是 1是默认地址',
  PRIMARY KEY (`a_id`),
  KEY `FK_u_a_fk` (`u_id`),
  CONSTRAINT `FK_u_a_fk` FOREIGN KEY (`u_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户个人中心的地址实体，用于存储地址信息';

BEGIN;
INSERT INTO `address` VALUES (1, 1, '张三', '176116112322', '北京昌平北科技术学校', 1);
INSERT INTO `address` VALUES (2, 1, '张三', '17611643234', '上海宝山区', 0);
COMMIT;

CREATE TABLE `orders` (
  `o_id` varchar(64) NOT NULL COMMENT '订单编号是字符串类型但是也是唯一标识',
  `u_id` int(11) DEFAULT NULL COMMENT '用户实体的主键属性',
  `a_id` int(11) DEFAULT NULL COMMENT '地址实体的唯一主键列',
  `o_count` decimal(12,2) DEFAULT NULL COMMENT '订单的总金额',
  `o_time` datetime DEFAULT NULL COMMENT '订单的详细时间',
  `o_state` int(11) DEFAULT NULL COMMENT '订单状态 0 未付款，1已经付款未发货 2发货待收货 3 收货待评价 4订单完成 5 退货状态',
  PRIMARY KEY (`o_id`),
  KEY `FK_a_o_fk` (`a_id`),
  KEY `FK_u_o_fk` (`u_id`),
  CONSTRAINT `FK_a_o_fk` FOREIGN KEY (`a_id`) REFERENCES `address` (`a_id`),
  CONSTRAINT `FK_u_o_fk` FOREIGN KEY (`u_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单模块的订单实体';

BEGIN;
INSERT INTO `orders` VALUES ('20200909111816002', 1, 1, 1999.00, '2020-09-09 11:18:16', 1);
COMMIT;