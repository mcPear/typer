package maciek.typer;

import maciek.typer.model.RateRange;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by maciej on 31.07.17.
 */
public interface DataProcessor {
    public List<RateRange> getFilledRateRanges(BigDecimal range, BigDecimal endRate);
}
