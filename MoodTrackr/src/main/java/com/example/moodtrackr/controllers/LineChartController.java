package com.example.moodtrackr.controllers;

import com.example.moodtrackr.GlobalData;
import com.example.moodtrackr.model.SqliteSessionDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class LineChartController implements Initializable {

    @FXML
    private LineChart<String, Number> lineChart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Access session manager
        SqliteSessionDAO sessionDAO = new SqliteSessionDAO();
        // Set current user's id
        int id = GlobalData.getInstance().getYourObject().getId();

        // Get HashMap of dates with average mood for UserID
        HashMap<String, Number> userData = sessionDAO.getUserIdSessions(id);

        // Defining the axes is not necessary as they are injected from FXML
        xAxis.setLabel("Date");
        yAxis.setLabel("Recorded Mood");
        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(1);
        yAxis.setUpperBound(7);
        yAxis.setTickUnit(1);

        // Defining a series
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Mood Over Time");

        for (Map.Entry<String, Number> entry : userData.entrySet()) {
            String date = entry.getKey();
            Number averageMood = entry.getValue();
            series.getData().add(new XYChart.Data<>(date, averageMood));
        }

        // Set the data to Line chart
        lineChart.getData().add(series);
    }
}
