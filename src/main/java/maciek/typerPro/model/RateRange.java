package maciek.typerPro.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import maciek.typerPro.util.BigDecimalComparison;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by maciej on 28.07.17.
 */
@AllArgsConstructor
@Getter
@Setter
public class RateRange {
    @Autowired
    private BigDecimalComparison bdc;
    private final BigDecimal range;
    private final BigDecimal primeRate;
    private List<Rate> rates;

    private boolean fits(Rate rate){
        return bdc.isGreaterEqual(rate.getValue(), primeRate) && bdc.isLess(rate.getValue(), primeRate.add(range));
    }

    public boolean add(Rate rate){
        boolean fits;
        if(fits = fits(rate)){
            rates.add(rate);
        }
        return fits;
    }


}
