package com.example.detection_spam.controleur;

import com.example.detection_spam.model.Mail;

import javafx.fxml.FXML;

import javafx.scene.control.TextArea;


public class MailContentController {

    @FXML
    private TextArea contentArea;


    public void initialize(Mail mail) {
        contentArea.setText(mail.getText());
    }

}
