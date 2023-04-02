/*
 * Application Detection Spam                                                                       18/03/2023
 * Groupe Romain PALAYRET, Alexis RAVAYROL, Marin BAILHE, Jérôme CHIROL
 * 3il Ingenieur Rodez
 */
package com.example.detection_spam.algorithm;

import com.example.detection_spam.model.Dictionary;
import com.example.detection_spam.model.Mail;
import com.example.detection_spam.model.State;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe possédant plusieurs algorithmes nécessaire à l'apprentissage et à la détection de spam
 * @author Alexis RAVAYROL, Romain PALAYRET, Jérôme CHIROL
 * @version 1.0.0
 */
public class Algorithm {

    private static Logger logger = Logger.getLogger(Algorithm.class.getPackage().getName());

    /**
     * Constructeur privé pour éviter de creer une instance de cette classe
     */
    private Algorithm() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Met à jour le dictionnaire pris en paramètre avec les données des mails pris en arguments.
     * @param mails une liste de mail. Tous les mails de cette liste sont soit tous des spams, soit tous des non_spams
     * @param spam true si mails ne contient que des spams, false s'il ne contient que des non_spam
     * @param dicoWord le dictionnaire à compléter
     */
    public static void learning(List<Mail> mails, boolean spam, Dictionary dicoWord) {

        final String SPLITTER = " ";

        // on parcourt les mails un par un
        for ( Mail mail: mails) {

            List<String> encounteredWords = new ArrayList<>();

            if(spam) {
                dicoWord.addSpamToLearn();
            } else {
                dicoWord.addNotSpamToLearn();
            }

            // on sépare les mots du mail
            String content = mail.getText();
            String[] words = content.split(SPLITTER);

            // on traite les mots un par un
            for (int i = 0; i < words.length; i++) {
                if(!encounteredWords.contains(words[i])) {
                    dicoWord.majProbaOrAdd(words[i], spam);
                }
            }

        }

        dicoWord.saveDico("dicoSaved.ser");

    }

    /**
     * Analyse les mails pris en paramètre et change l'etat UNTREATED en l'etat correspondant :
     *  - SPAM si le mail est un spam
     *  - NOT_SPAM si le mail n'en est pas un
     * @param mails la liste des mails à traiter
     * @param dicoWord le dictionnaire utilisé pour juger les mots
     * @param filter le % à partir duquel on considère qu'un mail est un spam
     */
    public static void analyse(List<Mail> mails, Dictionary dicoWord, double filter) {
        final String SPLITTER = " ";

        // on parcourt les mails un par un
        for ( Mail mail: mails) {

            double probaMail = 0.00;
            int nbTreatableWord = 0;

            // on sépare les mots du mail
            String content = mail.getText();
            String[] words = content.split(SPLITTER);

            // on traite les mots un par un
            for (int i = 0; i < words.length; i++) {
                if(dicoWord.containsWord(words[i])) {
                    nbTreatableWord ++;
                    probaMail += dicoWord.getProbaSpam(words[i]);
                }
            }

            if(nbTreatableWord == 0) {
                throw new ArithmeticException("Le mail ne contient aucun mot connu");
            }
            probaMail = probaMail / nbTreatableWord;

            logger.log( Level.INFO, String.format("Probabilite du mail : %d%",probaMail));
            if(probaMail > filter) {
                mail.setState(State.SPAM);
            } else {
                mail.setState(State.NOT_SPAM);
            }

        }
    }

}
