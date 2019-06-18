package quoridor;
import java.util.ArrayList;

/**
  * Cette classe gère les pions utilisés par les joueurs
  * @author AlexM02 , Drmarsupial35 , Eclixal , griffin568
  * @version 0.1.0
  */
public class Pion {

    private String COULEUR;
    private int[][] deplacementPossibles;
    private Coordonnee coordonnee;


    /**
      * Créé un nouvel objet Pion
      * @param couleur la couleur du joueur (désigne une forme en mode texte)
      * @param coordonnee les coordonnées de départ du pion
      */
    public Pion(String couleur, Coordonnee coordonnee) {
      try {
        if (couleur == null || coordonnee == null) {
          throw new Exception("Erreur Pion(), parametre null");
        }
        else {
          this.COULEUR = couleur;
          this.coordonnee = coordonnee;
        }
      }
      catch (Exception e) {
        System.err.println(e.getMessage());
      }

    }

    /**
      * Retourne la couleur du pion
      * @return la couleur du pion
      */
    public String getCouleur() {
        return this.COULEUR;
    }

    /**
      * Retourne les coordonnées du pion
      * @return les coordonnées du pion sous la forme d'un objet Coordonnee
      */
    public Coordonnee getCoordonnee() {
        return this.coordonnee;
    }

    /**
      * Modifie les coordonnées du pion
      * @param coordonnees les nouvelles coordonnées du pion
      */
    public void setCoordonnee(Coordonnee coordonnees) {
      try {
        if (coordonnees == null) {
          throw new Exception("Pion setCoordonnee() - Les coordonnees a changer doivent exister.");
        }
        else {
          this.coordonnee = coordonnees;
        }
      }
      catch (Exception e) {
        System.err.println(e.getMessage());
      }
    }

    /**
      * Retourne les différents déplacements possibles du pion
      * @return les différents déplacements possibles du pion sous la forme d'un tableau à deux dimensions
      */
    public int[][] getDeplacementPossibles(boolean[][] damier) {
        nextCoup(damier);
        return this.deplacementPossibles;
    }

    /**
      * Identifie les nouveaux déplacements possibles du pion avant ou après un déplacement
      */
    public void nextCoup(boolean[][] damier) {
      int x = this.coordonnee.getX1();
      int y = this.coordonnee.getY1();
      ArrayList<int[]> temp = new ArrayList<int[]>();
      int[] deplacement;
      for (int i = -2 ; i <= 2 ; i++) {
        for (int j = -2 ; j <= 2 ; j++) {
          if (i % 2 == 0 && j % 2 == 0) {
            if (x+i >= 0 && x+i < damier.length && y+j >= 0 && y+j < damier.length) {
              if (damier[x+i][y+j] && damier[x+((int)i/2)][y+((int)j/2)]) {
                deplacement = new int[2];
                deplacement[0] = x+i;
                deplacement[1] = y+j;
                temp.add(deplacement);
              }
              else if (!damier[x+i][y+j] && damier[x+((int)i/2)][y+((int)j/2)]) {
                if (x+2*i >= 0 && x+2*i < damier.length && y+2*j >= 0 && y+2*j < damier.length) {
                  if (damier[(int)x+3*i/2][(int)y+3*j/2] && damier[x+2*i][y+2*j]) {
                    deplacement = new int[2];
                    deplacement[0] = x+2*i;
                    deplacement[1] = y+2*j;
                    temp.add(deplacement);
                  }
                }
              }
            }
          }
        }
      }
      this.deplacementPossibles = new int[temp.size()][temp.size()];
      for (int i = 0 ; i < temp.size() ; i++) {
        this.deplacementPossibles[i] = temp.get(i);
      }
    }
}
