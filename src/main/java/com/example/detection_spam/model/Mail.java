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

    private String contenu;

    private Etat etat;

    /**
     * Constructeur de la classe Mail
     * @param contenu le texte du mail
     */
    public Mail(String contenu) {

        if(contenu != null) {
            this.contenu = contenu;
            this.etat = Etat.NON_TRAITE;
        } else {
            throw new IllegalArgumentException("Le contenu d'un mail ne peut pas etre null");
        }
    }

    /**
     * Renvoie le texte associé au mail, donc son contenu
     * @return le contenu du mail
     */
    public String getText() {
        return contenu;
    }

    /**
     * Modifie l'etat du mail (SPAM, NON_SPAM, NON_TRAITE)
     * @param etat le nouvel etat du mail
     */
    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    /**
     * Renvoie l'etat du mail (SPAM, NON_SPAM, NON_TRAITE)
     * @return l'etat du mail
     */
    public Etat getEtat() {
        return etat;
    }




}
