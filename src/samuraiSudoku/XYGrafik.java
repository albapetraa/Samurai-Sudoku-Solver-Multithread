/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package samuraiSudoku;

import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Emre
 */
public class XYGrafik extends JFrame {

    public XYGrafik(String title, double s1, double s2, int d1, int d2) {
        super(title);

        XYDataset dataset = createDataset(s1, s2, d1, d2);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "XY Grafiği",
                "X-Axis",
                "Y-Axis",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    private XYDataset createDataset(double s1, double s2, int d1, int d2) {
        XYSeriesCollection dataset = new XYSeriesCollection();

        XYSeries series = new XYSeries("5 THREAD");
        XYSeries series2 = new XYSeries("10 THREAD");

        series.add(s1 - s1, d1 - d1);
        series.add(s1 - 0.70, d1 - 80);
        series.add(s1 - 0.60, d1 - 70);
        series.add(s1 - 0.50, d1 - 60);
        series.add(s1 - 0.40, d1 - 50);
        series.add(s1 - 0.30, d1 - 40);
        series.add(s1 - 0.30, d1 - 30);
        series.add(s1 - 0.20, d1 - 20);
        series.add(s1 - 0.10, d1 - 10);

        series.add(s1, d1);

        series2.add(s2 - s2, d2 - d2);
        series2.add(s2 - 0.70, d2 - 80);
        series2.add(s2 - 0.60, d2 - 70);
        series2.add(s2 - 0.50, d2 - 60);
        series2.add(s2 - 0.40, d2 - 50);
        series2.add(s2 - 0.40, d2 - 40);
        series2.add(s2 - 0.30, d2 - 30);
        series2.add(s2 - 0.20, d2 - 20);
        series2.add(s2 - 0.10, d2 - 10);

        series2.add(s2, d2);

        dataset.addSeries(series);
        dataset.addSeries(series2);

        return dataset;
    }
}
