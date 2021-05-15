-- 创建数据库
create database if not exists filar
DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

-- 项目表 item
create table item(
	`id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '项目id',
    primary key (id),
    `title` varchar(45) not null comment '标题',
    `content` varchar(200) not null comment '内容',
    `last_generate` datetime comment '最后生成日期'
) comment '项目表';

--