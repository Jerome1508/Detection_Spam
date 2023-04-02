package com.example.detection_spam.utils.test;

import com.example.detection_spam.model.Mail;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.example.detection_spam.utils.EmailUtils.parseEmail;
import static com.example.detection_spam.utils.EmailUtils.emailConnect;

/**
 * Classe qui va effectuer des tests sur la classe EmailUtils
 * @author Marin BAILHE, Jérôme CHIROL
 * @version 1.0.0
 */
public class TestEmailUtils {
    private static Logger logger = Logger.getLogger(TestEmailUtils.class.getPackage().getName());

    private static void testConnexion()  {
        try {
            logger.log(Level.INFO, () -> {
                try {
                    return Arrays.toString(emailConnect("imap.gmx.com","javamailspam@gmx.fr", "7hkCMpnu2iyP49V"));
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            });
        }catch(Exception e){
            logger.log(Level.SEVERE, (e.getMessage()));
        }

    }

    private static void testParseMails() {
        try {
            List<Mail> mails = parseEmail(emailConnect("imap.gmx.com","javamailspam@gmx.fr", "7hkCMpnu2iyP49V"));
            for(Mail mail : mails) {
                logger.log(Level.INFO, (mail.getText()));
                logger.log(Level.INFO, ("\n*******************************\n"));
            }
        }catch(Exception e){
            logger.log(Level.SEVERE, (e.getMessage()));
        }
    }


    public static void main (String[] args){
        testConnexion();
        testParseMails();

    }
}
