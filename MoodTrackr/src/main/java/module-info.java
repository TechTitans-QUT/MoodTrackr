module com.example.addressbook.moodtrackr {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.moodtrackr to javafx.fxml;
    exports com.example.moodtrackr;
    exports com.example.moodtrackr.controller;
    opens com.example.moodtrackr.controller to javafx.fxml;
}