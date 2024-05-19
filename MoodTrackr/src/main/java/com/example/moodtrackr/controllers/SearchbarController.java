package com.example.moodtrackr.controllers;

import com.example.moodtrackr.GlobalData;
import com.example.moodtrackr.model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchbarController implements Initializable {
    @FXML
    private Label titleLabel;
    private User current;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        current = GlobalData.getInstance().getYourObject();
        titleLabel.setText(current.getFirstName());
    }
}