package maciek.typer.statistics;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by maciej on 21.07.17.
 */
@Getter
@Setter
public class RatePosition {

    private char position;
    private int losings;
    private int wins;

    public RatePosition(char position) {
        this.position = position;
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
        return "RatePosition{" +
                "position=" + position +
                ", losings=" + losings +
                ", wins=" + wins +
                ", winsPercent=" + getWinsPercent() +
                '}';
    }
}
