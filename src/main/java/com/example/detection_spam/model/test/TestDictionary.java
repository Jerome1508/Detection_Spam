/*
 * Application Detection Spam                                                                       14/03/2023
 * Groupe Romain PALAYRET, Alexis RAVAYROL, Marin BAILHE, Jérôme CHIROL
 * 3il Ingenieur Rodez
 */
package com.example.detection_spam.model.test;

import com.example.detection_spam.model.Dictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Classe qui va effectuer les tests unitaires sur la classe Dictionnaire
 * @author Alexis RAVAYROL, Romain PALAYRET
 * @version 1.0.0
 */
public class TestDictionary {

    private static Logger logger = Logger.getLogger(TestDictionary.class.getPackage().getName());

    /**
     * méthode qui test le constructeur de la classe dictionnaire
     */
    private static void testConstructor() {
        // on essaie de creer un Dictionnaire
        new Dictionary();
    }

    /**
     * méthode qui test le getProbaSpam()
     * il s'agit d'une méthode privée, donc il faut penser à la mettre en public pour les tests.
     */
    private static void testgetProbaSpam() {
        int nbSuccessfulTest = 0;
        final String WORD_TEST = "promo";
        Dictionary dico1 = new Dictionary();

        dico1.addSpamToLearn();
        dico1.addSpamToLearn();
        dico1.addSpamToLearn();
        dico1.addNotSpamToLearn();

        dico1.majProbaOrAdd(WORD_TEST, true);
        dico1.majProbaOrAdd(WORD_TEST, true);
        dico1.majProbaOrAdd(WORD_TEST, false);
        dico1.majProbaOrAdd(WORD_TEST, true);
        nbSuccessfulTest += dico1.getProbaSpam(WORD_TEST) == 0.5 ? 1 : 0;
        nbSuccessfulTest += dico1.getProbaSpam("mot inconnu") == 0 ? 1 : 0;


        logger.log(Level.INFO, String.format("getProbaSpam() : Test reussi => %d/2", nbSuccessfulTest));


    }

    /**
     * méthode qui test le getProbaNotSpam()
     */
    private static void testgetProbaNotSpam() {
        int nbSuccessfulTest = 0;
        final String WORD_TEST = "promo";
        Dictionary dico1 = new Dictionary();

        dico1.addSpamToLearn();
        dico1.addSpamToLearn();
        dico1.addSpamToLearn();
        dico1.addNotSpamToLearn();
        dico1.addNotSpamToLearn();
        dico1.addNotSpamToLearn();

        dico1.majProbaOrAdd(WORD_TEST, true);
        dico1.majProbaOrAdd(WORD_TEST, true);
        dico1.majProbaOrAdd(WORD_TEST, false);
        dico1.majProbaOrAdd(WORD_TEST, true);

        nbSuccessfulTest += dico1.getProbaNotSpam(WORD_TEST) == 0.25 ? 1 : 0;
        nbSuccessfulTest += dico1.getProbaNotSpam("mot inconnu") == 0 ? 1 : 0;

        logger.log( Level.INFO, String.format("getProbaNotSpam() : Test reussi => %d/2",nbSuccessfulTest));
    }

