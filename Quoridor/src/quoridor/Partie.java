package quoridor;

import java.util.ArrayList;
import java.io.FileNotFoundException;

/**
  * Cette classe gère les différents aspect de la partie
  * @author AlexM02 , Drmarsupial35 , Eclixal , griffin568
  * @version 0.1.0
  */
public class Partie {

    private int tour;
    private Mode mode;
    private Plateau plateau;
    private ArrayList<Joueur> joueur;

    /**
      * Créé un nouvel objet Partie
      * @param fileName le nom du fichier de configuration
      */
    public Partie(String fileName) {
      try {
        if (fileName == null) {
          throw new Exception("Partie constructeur - Le nom du fichier doit être valide pour pouvoir être utilisé.");
        }
        else {

        }
      }
      catch(FileNotFoundException e) {
        System.err.println("Partie construceur - Fichier non trouvé (" + fileName + ")");
      }
      catch(Exception e) {
        System.err.println(e.getMessage());
      }

    }

  /**
    * Retourne le numéro du tour actuel
    * @return le numéro du tour
    */
  public int getTour() {
        return tour;
    }

    /**
      * Retourne le mode de jeu utilisé
      * @return le mode de jeu utilisé
      */
    public Mode getMode() {
        return mode;
    }

    /**
      * Sauvegarde la partie
      */
    public void sauvegarder() {

    }

    /**
      * Charge les données de sauvegarde contenues dans le fichier sélectionné
      * @param filename le fichier contenant les données à charger
      */
    public void charger(String filename) {
      try {
        if (filename == null) {
          throw new Exception("Partie constructeur - Le nom du fichier doit être valide pour pouvoir être utilisé.");
        }
        else {

        }
      }
      catch(FileNotFoundException e) {
        System.err.println("Partie construceur - Fichier non trouvé (" + filename + ")");
      }
      catch(Exception e) {
        System.err.println(e.getMessage());
      }

    }

    /**
      * Initialise les différents éléments constants de la partie
      */
    private void initialisation() {

    }

    /**
      * Configure les éléments non constants de la partie à l'aide du fichier de configuration
      * @param filename le nom du fichier de configuration
      */
    private void configuration(String filename) {
      try {
        if (fileName == null) {
          throw new Exception("Partie constructeur - Le nom du fichier doit être valide pour pouvoir être utilisé.");
        }
        else {

        }
      }
      catch(FileNotFoundException e) {
        System.err.println("Partie construceur - Fichier non trouvé (" + fileName + ")");
      }
      catch(Exception e) {
        System.err.println(e.getMessage());
      }
    }

    /**
      * Lance la partie
      */
    public void start() {

    }

    /**
      * Termine la partie
      */
    public void fin() {

    }
}
