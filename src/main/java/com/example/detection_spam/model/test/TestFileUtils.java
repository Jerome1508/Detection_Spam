/*
 * Application Detection Spam                                                                       14/03/2023
 * Groupe Romain PALAYRET, Alexis RAVAYROL, Marin BAILHE, Jérôme CHIROL
 * 3il Ingenieur Rodez
 */
package com.example.detection_spam.model.test;

import com.example.detection_spam.model.Etat;
import com.example.detection_spam.model.Mail;

/**
 * Classe qui va effectuer les tests unitaires sur la classe Mail
 * @author Alexis RAVAYROL, Romain PALAYRET
 * @version 1.0.0
 */
public class TestFileUtils {

    /**
     * méthode qui test le constructeur de la class mail
     */
    private static void testConstructeur() {
        // on essaie de creer un mail
        Mail mail1 = new Mail("Ceci est un test");

        // on essaie de creer un mail avec du text vide
        Mail mail2 = new Mail("");

        // on essaie de creer un mail avec des sauts de lignes
        Mail mail3 = new Mail("Ceci est un /n test");

        // on essaie de creer un mail avec un contenu == a null;
        try {
            Mail mail4 = new Mail(null);
            System.err.println("Le contenu du mail peut etre null, alors que ce n'est pas le comportement attendu");
        } catch (IllegalArgumentException e) {
        }
    }

    /**
     * méthode qui test le getText() de la class mail
     */
    private static void testgetText() {
        String contenu = "Ceci est un test";

        Mail mail1 = new Mail(contenu);

        if(! mail1.getText().equals(contenu)) {
            System.err.println("Le getText() renvoie : \"" + mail1.getText() + "\" alors qu'on attendait \"" + contenu + "\"...");
        } else {
            System.out.println("test getText() passe avec Succes");
        }

    }


    /**
     * méthode qui test le getEtat() et setEtat de la class mail
     */
    private static void testGetSetEtat() {
        String contenu = "Ceci est un test";
        int nbTestReussi = 0;
        Mail mail1 = new Mail(contenu);

        // test non traitee par defaut
        if(! mail1.getEtat().equals(Etat.NON_TRAITE)) {
            System.err.println("Le getEtat() renvoie : \"" + mail1.getEtat() + "\" alors qu'on attendait \"" + Etat.NON_TRAITE + "\"...");
        } else {
            nbTestReussi ++;
        }

        // test spam
        mail1.setEtat(Etat.SPAM);
        if(! mail1.getEtat().equals(Etat.SPAM)) {
            System.err.println("Le getEtat() renvoie : \"" + mail1.getEtat() + "\" alors qu'on attendait \"" + Etat.SPAM + "\"...");
        } else {
            nbTestReussi ++;
        }

        // test non spam
        mail1.setEtat(Etat.NON_SPAM);
        if(! mail1.getEtat().equals(Etat.NON_SPAM)) {
            System.err.println("Le getEtat() renvoie : \"" + mail1.getEtat() + "\" alors qu'on attendait \"" + Etat.NON_SPAM + "\"...");
        } else {
            nbTestReussi ++;
        }

        // test non traitee
        mail1.setEtat(Etat.NON_TRAITE);
        if(! mail1.getEtat().equals(Etat.NON_TRAITE)) {
            System.err.println("Le getEtat() renvoie : \"" + mail1.getEtat() + "\" alors qu'on attendait \"" + Etat.NON_TRAITE + "\"...");
        } else {
            nbTestReussi ++;
        }

        System.out.println("Tests getEtat/setEtat reussis : " + nbTestReussi + "/4 ");

    }

    /**
     * lance les différents tests
     * @param args
     */
    public static void main(String[] args) {
        testConstructeur();
        testgetText();
        testGetSetEtat();
    }
}
