package ch.fhnw.project;

import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;

/**
 * Created by thomasschmidlin on 04.05.16.
 */
public class ScatterPlot {

    public Pane getScatterPlot(double dataA,double dataB){


        NumberAxis xAxis = new NumberAxis (0, 10, 1);
        NumberAxis yAxis = new NumberAxis(0, 100, 1);
        ScatterChart<Number,Number> sc = new ScatterChart<>(xAxis,yAxis);

        double[] a = {2,3,4};
        double[] b = {1,3,4};

        XYChart.Series data1 = new XYChart.Series();
        for (int i = 0; i < a.length; i++) {
            data1.getData().add(new XYChart.Data(a[i],b[i]));
        }

        sc.getData().add(data1);
        Pane scatterPane = new Pane();
        scatterPane.getChildren().add(sc);
        return scatterPane;
    }

}
