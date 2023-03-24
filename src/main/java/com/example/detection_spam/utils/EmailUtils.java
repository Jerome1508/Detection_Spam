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
    public static Folder[] emailConnect() throws MessagingException {
        String host = "imap.gmx.com";
        String username = "javamailspam@gmx.fr";
        String password = "7hkCMpnu2iyP49V";

        Properties props = System.getProperties();
        props.setProperty("mail.imap.ssl.enable", "true");
        Session session = Session.getDefaultInstance(props, null);
        Store store = session.getStore("imap");
        store.connect(host, username, password);
        Folder[] folderList = store.getDefaultFolder().list("*");
        for (javax.mail.Folder folder : folderList) {
            if ((folder.getType() & javax.mail.Folder.HOLDS_MESSAGES) != 0) {
                System.out.println(folder.getFullName() + ": " + folder.getMessageCount());
            }
        }
        store.close();
        return folderList;
    }

    private static String getcontentFromMultipartMail(MimeMultipart mpt) throws MessagingException, IOException {
        String res = "";
        for (int i = 0; i < mpt.getCount(); i++) {
            BodyPart bodyPart = mpt.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                res += "\n" + bodyPart.getContent();
            }
        }
        return res;
    }

    public static ArrayList<Mail> ParseEmail(Folder[] folders) throws MessagingException, IOException {
        ArrayList<Mail> mails = new ArrayList<Mail>();

        for (Folder folder : folders) {
            if (folder.getMessageCount() != 0) {
                folder.open(Folder.READ_ONLY);
                for (int i = 1; i < folder.getMessageCount(); i++) {
                    Message message = folder.getMessage(i);
                    String content = message.getSubject();
                    if (message.isMimeType("TEXT/plain")) {
                        content += message.getContent().toString();
                    }
                    else {
                        MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
                        content += getcontentFromMultipartMail(mimeMultipart);
                    }

                    Mail mail = new Mail(content);
                    mails.add(mail);
                }
                folder.close();
            }
        }
        return mails;
    }
}
