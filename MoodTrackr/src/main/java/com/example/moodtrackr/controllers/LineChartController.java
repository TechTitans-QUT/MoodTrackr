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
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class LineChartController implements Initializable {
    @FXML
    private LineChart<Number,Number> lineChart;
    private final String[] moods = { "Very Happy", "Happy", "Slightly Happy", "Neutral", "Slightly Sad", "Sad", "Very Sad" };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Access session manager
        SqliteSessionDAO sessionDAO = new SqliteSessionDAO();
        // Set current user's id
        int id = GlobalData.getInstance().getYourObject().getId();
        //Defining x Axis
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Date");

        //Defining y Axis
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Recorded Mood");

        new LineChart<Number,Number>(xAxis,yAxis);

        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Mood Over Time");


    }
}
