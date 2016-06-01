package ch.fhnw.project.Plot;

import ch.fhnw.project.App;
import ch.fhnw.project.Variables;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import sun.applet.Main;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static ch.fhnw.project.App.*;

/**
 * Created by thomasschmidlin on 04.05.16.
 */
public class ScatterPlot {
    static CheckBox timeLine = new CheckBox("Show Time Line");
    static Slider pointSizeSlider = new Slider(2,6,4);
    static Label pointSizeSliderLabel = new Label("Point Size: ");
    static ColorPicker cP = new ColorPicker(Color.BLUE);
    static Label sliderLabel = new Label("Change Point Size");
    static ComboBox<String> cbZAxis;
    static CheckBox bubblePlot = new CheckBox("Bubble-Plot");
    static Label labelZAxis = new Label ("z-Axis");




    public static Pane createScatterPane(List<Variables> variableList, int x, int y) {
        //x = xAxis;
        //y = yAxis;
        //System.out.println("Variable x, Scatterplot: "+ x);


        List<Variables> testList = new ArrayList<>();
        testList.add(variableList.get(x));
        testList.add(variableList.get(y));

        cbZAxis = MainPain.getChoiceBox(variableList);
        cbZAxis.setValue(testList.get(0).getName());
        cbZAxis.setDisable(!bubblePlot.isSelected());
        bubblePlot.setSelected(bubblePlot.isSelected());

        if (variableList.size()>2) {
            testList.add(variableList.get(cbZAxis.getSelectionModel().selectedIndexProperty().getValue()));
            cbZAxis.setValue(testList.get(2).getName());
            testList.add(variableList.get(cbZAxis.getSelectionModel().selectedIndexProperty().get()));
        }


        //create Widgets
;

        //create Pane
        ScatterChart<Number, Number> sc = getsc(testList,-1);
        sc.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent");
        if (bubblePlot.isSelected()){
            sc = getsc(testList,cbZAxis.getSelectionModel().selectedIndexProperty().getValue() );
            }

        LineChart<Number,Number> lc = getlc(testList);;
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

        pointSizeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            }
        });

        bubblePlot.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue){
                    //bubblePlot.setSelected(false);
                    cbZAxis.setDisable(!newValue);
                    //App.cleanup(variablesList,(int) cbXAxis.getSelectionModel().selectedIndexProperty().getValue(),(int) cbYAxis.getSelectionModel().selectedIndexProperty().getValue());
                }else{
                    //bubblePlot.setSelected(true);
                    cbZAxis.setDisable(!newValue);
                    //App.cleanup(variablesList,(int) cbXAxis.getSelectionModel().selectedIndexProperty().getValue(),(int) cbYAxis.getSelectionModel().selectedIndexProperty().getValue());
                }
            }
        });


        //style pane
        HBox firstLine = new HBox();
        firstLine.getChildren().addAll(pointSizeSliderLabel,pointSizeSlider);
        firstLine.setAlignment(Pos.CENTER);
        firstLine.setPadding(new Insets(5,5,5,5));

        HBox secondLine = new HBox();
        if (variableList.size()>2){
            secondLine.getChildren().addAll(labelZAxis,cbZAxis,bubblePlot);
        }
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

    private static List<Variables> getList(){ //remove?!

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

    private static ScatterChart<Number,Number> getsc(List<Variables> variablesList, int z){
        NumberAxis scXAxis = new NumberAxis ();
        NumberAxis scYAxis = new NumberAxis();
        List<Variables> testList = new ArrayList<>();
        testList.add(variablesList.get(0));
        testList.add(variablesList.get(1));
        scXAxis.setLabel(testList.get(0).getName());
        scYAxis.setLabel(testList.get(1).getName());
        scXAxis.setForceZeroInRange(false);
        scYAxis.setForceZeroInRange(false);
        ScatterChart<Number,Number> sc = new ScatterChart<>(scXAxis, scYAxis);

        List<Double> a,b;
        a = testList.get(0).getValues();
        b = testList.get(1).getValues();

        XYChart.Series data1 = new XYChart.Series();

        for (int i = 0; i < a.size(); i++) {
            XYChart.Data<Number, Number> point = new XYChart.Data<>(a.get(i),b.get(i));
            Circle circle = new Circle();
            circle.fillProperty().bind(cP.valueProperty());
            double x = 0;
            if (z != -1){
                circle.radiusProperty().bind(pointSizeSlider.valueProperty().multiply((variablesList.get(cbZAxis.getSelectionModel().selectedIndexProperty().getValue()).getValues().get(i))/x));
            }else{
                circle.radiusProperty().bind(pointSizeSlider.valueProperty());
            }
            point.setNode(circle);
            data1.getData().add(point);
        }
        sc.getData().add(data1);
        sc.setLegendVisible(false);

        return sc;
    }

    private static LineChart<Number,Number> getlc(List<Variables> testList){

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