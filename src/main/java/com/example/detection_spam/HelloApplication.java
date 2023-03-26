package com.example.detection_spam;

import com.example.detection_spam.algorithm.Algorithm;
import com.example.detection_spam.model.Dictionary;
import com.example.detection_spam.model.Mail;
import com.example.detection_spam.utils.EmailUtils;
import com.example.detection_spam.utils.FileUtils;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class HelloApplication extends Application {

    @FXML
    private TextField searchPath;

    @FXML
    private TextField emailArea;

    @FXML
    private PasswordField passwordArea;

    @FXML
    private TextField IMAP_Area;

    private Stage connectionStage;


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
        if(searchPath.getText() != "") {
            List<Mail> mails = FileUtils.parseFolder(searchPath.getText());
            Algorithm.analyse(mails, new Dictionary("dicoSaved.ser"), 0.5);

            // création de la scène
            Stage mainMenuStage = new Stage(StageStyle.DECORATED);
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-menu-view.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            mainMenuStage.setTitle("Tri des mails");
            mainMenuStage.setScene(scene);
            mainMenuStage.setResizable(false);
            mainMenuStage.initOwner(connectionStage);
            mainMenuStage.initModality(Modality.APPLICATION_MODAL);
            MainMenuController controller = fxmlLoader.getController();
            controller.initialize(mails);

            mainMenuStage.show();
        }


    }

    @FXML
    private void onValidateEmailButtonClick() throws IOException, MessagingException {
        if(emailArea.getText() != "" && passwordArea.getText() != "" && IMAP_Area.getText() != "") {
            List<Mail> mails = EmailUtils.ParseEmail(EmailUtils.emailConnect(IMAP_Area.getText(), emailArea.getText() , passwordArea.getText()));
            Algorithm.analyse(mails, new Dictionary("dicoSaved.ser"), 0.5);

            // création de la scène
            Stage mainMenuStage = new Stage(StageStyle.DECORATED);
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-menu-view.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            mainMenuStage.setTitle("Tri des mails");
            mainMenuStage.setScene(scene);
            mainMenuStage.setResizable(false);
            mainMenuStage.initOwner(connectionStage);
            mainMenuStage.initModality(Modality.APPLICATION_MODAL);
            MainMenuController controller = fxmlLoader.getController();
            controller.initialize(mails);

            mainMenuStage.show();
        }


    }

    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Tri des mails");
        stage.setScene(scene);
        connectionStage = stage;
        stage.show();
        stage.setResizable(false);
    }

    public static void main(String[] args) {
        launch();
    }
}