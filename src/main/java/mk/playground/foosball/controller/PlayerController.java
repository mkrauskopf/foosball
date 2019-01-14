package mk.playground.foosball.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mk.playground.foosball.model.Player;
import mk.playground.foosball.repository.PlayerRepository;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerRepository repository;

    @GetMapping(value = "/")
    public List<Player> getAllPlayers() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable(value = "id") long playerId) {
        return ResponseEntity.ok().body(repository.findById(playerId).get());
    }

    @RequestMapping(value = "/player", method = RequestMethod.POST)
    public ResponseEntity<Player> create(@RequestBody Player player) {
        Player item = repository.save(player);
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

}
