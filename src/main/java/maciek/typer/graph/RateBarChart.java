package maciek.typer.graph;


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

    public RateBarChart(String applicationTitle, String chartTitle, List<RateRange> rateRanges, String status) {
        super(applicationTitle);
        JFreeChart barChart = ChartFactory.createBarChart(
                chartTitle,
                "Status " + status,
                "Percent of wins",
                createRateDataset(rateRanges, status),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        setContentPane(chartPanel);
    }

    private CategoryDataset createRateDataset(List<RateRange> rateRanges, String status) {

        final String columnRatePercent = "";
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        rateRanges.forEach(rr -> dataset.addValue(rr.getWinsPercent(status), rr.getPrimeRate(), columnRatePercent));

        return dataset;
    }

}