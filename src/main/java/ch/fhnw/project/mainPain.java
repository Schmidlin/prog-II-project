package ch.fhnw.project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * Created by thomasschmidlin on 04.05.16.
 */
public class mainPain {

    public static void main(String[] args) {

        Pane scatterPane = new Pane();


        HBox firstLine = new HBox();
        //firstLine.getChildren().addAll();
        firstLine.setAlignment(Pos.CENTER);
        firstLine.setPadding(new Insets(5, 5, 5, 5));
        firstLine.setStyle("-fx-background-color: cornflowerblue;");

        HBox secondLine = new HBox();
        //secondLine.getChildren().addAll();
        secondLine.setAlignment(Pos.CENTER);
        firstLine.setPadding(new Insets(5, 5, 5, 5));
        firstLine.setStyle("-fx-background-color: cornflowerblue;");

        VBox vBox = new VBox();
        vBox.getChildren().addAll(firstLine, secondLine);
        vBox.setSpacing(10);

        StackPane mainPain = new StackPane();
        mainPain.getChildren().add(vBox);
    }
}
