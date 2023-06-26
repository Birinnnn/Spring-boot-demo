DROP SCHEMA IF EXISTS `db-many-to-many`;
CREATE SCHEMA `db-many-to-many`;
USE `db-many-to-many`;

SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `instructor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE `student_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `gpa` double(4,2) DEFAULT NULL,
  `hobby` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `student` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `student_detail_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_DETAIL_idx` (`student_detail_id`),
  CONSTRAINT `FK_DETAIL` FOREIGN KEY (`student_detail_id`) 
  REFERENCES `student_detail` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `lecture` (
	`id` int NOT NULL AUTO_INCREMENT,
    `title` varchar(128) DEFAULT NULL,
    `instructor_id` int DEFAULT NULL,
    
    
	PRIMARY KEY (`id`),
    UNIQUE KEY `TITLE_UNIQUE` (`title`),
    
    KEY `FK_INSTRUCTOR_idx` (`instructor_id`),
  
  CONSTRAINT `FK_INSTRUCTOR` 
  FOREIGN KEY (`instructor_id`) 
  REFERENCES `instructor` (`id`) 

ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `lecture_student` (
  `lecture_id` int NOT NULL,
  `student_id` int NOT NULL,
  
  PRIMARY KEY (`lecture_id`,`student_id`),
  KEY `FK_STUDENT_idx` (`student_id`),
  
  CONSTRAINT `FK_LECTURE_05` FOREIGN KEY (`lecture_id`)
  REFERENCES `lecture` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_STUDENT` FOREIGN KEY (`student_id`) 
  REFERENCES `student` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO `student` VALUES 
	(1,'Leslie','Andrews','leslie@hotmail.com', NULL),
	(2,'Emma','Baumgarten','emma@hotmail.com', NULL),
	(3,'Avani','Gupta','avani@hotmail.com', NULL),
	(4,'Yuri','Petrov','yuri@hotmail.com', NULL),
	(5,'Juan','Vega','juan@hotmail.com', NULL);
