CREATE TABLE `locations`( `location_id` int(5) NOT NULL AUTO_INCREMENT, `suburb` varchar(200) NOT NULL, `postcode` varchar(4) NOT NULL, PRIMARY KEY (`location_id`) );
CREATE TABLE `users` (`username` varchar(40) NOT NULL,`password` varchar(45) NOT NULL,`enabled` varchar(45),PRIMARY KEY (`username`),UNIQUE KEY `USER_NAME_UNIQUE` (`username`));
CREATE TABLE `user_roles` (`username` varchar(40) NOT NULL,`role` varchar(45) DEFAULT NULL,PRIMARY KEY (`username`));
	