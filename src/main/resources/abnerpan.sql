/*
SQLyog 浼佷笟鐗?- MySQL GUI v8.14
MySQL - 5.7.17-log : Database - abnerpan
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`abnerpan` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `abnerpan`;

/*Table structure for table `article` */

DROP TABLE IF EXISTS `article`;

CREATE TABLE `article` (
  `article_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `selection_id` bigint(3) DEFAULT NULL COMMENT '菜单内标签',
  `user_id` bigint(20) DEFAULT NULL,
  `title` varchar(30) DEFAULT NULL COMMENT '文章标题',
  `content` longtext COMMENT '文章内容',
  `creat_time` datetime DEFAULT NULL COMMENT '发贴时间',
  `click_count` bigint(20) DEFAULT '0',
  `replay_count` bigint(20) DEFAULT '0',
  `last_replaytime` datetime DEFAULT NULL COMMENT '上次回复时间',
  `astaus` bigint(3) DEFAULT NULL COMMENT '默认0,置顶1,hot2',
  `alike` bigint(20) DEFAULT '0',
  `article_abstract` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`article_id`),
  KEY `FK_Reference_6` (`selection_id`),
  KEY `FK_Reference_9` (`user_id`),
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`selection_id`) REFERENCES `selection` (`selection_id`),
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Table structure for table `collect` */

DROP TABLE IF EXISTS `collect`;

CREATE TABLE `collect` (
  `article_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  KEY `FK_Reference_7` (`article_id`),
  KEY `FK_Reference_8` (`user_id`),
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`),
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `comment` */

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `comment_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `article_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  `content` varchar(300) DEFAULT NULL,
  `clike` bigint(20) DEFAULT '0',
  `isread` int(2) DEFAULT '0' COMMENT '鏈0;宸茶1',
  `parent_comment_id` bigint(20) DEFAULT '0' COMMENT '涓€绾ц瘎璁轰负0',
  `floor` bigint(20) DEFAULT NULL COMMENT '璇勮涓嬭瘎璁轰负-1',
  `haschild` int(2) DEFAULT '0' COMMENT '涓嬭竟鏃犺瘎璁?;涓嬭竟鏈夎瘎璁?',
  PRIMARY KEY (`comment_id`),
  KEY `FK_Reference_10` (`user_id`),
  KEY `FK_Reference_5` (`article_id`),
  CONSTRAINT `FK_Reference_10` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8;

/*Table structure for table `pic` */

DROP TABLE IF EXISTS `pic`;

CREATE TABLE `pic` (
  `pic_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `plike` bigint(20) DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  `pic_describe` varchar(500) DEFAULT NULL,
  `path` varchar(100) DEFAULT NULL,
  `parent` bigint(20) DEFAULT NULL COMMENT '-1鍒欎负灏侀潰',
  PRIMARY KEY (`pic_id`),
  KEY `FK_Reference_11` (`user_id`),
  CONSTRAINT `FK_Reference_11` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=213 DEFAULT CHARSET=utf8;

/*Table structure for table `secret` */

DROP TABLE IF EXISTS `secret`;

CREATE TABLE `secret` (
  `secret_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(300) DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  `ip` varchar(20) DEFAULT NULL,
  `slike` bigint(20) DEFAULT '0',
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`secret_id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

/*Table structure for table `selection` */

DROP TABLE IF EXISTS `selection`;

CREATE TABLE `selection` (
  `selection_id` bigint(3) NOT NULL AUTO_INCREMENT COMMENT '菜单内标签',
  `sname` varchar(10) DEFAULT NULL,
  `selection_click_count` bigint(20) DEFAULT NULL,
  `selection_article_count` bigint(20) DEFAULT NULL,
  `selection_parent_id` bigint(3) DEFAULT NULL COMMENT '所属菜单',
  PRIMARY KEY (`selection_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `pwd` varchar(30) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `qq` varchar(20) DEFAULT NULL,
  `gender` bigint(2) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `headimg` varchar(30) DEFAULT NULL COMMENT '头像图片位置',
  `statement` varchar(30) DEFAULT NULL COMMENT '个人描述',
  `regist_time` datetime DEFAULT NULL,
  `login_time` datetime DEFAULT NULL COMMENT '上次登录时间',
  `ustaus` varchar(100) DEFAULT NULL COMMENT '1为激活',
  `user_ip` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
