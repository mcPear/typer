package maciek.typer.graph;


import maciek.typer.statistics.model.RateModel;
import maciek.typer.model.RateRange;
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
public class RateBarChart extends ApplicationFrame {

    public RateBarChart(String applicationTitle, String chartTitle, List<RateModel> rateModels, int rateCountLimit, char position) {
        super(applicationTitle);
        JFreeChart barChart = ChartFactory.createBarChart(
                chartTitle,
                "Position" + position,
                "Percent of wins",
                createDataset(rateModels, rateCountLimit, position),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        setContentPane(chartPanel);
    }

    public RateBarChart(String applicationTitle, String chartTitle, List<RateRange> rateRanges, String status) {
        super(applicationTitle);
        JFreeChart barChart = ChartFactory.createBarChart(
                chartTitle,
                "Status " + status,
                "Percent of wins",
                createDatasetPro(rateRanges, status),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        setContentPane(chartPanel);
    }

    private CategoryDataset createDataset(List<RateModel> rateModels, int rateCountLimit, char position) {

        final String columnRatePercent = "";
        final DefaultCategoryDataset dataset =
                new DefaultCategoryDataset();

        for (int i = 0; i < rateCountLimit; i++) {
            double percentOfWins = 0;
            switch (position) {
                case ('L'): {
                    percentOfWins = rateModels.get(i).getPositionL().getWinsPercent();
                    break;
                }
                case ('0'): {
                    percentOfWins = rateModels.get(i).getPosition0().getWinsPercent();
                    break;
                }
                case ('G'): {
                    percentOfWins = rateModels.get(i).getPositionG().getWinsPercent();
                    break;
                }
            }
            String rateVal = rateModels.get(i).getRateValue().toString();
            dataset.addValue(percentOfWins, rateVal, columnRatePercent);
        }


        return dataset;
    }

    private CategoryDataset createDatasetPro(List<RateRange> rateRanges, String status) {

        final String columnRatePercent = "";
        final DefaultCategoryDataset dataset =
                new DefaultCategoryDataset();

        rateRanges.forEach(rr -> dataset.addValue(rr.getWinsPercent(status), rr.getPrimeRate(), columnRatePercent));

        return dataset;
    }

}