package ch.fhnw.project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.function.Supplier;

public final class App extends Application {

    @Override
    public void start(Stage stage) {



        Scene scene  = new Scene(mainPain, 500, 400);
        stage.setScene(scene);
        stage.show();
    }

}
