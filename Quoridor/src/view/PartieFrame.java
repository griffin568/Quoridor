package view;

import quoridor.*;
import controller.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
  * L'écran affichant la partie en mode graphique
  * @author Drmarsupial35 , griffin568
  * @version 0.9.0
  */
public class PartieFrame extends JPanel {

  private MainFrame mainF;
  private Partie laPartie;
  private Controleur controleur;

  private Plateau plateau;
  private boolean[][] damier;
  private JButton[][] damierB;
  private ArrayList<Joueur> lesJoueurs;
  private ArrayList<Barriere> lesBarrieres;
  private ArrayList<Pion> lesPions;

  private JButton titreContainer;
  private JButton backButton;
  private JTextArea rWall;
  private JLabel j1Wall,j2Wall,j3Wall,j4Wall;
  private JLabel click;

  /**
    * Créé une page de jeu vide. Ce constructeur est utilisé pour permettre son utilisation future dans d'autres classes.
    */
  public PartieFrame() {}


  /**
    * Créé un nouvel objet PartieFrame
    * @param parent la fenêtre principale de l'application
    * @param laPartie la partie à afficher à l'écran
    */
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
        this.controleur = new Controleur(this.laPartie,this.laPartie.getJoueurs().get(0),this);
        this.setBackground(Color.BLACK);

        this.plateau = this.laPartie.getPlateau();
        this.damier = this.plateau.getDamier();
        this.damierB = new JButton[17][17];
        this.lesJoueurs = this.laPartie.getJoueurs();
        this.lesBarrieres = this.laPartie.getBarrieres();

        this.lesPions = new ArrayList<Pion>();
        for (Joueur j : this.lesJoueurs) {
          lesPions.add(j.getPion());
        }

        initComponent();

