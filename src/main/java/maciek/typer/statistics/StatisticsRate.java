package maciek.typer.statistics;

import lombok.AllArgsConstructor;
import maciek.typer.graph.RateBarChart;
import maciek.typer.model.FootballMatch;
import maciek.typer.repository.FootballMatchRepository;
import maciek.typer.statistics.model.MatchOpponents;
import maciek.typer.statistics.model.RateModel;
import maciek.typer.statistics.model.RatePosition;
import org.apache.log4j.Logger;
import org.jfree.ui.RefineryUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by maciej on 21.07.17.
 */
@Component
public class StatisticsRate implements CommandLineRunner{

    private Logger log = Logger.getLogger(StatisticsRate.class.getName());

    private final static String RATE_RANGE = "0.05";
    private final static int CHART_RATE_COUNT_LIMIT = 60;
    private final static char CHART_POSITION = 'L';
    private List<RateModel> rateModels;

    @Autowired
    private FootballMatchRepository repo;

    @Autowired
    private StatisticsOpponent statisticsOpponent;

    @Override
    public void run(String... strings) throws Exception {
        rateModels = loadRateStats();
        printRates();
        printChart();
        //printOpponentChart(new BigDecimal(1.6));
    }

    private void printOpponentChart(BigDecimal rate){
        RatePosition ratePosition = null;
        for (RateModel rm:rateModels){
            if(rm.getRateValue().compareTo(rate)==1){
                switch(CHART_POSITION){
                    case('L'):{
                        ratePosition = rm.getPositionL();
                        break;
                    }
                    case('0'):{
                        ratePosition = rm.getPosition0();
                        break;
                    }
                    case('G'):{
                        ratePosition = rm.getPositionG();
                        break;
                    }
                }
            }
        }
        statisticsOpponent.printChart(ratePosition);
    }

    private void printRates(){
        rateModels.forEach(rate -> System.out.println(rate));

    }

    private void printChart(){
        RateBarChart chart = new RateBarChart("Typer", "Rates", rateModels, CHART_RATE_COUNT_LIMIT, CHART_POSITION);
        chart.pack( );
        RefineryUtilities.centerFrameOnScreen( chart );
        chart.setVisible( true );
    }

    private List<RateModel> loadRateStats(){
        List<FootballMatch> footballMatchesWithResults =
                repo.findByResultIsNotNullAndRate1GreaterThanAndRate0GreaterThanAndRate2GreaterThan(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
        List<RateModel> rates = getEmptyRatesList();



        for (FootballMatch match : footballMatchesWithResults){
            BigDecimal rateL = match.getRate1().min(match.getRate2());
            BigDecimal rate0 = match.getRate0();
            BigDecimal rateG = match.getRate1().max(match.getRate2());
            RatePosition posL = getRateModel(rates, rateL).getPositionL();
            RatePosition pos0 = getRateModel(rates, match.getRate0()).getPosition0();
            RatePosition posG = getRateModel(rates, rateG).getPositionG();

            BigDecimal winnerRate = null;
            switch (match.getResult()){
                case("1"):{
                    winnerRate = match.getRate1();
                    break;
                }
                case("0"):{
                    winnerRate = match.getRate0();
                    break;
                }
                case("2"):{
                    winnerRate = match.getRate2();
                    break;
                }
            }

            if(winnerRate.equals(rateL)){
               posL.incrementWins();
               pos0.incrementLosings();
               posG.incrementLosings();
               posL.getMatchOpponentss().add(new MatchOpponents(rate0, rateG, 'L'));
               pos0.getMatchOpponentss().add(new MatchOpponents(rateL, rateG, 'L'));
               posG.getMatchOpponentss().add(new MatchOpponents(rateL, rate0, 'L'));
            }
            else if(winnerRate.equals(rate0)){
                posL.incrementLosings();
                pos0.incrementWins();
                posG.incrementLosings();
                posL.getMatchOpponentss().add(new MatchOpponents(rate0, rateG,  '0'));
                pos0.getMatchOpponentss().add(new MatchOpponents(rateL, rateG, '0'));
                posG.getMatchOpponentss().add(new MatchOpponents(rateL, rate0, '0'));
            }
            else if(winnerRate.equals(rateG)){
                posL.incrementLosings();
                pos0.incrementLosings();
                posG.incrementWins();
                posL.getMatchOpponentss().add(new MatchOpponents(rate0,rateG, 'G'));
                pos0.getMatchOpponentss().add(new MatchOpponents(rateL, rateG, 'G'));
                posG.getMatchOpponentss().add(new MatchOpponents(rateL, rate0, 'G'));
            }
        }
        return rates;
    }

    private List<RateModel> getEmptyRatesList(){
        List<RateModel> rates = new ArrayList<>();
        for(BigDecimal i=new BigDecimal(1);i.compareTo(new BigDecimal(22))!=1;i=i.add(new BigDecimal(RATE_RANGE))){
            rates.add(new RateModel(i));
        }
        return rates;
    }

    private RateModel getRateModel(List<RateModel> rates, BigDecimal rateVal){
        Integer chasedRateIndex = null;
        boolean found = false;
        for(int i=0; i<rates.size(); i++){
            if(!found && rateVal.compareTo(rates.get(i).getRateValue())!=-1 && rateVal.compareTo(rates.get(i+1).getRateValue())==-1){
                chasedRateIndex = i;
                found = true;
            }
        }
        return rates.get(chasedRateIndex);
    }

}
