package com.example.detection_spam;

import com.example.detection_spam.model.Mail;
import com.example.detection_spam.model.State;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.List;

public class MainMenuController {

    @FXML
    private  ListView<Mail> spamListView;

    @FXML
    private  ListView<Mail> not_spamListView;


    public void initialize(List<Mail> mails) throws IOException {

        // remplissage des listView
        for(Mail mail : mails) {
            if(mail.getState() == State.SPAM) {
                spamListView.getItems().add(mail);
            } else if(mail.getState() == State.NOT_SPAM) {
                not_spamListView.getItems().add(mail);
            }
        }
    }


}
