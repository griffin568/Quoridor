package quoridor;
import java.util.ArrayList;

/**
  * Cette classe gère les pions utilisés par les joueurs
  * @author AlexM02 , Drmarsupial35 , Eclixal , griffin568
  * @version 1.0.0
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
      * @param damier le damier sur lequel se déplacer
      * @return les différents déplacements possibles du pion sous la forme d'un tableau à deux dimensions
      */
    public int[][] getDeplacementPossibles(boolean[][] damier) {
        nextCoup(damier);
        return this.deplacementPossibles;
    }

    /**
      * Identifie les nouveaux déplacements possibles du pion avant ou après un déplacement
      * @param damier le damier sur lequel se déplacer
      */
    public void nextCoup(boolean[][] damier) {
      int x = this.coordonnee.getX1();
      int y = this.coordonnee.getY1();
      ArrayList<int[]> temp = new ArrayList<int[]>();
      int[] deplacement;
      boolean nextTo = false;
      int[] next = new int[2];
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
                    if (x+2*i >= 0 && x+2*i < damier.length && y+2*j >= 0 && y+2*j < damier.length) {
                      deplacement = new int[2];
                      deplacement[0] = x+2*i;
                      deplacement[1] = y+2*j;
                      temp.add(deplacement);
                  }
                }
                else {
                  nextTo = true;
                  next[0] = x+i;
                  next[1] = y+j;
                }
              }
            }
          }
        }
      }
    }
    ArrayList<int[]> temp2 = new ArrayList<int[]>();
    for (int k = 0 ; k < temp.size() ; k++) {
      if (!nextTo && (temp.get(k)[0] == x || temp.get(k)[1] == y)) {
        temp2.add(temp.get(k));
      }
      else if (nextTo && !(Math.abs(next[0] - temp.get(k)[0]) == 4 || Math.abs(next[1] - temp.get(k)[1]) == 4)) {
        temp2.add(temp.get(k));
      }
      else if (nextTo) {
        if (next[0] == x) {
          if (temp.get(k)[0] == x && Math.abs(temp.get(k)[1] - next[1]) == 4) {
            temp2.add(temp.get(k));
          }
        }
        else if (next[1] == y) {
          if (temp.get(k)[1] == y && Math.abs(temp.get(k)[0] - next[0]) == 4) {
            temp2.add(temp.get(k));
          }
        }
      }
    }
    this.deplacementPossibles = new int[temp2.size()][temp2.size()];
    for (int k = 0 ; k < temp2.size() ; k++) {
      this.deplacementPossibles[k] = temp2.get(k);
    }
  }
}
