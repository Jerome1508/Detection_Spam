/*
 * Application Detection Spam                                                                       14/03/2023
 * Groupe Romain PALAYRET, Alexis RAVAYROL, Marin BAILHE, Jérôme CHIROL
 * 3il Ingenieur Rodez
 */

package com.example.detection_spam.utils.test;

import com.example.detection_spam.model.Mail;
import com.example.detection_spam.utils.FileUtils;

import java.util.ArrayList;

/**
 * Classe qui va effectuer des tests sur la classe FilesUtils
 * @author Marin BAILHE, Jérôme CHIROL
 * @version 1.0.0
 */
public class TestFileUtils {
    /**
     * TODO méthode qui test le constructeur de la class dictionnaire
     */
    private static void testParseFile() {
        Mail mailtest = FileUtils.parseFile("src/main/resources/Data/base_text/Test.txt");
        System.out.println(mailtest.getText());
    }

    private static void testParseFolder() {
        ArrayList<Mail> mails = FileUtils.parseFolder("src/main/resources/Data/base_text");
        mails.forEach(x -> System.out.println(x.getText()));
    }

    public static void main (String[] args) {
        testParseFolder();

    }
}
