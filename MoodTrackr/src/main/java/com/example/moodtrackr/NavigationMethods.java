package com.example.moodtrackr;

import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.stage.*;
import java.io.IOException;

public class NavigationMethods {
    public static void ButtonNav(Button buttonName, String fxmlFile) throws IOException {
        Stage stage = (Stage) buttonName.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxmlFile));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }
}
