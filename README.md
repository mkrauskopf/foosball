Yet another playground. This time for Java web application development, so far mainly for Spring stuff (Boot, JPA,
Data, Security, Actuator,  ...).

### Implementation Notes

#### General
* I prefer Spring _constructor injection_ before _setter injection_. Fields can be declared `final`.
* By default, project uses [H2](http://www.h2database.com/) in-memory database. Should be easy to switch via properties
and more JDBC drivers on the classpath.
* Security is JDBC-based using tables `player`(authentication), `role`(authorization) and the `player_role` join-table.
  * Passwords are hashed with [bcrypt](https://en.wikipedia.org/wiki/Bcrypt) function before stored in the database.
* Spring Boot Actuator is available. Go to http://localhost:8080/actuator/.
* CSRF is disabled for now. To make life easier for REST clients.

#### Test
* Tests should use own data instead of the ones set up on the `ApplicationReadyEvent`.

#### No frontend
* This is backend stuff only so far. I have enough of frontend in [Devstronomy](https://devstronomy.com/) project :)
* Use, e.g. [ARC](https://chrome.google.com/webstore/detail/advanced-rest-client/hgmloofddffdnphfgcellkdfbfbjeloo)
to play with the REST API (for `GET` browser URL is enough).

### To be done...
... when having a need, or more time, and the right mood. :)

* Allow player creation only by `ADMIN` role.
* Integration testing.
* A better model for winners and/or more effective fetching of statistics.


#### REST API

##### Authentication
* `GET` http://localhost:8080/login: login endpoint (default form by Spring Security)
* `GET` http://localhost:8080/logout: logout endpoint

##### Players
* `GET` http://localhost:8080/player/: lists all players
* `GET` http://localhost:8080/player/2/: gets information about player with ID 2
* `POST` http://localhost:8080/player/: creates new player. Sample JSON body:
   ```json
    {
      "name": "lojza",
      "password": "lojza"
    }
    ```
* `GET` http://localhost:8080/player/2/statistics: gets statistics for the player with ID 2

##### Games
* `GET` http://localhost:8080/game/: lists all games
* `GET` http://localhost:8080/game/2/: gets information about game with ID 1
* `POST` http://localhost:8080/game/: creates new game. Sample JSON body:
    ```json
    {
      "playersIds": [1, 2, 3, 4],
      "winner1Id": 1,
      "winner2Id": 3
    }
    ```
