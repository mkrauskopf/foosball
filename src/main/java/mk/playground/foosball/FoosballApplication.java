package mk.playground.foosball;

import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.google.common.collect.ImmutableSet;

import mk.playground.foosball.model.Player;
import mk.playground.foosball.model.Role;
import mk.playground.foosball.repository.RoleRepository;
import mk.playground.foosball.service.GameService;
import mk.playground.foosball.service.PlayerService;

import static java.util.stream.Collectors.toList;

@SpringBootApplication
public class FoosballApplication {

    private static Logger LOG = LoggerFactory.getLogger(FoosballApplication.class);

    private final PlayerService playerService;

    private final GameService gameService;

    private final RoleRepository roleRepository;

    @Autowired
    public FoosballApplication(PlayerService playerService, GameService gameService, RoleRepository roleRepository) {
        this.playerService = playerService;
        this.gameService = gameService;
        this.roleRepository = roleRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(FoosballApplication.class, args);
    }

    /**
     * Add four players straight out of the box. Such that application is immediately ready for playing.
     *
     * <p>
     *     Note: This is rather a hack for testing purposes. We could do this via {@code data.sql}. But then we would
     *     have to use encrypted passwords which would be ugly as well.
     * </p>
     */
    @EventListener(ApplicationReadyEvent.class)
    public void initialFewPlayersAfterStartup() {
        LOG.info("Adding four players and admin for demonstrating purposes.");

        // players with 'user' role
        Role userRole = roleRepository.findByRole("USER");
        List<Long> playersIds = Stream.of("martin", "honza", "katka", "boris")
                .map(name -> player(name, userRole)) // use name as password
                .map(playerService::create)
                .map(Player::getId)
                .collect(toList());

        // admin
        playerService.create(player("admin", roleRepository.findByRole("ADMIN")));

        LOG.info("Adding two games for demonstrating purposes.");
        gameService.create(playersIds, playersIds.get(0), playersIds.get(1));
        gameService.create(playersIds, playersIds.get(0), playersIds.get(3));
    }

    private Player player(String name, Role role) {
        return Player.builder()
                .name(name)
                .password(name)
                .roles(ImmutableSet.of(role))
                .build();
    }

}
