package maciek.typer.statistics.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by maciej on 21.07.17.
 */
@Getter
@Setter
@NoArgsConstructor
public class RateModel {

    private BigDecimal rateValue;
    private RatePosition positionL;
    private RatePosition position0;
    private RatePosition positionG;

    public RateModel(BigDecimal rateValue) {
        this.rateValue = rateValue;
        positionL = new RatePosition('L');
        position0 = new RatePosition('0');
        positionG = new RatePosition('G');
    }


    @Override
    public String toString() {
        return "RateModel{" +
                "rateValue=" + rateValue +"\n"+
                ", positionL=" + positionL +"\n"+
                ", position0=" + position0 +"\n"+
                ", positionG=" + positionG +
                '}';
    }
}
