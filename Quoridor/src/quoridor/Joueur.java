package quoridor;

import java.util.ArrayList;

/**
  * Classe abstraite gérant les joueurs
  * @author AlexM02 , Drmarsupial35 , Eclixal , griffin568
  * @version 1.0.0
  */
public abstract class Joueur {

    protected String nom;
    protected int NUMERO;
    protected String COULEUR;
    protected ArrayList<Barriere> barrieres;
    protected Pion pion;
    protected Plateau plateau;
    protected boolean humain;

    /**
      * Créé un nouvel objet Humain
      * @param nom le nom du joueur
      * @param numero le numéro du joueur defini selon l'ordre de création (ex joueur 1 , joueur 2 ...)
      * @param couleur la couleur du joueur (indique la forme du pion en mode texte)
      * @param barrieres liste contenant les barrières restantes du joueur
      * @param pion le pion utilisé par le joueur
      * @param plateau le plateau de jeu
      */
    public Joueur(String nom, int numero, String couleur, ArrayList<Barriere> barrieres, Pion pion, Plateau plateau) {
      try {
        if (nom == null) {
          throw new Exception("Joueur constructeur - Le joueur doit avoir un nom.");
        }
        else if ((numero < 0) || (numero > 4)) {
          throw new Exception("Joueur constructeur - Le numéro d'un joueur est compris entre 0 et 4.");
        }
        else if (couleur == null) {
          throw new Exception("Joueur constructeur - Le joueur doit avoir une couleur.");
        }
        else if (barrieres == null) {
          throw new Exception("Joueur constructeur - Le joueur doit posséder une liste de barrières.");
        }
        else if (pion == null) {
          throw new Exception("Joueur constructeur - Le joueur doit posséder un pion qui existe.");
        }
        else if (plateau == null) {
          throw new Exception("Joueur constructeur - Le joueur doit se trouver sur un plateau existant");
        }
        else {
          this.nom = nom;
          this.NUMERO = numero;
          this.COULEUR = couleur;
          this.barrieres = barrieres;
          this.pion = pion;
          this.plateau = plateau;
        }
      }
      catch(Exception e) {
        System.err.println(e.getMessage());
      }
    }

    /**
      * Retourne le nom du joueur
      * @return le nom du joueur
      */
    public String getNom() {
        return nom;
    }

    /**
      * Retourne le numéro du joueur
      * @return le numero du joueur
      */
    public int getNumero() {
        return this.NUMERO;
    }

    /**
      * Retourne la couleur du joueur
      * @return la couleur du joueur
      */
    public String getCouleur() {
        return this.COULEUR;
    }

    /**
      * Retourne le pion utilisé par le joueur
      * @return le pion utilisé par le joueur
      */
    public Pion getPion() {
        return this.pion;
    }

    /**
      * Retourne la liste des barrières restantes du joueur
      * @return la liste des barrières restantes du joueur
      */
    public ArrayList<Barriere> getBarrieres() {
        return this.barrieres;
    }

    /**
      * Déplace le pion vers de nouvelles coordonnées
      * si celles-ci sont atteignables
      * @param coordonnee les coordonnées à atteindre
      */
    public void deplacerPion(Coordonnee coordonnee, boolean[][] damier) {
      try {
        if (damier == null || coordonnee == null) {
          throw new Exception ("Erreur deplacerPion(), parametre null");
        }
        else {
          damier[this.pion.getCoordonnee().getX1()][this.pion.getCoordonnee().getY1()] = true;
          damier[coordonnee.getX1()][coordonnee.getY1()] = false;
          this.pion.setCoordonnee(coordonnee);
        }
      }
      catch (Exception e) {
        System.err.println(e.getMessage());
      }

    }

    /**
      * Place une barrière aux coordonnées sélectionnées s'il en reste une au joueur
      * @param coordonnee les coordonnées où placer la barrière
      */
    public Barriere placerBarriere(Coordonnee coordonnee) {
      Barriere ret = null;
      if (!(this.barrieres.isEmpty())) {
        Barriere aPlacer = this.barrieres.get(0).setCoordonnee(coordonnee);

        if (aPlacer != null) {
          this.barrieres.remove(aPlacer);
          ret = aPlacer;
        }
      }
      return ret;
    }

    /**
      * Permet au joueur de jouer
      * @return la barriere placée par le joueur, ou une autre permettant de faire passer des messages
      */
    public abstract Barriere jeu();

  /**
    * Dit si le joueur est humain
    * @return vrai si le joueur est Humain, faux sinon
    */
    public boolean isHumain() {
      return this.humain;
    }
}
