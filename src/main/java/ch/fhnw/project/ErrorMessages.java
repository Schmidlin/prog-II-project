package ch.fhnw.project;

import javafx.scene.control.Alert;

import static javafx.application.Platform.exit;

/**
 * Created by fabienne on 25.05.16.
 */
public class ErrorMessages {

    public static void ioexception(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("IOException");
        String s = "No such file or directory. Start application again.";
        alert.setContentText(s);
        alert.showAndWait();
        exit();
    }

}
