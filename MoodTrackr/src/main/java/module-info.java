module com.example.addressbook.moodtrackr {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;

    opens com.example.moodtrackr to javafx.fxml;
    exports com.example.moodtrackr;
    exports com.example.moodtrackr.controllers;
    opens com.example.moodtrackr.controllers to javafx.fxml;
}