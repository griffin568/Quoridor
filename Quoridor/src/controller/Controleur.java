package controller;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import view.*;
import quoridor.*;
import java.util.ArrayList;

public class Controleur {

  private int x1;
  private int y1;
  private int x2;
  private int y2;
  private Partie partie;
  private Joueur actif;

  /**
    * Créé un nouvel objet Controleur
    * @param partie la Partie à controler
    * @param actif le joueur actif
    */
    public Controleur (Partie partie , Joueur actif) {
      try {
        if (partie == null || actif == null) {
          throw new Exception ("Erreur du constructeur Controleur(), parametre null");
        }
        else {
          this.x1 = -1;
          this.y1 = -1;
          this.x2 = -1;
          this.y2 = -1;
          this.partie = partie;
          this.actif = actif;
        }
      }
      catch (Exception e) {
        System.err.println(e.getMessage());
      }
    }

  /**
    * Retourne la valeur de l'attribut x1
    * @return x1
    */
    public int getX1() {
      return this.x1;
    }

  /**
    * Retourne la valeur de l'attribut y1
    * @return y1
    */
    public int getY1() {
      return this.y1;
    }

  /**
    * Retourne la valeur de l'attribut x2
    * @return x2
    */
    public int getX2() {
      return this.x2;
    }

  /**
    * Retourne la valeur de l'attribut y2
    * @return y2
    */
    public int getY2() {
      return this.y2;
    }

  /**
    * Modifie la valeur de l'attribut x1
    * @param x1 la nouvel valeur
    */
    public void setX1 (int x1) {
      if (x1 >= 0 && x1 <= 16) {
        this.x1 = x1;
      }
      else {
        System.err.println("Controleur - setX1() : parametre invalide");
      }
    }

  /**
    * Modifie la valeur de l'attribut y1
    * @param y1 la nouvel valeur
    */
    public void setY1 (int y1) {
      if (y1 >= 0 && y1 <= 16) {
        this.y1 = y1;
      }
      else {
        System.err.println("Controleur - setY1() : parametre invalide");
      }
    }

  /**
    * Modifie la valeur de l'attribut x2
    * @param x2 la nouvel valeur
    */
    public void setX2 (int x2) {
      if (x2 >= 0 && x2 <= 16) {
        this.x2 = x2;
      }
      else {
        System.err.println("Controleur - setX2() : parametre invalide");
      }
    }

  /**
    * Modifie la valeur de l'attribut y2
    * @param y2 la nouvel valeur
    */
    public void setY2 (int y2) {
      if (y2 >= 0 && y2 <= 16) {
        this.y2 = y2;
      }
      else {
        System.err.println("Controleur - setY2() : parametre invalide");
      }
    }

  /**
    * Change le joueur actif
    */
    public void changeActif () {
      if (this.partie.getJoueurs().indexOf(this.actif) + 1 < this.partie.getJoueurs().size()) {
        this.actif = this.partie.getJoueurs().get(this.partie.getJoueurs().indexOf(this.actif) + 1);
      }
      else {
        this.actif = this.partie.getJoueurs().get(this.partie.getJoueurs().size() - (this.partie.getJoueurs().indexOf(this.actif) + 1));
      }
    }

  /**
    * Résout les actions sur le plateau
    * @return true si une action a bien été effectuée
    */
    public boolean controle() {
      boolean ret = false;
      ArrayList<Pion> listePion = new ArrayList<Pion>();
      for (Joueur j : this.partie.getJoueurs()) {
        listePion.add(j.getPion());
      }
      try {
        Plateau oldPlateau = this.partie.savePlateau();
        ArrayList<Joueur> oldJoueurs = this.partie.saveJoueurs(oldPlateau);
        ArrayList<Barriere> oldBarrieres = this.partie.saveBarrieres();
        boolean check = false;
        if (this.x1 % 2 == 0 && this.y1 % 2 == 0) {
          if (this.actif.getPion().getCoordonnee().getX1() == this.x1 && this.actif.getPion().getCoordonnee().getY1() == this.y1) {
            check = true;
          }
          if (check) {
            for (int[] next : this.actif.getPion().getDeplacementPossibles(this.partie.getPlateau().getDamier())) {
              if (this.x2 == next[0] && this.y2 == next[1]) {
                this.actif.deplacerPion(new Coordonnee(this.x2,this.y2,-1,-1),this.partie.getPlateau().getDamier());
                ret = true;
              }
            }
          }
        }
        else {
          if (this.x1 == this.x2) {
            if (this.y1 % 2 == 0 && this.y2 % 2 == 0 && Math.abs(this.y1 - this.y2) == 2) {
              if (this.partie.getPlateau().getDamier()[x1][y1] && this.partie.getPlateau().getDamier()[x1][y2] && this.partie.getPlateau().getDamier()[x1][(int)((y1+y2) / 2)]) {
                Barriere temp = this.actif.placerBarriere(new Coordonnee(this.x1,this.y1,this.x2,this.y2));
                this.partie.addBarriere(temp);
                if (this.partie.start()) {
                  ret = true;
                }
                else {
                  this.partie.setPlateau(oldPlateau);
                  this.partie.setJoueurs(oldJoueurs);
                  this.partie.setBarrieres(oldBarrieres);
                  listePion = new ArrayList<Pion>();
                  for (Joueur j : this.partie.getJoueurs()) {
                    listePion.add(j.getPion());
                  }
                }
              }
            }
          }
          else if (this.y1 == this.y2) {
            if (this.x1 % 2 == 0 && this.x2 % 2 == 0 && Math.abs(this.x1 - this.x2) == 2) {
              if (this.partie.getPlateau().getDamier()[x1][y1] && this.partie.getPlateau().getDamier()[x2][y2] && this.partie.getPlateau().getDamier()[(int)((x1+x2) / 2)][y1]) {
                Barriere temp = this.actif.placerBarriere(new Coordonnee(this.x1,this.y1,this.x2,this.y2));
                this.partie.addBarriere(temp);
                if (this.partie.start()) {
                  ret = true;
                }
                else {
                  this.partie.setPlateau(oldPlateau);
                  this.partie.setJoueurs(oldJoueurs);
                  this.partie.setBarrieres(oldBarrieres);
                  listePion = new ArrayList<Pion>();
                  for (Joueur j : this.partie.getJoueurs()) {
                    listePion.add(j.getPion());
                  }
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
        this.x1 = -1;
        this.y1 = -1;
        this.x2 = -1;
        this.y2 = -1;
        return ret;
      }
    }

    public Joueur getJoueurActif() {
      return this.actif;
    }

}
