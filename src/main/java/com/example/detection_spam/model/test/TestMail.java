/*
 * Application Detection Spam                                                                       14/03/2023
 * Groupe Romain PALAYRET, Alexis RAVAYROL, Marin BAILHE, Jérôme CHIROL
 * 3il Ingenieur Rodez
 */
package com.example.detection_spam.model.test;

import com.example.detection_spam.model.State;
import com.example.detection_spam.model.Mail;

/**
 * Classe qui va effectuer les tests unitaires sur la classe Mail
 * @author Alexis RAVAYROL, Romain PALAYRET
 * @version 1.0.0
 */
public class TestMail {

    /**
     * méthode qui test le constructeur de la classe mail
     */
    private static void testConstructeur() {
        // on essaie de creer un mail
        Mail mail1 = new Mail("Ceci est un test");

        // on essaie de creer un mail avec du text vide
        Mail mail2 = new Mail("");

        // on essaie de creer un mail avec des sauts de lignes
        Mail mail3 = new Mail("Ceci est un /n test");

        // on essaie de creer un mail avec un content == a null;
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
        String content = "Ceci est un test";

        Mail mail1 = new Mail(content);

        if(! mail1.getText().equals(content)) {
            System.err.println("Le getText() renvoie : \"" + mail1.getText() + "\" alors qu'on attendait \"" + content + "\"...");
        } else {
            System.out.println("test getText() passe avec Succes");
        }

    }


    /**
     * méthode qui test le getEtat() et setEtat() de la classe mail
     */
    private static void testGetSetEtat() {
        String content = "Ceci est un test";
        int nbSuccessfulTest = 0;
        Mail mail1 = new Mail(content);

        // test non traitee par defaut
        if(! mail1.getState().equals(State.UNTREATED)) {
            System.err.println("Le getEtat() renvoie : \"" + mail1.getState() + "\" alors qu'on attendait \"" + State.UNTREATED + "\"...");
        } else {
            nbSuccessfulTest ++;
        }

        // test spam
        mail1.setState(State.SPAM);
        if(! mail1.getState().equals(State.SPAM)) {
            System.err.println("Le getEtat() renvoie : \"" + mail1.getState() + "\" alors qu'on attendait \"" + State.SPAM + "\"...");
        } else {
            nbSuccessfulTest ++;
        }

        // test non spam
        mail1.setState(State.NOT_SPAM);
        if(! mail1.getState().equals(State.NOT_SPAM)) {
            System.err.println("Le getEtat() renvoie : \"" + mail1.getState() + "\" alors qu'on attendait \"" + State.NOT_SPAM + "\"...");
        } else {
            nbSuccessfulTest ++;
        }

        // test non traitee
        mail1.setState(State.UNTREATED);
        if(! mail1.getState().equals(State.UNTREATED)) {
            System.err.println("Le getEtat() renvoie : \"" + mail1.getState() + "\" alors qu'on attendait \"" + State.UNTREATED + "\"...");
        } else {
            nbSuccessfulTest ++;
        }

        System.out.println("Tests getEtat/setEtat reussis : " + nbSuccessfulTest + "/4 ");

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
