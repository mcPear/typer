package maciek.typerPro.model;

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
        return getAllMatchRatesSorted()[2];
    }

    public BigDecimal getSmallNeighbour() {
        return getAllMatchRatesSorted()[0];
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

        switch (getStatus()) {
            case ("small"): {
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

    private BigDecimal[] getAllMatchRates() {
        BigDecimal[] allMatchRates = new BigDecimal[3];
        allMatchRates[0] = footballMatch.getRate0();
        allMatchRates[1] = footballMatch.getRate1();
        allMatchRates[2] = footballMatch.getRate2();
        return allMatchRates;
    }

    private BigDecimal[] getAllMatchRatesSorted() {
        Arrays.sort(getAllMatchRates());
        return getAllMatchRatesSorted();
    }
}
