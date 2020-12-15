use tptproject;

INSERT INTO `tptproject`.`question`
(`Content`)
VALUES
('When TransPerfect is founded?');

INSERT INTO `tptproject`.`questionanswer`
(`AnswerID`,
`Correct`,
`Content`)
VALUES
(1,
1,
'1992');

INSERT INTO `tptproject`.`questionanswer`
(`AnswerID`,
`Correct`,
`Content`)
VALUES
(1,
0,
'1990');

INSERT INTO `tptproject`.`user`
(`Name`,
`Surname`,
`Username`,
`Password`,
`TypeOfUser`,
`email`)
VALUES
('admin',
'admin',
'admin',
'admin',
'admin',
'admin@gmail.com');

