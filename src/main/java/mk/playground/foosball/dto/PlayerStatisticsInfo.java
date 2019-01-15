package mk.playground.foosball.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ToString
public class PlayerStatisticsInfo {

    private int nOfWins;

    private int nOfLooses;

}
