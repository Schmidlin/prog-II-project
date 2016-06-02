package ch.fhnw.project.Plot;

import ch.fhnw.project.Variables;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by thomasschmidlin on 04.05.16.
 * Create ScatterPlot
 */
class ScatterPlot {
    private static CheckBox timeLine = new CheckBox("Show Time Line");
    private static Slider pointSizeSlider = new Slider(2, 6, 4);
    private static Label pointSizeSliderLabel = new Label("Point Size: ");
    private static ColorPicker cP = new ColorPicker(Color.BLUE);
    private static ComboBox<String> cbZAxis;
    private static CheckBox bubblePlot = new CheckBox("Bubble-Plot");
    private static Label labelZAxis = new Label("z-Axis");
    private static HBox firstLine = new HBox();
    private static HBox secondLine = new HBox();
    private static StackPane scatterPane = new StackPane();
    private static VBox overAllBox = new VBox();
    private static ScatterChart<Number, Number> sc;
    private static LineChart<Number, Number> lc;


    static Pane createScatterPane(List<Variables> variableList, int x, int y) {

        sc = getsc(variableList, x, y);
        lc = getlc(variableList,x,y);
        cbZAxis = getChoiceBox(variableList);
        cbZAxis.setDisable(!bubblePlot.isSelected());

        if (cbZAxis.getValue() == null) {cbZAxis.setValue(variableList.get(x).getName());}

        if (variableList.size() > 2) {cbZAxis.setValue(variableList.get(2).getName());}

        if (!timeLine.isSelected()) {lc.setVisible(false);}

        //Listener
        timeLine.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue) {
                lc.setVisible(false);
            } else {
                lc.setVisible(true);
            }
        });

        bubblePlot.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                cbZAxis.setDisable(false);
                sc = getsc(variableList, x, y);
                scatterPane.getChildren().clear();
                scatterPane.getChildren().addAll(lc, sc);
                scatterPane.setAlignment(Pos.CENTER);
            } else {
                cbZAxis.setDisable(!newValue);
                sc = getsc(variableList, x, y);
                scatterPane.getChildren().clear();
                scatterPane.getChildren().addAll(lc, sc);
                scatterPane.setAlignment(Pos.CENTER);
            }
        });

        cbZAxis.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            sc = getsc(variableList, x, y);
            scatterPane.getChildren().clear();
            scatterPane.getChildren().addAll(lc, sc);
            scatterPane.setAlignment(Pos.CENTER);

        });


        //style pane
        firstLine.getChildren().clear();
        firstLine.getChildren().addAll(pointSizeSliderLabel, pointSizeSlider);
        firstLine.setAlignment(Pos.CENTER);
        firstLine.setPadding(new Insets(5, 5, 5, 5));

        secondLine.getChildren().clear();
        if (variableList.size() > 2) {
            secondLine.getChildren().addAll(labelZAxis, cbZAxis, bubblePlot);
        }
        secondLine.getChildren().addAll(timeLine, cP);
        secondLine.setAlignment(Pos.CENTER);
        secondLine.setSpacing(10);
        secondLine.setPadding(new Insets(5, 5, 5, 5));

        scatterPane.getChildren().clear();
        scatterPane.getChildren().addAll(lc, sc);
        scatterPane.setAlignment(Pos.CENTER);

        overAllBox.getChildren().clear();
        overAllBox.getChildren().addAll(firstLine, secondLine, scatterPane);
        overAllBox.setAlignment(Pos.CENTER);
        overAllBox.setSpacing(10);
        overAllBox.setPadding(new Insets(5, 5, 5, 5));

        return overAllBox;
    }

    private static ScatterChart<Number, Number> getsc(List<Variables> variableList, int x, int y) {
        NumberAxis scXAxis = new NumberAxis();
        NumberAxis scYAxis = new NumberAxis();
        scXAxis.setLabel(variableList.get(x).getName());
        scYAxis.setLabel(variableList.get(y).getName());
        scXAxis.setForceZeroInRange(false);
        scYAxis.setForceZeroInRange(false);
        ScatterChart<Number, Number> sc = new ScatterChart<>(scXAxis, scYAxis);

        List<Double> a, b;
        a = variableList.get(x).getValues();
        b = variableList.get(y).getValues();

        XYChart.Series<Number,Number> data1 = new XYChart.Series<>();

        for (int i = 0; i < a.size(); i++) {
            XYChart.Data<Number, Number> point = new XYChart.Data<>(a.get(i), b.get(i));
            Circle circle = new Circle();
            circle.fillProperty().bind(cP.valueProperty());

            if (bubblePlot.isSelected()) {
                Double r = Collections.max(variableList.get(cbZAxis.getSelectionModel().selectedIndexProperty().getValue()).getValues())-Collections.min(variableList.get(cbZAxis.getSelectionModel().selectedIndexProperty().getValue()).getValues());
                if(r<1.0){r=1.0;}
                circle.radiusProperty().bind(pointSizeSlider.valueProperty().multiply((variableList.get(cbZAxis.getSelectionModel().selectedIndexProperty().getValue()).getValues().get(i)) / r));
            } else {
                circle.radiusProperty().bind(pointSizeSlider.valueProperty());
            }
            point.setNode(circle);
            data1.getData().add(point);
        }
        sc.getData().add(data1);
        sc.setLegendVisible(false);
        sc.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent");

        return sc;
    }

    private static LineChart<Number, Number> getlc(List<Variables> variableList, int x, int y) {

        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setForceZeroInRange(false);
        yAxis.setForceZeroInRange(false);
        xAxis.setLabel(" ");
        yAxis.setLabel(" ");
        LineChart<Number, Number> lc = new LineChart<>(xAxis, yAxis);

        List<Double> a, b;
        a = variableList.get(x).getValues();
        b = variableList.get(y).getValues();
        XYChart.Series<Number,Number> data1 = new XYChart.Series<>();

        for (int i = 0; i < a.size(); i++) {
            XYChart.Data<Number, Number> lines = new XYChart.Data<>(a.get(i), b.get(i));
            data1.getData().add(lines);
        }
        lc.getData().add(data1);
        lc.setCreateSymbols(false);
        lc.setAxisSortingPolicy(LineChart.SortingPolicy.NONE);
        lc.setHorizontalGridLinesVisible(false);
        lc.setVerticalGridLinesVisible(false);
        lc.setLegendVisible(false);
        return lc;
    }

    private static ComboBox<String> getChoiceBox(List<Variables> variableList) {
        ComboBox<String> choiceBox = new ComboBox<>();
        for (int i = 0; i <= variableList.size() - 1; i++) {
            choiceBox.getItems().add(variableList.get(i).getName());

        }
        return choiceBox;
    }


}