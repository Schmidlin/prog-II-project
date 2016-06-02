package ch.fhnw.project.Plot;

import ch.fhnw.project.Variables;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.List;


/**
 * Created by thomasschmidlin on 04.05.16.
 * Create the MainPain
 */
public class MainPain {
    private static HBox choicePain = new HBox();
    private static ComboBox<String> cbXAxis;
    private static ComboBox<String> cbYAxis;
    private static VBox mainHBox = new VBox();
    private static Pane scatterPane;
    private static Pane histogram;


    public static Pane createMainPain(List<Variables> variableList) {

        cbXAxis = getChoiceBox(variableList);
        cbXAxis.setValue(variableList.get(0).getName());
        Label labelXAxis = new Label("x-Axis:");
        cbYAxis = getChoiceBox(variableList);
        cbYAxis.setValue(variableList.get(1).getName());
        Label labelYAxis = new Label("y-Axis:");

        cbXAxis.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            cbXAxis.setValue(variableList.get((int) newValue).getName());
            scatterPane = ScatterPlot.createScatterPane(variableList, (int) newValue, cbYAxis.getSelectionModel().getSelectedIndex());
            histogram = Histogram.createHistogram(variableList, (int) newValue, cbYAxis.getSelectionModel().getSelectedIndex());
        });
        cbYAxis.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            cbYAxis.setValue(variableList.get((int) newValue).getName());
            scatterPane = ScatterPlot.createScatterPane(variableList, cbXAxis.getSelectionModel().getSelectedIndex(), (int) newValue);
            histogram = Histogram.createHistogram(variableList, cbXAxis.getSelectionModel().getSelectedIndex(), (int) newValue);

        });


        scatterPane = ScatterPlot.createScatterPane(variableList, cbXAxis.getSelectionModel().selectedIndexProperty().getValue(), cbYAxis.getSelectionModel().selectedIndexProperty().getValue());
        histogram = Histogram.createHistogram(variableList, cbXAxis.getSelectionModel().selectedIndexProperty().getValue(), cbYAxis.getSelectionModel().selectedIndexProperty().getValue());


        choicePain.getChildren().addAll(labelXAxis, cbXAxis, labelYAxis, cbYAxis);
        choicePain.setAlignment(Pos.CENTER);
        choicePain.setSpacing(10);
        choicePain.setPadding(new Insets(5, 5, 5, 5));

        mainHBox.getChildren().addAll(choicePain, scatterPane, histogram);
        mainHBox.setPadding(new Insets(5, 5, 5, 5));
        mainHBox.setSpacing(10);

        StackPane mainPain = new StackPane();
        mainPain.getChildren().addAll(mainHBox);
        return mainPain;
    }

    private static ComboBox<String> getChoiceBox(List<Variables> variableList) {
        ComboBox<String> choiceBox = new ComboBox<>();
        for (int i = 0; i <= variableList.size() - 1; i++) {
            choiceBox.getItems().add(variableList.get(i).getName());

        }


        return choiceBox;
    }
}
