package maciek.typer.graph;

import maciek.typer.statistics.model.RateModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

import java.util.List;

/**
 * Created by maciej on 22.07.17.
 */
public class BarChart extends ApplicationFrame{

    public BarChart(String applicationTitle, String chartTitle, List<RateModel> rateModels, int rateCountLimit) {
        super(applicationTitle);
        JFreeChart barChart = ChartFactory.createBarChart(
                chartTitle,
                "PositionL",
                "Percent of wins",
                createDataset(rateModels, rateCountLimit),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        setContentPane(chartPanel);
    }

    private CategoryDataset createDataset(List<RateModel> rateModels, int rateCountLimit) {
        /*final String rateRow = "F";
        final String audi = "AUDI";
        final String ford = "FORD";
        final String columnRatePercent = "rate percent";
        final DefaultCategoryDataset dataset =
                new DefaultCategoryDataset();

        dataset.addValue(1.0, "1.0x", columnRatePercent);
        dataset.addValue(3.0, "1.1x", columnRatePercent);
        dataset.addValue(5.0, "1.2x", columnRatePercent);
        dataset.addValue(5.0, "1.3x", columnRatePercent);

        dataset.addValue(5.0, "1.4x", columnRatePercent);
        dataset.addValue(6.0, "1.5x", columnRatePercent);
        dataset.addValue(10.0, "1.6x", columnRatePercent);
        dataset.addValue(4.0, "1.7x",columnRatePercent);

        dataset.addValue(4.0, "1.8x", columnRatePercent);
        dataset.addValue(2.0, "1.9x", columnRatePercent);
        dataset.addValue(3.0, "2.0x", columnRatePercent);
        dataset.addValue(6.0, "2.1x", columnRatePercent);*/

        final String columnRatePercent = "less position set";
        final DefaultCategoryDataset dataset =
                new DefaultCategoryDataset();

        for(int i=0; i<rateCountLimit; i++){
            double percentOfWins = rateModels.get(i).getPositionL().getWinsPercent();
            String rateVal = rateModels.get(i).getRateValue().toString();
            dataset.addValue(percentOfWins, rateVal, columnRatePercent);
        }


        return dataset;
    }
}