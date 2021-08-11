INSERT INTO `locations` (`location_id`, `suburb`, `postcode`) VALUES (1, 'DELAHEY', '3037'),(2, 'HILSIDE', '3037'),(3, 'BURNSIDE HEIGHTS', '3023'); 
INSERT INTO `user_roles` (`username`,`role`) values ('user1','ROLE_USER'),('user2','ROLE_USER'),('user3','ROLE_USER');
INSERT INTO `users` (`username`,`password`,`enabled`) VALUES ('user1','{noop}password1','true'),('user2','{noop}password2','true'),('user3','password3','false');
