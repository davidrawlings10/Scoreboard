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
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `game` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `sport_id` int NOT NULL,
  `season_id` int DEFAULT NULL,
  `home_team_id` int NOT NULL,
  `away_team_id` int NOT NULL,
  `home_score` int DEFAULT NULL,
  `away_score` int DEFAULT NULL,
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
  `away_win` int DEFAULT NULL,
  `away_loss` int DEFAULT NULL,
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

CREATE TABLE `sport` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- insert data
INSERT INTO league (name) VALUES ('Aves');
INSERT INTO team (league_id, name) VALUES (1, 'Hummingbird');
INSERT INTO team (league_id, name) VALUES (1, 'Sparrow');
INSERT INTO team (league_id, name) VALUES (1, 'Goldfinch');
INSERT INTO team (league_id, name) VALUES (1, 'Chickadee');
INSERT INTO team (league_id, name) VALUES (1, 'Cardinal');
INSERT INTO team (league_id, name) VALUES (1, 'Oriole');
INSERT INTO team (league_id, name) VALUES (1, 'Robin');
INSERT INTO team (league_id, name) VALUES (1, 'Woodpecker');
INSERT INTO team (league_id, name) VALUES (1, 'Canary');
INSERT INTO team (league_id, name) VALUES (1, 'Bluebird');
INSERT INTO team (league_id, name) VALUES (1, 'Warbler');
INSERT INTO team (league_id, name) VALUES (1, 'Blue Jay');
INSERT INTO team (league_id, name) VALUES (1, 'Purple Martin');
INSERT INTO team (league_id, name) VALUES (1, 'Mourning Dove');
INSERT INTO team (league_id, name) VALUES (1, 'Killdeer');
INSERT INTO team (league_id, name) VALUES (1, 'Mockingbird');
INSERT INTO team (league_id, name) VALUES (1, 'Mallard');
INSERT INTO team (league_id, name) VALUES (1, 'Sea Gull');
INSERT INTO team (league_id, name) VALUES (1, 'Penguin');
INSERT INTO team (league_id, name) VALUES (1, 'Swan');
INSERT INTO team (league_id, name) VALUES (1, 'Flamingo');
INSERT INTO team (league_id, name) VALUES (1, 'Ostrich');
INSERT INTO team (league_id, name) VALUES (1, 'Parrot');
INSERT INTO team (league_id, name) VALUES (1, 'Heron');
INSERT INTO team (league_id, name) VALUES (1, 'Dove');
INSERT INTO team (league_id, name) VALUES (1, 'Eagle');
INSERT INTO team (league_id, name) VALUES (1, 'Owl');
INSERT INTO team (league_id, name) VALUES (1, 'Hawk');
INSERT INTO team (league_id, name) VALUES (1, 'Turkey');
INSERT INTO team (league_id, name) VALUES (1, 'Peacock');
INSERT INTO team (league_id, name) VALUES (1, 'Pelican');
INSERT INTO team (league_id, name) VALUES (1, 'Roadrunner');

INSERT INTO league (name) VALUES ('NHL');
INSERT INTO team (league_id, location, name) VALUES (2, 'Boston', 'Bruins');
INSERT INTO team (league_id, location, name) VALUES (2, 'Tampa Bay', 'Lightning');
INSERT INTO team (league_id, location, name) VALUES (2, 'Toronto', 'Maple Leafs');
INSERT INTO team (league_id, location, name) VALUES (2, 'Florida', 'Panthers');
INSERT INTO team (league_id, location, name) VALUES (2, 'Montreal', 'Canadiens');
INSERT INTO team (league_id, location, name) VALUES (2, 'Buffalo', 'Sabres');
INSERT INTO team (league_id, location, name) VALUES (2, 'Ottawa', 'Senators');
INSERT INTO team (league_id, location, name) VALUES (2, 'Detroit', 'Red Wings');
INSERT INTO team (league_id, location, name) VALUES (2, 'Washington', 'Capitals');
INSERT INTO team (league_id, location, name) VALUES (2, 'Philadelphia', 'Flyers');
INSERT INTO team (league_id, location, name) VALUES (2, 'Pittsburgh', 'Penguins');
INSERT INTO team (league_id, location, name) VALUES (2, 'Carolina', 'Hurricanes');
INSERT INTO team (league_id, location, name) VALUES (2, 'Columbus', 'Blue Jackets');
INSERT INTO team (league_id, location, name) VALUES (2, 'New York', 'Islanders');
INSERT INTO team (league_id, location, name) VALUES (2, 'New York', 'Rangers');
INSERT INTO team (league_id, location, name) VALUES (2, 'New Jersey', '');
INSERT INTO team (league_id, location, name) VALUES (2, 'St. Louis', 'Blues');
INSERT INTO team (league_id, location, name) VALUES (2, 'Colorado', 'Avalanche');
INSERT INTO team (league_id, location, name) VALUES (2, 'Dallas', 'Stars');
INSERT INTO team (league_id, location, name) VALUES (2, 'Winnipeg', 'Jets');
INSERT INTO team (league_id, location, name) VALUES (2, 'Nashville', 'Predators');
INSERT INTO team (league_id, location, name) VALUES (2, 'Minnesota', 'Wild');
INSERT INTO team (league_id, location, name) VALUES (2, 'Chicago', 'Blackhawks');
INSERT INTO team (league_id, location, name) VALUES (2, 'Vegas', 'Golden Knights');
INSERT INTO team (league_id, location, name) VALUES (2, 'Edmonton', 'Oilers');
INSERT INTO team (league_id, location, name) VALUES (2, 'Calgary', 'Flames');
INSERT INTO team (league_id, location, name) VALUES (2, 'Vancouver', 'Canucks');
INSERT INTO team (league_id, location, name) VALUES (2, 'Arizona', 'Coyotes');
INSERT INTO team (league_id, location, name) VALUES (2, 'Anaheim', 'Ducks');
INSERT INTO team (league_id, location, name) VALUES (2, 'Los Angeles', 'Kings');
INSERT INTO team (league_id, location, name) VALUES (2, 'San Jose', 'Sharks');

INSERT INTO league (name) VALUES ('Test');
INSERT INTO team (league_id, location, name) VALUES (3, 'LocationA', 'TeamA');
INSERT INTO team (league_id, location, name) VALUES (3, 'LocationB', 'TeamB');
INSERT INTO team (league_id, location, name) VALUES (3, 'LocationC', 'TeamC');
INSERT INTO team (league_id, location, name) VALUES (3, 'LocationD', 'TeamD');

INSERT INTO sport (name) VALUES ('Hockey');
INSERT INTO sport (name) VALUES ('Basketball');