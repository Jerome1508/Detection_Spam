/*
 * Application Detection Spam                                                                       14/03/2023
 * Groupe Romain PALAYRET, Alexis RAVAYROL, Marin BAILHE, Jérôme CHIROL
 * 3il Ingenieur Rodez
 */

package com.example.detection_spam.utils.test;

import com.example.detection_spam.model.Mail;
import com.example.detection_spam.utils.FileUtils;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe qui va effectuer des tests sur la classe FilesUtils
 * @author Marin BAILHE, Jérôme CHIROL
 * @version 1.0.0
 */
public class TestFileUtils {
    private static Logger logger = Logger.getLogger(TestFileUtils.class.getPackage().getName());

    /**
     * méthode qui teste le constructeur de la class dictionnaire
     */
    private static void testParseFile() {
        Mail mailtest = FileUtils.parseFile("src/main/resources/Data/base_text/Test.txt");
        logger.log(Level.INFO, (mailtest.getText()));
    }

    private static void testParseWrongFile() {
        Mail mailtest = FileUtils.parseFile("pom.xml");
        if (mailtest == null)
            logger.log(Level.INFO, ("fichier incorrect détecté"));
        else
            logger.log(Level.INFO, ("erreur de lecture de fichier incorrect"));
    }

    private static void testParseFolder() {
        List<Mail> mails = FileUtils.parseFolder("src/main/resources/Data/base_text");
        mails.forEach(x -> logger.log(Level.INFO, (x.getText())));
    }

    private static void testParseWrongFolder() {
        List<Mail> mails = FileUtils.parseFolder("src/main/resources/Data/base_text/Test.txt");
        if (mails == null)
            logger.log(Level.INFO, ("dossier incorrect détecté"));
        else
            logger.log(Level.INFO, ("erreur de lecture de dossier incorrect"));
    }

    public static void main (String[] args) {
        logger.log(Level.INFO, ("TESTS FICHIERS :\n"));
        testParseFile();
        testParseWrongFile();
        logger.log(Level.INFO, ("\nTESTS DOSSIERS :\n"));
        testParseFolder();
        testParseWrongFolder();
    }
}
