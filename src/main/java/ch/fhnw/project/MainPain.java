package ch.fhnw.project;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import static ch.fhnw.project.App.*;

/**
 * Created by thomasschmidlin on 04.05.16.
 */
public class MainPain {

    public static Pane createMainPain(List<Variables> variablesList, IntegerProperty x, IntegerProperty y) {

        x = xAxis;
        y = yAxis;

        List<Variables> plotVariables = new ArrayList<>();
        plotVariables.add(variablesList.get(x.getValue()));
        plotVariables.add(variablesList.get(y.getValue()));


        ComboBox<String> cbXAxis = getChoiceBox(variablesList);
        cbXAxis.setValue(plotVariables.get(0).getName());
        Label labelXAxis = new Label("x-Axis:");
        ComboBox<String> cbYAxis = getChoiceBox(variablesList);
        cbYAxis.setValue(plotVariables.get(1).getName());
        Label labelYAxis = new Label("y-Axis:");
        ComboBox<String> cbZAxis = getChoiceBox(variablesList);
        Label labelZAxis = new Label ("z-Axis");
        cbZAxis.setValue(plotVariables.get(0).getName());
        CheckBox bubblePlot = new CheckBox("Bubble-Plot");
        cbZAxis.setDisable(bubblePlotCheckbox.getValue());
        bubblePlot.setSelected(!bubblePlotCheckbox.getValue());
        if (variablesList.size()>2){
            plotVariables.add(variablesList.get(zAxis.getValue()));
            cbZAxis.setValue(plotVariables.get(2).getName());
        }


        //plotVariables.add(variablesList.get(cbXAxis.getSelectionModel().selectedIndexProperty().getValue()));
        //plotVariables.add(variablesList.get(cbYAxis.getSelectionModel().selectedIndexProperty().getValue()));


        cbXAxis.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                xAxis.setValue(newValue);
                //System.out.println("VariableName: "+plotVariables.get(0).getName());
                //System.out.println("newValue: "+newValue);
                App.cleanup(variablesList,xAxis,yAxis);
            }
        });
        cbYAxis.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                yAxis.setValue(newValue);
                //System.out.println("VariableName: "+plotVariables.get(0).getName());
                //System.out.println("newValue: "+newValue);
                App.cleanup(variablesList, xAxis, yAxis);


            }
        });

        cbZAxis.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                zAxis.setValue(newValue);
                App.cleanup(variablesList,xAxis,yAxis);
            }
        });

        bubblePlot.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue){
                    bubblePlotCheckbox.setValue(false);
                    cbZAxis.setDisable(bubblePlotCheckbox.getValue());
                    App.cleanup(variablesList,xAxis,yAxis);
                }else{
                    bubblePlotCheckbox.setValue(true);
                    cbZAxis.setDisable(bubblePlotCheckbox.getValue());
                    App.cleanup(variablesList,xAxis,yAxis);
                }
            }
        });



        Pane scatterPane = ScatterPlot.createScatterPane(variablesList,x,y);

        HBox choicePain = new HBox();
        choicePain.getChildren().addAll(labelXAxis,cbXAxis,labelYAxis,cbYAxis);
        if (variablesList.size()>2){
            choicePain.getChildren().addAll(labelZAxis,cbZAxis,bubblePlot);
        }
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

        VBox mainHBox = new VBox();
        mainHBox.getChildren().addAll(choicePain,scatterPane, Histogram.createHistogram(variablesList, xAxis, yAxis));
        mainHBox.setPadding(new Insets(5, 5, 5, 5));
        mainHBox.setSpacing(10);

        StackPane mainPain = new StackPane();
        mainPain.getChildren().addAll(mainHBox);
        return mainPain;


    }



    private static ComboBox<String> getChoiceBox(List<Variables> variableList) {
        ComboBox<String> choiceBox = new ComboBox<>();
        for(int i = 0; i <= variableList.size()-1; i++) {
            choiceBox.getItems().add(variableList.get(i).getName());

        }



        return choiceBox;
    }
}
