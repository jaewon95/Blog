CREATE TABLE `account` (
  `act_num` int NOT NULL AUTO_INCREMENT,
  `act_id` varchar(45) NOT NULL,
  `act_pw` varchar(45) NOT NULL,
  `act_role` int DEFAULT '1',
  PRIMARY KEY (`act_num`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


CREATE TABLE `board` (
  `bid` int NOT NULL AUTO_INCREMENT,
  `btitle` varchar(200) DEFAULT NULL,
  `bwriter` varchar(45) DEFAULT NULL,
  `bcontent` varchar(3000) DEFAULT NULL,
  `bdate` datetime DEFAULT CURRENT_TIMESTAMP,
  `bdelete_yn` tinyint DEFAULT '0',
  `filename` varchar(150) DEFAULT NULL,
  `filepath` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`bid`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci



CREATE TABLE `boardreply` (
  `bid` int DEFAULT NULL,
  `br_id` int NOT NULL AUTO_INCREMENT,
  `br_writer` varchar(45) DEFAULT NULL,
  `br_content` varchar(300) DEFAULT NULL,
  `br_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `br_delete_yn` tinyint DEFAULT '0',
  PRIMARY KEY (`br_id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci