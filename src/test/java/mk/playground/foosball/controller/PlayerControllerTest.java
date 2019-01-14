package mk.playground.foosball.controller;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.google.common.collect.ImmutableList;

import mk.playground.foosball.model.Player;
import mk.playground.foosball.repository.PlayerRepository;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PlayerControllerTest {

    private final ImmutableList<Player> testPlayers;

    @Autowired
    private PlayerController playerController;

    @Autowired
    private PlayerRepository playerRepository;

    public PlayerControllerTest() {
        this.testPlayers = ImmutableList.of(
                new Player("Martin"),
                new Player("Honza"),
                new Player("Katka"),
                new Player("Boris")
        );
    }

    @BeforeEach
    void setUp() {
        testPlayers.forEach(p -> playerRepository.save(p));
    }

    @Test
    public void getAllPlayers() {
        assertThat(playerController).isNotNull();
        List<Player> allPlayers = playerController.getAllPlayers();
        assertThat(allPlayers).hasSize(4);
        assertThat(allPlayers.get(0).getName()).isEqualTo("Martin");
        assertThat(allPlayers).isEqualTo(testPlayers);
    }

}
