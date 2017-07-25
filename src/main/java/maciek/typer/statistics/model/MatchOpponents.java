package maciek.typer.statistics.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by maciej on 22.07.17.
 */
@AllArgsConstructor
@Getter
@Setter
public class MatchOpponents {

    private BigDecimal opponentA;
    private BigDecimal opponentB;
    private char result;

    /*when owner is posL
    A= 0
    B= G

    when owner is pos0
    A= L
    B= G

    when owner is posG
    A= L
    B= 0
     */

}
