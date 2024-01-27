select max(id) from season;

select * from season;





-- MAS -- OLD -- HOCKEY
select avg(home_score), avg(away_score), avg(home_score + away_score) / 2, count(*), (select count(*) from game where season_id = g.season_id and status = 'FINAL') from game g where season_id < 44 and status = 'FINAL' and ending_period = 3;
select avg(home_score), avg(away_score), avg(home_score + away_score) / 2, count(*), (select count(*) from game where season_id = g.season_id and status = 'FINAL') from game g where season_id = 46 and status = 'FINAL' and ending_period = 3;
select avg(home_score), avg(away_score), avg(home_score + away_score) / 2, count(*), (select count(*) from game where season_id = g.season_id and status = 'FINAL') from game g where season_id = 47 and status = 'FINAL' and ending_period = 3;
select avg(home_score), avg(away_score), avg(home_score + away_score) / 2, count(*), (select count(*) from game where season_id = g.season_id and status = 'FINAL') from game g where season_id = 48 and status = 'FINAL' and ending_period = 3;
select avg(home_score), avg(away_score), avg(home_score + away_score) / 2, count(*), (select count(*) from game where season_id = g.season_id and status = 'FINAL') from game g where season_id = 51 and status = 'FINAL' and ending_period = 3;
select avg(home_score), avg(away_score), avg(home_score + away_score) / 2, count(*), (select count(*) from game where season_id = g.season_id and status = 'FINAL') from game g where season_id = 52 and status = 'FINAL' and ending_period = 3;
select avg(home_score), avg(away_score), avg(home_score + away_score) / 2, count(*), (select count(*) from game where season_id = g.season_id and status = 'FINAL') from game g where season_id = 53 and status = 'FINAL' and ending_period = 3;
select avg(home_score), avg(away_score), avg(home_score + away_score) / 2, count(*), (select count(*) from game where season_id = g.season_id and status = 'FINAL') from game g where season_id = 54 and status = 'FINAL' and ending_period = 3;

select avg(home_score), avg(away_score), avg(home_score + away_score) / 2, count(*), (select count(*) from game where season_id = g.season_id and status = 'FINAL') from game g where season_id in (46, 47, 48, 51, 52, 53, 54) and status = 'FINAL' and ending_period = 3;
select count(*) from game where season_id >= 46 and season_id <= 54 and home_score > away_score and status = 'FINAL';
select count(*) from game where season_id >= 46 and season_id <= 54 and status = 'FINAL';
select 4131 / 7316; -- = .56%

-- POS -- OLD -- HOCKEY
select avg(home_score), avg(away_score), avg(home_score + away_score) / 2, count(*), (select count(*) from game where season_id = g.season_id and status = 'FINAL') from game g where season_id = 44 and status = 'FINAL' and ending_period = 3;
select avg(home_score), avg(away_score), avg(home_score + away_score) / 2, count(*), (select count(*) from game where season_id = g.season_id and status = 'FINAL') from game g where season_id = 45 and status = 'FINAL' and ending_period = 3;
select avg(home_score), avg(away_score), avg(home_score + away_score) / 2, count(*), (select count(*) from game where season_id = g.season_id and status = 'FINAL') from game g where season_id = 55 and status = 'FINAL' and ending_period = 3;
select avg(home_score), avg(away_score), avg(home_score + away_score) / 2, count(*), (select count(*) from game where season_id = g.season_id and status = 'FINAL') from game g where season_id = 56 and status = 'FINAL' and ending_period = 3;
select avg(home_score), avg(away_score), avg(home_score + away_score) / 2, count(*), (select count(*) from game where season_id = g.season_id and status = 'FINAL') from game g where season_id = 57 and status = 'FINAL' and ending_period = 3;
select avg(home_score), avg(away_score), avg(home_score + away_score) / 2, count(*), (select count(*) from game where season_id = g.season_id and status = 'FINAL') from game g where season_id = 58 and status = 'FINAL' and ending_period = 3;
select avg(home_score), avg(away_score), avg(home_score + away_score) / 2, count(*), (select count(*) from game where season_id = g.season_id and status = 'FINAL') from game g where season_id = 59 and status = 'FINAL' and ending_period = 3;
select avg(home_score), avg(away_score), avg(home_score + away_score) / 2, count(*), (select count(*) from game where season_id = g.season_id and status = 'FINAL') from game g where season_id = 60 and status = 'FINAL' and ending_period = 3;

select avg(home_score), avg(away_score), avg(home_score + away_score) / 2, count(*), (select count(*) from game where season_id = g.season_id and status = 'FINAL') from game g where season_id in (44, 45, 55, 56, 57, 58, 59, 60) and status = 'FINAL' and ending_period = 3;

-- POS -- OLD -- BASKETBALL
select avg(home_score), avg(away_score), avg(home_score + away_score) / 2, count(*), (select count(*) from game where season_id = g.season_id and status = 'FINAL') from game g where season_id = 61 and status = 'FINAL' and ending_period = 2;

