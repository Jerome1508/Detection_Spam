/*
 * Application Detection Spam                                                                       14/03/2023
 * Groupe Romain PALAYRET, Alexis RAVAYROL, Marin BAILHE, Jérôme CHIROL
 * 3il Ingenieur Rodez
 */
package com.example.detection_spam.model.test;

import com.example.detection_spam.model.Dictionary;

/**
 * Classe qui va effectuer les tests unitaires sur la classe Dictionnaire
 * @author Alexis RAVAYROL, Romain PALAYRET
 * @version 1.0.0
 */
public class TestDictionary {

    /**
     * méthode qui test le constructeur de la classe dictionnaire
     */
    private static void testConstructor() {
        // on essaie de creer un Dictionnaire
        Dictionary dico1 = new Dictionary();
    }

    /**
     * méthode qui test le getProbaSpam()
     * Il s'agit d'une méthode privée, donc il faut penser à la mettre en public pour les tests.
     */
    private static void testgetProbaSpam() {
        int nbSuccessfulTest = 0;
        Dictionary dico1 = new Dictionary();

        dico1.addSpamToLearn();
        dico1.addSpamToLearn();
        dico1.addSpamToLearn();
        dico1.addNotSpamToLearn();

        dico1.majProbaOrAdd("promo", true);
        dico1.majProbaOrAdd("promo", true);
        dico1.majProbaOrAdd("promo", false);
        dico1.majProbaOrAdd("promo", true);
        nbSuccessfulTest += dico1.getProbaSpam("promo") == 0.5 ? 1 : 0;
        nbSuccessfulTest += dico1.getProbaSpam("mot inconnu") == 0 ? 1 : 0;

        System.out.println("getProbaSpam() : Test reussi => " + nbSuccessfulTest + "/2");

    }

    /**
     * méthode qui test le getProbaNotSpam()
     */
    private static void testgetProbaNotSpam() {
        int nbSuccessfulTest = 0;
        Dictionary dico1 = new Dictionary();

        dico1.addSpamToLearn();
        dico1.addSpamToLearn();
        dico1.addSpamToLearn();
        dico1.addNotSpamToLearn();
        dico1.addNotSpamToLearn();
        dico1.addNotSpamToLearn();

        dico1.majProbaOrAdd("promo", true);
        dico1.majProbaOrAdd("promo", true);
        dico1.majProbaOrAdd("promo", false);
        dico1.majProbaOrAdd("promo", true);

        nbSuccessfulTest += dico1.getProbaNotSpam("promo") == 0.25 ? 1 : 0;
        nbSuccessfulTest += dico1.getProbaNotSpam("mot inconnu") == 0 ? 1 : 0;

        System.out.println("getProbaNotSpam() : Test reussi => " + nbSuccessfulTest + "/2");
    }

