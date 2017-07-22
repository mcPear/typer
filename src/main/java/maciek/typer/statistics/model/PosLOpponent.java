package maciek.typer.statistics.model;

import lombok.AllArgsConstructor;
import java.math.BigDecimal;

/**
 * Created by maciej on 22.07.17.
 */
@AllArgsConstructor
public class PosLOpponent {

    private BigDecimal opponentG;
    private BigDecimal opponent0;
    private boolean won;

    public BigDecimal getRatioG(BigDecimal opponentL){
        return opponentL.divide(opponentG);
    }

    public BigDecimal getDiffL(BigDecimal opponentL){
        return opponentG.min(opponentL);
    }

    public BigDecimal getRatio0(BigDecimal opponentL){
        return opponentL.divide(opponent0);
    }

    public BigDecimal getDiff0(BigDecimal opponentL){
        return opponent0.min(opponentL);
    }

    @Override
    public String toString() {
        return "{" +
                "opponentG=" + opponentG +
                ", won=" + won +
                '}';
    }
}
