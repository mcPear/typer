package maciek.typerPro.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by maciej on 28.07.17.
 */
@AllArgsConstructor
@Getter
@Setter
public class Rate {
    private BigDecimal value;
    private FootballMatch footballMatch;

    public String getStatus(){
        String status;
        BigDecimal smallRate;
        BigDecimal greatRate;
        try {
            smallRate = footballMatch.getRate0().min(footballMatch.getRate1()).min(footballMatch.getRate2());
            greatRate = footballMatch.getRate0().max(footballMatch.getRate1()).max(footballMatch.getRate2());
        }
        catch (NullPointerException e){
            throw new NullPointerException("Rate::getStatus: One of all three FootballMatch's rates equals null");
        }
        try {
            if (value.equals(smallRate)) {
                status = "small";
            } else if (value.equals(greatRate)) {
                status = "great";
            } else {
                status = "draw";
            }
        }
        catch (NullPointerException e){
            throw new NullPointerException("Rate::getStatus: The value of this Rate equals null");
        }
        return status;
    }
}
