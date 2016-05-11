package ch.fhnw.project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.List;

/**
 * Created by thomasschmidlin on 04.05.16.
 */
public class ScatterPlot {

    public static Pane createScatterPane() {

        //create Widgets
        CheckBox timeLine = new CheckBox("Show Time Line");
        Slider pointSizeSlider = new Slider(5,15,15);
        ColorPicker cP = new ColorPicker(Color.BLUE);
        Label sliderLabel = new Label("Change Point Size");

        NumberAxis xAxis = new NumberAxis ();
        NumberAxis yAxis = new NumberAxis();
        ScatterChart<Number,Number> sc = new ScatterChart<>(xAxis,yAxis);
        LineChart<Number,Number> lc = new LineChart<>(xAxis,yAxis);

        double[] a = {2,3,4};
        double[] b = {1,3,4};

        XYChart.Series data1 = new XYChart.Series();
        for (int i = 0; i < a.length; i++) {
            XYChart.Data<Number, Number> point = new XYChart.Data<>(a[i],b[i]);
            Circle circle = new Circle();
            circle.fillProperty().bind(cP.valueProperty());
            circle.radiusProperty().bind(pointSizeSlider.valueProperty());
            point.setNode(circle);
            data1.getData().add(point);
        }
        sc.getData().add(data1);
        lc.getData().add(data1);
        lc.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent");

        /*VBox rigthHbox = new VBox();
        rigthHbox.setAlignment(Pos.CENTER);
        rigthHbox.setSpacing(15);
        rigthHbox.getChildren().addAll(timeLine,cP,sliderLabel,pointSizeSlider);

        VBox leftHbox = new VBox();
        leftHbox.setAlignment(Pos.CENTER);
        leftHbox.getChildren().addAll(sc,lc);

        HBox hBox = new HBox();
        hBox.getChildren().addAll(leftHbox,rigthHbox);

        StackPane scatterPane = new StackPane();
        scatterPane.getChildren().add(hBox);*/


        GridPane scatterPane = new GridPane();
        scatterPane.setHgap(7);
        scatterPane.setVgap(7);
        scatterPane.setPadding(new Insets(10,10,10,10));
        scatterPane.setAlignment(Pos.CENTER);
        scatterPane.add(pointSizeSlider,1,0);
        scatterPane.add(timeLine,1,1);
        scatterPane.add(cP,2,1);
        scatterPane.add(sc, 1,3);
        scatterPane.add(lc, 1,3);

        return scatterPane;

    }
}