        this.autoJeu();
      }
    }
    catch(Exception e) {
      System.err.println(e.getMessage());
    }
  }

  /**
    * Initialise les différents composants
    */
  public void initComponent() {

    JPanel up = new JPanel(new BorderLayout());
    JPanel grid = new JPanel(new GridLayout(17,17));
    JPanel down = new JPanel(new BorderLayout());
    JPanel left = new JPanel(new BorderLayout());
    this.setLayout(new BorderLayout());


    this.titreContainer = new JButton("Au tour de " + this.controleur.getJoueurActif().getNom());

    this.click = new JLabel("Cliquez");
    this.click.setForeground(Color.WHITE);
    left.add(this.click,BorderLayout.NORTH);

    this.rWall = new JTextArea("Murs restants");
    this.rWall.setBackground(Color.BLACK);
    this.rWall.setForeground(Color.WHITE);

    this.j1Wall = new JLabel();
    this.j2Wall = new JLabel();
    this.j3Wall = new JLabel();
    this.j4Wall = new JLabel();

    if (this.lesJoueurs.size() == 2) {
      this.j1Wall.setText(this.lesJoueurs.get(0).getNom() + " : " +this.lesJoueurs.get(0).getBarrieres().size());
      this.j2Wall.setText(this.lesJoueurs.get(1).getNom() + " : " +this.lesJoueurs.get(1).getBarrieres().size());
      this.j1Wall.setForeground(Color.WHITE);
      this.j2Wall.setForeground(Color.WHITE);
      JPanel wallContainer = new JPanel(new BorderLayout());
      JPanel playerWallContainer = new JPanel(new GridLayout(2,1));
      playerWallContainer.add(this.j1Wall);
      playerWallContainer.add(this.j2Wall);
      playerWallContainer.setBackground(Color.BLACK);
      wallContainer.add(this.rWall,BorderLayout.NORTH);
      wallContainer.add(playerWallContainer,BorderLayout.CENTER);
      left.add(wallContainer,BorderLayout.SOUTH);
    }
    else if (this.lesJoueurs.size() == 4) {
      this.j1Wall.setText(this.lesJoueurs.get(0).getNom() + " : " + this.lesJoueurs.get(0).getBarrieres().size());
      this.j2Wall.setText(this.lesJoueurs.get(1).getNom() + " : " +this.lesJoueurs.get(1).getBarrieres().size());
      this.j3Wall.setText(this.lesJoueurs.get(2).getNom() + " : " +this.lesJoueurs.get(2).getBarrieres().size());
      this.j4Wall.setText(this.lesJoueurs.get(3).getNom() + " : " +this.lesJoueurs.get(3).getBarrieres().size());
      this.j1Wall.setForeground(Color.WHITE);
      this.j2Wall.setForeground(Color.WHITE);
      this.j3Wall.setForeground(Color.WHITE);
      this.j4Wall.setForeground(Color.WHITE);
      JPanel wallContainer = new JPanel(new BorderLayout());
      JPanel playerWallContainer = new JPanel(new GridLayout(4,1));
      playerWallContainer.add(this.j1Wall);
      playerWallContainer.add(this.j2Wall);
      playerWallContainer.add(this.j3Wall);
      playerWallContainer.add(this.j4Wall);
      playerWallContainer.setBackground(Color.BLACK);
      playerWallContainer.setForeground(Color.WHITE);
      wallContainer.add(this.rWall,BorderLayout.NORTH);
      wallContainer.add(playerWallContainer,BorderLayout.CENTER);
      left.add(wallContainer,BorderLayout.SOUTH);
    }



    up.setBackground(Color.BLACK);
    grid.setBackground(Color.BLACK);
    down.setBackground(Color.BLACK);
    left.setBackground(Color.BLACK);
    this.setBackground(Color.BLACK);

    this.titreContainer.setOpaque(false);
    this.titreContainer.setContentAreaFilled(false);
    this.titreContainer.setBorderPainted(false);
    this.titreContainer.setFocusable(false);
    this.titreContainer.setForeground(Color.WHITE);
    up.add(titreContainer, BorderLayout.CENTER);

    this.backButton = new JButton("Jeu humain");
    this.backButton.addActionListener(new JeuIAListener(this.controleur,this));
    down.add (this.backButton,BorderLayout.EAST);


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
        this.damierB[i][j].addMouseListener(new PartieListener(i,j,this.controleur,this,this.click));
        this.damierB[i][j].setOpaque(false);
        this.damierB[i][j].setContentAreaFilled(false);
        this.damierB[i][j].setBorderPainted(false);
        grid.add(this.damierB[i][j]);
      }
    }
    this.add(up,BorderLayout.NORTH);
    this.add(grid,BorderLayout.CENTER);
    this.add(down,BorderLayout.SOUTH);
    this.add(left,BorderLayout.WEST);
  }
  /**
    * Met à jour l'affichage visuel
    */
    public void updateFrame() {
      this.plateau = this.laPartie.getPlateau();
      this.damier = this.plateau.getDamier();
      this.lesJoueurs = this.laPartie.getJoueurs();
      this.lesBarrieres = this.laPartie.getBarrieres();
      this.lesPions = new ArrayList<Pion>();
      for (Joueur j : this.lesJoueurs) {
        lesPions.add(j.getPion());
      }
      for (int i = 0 ; i < this.damierB.length ; i++) {
        for (int j = 0 ; j < this.damierB.length ; j++) {
          if (i % 2 == 0 && j % 2 == 0) {
            if (!damier[i][j]) {
              for (Pion p : this.lesPions) {
                if (p.getCoordonnee().getX1() == i && p.getCoordonnee().getY1() == j) {
                  this.damierB[i][j].setIcon(new ImageIcon("../data/img/Pion-"+p.getCouleur()+".jpg"));
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
          else if (damier[i][j]) {
            this.damierB[i][j].setIcon(new ImageIcon("../data/img/NonBarriere.jpg"));
          }
        }
      }
      this.titreContainer.setText("Au tour de " + this.controleur.getJoueurActif().getNom());

      if (this.lesJoueurs.size() == 2) {
        this.j1Wall.setText(this.lesJoueurs.get(0).getNom() + " : " + this.lesJoueurs.get(0).getBarrieres().size());
        this.j2Wall.setText(this.lesJoueurs.get(1).getNom() + " : " +this.lesJoueurs.get(1).getBarrieres().size());
      }
      else if (this.lesJoueurs.size() == 4) {
        this.j1Wall.setText(this.lesJoueurs.get(0).getNom() + " : " + this.lesJoueurs.get(0).getBarrieres().size());
        this.j2Wall.setText(this.lesJoueurs.get(1).getNom() + " : " +this.lesJoueurs.get(1).getBarrieres().size());
        this.j3Wall.setText(this.lesJoueurs.get(2).getNom() + " : " +this.lesJoueurs.get(2).getBarrieres().size());
        this.j4Wall.setText(this.lesJoueurs.get(3).getNom() + " : " +this.lesJoueurs.get(3).getBarrieres().size());
      }

      if (!this.controleur.getJoueurActif().isHumain()) {
        this.backButton.setText("Jeu IA");
      }
      else {
        this.backButton.setText("Jeu humain");
      }

    }

    /**
      * Vérifie si le joueur actif est une IA
      * Si c'est le cas, l'IA joue
      */
      public void autoJeu() {
        if (!this.controleur.getJoueurActif().isHumain()) {
          this.controleur.jeuAuto();
        }
      }

    /**
      * Retourne la liste des joueurs de la partie
      * @return la liste des joueurs
      */
    public ArrayList<Joueur> getJoueurs() {
      return this.lesJoueurs;
    }

    /**
      * Retourne la liste des barrières posées durant la partie
      * @return la liste des barrières posées
      */
    public ArrayList<Barriere> getBarrieres() {
      return this.lesBarrieres;
    }

    /**
      * Retourne la partie affichée à l'écran
      * @return la partie en cours
      */
    public Partie getPartie() {
      return this.laPartie;
    }

    /**
      * Retourne le controleur utilisé pour la partie
      * @return le controleur de la partie
      */
    public Controleur getControleur() {
      return this.controleur;
    }
}
