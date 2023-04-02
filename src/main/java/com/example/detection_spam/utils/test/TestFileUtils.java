/*
 * Application Detection Spam                                                                       14/03/2023
 * Groupe Romain PALAYRET, Alexis RAVAYROL, Marin BAILHE, Jérôme CHIROL
 * 3il Ingenieur Rodez
 */

package com.example.detection_spam.utils.test;

import com.example.detection_spam.model.Mail;
import com.example.detection_spam.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;

/**
 * Classe qui va effectuer des tests sur la classe FilesUtils
 * @author Marin BAILHE, Jérôme CHIROL
 * @version 1.0.0
 */
public class TestFileUtils {
    /**
     * méthode qui teste le constructeur de la class dictionnaire
     */
    private static void testParseFile() {
        Mail mailtest = FileUtils.parseFile("src/main/resources/Data/base_text/Test.txt");
        System.out.println(mailtest.getText());
    }

    private static void testParseWrongFile() {
        Mail mailtest = FileUtils.parseFile("pom.xml");
        if (mailtest == null)
            System.out.println("fichier incorrect détecté");
        else
            System.out.println("erreur de lecture de fichier incorrect");
    }

    private static void testParseFolder() {
        ArrayList<Mail> mails = FileUtils.parseFolder("src/main/resources/Data/base_text");
        mails.forEach(x -> System.out.println(x.getText()));
    }

    private static void testParseWrongFolder() {
        ArrayList<Mail> mails = FileUtils.parseFolder("src/main/resources/Data/base_text/Test.txt");
        if (mails == null)
            System.out.println("dossier incorrect détecté");
        else
            System.out.println("erreur de lecture de dossier incorrect");
    }

    public static void main (String[] args) {
        System.out.println("TESTS FICHIERS :\n");
        testParseFile();
        testParseWrongFile();
        System.out.println("\nTESTS DOSSIERS :\n");
        testParseFolder();
        testParseWrongFolder();
    }
}
