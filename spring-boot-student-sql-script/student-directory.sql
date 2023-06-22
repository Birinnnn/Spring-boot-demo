CREATE DATABASE  IF NOT EXISTS `student_directory`;
USE `student_directory`;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Data for table `student`
--

INSERT INTO `student` VALUES 
	(1,'Leslie','Andrews','leslie@hotmail.com'),
	(2,'Emma','Baumgarten','emma@hotmail.com'),
	(3,'Avani','Gupta','avani@hotmail.com'),
	(4,'Yuri','Petrov','yuri@hotmail.com'),
	(5,'Juan','Vega','juan@hotmail.com');

