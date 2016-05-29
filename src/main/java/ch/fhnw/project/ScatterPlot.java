package ch.fhnw.project;

import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static ch.fhnw.project.App.xAxis;
import static ch.fhnw.project.App.yAxis;

/**
 * Created by thomasschmidlin on 04.05.16.
 */
public class ScatterPlot {

    public static Pane createScatterPane(List<Variables> variableList, IntegerProperty x, IntegerProperty y) {
        x = xAxis;
        y = yAxis;
        //System.out.println("Variable x, Scatterplot: "+ x);

        List<Variables> testList = new ArrayList<>();
        testList.add(variableList.get(x.getValue()));
        testList.add(variableList.get(y.getValue()));

        //create Widgets
        CheckBox timeLine = new CheckBox("Show Time Line");
        Slider pointSizeSlider = new Slider(1,2,5);
        Label pointSizeSliderLabel = new Label("Point Size: ");
        ColorPicker cP = new ColorPicker(Color.BLUE);
        Label sliderLabel = new Label("Change Point Size");

        //create Pane
        ScatterChart<Number,Number> sc = getsc(testList,pointSizeSlider,cP,timeLine);
        LineChart<Number,Number> lc = getlc(testList,timeLine,cP);
        sc.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent");
        lc.setVisible(false);
        //Checkbox commands
        timeLine.selectedProperty().addListener(new ChangeListener<Boolean>(){

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(oldValue){
                    lc.setVisible(false);
                }
                else {
                    lc.setVisible(true);
                }
            }
        });

        //style pane
        HBox firstLine = new HBox();
        firstLine.getChildren().addAll(pointSizeSliderLabel,pointSizeSlider);
        firstLine.setAlignment(Pos.CENTER);
        firstLine.setPadding(new Insets(5,5,5,5));

        HBox secondLine = new HBox();
        secondLine.getChildren().addAll(timeLine,cP);
        secondLine.setAlignment(Pos.CENTER);
        secondLine.setSpacing(10);
        secondLine.setPadding(new Insets(5,5,5,5));

        StackPane scatterPane = new StackPane();
        scatterPane.getChildren().addAll(lc,sc);
        scatterPane.setAlignment(Pos.CENTER);
        //scatterPane.setMaxWidth(600);

        VBox overAllBox =new VBox();
        overAllBox.getChildren().addAll(firstLine,secondLine,scatterPane);
        overAllBox.setAlignment(Pos.CENTER);
        overAllBox.setSpacing(10);
        overAllBox.setPadding(new Insets(5,5,5,5));

        return overAllBox;
    }

    private static List<Variables> getList(){

        List<Variables> testList = new LinkedList<>();

        List<Double> a = new LinkedList<>();
        a.add(3.0);a.add(1.0);a.add(4.0);a.add(6.0);a.add(5.0);
        Variables var1 = new Variables("Variable1", a );

        List<Double> b = new LinkedList<>();
        b.add(4.0);b.add(2.0);b.add(7.0);b.add(5.0);b.add(3.0);
        Variables var2 = new Variables("Variable2", b );
        testList.add(var1);
        testList.add(var2);

        return testList;
    }

    private static ScatterChart<Number,Number> getsc(List<Variables> testList,Slider pointSizeSlider,ColorPicker cP,CheckBox timeLine){
        NumberAxis xAxis = new NumberAxis ();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel(testList.get(0).getName());
        yAxis.setLabel(testList.get(1).getName());
        ScatterChart<Number,Number> sc = new ScatterChart<>(xAxis, yAxis);

        List<Double> a,b;
        a = testList.get(0).getValues();
        b = testList.get(1).getValues();

        XYChart.Series data1 = new XYChart.Series();

        for (int i = 0; i < a.size(); i++) {
            XYChart.Data<Number, Number> point = new XYChart.Data<>(a.get(i),b.get(i));
            Circle circle = new Circle();
            circle.fillProperty().bind(cP.valueProperty());
            circle.radiusProperty().bind(pointSizeSlider.valueProperty());
            point.setNode(circle);
            data1.getData().add(point);
        }
        sc.getData().add(data1);
        sc.setLegendVisible(false);

        return sc;
    }

    private static LineChart<Number,Number> getlc(List<Variables> testList,CheckBox timeLine,ColorPicker cP){

        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel(" ");
        yAxis.setLabel(" ");
        LineChart<Number, Number> lc = new LineChart<>(xAxis, yAxis);


        List<Double> a, b;
        a = testList.get(0).getValues();
        b = testList.get(1).getValues();
        XYChart.Series data1 = new XYChart.Series();
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


}