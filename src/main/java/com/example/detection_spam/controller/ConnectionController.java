package com.example.detection_spam.controller;

import com.example.detection_spam.MainApplication;
import com.example.detection_spam.algorithm.Algorithm;
import com.example.detection_spam.model.AcceptanceType;
import com.example.detection_spam.model.Dictionary;
import com.example.detection_spam.model.Mail;
import com.example.detection_spam.utils.EmailUtils;
import com.example.detection_spam.utils.FileUtils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.EnumSet;
import java.util.List;
import java.util.ResourceBundle;

public class ConnectionController implements Initializable {

    @FXML
    private TextField searchPath;

    @FXML
    private TextField emailArea;

    @FXML
    private PasswordField passwordArea;

    @FXML
    private TextField imapArea;

    @FXML
    private ChoiceBox<AcceptanceType> acceptanceChoiceBox;



    @FXML
    private void onSearchFileButtonClick() {
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        File dir = directoryChooser.showDialog(null);
        if (dir != null) {
            searchPath.setText(dir.getAbsolutePath());
        } else {
            searchPath.setText(null);
        }
    }

    @FXML
    private void onValidateFolderButtonClick() throws IOException {
        double filter = 0.6;
        if(! searchPath.getText().equals("")) {
            List<Mail> mails = FileUtils.parseFolder(searchPath.getText());

            AcceptanceType acceptance = acceptanceChoiceBox.getValue();

            switch (acceptance) {
                case NORMAL: filter = 0.6; break;
                case FAUX_NEGATIF: filter = 0.5; break;
                case FAUX_POSITIF: filter = 0.75; break;
            }

            Algorithm.analyse(mails, new Dictionary("dicoSaved.ser"), filter);
            // création de la scène
            Stage mainMenuStage = new Stage(StageStyle.DECORATED);
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-menu-view.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            mainMenuStage.setTitle("Tri des mails");
            mainMenuStage.setScene(scene);
            mainMenuStage.setResizable(false);
            mainMenuStage.initModality(Modality.APPLICATION_MODAL);
            MainMenuController controller = fxmlLoader.getController();
            controller.initialize(mails);

            mainMenuStage.show();
        }


    }

    @FXML
    private void onValidateEmailButtonClick() throws IOException, MessagingException {
        double filter = 0.6;

        if(! emailArea.getText().equals("") && passwordArea.getText().equals("") && imapArea.getText().equals("")) {
            List<Mail> mails = EmailUtils.parseEmail(EmailUtils.emailConnect(imapArea.getText(), emailArea.getText() , passwordArea.getText()));
            AcceptanceType acceptance = acceptanceChoiceBox.getValue();

            switch (acceptance) {
                case NORMAL: filter = 0.6; break;
                case FAUX_NEGATIF: filter = 0.5; break;
                case FAUX_POSITIF: filter = 0.75; break;
            }
            Algorithm.analyse(mails, new Dictionary("dicoSaved.ser"), filter);

            // création de la scène
            Stage mainMenuStage = new Stage(StageStyle.DECORATED);
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-menu-view.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            mainMenuStage.setTitle("Tri des mails");
            mainMenuStage.setScene(scene);
            mainMenuStage.setResizable(false);
            mainMenuStage.initModality(Modality.APPLICATION_MODAL);
            MainMenuController controller = fxmlLoader.getController();
            controller.initialize(mails);

            mainMenuStage.show();
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        EnumSet<AcceptanceType> acceptanceTypes = EnumSet.allOf(AcceptanceType.class);
        acceptanceChoiceBox.setItems(FXCollections.observableArrayList(acceptanceTypes));
        acceptanceChoiceBox.setValue(AcceptanceType.NORMAL);
    }
}
