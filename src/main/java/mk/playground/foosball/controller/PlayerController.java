package mk.playground.foosball.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mk.playground.foosball.dto.PlayerInfo;
import mk.playground.foosball.dto.PlayerStatisticsInfo;
import mk.playground.foosball.model.Player;
import mk.playground.foosball.repository.PlayerRepository;
import mk.playground.foosball.service.PlayerService;

@RestController
@RequestMapping("/player")
public class PlayerController {

    private final PlayerRepository repository;

    private final PlayerService service;

    public PlayerController(PlayerRepository repository, PlayerService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping(value = "/")
    public List<Player> getAllPlayers() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable(value = "id") long playerId) {
        Player player = repository.findById(playerId).get();
        return ResponseEntity.ok().body(player);
    }

    @GetMapping("/{id}/statistics")
    public PlayerStatisticsInfo getStatistics(@PathVariable(value = "id") long playerId) {
        return service.getStatistics(playerId);
    }

    @PostMapping(value = "/")
    public ResponseEntity<Player> create(@RequestBody PlayerInfo playerInfo) {
        Player player = Player.builder()
                .name(playerInfo.getName())
                .password(playerInfo.getPassword())
                .build();
        return new ResponseEntity<>(service.create(player), HttpStatus.CREATED);
    }

}
