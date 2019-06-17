package quoridor;
import java.util.ArrayList;

/**
  * Cette classe gère les barrières utilisées par les joueurs
  * @author AlexM02 , Drmarsupial35 , Eclixal , griffin568
  * @version 0.1.0
  */
public class Barriere {

    private String COULEUR;
    private Coordonnee coordonnee;
    private Plateau plateau;

    /**
      * Créé un nouvel objet Barriere
      * @param couleur la couleur du joueur
      * @param coordonnee les coordonnées de la barrière sur le plateau (null si non posée)
      */
    public Barriere(String couleur, Plateau plateau) {
      try {
        if (couleur == null) {
          throw new Exception("Barriere constructeur - La couleur de la barrière doit exister.");
        }
        else if (plateau == null) {
          throw new Exception("Barriere constructeur - Le plateau doit exister.");
        }
        else {
          this.COULEUR = couleur;
          this.coordonnee = null;
          this.plateau = plateau;
        }
      }
      catch(Exception e) {
        System.err.println(e.getMessage());
      }
    }

    /**
      * Retourne la couleur de la barrière
      * @return la couleur de la barrière
      */
    public String getCouleur() {
        return COULEUR;
    }


    /**
      * Retourne les coordonnées de la barrière
      * @return les coordonnées de la barrière sous la forme d'un objet Coordonnee
      */
    public Coordonnee getCoordonnee() {
        return coordonnee;
    }

    /**
      * Modifie les coordonnées de la barrière
      * @param coordonnee les nouvelles coordonnées de la barrière
      */
    public Barriere setCoordonnee(Coordonnee coordonnee) {
      Barriere ret = null;
      try {
        if (coordonnee == null) {
          throw new Exception("Barriere setCoordonnee() - Les coordonnees a changer doivent exister.");
        }
        else {
          int x3,y3;
          boolean[][] damier = this.plateau.getDamier();
          int x1 = coordonnee.getX1();
          int x2 = coordonnee.getX2();
          int y1 = coordonnee.getY1();
          int y2 = coordonnee.getY2();

          if (coordonnee.getX1() < coordonnee.getX2()) {
            x3 = coordonnee.getX2() - 1;
          }
          else if(coordonnee.getX1() > coordonnee.getX2()) {
            x3 = coordonnee.getX2() + 1;
          }
          else {
            x3 = coordonnee.getX2();
          }

          if (coordonnee.getY1() < coordonnee.getY2()) {
            y3 = coordonnee.getY2() - 1;
          }
          else if (coordonnee.getY1() > coordonnee.getY2()) {
            y3 = coordonnee.getY2() + 1;
          }
          else {
            y3 = coordonnee.getY2();
          }


          if ((damier[x1][y1]) && (damier[x2][y2]) && (damier[x3][y3])) {
            this.coordonnee = coordonnee;
            ArrayList<int[]> aChanger = new ArrayList<int[]>();
            int[] tab1 = {x1,y1};
            int[] tab2 = {x2,y2};
            int[] tab3 = {x3,y3};
            aChanger.add(tab1);
            aChanger.add(tab2);
            aChanger.add(tab3);
            this.plateau.setDisponibilite(aChanger);
            ret = this;
          }
        }
      }
      catch(Exception e) {
        System.err.println();
      }
      finally {
        return ret;
      }
    }
}
