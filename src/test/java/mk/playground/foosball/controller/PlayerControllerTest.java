package mk.playground.foosball.controller;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mk.playground.foosball.dto.PlayerStatisticsInfo;
import mk.playground.foosball.model.Player;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PlayerControllerTest {

    @Autowired
    private PlayerController playerController;

    @Test
    public void getAllPlayers() {
        assertThat(playerController).isNotNull();
        List<Player> allPlayers = playerController.getAllPlayers();
        assertThat(allPlayers).hasSize(6);
        assertThat(allPlayers)
                .extracting(Player::getName)
                .containsExactly("martin", "honza", "katka", "boris", "john", "admin");
    }

    @Test
    public void statistics() {
        Player martin = playerController.getAllPlayers().get(0);
        PlayerStatisticsInfo statistics = playerController.getStatistics(martin.getId());
        assertThat(statistics.getNOfWins()).isEqualTo(1);
        assertThat(statistics.getNOfLooses()).isEqualTo(0);
    }

}
