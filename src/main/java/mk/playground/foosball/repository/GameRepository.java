package mk.playground.foosball.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mk.playground.foosball.model.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    @Query("select count(g) from Game g where g.winner1Id = ?1 or g.winner2Id = ?1")
    int countWins(Long playerId);

    @Query("select count(g) from Game g join g.players p where p.id = ?1")
    int numberOfGames(Long playerId);

}
