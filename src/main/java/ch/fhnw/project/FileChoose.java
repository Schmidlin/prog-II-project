package ch.fhnw.project;

import javafx.stage.FileChooser;

import java.io.File;


/**
 * Created by fabienne on 04.05.16.
 */
public class FileChoose {

    public static java.io.File chooser()
    {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose a File");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("Line Text Files", "*.lin")

        );

        File selectedFile = chooser.showOpenDialog(null);
        if (selectedFile != null){
            return selectedFile;
        }else{
            chooser();
        }
        return selectedFile;

    }


    public static void main(String[] args) {

        chooser();

    }

}
