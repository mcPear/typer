package maciek.typer.statistics;

import maciek.typer.graph.RateBarChart;
import maciek.typer.DataProcessor;
import maciek.typer.graph.RateNeighbourBarChart;
import maciek.typer.model.RateRange;
import org.apache.log4j.Logger;
import org.jfree.ui.RefineryUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by maciej on 21.07.17.
 */
@Component
public class RateStats implements CommandLineRunner{

    private Logger log = Logger.getLogger(RateStats.class.getName());

    @Autowired
    private DataProcessor dataProcessorImpl;

    private static final BigDecimal RANGE = new BigDecimal("0.1");
    private static final BigDecimal STOP_RATE = new BigDecimal("10.0");
    private static String NEIGHBOUR_STATUS = "small";
    private static final String RATES_STATUS = "draw";

    private static final BigDecimal CHOSEN_RATE = new BigDecimal("4.10");

    @Override
    public void run(String... strings) throws Exception {
        printRateData();
        printRateByNeighbourData();
    }

    private void printRateChart(List<RateRange> rateRanges){
        RateBarChart chartPro = new RateBarChart("Typer", "Rates",
                rateRanges, RATES_STATUS);
        chartPro.pack( );
        RefineryUtilities.centerFrameOnScreen( chartPro );
        chartPro.setVisible( true );
    }

    private void printRateData(){
        List<RateRange> rateRanges = getFilledRateRanges();
        rateRanges.forEach(rr -> System.out.println(rr.toStringPro()));
        printRateChart(rateRanges);
    }

    private void printRateByNeighbourData(){
        List<RateRange> rateRanges = dataProcessorImpl.getFilledByNeighbourRateRanges(
                new BigDecimal("0.1"),
                new BigDecimal("5.0"),
                CHOSEN_RATE,
                getFilledRateRanges(),
                NEIGHBOUR_STATUS);
        rateRanges.forEach(rr -> System.out.println(rr.toStringPro()));
        printRateByNeighbourChart(rateRanges);
    }

    private void printRateByNeighbourChart(List<RateRange> rateRanges){
        RateNeighbourBarChart chartPro = new RateNeighbourBarChart("Typer", "Rates by Neighbour",
                rateRanges, RATES_STATUS);
        chartPro.pack( );
        RefineryUtilities.centerFrameOnScreen( chartPro );
        chartPro.setVisible( true );
    }

    private List<RateRange> getFilledRateRanges(){
        return dataProcessorImpl.getFilledRateRanges(RANGE, STOP_RATE);
    }

}
