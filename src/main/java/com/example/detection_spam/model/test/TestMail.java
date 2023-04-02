/*
 * Application Detection Spam                                                                       14/03/2023
 * Groupe Romain PALAYRET, Alexis RAVAYROL, Marin BAILHE, Jérôme CHIROL
 * 3il Ingenieur Rodez
 */
package com.example.detection_spam.model.test;

import com.example.detection_spam.model.State;
import com.example.detection_spam.model.Mail;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe qui va effectuer les tests unitaires sur la classe Mail
 * @author Alexis RAVAYROL, Romain PALAYRET
 * @version 1.0.0
 */
public class TestMail {
    private static Logger logger = Logger.getLogger(TestMail.class.getPackage().getName());
    static final String TEST_CONTENT = "Ceci est un test";
    static final String TEST_SUBJECT = "ceci est un sujet de mail";

    /**
     * méthode qui test le constructeur de la classe mail
     */
    private static void testConstructeur() {
        // on essaie de creer un mail
        new Mail(TEST_CONTENT, TEST_SUBJECT);

        // on essaie de creer un mail avec du text vide
        new Mail("", "");

        // on essaie de creer un mail avec des sauts de lignes
        new Mail("Ceci est un /n test", TEST_SUBJECT);

        try {
            new Mail(null, null);
            logger.log(Level.SEVERE, ("Le contenu du mail peut etre null, alors que ce n'est pas le comportement attendu"));

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * méthode qui test le getText() de la class mail
     */
    private static void testGetText() {
        String content = TestMail.TEST_CONTENT;

        Mail mail1 = new Mail(content, TEST_SUBJECT);

        if(! mail1.getText().equals(content)) {
            logger.log(Level.WARNING, String.format("Le getText() renvoie : %s alors qu'on attendait : %s", mail1.getText(), content));
        } else {
            logger.log(Level.INFO, "test getText() passe avec Succes");
        }

    }

    /**
     * méthode qui test le getSubject() de la class mail
     */
    private static void testGetSubject() {
        String content = TestMail.TEST_CONTENT;

        Mail mail1 = new Mail(content, TEST_SUBJECT);

        if(! mail1.getSubject().equals(TEST_SUBJECT)) {
            logger.log(Level.WARNING, String.format("Le getSubject() renvoie : %s  alors qu'on attendait : %s ..." , mail1.getSubject(), TEST_SUBJECT));
        } else {
            logger.log(Level.INFO, "test getSubject() passe avec Succes");
        }

    }


    /**
     * méthode qui test le getEtat() et setEtat() de la classe mail
     */
    private static void testGetSetEtat() {
        String content = TestMail.TEST_CONTENT;
        int nbSuccessfulTest = 0;
        final String ERR_SENTENCE = "Le getEtat() renvoie : [t1] alors qu'on attendait [t2]...";
        Mail mail1 = new Mail(content, TEST_SUBJECT);

        // test non traitee par defaut
        if(! mail1.getState().equals(State.UNTREATED)) {
            logger.log(Level.WARNING, ERR_SENTENCE.replace("[t1]", String.valueOf(mail1.getState())).replace( "[t2]", String.valueOf(State.UNTREATED)));
        } else {
            nbSuccessfulTest ++;
        }

        // test spam
        mail1.setState(State.SPAM);
        if(! mail1.getState().equals(State.SPAM)) {
            logger.log(Level.WARNING, ERR_SENTENCE.replace("[t1]", String.valueOf(mail1.getState())).replace( "[t2]", String.valueOf(State.SPAM)));
        } else {
            nbSuccessfulTest ++;
        }

        // test non spam
        mail1.setState(State.NOT_SPAM);
        if(! mail1.getState().equals(State.NOT_SPAM)) {
            logger.log(Level.WARNING, ERR_SENTENCE.replace("[t1]", String.valueOf(mail1.getState())).replace( "[t2]", String.valueOf(State.NOT_SPAM)));

        } else {
            nbSuccessfulTest ++;
        }

        // test non traitee
        mail1.setState(State.UNTREATED);
        if(! mail1.getState().equals(State.UNTREATED)) {
            logger.log(Level.WARNING, ERR_SENTENCE.replace("[t1]", String.valueOf(mail1.getState())).replace( "[t2]", String.valueOf(State.UNTREATED)));
        } else {
            nbSuccessfulTest ++;
        }

        logger.log(Level.INFO, String.format("Tests getEtat/setEtat reussis %d/4", nbSuccessfulTest));

    }

    /**
     * méthode qui test le toString() de la classe mail
     */
    public static void testToString() {
        String content = TestMail.TEST_CONTENT;

        Mail mail1 = new Mail(content, TEST_SUBJECT);

        if(mail1.toString().equals(TEST_SUBJECT)) {
            logger.log(Level.INFO, "test toString() passe avec Succes");
        } else {
            logger.log(Level.WARNING, String.format("Le toString() renvoie : %s alors qu'on attendait : %s", mail1.toString(), TEST_SUBJECT));
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
