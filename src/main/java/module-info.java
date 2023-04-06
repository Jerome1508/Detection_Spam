module com.example.detection_spam {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.mail;
    requires java.logging;


    opens com.example.detection_spam to javafx.fxml;
    exports com.example.detection_spam;
    exports com.example.detection_spam.controller;
    opens com.example.detection_spam.controller to javafx.fxml;
}