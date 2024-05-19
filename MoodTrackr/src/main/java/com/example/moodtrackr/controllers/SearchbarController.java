package com.example.moodtrackr.controllers;

import com.example.moodtrackr.GlobalData;
import com.example.moodtrackr.model.User;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TransferQueue;

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
}
