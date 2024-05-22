package com.example.moodtrackr.controllers;

import com.example.moodtrackr.GlobalData;
import com.example.moodtrackr.MainApplication;
import com.example.moodtrackr.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SearchbarController implements Initializable {
    @FXML
    private Label titleLabel;
    @FXML
    private MenuButton menuButton;
    private User current;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        current = GlobalData.getInstance().getYourObject();
        titleLabel.setText(current.getFirstName());
        menuButton.setText(current.getFirstName());
    }

    @FXML
    protected void logoutItemClicked() throws IOException {
        Stage stage = (Stage) menuButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
        stage.setScene(scene);
    }
}
