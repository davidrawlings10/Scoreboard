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
  `title` varchar(45) DEFAULT NULL,
  `winner_team_id` int DEFAULT NULL,
  `summary` varchar(256) DEFAULT NULL,
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