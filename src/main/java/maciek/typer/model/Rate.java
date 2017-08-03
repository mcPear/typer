package maciek.typer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Created by maciej on 28.07.17.
 */
@AllArgsConstructor
@Getter
@Setter
public class Rate {
    private BigDecimal value;
    private FootballMatch footballMatch;

    public String getStatus() {
        String status;
        if (value.equals(getSmallNeighbour())) {
            status = "small";
        } else if (value.equals(getGreatNeighbour())) {
            status = "great";
        } else {
            status = "draw";
        }
        return status;
    }

    public BigDecimal getGreatNeighbour() {
        return footballMatch.getRate1().max(footballMatch.getRate2());
    }

    public BigDecimal getSmallNeighbour() {
        return footballMatch.getRate1().min(footballMatch.getRate2());
    }

    public BigDecimal getDrawNeighbour() {
        return footballMatch.getRate0();
    }

    public boolean isWinner() {
        boolean isWinner = false;
        BigDecimal winnerRate = null;
        switch (footballMatch.getResult()) {
            case ("0"): {
                winnerRate = footballMatch.getRate0();
                break;
            }
            case ("1"): {
                winnerRate = footballMatch.getRate1();
                break;
            }
            case ("2"): {
                winnerRate = footballMatch.getRate2();
                break;
            }
        }
        //System.out.println(winnerRate + " " + footballMatch.getResult());
        switch (getStatus()) {
            case ("small"): {
                //System.out.println(winnerRate+" "+getSmallNeighbour());
                if (winnerRate.equals(getSmallNeighbour())) {
                    isWinner = true;
                }
                break;
            }
            case ("great"): {
                if (winnerRate.equals(getGreatNeighbour())) {
                    isWinner = true;
                }
                break;
            }
            case ("draw"): {
                if (winnerRate.equals(getDrawNeighbour())) {
                    isWinner = true;
                }
                break;
            }
        }
        return isWinner;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "value=" + value +
                "status=" + getStatus() +
                "smallNeighbour="+ getSmallNeighbour() +
                "greatNeighbour="+ getGreatNeighbour() +
                ", footballMatch=" + footballMatch +
                '}';
    }
}
