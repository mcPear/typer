package maciek.typer;

import lombok.AllArgsConstructor;
import maciek.typer.model.FootballMatch;
import maciek.typer.model.RateRange;
import maciek.typer.model.Rate;
import maciek.typer.repository.FootballMatchRepository;
import maciek.typer.util.BigDecimalComparison;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by maciej on 30.07.17.
 */
@AllArgsConstructor
@Component
public class DataProcessorImpl implements DataProcessor{
    private final String START_RATE = "1.00";

    @Autowired
    private FootballMatchRepository repo;

    @Autowired
    private BigDecimalComparison bdc;

    public List<Rate> getAllNotNullRates(){
        List<FootballMatch> footballMatches = repo.findByResultIsNotNullAndRate1IsNotNullAndRate0IsNotNullAndRate2IsNotNull();

        List<Rate> rates = new ArrayList<>();
        for (FootballMatch footballMatch:footballMatches){
            rates.add(new Rate(footballMatch.getRate0(), footballMatch));
            rates.add(new Rate(footballMatch.getRate1(), footballMatch));
            rates.add(new Rate(footballMatch.getRate2(), footballMatch));
        }

        return rates;
    }

    private List<RateRange> getEmptyRateRanges(BigDecimal range, BigDecimal endRate){
        List<RateRange> rateRanges = new ArrayList<>();
        for(BigDecimal i = new BigDecimal(START_RATE); bdc.isLess(i, endRate); i=i.add(range)){
            rateRanges.add(new RateRange(range, i, new ArrayList<>()));
        }

        return rateRanges;
    }

    public List<RateRange> getFilledRateRanges(BigDecimal range, BigDecimal endRate){
        List<RateRange> rateRanges = getEmptyRateRanges(range, endRate);
        List<Rate> rates = getAllNotNullRates();

        for (Rate rate:rates){
            boolean rateIsPut = false;
            for(int i=0; i<rateRanges.size()&&!rateIsPut;i++){
                rateIsPut = rateRanges.get(i).add(rate);
            }
        }

        return rateRanges;
    }

}