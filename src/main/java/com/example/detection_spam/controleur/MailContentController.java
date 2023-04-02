package com.example.detection_spam.controleur;

import com.example.detection_spam.model.Mail;
import com.example.detection_spam.model.State;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MailContentController {

    @FXML
    private TextArea contentArea;


    public void initialize(Mail mail) throws IOException {
        contentArea.setText(mail.getText());
    }

}
