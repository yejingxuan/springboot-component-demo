/*
SQLyog Ultimate v12.3.3 (64 bit)
MySQL - 8.0.12 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `user_info` (
	`uid` varchar (96),
	`username` varchar (96),
	`password` varchar (96)
); 
insert into `user_info` (`uid`, `username`, `password`) values('001','张三','123');
insert into `user_info` (`uid`, `username`, `password`) values('002','李四','456');
