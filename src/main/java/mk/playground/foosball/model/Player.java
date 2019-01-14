package mk.playground.foosball.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.google.common.annotations.VisibleForTesting;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "player")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
// NOTE: Lombok and Jackson does not work well together. Getters in the source code are still needed.
//       See e.g.: https://stackoverflow.com/questions/51464720/lombok-1-18-0-and-jackson-2-9-6-not-working-together
public final class Player {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", nullable = false)
    @Size(min = 3, max = 20)
    private String name;

    /**
     * Production does not need this. It likely uses no-arg ctor with reflection.
     */
    @VisibleForTesting
    public Player(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
