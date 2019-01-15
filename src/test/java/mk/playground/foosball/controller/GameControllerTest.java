package mk.playground.foosball.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class GameControllerTest {

    @Autowired
    private GameController gameController;

    @Test
    public void getAllGames() {
        assertThat(gameController).isNotNull();
        assertThat(gameController.getAllGames()).hasSize(2);
    }

}
