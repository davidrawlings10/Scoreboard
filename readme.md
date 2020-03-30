This project relies on MySQL. I installed MySQL Community Server. https://dev.mysql.com/downloads/mysql/

I made the root password "root".

I used MySQL Workbench to query the database. https://www.mysql.com/products/workbench/

The script resources/dropTables.sql can be run to clear the database for resources/createTables.sql.
The script resources/createTables.sql can be run to setup the database tables for the application.
The script resources/insertData.sql can be run to setup data for a league.

After running the scripts...
To play a game: http://localhost:8080/game/play
To play a season: http://localhost:8080/season/play?leagueId=1


Note dump:

Plan:
1. Establish update, delete
2. Setup spring boot best practice model

3. Start with 4 clubs just like NHL started. 2 from Brickway, 1 from Superdome, 1 from Telluride. Create league called ___ in league table. Link these 4 teams to league with league_id. From one click, a new season should be created and played out. No playoffs yet.
4. Conferences, Divisions can be added as rows in league table and additional foreign keys can be added to team table: b_league_id, c_league_id. That's the best design I can think of right now.

Aves Hockey League
Hummingbird
Goldfinch
Chickadee
Sparrow

Hummingbird
Cardinal
Oriole
Robin
Woodpecker
Goldfinch
Chickadee
Bluebird
Wren
Sparrow
Warbler
Blue Jay
Purple Martin
Mourning Dove
Killdeer

Mallard
Turkey
Rooster
Sea Gull
Penguin
Parrot
Goose
Hawk
Swan

Birds belong to Aves


https://www.lisashea.com/birding/basics/art18118.html
https://www.thespruce.com/types-of-birds-385446
http://www.birdsandblooms.com/birding/birding-basics/common-birds-north-america/
https://www.iconfinder.com/search/?q=hummingbird&from=navbar

Rainforest
Prairie
Beach
Ocean
Mountain

Farsbee

Cities:
Cherry point, Brickway, CA
Telluride
Waterstep
Denni, CA

Spring boot best practice project structure
https://medium.com/the-resonant-web/spring-boot-2-0-starter-kit-part-1-23ddff0c7da2
https://medium.com/the-resonant-web/spring-boot-2-0-project-structure-and-best-practices-part-2-7137bdcba7d3

Bootiful:
https://youtu.be/P6rwKHnXUJI

Intro to React:
https://vimeo.com/213710634

Connecting with MySQL
https://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html
https://spring.io/guides/gs/accessing-data-mysql/