package ch.fhnw.project;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * Created by thomasschmidlin on 04.05.16.
 */
public class ScatterPlot {

    public static Pane getScatterPane(Slider slider) {

        NumberAxis xAxis = new NumberAxis (0, 10, 1);
        NumberAxis yAxis = new NumberAxis(0, 10, 1);
        ScatterChart<Number,Number> sc = new ScatterChart<>(xAxis,yAxis);

        double[] a = {2,3,4};
        double[] b = {1,3,4};

        XYChart.Series data1 = new XYChart.Series();
        for (int i = 0; i < a.length; i++) {
            XYChart.Data<Number, Number> point = new XYChart.Data<>(a[i],b[i]);
            point.setNode(new Circle(slider.getValue()));
            data1.getData().add(point);
        }

        sc.getData().add(data1);
        Pane scatterPane = new StackPane();
        scatterPane.getChildren().add(sc);

        return scatterPane;

    }

}