    /**
     * méthode qui test la methode majProbaOrAdd()
     * Cette méthode ajoute un mot au dictionnaire s'il n'existe pas déjà
     * ou met a jour sa probabilité s'il est déjà présent
     */
    private static void testMajProbaOrAdd() {
        int nbSuccessfulTest = 0;
        Dictionary dico1 = new Dictionary();

        List<String> wordsTest = new ArrayList<>();
        wordsTest.add("test1");
        wordsTest.add("test2");
        wordsTest.add("test3");
        wordsTest.add("test4");
        wordsTest.add("test5");
        wordsTest.add("test6");
        wordsTest.add("test7");

        // Test 1 : on ajoute un mot spam
        dico1.majProbaOrAdd(wordsTest.get(0), true);
        nbSuccessfulTest += dico1.getProbaSpam(wordsTest.get(0)) == 1 ? 1 : 0;
        nbSuccessfulTest += dico1.getProbaNotSpam(wordsTest.get(0)) == 0 ? 1 : 0;

        // Test 2 : on ajoute un mot non spam
        dico1.majProbaOrAdd(wordsTest.get(1), false);
        nbSuccessfulTest += dico1.getProbaNotSpam(wordsTest.get(1)) == 1 ? 1 : 0;
        nbSuccessfulTest += dico1.getProbaSpam(wordsTest.get(1)) == 0 ? 1 : 0;

        // Test 3 : on ajoute un mot spam, et on lui met a jour sa proba spam
        dico1.majProbaOrAdd(wordsTest.get(2), true);
        dico1.majProbaOrAdd(wordsTest.get(2), true);
        nbSuccessfulTest += dico1.getProbaSpam(wordsTest.get(2)) == 1 ? 1 : 0;
        nbSuccessfulTest += dico1.getProbaNotSpam(wordsTest.get(2)) == 0 ? 1 : 0;

        // Test 4 : on ajoute un mot spam, et on lui met a jour sa proba non spam
        dico1.majProbaOrAdd(wordsTest.get(3), true);
        dico1.majProbaOrAdd(wordsTest.get(3), false);
        nbSuccessfulTest += dico1.getProbaSpam(wordsTest.get(3)) == 0.5 ? 1 : 0;
        nbSuccessfulTest += dico1.getProbaNotSpam(wordsTest.get(3)) == 0.5 ? 1 : 0;

        // Test 5 : on ajoute un mot non spam, et on lui met a jour sa proba non spam
        dico1.majProbaOrAdd(wordsTest.get(4), false);
        dico1.majProbaOrAdd(wordsTest.get(4), false);
        nbSuccessfulTest += dico1.getProbaSpam(wordsTest.get(4)) == 0 ? 1 : 0;
        nbSuccessfulTest += dico1.getProbaNotSpam(wordsTest.get(4)) == 1 ? 1 : 0;

        // Test 6 : on ajoute un mot non spam, et on lui met a jour sa proba spam
        dico1.majProbaOrAdd(wordsTest.get(5), false);
        dico1.majProbaOrAdd(wordsTest.get(5), true);
        nbSuccessfulTest += dico1.getProbaSpam(wordsTest.get(5)) == 0.5 ? 1 : 0;
        nbSuccessfulTest += dico1.getProbaNotSpam(wordsTest.get(5)) == 0.5 ? 1 : 0;

        // Test 7 : on ajoute un mot , et on lui met a jour sa proba plusieurs fois
        dico1.majProbaOrAdd(wordsTest.get(6), true);
        dico1.majProbaOrAdd(wordsTest.get(6), true);
        dico1.majProbaOrAdd(wordsTest.get(6), false);
        dico1.majProbaOrAdd(wordsTest.get(6), true);
        nbSuccessfulTest += dico1.getProbaSpam(wordsTest.get(6)) == 0.75 ? 1 : 0;
        nbSuccessfulTest += dico1.getProbaNotSpam(wordsTest.get(6)) == 0.25 ? 1 : 0;

        logger.log( Level.INFO, String.format("majProbaOrAdd() : Test reussi => %d/14", nbSuccessfulTest));

    }



