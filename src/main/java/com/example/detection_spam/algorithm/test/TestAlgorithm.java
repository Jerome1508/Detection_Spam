/*
 * Application Detection Spam                                                                       18/03/2023
 * Groupe Romain PALAYRET, Alexis RAVAYROL, Marin BAILHE, Jérôme CHIROL
 * 3il Ingenieur Rodez
 */
package com.example.detection_spam.algorithm.test;

import com.example.detection_spam.algorithm.Algorithm;
import com.example.detection_spam.model.Dictionary;
import com.example.detection_spam.model.Mail;
import com.example.detection_spam.model.State;
import com.example.detection_spam.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui va effectuer les tests des différents algorithmes de la classe Algorithm
 * @author Alexis RAVAYROL, Romain PALAYRET, Jérôme CHIROL
 * @version 1.0.0
 */
public class TestAlgorithm {

    /**
     * méthode qui test l'algorithme d'apprentissage de l'application
     *     - l'algorithme d'apprentissage est l'algorithme qui a pour finalité de remplir le dictionnaire de mot
     */
    private static void testLearning() {
        Dictionary dico1 = new Dictionary();

        List<Mail> spams = FileUtils.parseFolder("src/main/resources/Data/base_text/training_spam");
        Algorithm.learning(spams, true, dico1);

        List<Mail> hams = FileUtils.parseFolder("src/main/resources/Data/base_text/training_ham");
        Algorithm.learning(hams, false, dico1);

        System.out.println("\nProba SPAM : ");
        System.out.println("Proba Free : " + dico1.getProbaSpam("Free"));
        System.out.println("Proba Go : " + dico1.getProbaSpam("Go"));
        System.out.println("Proba I : " + dico1.getProbaSpam("I"));
        System.out.println("Proba you : " + dico1.getProbaSpam("you"));
        System.out.println("Proba Did : " + dico1.getProbaSpam("Did"));

        System.out.println("\nProba Non SPAM : ");
        System.out.println("Proba Free : " + dico1.getProbaNotSpam("Free"));
        System.out.println("Proba Go : " + dico1.getProbaNotSpam("Go"));
        System.out.println("Proba I : " + dico1.getProbaNotSpam("I"));
        System.out.println("Proba you : " + dico1.getProbaNotSpam("you"));
        System.out.println("Proba Did : " + dico1.getProbaNotSpam("Did"));

        System.out.println("\nSomme Proba: ");
        System.out.println("Proba Free : " + (dico1.getProbaNotSpam("Free") + dico1.getProbaSpam("Free")));
        System.out.println("Proba Go : " + (dico1.getProbaNotSpam("Go") + dico1.getProbaSpam("Go")));
        System.out.println("Proba I : " + (dico1.getProbaNotSpam("I") + dico1.getProbaSpam("I")));
        System.out.println("Proba you : " + (dico1.getProbaNotSpam("you") + dico1.getProbaSpam("you")));
        System.out.println("Proba Did : " + (dico1.getProbaNotSpam("Did") + dico1.getProbaSpam("Did")));

    }


    /**
     * méthode qui test l'algorithme d'analyse de l'application
     *     - l'algorithme d'analyse est l'algorithme qui a pour finalité de détecter les spams
     */
    private static void testAnalyse() {
        Dictionary dico1 = new Dictionary("dicoSaved.ser");

        List<Mail> toTreat = FileUtils.parseFolder("src/main/resources/Data/base_text/analyse");
        Algorithm.analyse(toTreat, dico1, 0.6);

        int nbSuccess = 0;
        int nbFailed = 0;
        int index = 0;
        for (Mail mail : toTreat ) {
            System.out.println(mail);
            if (mail.toString().contains("ham")) {
                if(mail.getState() == State.NOT_SPAM) {
                    nbSuccess ++;
                } else {
                    System.err.println("Mail n°" + index + " a ete juge SPAM et pas NOT_SPAM");
                    nbFailed ++;
                }
            } else if (mail.toString().contains("spam")) {
                if(mail.getState() == State.SPAM) {
                    nbSuccess ++;
                } else {
                    System.err.println("Mail n°" + index + " a ete juge NOT_SPAM et pas SPAM");
                    nbFailed ++;
                }
            }
//            if(index < 50) {
//                if(mail.getState() == State.NOT_SPAM) {
//                    nbSuccess ++;
//                } else {
//                    System.err.println("Mail n°" + index + " a ete juge SPAM et pas NOT_SPAM");
//                    nbFailed ++;
//                }
//            } else {
//                if(mail.getState() == State.SPAM) {
//                    nbSuccess ++;
//                } else {
//                    System.err.println("Mail n°" + index + " a ete juge NOT_SPAM et pas SPAM");
//                    nbFailed ++;
//                }
//            }
            index ++;

        }

        System.out.println("Succes : " + nbSuccess);
        System.out.println("Echec : " + nbFailed);
        System.out.println("proportion de réussite : "+ (float) nbSuccess * 100 / (nbFailed + nbSuccess) + "%");
    }

    private static void deleteSerializedDictionnary() {
        File dictionnary = new File("dicoSaved.ser");
        if (dictionnary.delete())
            System.out.println("Dicosaved supprimé");
        else
            System.out.println("Dicosaved n'existe pas, pas de suppression");
    }

    /**
     * lance les différents tests
     * @param args
     */
    public static void main (String[] args) {
        try {
            deleteSerializedDictionnary();
        } catch (Exception e) {
            System.err.println(e);
        }
        testLearning();
        testAnalyse();
    }
}
