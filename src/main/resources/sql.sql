drop TABLE if EXISTS t_user;
CREATE TABLE `t_user` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `username` varchar(10) NOT NULL,
  `password` varchar(6) NOT NULL,
  `createtime` date NOT NULL,
  `updatetime` date NOT NULL,
  `openid` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

drop TABLE if EXISTS t_message;
CREATE TABLE `t_message` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `image` varchar(10) DEFAULT NULL,
  `message` varchar(200) DEFAULT NULL,
  `createtime` date NOT NULL,
  `updatetime` date NOT NULL,
  `openid` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop TABLE if EXISTS t_access_token;
CREATE TABLE `t_access_token` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `openid` varchar(20) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `accessToken` varchar(50) NOT NULL,
  `createtime` date NOT NULL,
  `updatetime` date NOT NULL,
  `userHeadImage` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop TABLE if EXISTS t_role;
CREATE TABLE `t_role` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(45) DEFAULT NULL,
  `rolecode` varchar(10) DEFAULT NULL,
  `roledesc` varchar(45) DEFAULT NULL,
  `createtime` date NOT NULL,
  `updatetime` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop TABLE if EXISTS t_permission;
CREATE TABLE `t_permission` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `permname` varchar(45) DEFAULT NULL,
  `permcode` varchar(10) DEFAULT NULL,
  `createtime` date NOT NULL,
  `updatetime` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;