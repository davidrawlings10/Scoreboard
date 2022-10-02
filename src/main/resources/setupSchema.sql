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
  `league` varchar(45),
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
  `league` varchar(45),
  `num_teams` int DEFAULT NULL,
  `title` varchar(45) DEFAULT NULL,
  `winner_team_id` int DEFAULT NULL,
  `summary` varchar(1000) DEFAULT NULL,
  `schedule_type` varchar(45) DEFAULT NULL,
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

/*CREATE TABLE `sport` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;*/

CREATE TABLE `game_event` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `game_id` int unsigned DEFAULT NULL,
  `team_id` int unsigned DEFAULT NULL,
  `event_type` varchar(45) NOT NULL,
  `home_score` int DEFAULT NULL,
  `away_score` int DEFAULT NULL,
  `period` int DEFAULT NULL,
  `minutes` int DEFAULT NULL,
  `seconds` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `clock` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `game_id` int unsigned DEFAULT NULL,
  `period` int DEFAULT NULL,
  `minutes` int DEFAULT NULL,
  `seconds` int DEFAULT NULL,
  `intermission` boolean NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

