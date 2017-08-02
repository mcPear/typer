package maciek.typer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import maciek.typer.util.BigDecimalComparison;

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

    public boolean addByNeighbout(Rate rate, String neighbourStatus) {
        boolean fits;
        if (fits = fitsByNeighbour(rate, neighbourStatus)) {
            rates.add(rate);
        }
        return fits;
    }

    private boolean fitsByNeighbour(Rate rate, String neighbourStatus) {
        boolean fits;
        switch(neighbourStatus){
            case("small"):{
                fits = BDC.isGreaterEqual(rate.getSmallNeighbour(), primeRate) && BDC.isLess(rate.getSmallNeighbour(), primeRate.add(range));
                break;
            }
            case("draw"):{
                fits = BDC.isGreaterEqual(rate.getDrawNeighbour(), primeRate) && BDC.isLess(rate.getDrawNeighbour(), primeRate.add(range));
                break;
            }
            case("great"):{
                fits = BDC.isGreaterEqual(rate.getGreatNeighbour(), primeRate) && BDC.isLess(rate.getGreatNeighbour(), primeRate.add(range));
                break;
            }
            default:{
                throw new IllegalArgumentException("RateRange::fitsByNeighbour: Wrong neighbour status: "+neighbourStatus);
            }
        }
        return fits;
    }

    public BigDecimal getWinsPercent(String status) {
        int wins = 0;
        int losses = 0;
        List<Rate> ratesByStatus = rates.stream().filter(r -> r.getStatus().equals(status)).collect(Collectors.toList());
        for (Rate rate : ratesByStatus) {
            System.out.println(rate.toString());
            if(rate.isWinner()){
                wins++;
            }
            else{
                losses++;
            }
        }
        return wins+losses>0?(new BigDecimal(wins).divide(new BigDecimal(wins+losses), 2, RoundingMode.HALF_UP)).multiply(new BigDecimal(100)):new BigDecimal("0.00");
    }

}
