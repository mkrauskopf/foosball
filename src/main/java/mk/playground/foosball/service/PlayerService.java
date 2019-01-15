package mk.playground.foosball.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import mk.playground.foosball.model.Player;
import mk.playground.foosball.repository.PlayerRepository;
import mk.playground.foosball.repository.RoleRepository;

@Service
public class PlayerService {

    private final PlayerRepository repository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public PlayerService(PlayerRepository playerRepository,
                         RoleRepository roleRepository,
                         BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.repository = playerRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /**
     * Persists player using {@link PlayerRepository} with the bcrypt-ed the password.
     *
     * @param player the player with the password in plaintext.
     * @return the persisted player with the encrypted password.
     */
    public Player create(Player player) {
        player.setPassword(bCryptPasswordEncoder.encode(player.getPassword()));
        player.setActive(1);
        return repository.save(player);
    }

}
