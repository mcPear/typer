package maciek.typer.statistics;

import maciek.typer.graph.BarChart;
import maciek.typer.model.FootballMatch;
import maciek.typer.repository.FootballMatchRepository;
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
public class Statistics implements CommandLineRunner{

    private Logger log = Logger.getLogger(Statistics.class.getName());

    @Autowired
    private FootballMatchRepository repo;

    @Override
    public void run(String... strings) throws Exception {
        //printRates();
        printChart();
    }

    private void printRates(){
        List<RateModel> rateStats = loadRateStats();
        rateStats.forEach(rate -> System.out.println(rate));

    }

    private void printChart(){
        BarChart chart = new BarChart("Typer", "Rates", loadRateStats());
        chart.pack( );
        RefineryUtilities.centerFrameOnScreen( chart );
        chart.setVisible( true );
    }

    private List<RateModel> loadRateStats(){
        List<FootballMatch> footballMatchesWithResults = repo.findByResultIsNotNull();
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
            }
            else if(winnerRate.equals(rate0)){
                posL.incrementLosings();
                pos0.incrementWins();
                posG.incrementLosings();
            }
            else if(winnerRate.equals(rateG)){
                posL.incrementLosings();
                pos0.incrementLosings();
                posG.incrementWins();
            }
        }
        return rates;
    }

    private List<RateModel> getEmptyRatesList(){
        List<RateModel> rates = new ArrayList<>();
        for(BigDecimal i=new BigDecimal(1);i.compareTo(new BigDecimal(22))!=1;i=i.add(new BigDecimal("0.1"))){
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
