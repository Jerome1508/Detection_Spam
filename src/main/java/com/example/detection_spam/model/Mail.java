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

    private String subject;

    private State state;

    /**
     * Constructeur de la classe Mail
     * @param content le texte du mail
     */
    public Mail(String content, String subject) {

        if(content != null) {
            this.content = content;
            this.subject = subject;
            this.state = State.UNTREATED;
        } else {
            throw new IllegalArgumentException("Le contenu d'un mail ne peut pas être null");
        }
    }

    /**
     * Renvoie le texte associé au mail, donc son contenu
     * @return le contenu du mail
     */
    public String getText() {
        return content;
    }

    /**
     * Modifie l'état du mail (SPAM, NON_SPAM, NON_TRAITE)
     * @param state le nouvel état du mail
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Renvoie l'état du mail (SPAM, NON_SPAM, NON_TRAITE)
     * @return l'état du mail
     */
    public State getState() {
        return state;
    }

    /**
     * Renvoie le sujet du mail
     * @return le sujet du mail
     */
    public String getSubject() {
        return subject;
    }

    @Override
    public String toString() {
        return subject;
    }
}
