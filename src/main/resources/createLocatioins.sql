CREATE DATABASE IF NOT EXISTS `auspost_coding_test`;
USE `auspost_coding_test`;

CREATE TABLE IF NOT EXISTS `locations` ( `location_id` int(5) NOT NULL AUTO_INCREMENT, `suburb` varchar(200) NOT NULL, `postcode` varchar(4) NOT NULL, PRIMARY KEY (`location_id`) ) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;



INSERT INTO `locations` (`location_id`, `suburb`, `postcode`) VALUES
	(1, 'DELAHEY', '3037'),
	(2, 'HILSIDE', '3037'),
	(3, 'BURNSIDE HEIGHTS', '3023'); 
	
CREATE TABLE `users` (
  `username` varchar(40) NOT NULL COMMENT 'userName',
  `password` varchar(45) NOT NULL,
  `enabled` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `USER_NAME_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `user_roles` (
  `username` varchar(40) NOT NULL,
  `role` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
	
