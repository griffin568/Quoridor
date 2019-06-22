package quoridor;

import java.util.ArrayList;
import java.util.Collections;
import quoridor.dijkstra.*;

/**
  * Cette classe gère les joueurs IA
  * @author AlexM02 , Drmarsupial35 , Eclixal , griffin568
  * @version 0.1.0
  */
public class IA extends Joueur {

    private Difficulte DIFFICULTE;
    private ArrayList<Noeud> plusCourtChemin;
    private ArrayList<Joueur> joueurs;
    private boolean forceMove = false;

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
    public IA(String nom, int numero, String couleur, ArrayList<Barriere> barrieres, Pion pion, Plateau plateau, Difficulte difficulte , ArrayList<Joueur> joueurs) {
      super(nom, numero, couleur, barrieres, pion, plateau);
      try {
        if (difficulte == null || joueurs == null) {
          throw new Exception("Erreur IA(), parametre null");
        }
        else {
            this.DIFFICULTE = difficulte;
            this.joueurs = joueurs;
            this.humain = false;
        }
      }
      catch (Exception e) {
        System.err.println(e.getMessage());
      }

    }


    /**
      * Modifie le plus court chemin en déplacement de pion que l'IA prévoie
      * @param plusCourtChemin une liste des Noeuds à parcourir
      */
    public void setPlusCourtChemin(ArrayList<Noeud> plusCourtChemin) {
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
      * Identifie le plus court chemin pour un joueur donné
      * @param joueur le joueur dont le chemin doit être identifié
      */
    public void plannification (Joueur joueur) {
      try {
        Graphe graphe = new Graphe(joueur.getPion().getCoordonnee().getX1(),joueur.getPion().getCoordonnee().getY1(),this.plateau.getDamier());
        Noeud depart = null;
        for (Noeud n : graphe.getNoeuds()) {
          if (n.getNom().equals(String.valueOf(joueur.getPion().getCoordonnee().getX1()) + " " + String.valueOf(joueur.getPion().getCoordonnee().getY1()))) {
            depart = n;
          }
        }
        if (depart == null) {
          throw new Exception("Erreur de l'IA, parametres null");
        }
        else {
          graphe = Graphe.dijkstra(graphe,depart);
          ArrayList<ArrayList<Noeud>> chemins = new ArrayList<ArrayList<Noeud>>();
          for (Noeud n : graphe.getNoeuds()) {
            if (joueur.getNumero() == 1) {
              if (n.getNom().split(" ")[0].trim().equals("16")) {
                ArrayList<Noeud> unChemin = new ArrayList<Noeud>();
                for (Noeud n2 : n.getPlusCourtChemin()) {
                  unChemin.add(n2);
                }
                unChemin.add(n);
                chemins.add(unChemin);
              }
            }
            else if (joueur.getNumero() == 2) {
              if (n.getNom().split(" ")[0].trim().equals("0")) {
                ArrayList<Noeud> unChemin = new ArrayList<Noeud>();
                for (Noeud n2 : n.getPlusCourtChemin()) {
                  unChemin.add(n2);
                }
                unChemin.add(n);
                chemins.add(unChemin);
              }
            }
            else if (joueur.getNumero() == 3) {
              if (n.getNom().split(" ")[1].trim().equals("16")) {
                ArrayList<Noeud> unChemin = new ArrayList<Noeud>();
                for (Noeud n2 : n.getPlusCourtChemin()) {
                  unChemin.add(n2);
                }
                unChemin.add(n);
                chemins.add(unChemin);
              }
            }
            else if (joueur.getNumero() == 4) {
              if (n.getNom().split(" ")[1].trim().equals("0")) {
                ArrayList<Noeud> unChemin = new ArrayList<Noeud>();
                for (Noeud n2 : n.getPlusCourtChemin()) {
                  unChemin.add(n2);
                }
                unChemin.add(n);
                chemins.add(unChemin);
              }
            }
          }
          this.plusCourtChemin = chemins.get(0);
          for (ArrayList<Noeud> chemin : chemins) {
            if (chemin.get(chemin.size()-1).getDistance() < this.plusCourtChemin.get(this.plusCourtChemin.size()-1).getDistance()) {
              this.plusCourtChemin = chemin;
            }
          }
        }
      }
      catch (Exception e) {
        System.err.println("Erreur de l'IA");
      }

    }

    /**
      * Permet à l'IA de jouer
      */
    public Barriere jeu() {
      Barriere ret = null;
      try {
        System.out.println("L'IA est en train de jouer");
        Thread.sleep(500);
        if (this.DIFFICULTE == Difficulte.FACILE) {
          this.randomMove();
        }
        else if (this.DIFFICULTE == Difficulte.DIFFICILE) {
          ArrayList<ArrayList<Noeud>> chemins = new ArrayList<ArrayList<Noeud>>();
          for (Joueur j : this.joueurs) {
              plannification(j);
              chemins.add(this.plusCourtChemin);
          }
          int num = this.joueurs.size();
          for (ArrayList<Noeud> c : chemins) {
            if (c.get(c.size()-1).getDistance() < this.plusCourtChemin.get(plusCourtChemin.size()-1).getDistance()) {
              this.plusCourtChemin = c;
              num = chemins.indexOf(c) + 1;
            }
          }
          System.out.println(num + "   " + this.NUMERO);
          if (num == this.NUMERO || this.forceMove) {
            this.smartMove();
          }
          else {
            if (this.plusCourtChemin.get(0).getDistance() == Integer.MAX_VALUE) {
              this.randomMove();
            }
            Noeud actuel = this.plusCourtChemin.get(0);
            Noeud suivant = this.plusCourtChemin.get(1);
            if (Integer.parseInt(actuel.getNom().split(" ")[0]) != Integer.parseInt(suivant.getNom().split(" ")[0])) {
              int xBarriere = (int) ((Integer.parseInt(actuel.getNom().split(" ")[0])+Integer.parseInt(suivant.getNom().split(" ")[0])) / 2);
              int y1Barriere = Integer.parseInt(actuel.getNom().split(" ")[1]);
              int y2Barriere = Integer.parseInt(actuel.getNom().split(" ")[1]) + 2;
              if (y2Barriere > 16 ) {
                y2Barriere = y1Barriere;
                y1Barriere -= 2;
              }
              else if (y1Barriere < 0) {
                y1Barriere = y2Barriere;
                y2Barriere += 2;
              }
              if (!this.plateau.getDamier()[xBarriere][y1Barriere]) {
                y1Barriere += 2;
                y2Barriere += 2;
              }
              else if (!this.plateau.getDamier()[xBarriere][y2Barriere]) {
                y1Barriere -= 2;
                y2Barriere -= 2;
              }
              if ((xBarriere < 0) || (xBarriere >= this.plateau.getDamier().length) || (y1Barriere < 0) || (y1Barriere >= this.plateau.getDamier().length) || (y2Barriere < 0) || (y2Barriere >= this.plateau.getDamier().length)) {
                this.smartMove();
              }
              else if (!this.plateau.getDamier()[xBarriere][y1Barriere] || !this.plateau.getDamier()[xBarriere][y2Barriere] || !this.plateau.getDamier()[xBarriere][(int)((y1Barriere+y2Barriere)/2)]) {
                this.smartMove();
              }
              else {
                placerBarriere(new Coordonnee(xBarriere,y1Barriere,xBarriere,y2Barriere));
              }
            }
            else if (Integer.parseInt(actuel.getNom().split(" ")[1]) != Integer.parseInt(suivant.getNom().split(" ")[1])) {
              int xBarriere = (int) ((Integer.parseInt(actuel.getNom().split(" ")[1])+Integer.parseInt(suivant.getNom().split(" ")[1])) / 2);
              int y1Barriere = Integer.parseInt(actuel.getNom().split(" ")[0]);
              int y2Barriere = Integer.parseInt(actuel.getNom().split(" ")[0]) + 2;
              if (y2Barriere > 16 ) {
                y2Barriere = y1Barriere;
                y1Barriere -= 2;
              }
              else if (y1Barriere < 0) {
                y1Barriere = y2Barriere;
                y2Barriere += 2;
              }
              if (!this.plateau.getDamier()[y1Barriere][xBarriere]) {
                y1Barriere += 2;
                y2Barriere += 2;
              }
              else if (!this.plateau.getDamier()[y2Barriere][xBarriere]) {
                y1Barriere -= 2;
                y2Barriere -= 2;
              }
              if ((xBarriere < 0) || (xBarriere >= this.plateau.getDamier().length) || (y1Barriere < 0) || (y1Barriere >= this.plateau.getDamier().length) || (y2Barriere < 0) || (y2Barriere >= this.plateau.getDamier().length)) {
                this.smartMove();
              }
              else if (!this.plateau.getDamier()[y1Barriere][xBarriere] || !this.plateau.getDamier()[y2Barriere][xBarriere] || !this.plateau.getDamier()[(int)((y1Barriere+y2Barriere)/2)][xBarriere]) {
                this.smartMove();
              }
              else {
                placerBarriere(new Coordonnee(y1Barriere,xBarriere,y2Barriere,xBarriere));
              }
            }
            else {
              System.err.println("Erreur dans la plannification de l'IA");
            }
          }
        }
      }
      catch (NullPointerException e) {
        System.err.println("Erreur de l'IA , pointeur null");
      }
      catch (IndexOutOfBoundsException ex) {
        System.err.println("Erreur de l'IA, indice hors des bornes");
      }
      catch (Exception exc) {
        System.err.println("Erreur inconnue");
      }
      finally {
        this.forceMove = false;
        return ret;
      }
    }

  /**
  * Force l'IA a bouger lors de son prochain tour
  * Permet de résoudre les conflits liés aux règles
  */
  public void forceMove() {
    this.forceMove = true;
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
  * @return la liste de noeuds contenant les différents noeuds que l'IA doit parcourir
  */
  public ArrayList<Noeud> getPlusCourtChemin() {
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

  /**
    * Bouge le pion de manière aléatoire
    */
    private void randomMove() {
      int[][] deplacementPossibles = this.pion.getDeplacementPossibles(this.plateau.getDamier());
      ArrayList<int[]> d = new ArrayList<int[]>();
      for (int[] tab : deplacementPossibles) {
        d.add(tab);
      }
      Collections.shuffle(d);
      deplacerPion(new Coordonnee(d.get(0)[0],d.get(0)[1],-1,-1),this.plateau.getDamier());
    }

  /**
    * Bouge le pion de manière réfléchie
    */
    private void smartMove() {
      this.plannification(this);
      if (this.plusCourtChemin.get(0).getDistance() == Integer.MAX_VALUE) {
        this.randomMove();
      }
      System.out.println(this.plusCourtChemin.get(1).getNom());
      Noeud next = this.plusCourtChemin.get(1);
      int nextX = Integer.parseInt(next.getNom().split(" ")[0]);
      int nextY = Integer.parseInt(next.getNom().split(" ")[1]);
      deplacerPion(new Coordonnee(nextX,nextY,-1,-1),this.plateau.getDamier());
    }


}
