# Points Per Game Test
1. Schedule Season with 16 test teams (passing in the sport by the sport param)
   1. http://192.168.1.71:8080/season/schedule?scheduleType=HOME_ROTATION_RANDOM&sport=BASKETBALL&league=TEST&teamIds=65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80&numGames=4&title=Points%20Per%20Game%20Test
2. Get next season game (with id that was returned  from the last step)
   1. http://192.168.1.71:8080/game/getNextSeasonGame?seasonId=<seasonId>
3. Play season game (with id that was returned from the last step)
   1. http://192.168.1.71:8080/game/playSeasonGame?gameId=<nextSeasonGameId>
4. Set the config & play ball
   1. http://192.168.1.71:8080/game/setTickMilliseconds?value=0
   2. http://192.168.1.71:8080/game/setGamesToPlay?numGames=240
   3. http://192.168.1.71:8080/game/setGamesPlayingConcurrently?numGames=8
5. Play ball!
   1. http://192.168.1.71:8080/game/playGames

6. In SQL, get most recent season
   1. select max(id) from season;
7. Check how many games have completed
   1. select count(*) from game where season_id = <seasonId> and status = 'FINAL';
8. query average home score and away score
   1. select avg(home_score), avg(away_score) from game where season_id = <seasonId>;
9. query average score overall
   1. select avg((home_score + away_score) / 2) from game where season_id = <seasonId>;
d
