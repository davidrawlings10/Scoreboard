-- season 1
INSERT INTO `season` (id, created, updated, league_id, num_teams, title, winner_team_id, summary, schedule_type) VALUES
(1,'2021-06-03 20:11:03','2021-06-03 20:11:03',1,4,'Aves Season 1',3,'Goldfinch wins the first scoreboard season on a goal diff tiebreaker', 'HOME_ROTATION');

INSERT INTO `standing` (id, created, updated, season_id, team_id, win, loss, tie, otloss, point, gp, gf, ga, home_win, home_loss, home_tie, home_otloss, home_point, home_gp, away_win, away_loss, away_tie, away_otloss, away_point, away_gp) VALUES
(1,'2020-04-03 00:00:00.000','2020-04-03 00:00:00.000',1,1,3,3,0,0,6,6,18,19,null,null,null,null,null,null,null,null,null,null,null,null),
(2,'2020-04-03 00:00:00.000','2020-04-03 00:00:00.000',1,2,1,5,0,0,2,6,13,23,null,null,null,null,null,null,null,null,null,null,null,null),
(3,'2020-04-03 00:00:00.000','2020-04-03 00:00:00.000',1,3,4,2,0,0,8,6,18,9,null,null,null,null,null,null,null,null,null,null,null,null),
(4,'2020-04-03 00:00:00.000','2020-04-03 00:00:00.000',1,4,4,2,0,0,8,6,16,14,null,null,null,null,null,null,null,null,null,null,null,null);

INSERT INTO `game` (id, created, updated, sport, season_id, home_team_id, away_team_id, home_score, away_score, status, ending_period) VALUES
(1,'2020-04-03 00:00:00.000','2020-04-03 00:00:00.000','HOCKEY',1,1,2,5,3,'FINAL',3),
(2,'2020-04-03 00:00:00.000','2020-04-03 00:00:00.000','HOCKEY',1,1,3,3,6,'FINAL',3),
(3,'2020-04-03 00:00:00.000','2020-04-03 00:00:00.000','HOCKEY',1,1,4,5,3,'FINAL',3),
(4,'2020-04-03 00:00:00.000','2020-04-03 00:00:00.000','HOCKEY',1,2,1,3,2,'FINAL',3),
(5,'2020-04-03 00:00:00.000','2020-04-03 00:00:00.000','HOCKEY',1,2,3,1,5,'FINAL',3),
(6,'2020-04-03 00:00:00.000','2020-04-03 00:00:00.000','HOCKEY',1,2,4,1,3,'FINAL',3),
(7,'2020-04-03 00:00:00.000','2020-04-03 00:00:00.000','HOCKEY',1,3,1,1,2,'FINAL',3),
(8,'2020-04-03 00:00:00.000','2020-04-03 00:00:00.000','HOCKEY',1,3,2,3,1,'FINAL',3),
(9,'2020-04-03 00:00:00.000','2020-04-03 00:00:00.000','HOCKEY',1,3,4,3,1,'FINAL',3),
(10,'2020-04-03 00:00:00.000','2020-04-03 00:00:00.000','HOCKEY',1,4,1,3,1,'FINAL',3),
(11,'2020-04-03 00:00:00.000','2020-04-03 00:00:00.000','HOCKEY',1,4,2,5,4,'FINAL',3),
(12,'2020-04-03 00:00:00.000','2020-04-03 00:00:00.000','HOCKEY',1,4,3,1,0,'FINAL',3);

-- season 2
INSERT INTO `season` (id, created, updated, league_id, num_teams, title, winner_team_id, summary, schedule_type) VALUES
(2,'2020-04-10 00:00:00','2020-04-10 00:00:00',1,8,'Aves Season 2',6,'Oriole wins season 2.', 'HOME_ROTATION');

-- season 3
INSERT INTO `season` (id, created, updated, league_id, num_teams, title, winner_team_id, summary, schedule_type) VALUES
(3,'2020-04-20 00:00:00','2020-04-20 00:00:00',1,16,'Aves Season 3',8,'Woodpecker wins season 3.', 'HOME_ROTATION');

-- season 4
INSERT INTO `season` (id, created, updated, league_id, num_teams, title, winner_team_id, summary, schedule_type) VALUES
(4,'2021-01-25 18:00:52','2021-02-17 16:43:04',1,8,'Aves Season 4',1,'Hummingbird and Goldfinch break away from the pack both winning an improbable number of away games. Hummingbird prevails and wins the season losing only 2 games to Goldfinch and 1 to Sparrow.', 'HOME_ROTATION');

-- season 5
INSERT INTO `season` (id, created, updated, league_id, num_teams, title, winner_team_id, summary, schedule_type) VALUES
(5,'2021-03-01 12:16:07','2021-03-31 17:33:00',1,32,'Aves Season 5',19,'Penguin runs away with Season 5 with an improbable home stand.', 'HOME_ROTATION');

