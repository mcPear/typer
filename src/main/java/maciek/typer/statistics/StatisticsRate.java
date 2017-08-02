package maciek.typer.statistics;

import maciek.typer.graph.RateBarChart;
import maciek.typer.DataProcessor;
import org.apache.log4j.Logger;
import org.jfree.ui.RefineryUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Created by maciej on 21.07.17.
 */
@Component
public class StatisticsRate implements CommandLineRunner{

    private Logger log = Logger.getLogger(StatisticsRate.class.getName());

    @Autowired
    private DataProcessor dataProcessorImpl;

    @Override
    public void run(String... strings) throws Exception {
        printRateChart();
    }

    private void printRateChart(){
        //TODO use new constrictor
        RateBarChart chartPro = new RateBarChart("Typer", "Rates",
                dataProcessorImpl.getFilledRateRanges(new BigDecimal("0.1"), new BigDecimal("5.0")), "small");
        chartPro.pack( );
        RefineryUtilities.centerFrameOnScreen( chartPro );
        chartPro.setVisible( true );
    }
}
