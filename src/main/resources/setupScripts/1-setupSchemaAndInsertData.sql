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
  `division` varchar(45) DEFAULT NULL,
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
  `sport` varchar(45) NOT NULL,
  `num_teams` int DEFAULT NULL,
  `title` varchar(45) DEFAULT NULL,
  `winner_team_id` int DEFAULT NULL,
  `summary` varchar(10000) DEFAULT NULL,
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
  `ranking` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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

ALTER TABLE game_event
ADD INDEX gameId (game_id ASC) VISIBLE;
ALTER TABLE game_event ALTER INDEX id_UNIQUE INVISIBLE;

-- insert teams
INSERT INTO team (league, location, name, division) VALUES
('AVES', null, 'Hummingbird', 'Small A'),
('AVES', null, 'Sparrow', 'Small A'),
('AVES', null, 'Goldfinch', 'Small A'),
('AVES', null, 'Chickadee', 'Small A'),
('AVES', null, 'Cardinal', 'Small B'),
('AVES', null, 'Oriole', 'Small B'),
('AVES', null, 'Robin', 'Small B'),
('AVES', null, 'Woodpecker', 'Small B'),
('AVES', null, 'Canary', 'Small C'),
('AVES', null, 'Bluebird', 'Small C'),
('AVES', null, 'Warbler', 'Small C'),
('AVES', null, 'Blue Jay', 'Small C'),
('AVES', null, 'Purple Martin', 'Small D'),
('AVES', null, 'Mourning Dove', 'Small D'),
('AVES', null, 'Killdeer', 'Small D'),
('AVES', null, 'Mockingbird', 'Small D'),
('AVES', null, 'Mallard', 'Big A'),
('AVES', null, 'Sea Gull', 'Big A'),
('AVES', null, 'Penguin', 'Big A'),
('AVES', null, 'Swan', 'Big A'),
('AVES', null, 'Flamingo', 'Big B'),
('AVES', null, 'Ostrich', 'Big B'),
('AVES', null, 'Parrot', 'Big B'),
('AVES', null, 'Heron', 'Big B'),
('AVES', null, 'Dove', 'Big C'),
('AVES', null, 'Eagle', 'Big C'),
('AVES', null, 'Owl', 'Big C'),
('AVES', null, 'Hawk', 'Big C'),
('AVES', null, 'Turkey', 'Big D'),
('AVES', null, 'Peacock', 'Big D'),
('AVES', null, 'Pelican', 'Big D'),
('AVES', null, 'Roadrunner', 'Big D'),
('NHL', 'Carolina', 'Hurricanes', 'East Metropolitan'),
('NHL', 'Columbus', 'Blue Jackets', 'East Metropolitan'),
('NHL', 'New Jersey', 'Scouts', 'East Metropolitan'),
('NHL', 'New York', 'Islanders', 'East Metropolitan'),
('NHL', 'New York', 'Rangers', 'East Metropolitan'),
('NHL', 'Philadelphia', 'Flyers', 'East Metropolitan'),
('NHL', 'Pittsburgh', 'Penguins', 'East Metropolitan'),
('NHL', 'Washington', 'Capitals', 'East Metropolitan'),
('NHL', 'Boston', 'Bruins', 'East Atlantic'),
('NHL', 'Buffalo', 'Sabres', 'East Atlantic'),
('NHL', 'Detroit', 'Red Wings', 'East Atlantic'),
('NHL', 'Florida', 'Panthers', 'East Atlantic'),
('NHL', 'Montreal', 'Canadiens', 'East Atlantic'),
('NHL', 'Ottawa', 'Senators', 'East Atlantic'),
('NHL', 'Tampa Bay', 'Lightning', 'East Atlantic'),
('NHL', 'Toronto', 'Maple Leafs', 'East Atlantic'),
('NHL', 'Arizona', 'Coyotes', 'West Central'),
('NHL', 'Chicago', 'Blackhawks', 'West Central'),
('NHL', 'Colorado', 'Avalanche', 'West Central'),
('NHL', 'Dallas', 'Stars', 'West Central'),
('NHL', 'Minnesota', 'Wild', 'West Central'),
('NHL', 'Nashville', 'Predators', 'West Central'),
('NHL', 'St. Louis', 'Blues', 'West Central'),
('NHL', 'Winnipeg', 'Jets', 'West Central'),
('NHL', 'Anaheim', 'Ducks', 'West Pacific'),
('NHL', 'Calgary', 'Flames', 'West Pacific'),
('NHL', 'Edmonton', 'Oilers', 'West Pacific'),
('NHL', 'Los Angeles', 'Kings', 'West Pacific'),
('NHL', 'San Jose', 'Sharks', 'West Pacific'),
('NHL', 'Seattle', 'Kraken', 'West Pacific'),
('NHL', 'Vancouver', 'Canucks', 'West Pacific'),
('NHL', 'Vegas', 'Golden Knights', 'West Pacific'),
('TEST', 'LocationA', 'TeamA', 'Test 1'),
('TEST', 'LocationB', 'TeamB', 'Test 1'),
('TEST', 'LocationC', 'TeamC', 'Test 1'),
('TEST', 'LocationD', 'TeamD', 'Test 1'),
('TEST', 'LocationE', 'TeamE', 'Test 1'),
('TEST', 'LocationF', 'TeamF', 'Test 1'),
('TEST', 'LocationG', 'TeamG', 'Test 1'),
('TEST', 'LocationH', 'TeamH', 'Test 1'),
('TEST', 'LocationI', 'TeamI', 'Test 1'),
('TEST', 'LocationJ', 'TeamJ', 'Test 1'),
('TEST', 'LocationK', 'TeamK', 'Test 1'),
('TEST', 'LocationL', 'TeamL', 'Test 1'),
('TEST', 'LocationM', 'TeamM', 'Test 1'),
('TEST', 'LocationN', 'TeamN', 'Test 1'),
('TEST', 'LocationO', 'TeamO', 'Test 1'),
('TEST', 'LocationP', 'TeamP', 'Test 1'),
('TEST', 'LocationQ', 'TeamQ', 'Test 1'),
('TEST', 'LocationR', 'TeamR', 'Test 1'),
('TEST', 'LocationS', 'TeamS', 'Test 1'),
('TEST', 'LocationT', 'TeamT', 'Test 1'),
('TEST', 'LocationU', 'TeamU', 'Test 1'),
('TEST', 'LocationV', 'TeamV', 'Test 1'),
('TEST', 'LocationW', 'TeamW', 'Test 1'),
('TEST', 'LocationX', 'TeamX', 'Test 1'),
('TEST', 'LocationY', 'TeamY', 'Test 1'),
('TEST', 'LocationZ', 'TeamZ', 'Test 1'),
('TEST', 'LocationAA', 'TeamAA', 'Test 1'),
('TEST', 'LocationAB', 'TeamAB', 'Test 1'),
('TEST', 'LocationAC', 'TeamAC', 'Test 1'),
('TEST', 'LocationAD', 'TeamAD', 'Test 1'),
('TEST', 'LocationAE', 'TeamAE', 'Test 1'),
('TEST', 'LocationAF', 'TeamAF', 'Test 1'),
('NFL','Buffalo','Bills','AFC East'),
('NFL','Miami','Dolphins','AFC East'),
('NFL','New York','Jets','AFC East'),
('NFL','New England','Patriots','AFC East'),
('NFL','Baltimore','Ravens','AFC North'),
('NFL','Cincinnati','Bengals','AFC North'),
('NFL','Cleveland','Browns','AFC North'),
('NFL','Pittsburgh','Steelers','AFC North'),
('NFL','Houston','Texans','AFC South'),
('NFL','Indianapolis','Colts','AFC South'),
('NFL','Jacksonville','Jaguars','AFC South'),
('NFL','Tennessee','Titans','AFC South'),
('NFL','Denver','Broncos','AFC West'),
('NFL','Kansas City','Chiefs','AFC West'),
('NFL','Los Angeles','Chargers','AFC West'),
('NFL','Las Vegas','Raiders','AFC West'),
('NFL','Dallas','Cowboys','AFC East'),
('NFL','New York','Giants','AFC East'),
('NFL','Philadelphia','Eagles','AFC East'),
('NFL','Washington','Commanders','AFC East'),
('NFL','Chicago','Bears','NFC North'),
('NFL','Detroit','Lions','NFC North'),
('NFL','Green Bay','Packers','NFC North'),
('NFL','Minnesota','Vikings','NFC North'),
('NFL','Atlanta','Falcons','NFC South'),
('NFL','Carolina','Panthers','NFC South'),
('NFL','New Orleans','Saints','NFC South'),
('NFL','Tampa Bay','Buccaneers','NFC South'),
('NFL','Arizona','Cardinals','NFC West'),
('NFL','Los Angeles','Rams','NFC West'),
('NFL','San Francisco','49ers','NFC West'),
('NFL','Seattle','Seahawks','NFC West'),
('NCAA','Baylor','Bears','Big 12'),
('NCAA','Iowa State','Cyclones','Big 12'),
('NCAA','Kansas','Jayhawks','Big 12'),
('NCAA','Kansas State','Wildcats','Big 12'),
('NCAA','Oklahoma','Sooners','Big 12'),
('NCAA','Oklahoma State','Cowboys','Big 12'),
('NCAA','TCU','Horned Frogs','Big 12'),
('NCAA','Texas','Longhorns','Big 12'),
('NCAA','Texas Tech','Red Raiders','Big 12'),
('NCAA','West Virginia','Mountaineers','Big 12'),
('NCAA','Alabama','Crimson Tide','SEC'),
('NCAA','Arkansas','Razorbacks','SEC'),
('NCAA','Auburn','Tigers','SEC'),
('NCAA','Florida','Gators','SEC'),
('NCAA','Georgia','Bulldogs','SEC'),
('NCAA','Kentucky','Wildcats','SEC'),
('NCAA','LSU','Tigers','SEC'),
('NCAA','Mississippi State','Bulldogs','SEC'),
('NCAA','Missouri','Tigers','SEC'),
('NCAA','Ole Miss','Rebels','SEC'),
('NCAA','South Carolina','Gamecocks','SEC'),
('NCAA','Tennessee','Volunteers','SEC'),
('NCAA','Texas A&M','Aggies','SEC'),
('NCAA','Vanderbilt','Commodores','SEC');