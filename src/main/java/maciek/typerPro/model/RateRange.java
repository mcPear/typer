package maciek.typerPro.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import maciek.typerPro.util.BigDecimalComparison;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by maciej on 28.07.17.
 */
@AllArgsConstructor
@Getter
@Setter
public class RateRange {

    private static final BigDecimalComparison BDC = new BigDecimalComparison();
    private final BigDecimal range;
    private final BigDecimal primeRate;
    private List<Rate> rates;

    private boolean fits(Rate rate) {
        return BDC.isGreaterEqual(rate.getValue(), primeRate) && BDC.isLess(rate.getValue(), primeRate.add(range));
    }

    public boolean add(Rate rate) {
        boolean fits;
        if (fits = fits(rate)) {
            rates.add(rate);
        }
        return fits;
    }

    public BigDecimal getWinsPercent(String status) {
        int wins = 0;
        int losses = 0;
        List<Rate> ratesByStatus = rates.stream().filter(r -> r.getStatus().equals(status)).collect(Collectors.toList());
        for (Rate rate : ratesByStatus) {
            if(rate.isWinner()){
                wins++;
            }
            else{
                losses++;
            }
        }
        return (new BigDecimal(wins).divide(new BigDecimal(wins+losses), 2, RoundingMode.HALF_UP)).multiply(new BigDecimal(100));
    }

}
