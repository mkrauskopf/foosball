Yet another playground. This time for Java web application development.

### Implementation Notes

#### General
* I prefer Spring _constructor injection_ before _setter injection_. Fields can be declared `final`.
* By default, project uses [H2](http://www.h2database.com/) in-memory database. Should be easy to switch via properties
and more JDBC drivers on the classpath.
* Security is JDBC-based using tables `player`(authentication), `role`(authorization) and the `player_role` join-table.
  * Passwords are hashed with [bcrypt](https://en.wikipedia.org/wiki/Bcrypt) function before stored in the database.
* Spring Boot Actuator is available. Go to http://localhost:8080/actuator/.

#### Test
* Tests should use own data instead of the ones set up on the `ApplicationReadyEvent`.

#### No frontend
* This is backend stuff only so far. I have enough of frontend in [Devstronomy](https://devstronomy.com/) project :)
* Use, e.g. [ARC](https://chrome.google.com/webstore/detail/advanced-rest-client/hgmloofddffdnphfgcellkdfbfbjeloo)
to play with the REST API (for `GET` browser URL is enough).
