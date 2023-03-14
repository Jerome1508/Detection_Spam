/*
 * Application Detection Spam                                                                       14/03/2023
 * Groupe Romain PALAYRET, Alexis RAVAYROL, Marin BAILHE, Jérôme CHIROL
 * 3il Ingenieur Rodez
 */

package com.example.detection_spam.utils.test;

import com.example.detection_spam.model.Mail;
import com.example.detection_spam.utils.FileUtils;

public class TestFileUtils {
    /**
     * TODO méthode qui test le constructeur de la class dictionnaire
     */
    private static void testParseFile() {
        Mail mailtest = FileUtils.parseFile("C:\\Users\\m.bailhe\\IdeaProjects\\Detection_Spam\\src\\main\\resources\\Data\\Test.txt");
        System.out.println(mailtest.getText());
    }

    public static void main (String[] args) {
        testParseFile();
    }
}
