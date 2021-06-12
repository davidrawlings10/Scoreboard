-- remove existing schema
DROP SCHEMA hockey_dev;

-- create schema
CREATE SCHEMA hockey_dev;
USE hockey_dev;

-- create tables
CREATE TABLE `team` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `league_id` int unsigned DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `active` boolean NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `game` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `sport` varchar(45) NOT NULL,
  `season_id` int DEFAULT NULL,
  `home_team_id` int NOT NULL,
  `away_team_id` int NOT NULL,
  `home_score` int DEFAULT NULL,
  `away_score` int DEFAULT NULL,
  `status` varchar(45) NOT NULL,
  `ending_period` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `season` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `league_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `standing` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `season_id` int NOT NULL,
  `team_id` int NOT NULL,
  `win` int DEFAULT NULL,
  `loss` int DEFAULT NULL,
  `tie` int DEFAULT NULL,
  `otloss` int DEFAULT NULL,
  `point` int DEFAULT NULL,
  `gp` int DEFAULT NULL,
  `gf` int DEFAULT NULL,
  `ga` int DEFAULT NULL,
  `home_win` int DEFAULT NULL,
  `home_loss` int DEFAULT NULL,
  `home_tie` int DEFAULT NULL,
  `home_otloss` int DEFAULT NULL,
  `home_point` int DEFAULT NULL,
  `home_gp` int DEFAULT NULL,
  `away_win` int DEFAULT NULL,
  `away_loss` int DEFAULT NULL,
  `away_tie` int DEFAULT NULL,
  `away_otloss` int DEFAULT NULL,
  `away_point` int DEFAULT NULL,
  `away_gp` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `league` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `parent_league_id` int unsigned DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*CREATE TABLE `sport` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;*/

/*CREATE TABLE `game_event` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `game_id` int unsigned DEFAULT NULL,
  `team_id` int unsigned DEFAULT NULL,
  `event_type` int unsigned DEFAULT NULL,
  `period` int DEFAULT NULL,
  `minutes` int DEFAULT NULL,
  `second` int DEFAULT NULL
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;*/

-- insert data
INSERT INTO league (name) VALUES ('Aves'),('NHL'),('Test');
INSERT INTO team (league_id, location, name) VALUES
(1, null, 'Hummingbird'),
(1, null, 'Sparrow'),
(1, null, 'Goldfinch'),
(1, null, 'Chickadee'),
(1, null, 'Cardinal'),
(1, null, 'Oriole'),
(1, null, 'Robin'),
(1, null, 'Woodpecker'),
(1, null, 'Canary'),
(1, null, 'Bluebird'),
(1, null, 'Warbler'),
(1, null, 'Blue Jay'),
(1, null, 'Purple Martin'),
(1, null, 'Mourning Dove'),
(1, null, 'Killdeer'),
(1, null, 'Mockingbird'),
(1, null, 'Mallard'),
(1, null, 'Sea Gull'),
(1, null, 'Penguin'),
(1, null, 'Swan'),
(1, null, 'Flamingo'),
(1, null, 'Ostrich'),
(1, null, 'Parrot'),
(1, null, 'Heron'),
(1, null, 'Dove'),
(1, null, 'Eagle'),
(1, null, 'Owl'),
(1, null, 'Hawk'),
(1, null, 'Turkey'),
(1, null, 'Peacock'),
(1, null, 'Pelican'),
(1, null, 'Roadrunner'),
(2, 'Boston', 'Bruins'),
(2, 'Tampa Bay', 'Lightning'),
(2, 'Toronto', 'Maple Leafs'),
(2, 'Florida', 'Panthers'),
(2, 'Montreal', 'Canadiens'),
(2, 'Buffalo', 'Sabres'),
(2, 'Ottawa', 'Senators'),
(2, 'Detroit', 'Red Wings'),
(2, 'Washington', 'Capitals'),
(2, 'Philadelphia', 'Flyers'),
(2, 'Pittsburgh', 'Penguins'),
(2, 'Carolina', 'Hurricanes'),
(2, 'Columbus', 'Blue Jackets'),
(2, 'New York', 'Islanders'),
(2, 'New York', 'Rangers'),
(2, 'New Jersey', 'Angels'),
(2, 'St. Louis', 'Blues'),
(2, 'Colorado', 'Avalanche'),
(2, 'Dallas', 'Stars'),
(2, 'Winnipeg', 'Jets'),
(2, 'Nashville', 'Predators'),
(2, 'Minnesota', 'Wild'),
(2, 'Chicago', 'Blackhawks'),
(2, 'Vegas', 'Golden Knights'),
(2, 'Edmonton', 'Oilers'),
(2, 'Calgary', 'Flames'),
(2, 'Vancouver', 'Canucks'),
(2, 'Arizona', 'Coyotes'),
(2, 'Anaheim', 'Ducks'),
(2, 'Los Angeles', 'Kings'),
(2, 'San Jose', 'Sharks'),
(3, 'LocationA', 'TeamA'),
(3, 'LocationB', 'TeamB'),
(3, 'LocationC', 'TeamC'),
(3, 'LocationD', 'TeamD'),
(3, 'LocationE', 'TeamE'),
(3, 'LocationF', 'TeamF'),
(3, 'LocationG', 'TeamG'),
(3, 'LocationH', 'TeamH'),
(3, 'LocationI', 'TeamI'),
(3, 'LocationJ', 'TeamJ'),
(3, 'LocationK', 'TeamK'),
(3, 'LocationL', 'TeamL'),
(3, 'LocationM', 'TeamM'),
(3, 'LocationN', 'TeamN'),
(3, 'LocationO', 'TeamO'),
(3, 'LocationP', 'TeamP'),
(3, 'LocationQ', 'TeamQ'),
(3, 'LocationR', 'TeamR'),
(3, 'LocationS', 'TeamS'),
(3, 'LocationT', 'TeamT'),
(3, 'LocationU', 'TeamU'),
(3, 'LocationV', 'TeamV'),
(3, 'LocationW', 'TeamW'),
(3, 'LocationX', 'TeamX'),
(3, 'LocationY', 'TeamY'),
(3, 'LocationZ', 'TeamZ'),
(3, 'LocationAA', 'TeamAA'),
(3, 'LocationAB', 'TeamAB'),
(3, 'LocationAC', 'TeamAC'),
(3, 'LocationAD', 'TeamAD'),
(3, 'LocationAE', 'TeamAE'),
(3, 'LocationAF', 'TeamAF');

/*INSERT INTO sport (name) VALUES ('Hockey');
INSERT INTO sport (name) VALUES ('Basketball');*/

/*update team set active = 0 where (id < 41 or id > 44) and league_id = 2;
update team set active = 1 where (id >= 41 and id <= 44) and league_id = 2;*/

/*INSERT INTO `season` VALUES (1,'2021-06-03 20:11:03','2021-06-03 20:11:03',1);
INSERT INTO `game` VALUES (6,'2021-06-03 20:11:04','2021-06-07 01:22:25','HOCKEY',1,8,7,2,1,3,0),(7,'2021-06-03 20:11:04','2021-06-07 02:40:30','HOCKEY',1,5,6,0,2,3,0),(8,'2021-06-03 20:11:04','2021-06-07 04:28:53','HOCKEY',1,6,7,0,6,3,0),(9,'2021-06-03 20:11:04','2021-06-07 05:40:55','HOCKEY',1,8,5,5,0,3,0),(10,'2021-06-03 20:11:04','2021-06-07 18:37:11','HOCKEY',1,8,6,0,3,3,0),(11,'2021-06-03 20:11:04','2021-06-07 19:43:43','HOCKEY',1,7,5,3,1,3,0),(12,'2021-06-03 20:11:04','2021-06-11 00:29:53','HOCKEY',1,5,6,2,3,5,0),(13,'2021-06-03 20:11:04','2021-06-07 23:34:43','HOCKEY',1,7,8,2,3,3,0),(14,'2021-06-03 20:11:04','2021-06-08 00:39:11','HOCKEY',1,5,7,3,4,3,0),(15,'2021-06-03 20:11:04','2021-06-08 18:29:59','HOCKEY',1,8,6,3,1,3,0),(16,'2021-06-03 20:11:04','2021-06-08 19:31:11','HOCKEY',1,6,7,2,4,3,0),(17,'2021-06-03 20:11:04','2021-06-08 20:32:10','HOCKEY',1,5,8,1,3,3,0),(18,'2021-06-03 20:11:04','2021-06-08 21:33:21','HOCKEY',1,8,6,3,1,3,0),(19,'2021-06-03 20:11:04','2021-06-08 22:50:02','HOCKEY',1,5,7,6,0,3,0),(20,'2021-06-03 20:11:04','2021-06-09 19:22:30','HOCKEY',1,7,8,8,2,3,0),(21,'2021-06-03 20:11:04','2021-06-09 20:23:36','HOCKEY',1,5,6,1,4,3,0),(22,'2021-06-03 20:11:04','2021-06-09 21:27:22','HOCKEY',1,6,7,5,1,3,0),(23,'2021-06-03 20:11:04','2021-06-09 22:29:20','HOCKEY',1,8,5,5,0,3,0),(24,'2021-06-03 20:11:04','2021-06-09 23:44:00','HOCKEY',1,7,8,3,4,4,0),(25,'2021-06-03 20:11:04','2021-06-10 18:34:03','HOCKEY',1,6,5,3,2,3,0),(26,'2021-06-03 20:11:04','2021-06-10 19:35:25','HOCKEY',1,7,8,3,2,3,0),(27,'2021-06-03 20:11:04','2021-06-10 20:37:16','HOCKEY',1,6,5,2,3,3,0),(28,'2021-06-03 20:11:04','2021-06-10 21:38:28','HOCKEY',1,7,5,4,5,3,0),(29,'2021-06-03 20:11:04','2021-06-10 22:51:58','HOCKEY',1,6,8,3,0,3,0);
INSERT INTO `standing` VALUES (2,'2021-06-03 20:11:03','2021-06-10 21:38:28',1,5,3,8,0,1,7,12,24,38,1,4,0,1,3,6,2,4,0,0,4,6),(3,'2021-06-03 20:11:03','2021-06-10 22:51:59',1,6,7,5,0,0,14,12,29,25,3,3,0,0,6,6,4,2,0,0,8,6),(4,'2021-06-03 20:11:04','2021-06-10 21:38:28',1,7,6,5,0,1,13,12,39,35,3,2,0,1,7,6,3,3,0,0,6,6),(5,'2021-06-03 20:11:04','2021-06-10 22:51:59',1,8,8,4,0,0,16,12,32,26,5,1,0,0,10,6,3,3,0,0,6,6);*/
