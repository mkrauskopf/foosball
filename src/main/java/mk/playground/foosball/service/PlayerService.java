package mk.playground.foosball.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import mk.playground.foosball.dto.PlayerStatisticsInfo;
import mk.playground.foosball.model.Player;
import mk.playground.foosball.repository.GameRepository;
import mk.playground.foosball.repository.PlayerRepository;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public PlayerService(PlayerRepository playerRepository,
                         GameRepository gameRepository,
                         BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.playerRepository = playerRepository;
        this.gameRepository = gameRepository;
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
        return playerRepository.save(player);
    }

    public PlayerStatisticsInfo getStatistics(long playerId) {
        return new PlayerStatisticsInfo(gameRepository.countWins(playerId), gameRepository.countLooses(playerId));
    }
}
