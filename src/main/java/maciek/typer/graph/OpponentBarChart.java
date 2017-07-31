package maciek.typer.graph;


import maciek.typer.statistics.model.OpponentModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

import java.util.List;

/**
 * Created by maciej on 23.07.17.
 */
public class OpponentBarChart extends ApplicationFrame {

    public OpponentBarChart(String applicationTitle, String chartTitle,List<OpponentModel> opponentModels, int rateCountLimit, char position) {
        super(applicationTitle);
        JFreeChart barChart = ChartFactory.createBarChart(
                chartTitle,
                "Position" + position,
                "Percent of wins",
                createDataset(opponentModels, rateCountLimit, position),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        setContentPane(chartPanel);
    }

    private CategoryDataset createDataset(List<OpponentModel> opponentModels, int rateCountLimit, char position) {

        final String columnRatePercent = "";
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        opponentModels.forEach(om -> dataset.addValue(om.getWinsPercent(), om.getRate(), columnRatePercent));

        return dataset;
    }
}