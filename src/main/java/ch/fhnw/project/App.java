package ch.fhnw.project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;


public final class App extends Application {

    public static File fileChoose() {


    FileChooser chooser = new FileChooser();
    chooser.setTitle("Choose a File");
    chooser.getExtensionFilters().

    addAll(
            new FileChooser.ExtensionFilter("Text Files", "*.txt","*.lin")

            );

    File selectedFile = chooser.showOpenDialog(null);
    if(selectedFile!=null)

    {
        return selectedFile;
    }

    else

    {
        fileChoose();
    }

    return selectedFile;
}

    @Override
    public void start(Stage stage) {

        fileChoose();



        Pane pane = new Pane();
        pane = MainPain.getMainPain();

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

}
