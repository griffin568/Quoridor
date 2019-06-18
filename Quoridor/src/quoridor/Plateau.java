package quoridor;
import java.util.ArrayList;

/**
  * Cette classe gère le plateau de jeu
  * @author AlexM02 , Drmarsupial35 , Eclixal , griffin568
  * @version 0.1.0
  */
public class Plateau {

  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_BLACK = "\u001B[30m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_YELLOW = "\u001B[33m";
  public static final String ANSI_BLUE = "\u001B[34m";
  public static final String ANSI_PURPLE = "\u001B[35m";
  public static final String ANSI_CYAN = "\u001B[36m";
  public static final String ANSI_WHITE = "\u001B[37m";

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
    * @param actif le pion du joueur en train de jouer
    * @return une String avec ces informations
    */
    public String toString(ArrayList<Pion> listePion , Pion actif) {
      String ret = "";
      try {
        boolean yellow = false;
        String[] letters = {"A","B","C","D","E","F","G","H","I"};
        int[][] deplacementsPossibles = actif.getDeplacementPossibles(this.DAMIER);
        if (System.getProperty("os.name").equalsIgnoreCase("linux")) {
          ret += ANSI_CYAN + "\t\t       1   2   3   4   5   6   7   8   9";
          ret += ANSI_CYAN + "\n\t\t   ________________________________________";
          for (int i = 0 ; i < this.TAILLE ; i++) {
            if (i > 0) {
              ret += ANSI_CYAN + "|\n\t\t";
            }
            else {
              ret += "\n\t\t";
            }

            if (i % 2 == 0) {
              ret += ANSI_CYAN + letters[(int)(i/2)] + "  |   ";
            }
            else {
              if (i == this.TAILLE - 1) {
                ret  += ANSI_CYAN +  "   ________________________________________";
              }
              else {
                ret += ANSI_CYAN +  "   |   ";
              }
            }
            for (int j = 0 ; j < this.TAILLE ; j++) {
              yellow = false;
              if (i % 2 == 0 && j % 2 == 0) {
                for (int[] tab : deplacementsPossibles) {
                  if (tab[0] == i && tab[1] == j) {
                    ret += ANSI_PURPLE + "X";
                    yellow = true;
                  }
                }
                if (this.DAMIER[i][j] && !yellow) {
                  ret += ANSI_GREEN + "X";
                }
                else {
                  for (Pion p : listePion) {
                    if (p.getCoordonnee().getX1() == i && p.getCoordonnee().getY1() == j) {
                      ret += ANSI_WHITE + p.getCouleur();
                    }
                  }
                }
              }
              else if (j % 2 == 0) {
                if (this.DAMIER[i][j]) {
                  ret += " ";
                }
                else {
                  ret += ANSI_RED + "/";
                }

              }
              else {
                if (this.DAMIER[i][j]) {
                  ret += "   ";
                }
                else {
                  ret += ANSI_RED +  " / ";
                }
              }
            }
          }
        }
        else {
          ret += "\t\t       1   2   3   4   5   6   7   8   9";
          ret += "\n\t\t   ________________________________________";
          for (int i = 0 ; i < this.TAILLE ; i++) {
            if (i > 0) {
              ret +="|\n\t\t";
            }
            else {
              ret += "\n\t\t";
            }

            if (i % 2 == 0) {
              ret += letters[(int)(i/2)] + "  |   ";
            }
            else {
              if (i == this.TAILLE - 1) {
                ret  += "   ________________________________________";
              }
              else {
                ret += "   |   ";
              }
            }
            for (int j = 0 ; j < this.TAILLE ; j++) {
              yellow = false;
              if (i % 2 == 0 && j % 2 == 0) {
                for (int[] tab : deplacementsPossibles) {
                  if (tab[0] == i && tab[1] == j) {
                    ret +=  "$";
                    yellow = true;
                  }
                }
                if (this.DAMIER[i][j] && !yellow) {
                  ret += "X";
                }
                else {
                  for (Pion p : listePion) {
                    if (p.getCoordonnee().getX1() == i && p.getCoordonnee().getY1() == j) {
                      ret += p.getCouleur();
                    }
                  }
                }
              }
              else if (j % 2 == 0) {
                if (this.DAMIER[i][j]) {
                  ret += " ";
                }
                else {
                  ret += "/";
                }

              }
              else {
                if (this.DAMIER[i][j]) {
                  ret += "   ";
                }
                else {
                  ret += " / ";
                }
              }
            }
          }

        }
      }
      catch (Exception e) {
        System.err.println(e.getMessage());
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



}
