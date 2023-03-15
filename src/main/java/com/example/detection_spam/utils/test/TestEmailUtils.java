package com.example.detection_spam.utils.test;

import com.example.detection_spam.model.Mail;
import com.example.detection_spam.utils.FileUtils;

import javax.mail.MessagingException;
import java.util.ArrayList;

import static com.example.detection_spam.utils.EmailUtils.emailConnect;

public class TestEmailUtils {
    private static void testConnexion()  {
        try {
            System.out.println(emailConnect());

        }catch(Exception e){
            System.err.println(e);
        }

    }


    public static void main (String[] args){
        testConnexion();

    }
}