select count(*) / 853 / 2 * 1 from game_event ge join game g on ge.game_id = g.id where g.season_id = 61 and event_type = 'BASKETBALL_FREE_THROW_1_MADE';
select count(*) / 853 / 2 * 2 from game_event ge join game g on ge.game_id = g.id where g.season_id = 61 and event_type = 'BASKETBALL_FREE_THROW_2_MADE';
select count(*) / 853 / 2 * 2 from game_event ge join game g on ge.game_id = g.id where g.season_id = 61 and event_type = 'BASKETBALL_TWO_POINTER';
select count(*) / 853 / 2 * 3 from game_event ge join game g on ge.game_id = g.id where g.season_id = 61 and event_type = 'BASKETBALL_THREE_POINTER';

-- POS -- NEW POS MATH -- BASKETBALL
select avg(home_score), avg(away_score), avg(home_score + away_score) / 2, count(*), (select count(*) from game where season_id = g.season_id and status = 'FINAL') from game g where season_id = 62 and status = 'FINAL' and ending_period = 2 and g.id not in (29484, 29485);
select count(*) from game where season_id = 62 and home_score > away_score and status = 'FINAL';
select count(*) from game where season_id = 62 and status = 'FINAL';
select 825 / 992; -- = .8317%

-- POS -- HOME/AWAY ADJUSTMENT -- BASKETBALL
select avg(home_score), avg(away_score), avg(home_score + away_score) / 2, count(*), (select count(*) from game where season_id = g.season_id and status = 'FINAL') from game g where season_id = 63 and status = 'FINAL' and ending_period = 2;
select count(*) from game where season_id = 63 and home_score > away_score and status = 'FINAL';
select count(*) from game where season_id = 63 and status = 'FINAL';
select 584 / 992; -- = .6066

select avg(home_score), avg(away_score), avg(home_score + away_score) / 2, count(*), (select count(*) from game where season_id = g.season_id and status = 'FINAL') from game g where season_id = 64 and status = 'FINAL' and ending_period = 2;
select count(*) from game where season_id = 64 and home_score > away_score and status = 'FINAL';
select count(*) from game where season_id = 64 and status = 'FINAL';
select 179 / 287; -- = .6237

-- POS -- HOME/AWAY FLIP -- HOCKEY
select avg(home_score), avg(away_score), avg(home_score + away_score) / 2, count(*), (select count(*) from game where season_id = g.season_id and status = 'FINAL') from game g where season_id = 65 and status = 'FINAL' and ending_period = 3;
select count(*) from game where season_id = 65 and home_score > away_score and status = 'FINAL';
select count(*) from game where season_id = 65 and status = 'FINAL';
select 184 / 337; -- = .5460

-- POS -- HOME/AWAY FLIP -- BASKETBALL
set @seasonId = 66;
select avg(home_score), avg(away_score), avg(home_score + away_score) / 2, count(*), (select count(*) from game where season_id = g.season_id and status = 'FINAL') from game g where season_id = @seasonId and status = 'FINAL' and ending_period = 2;
select (select count(*) from game where season_id = @seasonId and home_score > away_score and status = 'FINAL') / (select count(*) from game where season_id = @seasonId and status = 'FINAL');

-- POS -- TOTAL SCORE CALIBRATION -- BASKETBALL
set @seasonId = 67;
select avg(home_score), avg(away_score), avg(home_score + away_score) / 2, count(*), (select count(*) from game where season_id = g.season_id and status = 'FINAL') from game g where season_id = @seasonId and status = 'FINAL' and ending_period = 2;
select (select count(*) from game where season_id = @seasonId and home_score > away_score and status = 'FINAL') / (select count(*) from game where season_id = @seasonId and status = 'FINAL');

set @seasonId = 68;
select avg(home_score), avg(away_score), avg(home_score + away_score) / 2, count(*), (select count(*) from game where season_id = g.season_id and status = 'FINAL') from game g where season_id = @seasonId and status = 'FINAL' and ending_period = 2;
select (select count(*) from game where season_id = @seasonId and home_score > away_score and status = 'FINAL') / (select count(*) from game where season_id = @seasonId and status = 'FINAL');

-- POS -- TOTAL SCORE CALIBRATION -- HOCKEY
set @seasonId = 69;
select avg(home_score), avg(away_score), avg(home_score + away_score) / 2, count(*), (select count(*) from game where season_id = g.season_id and status = 'FINAL') from game g where season_id = @seasonId and status = 'FINAL' and ending_period = 3;
select (select count(*) from game where season_id = @seasonId and home_score > away_score and status = 'FINAL') / (select count(*) from game where season_id = @seasonId and status = 'FINAL');

-- POS -- TOTAL SCORE CALIBRATION -- BASKETBALL
set @seasonId = 70;
select avg(home_score), avg(away_score), avg(home_score + away_score) / 2, count(*), (select count(*) from game where season_id = g.season_id and status = 'FINAL') from game g where season_id = @seasonId and status = 'FINAL' and ending_period = 2;
select (select count(*) from game where season_id = @seasonId and home_score > away_score and status = 'FINAL') / (select count(*) from game where season_id = @seasonId and status = 'FINAL');

