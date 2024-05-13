package com.example.moodtrackr.controllers;

import com.example.moodtrackr.GlobalData;
import com.example.moodtrackr.model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.controlsfx.control.SearchableComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class SeachBarController implements Initializable {
    @FXML
    private Label titleLabel;
    private User current;

//    SeachBarController(User user) {
//        current = user;
//    }

    @FXML
    public void set(User current) {
        titleLabel.setText(current.getFullName());
    }

    @FXML
    public void test() {
        System.out.println(current.getFullName());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        titleLabel.setText("working!!!!!");
        current = GlobalData.getInstance().getYourObject();
    }
}
