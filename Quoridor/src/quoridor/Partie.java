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
    private ArrayList<Joueur> joueurs;
    private ArrayList<Barriere> barrieres;

    /**
      * Créé un nouvel objet Partie
      * @param fileName le nom du fichier de configuration
      */
    public Partie(Mode mode) {
      try {
        if (mode == null) {
          throw new Exception("Partie constructeur - Le mode de jeu doit être valide pour pouvoir être utilisé.");
        }
        else {
          initialisation(mode);
        }
      }
      catch(Exception e) {
        System.err.println(e.getMessage());
      }

    }


    /**
      * Sauvegarde la partie
      */
    public void sauvegarder(String fileName, Joueur leJoueur) {
      try {
        if (fileName == null) {
          throw new Exception("Partie sauvegarder() - Le nom du fichier doit exister");
        }
        else if (leJoueur == null) {
          throw new Exception("Partie sauvegarder() - Le dernier joueur ayant joué doit exister");
        }
        else {
          RWFile.writeFile(fileName, this.joueurs, this.barrieres, this.tour, leJoueur);
          System.out.println("La partie a bien été sauvegardé !");
        }
      }
      catch (Exception e) {
        System.err.println(e.getMessage());
      }

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
    private void initialisation(Mode mode) {
          this.barrieres = new ArrayList<Barriere>();
          this.tour = 0;
          this.mode = mode;
          configuration(mode);
    }

    /**
      * Configure les éléments non constants de la partie à l'aide du fichier de configuration
      * @param filename le nom du fichier de configuration
      */
    private void configuration(Mode mode) {

      Barriere b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16,b17,b18,b19,b20;

        if (mode.equals(Mode.HH) || mode.equals(Mode.HI)) {
          ArrayList<String> lignes = RWFile.readFile("config2");
          this.plateau = new Plateau(Integer.parseInt(lignes.get(0)));



        }
        else {
          ArrayList<String> lignes = RWFile.readFile("config4");
          this.plateau = new Plateau(Integer.parseInt(lignes.get(0)));
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

    /**
      * Ajoute une barrière à la liste des barrières sur le plateau
      * @param barriere la barrière à ajouter à la liste
      */
      public void addBarriere (Barriere barriere) {
        try {
          if (barriere == null) {
            throw new Exception ("Erreur addBarriere(), parametre null");
          }
          else {
            this.barrieres.add(barriere);
          }
        }
        catch (Exception e) {
          System.err.println(e.getMessage());
        }
      }


  /**
  * Retourne le numéro du tour actuel
  * @return le numéro du tour
  */
  public int getTour() {
    return this.tour;
  }

  /**
  * Retourne le mode de jeu utilisé
  * @return le mode de jeu utilisé
  */
  public Mode getMode() {
    return this.mode;
  }

  public Plateau getPlateau() {
    return this.plateau;
  }
}
