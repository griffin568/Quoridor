package quoridor;

/**
  * Cette classe gère le plateau de jeu
  * @author AlexM02 , Drmarsupial35 , Eclixal , griffin568
  * @version 0.1.0
  */
public class Plateau {

    private final int TAILLE;
    private final int[][] DAMIER;

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
    public String toString() {
      String ret = "\n\n\n";
      ret += "\t\t   1    2    3    4    5    6    7    8    9 ";
      ret += "\t\t                                             ";
      String[] letters = {"A","B","C","D","E","F","G","H","I"};
      for (int i = 0 ; i < this.TAILLE ; i++) {
        for (int j = 0 ; j < this.TAILLE ; j++) {
          ret += "\t\t   _    _    _    _    _    _    _    _    _";
          ret += "\t\t"+letters[i]+" | |  | |  | |  | |  | |  | |  | |  | |  | |";
          ret += "\t\t   -    -    -    -    -    -    -    -    -";
          ret += "\t\t                                             ";
        }
      }
    }
}
