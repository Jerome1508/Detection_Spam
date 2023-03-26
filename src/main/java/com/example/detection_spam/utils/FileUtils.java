package com.example.detection_spam.utils;

import com.example.detection_spam.model.Mail;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Classe permettant de récupérer des messages textes pour en faire des objets mail
 * @author Marin BAILHE, Jérôme CHIROL
 * @version 1.0.0
 */
public class FileUtils {
    public static Mail parseFile(String filePath) {
        if (filePath.endsWith(".txt")) {
            Path P1 = Paths.get(filePath);
            String subject = "";
            try(BufferedReader br = Files.newBufferedReader(P1)){
                StringBuilder res = new StringBuilder();
                String lines = br.readLine();
                if (lines != null)
                    subject = lines;
                while(lines != null){
                    res.append(lines);
                    lines = br.readLine();
                }
                return new Mail(res.toString(), subject);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }


    public static ArrayList<Mail> parseFolder(String folderPath) {
        ArrayList<Mail> mails = new ArrayList<Mail>();
        File folder = new File(folderPath);
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    Mail mail = parseFile(file.getPath());
                    mails.add(mail);
                }
            }
            return mails;
        }
        else
            return null;
    }
}
