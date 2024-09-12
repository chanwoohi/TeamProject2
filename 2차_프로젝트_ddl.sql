DROP DATABASE IF EXISTS quizgame;

CREATE DATABASE IF NOT EXISTS quizgame;

USE quizgame;

DROP TABLE IF EXISTS `community`;

CREATE TABLE `community` (
	`co_num`	INT PRIMARY KEY AUTO_INCREMENT	NOT NULL,
	`co_name`	VARCHAR(50) UNIQUE	NOT NULL
);

DROP TABLE IF EXISTS `post`;

CREATE TABLE `post` (
	`po_num`		INT PRIMARY KEY AUTO_INCREMENT	NOT NULL,
	`po_title`		VARCHAR(50)						NOT NULL,
	`po_content`	LONGTEXT						NOT NULL,
	`po_date`		DATETIME 						NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`po_view`		INT								NOT NULL DEFAULT 1,
	`po_co_num`		INT								NOT NULL,
	`po_me_id`		VARCHAR(13)						NOT NULL,
	`po_secret`		VARCHAR(200)					NOT NULL
);

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
	`cm_num`		INT PRIMARY KEY AUTO_INCREMENT	NOT NULL,
	`cm_content`	VARCHAR(200) 					NULL,
	`cm_ori_num`	INT								NULL,
	`cm_date`		DATETIME 						NULL,
	`cm_state`		INT								NULL,
	`cm_po_num`		INT								NOT NULL,
	`cm_me_id`		VARCHAR(13)						NOT NULL
);

DROP TABLE IF EXISTS `recommend`;

CREATE TABLE `recommend` (
	`re_num`	INT PRIMARY KEY AUTO_INCREMENT	NOT NULL,
	`re_state`	INT								NOT NULL default 0,
	`re_me_id`	VARCHAR(13)						NOT NULL,
	`re_po_num`	INT								NOT NULL 
);

DROP TABLE IF EXISTS `member_state`;

CREATE TABLE `member_state` (
	`ms_name`	VARCHAR(10) PRIMARY KEY	NOT NULL
);


DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
	`me_id`			VARCHAR(13) PRIMARY KEY	NOT NULL,
	`me_pw`			VARCHAR(255) 			NOT NULL,
	`me_email`		VARCHAR(50) UNIQUE 		NOT NULL,
	`me_authority`	VARCHAR(5) 				NULL DEFAULT 'USER',
	`me_point`		INT 					NOT NULL DEFAULT 1000,
	`me_cookie`		VARCHAR(255) 			NULL,
	`me_limit`		DATETIME 				NULL,
	`me_ms_name`	VARCHAR(10)				NOT NULL,
	`me_ranking`	INT						NULL,
	`me_address`	VARCHAR(255)			NULL,
    `me_addressDetail` VARCHAR(255)			NULL,
    `me_phoneNumberMid` VARCHAR(4)			NULL,
    `me_phoneNumberEnd` VARCHAR(4)			NULL
);

DROP TABLE IF EXISTS `file`;

CREATE TABLE `file` (
	`fi_num`		INT PRIMARY KEY AUTO_INCREMENT	NOT NULL,
	`fi_ori_name`	VARCHAR(255)					NULL,
	`fi_name`		VARCHAR(255)					NULL,
	`fi_po_num`		INT								NOT NULL
);
DROP TABLE IF EXISTS `quiz_type`;

CREATE TABLE `quiz_type` (
	`qt_num`	INT PRIMARY KEY AUTO_INCREMENT 	NOT NULL,
    `qt_name`	VARCHAR(10) UNIQUE				NOT NULL
);

DROP TABLE IF EXISTS `quiz`;

CREATE TABLE `quiz` (
	`qu_num`			INT PRIMARY KEY AUTO_INCREMENT	NOT NULL,
	`qu_content`		LONGTEXT						NOT NULL,
	`qu_answer1`		VARCHAR(400)					NOT NULL,
	`qu_answer2`		VARCHAR(400)					NOT NULL,
	`qu_answer3`		VARCHAR(400)					NOT NULL,
	`qu_answer4`		VARCHAR(400)					NOT NULL,
	`qu_correct_answer`	INT								NOT NULL,
    `qu_qt_num`			INT								NOT NULL
);

DROP TABLE IF EXISTS `quiz_attempt`;

CREATE TABLE `quiz_attempt` (
	`qa_num`		INT PRIMARY KEY AUTO_INCREMENT	NOT NULL,
	`qa_me_id`		VARCHAR(13)						NOT NULL,
	`qa_qt_num`		INT								NOT NULL,
	`qa_count`		INT DEFAULT 0					NOT NULL,
	`qa_point`		INT DEFAULT 0					NOT NULL
);