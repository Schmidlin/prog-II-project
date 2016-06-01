package ch.fhnw.project.Plot;

import ch.fhnw.project.Variables;
import javafx.beans.property.IntegerProperty;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ch.fhnw.project.App.xAxis;
import static ch.fhnw.project.App.yAxis;
import static java.lang.Math.round;


/**
 * Created by thomasschmidlin on 06.05.16.
 */
public class Histogram {

    public static Pane createHistogram(List<Variables> variableList, IntegerProperty x, IntegerProperty y) {

        x = xAxis;
        y = yAxis;

        List<Variables> testList = new ArrayList<>();
        testList.add(variableList.get(x.getValue()));
        testList.add(variableList.get(y.getValue()));


        VBox left = new VBox();
        left.getChildren().addAll(createBarChart(testList,0));
        left.setAlignment(Pos.CENTER);
        left.setSpacing(10);
        left.setPadding(new Insets(5,5,5,5));

        VBox right = new VBox();
        right.getChildren().addAll(createBarChart(testList,1));
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

    private static int histogramIntervals(List<Variables> testList, int listIndex){
        List<Double> a = testList.get(listIndex).getValues();
        double n = a.size();
        return (int) Math.sqrt(n);
    }

    private static double intervalWidth(List<Variables> testList, int listIndex){
        int interval = histogramIntervals(testList,listIndex);
        List<Double> variable = testList.get(listIndex).getValues();
        double max = Collections.max(variable);
        double min = Collections.min(variable);
        return (max - min) / interval; //returns intervalWidth;
    }

    private static int[] histogramAllocationArray(List<Variables> testList, int listIndex){
        int interval = histogramIntervals(testList,listIndex);
        int[] allocationArray = new int[interval];
        List<Double> variable = testList.get(listIndex).getValues();


        double min = Collections.min(variable);
        double intervalWidth = intervalWidth(testList,listIndex);
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


    private static BarChart createBarChart(List<Variables> testList, int listIndex) {

        int[] allocationArray = histogramAllocationArray(testList,listIndex);
        List<Double> variable = testList.get(listIndex).getValues();
        double intervalWidth = intervalWidth(testList,listIndex);

        CategoryAxis xAxisBC = new CategoryAxis();
        NumberAxis yAxisBC = new NumberAxis();
        xAxisBC.setTickLabelsVisible(false);
        xAxisBC.setOpacity(0);

        BarChart<String,Number> bc = new BarChart<>(xAxisBC,yAxisBC);

        XYChart.Series series1 = new XYChart.Series();
        series1.setName(testList.get(listIndex).getName());
        double a = Collections.min(variable);
        for (int i = 0; i < allocationArray.length; i++) {
            XYChart.Data<String,Number> lines = new XYChart.Data<String, Number>(String.format("%f - %f",a,a+intervalWidth),allocationArray[i]);
            //System.out.println("Line: "+lines);
            series1.getData().add(lines);
            a = a + intervalWidth;
        }
        bc.getData().add(series1);
        bc.setLegendVisible(false);
        bc.setTitle(testList.get(listIndex).getName());
        return bc;
    }
}
