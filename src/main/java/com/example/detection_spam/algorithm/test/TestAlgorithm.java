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

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe qui va effectuer les tests des différents algorithmes de la classe Algorithm
 * @author Alexis RAVAYROL, Romain PALAYRET, Jérôme CHIROL
 * @version 1.0.0
 */
public class TestAlgorithm {

    private static Logger logger = Logger.getLogger(TestAlgorithm.class.getPackage().getName());

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

        logger.log( Level.INFO, String.format("\nProba SPAM : \n" +
                "Proba Free : %.2f\n" +
                "Proba I : %.2f\n" +
                "Proba GO : %.2f\n" +
                "Proba you : %.2f\n" +
                "Proba Did : %.2f\n",
                dico1.getProbaSpam("Free"),
                dico1.getProbaSpam("Go"),
                dico1.getProbaSpam("I"),
                dico1.getProbaSpam("you"),
                dico1.getProbaSpam("Did")));

        logger.log( Level.INFO, String.format("\nProba Non SPAM : \n" +
                        "Proba Free : %.2f\n" +
                        "Proba I : %.2f\n" +
                        "Proba GO : %.2f\n" +
                        "Proba you : %.2f\n" +
                        "Proba Did : %.2f\n",
                dico1.getProbaNotSpam("Free"),
                dico1.getProbaNotSpam("Go"),
                dico1.getProbaNotSpam("I"),
                dico1.getProbaNotSpam("you"),
                dico1.getProbaNotSpam("Did")));

        logger.log( Level.INFO, String.format("\nSomme Proba: : \n" +
                        "Proba Free : %f\n" +
                        "Proba I : %f\n" +
                        "Proba GO : %f\n" +
                        "Proba you : %f\n" +
                        "Proba Did : %f\n",
                (dico1.getProbaNotSpam("Free") + dico1.getProbaSpam("Free")),
                (dico1.getProbaNotSpam("Go") + dico1.getProbaSpam("Go")),
                (dico1.getProbaNotSpam("I") + dico1.getProbaSpam("I")),
                (dico1.getProbaNotSpam("you") + dico1.getProbaSpam("you")),
                (dico1.getProbaNotSpam("Did") + dico1.getProbaSpam("Did"))));



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
            if (mail.toString().contains("ham")) {
                if(mail.getState() == State.NOT_SPAM) {
                    nbSuccess ++;
                } else {
                    logger.log( Level.WARNING, String.format("Mail n°%d a ete juge SPAM et pas NOT_SPAM", index));
                    nbFailed ++;
                }
            } else if (mail.toString().contains("spam")) {
                if(mail.getState() == State.SPAM) {
                    nbSuccess ++;
                } else {
                    logger.log( Level.WARNING, String.format("Mail n°%d a ete juge NOT_SPAM et pas SPAM", index));
                    nbFailed ++;
                }
            }

            index ++;

        }

        if(nbFailed + nbSuccess != 0) {
            logger.log(Level.INFO, String.format("Succes : %d\n" +
                            "Echec : %d\n" +
                            "Proportion de réussite : %f%%",
                    nbSuccess, nbFailed,
                    (float) nbSuccess * 100 / (nbFailed + nbSuccess)));
        }
    }

    /**
     * lance les différents tests
     * @param args
     */
    public static void main (String[] args) {
        testLearning();
        testAnalyse();
    }
}
