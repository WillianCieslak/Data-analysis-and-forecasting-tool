package courseworkcomp1555;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public final class GraphApplication extends JFrame {

    private static final long serialVersionUID = 1L;
    public static XYSeriesCollection dataCollection;
    public static JFreeChart chart;
    private static XYLineAndShapeRenderer renderer;
    private static XYPlot plot;

    //default constructor of the class
    public GraphApplication() {
    }

    //create a dataCollection to add the series that will be plotted
    public static XYSeriesCollection createSeriesAndCollection(double[] valuesX, double[] valuesY) {
        dataCollection = new XYSeriesCollection();
        addXYValuesToSeries(valuesX, valuesY);
        createRegressionLine();
        return dataCollection;
    }

    //creates a series that will take the values from two arrays to plot the data from the training town
    public static void addXYValuesToSeries(double[] valuesX, double[] valuesY) {

        XYSeries valueSeries = new XYSeries("Training Data");

        for (int i = 0; i < valuesX.length; i++) {
            valueSeries.add(valuesX[i], valuesY[i]);
        }
        dataCollection.addSeries(valueSeries);
    }

    //This method creates a dataSeries which contains the 2 data points where the regression line will cross
    //The  point A(x,y), where x is the minimum value from the array that contains the X values, y is the minimum value of the estimates
    //The point B(x1,y1), where x1 is the maximum value from the array with the X values and y1 is the maximum value of the estimates.
    public static void createRegressionLine() {

        XYSeries regressionSeries = new XYSeries("Regression Line");
        MathOperations mO = new MathOperations();

        if (mO.Slope(CourseworkComp1555.variableValues, CourseworkComp1555.YValues) > 0) {
            regressionSeries.add(CommonCode.getMin(CourseworkComp1555.variableValues),//Eg Point A
                    CommonCode.getMin(mO.RegressionLine(CourseworkComp1555.variableValues, CourseworkComp1555.YValues)));

            regressionSeries.add(CommonCode.getMax(CourseworkComp1555.variableValues),//Eg Point B
                    CommonCode.getMax(mO.RegressionLine(CourseworkComp1555.variableValues, CourseworkComp1555.YValues)));

        } else {
            regressionSeries.add(CommonCode.getMax(CourseworkComp1555.variableValues),//Eg Point A
                    CommonCode.getMin(mO.RegressionLine(CourseworkComp1555.variableValues, CourseworkComp1555.YValues)));

            regressionSeries.add(CommonCode.getMin(CourseworkComp1555.variableValues),//Eg Point B
                    CommonCode.getMax(mO.RegressionLine(CourseworkComp1555.variableValues, CourseworkComp1555.YValues)));
        }
        dataCollection.addSeries(regressionSeries);
    }

    //creates the XY prediction series
    public static XYSeries createXIPrediction(double x, double y) {

        XYSeries xiPrediction = new XYSeries("Predicted value");
        xiPrediction.add(x, y);

        return xiPrediction;
    }

    //creates a series that will take the values from the comparison town and plot the data
    public static XYSeries createNewXYSeries(double[] valuesX, double[] valuesY) {

        XYSeries series = new XYSeries("Comparison Town");

        for (int i = 0; i < valuesX.length; i++) {
            series.add(valuesX[i], valuesY[i]);
        }
        return series;

    }

    //creates a plot given a chart 
    public static void createPlot(JFreeChart chart) {

        renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, false);
        renderer.setSeriesShapesVisible(0, true);
        renderer.setSeriesLinesVisible(1, true);
        renderer.setSeriesShapesVisible(1, false);
        renderer.setSeriesLinesVisible(2, false);
        renderer.setSeriesShapesVisible(2, true);

        plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(new Color(169, 172, 178));
        plot.setRenderer(renderer);
    }

}
