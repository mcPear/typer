package maciek.typer.statistics.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * Created by maciej on 23.07.17.
 */
@AllArgsConstructor
@Getter
public class OpponentModel {
    private char position;
    private int losings;
    private int wins;
    private BigDecimal rate;

    public OpponentModel(char position, BigDecimal rate) {
        this.position = position;
        this.rate = rate;
    }

    public void incrementWins(){
        wins++;
    }

    public void incrementLosings(){
        losings++;
    }

    public int getWinsPercent(){
        return wins+losings==0?0:(100*wins)/(wins+losings);
    }

}
