# 물리 설계
# ERD CLOUD : https://www.erdcloud.com/d/5Z4yoFSsnmzGAnNPB
DROP DATABASE IF EXISTS SHOPPINGMALL;
CREATE DATABASE SHOPPINGMALL;
USE SHOPPINGMALL;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
	`id`	varchar(13)	NOT NULL,
	`pw`	varchar(255)	NOT NULL,
	`email`	varchar(50)	NOT NULL UNIQUE,
	`phone`	varchar(13)	NOT NULL UNIQUE,
	`state`	varchar(5)	NOT NULL DEFAULT '이용',
	`authority`	varchar(5)	NOT NULL DEFAULT 'USER',
	`login_count`	int	NOT NULL DEFAULT 0
);

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
	`code`	char(3)	NOT NULL,
	`name`	varchar(10)	NOT NULL
);

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
	`code`	char(6)	NOT NULL,
	`name`	varchar(100)	NOT NULL,
	`content`	text	NOT NULL,
	`price`	int	NOT NULL DEFAULT 0,
	`thumbnail`	varchar(255)	NULL,
	`amount`	int	NOT NULL DEFAULT 0,
	`ca_code`	char(3)	NOT NULL
);

DROP TABLE IF EXISTS `buy`;

CREATE TABLE `buy` (
	`id`	varchar(13)	NOT NULL,
	`code`	char(6)	NOT NULL,
	`amount`	int	NOT NULL,
	`date`	datetime	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`state`	varchar(10)	NOT NULL DEFAULT '결제완료'
);

DROP TABLE IF EXISTS `cart`;

CREATE TABLE `cart` (
	`num`	int	PRIMARY KEY AUTO_INCREMENT,
	`amount`	int	NOT NULL,
	`id`	varchar(13)	NOT NULL,
	`code`	char(6)	NOT NULL
);

ALTER TABLE `user` ADD CONSTRAINT `PK_USER` PRIMARY KEY (
	`id`
);

ALTER TABLE `category` ADD CONSTRAINT `PK_CATEGORY` PRIMARY KEY (
	`code`
);

ALTER TABLE `product` ADD CONSTRAINT `PK_PRODUCT` PRIMARY KEY (
	`code`
);

ALTER TABLE `buy` ADD CONSTRAINT `PK_BUY` PRIMARY KEY (
	`id`,
	`code`
);

ALTER TABLE `product` ADD CONSTRAINT `FK_category_TO_product_1` FOREIGN KEY (
	`ca_code`
)
REFERENCES `category` (
	`code`
);

ALTER TABLE `buy` ADD CONSTRAINT `FK_user_TO_buy_1` FOREIGN KEY (
	`id`
)
REFERENCES `user` (
	`id`
);

ALTER TABLE `buy` ADD CONSTRAINT `FK_product_TO_buy_1` FOREIGN KEY (
	`code`
)
REFERENCES `product` (
	`code`
);

ALTER TABLE `cart` ADD CONSTRAINT `FK_user_TO_cart_1` FOREIGN KEY (
	`id`
)
REFERENCES `user` (
	`id`
);

ALTER TABLE `cart` ADD CONSTRAINT `FK_product_TO_cart_1` FOREIGN KEY (
	`code`
)
REFERENCES `product` (
	`code`
);

