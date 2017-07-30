package maciek.typer.statistics.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maciej on 21.07.17.
 */
@Getter
@Setter
public class RatePosition {

    private char position;
    private int losings;
    private int wins;
    private List<MatchOpponents> matchOpponentss;

    public RatePosition(char position) {
        this.position = position;
        matchOpponentss = new ArrayList<>();
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

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        matchOpponentss.forEach(plo -> sb.append(plo));

        return "RatePosition{" +
                "position=" + position +
                ", losings=" + losings +
                ", wins=" + wins +
                ", getWinsPercent=" + getWinsPercent() +
                //"\n"+sb+
                '}';
    }
}
