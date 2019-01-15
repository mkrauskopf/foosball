package mk.playground.foosball.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mk.playground.foosball.model.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

}