-- POS -- TOTAL SCORE CALIBRATION * 2 -- HOCKEY
set @seasonId = 71;
select avg(home_score), avg(away_score), avg(home_score + away_score) / 2, count(*), (select count(*) from game where season_id = g.season_id and status = 'FINAL') from game g where season_id = @seasonId and status = 'FINAL' and ending_period = 3;
select (select count(*) from game where season_id = @seasonId and home_score > away_score and status = 'FINAL') / (select count(*) from game where season_id = @seasonId and status = 'FINAL');

-- POS -- TOTAL SCORE CALIBRATION 1.2 -- HOCKEY
set @seasonId = 72;
select avg(home_score), avg(away_score), avg(home_score + away_score) / 2, count(*), (select count(*) from game where season_id = g.season_id and status = 'FINAL') from game g where season_id = @seasonId and status = 'FINAL' and ending_period = 3;
select (select count(*) from game where season_id = @seasonId and home_score > away_score and status = 'FINAL') / (select count(*) from game where season_id = @seasonId and status = 'FINAL');

set @seasonId = 73;
select avg(home_score), avg(away_score), avg(home_score + away_score) / 2, count(*), (select count(*) from game where season_id = g.season_id and status = 'FINAL') from game g where season_id = @seasonId and status = 'FINAL' and ending_period = 3;
select (select count(*) from game where season_id = @seasonId and home_score > away_score and status = 'FINAL') / (select count(*) from game where season_id = @seasonId and status = 'FINAL');

-- POS -- TOTAL SCORE CALIBRATION 1.1 -- HOCKEY -- result: ~2.8
set @seasonId = 74;
select avg(home_score), avg(away_score), avg(home_score + away_score) / 2, count(*), (select count(*) from game where season_id = g.season_id and status = 'FINAL') from game g where season_id = @seasonId and status = 'FINAL' and ending_period = 3;
select (select count(*) from game where season_id = @seasonId and home_score > away_score and status = 'FINAL') / (select count(*) from game where season_id = @seasonId and status = 'FINAL');

-- POS -- TOTAL SCORE CALIBRATION 1.0 -- HOCKEY -- result: ~2.6
set @seasonId = 75;
select avg(home_score), avg(away_score), avg(home_score + away_score) / 2, count(*), (select count(*) from game where season_id = g.season_id and status = 'FINAL') from game g where season_id = @seasonId and status = 'FINAL' and ending_period = 3;
select (select count(*) from game where season_id = @seasonId and home_score > away_score and status = 'FINAL') / (select count(*) from game where season_id = @seasonId and status = 'FINAL');

-- POS -- TOTAL SCORE CALIBRATION 1.05 -- HOCKEY -- result: ~2.7
set @seasonId = 76;
select avg(home_score), avg(away_score), avg(home_score + away_score) / 2, count(*), (select count(*) from game where season_id = g.season_id and status = 'FINAL') from game g where season_id = @seasonId and status = 'FINAL' and ending_period = 3;
select (select count(*) from game where season_id = @seasonId and home_score > away_score and status = 'FINAL') / (select count(*) from game where season_id = @seasonId and status = 'FINAL');

-- POS -- TOTAL SCORE CALIBRATION 1.11 -- BASKETBALL -- result: ~73.5
set @seasonId = 78;
select avg(home_score), avg(away_score), avg(home_score + away_score) / 2, count(*), (select count(*) from game where season_id = g.season_id and status = 'FINAL') from game g where season_id = @seasonId and status = 'FINAL' and ending_period = 2;
select (select count(*) from game where season_id = @seasonId and home_score > away_score and status = 'FINAL') / (select count(*) from game where season_id = @seasonId and status = 'FINAL');

-- POS -- TOTAL SCORE CALIBRATION 1.05 -- HOCKEY -- result: ~2.7
set @seasonId = 79;
select avg(home_score), avg(away_score), avg(home_score + away_score) / 2, count(*), (select count(*) from game where season_id = g.season_id and status = 'FINAL') from game g where season_id = @seasonId and status = 'FINAL' and ending_period = 3;
select (select count(*) from game where season_id = @seasonId and home_score > away_score and status = 'FINAL') / (select count(*) from game where season_id = @seasonId and status = 'FINAL');

-- POS -- TOTAL SCORE CALIBRATION 1.05 -- HOCKEY -- result: ~2.7
set @seasonId = 80;
select avg(home_score), avg(away_score), avg(home_score + away_score) / 2, count(*), (select count(*) from game where season_id = g.season_id and status = 'FINAL') from game g where season_id = @seasonId and status = 'FINAL' and ending_period = 3;
select (select count(*) from game where season_id = @seasonId and home_score > away_score and status = 'FINAL') / (select count(*) from game where season_id = @seasonId and status = 'FINAL');
