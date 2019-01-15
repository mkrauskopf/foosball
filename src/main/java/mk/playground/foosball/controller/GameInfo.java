package mk.playground.foosball.controller;


import java.util.List;

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
public class GameInfo {

    private List<Long> playersIds;

    private Long winner1Id;

    private Long winner2Id;

}
