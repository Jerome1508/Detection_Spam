/*
 * Application Detection Spam                                                                       14/03/2023
 * Groupe Romain PALAYRET, Alexis RAVAYROL, Marin BAILHE, Jérôme CHIROL
 * 3il Ingenieur Rodez
 */
package com.example.detection_spam.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe possédant une collection de mot associés à leurs probabilité d'être présent dans mail spam ou non
 * @author Alexis RAVAYROL, Romain PALAYRET
 * @version 1.0.0
 */
public class Dictionary implements Serializable {

    private int totalMailNumber, totalSpamNumber, totalNotSpamNumber;

    private Map<String, Double> DicoSpam;
    private Map<String, Double> DicoNotSpam;

    /**
     * On construit un dictionnaire en dé-sérializant sa sauvegarde
     * @param path le chemin de la sauvegarde du dictionnaire (un fichier au format xml)
     */
    public Dictionary(String path) {

    }

    /**
     * On construit un dictionnaire avec aucun mot au début
     */
    public Dictionary() {
        int totalMailNumber = 0;
        int totalSpamNumber = 0;
        int totalNotSpamNumber = 0;
        this.DicoSpam = new HashMap<String, Double>();
        this.DicoNotSpam = new HashMap<String, Double>();
    }

    /**
     * Renvoie la probabilité que le mot pris en argument soit dans un mail de type SPAM
     * @param word dont on veut la probabilité
     * @return la probabilité que le mot pris en argument soit dans un mail de type SPAM
     */
    private double getProbaSpam(String word) {
        return 0.0; // bouchon
    }

    /**
     * Renvoie la probabilité que le mot pris en argument soit dans un mail de type NON_SPAM
     * @param word dont on veut la probabilité
     * @return la probabilité que le mot pris en argument soit dans un mail de type NON_SPAM
     */
    private double getProbaNotSpam(String word) {
        return 0.0; // bouchon
    }


    /**
     * @return le nombre de mail total qui a permis de construire ce dictionnaire
     */
    public int getTotalMailNumber() {
        return totalMailNumber;
    }

    /**
     * @return le nombre de mail SPAM total qui a permis de construire ce dictionnaire
     */
    public int getTotalSpamNumber() {
        return totalSpamNumber;
    }

    /**
     * @return le nombre de mail NON_SPAM total qui a permis de construire ce dictionnaire
     */
    public int getTotalNotSpamNumber() {
        return totalNotSpamNumber;
    }

    /**
     * Ajoute un nouveau mot dans les dictionnaires, avec leur probabilité
     * préposition : le mot n'est pas présent dans le dico
     * @param word le mot qu'on souhaite ajouter
     * @param probaSpam la probabilité que le mot soit présent dans un spam
     * @param probaNotSpam la probabilité que le mot soit présent dans un non spam
     */
    private void addWord(String word, double probaSpam, double probaNotSpam) {

    }

    /**
     * Récupère la probabilité que le mail qui contient le mot soit un spam
     * @param word dont on veut la probabilité
     * @return la probabilité que le mail qui contient le mot soit un spam
     */
    public double getProbaSpamOrAdd(String word) {
        return 0.0; // bouchon
    }

    /**
     * Récupère la probabilité que le mail qui contient le mot soit un spam
     * @param word dont on veut la probabilité
     * @return la probabilité que le mail qui contient le mot soit un non spam
     */
    public double getProbaNotSpamOrAdd(String word) {
        return 0.0; // bouchon
    }

}
