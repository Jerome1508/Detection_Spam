package com.example.detection_spam;

import com.example.detection_spam.algorithm.Algorithm;
import com.example.detection_spam.model.Dictionary;
import com.example.detection_spam.model.Mail;
import com.example.detection_spam.model.State;
import com.example.detection_spam.utils.FileUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
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

        spamListView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent click) {

                if (click.getClickCount() == 2) {
                    //Use ListView's getSelected Item
                    Mail currentMail = spamListView.getSelectionModel()
                            .getSelectedItem();
                    //use this to do whatever you want to. Open Link etc.
                    try {
                        onItemListClick(currentMail);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        not_spamListView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent click) {

                if (click.getClickCount() == 2) {
                    //Use ListView's getSelected Item
                    Mail currentMail = not_spamListView.getSelectionModel()
                            .getSelectedItem();
                    //use this to do whatever you want to. Open Link etc.
                    try {
                        onItemListClick(currentMail);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    @FXML
    private void onItemListClick(Mail mail) throws IOException {

        // création de la scène
        Stage mailContentStage = new Stage(StageStyle.DECORATED);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mail-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        mailContentStage.setTitle(mail.getSubject());
        mailContentStage.setScene(scene);
        mailContentStage.setResizable(false);
        //Todo mailContentStage.initOwner(connectionStage);
        mailContentStage.initModality(Modality.APPLICATION_MODAL);
        MailContentController controller = fxmlLoader.getController();
        controller.initialize(mail);

        mailContentStage.show();


    }


}