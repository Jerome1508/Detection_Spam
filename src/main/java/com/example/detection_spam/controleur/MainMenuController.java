package com.example.detection_spam.controleur;

import com.example.detection_spam.MainApplication;
import com.example.detection_spam.algorithm.Algorithm;
import com.example.detection_spam.model.Dictionary;
import com.example.detection_spam.model.Mail;
import com.example.detection_spam.model.State;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainMenuController {

    @FXML
    private  ListView<Mail> spamListView;

    @FXML
    private  ListView<Mail> notSpamListView;

    private Mail currentMail;

    private static Logger logger = Logger.getLogger(MainMenuController.class.getPackage().getName());



    public void initialize(List<Mail> mails) {

        // remplissage des listView
        for(Mail mail : mails) {
            if(mail.getState() == State.SPAM) {
                spamListView.getItems().add(mail);
            } else if(mail.getState() == State.NOT_SPAM) {
                notSpamListView.getItems().add(mail);
            }
        }

        spamListView.setOnMouseClicked(click -> {
            //Use ListView's getSelected Item
            currentMail = spamListView.getSelectionModel().getSelectedItem();

            if (click.getClickCount() == 2) {
                //use this to do whatever you want to. Open Link etc.
                try {
                    onItemListClick(currentMail);
                } catch (IOException e) {
                    logger.log(Level.SEVERE, (e.getMessage()));
                }
            }
        });


        notSpamListView.setOnMouseClicked(click -> {
            currentMail = notSpamListView.getSelectionModel().getSelectedItem();

            if (click.getClickCount() == 2) {
                try {
                    onItemListClick(currentMail);
                } catch (IOException e) {
                    logger.log(Level.SEVERE, (e.getMessage()));
                }
            }
        });
    }

    @FXML
    private void onItemListClick(Mail mail) throws IOException {

        // création de la scène
        Stage mailContentStage = new Stage(StageStyle.DECORATED);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("mail-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        mailContentStage.setTitle(mail.getSubject());
        mailContentStage.setScene(scene);
        mailContentStage.setResizable(false);
        mailContentStage.initModality(Modality.APPLICATION_MODAL);
        MailContentController controller = fxmlLoader.getController();
        controller.initialize(mail);

        mailContentStage.show();


    }
    @FXML
    private void onClickRightArrowButton() {

        if(currentMail != null && currentMail.getState() == State.SPAM) {
            currentMail.setState(State.NOT_SPAM);
            spamListView.getItems().remove(currentMail);
            notSpamListView.getItems().add(currentMail);

        }

    }

    @FXML
    private void onClickLeftArrowButton()  {

        if(currentMail != null&& currentMail.getState() == State.NOT_SPAM) {
            currentMail.setState(State.SPAM);
            spamListView.getItems().add(currentMail);
            notSpamListView.getItems().remove(currentMail);
        }

    }

    @FXML
    private void onLearningButton()  {

        Algorithm.learning(new ArrayList<>(spamListView.getItems()), true,  new Dictionary("dicoSaved.ser"));
        Algorithm.learning(new ArrayList<>(notSpamListView.getItems()), false,  new Dictionary("dicoSaved.ser"));

    }



}
