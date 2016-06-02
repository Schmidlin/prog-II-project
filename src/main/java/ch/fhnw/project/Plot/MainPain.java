package ch.fhnw.project.Plot;

import ch.fhnw.project.App;
import ch.fhnw.project.Variables;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

import static ch.fhnw.project.App.*;

/**
 * Created by thomasschmidlin on 04.05.16.
 */
public class MainPain {
    static HBox choicePain = new HBox();
    static ComboBox<String> cbXAxis;
    static ComboBox<String> cbYAxis;
    static VBox mainHBox = new VBox();
    static Pane scatterPane;
    static Pane histogram;



    public static Pane createMainPain(List<Variables> variableList) {

        cbXAxis = getChoiceBox(variableList);
        cbXAxis.setValue(variableList.get(0).getName());
        Label labelXAxis = new Label("x-Axis:");
        cbYAxis = getChoiceBox(variableList);
        cbYAxis.setValue(variableList.get(1).getName());
        Label labelYAxis = new Label("y-Axis:");

        //int xAxis = (int) cbXAxis.getSelectionModel().selectedIndexProperty().getValue();
        //int yAxis = (int) cbYAxis.getSelectionModel().selectedIndexProperty().getValue();

        //plotVariables.add(variableList.get(cbXAxis.getSelectionModel().selectedIndexProperty().getValue()));
        //plotVariables.add(variableList.get(cbYAxis.getSelectionModel().selectedIndexProperty().getValue()));


        cbXAxis.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                cbXAxis.setValue(variableList.get((int) newValue).getName());
                scatterPane = ScatterPlot.createScatterPane(variableList, (int) newValue, cbYAxis.getSelectionModel().getSelectedIndex());
                histogram = Histogram.createHistogram(variableList,(int) newValue,cbYAxis.getSelectionModel().getSelectedIndex());
            }
        });
        cbYAxis.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                cbYAxis.setValue(variableList.get((int)newValue).getName());
                scatterPane = ScatterPlot.createScatterPane(variableList, (int) cbXAxis.getSelectionModel().getSelectedIndex(), (int) newValue);
                histogram = Histogram.createHistogram(variableList,(int) cbXAxis.getSelectionModel().getSelectedIndex() ,(int) newValue);

            }
        });



        scatterPane = ScatterPlot.createScatterPane(variableList,(int) cbXAxis.getSelectionModel().selectedIndexProperty().getValue(),(int) cbYAxis.getSelectionModel().selectedIndexProperty().getValue());
        histogram = Histogram.createHistogram(variableList, (int) cbXAxis.getSelectionModel().selectedIndexProperty().getValue(), (int) cbYAxis.getSelectionModel().selectedIndexProperty().getValue());



        choicePain.getChildren().addAll(labelXAxis,cbXAxis,labelYAxis,cbYAxis);
        choicePain.setAlignment(Pos.CENTER);
        choicePain.setSpacing(10);
        choicePain.setStyle("-fx-background-color: yellow;");
        choicePain.setPadding(new Insets(5, 5, 5, 5));

        /*
        HBox histogramm = new HBox();
        histogramm.getChildren().addAll(Histogram.createHistogram(variableList));
        histogramm.setAlignment(Pos.CENTER);
        histogramm.setPadding(new Insets(5, 5, 5, 5));
        */


        mainHBox.getChildren().addAll(choicePain,scatterPane, histogram);
        mainHBox.setPadding(new Insets(5, 5, 5, 5));
        mainHBox.setSpacing(10);

        StackPane mainPain = new StackPane();
        mainPain.getChildren().addAll(mainHBox);
        return mainPain;


    }



    public static ComboBox<String> getChoiceBox(List<Variables> variableList) {
        ComboBox<String> choiceBox = new ComboBox<>();
        for(int i = 0; i <= variableList.size()-1; i++) {
            choiceBox.getItems().add(variableList.get(i).getName());

        }



        return choiceBox;
    }
}
