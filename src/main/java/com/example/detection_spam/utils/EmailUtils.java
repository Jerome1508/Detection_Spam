package com.example.detection_spam.utils;

import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import com.example.detection_spam.model.Mail;

public class EmailUtils {
    public static Folder[] emailConnect() throws MessagingException, IOException {
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
}
