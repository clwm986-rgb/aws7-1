drop database if exists health;

create database health;

use health;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
	`id`	int	primary key auto_increment,
	`name`	varchar(20)	not null,
	`reg_date`	datetime	not NULL default current_timestamp,
	`phone`	varchar(13)	not NULL unique
);

DROP TABLE IF EXISTS `locker`;

CREATE TABLE `locker` (
	`id`	int	primary key auto_increment,
	`user_id`	int	NULL
);

DROP TABLE IF EXISTS `trainer`;

CREATE TABLE `trainer` (
	`id`	int	primary key auto_increment,
	`name`	varchar(20)	not NULL,
	`major`	varchar(10)	NULL,
	`history`	int	not NULL default 0,
	`leader_id`	int	NULL
);

DROP TABLE IF EXISTS `program`;

CREATE TABLE `program` (
	`id`	int	primary key auto_increment,
	`title`	varchar(20)	not NULL,
	`personnel`	int	not NULL,
	`fee`	int	not NULL default 0,
	`trainer_id`	int	NOT NULL
);

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
	`id`	int	primary key auto_increment,
	`app_date`	datetime	not NULL default current_timestamp,
	`state`	varchar(10)	not NULL default '결재완료',
	`user_id`	int	NOT NULL,
	`program_id`	int	NOT NULL
);

DROP TABLE IF EXISTS `attendance`;

CREATE TABLE `attendance` (
	`id`	int	primary key auto_increment,
	`date`	date	NULL,
	`state`	char(1)	NULL,
	`user_id`	int	NOT NULL,
	`program_id`	int	NOT NULL
);

ALTER TABLE `locker` ADD CONSTRAINT `FK_user_TO_locker_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `user` (
	`id`
);

ALTER TABLE `trainer` ADD CONSTRAINT `FK_trainer_TO_trainer_1` FOREIGN KEY (
	`leader_id`
)
REFERENCES `trainer` (
	`id`
);

ALTER TABLE `program` ADD CONSTRAINT `FK_trainer_TO_program_1` FOREIGN KEY (
	`trainer_id`
)
REFERENCES `trainer` (
	`id`
);

ALTER TABLE `course` ADD CONSTRAINT `FK_user_TO_course_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `user` (
	`id`
);

ALTER TABLE `course` ADD CONSTRAINT `FK_program_TO_course_1` FOREIGN KEY (
	`program_id`
)
REFERENCES `program` (
	`id`
);

ALTER TABLE `attendance` ADD CONSTRAINT `FK_user_TO_attendance_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `user` (
	`id`
);

ALTER TABLE `attendance` ADD CONSTRAINT `FK_program_TO_attendance_1` FOREIGN KEY (
	`program_id`
)
REFERENCES `program` (
	`id`
);

