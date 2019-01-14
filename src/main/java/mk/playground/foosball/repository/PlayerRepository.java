package mk.playground.foosball.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mk.playground.foosball.model.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

}