    /**
     * méthode qui test la methode majProbaOrAdd()
     * Cette méthode ajoute un mot au dictionnaire s'il n'existe pas déjà
     * ou met a jour sa probabilité s'il est déjà présent
     */
    private static void testMajProbaOrAdd() {
        int nbSuccessfulTest = 0;
        Dictionary dico1 = new Dictionary();

        // Test 1 : on ajoute un mot spam
        dico1.majProbaOrAdd("test1", true);
        nbSuccessfulTest += dico1.getProbaSpam("test1") == 1 ? 1 : 0;
        System.out.println(dico1.getProbaSpam("test1"));
        nbSuccessfulTest += dico1.getProbaNotSpam("test1") == 0 ? 1 : 0;

        // Test 2 : on ajoute un mot non spam
        dico1.majProbaOrAdd("test2", false);
        nbSuccessfulTest += dico1.getProbaNotSpam("test2") == 1 ? 1 : 0;
        nbSuccessfulTest += dico1.getProbaSpam("test2") == 0 ? 1 : 0;

        // Test 3 : on ajoute un mot spam, et on lui met a jour sa proba spam
        dico1.majProbaOrAdd("test3", true);
        dico1.majProbaOrAdd("test3", true);
        nbSuccessfulTest += dico1.getProbaSpam("test3") == 1 ? 1 : 0;
        nbSuccessfulTest += dico1.getProbaNotSpam("test3") == 0 ? 1 : 0;

        // Test 4 : on ajoute un mot spam, et on lui met a jour sa proba non spam
        dico1.majProbaOrAdd("test4", true);
        dico1.majProbaOrAdd("test4", false);
        nbSuccessfulTest += dico1.getProbaSpam("test4") == 0.5 ? 1 : 0;
        nbSuccessfulTest += dico1.getProbaNotSpam("test4") == 0.5 ? 1 : 0;

        // Test 5 : on ajoute un mot non spam, et on lui met a jour sa proba non spam
        dico1.majProbaOrAdd("test5", false);
        dico1.majProbaOrAdd("test5", false);
        nbSuccessfulTest += dico1.getProbaSpam("test5") == 0 ? 1 : 0;
        nbSuccessfulTest += dico1.getProbaNotSpam("test5") == 1 ? 1 : 0;

        // Test 6 : on ajoute un mot non spam, et on lui met a jour sa proba spam
        dico1.majProbaOrAdd("test6", false);
        dico1.majProbaOrAdd("test6", true);
        nbSuccessfulTest += dico1.getProbaSpam("test6") == 0.5 ? 1 : 0;
        nbSuccessfulTest += dico1.getProbaNotSpam("test6") == 0.5 ? 1 : 0;

        // Test 7 : on ajoute un mot , et on lui met a jour sa proba plusieurs fois
        dico1.majProbaOrAdd("test7", true);
        dico1.majProbaOrAdd("test7", true);
        dico1.majProbaOrAdd("test7", false);
        dico1.majProbaOrAdd("test7", true);
        nbSuccessfulTest += dico1.getProbaSpam("test7") == 0.75 ? 1 : 0;
        nbSuccessfulTest += dico1.getProbaNotSpam("test7") == 0.25 ? 1 : 0;

        System.out.println("majProbaOrAdd() : Test reussi => " + nbSuccessfulTest + "/14");

    }



    /**
     * méthode qui test la serializable()
     */
    private static void testSerialisable() {

        // on crée un dictionnaire
        Dictionary dico1 = new Dictionary();
        dico1.majProbaOrAdd("test1", true);
        dico1.majProbaOrAdd("test2", false);
        dico1.majProbaOrAdd("test3", true);
        dico1.majProbaOrAdd("test3", true);
        dico1.majProbaOrAdd("test4", true);
        dico1.majProbaOrAdd("test4", false);
        dico1.majProbaOrAdd("test5", false);
        dico1.majProbaOrAdd("test5", false);
        dico1.majProbaOrAdd("test6", false);
        dico1.majProbaOrAdd("test6", true);
        dico1.majProbaOrAdd("test7", true);
        dico1.majProbaOrAdd("test7", true);
        dico1.majProbaOrAdd("test7", false);
        dico1.majProbaOrAdd("test7", true);

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

        nbSuccessfulTest += dico2.getProbaSpam("test7") == 0.75 ? 1 : 0;
        nbSuccessfulTest += dico2.getNbSpamToLearn() == 3 ? 1 : 0;

        System.out.println(nbSuccessfulTest == 2 ? "de-serialisation reussie" : "echec");
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

        System.out.println("getNbSpamToLearn() : Test reussi => " + nbSuccessfulTest + "/2");
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

        System.out.println("getNbNotSpamToLearn() : Test reussi => " + nbSuccessfulTest + "/2");
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

        System.out.println("containsWord() : Test reussi => " + nbSuccessfulTest + "/2");
    }

    /**
     * lance les différents tests
     * @param args
     */
    public static void main (String[] args) {
        //testConstructor();
        //testgetProbaSpam();
        //testgetProbaNotSpam();
        testMajProbaOrAdd();
        //testSerialisable();
        //testDeserialisable();
        //testGetAddNbSpamToLearn();
        //testGetAddNbNotSpamToLearn();
        //testContainsWord();
    }
}
