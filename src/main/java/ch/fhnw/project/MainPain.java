package ch.fhnw.project;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thomasschmidlin on 04.05.16.
 */
public class MainPain {

    public static Pane createMainPain(List<Variables> variablesList) {

        List<Variables> plotVariables = new ArrayList<>();

        Variables variable = variablesList.get(0);

        ComboBox<String> cbXAxis = getChoiceBox(variablesList);
        cbXAxis.setValue(variablesList.get(0).getName());
        Label labelXAxis = new Label("x-Axis:");
        ComboBox<String> cbYAxis = getChoiceBox(variablesList);
        cbYAxis.setValue(variablesList.get(1).getName());
        Label labelYAxis = new Label("y-Axis:");
        plotVariables.add(variablesList.get(cbXAxis.getSelectionModel().selectedIndexProperty().getValue()));
        plotVariables.add(variablesList.get(cbYAxis.getSelectionModel().selectedIndexProperty().getValue()));


        cbXAxis.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                plotVariables.set(0,variablesList.get((int)(newValue)));
                System.out.print("VariableName: "+plotVariables.get(0).getName());
                ScatterPlot.createScatterPane(plotVariables.get(0),plotVariables.get(1),variablesList);

            }
        });

        Pane scatterPane = ScatterPlot.createScatterPane(plotVariables.get(0),plotVariables.get(1),plotVariables);

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
        mainHBox.getChildren().addAll(choicePain,scatterPane, Histogram.createHistogram(plotVariables));
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
