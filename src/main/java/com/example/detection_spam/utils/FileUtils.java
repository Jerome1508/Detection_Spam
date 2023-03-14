package com.example.detection_spam.utils;

import com.example.detection_spam.model.Mail;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

public class FileUtils {
    public Mail parseFile(String filePath) {
        Mail mail = new Mail();


        return mail;
    }


    public ArrayList<Mail> parseFolder(String folderPath) {
        ArrayList<Mail> mails = new ArrayList<Mail>();
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                Mail mail = parseFile(file.getPath());
                mails.add(mail);
            }
        }
        return mails;
    }
}
