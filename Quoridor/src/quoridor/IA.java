package quoridor;

import java.util.ArrayList;
import java.util.Collections;

/**
  * Cette classe gère les joueurs IA
  * @author AlexM02 , Drmarsupial35 , Eclixal , griffin568
  * @version 0.1.0
  */
public class IA extends Joueur {

    private Difficulte DIFFICULTE;
    private int[][] plusCourtChemin;

    /**
      * Créé un nouvel objet IA
      * @param nom le nom du joueur
      * @param numero le numéro du joueur defini selon l'ordre de création (ex joueur 1 , joueur 2 ...)
      * @param couleur la couleur du joueur (indique la forme du pion en mode texte)
      * @param barrieres liste contenant les barrières restantes du joueur
      * @param pion le pion utilisé par le joueur
      * @param plateau le plateau de jeu
      * @param difficulte le niveau de difficulté de cette IA
      */
    public IA(String nom, int numero, String couleur, ArrayList<Barriere> barrieres, Pion pion, Plateau plateau, Difficulte difficulte) {
      super(nom, numero, couleur, barrieres, pion, plateau);
      try {
        if (difficulte == null) {
          throw new Exception("Erreur IA(), parametre null");
        }
        else {
            this.DIFFICULTE = difficulte;
            this.humain = false;
        }
      }
      catch (Exception e) {
        System.err.println(e.getMessage());
      }

    }


    /**
      * Modifie le plus court chemin en déplacement de pion que l'IA prévoie
      * @param plusCourtChemin un tableau a deux dimensions contenant le plus court chemin que l'IA doit identifier
      */
    public void setPlusCourtChemin(int[][] plusCourtChemin) {
      try {
        if (plusCourtChemin == null) {
          throw new Exception("Erreur setPlusCourtChemin(), parametre null");
        }
        else {
          this.plusCourtChemin = plusCourtChemin;
        }
      }
      catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }

    /**
      * Identifie le plus court chemin pour chacun des joueurs et planifie les actions de l'IA en conséquences
      */
    public void plannification() {

    }

    /**
      * Permet à l'IA de jouer
      */
    public Barriere jeu() {
      Barriere ret = null;
      try {
        System.out.println("L'IA est en train de jouer");
        Thread.sleep(1000);
        if (this.DIFFICULTE == Difficulte.FACILE) {
          int[][] deplacementPossibles = this.pion.getDeplacementPossibles(this.plateau.getDamier());
          ArrayList<int[]> d = new ArrayList<int[]>();
          for (int[] tab : deplacementPossibles) {
            d.add(tab);
          }
          Collections.shuffle(d);
          deplacerPion(new Coordonnee(d.get(0)[0],d.get(0)[1],-1,-1),this.plateau.getDamier());
        }
        else if (this.DIFFICULTE == Difficulte.MOYEN) {
          int[][] deplacementPossibles = this.pion.getDeplacementPossibles(this.plateau.getDamier());

        }
      }
      catch (NullPointerException e) {
        System.err.println(e.getMessage());
      }
      finally {
        return ret;
      }
    }

  /**
    * Identifie un chemin de victoire pour un joueur grâce à l'algorithme de Dijkstra
    * @param joueur le joueur dont il faut identifier le chemin
    * @return le chemin trouvé pour ce joueur
    */
  private ArrayList<int[]> dijkstra (Joueur joueur) {

  }

  /**
  * Retourne la difficulté de l'IA
  * @return la difficulté de l'IA
  */
  public Difficulte getDifficulte() {
    return this.DIFFICULTE;
  }

  /**
  * Retourne le plus court chemin en déplacement de pion pour gagner que l'IA a prévu
  * @return un tableau a deux dimensions contenant le plus court chemin identifié par l'IA
  */
  public int[][] getPlusCourtChemin() {
    return plusCourtChemin;
  }

  /**
    * Change la difficulté de l'IA
    * @param diff la nouvelle difficulté que l'on souhaite mettre à l'IA.
    */
  public void setDifficulte(Difficulte diff) {
    try {
      if (diff == null) {
        throw new Exception("IA setDifficulte() - la difficulté n'est pas valide.");
      }
      else {
        this.DIFFICULTE = diff;
      }
    }
    catch(Exception e) {
      System.err.println();
    }
  }
}
