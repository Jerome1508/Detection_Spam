/*
 * Application Detection Spam                                                                       14/03/2023
 * Groupe Romain PALAYRET, Alexis RAVAYROL, Marin BAILHE, Jérôme CHIROL
 * 3il Ingenieur Rodez
 */
package com.example.detection_spam.model;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe possédant une collection de mot associés à leurs probabilité d'être présent dans mail spam ou non
 * @author Alexis RAVAYROL, Romain PALAYRET
 * @version 1.0.0
 */
public class Dictionary implements Serializable {

    private Map<String, Integer> DicoSpam;
    private Map<String, Integer> DicoNotSpam;

    private int nbSpamToLearn;

    public int getNbSpamToLearn() {
        return nbSpamToLearn;
    }

    public int getNbNotSpamToLearn() {
        return nbNotSpamToLearn;
    }

    private int nbNotSpamToLearn;

    public Map<String, Integer> getDicoSpam() {
        return DicoSpam;
    }

    public Map<String, Integer> getDicoNotSpam() {
        return DicoNotSpam;
    }


    /**
     * On construit un dictionnaire en dé-sérializant sa sauvegarde
     * @param path le chemin de la sauvegarde du dictionnaire (un fichier au format xml)
     */
    public Dictionary(String path) {
        ObjectInputStream ois = null;

        try {
            final FileInputStream fichier = new FileInputStream(path);
            ois = new ObjectInputStream(fichier);
            Dictionary temp = (Dictionary) ois.readObject();
            this.DicoNotSpam = temp.getDicoNotSpam();
            this.DicoSpam = temp.getDicoSpam();
            this.nbSpamToLearn = temp.getNbSpamToLearn();
            this.nbNotSpamToLearn = temp.getNbNotSpamToLearn();
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * On construit un dictionnaire avec aucun mot au début
     */
    public Dictionary() {
        this.DicoSpam = new HashMap<String, Integer>();
        this.DicoNotSpam = new HashMap<String, Integer>();
        this.nbNotSpamToLearn = 0;
        this.nbSpamToLearn = 0;
    }

    /**
     * ajoute 1 à la variable qui compte le compte de spams utilisé pour remplir le dico
     */
    public void addSpamToLearn() {
        this.nbSpamToLearn += 1;
    }

    /**
     * ajoute 1 à la variable qui compte le compte de mail non spam utilisé pour remplir le dico
     */
    public void addNotSpamToLearn() {
        this.nbNotSpamToLearn += 1;
    }

    /**
     * Renvoie la probabilité que le mot pris en argument soit dans un mail de type SPAM
     * @param word dont on veut la probabilité
     * @return la probabilité que le mot pris en argument soit dans un mail de type SPAM
     */
    public double getProbaSpam(String word) {
        double ret;
        try {
            double probaSpam = ((double) DicoSpam.get(word)) / ((double) this.nbSpamToLearn);
            double probaNotSpam = ((double) DicoNotSpam.get(word)) / ((double) this.nbNotSpamToLearn);
            ret = probaSpam / (probaSpam + probaNotSpam);
        } catch (Exception e) {
            ret = 0.00;
        }
        return ret;
    }

    /**
     * Renvoie la probabilité que le mot pris en argument soit dans un mail de type NON_SPAM
     * @param word dont on veut la probabilité
     * @return la probabilité que le mot pris en argument soit dans un mail de type NON_SPAM
     */
    public double getProbaNotSpam(String word) {
        double ret;
        try {
            double probaSpam = ((double) DicoSpam.get(word)) / ((double) this.nbSpamToLearn);
            double probaNotSpam = ((double) DicoNotSpam.get(word)) / ((double) this.nbNotSpamToLearn);
            ret = probaNotSpam / (probaSpam + probaNotSpam);
        } catch (Exception e) {
            ret = 0;
        }
        return ret;
    }

    /**
     * Renvoie true si le mot est contenu dans les dictionnaires
     * @param word dont on veut la probabilité
     * @return true si le mot est contenu dans les dictionnaires, false sinon
     */
    public boolean containsWord(String word) {
        return DicoSpam.containsKey(word);
    }

    /**
     * Ajoute un nouveau mot dans les dictionnaires
     * préposition : le mot n'est pas présent dans le dico
     * @param word le mot qu'on souhaite ajouter
     * @param spam true si le mot provient d'un spam, false sinon
     */
    private void addWord(String word, boolean spam) {
        DicoSpam.put(word, spam ? 1 : 0);
        DicoNotSpam.put(word, spam ? 0 : 1);
    }

    /**
     * Met à jour la probabilité que le mail qui contient le mot soit un spam, ou l'ajoute
     * @param word dont on veut la probabilité
     */
    public void majProbaOrAdd(String word, boolean spam) {

        if(DicoSpam.containsKey(word)) {
            DicoSpam.put(word, DicoSpam.get(word) + (spam ? 1 : 0));
            DicoNotSpam.put(word, DicoNotSpam.get(word) + (spam ? 0 : 1));
        } else {
            addWord(word, spam);
        }
    }

    /**
     * Enregistre le dictionnaire dans un fichier
     */
    public void saveDico(String path) {
        ObjectOutputStream oos = null;

        try {
            final FileOutputStream fichier = new FileOutputStream(path);
            oos = new ObjectOutputStream(fichier);
            oos.writeObject(this);
            oos.flush();
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.flush();
                    oos.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
