package mk.playground.foosball.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mk.playground.foosball.model.Game;
import mk.playground.foosball.repository.GameRepository;
import mk.playground.foosball.service.GameService;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameRepository repository;
    private final GameService service;

    public GameController(GameRepository repository, GameService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping(value = "/")
    public List<Game> getAllGames() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable(value = "id") long gameId) {
        return ResponseEntity.ok().body(repository.findById(gameId).get());
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Game> create(@RequestBody GameInfo gameInfo) {
        Game game = service.create(gameInfo.getPlayersIds(), gameInfo.getWinner1Id(), gameInfo.getWinner2Id());
        return new ResponseEntity<>(game, HttpStatus.CREATED);

    }

}
