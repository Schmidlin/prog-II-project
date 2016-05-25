package ch.fhnw.project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;


public final class App extends Application {

    public static File data;

    public static File fileChoose() {


        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose a File");
        chooser.getExtensionFilters().

                addAll(
                        new FileChooser.ExtensionFilter("Text Files", "*.txt", "*.lin")

                );

        File selectedFile = chooser.showOpenDialog(null);
        return selectedFile;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        DataConverter converter;
        File file = fileChoose();
        if (file==null){System.exit(0);}
        if(file.getName().endsWith("txt")){
            converter = new TxtConverter();
        }
        else{
            converter = new LinConverter();
        }
        try {
            Data dataObject = converter.read(file);
            StackPane pane = new StackPane(mainPain.createMainPain(dataObject.getListVariables()));
            Scene scene = new Scene(pane);
            stage.setScene(scene);
            stage.setTitle(dataObject.getDataName());
            stage.show();

        } catch (IOException e) {
            ErrorMessages.ioexception();
        }




    }




}
