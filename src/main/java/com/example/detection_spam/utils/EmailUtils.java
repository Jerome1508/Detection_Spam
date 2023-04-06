package com.example.detection_spam.utils;

import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import com.example.detection_spam.model.Mail;

/**
 * Classe qui va effectuer la connexion avec la boite mail de l'utilisateur et récupérer les mails
 * @author Marin BAILHE, Jérôme CHIROL
 * @version 1.0.0
 */
public class EmailUtils {
    /**
     * Constructeur privé pour éviter de créer une instance de cette classe
     */
    private EmailUtils() {

    }

    /**
     * Permet de se connecter à une adresse mail et de récuperer un dossier de mail
     *  Donnée de test :
     *      - host = "imap.gmx.com";
     *      - username = "javamailspam@gmx.fr";
     *      - password = "7hkCMpnu2iyP49V";
     * @param host l'adresse du serveur IMAP
     * @param username l'adresse mail à laquelle on veut se connecter
     * @param password le mot de passe lié à l'adresse mail
     * @return un dossier de mail
     * @throws MessagingException
     */
    public static Folder[] emailConnect(String host, String username, String password) throws MessagingException {

        Properties props = System.getProperties();
        props.setProperty("mail.imap.ssl.enable", "true");
        Session session = Session.getDefaultInstance(props, null);
        Store store = session.getStore("imap");
        store.connect(host, username, password);
        Folder[] folderList = store.getDefaultFolder().list("*");
        store.close();
        return folderList;
    }

    private static String getcontentFromMultipartMail(MimeMultipart mpt) throws MessagingException, IOException {
        String res = "";
        for (int i = 0; i < mpt.getCount(); i++) {
            BodyPart bodyPart = mpt.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                res = bodyPart.getContent() + "\n";
            }
            else {
                if (bodyPart.getContent() instanceof MimeMultipart){
                    res = getcontentFromMultipartMail((MimeMultipart)bodyPart.getContent());
                }
            }
        }
        return res;
    }

    /**
     * Parse le dossier brut récupéré depuis l'adresse mail en une liste d'objet Mail exploitable par l'application
     * @param folders
     * @return la liste des mails contenus dans le dossier pris en argument
     * @throws MessagingException
     * @throws IOException
     */
    public static List<Mail> parseEmail(Folder[] folders) throws MessagingException, IOException {
        ArrayList<Mail> mails = new ArrayList<>();

        for (Folder folder : folders) {
            if (folder.getMessageCount() != 0) {
                folder.open(Folder.READ_ONLY);
                for (int i = 1; i <= folder.getMessageCount(); i++) {
                    Message message = folder.getMessage(i);
                    String content = "";
                    if (message.isMimeType("TEXT/plain")) {
                        content += message.getContent().toString();
                    }
                    else {
                        MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
                        content += getcontentFromMultipartMail(mimeMultipart);
                    }

                    Mail mail = new Mail(content, message.getSubject());
                    mails.add(mail);
                }
                folder.close();
            }
        }
        return mails;
    }
}
