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

    @FXML
    public void set(User current) {
        titleLabel.setText("test");
    }

    @FXML
    public void test() {
        System.out.println("initialize");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        titleLabel.setText("working!!!!!");
//        User currentAccount = ;
//        System.out.println(currentAccount);
        titleLabel.setText(GlobalData.getInstance().getYourObject().getFullName());
//        test();
    }
}
