CREATE DATABASE IF NOT EXISTS `jxc` DEFAULT CHARACTER SET utf8;
use 'jxc';
/*1.客户表 */
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT,
 `account` VARCHAR(20) NOT NULL COMMENT '账号',
 `password` VARCHAR(20) NOT NULL COMMENT '密码',
 `user_name` VARCHAR(50) NOT NULL COMMENT '姓名',
 `user_desc` VARCHAR(100) COMMENT '备注',
 `created_by` VARCHAR(20) NOT NULL COMMENT '创建人',
 `updated_by` VARCHAR(20) NOT NULL COMMENT '更新人',
 `created_date` datetime DEFAULT NULL COMMENT '创建时间',
 `updated_date` datetime DEFAULT NULL COMMENT '更新时间',
 PRIMARY KEY (`id`)
) ENGINE=InnoDB　DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `customer`;
/*1.客户表 */
CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `city_code` varchar(255) DEFAULT NULL COMMENT '所在城市，最好是6位编码',
  `note` varchar(60) DEFAULT NULL COMMENT '备注',
  `status` int(1) DEFAULT 0 COMMENT '状态，0是删除，1是正常',
  `created_by` VARCHAR(20) NOT NULL COMMENT '创建人',
  `updated_by` VARCHAR(20) NOT NULL COMMENT '更新人',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB　DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `supplier`;
/*2.供应商表（厂家表） */
CREATE TABLE `supplier` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) NOT NULL COMMENT '厂家编码',
  `name` varchar(30) NOT NULL,
  `link_mobile` varchar(50) DEFAULT NULL COMMENT '联系方式',
  `link_address` varchar(100) DEFAULT NULL COMMENT '联系地址',
  `fax` varchar(50) DEFAULT NULL COMMENT '传真，多个逗号分割',
  `city_code` varchar(255) DEFAULT NULL COMMENT '所在城市，最好是6位编码',
  `note` varchar(60) DEFAULT NULL COMMENT '备注',
  `status` int(1) DEFAULT 0 COMMENT '状态，0是删除，1是正常',
  `created_by` VARCHAR(20) NOT NULL COMMENT '创建人',
  `updated_by` VARCHAR(20) NOT NULL COMMENT '更新人',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB　DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `product`;
/*3.产品表 */
CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `supplier_id` bigint(20) NOT NULL,
  `code` varchar(20) NOT NULL,
  `name` varchar(30) NOT NULL,
  `specs` varchar(30) NOT NULL COMMENT '产品规格',
  `unit` varchar(20) NOT NULL COMMENT '单位',
  `note` varchar(60) DEFAULT NULL COMMENT '备注',
  `price` decimal(19,2) DEFAULT 0.00 COMMENT '毛利价格',
  `status` int(1) DEFAULT 0 COMMENT '客户状态，0是删除，1是正常',
  `created_by` VARCHAR(20) NOT NULL COMMENT '创建人',
  `updated_by` VARCHAR(20) NOT NULL COMMENT '更新人',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB　DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `storage`;
/*4.进货表 　*/
CREATE TABLE `storage` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `supplier_id` bigint(20) NOT NULL COMMENT '供货商主键',
  `amount` decimal(19,2) DEFAULT 0.00 COMMENT '合计金额',
  `supplier_mobile` varchar(20) DEFAULT NULL COMMENT '发货人电话',
  `express_mobile` varchar(20) DEFAULT NULL COMMENT '物流司机电话',
  `receiver` varchar(10) DEFAULT 0 COMMENT '接收人',
  `deliverer` varchar(30) NOT NULL COMMENT '发货人',
  `deliver_date` varchar(30) NOT NULL COMMENT '送货日期',
  `receive_date` varchar(30) NOT NULL COMMENT '签收日期',
  `batch` varchar(30) NOT NULL COMMENT '进货批次号',
  `note` varchar(60) DEFAULT NULL COMMENT '备注',
  `status` int(1) DEFAULT 0 COMMENT '状态，0是删除，1是正常',
  `created_by` VARCHAR(20) NOT NULL COMMENT '创建人',
  `updated_by` VARCHAR(20) NOT NULL COMMENT '更新人',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB　DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `storage_detail`;
/*5.进货明细表 产品编码	产品名称	产品规格	单位	单价	数量	金额	备注　*/
CREATE TABLE `storage_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `supplier_id` bigint(20) NOT NULL COMMENT '供货商主键',
  `product_id` bigint(20) NOT NULL COMMENT '产品主键',
  `product_code` varchar(30) NOT NULL COMMENT '产品编码',
  `price` decimal(19,2) DEFAULT 0.00 COMMENT '进货价格',
  `count` bigint(19) DEFAULT 0 COMMENT '进货数量',
  `amount` decimal(19,2) DEFAULT 0.00 COMMENT '进货金额',
  `batch` varchar(30) NOT NULL COMMENT '进货批次号',
  `note` varchar(60) DEFAULT NULL COMMENT '备注',
  `status` int(1) DEFAULT 0 COMMENT '状态，0是删除，1是正常',
  `created_by` VARCHAR(20) NOT NULL COMMENT '创建人',
  `updated_by` VARCHAR(20) NOT NULL COMMENT '更新人',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB　DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sale_order`;
/*6.订单表（销售表） */
CREATE TABLE `sale_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customer_id` bigint(20) NOT NULL COMMENT '客户主键',
  `account_id` bigint(20) NOT NULL COMMENT '账号主键',
  `amount` decimal(19,2) DEFAULT 0.00 COMMENT '合计金额',
  `receiver_mobile` varchar(20) DEFAULT NULL COMMENT '接收人电话',
  `express_mobile` varchar(20) DEFAULT NULL COMMENT '物流司机电话',
  `receiver` varchar(10) DEFAULT 0 COMMENT '接收人',
  `deliverer` varchar(30) NOT NULL COMMENT '发货人',
  `sale_date` varchar(30) NOT NULL COMMENT '销售日期',
  `receive_date` varchar(30) NOT NULL COMMENT '签收日期',
  `batch` varchar(30) NOT NULL COMMENT '订单号',
  `note` varchar(60) DEFAULT NULL COMMENT '备注',
  `status` int(1) DEFAULT 0 COMMENT '状态，0是删除，1是正常',
  `created_by` VARCHAR(20) NOT NULL COMMENT '创建人',
  `updated_by` VARCHAR(20) NOT NULL COMMENT '更新人',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB　DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sale_detail`;
/*7.售货明细表 */
CREATE TABLE `sale_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `supplier_id` bigint(20) NOT NULL COMMENT '供货商主键',
  `product_id` bigint(20) NOT NULL COMMENT '产品主键',
  `product_code` varchar(30) NOT NULL COMMENT '产品编码',
  `price` decimal(19,2) DEFAULT 0.00 COMMENT '售出价格',
  `count` int(10) DEFAULT 0 COMMENT '售出数量',
  `amount` decimal(19,2) DEFAULT 0.00 COMMENT '售出金额',
  `batch` varchar(30) NOT NULL COMMENT '订单号',
  `note` varchar(60) DEFAULT NULL COMMENT '备注',
  `status` int(1) DEFAULT 0 COMMENT '状态，0是删除，1是正常',
  `created_by` VARCHAR(20) NOT NULL COMMENT '创建人',
  `updated_by` VARCHAR(20) NOT NULL COMMENT '更新人',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB　DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sale_amount`;
/*8.销售额表，也可以用于排名　*/
CREATE TABLE `sale_amount` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `supplier_id` bigint(20) NOT NULL COMMENT '供货商主键',
  `product_id` bigint(20) NOT NULL COMMENT '产品主键',
  `customer_id` bigint(20) NOT NULL COMMENT '客户主键',
  `account_id` bigint(20) NOT NULL COMMENT '客户主键',
  `amount` decimal(19,2) DEFAULT 0.00 COMMENT '售出金额',
  `sale_date` varchar(30) NOT NULL COMMENT '销售日期',
  `statistics_type` varchar(30) NOT NULL COMMENT 'DT:日汇总;SDT:供应商日汇总;PDT:产品日汇总;SPDT:销售人员日汇总;CDT:客户日汇总;MT:月汇总;SMT:供应商月汇总;PMT:产品月汇总;SPMT:销售人员月汇总;CMT:客户日汇总',
  `note` varchar(60) DEFAULT NULL COMMENT '备注',
  `status` int(1) DEFAULT 0 COMMENT '状态，0是删除，1是正常',
  `created_by` VARCHAR(20) NOT NULL COMMENT '创建人',
  `updated_by` VARCHAR(20) NOT NULL COMMENT '更新人',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB　DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `profit_amount`;
/*9.利润表，也可以用作排名表　*/
CREATE TABLE `profit_amount` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `supplier_id` bigint(20) NOT NULL COMMENT '供货商主键',
  `product_id` bigint(20) NOT NULL COMMENT '产品主键',
  `account_id` bigint(20) NOT NULL COMMENT '账号主键',
  `amount` decimal(19,2) DEFAULT 0.00 COMMENT '售出金额',
  `sale_date` varchar(30) NOT NULL COMMENT '销售日期',
  `statistics_type` varchar(30) NOT NULL COMMENT 'DT:日汇总;SDT:供应商日汇总;PDT:产品日汇总;SPDT:销售人员日汇总;MT:月汇总;SMT:供应商月汇总;PMT:产品月汇总;SPMT:销售人员月汇总',
  `note` varchar(60) DEFAULT NULL COMMENT '备注',
  `status` int(1) DEFAULT 0 COMMENT '状态，0是删除，1是正常',
  `created_by` VARCHAR(20) NOT NULL COMMENT '创建人',
  `updated_by` VARCHAR(20) NOT NULL COMMENT '更新人',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB　DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `account`;
/*10.账户表　*/
CREATE TABLE `account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `department` int(11) NOT NULL COMMENT '部门主键',
  `account` varchar(20) NOT NULL COMMENT '账号',
  `name` varchar(100) DEFAULT NULL COMMENT '账户名称',
  `phone` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(255) DEFAULT NULL COMMENT '电子邮箱地址',
  `status` int(1) NOT NULL COMMENT '状态，0是删除，1是正常',
  `created_by` VARCHAR(20) NOT NULL COMMENT '创建人',
  `updated_by` VARCHAR(20) NOT NULL COMMENT '更新人',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sys_department`;
/*11.部门表　*/
CREATE TABLE `sys_department` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `description` varchar(255) DEFAULT NULL COMMENT '部门描述',
  `parent_id` int(11) DEFAULT NULL COMMENT '父级部门id',
  `status` int(1) NOT NULL COMMENT '账号状态',
  `created_by` VARCHAR(20) NOT NULL COMMENT '创建人',
  `updated_by` VARCHAR(20) NOT NULL COMMENT '更新人',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门表';