-- season 6
INSERT INTO `season` (id, created, updated, league_id, num_teams, title, winner_team_id, summary, schedule_type) VALUES
(6,'2021-04-12 14:01:22','2021-04-15 17:09:18',1,4,'Aves Season 6',3,'Goldfinch wins season 6. This was the first season scheduled using the Rounds algorithm.', 'ROUNDS');

-- season 7
INSERT INTO `season` (id, created, updated, league_id, num_teams, title, winner_team_id, summary, schedule_type) VALUES
(7,'2021-06-03 20:11:03','2021-06-03 20:11:03',1,4,'Aves Season 7',8,'Woodpecker wins season 7. This was the first season played using the react ui. Before this the back end was used by itself.', 'ROUNDS');

INSERT INTO `standing` (id, created, updated, season_id, team_id, win, loss, tie, otloss, point, gp, gf, ga, home_win, home_loss, home_tie, home_otloss, home_point, home_gp, away_win, away_loss, away_tie, away_otloss, away_point, away_gp) VALUES
(1002,'2021-06-03 20:11:03','2021-06-10 21:38:28',7,5,3,8,0,1,7,12,24,38,1,4,0,1,3,6,2,4,0,0,4,6),
(1003,'2021-06-03 20:11:03','2021-06-10 22:51:59',7,6,7,5,0,0,14,12,29,25,3,3,0,0,6,6,4,2,0,0,8,6),
(1004,'2021-06-03 20:11:04','2021-06-10 21:38:28',7,7,6,5,0,1,13,12,39,35,3,2,0,1,7,6,3,3,0,0,6,6),
(1005,'2021-06-03 20:11:04','2021-06-10 22:51:59',7,8,8,4,0,0,16,12,32,26,5,1,0,0,10,6,3,3,0,0,6,6);

INSERT INTO `game` (id, created, updated, sport, season_id, home_team_id, away_team_id, home_score, away_score, status, ending_period) VALUES
(1006,'2021-06-03 20:11:04','2021-06-07 01:22:25','HOCKEY',7,8,7,2,1,'FINAL',3),
(1007,'2021-06-03 20:11:04','2021-06-07 02:40:30','HOCKEY',7,5,6,0,2,'FINAL',3),
(1008,'2021-06-03 20:11:04','2021-06-07 04:28:53','HOCKEY',7,6,7,0,6,'FINAL',3),
(1009,'2021-06-03 20:11:04','2021-06-07 05:40:55','HOCKEY',7,8,5,5,0,'FINAL',3),
(1010,'2021-06-03 20:11:04','2021-06-07 18:37:11','HOCKEY',7,8,6,0,3,'FINAL',3),
(1011,'2021-06-03 20:11:04','2021-06-07 19:43:43','HOCKEY',7,7,5,3,1,'FINAL',3),
(1012,'2021-06-03 20:11:04','2021-06-11 00:29:53','HOCKEY',7,5,6,2,3,'FINAL',5),
(1013,'2021-06-03 20:11:04','2021-06-07 23:34:43','HOCKEY',7,7,8,2,3,'FINAL',3),
(1014,'2021-06-03 20:11:04','2021-06-08 00:39:11','HOCKEY',7,5,7,3,4,'FINAL',3),
(1015,'2021-06-03 20:11:04','2021-06-08 18:29:59','HOCKEY',7,8,6,3,1,'FINAL',3),
(1016,'2021-06-03 20:11:04','2021-06-08 19:31:11','HOCKEY',7,6,7,2,4,'FINAL',3),
(1017,'2021-06-03 20:11:04','2021-06-08 20:32:10','HOCKEY',7,5,8,1,3,'FINAL',3),
(1018,'2021-06-03 20:11:04','2021-06-08 21:33:21','HOCKEY',7,8,6,3,1,'FINAL',3),
(1019,'2021-06-03 20:11:04','2021-06-08 22:50:02','HOCKEY',7,5,7,6,0,'FINAL',3),
(1020,'2021-06-03 20:11:04','2021-06-09 19:22:30','HOCKEY',7,7,8,8,2,'FINAL',3),
(1021,'2021-06-03 20:11:04','2021-06-09 20:23:36','HOCKEY',7,5,6,1,4,'FINAL',3),
(1022,'2021-06-03 20:11:04','2021-06-09 21:27:22','HOCKEY',7,6,7,5,1,'FINAL',3),
(1023,'2021-06-03 20:11:04','2021-06-09 22:29:20','HOCKEY',7,8,5,5,0,'FINAL',3),
(1024,'2021-06-03 20:11:04','2021-06-09 23:44:00','HOCKEY',7,7,8,3,4,'FINAL',4),
(1025,'2021-06-03 20:11:04','2021-06-10 18:34:03','HOCKEY',7,6,5,3,2,'FINAL',3),
(1026,'2021-06-03 20:11:04','2021-06-10 19:35:25','HOCKEY',7,7,8,3,2,'FINAL',3),
(1027,'2021-06-03 20:11:04','2021-06-10 20:37:16','HOCKEY',7,6,5,2,3,'FINAL',3),
(1028,'2021-06-03 20:11:04','2021-06-10 21:38:28','HOCKEY',7,7,5,4,5,'FINAL',3),
(1029,'2021-06-03 20:11:04','2021-06-10 22:51:58','HOCKEY',7,6,8,3,0,'FINAL',3);

