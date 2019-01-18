package mk.playground.foosball.controller;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mk.playground.foosball.model.Player;
import mk.playground.foosball.repository.PlayerRepository;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PlayerRepositoryTest {

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    public void findAll() {
        assertThat(playerRepository.findAll()).hasSize(6);
    }

    @Test
    void findById() {
        Player martin = playerRepository.findAll().stream()
                .filter(p -> p.getName().equals("martin"))
                .findFirst()
                .get(); // force or fail

        Optional<Player> maybeMartin = playerRepository.findById(martin.getId());
        assertThat(maybeMartin).isPresent();
        assertThat(maybeMartin.get().getName()).isEqualTo("martin");
    }

}
