package ch.fhnw.project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.converter.DoubleStringConverter;

import java.text.NumberFormat;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by thomasschmidlin on 06.05.16.
 */
public class Histogram {

    public static Pane createHistogram(List<Variables> variableList) {

        List<Variables> testList = variableList;

        VBox left = new VBox();
        left.getChildren().addAll(leftBarChart(testList));
        left.setAlignment(Pos.CENTER);
        left.setSpacing(10);
        left.setPadding(new Insets(5,5,5,5));

        VBox right = new VBox(rightBarChart(testList));
        right.getChildren().addAll();
        right.setAlignment(Pos.CENTER);
        right.setSpacing(10);
        right.setPadding(new Insets(5,5,5,5));

        HBox HBox = new HBox();
        HBox.getChildren().addAll(left,right);
        HBox.setAlignment(Pos.CENTER);
        HBox.setSpacing(20);
        HBox.setPadding(new Insets(5,5,5,5));

        StackPane pane = new StackPane();
        pane.getChildren().add(HBox);

        return pane;
    }

    private static int histogramIntervals(List<Variables> testList){
        List<Double> a = testList.get(0).getValues();
        double n = a.size();
        return (int) Math.sqrt(n);
    }

    private static double intervalWidth(List<Variables> testList){
        int interval = histogramIntervals(testList);
        List<Double> variable = testList.get(0).getValues();
        double max = variable.get(variable.size()-1);
        double min = variable.get(0);
        return (max - min) / interval; //returns intervalWidth;
    }

    private static int[] histogramAllocationArray(List<Variables> testList){
        int interval = histogramIntervals(testList);
        int[] allocationArray = new int[interval];
        List<Double> variable = testList.get(0).getValues();

        Collections.sort(variable);
        double min = variable.get(0);
        double intervalWidth = intervalWidth(testList);
        for (Double element : variable){
            for (int i =0; i<interval; i++){
                if(i*intervalWidth+min<element&&(i+1)*intervalWidth+min >= element){
                    allocationArray[i] = allocationArray[i]+1;
                }
            }
            if(element == min){
                allocationArray[0] = allocationArray[0]+1;
            }
        }
        return allocationArray;
    }


    private static BarChart leftBarChart(List<Variables> testList) {

        int[] allocationArray = histogramAllocationArray(testList);
        List<Double> variable = testList.get(0).getValues();
        double intervalWidth = intervalWidth(testList);

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setTickLabelsVisible(false);
        xAxis.setOpacity(0);

        BarChart<String,Number> bc = new BarChart<>(xAxis,yAxis);

        XYChart.Series series1 = new XYChart.Series();
        series1.setName(testList.get(0).getName());
        double a = variable.get(0);
        for (int i = 0; i < allocationArray.length; i++) {
            XYChart.Data<String,Number> lines = new XYChart.Data<String, Number>(String.format("%s - %s",a,a+intervalWidth),allocationArray[i]);
            series1.getData().add(lines);
            a = a + intervalWidth;
        }
        bc.getData().add(series1);
        bc.setLegendVisible(false);
        bc.setTitle(testList.get(0).getName());
        return bc;
    }
    private static BarChart rightBarChart(List<Variables> testList) {

        int[] allocationArray = histogramAllocationArray(testList);
        List<Double> variable = testList.get(1).getValues();
        double intervalWidth = intervalWidth(testList);

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setTickLabelsVisible(false);
        xAxis.setOpacity(0);

        BarChart<String,Number> bc = new BarChart<>(xAxis,yAxis);

        XYChart.Series series1 = new XYChart.Series();
        series1.setName(testList.get(1).getName());
        double a = variable.get(0);
        for (int i = 0; i < allocationArray.length; i++) {
            XYChart.Data<String,Number> lines = new XYChart.Data<String, Number>(String.format("%s - %s",a,a+intervalWidth),allocationArray[i]);
            series1.getData().add(lines);
            a = a + intervalWidth;
        }
        bc.getData().add(series1);
        bc.setLegendVisible(false);
        bc.setTitle(testList.get(1).getName());
        return bc;
    }
}
