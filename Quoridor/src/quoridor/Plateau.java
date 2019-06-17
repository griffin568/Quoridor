package quoridor;
import java.util.ArrayList;

/**
  * Cette classe gère le plateau de jeu
  * @author AlexM02 , Drmarsupial35 , Eclixal , griffin568
  * @version 0.1.0
  */
public class Plateau {

    private int TAILLE;
    private boolean[][] DAMIER;

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
          this.DAMIER = new boolean[taille][taille];
          for (int i = 0 ; i < this.TAILLE ; i++) {
            for (int j = 0 ; j < this.TAILLE ; j++) {
              this.DAMIER[i][j] = true;
            }
          }
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
    * @param listePion la liste des pions à placer sur le terrain
    * @return une String avec ces informations
    */
    public String toString(ArrayList<Pion> listePion) {
      String ret = "";
      try  {
        ret += "\n\n\n";
        ret += "\t\t    1    2    3    4    5    6    7    8    9 \n";
        String[] letters = {"A","B","C","D","E","F","G","H","I"};
        char icon = 'F';
        for (int i = 0 ; i < this.TAILLE ; i++) {
          if (i % 2 == 1) {
            ret += "\n\t\t";
            for (int j = 0 ; j < this.TAILLE ; j++) {
              if (this.DAMIER[i][j]) {
                ret += "   ";
              }
              else {
                ret += "::";
              }
            }
          }
          else {
            ret += "\n\t\t    _    _    _    _    _    _    _    _    _  \n";
            ret += "\t\t" + letters[(int)(i/2)] + "  ";
            for (int j = 0 ; j < this.TAILLE ; j++) {
              if (j % 2 == 0) {
                if (this.DAMIER[i][j]) {
                  ret += "| |";
                }
                else {
                  for (Pion p : listePion) {
                    if (p.getCoordonnee().getX1() == i && p.getCoordonnee().getY1() == j) {
                      icon = p.getCouleur().charAt(0);
                    }
                  }
                  ret += "|"+icon+"|";
                }
              }
              else {
                if (this.DAMIER[i][j]) {
                  ret += "  ";
                }
                else {
                  ret += "::";
                }
              }
            }
            ret += "\n\t\t    -    -    -    -    -    -    -    -    -  \n";
          }
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

    /**
      * Modifie les différentes cases disponibles pour le déplacement
      * @param listCases la liste des cases dont la disponibilité doit changer
      */
      public void setDisponibilite (ArrayList<int[]> listCases) {
        for (int[] co : listCases) {
          this.DAMIER[co[0]][co[1]] = !this.DAMIER[co[0]][co[1]];
        }
      }

      public boolean[][] getDamier() {
        return this.DAMIER;
      }

    public static void main(String[] args) {
      Plateau p = new Plateau(18);
      ArrayList<int[]> a = new ArrayList<int[]>();
      int[] i = new int[2];
      i[0] = 0;
      i[1] = 1;
      a.add(i);
      int[] j = new int[2];
      j[0] = 1;
      j[1] = 1;
      a.add(j);
      int[] k = new int[2];
      k[0] = 2;
      k[1] = 1;
      a.add(k);
      int[] l = new int[2];
      l[0] = 3;
      l[1] = 2;
      a.add(l);
      int[] m = new int[2];
      m[0] = 3;
      m[1] = 3;
      a.add(m);
      int[] n = new int[2];
      n[0] = 3;
      n[1] = 4;
      a.add(n);
      p.setDisponibilite(a);
      System.out.println(p.toString(new ArrayList<Pion>()));
    }


}
