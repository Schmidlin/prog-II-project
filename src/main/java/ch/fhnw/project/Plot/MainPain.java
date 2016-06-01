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



    public static Pane createMainPain(List<Variables> variablesList, int x, int y) {


        List<Variables> plotVariables = new ArrayList<>();
        plotVariables.add(variablesList.get(x));
        plotVariables.add(variablesList.get(y));


        ComboBox<String> cbXAxis = getChoiceBox(variablesList);
        cbXAxis.setValue(plotVariables.get(0).getName());
        Label labelXAxis = new Label("x-Axis:");
        ComboBox<String> cbYAxis = getChoiceBox(variablesList);
        cbYAxis.setValue(plotVariables.get(1).getName());
        Label labelYAxis = new Label("y-Axis:");

        //int xAxis = (int) cbXAxis.getSelectionModel().selectedIndexProperty().getValue();
        //int yAxis = (int) cbYAxis.getSelectionModel().selectedIndexProperty().getValue();

        //plotVariables.add(variablesList.get(cbXAxis.getSelectionModel().selectedIndexProperty().getValue()));
        //plotVariables.add(variablesList.get(cbYAxis.getSelectionModel().selectedIndexProperty().getValue()));


        cbXAxis.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                //System.out.println("VariableName: "+plotVariables.get(0).getName());
                //System.out.println("newValue: "+newValue);
                App.cleanup(variablesList,(int) newValue,(int) cbYAxis.getSelectionModel().selectedIndexProperty().getValue());
            }
        });
        cbYAxis.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                //System.out.println("VariableName: "+plotVariables.get(0).getName());
                //System.out.println("newValue: "+newValue);
                App.cleanup(variablesList, (int) cbXAxis.getSelectionModel().selectedIndexProperty().getValue(), (int) newValue);


            }
        });







        Pane scatterPane = ScatterPlot.createScatterPane(variablesList,(int) cbXAxis.getSelectionModel().selectedIndexProperty().getValue(),(int) cbYAxis.getSelectionModel().selectedIndexProperty().getValue());

        HBox choicePain = new HBox();
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

        VBox mainHBox = new VBox();
        mainHBox.getChildren().addAll(choicePain,scatterPane, Histogram.createHistogram(variablesList, (int) cbXAxis.getSelectionModel().selectedIndexProperty().getValue(), (int) cbYAxis.getSelectionModel().selectedIndexProperty().getValue()));
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
