module com.example.addressbook.moodtrackr {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.addressbook.moodtrackr to javafx.fxml;
    exports com.example.addressbook.moodtrackr;
}