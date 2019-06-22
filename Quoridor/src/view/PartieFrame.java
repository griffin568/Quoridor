package view;

import quoridor.*;
import controller.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PartieFrame extends JPanel {

  private MainFrame mainF;
  private Partie laPartie;
  private Controleur controleur;

  private Plateau plateau;
  private boolean[][] damier;
  private JButton[][] damierB;
  private ArrayList<Joueur> lesJoueurs;
  private ArrayList<Barriere> lesBarrieres;

  /**
    * Créé une page de jeu vide. Ce constructeur est utilisé pour permettre son utilisation future dans d'autres classes.
    */
  public PartieFrame() {}

  public PartieFrame(MainFrame parent, Mode leMode) {
    try {
      if (parent == null) {
        throw new Exception("L'écran principal doit exister pour créer l'écran de de jeu");
      }
      else if (leMode == null) {
        throw new Exception("La difficulté doit être valide pour pouvoir créer l'écran de jeu.");
      }
      else {
        this.mainF = parent;
        this.laPartie = new Partie(leMode);
        this.controleur = new Controleur(this.laPartie,this.laPartie.getJoueurs().get(0));
        this.setBackground(Color.BLACK);

        this.plateau = this.laPartie.getPlateau();
        this.damier = this.plateau.getDamier();
        this.damierB = new JButton[17][17];
        this.lesJoueurs = this.laPartie.getJoueurs();
        this.lesBarrieres = this.laPartie.getBarrieres();

        initComponent();
      }
    }
    catch(Exception e) {
      System.err.println(e.getMessage());
    }
  }

  public PartieFrame(MainFrame parent, Partie laPartie) {
    try {
      if (parent == null) {
        throw new Exception("L'écran principal doit exister pour créer l'écran de de jeu");
      }
      else if (laPartie == null) {
        throw new Exception("La partie doit exister pour créer l'écran de jeu.");
      }
      else {
        this.mainF = parent;
        this.laPartie = laPartie;
        this.setBackground(Color.BLACK);

        this.plateau = this.laPartie.getPlateau();
        this.damier = this.plateau.getDamier();
        this.damierB = new JButton[17][17];
        this.lesJoueurs = this.laPartie.getJoueurs();
        this.lesBarrieres = this.laPartie.getBarrieres();

        initComponent();
      }
    }
    catch(Exception e) {
      System.err.println(e.getMessage());
    }
  }

  public void initComponent() {
    this.setLayout(new GridLayout(17,17));
    final String PATH = "../data/img/";
    for (int i = 0; i < this.damier.length - 1; i++) {
      for (int j = 0; j < this.damier[i].length - 1; j++) {
        if ((i % 2 == 1) || (j % 2 == 1)) {
          //JButton des murs
          if (!this.damier[i][j]) { // Si la case contient une barrière
            this.damierB[i][j] = new JButton(new ImageIcon(PATH + "Barriere.jpg"));
          }
          else {
            this.damierB[i][j] = new JButton(new ImageIcon(PATH + "NonBarriere.jpg"));
          }
        }
        else {
          //JButton des cases
          this.damierB[i][j] = new JButton(new ImageIcon(PATH + "brick2.jpg"));
          if (!this.damier[i][j]) {//Si la case contient un pion
            for (Joueur jo : this.lesJoueurs) {
              Pion p = jo.getPion();
              if ((p.getCoordonnee().getX1() == i) && (p.getCoordonnee().getY1() == j)) {
                this.damierB[i][j] = new JButton(new ImageIcon(PATH + "Pion-" + jo.getCouleur() +".jpg"));
              }
            }
          }
          else {
              this.damierB[i][j] = new JButton(new ImageIcon(PATH + "NonPion.jpg"));
          }
        }
        this.damierB[i][j].addKeyListener(new PartiePauseListener(this.mainF));
        this.damierB[i][j].addMouseListener(new PartieListener(i,j,this.controleur,this));
        this.damierB[i][j].setOpaque(false);
        this.damierB[i][j].setContentAreaFilled(false);
        this.damierB[i][j].setBorderPainted(false);
        this.add(this.damierB[i][j]);
      }
    }
  }
  /**
    * Met à jour l'affichage visuel
    */
    public void updateFrame() {
      for (int i = 0 ; i < this.damierB.length ; i++) {
        for (int j = 0 ; j < this.damierB.length ; j++) {
          if (i % 2 == 0 && j % 2 == 0) {
            if (!damier[i][j]) {
              for (Joueur joueur : this.laPartie.getJoueurs()) {
                if (joueur.getPion().getCoordonnee().getX1() == i && joueur.getPion().getCoordonnee().getY1() == j) {
                  this.damierB[i][j].setIcon(new ImageIcon("../data/img/Pion-"+joueur.getCouleur()+".jpg"));
                  System.out.println("../data/img/Pion-"+joueur.getCouleur()+".jpg");
                }
              }
            }
            else {
              this.damierB[i][j].setIcon(new ImageIcon("../data/img/NonPion.jpg"));
            }
          }
          else if (!damier[i][j]) {
            this.damierB[i][j].setIcon(new ImageIcon("../data/img/Barriere.jpg"));
          }
        }
      }
    }


    public ArrayList<Joueur> getJoueurs() {
      return this.joueurs;
    }

    public ArrayList<Barriere> getBarrieres() {
      return this.barrieres;
    }

    public Partie getPartie() {
      return this.laPartie;
    }
}
