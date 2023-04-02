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
    static final String testContent = "Ceci est un test";
    static final String testSubject = "ceci est un sujet de mail";

    /**
     * méthode qui test le constructeur de la classe mail
     */
    private static void testConstructeur() {
        // on essaie de creer un mail
        new Mail(testContent, testSubject);

        // on essaie de creer un mail avec du text vide
        new Mail("", "");

        // on essaie de creer un mail avec des sauts de lignes
        new Mail("Ceci est un /n test", testSubject);

        try {
            new Mail(null, null);
            System.err.println("Le contenu du mail peut etre null, alors que ce n'est pas le comportement attendu");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * méthode qui test le getText() de la class mail
     */
    private static void testGetText() {
        String content = TestMail.testContent;

        Mail mail1 = new Mail(content, testSubject);

        if(! mail1.getText().equals(content)) {
            System.err.println("Le getText() renvoie : \"" + mail1.getText() + "\" alors qu'on attendait \"" + content + "\"...");
        } else {
            System.out.println("test getText() passe avec Succes");
        }

    }

    /**
     * méthode qui test le getSubject() de la class mail
     */
    private static void testGetSubject() {
        String content = TestMail.testContent;

        Mail mail1 = new Mail(content, testSubject);

        if(! mail1.getSubject().equals(testSubject)) {
            System.err.println("Le getSubject() renvoie : \"" + mail1.getSubject() + "\" alors qu'on attendait \"" + testSubject + "\"...");
        } else {
            System.out.println("test getSubject() passe avec Succes");
        }

    }


    /**
     * méthode qui test le getEtat() et setEtat() de la classe mail
     */
    private static void testGetSetEtat() {
        String content = TestMail.testContent;
        int nbSuccessfulTest = 0;
        final String ERR_SENTENCE = "Le getEtat() renvoie : [t1] alors qu'on attendait [t2]...";
        Mail mail1 = new Mail(content, testSubject);

        // test non traitee par defaut
        if(! mail1.getState().equals(State.UNTREATED)) {
            System.err.println(ERR_SENTENCE.replace("[t1]", String.valueOf(mail1.getState())).replace( "[t2]", String.valueOf(State.UNTREATED)));
        } else {
            nbSuccessfulTest ++;
        }

        // test spam
        mail1.setState(State.SPAM);
        if(! mail1.getState().equals(State.SPAM)) {
            System.err.println(ERR_SENTENCE.replace("[t1]", String.valueOf(mail1.getState())).replace( "[t2]", String.valueOf(State.SPAM)));
        } else {
            nbSuccessfulTest ++;
        }

        // test non spam
        mail1.setState(State.NOT_SPAM);
        if(! mail1.getState().equals(State.NOT_SPAM)) {
            System.err.println(ERR_SENTENCE.replace("[t1]", String.valueOf(mail1.getState())).replace( "[t2]", String.valueOf(State.NOT_SPAM)));
        } else {
            nbSuccessfulTest ++;
        }

        // test non traitee
        mail1.setState(State.UNTREATED);
        if(! mail1.getState().equals(State.UNTREATED)) {
            System.err.println(ERR_SENTENCE.replace("[t1]", String.valueOf(mail1.getState())).replace( "[t2]", String.valueOf(State.UNTREATED)));
        } else {
            nbSuccessfulTest ++;
        }

        System.out.println("Tests getEtat/setEtat reussis : " + nbSuccessfulTest + "/4 ");

    }

    /**
     * méthode qui test le toString() de la classe mail
     */
    public static void testToString() {
        String content = TestMail.testContent;

        Mail mail1 = new Mail(content, testSubject);

        if(mail1.toString().equals(testSubject)) {
            System.out.println("test toString() passe avec Succes");
        } else {
            System.err.println("Le toString() renvoie : \"" + mail1.toString() + "\" alors qu'on attendait \"" + testSubject + "\"...");
        }
    }

    /**
     * lance les différents tests
     * @param args
     */
    public static void main(String[] args) {
        testConstructeur();
        testGetText();
        testGetSetEtat();
        testGetSubject();
        testToString();
    }
}
