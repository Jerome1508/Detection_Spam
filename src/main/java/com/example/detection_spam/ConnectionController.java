package com.example.detection_spam;

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
    private TextField IMAP_Area;

    @FXML
    private ChoiceBox<AcceptanceType> AcceptanceChoiceBox;



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
        if(searchPath.getText() != "") {
            List<Mail> mails = FileUtils.parseFolder(searchPath.getText());

            AcceptanceType acceptance = AcceptanceChoiceBox.getValue();

            switch (acceptance) {
                case Normal: filter = 0.6; break;
                case Faux_négatif: filter = 0.5; break;
                case Faux_positif: filter = 0.75; break;
            }

            Algorithm.analyse(mails, new Dictionary("dicoSaved.ser"), filter);
            // création de la scène
            Stage mainMenuStage = new Stage(StageStyle.DECORATED);
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-menu-view.fxml"));

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

        if(emailArea.getText() != "" && passwordArea.getText() != "" && IMAP_Area.getText() != "") {
            List<Mail> mails = EmailUtils.ParseEmail(EmailUtils.emailConnect(IMAP_Area.getText(), emailArea.getText() , passwordArea.getText()));
            AcceptanceType acceptance = AcceptanceChoiceBox.getValue();

            switch (acceptance) {
                case Normal: filter = 0.6; break;
                case Faux_négatif: filter = 0.5; break;
                case Faux_positif: filter = 0.75; break;
            }
            Algorithm.analyse(mails, new Dictionary("dicoSaved.ser"), filter);

            // création de la scène
            Stage mainMenuStage = new Stage(StageStyle.DECORATED);
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-menu-view.fxml"));

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
        AcceptanceChoiceBox.setItems(FXCollections.observableArrayList(acceptanceTypes));
        AcceptanceChoiceBox.setValue(AcceptanceType.Normal);
    }
}
