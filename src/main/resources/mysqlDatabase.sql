/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 8.2.0 : Database - studentmanagementsystem
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`studentmanagementsystem` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `studentmanagementsystem`;

/*Table structure for table `admin_info` */

CREATE TABLE `admin_info` (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '管理员ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '123456' COMMENT '密码',
  `sex` bit(1) DEFAULT NULL COMMENT '性别1男0女',
  `age` int DEFAULT NULL COMMENT '年龄',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '手机号',
  `level` int DEFAULT '1' COMMENT '权限等级，默认1',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='管理员信息表';

/*Data for the table `admin_info` */

insert  into `admin_info`(`id`,`name`,`password`,`sex`,`age`,`phone`,`level`) values ('1','张三','123456','',18,'12345678910',1);

/*Table structure for table `choose_course` */

CREATE TABLE `choose_course` (
  `course_id` bigint NOT NULL,
  `student_id` bigint NOT NULL,
  `grade` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '分数',
  PRIMARY KEY (`course_id`,`student_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学生选课表';

/*Data for the table `choose_course` */

/*Table structure for table `college_info` */

CREATE TABLE `college_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '学院名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学院信息表';

/*Data for the table `college_info` */

/*Table structure for table `course_info` */

CREATE TABLE `course_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '课程名称',
  `credit` int DEFAULT NULL COMMENT '学分',
  `teacher_id` int NOT NULL COMMENT '负责教师id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课程信息表';

/*Data for the table `course_info` */

/*Table structure for table `major_info` */

CREATE TABLE `major_info` (
  `id` bigint NOT NULL COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '专业名称',
  `college_id` bigint DEFAULT NULL COMMENT '学院id',
  PRIMARY KEY (`id` DESC) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='专业信息表';

/*Data for the table `major_info` */

/*Table structure for table `student_info` */

CREATE TABLE `student_info` (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'ID学号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '123456' COMMENT '密码',
  `sex` bit(1) DEFAULT NULL COMMENT '性别1男0女',
  `age` int DEFAULT NULL COMMENT '年龄',
  `level` int DEFAULT '3' COMMENT '权限等级，默认3',
  `major_id` int DEFAULT NULL COMMENT '学生专业id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='学生信息表';

/*Data for the table `student_info` */

insert  into `student_info`(`id`,`name`,`password`,`sex`,`age`,`level`,`major_id`) values ('100001','小明','123456','',19,3,1),('111112','李四','123456','',18,3,1);

/*Table structure for table `teacher_info` */

CREATE TABLE `teacher_info` (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'ID职工号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `sex` bit(1) DEFAULT NULL COMMENT '性别1男0女',
  `age` int DEFAULT NULL COMMENT '年龄',
  `level` int DEFAULT '2' COMMENT '权限等级，默认2',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '职称',
  `college_id` int DEFAULT NULL COMMENT '学院id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='教师信息表';

/*Data for the table `teacher_info` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
