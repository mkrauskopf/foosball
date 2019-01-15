package mk.playground.foosball;

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
import mk.playground.foosball.service.PlayerService;

@SpringBootApplication
public class FoosballApplication {

    private static Logger LOG = LoggerFactory.getLogger(FoosballApplication.class);

    private final PlayerService playerService;

    private final RoleRepository roleRepository;

    @Autowired
    public FoosballApplication(PlayerService playerRepository, RoleRepository roleRepository) {
        this.playerService = playerRepository;
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
        LOG.info("Adding four players to make the playing easier.");
        Role userRole = roleRepository.findByRole("USER");
        Stream.of("martin", "honza", "katka", "boris")
                .map(name -> player(name, userRole)) // use name as password
                .forEach(playerService::create);
    }

    private Player player(String name, Role role) {
        return Player.builder()
                .name(name)
                .password(name)
                .roles(ImmutableSet.of(role))
                .build();
    }

}
