/*
 * Application Detection Spam                                                                       14/03/2023
 * Groupe Romain PALAYRET, Alexis RAVAYROL, Marin BAILHE, Jérôme CHIROL
 * 3il Ingenieur Rodez
 */
package com.example.detection_spam.model;

/**
 * Les différents types de pourcentage d'acceptation pour detecter si un mail est un spam ou non
 * @author Romain PALAYRET
 * @version 1.0.0
 */
public enum AcceptanceType {
    Normal, // 60 %
    Faux_positif, // 75 %
    Faux_négatif // 50%
}
