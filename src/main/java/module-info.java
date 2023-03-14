module com.example.detection_spam {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.detection_spam to javafx.fxml;
    exports com.example.detection_spam;
}