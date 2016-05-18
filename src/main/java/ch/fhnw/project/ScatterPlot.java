package ch.fhnw.project;

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

import java.util.LinkedList;
import java.util.List;

/**
 * Created by thomasschmidlin on 04.05.16.
 */
public class ScatterPlot {

    public static Pane createScatterPane(List<Variables> variableList) {

        List<Variables> testList = variableList;

        //create Widgets
        CheckBox timeLine = new CheckBox("Show Time Line");
        timeLine.setSelected(false);
        Slider pointSizeSlider = new Slider(1,2,5);
        ColorPicker cP = new ColorPicker(Color.BLUE);
        Label sliderLabel = new Label("Change Point Size");

        //create Pane
        GridPane scatterPane = new GridPane();
        ScatterChart<Number,Number> sc = getsc(testList,pointSizeSlider,cP,timeLine);
        LineChart<Number,Number> lc = getlc(testList,timeLine,cP);

        //Checkbox commands
        timeLine.selectedProperty().addListener(new ChangeListener<Boolean>(){

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(oldValue){
                    lc.setVisible(false);
                }
                else {
                    lc.setVisible(true);
                    sc.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent");
                }
            }
        });

        //style pane
        scatterPane.setHgap(7);
        scatterPane.setVgap(7);
        scatterPane.setPadding(new Insets(10,10,10,10));
        scatterPane.setAlignment(Pos.CENTER);
        scatterPane.add(pointSizeSlider,1,0,1,1);
        scatterPane.add(timeLine,1,1);
        scatterPane.add(cP,2,1);
        scatterPane.add(lc, 1,3);
        scatterPane.add(sc, 1,3);

        return scatterPane;
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