-- season 8
INSERT INTO `season` VALUES (8,'2021-06-21 04:45:07','2021-06-21 04:47:06',1,4,'Aves Season 8',10,'Bluebird wins 3 straight away games and takes season 8. This was the first season where two games were played simultaneously','ROUNDS');
INSERT INTO `standing` VALUES (1006,'2021-06-21 04:45:08','2021-06-26 00:31:59',8,9,5,5,0,2,12,12,34,29,4,2,0,0,8,6,1,3,0,2,4,6),(1007,'2021-06-21 04:45:08','2021-06-26 00:31:59',8,10,8,4,0,0,16,12,29,21,5,1,0,0,10,6,3,3,0,0,6,6),(1008,'2021-06-21 04:45:08','2021-06-26 00:31:58',8,11,6,6,0,0,12,12,32,35,3,3,0,0,6,6,3,3,0,0,6,6),(1009,'2021-06-21 04:45:08','2021-06-26 00:31:58',8,12,5,7,0,0,10,12,27,37,5,1,0,0,10,6,0,6,0,0,0,6);
INSERT INTO `game` VALUES (1030,'2021-06-21 04:45:08','2021-06-21 21:01:32','HOCKEY',8,11,10,3,0,'FINAL',3),(1031,'2021-06-21 04:45:08','2021-06-21 21:46:47','HOCKEY',8,12,9,5,4,'FINAL',5),(1032,'2021-06-21 04:45:08','2021-06-22 00:01:13','HOCKEY',8,10,12,3,1,'FINAL',3),(1033,'2021-06-21 04:45:08','2021-06-22 00:01:13','HOCKEY',8,9,11,2,3,'FINAL',3),(1034,'2021-06-21 04:45:08','2021-06-22 02:16:16','HOCKEY',8,12,11,4,2,'FINAL',3),(1035,'2021-06-21 04:45:08','2021-06-22 02:33:14','HOCKEY',8,10,9,3,2,'FINAL',4),(1036,'2021-06-21 04:45:08','2021-06-22 20:27:18','HOCKEY',8,10,12,3,1,'FINAL',3),(1037,'2021-06-21 04:45:08','2021-06-22 20:48:29','HOCKEY',8,9,11,4,2,'FINAL',3),(1038,'2021-06-21 04:45:08','2021-06-22 23:37:50','HOCKEY',8,12,9,3,1,'FINAL',3),(1039,'2021-06-21 04:45:08','2021-06-22 23:58:02','HOCKEY',8,10,11,1,3,'FINAL',3),(1040,'2021-06-21 04:45:08','2021-06-23 20:30:58','HOCKEY',8,11,9,1,6,'FINAL',3),(1041,'2021-06-21 04:45:08','2021-06-23 20:30:58','HOCKEY',8,10,12,2,0,'FINAL',3),(1042,'2021-06-21 04:45:08','2021-06-23 22:48:44','HOCKEY',8,10,11,4,0,'FINAL',3),(1043,'2021-06-21 04:45:08','2021-06-23 23:40:29','HOCKEY',8,12,9,1,0,'FINAL',3),(1044,'2021-06-21 04:45:08','2021-06-24 18:24:40','HOCKEY',8,12,9,2,1,'FINAL',3),(1045,'2021-06-21 04:45:08','2021-06-24 18:56:42','HOCKEY',8,11,10,4,3,'FINAL',3),(1046,'2021-06-21 04:45:08','2021-06-24 20:59:16','HOCKEY',8,9,10,3,5,'FINAL',3),(1047,'2021-06-21 04:45:08','2021-06-24 20:59:17','HOCKEY',8,12,11,5,6,'FINAL',3),(1048,'2021-06-21 04:45:08','2021-06-25 00:33:36','HOCKEY',8,11,10,0,2,'FINAL',3),(1049,'2021-06-21 04:45:08','2021-06-25 00:33:36','HOCKEY',8,9,12,5,2,'FINAL',3),(1050,'2021-06-21 04:45:08','2021-06-25 21:13:16','HOCKEY',8,11,10,1,2,'FINAL',3),(1051,'2021-06-21 04:45:08','2021-06-25 22:25:25','HOCKEY',8,9,12,3,1,'FINAL',3),(1052,'2021-06-21 04:45:08','2021-06-26 00:31:58','HOCKEY',8,11,12,7,2,'FINAL',3),(1053,'2021-06-21 04:45:08','2021-06-26 00:31:59','HOCKEY',8,9,10,3,1,'FINAL',3);