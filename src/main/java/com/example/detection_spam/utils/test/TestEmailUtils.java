package com.example.detection_spam.utils.test;

import com.example.detection_spam.model.Mail;
import com.example.detection_spam.utils.FileUtils;

import javax.mail.MessagingException;
import java.util.ArrayList;

import static com.example.detection_spam.utils.EmailUtils.ParseEmail;
import static com.example.detection_spam.utils.EmailUtils.emailConnect;

/**
 * Classe qui va effectuer des tests sur la classe EmailUtils
 * @author Marin BAILHE, Jérôme CHIROL
 * @version 1.0.0
 */
public class TestEmailUtils {
    private static void testConnexion()  {
        try {
            System.out.println(emailConnect("imap.gmx.com","javamailspam@gmx.fr", "7hkCMpnu2iyP49V"));

        }catch(Exception e){
            System.err.println(e);
        }

    }

    private static void testParseMails() {
        try {
            ArrayList<Mail> mails = ParseEmail(emailConnect("imap.gmx.com","javamailspam@gmx.fr", "7hkCMpnu2iyP49V"));
            for(Mail mail : mails) {
                System.out.println(mail.getText());
                System.out.println("\n*******************************\n");
            }
        }catch(Exception e){
            System.err.println(e);
        }
    }


    public static void main (String[] args){
        testConnexion();
        testParseMails();

    }
}
