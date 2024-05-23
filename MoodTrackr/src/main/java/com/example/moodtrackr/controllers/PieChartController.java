package com.example.moodtrackr.controllers;

import com.example.moodtrackr.GlobalData;
import com.example.moodtrackr.model.SessionManager;
import com.example.moodtrackr.model.SqliteSessionDAO;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.chart.PieChart;

import java.net.URL;
import java.util.ResourceBundle;

public class PieChartController implements Initializable {
    @FXML
    private PieChart pieChart;

    private SessionManager sessionManager;
    private SqliteSessionDAO sessionDAO;
    private String[] moods = { "Very Happy", "Happy", "Slightly Happy", "Neutral", "Slightly Sad", "Sad", "Very Sad" };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Access session manager
        sessionDAO = new SqliteSessionDAO();
        // Set current user's id
        int id = GlobalData.getInstance().getYourObject().getId();
        // Create pie chart
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList();
        // Insert data into pie data
        for (String mood : moods) {
            pieData.add(new PieChart.Data(mood, sessionDAO.getNumberOfMood(id, mood)));
        }
        // Give the number beside their name
        pieData.forEach(data -> {
            data.nameProperty().bind(
                    Bindings.concat(
                            data.getName(), ": ", data.pieValueProperty()
                    )
            );
        });
        // Insert data into pie chart
        pieChart.getData().addAll(pieData);
        pieChart.setTitle("Mood Chart");
    }
}
