/*
 * Application Detection Spam                                                                       14/03/2023
 * Groupe Romain PALAYRET, Alexis RAVAYROL, Marin BAILHE, Jérôme CHIROL
 * 3il Ingenieur Rodez
 */
package com.example.detection_spam.model;

/**
 * Objet java qui represente un mail et qui permet de faire des opérations avec
 * @author Alexis RAVAYROL, Romain PALAYRET
 * @version 1.0.0
 */
public class Mail {

    private String content;

    private State state;

    /**
     * Constructeur de la classe Mail
     * @param content le texte du mail
     */
    public Mail(String content) {

        if(content != null) {
            this.content = content;
            this.state = State.UNTREATED;
        } else {
            throw new IllegalArgumentException("Le content d'un mail ne peut pas etre null");
        }
    }

    /**
     * Renvoie le texte associé au mail, donc son content
     * @return le content du mail
     */
    public String getText() {
        return content;
    }

    /**
     * Modifie l'etat du mail (SPAM, NON_SPAM, NON_TRAITE)
     * @param state le nouvel etat du mail
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Renvoie l'etat du mail (SPAM, NON_SPAM, NON_TRAITE)
     * @return l'etat du mail
     */
    public State getState() {
        return state;
    }




}
