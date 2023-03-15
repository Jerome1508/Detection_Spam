/*
 * Application Detection Spam                                                                       14/03/2023
 * Groupe Romain PALAYRET, Alexis RAVAYROL, Marin BAILHE, Jérôme CHIROL
 * 3il Ingenieur Rodez
 */
package com.example.detection_spam.model.test;

import com.example.detection_spam.model.Dictionary;
import com.example.detection_spam.model.Mail;

/**
 * Classe qui va effectuer les tests unitaires sur la classe Dictionnaire
 * @author Alexis RAVAYROL, Romain PALAYRET
 * @version 1.0.0
 */
public class TestDictionnaire {

    /**
     * méthode qui test le constructeur de la class dictionnaire
     */
    private static void testConstructeur() {
        // on essaie de creer un Dictionnaire
        Dictionary dico1 = new Dictionary();
    }

    /**
     * TODO méthode qui test le constructeur XML de la class dictionnaire
     */
    private static void testConstructeurXML() {

    }

    /**
     * méthode qui test le getProbaSpam()
     * Il s'agit d'une méthode privée, donc il faut penser à la mettre en public pour les tests.
     */
    private static void testgetProbaSpam() {
        int nbTestReussi = 0;
        Dictionary dico1 = new Dictionary();

        dico1.majProbaOrAdd("promo", true);
        dico1.majProbaOrAdd("promo", true);
        dico1.majProbaOrAdd("promo", false);
        dico1.majProbaOrAdd("promo", true);
        nbTestReussi += dico1.getProbaSpam("promo") == 0.75 ? 1 : 0;
        nbTestReussi += dico1.getProbaSpam("mot inconnu") == 0 ? 1 : 0;

        System.out.println("getProbaSpam() : Test reussi => " + nbTestReussi + "/2");

    }

    /**
     * méthode qui test le getProbaNotSpam()
     */
    private static void testgetProbaNotSpam() {
        int nbTestReussi = 0;
        Dictionary dico1 = new Dictionary();

        dico1.majProbaOrAdd("promo", true);
        dico1.majProbaOrAdd("promo", true);
        dico1.majProbaOrAdd("promo", false);
        dico1.majProbaOrAdd("promo", true);

        nbTestReussi += dico1.getProbaNotSpam("promo") == 0.25 ? 1 : 0;
        nbTestReussi += dico1.getProbaNotSpam("mot inconnu") == 0 ? 1 : 0;

        System.out.println("getProbaNotSpam() : Test reussi => " + nbTestReussi + "/2");
    }



    /**
     * méthode qui test la methode majProbaOrAdd()
     * Cette méthode ajoute un mot au dictionnaire s'il n'existe pas déjà
     * ou met a jour sa proba s'il est déjà présent
     */
    private static void testMajProbaOrAdd() {
        int nbTestReussi = 0;
        Dictionary dico1 = new Dictionary();

        // Test 1 : on ajoute un mot spam
        dico1.majProbaOrAdd("test1", true);
        nbTestReussi += dico1.getProbaSpam("test1") == 1 ? 1 : 0;
        nbTestReussi += dico1.getProbaNotSpam("test1") == 0 ? 1 : 0;

        // Test 2 : on ajoute un mot non spam
        dico1.majProbaOrAdd("test2", false);
        nbTestReussi += dico1.getProbaNotSpam("test2") == 1 ? 1 : 0;
        nbTestReussi += dico1.getProbaSpam("test2") == 0 ? 1 : 0;

        // Test 3 : on ajoute un mot spam, et on lui met a jour sa proba spam
        dico1.majProbaOrAdd("test3", true);
        dico1.majProbaOrAdd("test3", true);
        nbTestReussi += dico1.getProbaSpam("test3") == 1 ? 1 : 0;
        nbTestReussi += dico1.getProbaNotSpam("test3") == 0 ? 1 : 0;

        // Test 4 : on ajoute un mot spam, et on lui met a jour sa proba non spam
        dico1.majProbaOrAdd("test4", true);
        dico1.majProbaOrAdd("test4", false);
        nbTestReussi += dico1.getProbaSpam("test4") == 0.5 ? 1 : 0;
        nbTestReussi += dico1.getProbaNotSpam("test4") == 0.5 ? 1 : 0;

        // Test 5 : on ajoute un mot non spam, et on lui met a jour sa proba non spam
        dico1.majProbaOrAdd("test5", false);
        dico1.majProbaOrAdd("test5", false);
        nbTestReussi += dico1.getProbaSpam("test5") == 0 ? 1 : 0;
        nbTestReussi += dico1.getProbaNotSpam("test5") == 1 ? 1 : 0;

        // Test 6 : on ajoute un mot non spam, et on lui met a jour sa proba spam
        dico1.majProbaOrAdd("test6", false);
        dico1.majProbaOrAdd("test6", true);
        nbTestReussi += dico1.getProbaSpam("test6") == 0.5 ? 1 : 0;
        nbTestReussi += dico1.getProbaNotSpam("test6") == 0.5 ? 1 : 0;

        // Test 7 : on ajoute un mot , et on lui met a jour sa proba plusieurs fois
        dico1.majProbaOrAdd("test7", true);
        dico1.majProbaOrAdd("test7", true);
        dico1.majProbaOrAdd("test7", false);
        dico1.majProbaOrAdd("test7", true);
        nbTestReussi += dico1.getProbaSpam("test7") == 0.75 ? 1 : 0;
        nbTestReussi += dico1.getProbaNotSpam("test7") == 0.25 ? 1 : 0;

        System.out.println("majProbaOrAdd() : Test reussi => " + nbTestReussi + "/14");

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

        // on sauvegarde ce dictionnaire dans un fichier
        dico1.saveDico("sauvegarde.ser");
    }

    /**
     *  méthode qui test la dé-serializable()
     */
    private static void testDeserialisable() {
        Dictionary dico2 = new Dictionary("sauvegarde.ser");

        System.out.println(dico2.getProbaSpam("test7") == 0.75 ? "de-serialisation reussie" : "echec");
    }
    

    /**
     * lance les différents tests
     * @param args
     */
    public static void main (String[] args) {
        testgetProbaSpam();
        testgetProbaNotSpam();
        testMajProbaOrAdd();
        testSerialisable();
        testDeserialisable();
    }
}
