package ch.fhnw.project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 * Created by thomasschmidlin on 04.05.16.
 */
public class MainPain {

    public static Pane createMainPain(List<Variables> variableList) {

        /*HBox scatterPlot = new HBox();
        scatterPlot.getChildren().add(ScatterPlot.createScatterPane(variableList));
        scatterPlot.setAlignment(Pos.CENTER);
        scatterPlot.setStyle("-fx-background-color: yellow;");
        //scatterPlot.setPadding(new Insets(5, 5, 5, 5));

        HBox histogramm = new HBox();
        histogramm.getChildren().addAll(Histogramm.createHistogramm(variableList));
        histogramm.setAlignment(Pos.CENTER);
        histogramm.setPadding(new Insets(5, 5, 5, 5));*/

        VBox mainHBox = new VBox();
        mainHBox.getChildren().addAll(ScatterPlot.createScatterPane(variableList),Histogramm.createHistogramm(variableList));
        mainHBox.setPadding(new Insets(5, 5, 5, 5));
        mainHBox.setSpacing(10);

        StackPane mainPain = new StackPane();
        mainPain.getChildren().addAll(mainHBox);
        return mainPain;
    }

}
