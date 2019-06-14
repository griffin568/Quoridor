package quoridor;
import java.util.ArrayList;

/**
  * Cette classe gère le plateau de jeu
  * @author AlexM02 , Drmarsupial35 , Eclixal , griffin568
  * @version 0.1.0
  */
public class Plateau {

    private int TAILLE;
    private int[][] DAMIER;

    /**
      * Créé un nouvel objet Plateau
      * @param taille la taille du plateau (longueur et largeur car le plateau est forcément un carré)
      */
    public Plateau(int taille) {
      try {
        if (taille < 2) {
          throw new Exception("Erreur Plateau(), taille trop petite");
        }
        else {
          this.TAILLE = taille;
          this.DAMIER = new int[taille][taille];
        }
      }
      catch (Exception e) {
        System.err.println(e.getMessage());
      }

    }

    /**
      * Retourne la taille du plateau
      * @return la taille du plateau
      */
    public int getTaille() {
        return TAILLE;
    }

  /**
    * Le plateau actuel en ASCII art
    * @return une String avec ces informations
    */
    public String toString(ArrayList<Pion> listePion) {
      String ret = "";
      try  {
        ret += "\n\n\n";
        ret += "\t\t   1    2    3    4    5    6    7    8    9 \n";
        String[] letters = {"A","B","C","D","E","F","G","H","I"};
        String icon =  "";
        for (int i = 0 ; i < this.TAILLE ; i++) {
          ret += "\n\t\t    _    _    _    _    _    _    _    _    _  \n";
          ret += "\t\t" + letters[i] + "  ";
          for (int j = 0 ; j < this.TAILLE ; j++) {
            for (Pion p : listePion) {
              if (p.getCoordonnee().getX1() == i && p.getCoordonnee().getY1() == j) {
                if (p.getCouleur().length() == 1) {
                  icon = p.getCouleur();
                }
              }
            }
            ret += "|"+icon+"|  ";
          }
          ret += "\n\t\t    -    -    -    -    -    -    -    -    -  \n";
          icon = "";
        }
      }
      catch (NullPointerException e) {
        ret = "";
        System.err.println("Erreur, liste de pions null");
      }
      finally {
        return ret;
      }
    }

}
