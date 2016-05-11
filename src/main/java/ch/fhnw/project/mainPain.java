package ch.fhnw.project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * Created by thomasschmidlin on 04.05.16.
 */
public class MainPain {

    public static Pane getMainPain () {

        HBox scatterPlot = new HBox();
        scatterPlot.getChildren().addAll(ScatterPlot.createScatterPane());
        scatterPlot.setAlignment(Pos.CENTER);
        scatterPlot.setPadding(new Insets(5, 5, 5, 5));
        scatterPlot.setStyle("-fx-background-color: cornflowerblue;");

        HBox histogramm = new HBox();
        //histogramm.getChildren().addAll();
        histogramm.setAlignment(Pos.CENTER);
        histogramm.setPadding(new Insets(5, 5, 5, 5));
        histogramm.setStyle("-fx-background-color: red;");

        VBox mainHBox = new VBox();
        mainHBox.getChildren().addAll(scatterPlot,histogramm);

        StackPane mainPain = new StackPane();
        mainPain.getChildren().addAll(mainHBox);
        return mainPain;
    }

}
