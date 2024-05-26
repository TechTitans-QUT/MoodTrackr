package com.example.moodtrackr.controllers;

import com.example.moodtrackr.GlobalData;
import com.example.moodtrackr.model.SqliteSessionDAO;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

public class LineChartController implements Initializable {
    @FXML
    private LineChart<String,Number> lineChart;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Access session manager
        SqliteSessionDAO sessionDAO = new SqliteSessionDAO();
        // Set current user's id
        int id = GlobalData.getInstance().getYourObject().getId();

        // Get HashMap of dates with average mood for UserID
        HashMap<String, Number> userData = SqliteSessionDAO.getUserIdSessions(id);

        // Defining an x Axis
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Date");

        // Defining a y Axis
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Recorded Mood");

        lineChart = new LineChart<String,Number>(xAxis,yAxis);

        // Defining a series
        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();

        // Get username
        String name = GlobalData.getInstance().getYourObject().getFirstName();
        series.setName(name + "'s Mood Over Time");

        for (Map.Entry<String, Number> entry : userData.entrySet()) {
            String date = entry.getKey();
            Number averageMood = entry.getValue();
            series.getData().add(new XYChart.Data<String, Number>(date, averageMood));
        }
        // Set the data to Line chart
        lineChart.getData().add(series);

    }
}
