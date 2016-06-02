package ch.fhnw.project;

import ch.fhnw.project.DataHandling.Data;
import ch.fhnw.project.DataHandling.DataConverter;
import ch.fhnw.project.DataHandling.LinConverter;
import ch.fhnw.project.DataHandling.TxtConverter;
import ch.fhnw.project.Plot.MainPain;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;


public final class App extends Application {

    private static File fileChoose() {


        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose a File");
        chooser.getExtensionFilters().

                addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt", "*.lin"));

        return chooser.showOpenDialog(null);
    }

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) {

        DataConverter converter;
        File file = fileChoose();
        if (file == null) {
            System.exit(0);
        }
        if (file.getName().endsWith("txt")) {
            converter = new TxtConverter();
        } else {
            converter = new LinConverter();
        }
        try {
            Data dataObject = converter.read(file);
            StackPane pane = new StackPane(MainPain.createMainPain(dataObject.getListVariables()));
            Scene scene = new Scene(pane);
            stage.setScene(scene);
            stage.setTitle(dataObject.getDataName());
            stage.show();

        } catch (IOException e) {
            ErrorMessages.ioexception();
        }
    }
}