    /**
     * méthode qui test la serializable()
     */
    private static void testSerialisable() {

        List<String> wordsTest = new ArrayList<>();
        wordsTest.add("test1");
        wordsTest.add("test2");
        wordsTest.add("test3");
        wordsTest.add("test4");
        wordsTest.add("test5");
        wordsTest.add("test6");
        wordsTest.add("test8");

        // on crée un dictionnaire
        Dictionary dico1 = new Dictionary();
        dico1.majProbaOrAdd(wordsTest.get(0), true);
        dico1.majProbaOrAdd(wordsTest.get(1), false);
        dico1.majProbaOrAdd(wordsTest.get(2), true);
        dico1.majProbaOrAdd(wordsTest.get(2), true);
        dico1.majProbaOrAdd(wordsTest.get(3), true);
        dico1.majProbaOrAdd(wordsTest.get(3), false);
        dico1.majProbaOrAdd(wordsTest.get(4), false);
        dico1.majProbaOrAdd(wordsTest.get(4), false);
        dico1.majProbaOrAdd(wordsTest.get(5), false);
        dico1.majProbaOrAdd(wordsTest.get(5), true);
        dico1.majProbaOrAdd(wordsTest.get(6), true);
        dico1.majProbaOrAdd(wordsTest.get(6), true);
        dico1.majProbaOrAdd(wordsTest.get(6), false);
        dico1.majProbaOrAdd(wordsTest.get(6), true);

        dico1.addSpamToLearn();
        dico1.addSpamToLearn();
        dico1.addSpamToLearn();
        dico1.addNotSpamToLearn();
        dico1.addNotSpamToLearn();
        dico1.addNotSpamToLearn();


        // on sauvegarde ce dictionnaire dans un fichier
        dico1.saveDico("sauvegarde.ser");
    }

    /**
     *  méthode qui test la dé-serializable()
     */
    private static void testDeserialisable() {
        int nbSuccessfulTest = 0;
        Dictionary dico2 = new Dictionary("sauvegarde.ser");

        nbSuccessfulTest += dico2.getProbaSpam("test8") == 0.75 ? 1 : 0;
        nbSuccessfulTest += dico2.getNbSpamToLearn() == 3 ? 1 : 0;

        logger.log( Level.INFO, nbSuccessfulTest == 2 ? "de-serialisation reussie" : "echec");
    }

    /**
     * méthode qui test le getter de nbSpamToLearn et addSpamToLearn()
     */
    private static void testGetAddNbSpamToLearn() {
        int nbSuccessfulTest = 0;
        Dictionary dico1 = new Dictionary();

        nbSuccessfulTest += dico1.getNbSpamToLearn() == 0 ? 1 : 0;
        dico1.addSpamToLearn();
        nbSuccessfulTest += dico1.getNbSpamToLearn() == 1 ? 1 : 0;

        logger.log( Level.INFO, String.format("getNbSpamToLearn() : Test reussi => %d/2", nbSuccessfulTest));
    }

    /**
     * méthode qui test le getter de nbNotSpamToLearn et addNotSpamToLearn()
     */
    private static void testGetAddNbNotSpamToLearn() {
        int nbSuccessfulTest = 0;
        Dictionary dico1 = new Dictionary();

        nbSuccessfulTest += dico1.getNbNotSpamToLearn() == 0 ? 1 : 0;
        dico1.addNotSpamToLearn();
        nbSuccessfulTest += dico1.getNbNotSpamToLearn() == 1 ? 1 : 0;

        logger.log( Level.INFO, String.format("getNbNotSpamToLearn() : Test reussi => %d/2", nbSuccessfulTest));
    }

    /**
     * méthode qui la méthode containsWord()
     */
    private static void testContainsWord() {
        int nbSuccessfulTest = 0;
        Dictionary dico1 = new Dictionary();

        dico1.majProbaOrAdd("Bonjour", true);

        nbSuccessfulTest += dico1.containsWord("Bonjour") ? 1 : 0;
        nbSuccessfulTest += dico1.containsWord("Philippe") ? 0 : 1;

        logger.log( Level.INFO, String.format("containsWord() : Test reussi => %d/2", nbSuccessfulTest));
    }

    /**
     * lance les différents tests
     * @param args
     */
    public static void main (String[] args) {
        testConstructor();
        testgetProbaSpam();
        testgetProbaNotSpam();
        testMajProbaOrAdd();
        testSerialisable();
        testDeserialisable();
        testGetAddNbSpamToLearn();
        testGetAddNbNotSpamToLearn();
        testContainsWord();
    }
}
