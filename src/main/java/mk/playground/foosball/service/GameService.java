package mk.playground.foosball.service;

import java.util.List;

import org.springframework.stereotype.Service;

import mk.playground.foosball.model.Game;
import mk.playground.foosball.model.Player;
import mk.playground.foosball.repository.GameRepository;
import mk.playground.foosball.repository.PlayerRepository;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;

    public GameService(GameRepository gameRepository, PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }

    public Game create(List<Long> playersIds, Long winner1Id, Long winner2Id) {
        List<Player> players = playerRepository.findAllById(playersIds);
        checkArgument(players.size() == 4, "Exactly four players expected: %s", players);
        checkState(playersIds.contains(winner1Id), "%s(id) must be among players: %s", winner1Id, players);
        checkState(playersIds.contains(winner2Id), "%s(id) must be among players: %s", winner2Id, players);
        Game game = Game.builder()
                .players(players)
                .winner1Id(winner1Id)
                .winner2Id(winner2Id)
                .build();
        return gameRepository.save(game);
    }

}
