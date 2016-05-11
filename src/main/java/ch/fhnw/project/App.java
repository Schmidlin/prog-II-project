package ch.fhnw.project;

import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.LinkedList;
import java.util.function.Supplier;

import javafx.*;


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
        if (selectedFile != null) {
            return selectedFile;
        } else {
            fileChoose();
        }

        return selectedFile;

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        LinkedList<Variables> variableList = DataConvert.convert(fileChoose());

        System.out.println("Name of second Variable: " + variableList.get(1).getName());
        System.out.println("first value of first Variable: " + variableList.get(0).getValues().get(0));
        System.out.println("third value of second Variable: "+ variableList.get(1).getValues().get(2));
        System.out.println("the size of value-List from first Variable: " + variableList.get(0).getValues().size());



    }




}
