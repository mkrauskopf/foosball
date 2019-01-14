package mk.playground.foosball.controller;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mk.playground.foosball.model.Player;
import mk.playground.foosball.repository.PlayerRepository;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class PlayerRepositoryTest {

    @Autowired
    private PlayerRepository playerRepository;

    @BeforeEach
    void setUp() {
        playerRepository.save(new Player("Martin"));
    }

    @Test
    public void findAll() {
        assertThat(playerRepository.findAll()).hasSize(1);
    }

    @Test
    void findById() {
        Optional<Player> maybeMartin = playerRepository.findById(1l);
        assertThat(maybeMartin).isPresent();
        assertThat(maybeMartin.get().getName()).isEqualTo("Martin");
    }
}
