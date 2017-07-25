package maciek.typer.statistics;

import maciek.typer.graph.OpponentBarChart;
import maciek.typer.graph.RateBarChart;
import maciek.typer.model.FootballMatch;
import maciek.typer.statistics.model.MatchOpponents;
import maciek.typer.statistics.model.OpponentModel;
import maciek.typer.statistics.model.RateModel;
import maciek.typer.statistics.model.RatePosition;
import org.jfree.ui.RefineryUtilities;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by maciej on 22.07.17.
 */
@Component
public class StatisticsOpponent {

    private final static String OPP_RATE_RANGE = "0.05";
    private final static int CHART_RATE_COUNT_LIMIT = 40;
    private final static char CHART_POSITION = 'L';
    private final static char CHART_OPPONENT = 'L';

    public void printChart(RatePosition ratePosition){
        OpponentBarChart chart = new OpponentBarChart("Typer", "Rates", loadRateStats(ratePosition), CHART_RATE_COUNT_LIMIT, CHART_POSITION);
        chart.pack( );
        RefineryUtilities.centerFrameOnScreen( chart );
        chart.setVisible( true );
    }

    private List<OpponentModel> loadRateStats(RatePosition ratePosition) {
        List<OpponentModel> opponentModels = getEmptyOpponentsList();

        char basePosition = ratePosition.getPosition();

        for (MatchOpponents matchOpponent : ratePosition.getMatchOpponentss()) {

            OpponentModel opp;

            switch (basePosition) {
                case ('L'): {
                    if (CHART_OPPONENT == '0') {
                        opp = getOpponent(opponentModels, matchOpponent.getOpponentA());
                    } else if (CHART_OPPONENT == 'G') {
                        opp = getOpponent(opponentModels, matchOpponent.getOpponentB());
                    } else throw new IllegalArgumentException();

                    if (matchOpponent.getResult() == 'L') {
                        opp.incrementLosings();
                    } else {
                        opp.incrementWins();
                    }

                }
                case ('0'): {
                    if (CHART_OPPONENT == 'L') {
                        opp = getOpponent(opponentModels, matchOpponent.getOpponentA());
                    } else if (CHART_OPPONENT == 'G') {
                        opp = getOpponent(opponentModels, matchOpponent.getOpponentB());
                    } else throw new IllegalArgumentException();

                    if (matchOpponent.getResult() == '0') {
                        opp.incrementLosings();
                    } else {
                        opp.incrementWins();
                    }
                }
                case ('G'): {
                    if (CHART_OPPONENT == 'L') {
                        opp = getOpponent(opponentModels, matchOpponent.getOpponentA());
                    } else if (CHART_OPPONENT == '0') {
                        opp = getOpponent(opponentModels, matchOpponent.getOpponentB());
                    } else throw new IllegalArgumentException();

                    if (matchOpponent.getResult() == 'G') {
                        opp.incrementLosings();
                    } else {
                        opp.incrementWins();
                    }
                }

            }

        }
        return opponentModels;
    }

    private List<OpponentModel> getEmptyOpponentsList(){
        List<OpponentModel> opponents = new ArrayList<>();
        for(BigDecimal i=new BigDecimal(1);i.compareTo(new BigDecimal(22))!=1;i=i.add(new BigDecimal(OPP_RATE_RANGE))){
            opponents.add(new OpponentModel(CHART_OPPONENT, i));
        }
        return opponents;
    }

    private OpponentModel getOpponent(List<OpponentModel> opponents, BigDecimal rateVal){
        Integer chasedRateIndex = null;
        boolean found = false;
        for(int i=0; i<opponents.size(); i++){
            if(!found && rateVal.compareTo(opponents.get(i).getRate())!=-1 && rateVal.compareTo(opponents.get(i+1).getRate())==-1){
                chasedRateIndex = i;
                found = true;
            }
        }
        return opponents.get(chasedRateIndex);
    }

}
