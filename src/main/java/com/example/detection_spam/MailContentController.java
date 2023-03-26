package com.example.detection_spam;

import com.example.detection_spam.model.Mail;
import com.example.detection_spam.model.State;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.util.List;

public class MailContentController {

    @FXML
    private TextArea contentArea;

    public void initialize(Mail mail) throws IOException {
        contentArea.setText(mail.getText());
    }
}
