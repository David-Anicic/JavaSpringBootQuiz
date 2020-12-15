CREATE DATABASE `tptproject`;

CREATE TABLE `question` (
  `QuestionID` int NOT NULL AUTO_INCREMENT,
  `Content` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`QuestionID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `questionanswer` (
  `AnswerID` int NOT NULL AUTO_INCREMENT,
  `QuestionID` int NOT NULL,
  `Correct` int DEFAULT NULL,
  `Content` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`AnswerID`,`QuestionID`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `questionsusers` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `UserID` int NOT NULL,
  `QuestionID` int NOT NULL,
  `Answer` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user` (
  `UserID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) DEFAULT NULL,
  `Surname` varchar(45) DEFAULT NULL,
  `Username` varchar(45) DEFAULT NULL,
  `Password` varchar(45) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  `TypeOfUser` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
