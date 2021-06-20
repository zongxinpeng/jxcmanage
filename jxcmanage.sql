# 用户表
# drop table user_info

CREATE TABLE `user_info` (
 `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
 `user_code` VARCHAR(100) NOT NULL COMMENT '账号',
 `user_password` VARCHAR(200) NOT NULL COMMENT '密码',
 `user_name` VARCHAR(200) NOT NULL COMMENT '姓名',
 `user_desc` VARCHAR(500) COMMENT '备注',
 `created_by` VARCHAR(100) NOT NULL COMMENT '创建人',
 `created_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
 `updated_by` VARCHAR(100) NOT NULL COMMENT '更新人',
 `updated_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
 PRIMARY KEY (`id`)
)

