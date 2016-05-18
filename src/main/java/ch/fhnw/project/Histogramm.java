package ch.fhnw.project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by thomasschmidlin on 06.05.16.
 */
public class Histogramm {

    public static Pane createHistogramm() {

        List<Variables> testList = getList();

        VBox left = new VBox();
        left.getChildren().addAll(leftBarChart(testList));
        left.setAlignment(Pos.CENTER);
        left.setSpacing(10);
        left.setPadding(new Insets(5,5,5,5));

        VBox right = new VBox();
        right.getChildren().addAll();
        right.setAlignment(Pos.CENTER);
        right.setSpacing(50);
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

    private static int[] histogramAllocation(List<Variables> testList){
        int interval = histogramIntervals(testList);
        int[] allocationArray = new int[interval];
        List<Double> variable = testList.get(0).getValues();
        Collections.sort(variable);
        double max = variable.get(variable.size()-1);
        double min = variable.get(0);
        double intervalWidth = (max - min) / interval;
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

        int[] allocationArray = histogramAllocation(testList);


        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String,Number> bc = new BarChart<>(xAxis,yAxis);

        XYChart.Series series1 = new XYChart.Series();
        series1.setName(testList.get(0).getName());
        for (int i = 0; i < allocationArray.length-1; i++) {
            XYChart.Data<String,Number> lines = new XYChart.Data<>(testList.get(0).getName(),allocationArray[i]);
            series1.getData().add(lines);
        }
        bc.getData().add(series1);
        return bc;
    }

    private static List<Variables> getList(){

        List<Variables> testList = new LinkedList<>();

        List<Double> a = new LinkedList<>();
        a.add(3.0);a.add(1.0);a.add(4.0);a.add(6.0);a.add(5.0);a.add(3.0);a.add(1.0);a.add(4.0);a.add(6.0);a.add(5.0);a.add(10.0);a.add(3.0);a.add(1.0);a.add(4.0);a.add(6.0);a.add(5.0);a.add(3.0);a.add(1.0);a.add(4.0);a.add(6.0);a.add(5.0);a.add(10.0);
        Variables var1 = new Variables("Variable1", a );

        List<Double> b = new LinkedList<>();
        b.add(4.0);b.add(2.0);b.add(7.0);b.add(5.0);b.add(3.0);
        Variables var2 = new Variables("Variable2", b );
        testList.add(var1);
        testList.add(var2);

        return testList;
    }


}
