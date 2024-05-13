package com.example.moodtrackr;

import com.example.moodtrackr.controllers.BaseUITemplate;
import com.example.moodtrackr.model.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.stage.*;
import java.io.IOException;

public class NavigationMethods {
    public static void ButtonNav(Button buttonName, String fxmlFile, User currentAccount) throws IOException {
        Stage stage = (Stage) buttonName.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(fxmlFile));
        Scene scene = new Scene(fxmlLoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
        stage.setScene(scene);
    }

    public static void NewButtonNav(Button buttonName, String fxmlFile, User user) throws IOException {
        Stage stage = (Stage) buttonName.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource(fxmlFile));
        Parent root = loader.load();
        BaseUITemplate baseUITemplate = loader.getController();
        baseUITemplate.init(user);
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}