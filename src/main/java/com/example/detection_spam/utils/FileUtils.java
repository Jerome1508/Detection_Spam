package com.example.detection_spam.utils;

import com.example.detection_spam.model.Mail;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Classe permettant de récupérer des messages textes pour en faire des objets mail
 * @author Marin BAILHE, Jérôme CHIROL
 * @version 1.0.0
 */
public class FileUtils {
    /**
     * Constructeur privé pour éviter de créer une instance de cette classe
     */
    private FileUtils() {
        throw new IllegalStateException("Utility class");
    }
    public static Mail parseFile(String filePath) {
        if (filePath.endsWith(".txt")) {
            Path p1 = Paths.get(filePath);
            String subject = "";
            try(BufferedReader br = Files.newBufferedReader(p1)){
                StringBuilder res = new StringBuilder();
                String lines = br.readLine();
                subject = new File(filePath).getName();
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


    public static List<Mail> parseFolder(String folderPath) {
        ArrayList<Mail> mails = new ArrayList<>();
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
            return new ArrayList<>();
    }
}
