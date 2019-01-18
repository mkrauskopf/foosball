package mk.playground.foosball.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mk.playground.foosball.model.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class GameRepositoryTest {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private GameRepository gameRepository;

    @Test
    public void countWins() {
        List<Player> players = playerRepository.findAll();
        assertStats(players, "martin", 1, 1);
        assertStats(players, "honza", 2, 2);
        assertStats(players, "john", 1, 0);
    }

    private void assertStats(
            List<Player> players, String playerName, int expectedGames, int expectedWins) {
        Player player = players.stream()
                .filter(p -> p.getName().equals(playerName))
                .findFirst()
                .get(); // force or fail
        int games = gameRepository.numberOfGames(player.getId());
        int wins = gameRepository.countWins(player.getId());
        assertEquals(expectedGames, games, "number of games for: " + playerName);
        assertEquals(expectedWins, wins, "number of wins: " + playerName);
    }

}
