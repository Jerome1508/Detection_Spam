module com.example.detection_spam {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.mail;


    opens com.example.detection_spam to javafx.fxml;
    exports com.example.detection_spam;
}