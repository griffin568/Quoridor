package quoridor;

import java.util.ArrayList;
import java.io.FileNotFoundException;
import quoridor.dijkstra.*;

/**
* Cette classe gère les différents aspect de la partie
* @author AlexM02 , Drmarsupial35 , Eclixal , griffin568
* @version 1.0.0
*/
public class Partie {

  private int tour;
  private Mode mode;
  private Plateau plateau;
  private ArrayList<Joueur> joueurs;
  private ArrayList<Barriere> barrieres;
  private long startRec;
  private long endRec;
  private boolean visuel;


  /**
    * Créé une partie vide. Ce constructeur peut être utilisé si l'on souhaite charger une partie.
    */
  public Partie() {
    ArrayList<String> lignes = RWFile.readFile("config2");
    this.plateau = new Plateau(Integer.parseInt(lignes.get(0)));
  }

/**
  * Créé un nouvel objet Partie
  * @param mode le mode de jeu
  * @param lesJoueurs la liste des noms des diférents joueurs
  * @param visuel vrai si la partie se déroule en mode graphique, faux sinon
  */
  public Partie(Mode mode , boolean visuel, ArrayList<String> lesJoueurs) {
    try {
      if (mode == null) {
        throw new Exception("Partie constructeur - Le mode de jeu doit être valide pour pouvoir être utilisé.");
      }
      else if (lesJoueurs == null) {
        throw new Exception("Partie constructeur - Les noms des joueurs doivent être valides pour créer la partie.");
      }
      else {
        this.visuel = visuel;
        initialisation(mode, lesJoueurs);
      }
    }
    catch(Exception e) {
      System.err.println(e.getMessage());
    }
  }

/**
  * Change le plateau utilisé
  * @param plateau le nouveau plateau
  */
  public void setPlateau (Plateau plateau) {
    try {
      if (plateau == null) {
        throw new Exception ("Erreur setPlateau(), parametre null");
      }
      else {
        this.plateau = plateau;
      }
    }
    catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

/**
  * Change la liste des joueurs
  * @param joueurs la nouvelle liste
  */
  public void setJoueurs (ArrayList<Joueur> joueurs) {
    try {
      if (joueurs == null) {
        throw new Exception ("Erreur setJoueurs(), parametre null");
      }
      else {
        this.joueurs = joueurs;
      }
    }
    catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

/**
  * Change la liste des barrières posées
  * @param barrieres la nouvelle liste
  */
  public void setBarrieres (ArrayList<Barriere> barrieres) {
    try {
      if (barrieres == null) {
        throw new Exception ("Erreur setBarrieres(), parametre null");
      }
      else {
        this.barrieres = barrieres;
      }
    }
    catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

/**
  * Sauvegarde la partie
  * @param fileName nom du fichier où nous souhaitons sauvegarder la partie
  * @param leJoueur le joueur qui a lancé la sauvegarde (Donc celui qui commencera à jouer lors de la reprise de la partie)
  */
  public void sauvegarder(String fileName, Joueur leJoueur) {
    try {
      if (fileName == null) {
        throw new Exception("Partie sauvegarder() - Le nom du fichier doit exister");
      }
      else if (leJoueur == null) {
        throw new Exception("Partie sauvegarder() - Le dernier joueur ayant joué doit exister");
      }
      else {
        RWFile.writeFile(fileName, this.joueurs, this.barrieres, this.tour, leJoueur, this.visuel);
        System.out.println("La partie a bien été sauvegardé !");
      }
    }
    catch (Exception e) {
      System.err.println(e.getMessage());
    }

  }

/**
  * Charge les données de sauvegarde contenues dans le fichier sélectionné
  * @param filename le fichier contenant les données à charger
  * @return la partie chargée
  */
  public Partie charger(String filename) {
    try {
      if (filename == null) {
        throw new Exception("Partie constructeur - Le nom du fichier doit être valide pour pouvoir être utilisé.");
      }
      else {
        this.plateau = new Plateau(this.plateau.getDamier().length);
        this.joueurs = new ArrayList<Joueur>();
        this.barrieres = new ArrayList<Barriere>();
        ArrayList<String> lignes = RWFile.readFile(filename);
        String[] lesJoueurs = lignes.get(0).split(";"); // La liste des joueurs sous forme de String
        String[] lesPions = lignes.get(1).split(";"); // La liste des joueurs sous forme de String
        String[] lesBarrieres = lignes.get(2).split(";"); // La liste des barrieres sous forme de String
        String[] leReste = lignes.get(3).split(";"); // Le reste des informations (n° de tour & tour du premier joueur) sous forme de String
        ArrayList<String[]> desJoueurs = new ArrayList<String[]>(); // Liste de toutes les informations de chaque joueur
        int i = 0;
        if (leReste[2].equals("1")) {
          this.visuel = true;
        }
        else {
          this.visuel = false;
        }

        for (String j: lesJoueurs) {
          desJoueurs.add(j.split(" "));
          i++;
        }
        this.tour = Integer.parseInt(leReste[0].trim());

        if (i == 2) {
          //Création des pions
          String[] pion1 = lesPions[0].split(" ");
          String[] pion2 = lesPions[1].split(" ");

          String couleur1 = pion1[0].trim();
          String couleur2 = pion2[0].trim();

          Coordonnee coord1 = new Coordonnee(Integer.parseInt(pion1[1]), Integer.parseInt(pion1[2]), -1, -1);
          Coordonnee coord2 = new Coordonnee(Integer.parseInt(pion2[1]), Integer.parseInt(pion2[2]), -1, -1);

          Coordonnee coord1B = new Coordonnee(0, 8, -1 ,-1);
          Coordonnee coord2B = new Coordonnee(16, 8, -1 ,-1);

          Pion p1 = new Pion(couleur1, coord1B);
          Pion p2 = new Pion(couleur2, coord2B);

          ArrayList<Barriere> barriere1 = new ArrayList<Barriere>();
          ArrayList<Barriere> barriere2 = new ArrayList<Barriere>();

          for (int j = 0; j < Integer.parseInt(lesBarrieres[0].trim()); j++) {
            barriere1.add(new Barriere(couleur1, this.plateau));
          }
          for (int j = 0; j < Integer.parseInt(lesBarrieres[1].trim()); j++) {
            barriere2.add(new Barriere(couleur2, this.plateau));
          }

          String[] joueur1 = desJoueurs.get(0);
          String[] joueur2 = desJoueurs.get(1);

          String nom1 = joueur1[0];
          String nom2 = joueur2[0];

          Joueur J1, J2;
          if (joueur1[1].trim().equals("H") && joueur2[1].trim().equals("H")) {
            this.mode = Mode.HH;
            J1 = new Humain(nom1, 1, couleur1, barriere1, p1, this.plateau);
            J2 = new Humain(nom2, 2, couleur2, barriere2, p2, this.plateau);
          }
          else if (joueur1[1].trim().equals("H") && joueur2[1].trim().equals("IA")) {
            this.mode = Mode.HI;
            J1 = new Humain(nom1, 1, couleur1, barriere1, p1, this.plateau);
            J2 = new IA(nom2, 2, couleur2, barriere2, p2, this.plateau, Difficulte.FACILE,this.joueurs);
          }
          else {
            this.mode = Mode.II;
            J1 = new IA(nom1, 1, couleur1, barriere1, p1, this.plateau, Difficulte.FACILE,this.joueurs);
            J2 = new IA(nom2, 2, couleur2, barriere2, p2, this.plateau, Difficulte.FACILE,this.joueurs);
          }

          if (leReste[1].trim().equals("1")) {
            this.joueurs.add(J1);
            this.joueurs.add(J2);
          }
          else {
            this.joueurs.add(J2);
            this.joueurs.add(J1);
          }

          J1.deplacerPion(coord1, this.plateau.getDamier());
          J2.deplacerPion(coord2, this.plateau.getDamier());
        }

        else {
          String[] pion1 = lesPions[0].split(" ");
          String[] pion2 = lesPions[1].split(" ");
          String[] pion3 = lesPions[2].split(" ");
          String[] pion4 = lesPions[3].split(" ");

          String couleur1 = pion1[0];
          String couleur2 = pion2[0];
          String couleur3 = pion3[0];
          String couleur4 = pion4[0];

          Coordonnee coord1 = new Coordonnee(Integer.parseInt(pion1[1]), Integer.parseInt(pion1[2]), -1, -1);
          Coordonnee coord2 = new Coordonnee(Integer.parseInt(pion2[1]), Integer.parseInt(pion2[2]), -1, -1);
          Coordonnee coord3 = new Coordonnee(Integer.parseInt(pion3[1]), Integer.parseInt(pion3[2]), -1, -1);
          Coordonnee coord4 = new Coordonnee(Integer.parseInt(pion4[1]), Integer.parseInt(pion4[2]), -1, -1);

          Coordonnee coord1B = new Coordonnee(0, 8, -1 ,-1);
          Coordonnee coord2B = new Coordonnee(16, 8, -1 ,-1);
          Coordonnee coord3B = new Coordonnee(8, 0, -1 ,-1);
          Coordonnee coord4B = new Coordonnee(8, 16, -1 ,-1);

          Pion p1 = new Pion(couleur1, coord1B);
          Pion p2 = new Pion(couleur2, coord2B);
          Pion p3 = new Pion(couleur3, coord3B);
          Pion p4 = new Pion(couleur4, coord4B);

          ArrayList<Barriere> barriere1 = new ArrayList<Barriere>();
          ArrayList<Barriere> barriere2 = new ArrayList<Barriere>();
          ArrayList<Barriere> barriere3 = new ArrayList<Barriere>();
          ArrayList<Barriere> barriere4 = new ArrayList<Barriere>();

          for (int j = 0; j < Integer.parseInt(lesBarrieres[0]); j++) {
            barriere1.add(new Barriere(couleur1, this.plateau));
          }
          for (int j = 0; j < Integer.parseInt(lesBarrieres[1]); j++) {
            barriere2.add(new Barriere(couleur2, this.plateau));
          }
          for (int j = 0; j < Integer.parseInt(lesBarrieres[2]); j++) {
            barriere3.add(new Barriere(couleur3, this.plateau));
          }
          for (int j = 0; j < Integer.parseInt(lesBarrieres[3]); j++) {
            barriere4.add(new Barriere(couleur4, this.plateau));
          }

          String[] joueur1 = desJoueurs.get(0);
          String[] joueur2 = desJoueurs.get(1);
          String[] joueur3 = desJoueurs.get(2);
          String[] joueur4 = desJoueurs.get(3);

          String nom1 = joueur1[0];
          String nom2 = joueur2[0];
          String nom3 = joueur3[0];
          String nom4 = joueur4[0];

          Joueur J1, J2, J3, J4;
          if (joueur1[1].trim().equals("H") && joueur2[1].trim().equals("H") && joueur3[1].trim().equals("H") && joueur4[1].trim().equals("H")) {
            this.mode = Mode.HHHH;
            J1 = new Humain(nom1, 1, couleur1, barriere1, p1, this.plateau);
            J2 = new Humain(nom2, 2, couleur2, barriere2, p2, this.plateau);
            J3 = new Humain(nom3, 3, couleur3, barriere3, p3, this.plateau);
            J4 = new Humain(nom4, 4, couleur4, barriere4, p4, this.plateau);
          }
          else if (joueur1[1].trim().equals("H") && joueur2[1].trim().equals("H") && joueur3[1].trim().equals("H") && joueur4[1].trim().equals("IA")) {
            J1 = new Humain(nom1, 1, couleur1, barriere1, p1, this.plateau);
            J2 = new Humain(nom2, 2, couleur2, barriere2, p2, this.plateau);
            J3 = new Humain(nom3, 3, couleur3, barriere3, p3, this.plateau);
            J4 = new IA(nom4, 4, couleur4, barriere4, p4, this.plateau, Difficulte.FACILE,this.joueurs);
          }
          else if (joueur1[1].trim().equals("H") && joueur2[1].trim().equals("H") && joueur3[1].trim().equals("IA") && joueur4[1].trim().equals("IA")) {
            J1 = new Humain(nom1, 1, couleur1, barriere1, p1, this.plateau);
            J2 = new Humain(nom2, 2, couleur2, barriere2, p2, this.plateau);
            J3 = new IA(nom3, 3, couleur3, barriere3, p3, this.plateau, Difficulte.FACILE,this.joueurs);
            J4 = new IA(nom4, 4, couleur4, barriere4, p4, this.plateau, Difficulte.FACILE,this.joueurs);
          }
          else if (joueur1[1].trim().equals("H") && joueur2[1].trim().equals("IA") && joueur3[1].trim().equals("IA") && joueur4[1].trim().equals("IA")) {
            J1 = new Humain(nom1, 1, couleur1, barriere1, p1, this.plateau);
            J2 = new IA(nom2, 2, couleur2, barriere2, p2, this.plateau, Difficulte.FACILE,this.joueurs);
            J3 = new IA(nom3, 3, couleur3, barriere4, p3, this.plateau, Difficulte.FACILE,this.joueurs);
            J4 = new IA(nom4, 4, couleur4, barriere4, p4, this.plateau, Difficulte.FACILE,this.joueurs);
          }
          else {
            J1 = new IA(nom1, 1, couleur1, barriere1, p1, this.plateau, Difficulte.FACILE,this.joueurs);
            J2 = new IA(nom2, 2, couleur2, barriere2, p2, this.plateau, Difficulte.FACILE,this.joueurs);
            J3 = new IA(nom3, 3, couleur3, barriere4, p3, this.plateau, Difficulte.FACILE,this.joueurs);
            J4 = new IA(nom4, 4, couleur4, barriere4, p4, this.plateau, Difficulte.FACILE,this.joueurs);
          }

          if (leReste[1].trim().equals("1")) {
            this.joueurs.add(J1);
            this.joueurs.add(J2);
            this.joueurs.add(J3);
            this.joueurs.add(J4);
          }
          else if (leReste[1].trim().equals("2")) {
            this.joueurs.add(J2);
            this.joueurs.add(J3);
            this.joueurs.add(J4);
            this.joueurs.add(J1);
          }
          else if (leReste[1].trim().equals("3")) {
            this.joueurs.add(J3);
            this.joueurs.add(J4);
            this.joueurs.add(J1);
            this.joueurs.add(J2);
          }
          else {
            this.joueurs.add(J4);
            this.joueurs.add(J1);
            this.joueurs.add(J2);
            this.joueurs.add(J3);
          }
          J1.deplacerPion(coord1, this.plateau.getDamier());
          J2.deplacerPion(coord2, this.plateau.getDamier());
          J3.deplacerPion(coord3, this.plateau.getDamier());
          J4.deplacerPion(coord4, this.plateau.getDamier());
        }

        i = 0;
        for (String j : lesJoueurs) {
          String[] lesInfos = j.split(" ");
          if(lesInfos.length > 3) {
            if (lesInfos[3].trim().equals("FACILE")) {
              ((IA)this.joueurs.get(i)).setDifficulte(Difficulte.FACILE);
            }
            else if (lesInfos[3].trim().equals("MOYEN")) {
              ((IA)this.joueurs.get(i)).setDifficulte(Difficulte.MOYEN);
            }
            else if (lesInfos[3].trim().equals("DIFFICILE")) {
              ((IA)this.joueurs.get(i)).setDifficulte(Difficulte.DIFFICILE);
            }
            else {
              ((IA)this.joueurs.get(i)).setDifficulte(Difficulte.IMPOSSIBLE);
            }
          }
          i++;
        }

        for (String b : lesBarrieres) {
          if (b.length() > 3) {
            String[] lesInfos = b.split(" ");
            String couleur = lesInfos[0];
            Coordonnee coord = new Coordonnee(Integer.parseInt(lesInfos[1]), Integer.parseInt(lesInfos[2]), Integer.parseInt(lesInfos[3]), Integer.parseInt(lesInfos[4]));
            Barriere laBarriere = new Barriere(couleur, this.plateau);
            laBarriere.setCoordonnee(coord);
            addBarriere(laBarriere);
          }
        }
      }
    }
    catch(FileNotFoundException e) {
      System.err.println("Partie charger - Fichier non trouvé (" + filename + ")");
    }
    catch (IndexOutOfBoundsException e) {
      System.err.println("Chargement de la partie impossible");
    }
    catch(Exception e) {
      System.err.println(e.getMessage());
    }
    finally {
      return this;
    }
  }

/**
  * Efface les données d'un emplacement de sauvegarde
  * @param fileName le nom du fichier dont les données seront effacées
  */
  public void effacer (String fileName) {
    try {
      if (fileName == null) {
        throw new Exception ("Erreur effacer(), parametre null");
      }
      else {
        RWFile.writeFile(fileName);
        System.out.println("La sauvegarde a bien ete effacee");
      }
    }
    catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

/**
  * Initialise les différents éléments constants de la partie
  * @param mode le mode jeu de la partie à créer
  * @param lesJoueurs la liste des joueurs
  */
  private void initialisation(Mode mode, ArrayList<String> lesJoueurs) {
    this.barrieres = new ArrayList<Barriere>();
    this.joueurs = new ArrayList<Joueur>();
    this.tour = 0;
    this.mode = mode;
    configuration(mode, lesJoueurs);
  }

/**
  * Configure les éléments non constants de la partie à l'aide du fichier de configuration
  * @param mode le mode de jeu de la partie à créer
  * @param lesJoueurs la liste des joueurs
  */
  private void configuration(Mode mode, ArrayList<String> lesJoueurs) {

    // Créer les joueurs
    if ((mode.equals(Mode.HH)) || (mode.equals(Mode.HI)) || (mode.equals(Mode.II))) {
      ArrayList<Barriere> BarriereJ1 = new ArrayList<Barriere>();
      ArrayList<Barriere> BarriereJ2 = new ArrayList<Barriere>();
      ArrayList<String> lignes = RWFile.readFile("config2");
      this.plateau = new Plateau(Integer.parseInt(lignes.get(0)));
      String[] coord1 = lignes.get(1).split(" ");
      String[] coord2 = lignes.get(2).split(" ");

      for (int i = 0; i< 10; i++) {
        BarriereJ1.add(new Barriere("O", this.plateau));
      }
      for (int i = 0; i < 10; i++) {
        BarriereJ2.add(new Barriere("W", this.plateau));
      }

      Coordonnee c1 = new Coordonnee(Integer.parseInt(coord1[0]),Integer.parseInt(coord1[1]), -1, -1);
      Coordonnee c2 = new Coordonnee(Integer.parseInt(coord2[0]),Integer.parseInt(coord1[1]), -1, -1);

      Joueur J1, J2;
      if (mode.equals(Mode.HH)) {
        J1 = new Humain(lesJoueurs.get(0),1,"O",BarriereJ1,new Pion("O",c1), this.plateau);
        J2 = new Humain(lesJoueurs.get(1),2,"W",BarriereJ2,new Pion("W",c2), this.plateau);
      }
      else if (mode.equals(Mode.HI)) {
        J1 = new Humain(lesJoueurs.get(0),1,"O",BarriereJ1,new Pion("O",c1), this.plateau);
        J2 = new IA(lesJoueurs.get(1),2,"W",BarriereJ2,new Pion("W",c2), this.plateau,Difficulte.FACILE,this.joueurs);
      }
      else {
        J1 = new IA(lesJoueurs.get(0),1,"O",BarriereJ1,new Pion("O",c1), this.plateau,Difficulte.FACILE,this.joueurs);
        J2 = new IA(lesJoueurs.get(1),2,"W",BarriereJ2,new Pion("W",c2), this.plateau,Difficulte.FACILE,this.joueurs);
      }

      ArrayList<int[]> aChanger = new ArrayList<int[]>();
      int[] lesCoords1 = {Integer.parseInt(coord1[0]),Integer.parseInt(coord1[1])};
      int[] lesCoords2 = {Integer.parseInt(coord2[0]),Integer.parseInt(coord1[1])};
      aChanger.add(lesCoords1);
      aChanger.add(lesCoords2);
      this.plateau.setDisponibilite(aChanger);

      this.joueurs.add(J1);
      this.joueurs.add(J2);
    }

    else {
      ArrayList<Barriere> BarriereJ1 = new ArrayList<Barriere>();
      ArrayList<Barriere> BarriereJ2 = new ArrayList<Barriere>();
      ArrayList<Barriere> BarriereJ3 = new ArrayList<Barriere>();
      ArrayList<Barriere> BarriereJ4 = new ArrayList<Barriere>();
      ArrayList<String> lignes = RWFile.readFile("config4");
      this.plateau = new Plateau(Integer.parseInt(lignes.get(0)));
      String[] coord1 = lignes.get(1).split(" ");
      String[] coord2 = lignes.get(2).split(" ");
      String[] coord3 = lignes.get(3).split(" ");
      String[] coord4 = lignes.get(4).split(" ");

      for (int i = 0; i< 5; i++) {
        BarriereJ1.add(new Barriere("O", this.plateau));
      }
      for (int i = 0; i< 5; i++) {
        BarriereJ2.add(new Barriere("W", this.plateau));
      }
      for (int i = 0; i< 5; i++) {
        BarriereJ3.add(new Barriere("Z", this.plateau));
      }
      for (int i = 0; i< 5; i++) {
        BarriereJ4.add(new Barriere("A", this.plateau));
      }

      Coordonnee c1 = new Coordonnee(Integer.parseInt(coord1[0]),Integer.parseInt(coord1[1]), -1, -1);
      Coordonnee c2 = new Coordonnee(Integer.parseInt(coord2[0]),Integer.parseInt(coord2[1]), -1, -1);
      Coordonnee c3 = new Coordonnee(Integer.parseInt(coord3[0]),Integer.parseInt(coord3[1]), -1, -1);
      Coordonnee c4 = new Coordonnee(Integer.parseInt(coord4[0]),Integer.parseInt(coord4[1]), -1, -1);

      Joueur J1,J2,J3,J4;

      if (this.mode.equals(Mode.HHHH)) {
        J1 = new Humain(lesJoueurs.get(0),1,"O",BarriereJ1,new Pion("O",c1), this.plateau);
        J2 = new Humain(lesJoueurs.get(1),2,"W",BarriereJ2,new Pion("W",c2), this.plateau);
        J3 = new Humain(lesJoueurs.get(2),3,"Z",BarriereJ3,new Pion("Z",c3), this.plateau);
        J4 = new Humain(lesJoueurs.get(3),4,"A",BarriereJ4,new Pion("A",c4), this.plateau);
      }
      else if (this.mode.equals(Mode.HHHI)) {
        J1 = new Humain(lesJoueurs.get(0),1,"O",BarriereJ1,new Pion("O",c1), this.plateau);
        J2 = new Humain(lesJoueurs.get(1),2,"W",BarriereJ2,new Pion("W",c2), this.plateau);
        J3 = new Humain(lesJoueurs.get(2),3,"Z",BarriereJ3,new Pion("Z",c3), this.plateau);
        J4 = new IA(lesJoueurs.get(3),4,"A",BarriereJ4,new Pion("A",c4), this.plateau,Difficulte.FACILE,this.joueurs);
      }
      else if (this.mode.equals(Mode.HHII)) {
        J1 = new Humain(lesJoueurs.get(0),1,"O",BarriereJ1,new Pion("O",c1), this.plateau);
        J2 = new Humain(lesJoueurs.get(1),2,"W",BarriereJ2,new Pion("W",c2), this.plateau);
        J3 = new IA(lesJoueurs.get(2),3,"Z",BarriereJ3,new Pion("Z",c3), this.plateau,Difficulte.FACILE,this.joueurs);
        J4 = new IA(lesJoueurs.get(3),4,"A",BarriereJ4,new Pion("A",c4), this.plateau,Difficulte.FACILE,this.joueurs);
      }
      else if (this.mode.equals(Mode.HIII)) {
        J1 = new Humain(lesJoueurs.get(0),1,"O",BarriereJ1,new Pion("O",c1), this.plateau);
        J2 = new IA(lesJoueurs.get(1),2,"W",BarriereJ2,new Pion("W",c2), this.plateau,Difficulte.FACILE,this.joueurs);
        J3 = new IA(lesJoueurs.get(2),3,"Z",BarriereJ3,new Pion("Z",c3), this.plateau,Difficulte.FACILE,this.joueurs);
        J4 = new IA(lesJoueurs.get(3),4,"A",BarriereJ4,new Pion("A",c4), this.plateau,Difficulte.FACILE,this.joueurs);
      }
      else {
        J1 = new IA(lesJoueurs.get(0),1,"O",BarriereJ1,new Pion("O",c1), this.plateau,Difficulte.FACILE,this.joueurs);
        J2 = new IA(lesJoueurs.get(1),2,"W",BarriereJ2,new Pion("W",c2), this.plateau,Difficulte.FACILE,this.joueurs);
        J3 = new IA(lesJoueurs.get(2),3,"Z",BarriereJ3,new Pion("Z",c3), this.plateau,Difficulte.FACILE,this.joueurs);
        J4 = new IA(lesJoueurs.get(3),4,"A",BarriereJ4,new Pion("A",c4), this.plateau,Difficulte.FACILE,this.joueurs);
      }

      ArrayList<int[]> aChanger = new ArrayList<int[]>();
      int[] lesCoords1 = {Integer.parseInt(coord1[0]),Integer.parseInt(coord1[1])};
      int[] lesCoords2 = {Integer.parseInt(coord2[0]),Integer.parseInt(coord1[1])};
      int[] lesCoords3 = {Integer.parseInt(coord3[0]),Integer.parseInt(coord3[1])};
      int[] lesCoords4 = {Integer.parseInt(coord4[0]),Integer.parseInt(coord4[1])};
      aChanger.add(lesCoords1);
      aChanger.add(lesCoords2);
      aChanger.add(lesCoords3);
      aChanger.add(lesCoords4);
      this.plateau.setDisponibilite(aChanger);

      this.joueurs.add(J1);
      this.joueurs.add(J2);
      this.joueurs.add(J3);
      this.joueurs.add(J4);
    }

  }

/**
  * Lance la partie
  * @return vrai si le tour s'est bien déroulé, faux sinon. Cette variable est utilisé uniquement dans le mode visuel de l'application
  */
  public boolean start() {
    boolean ret = true;
    if (!this.visuel) {
      ArrayList<Pion> listePion = new ArrayList<Pion>();
      for (Joueur j : this.joueurs) {
        listePion.add(j.getPion());
      }
      Plateau oldPlateau = this.savePlateau();
      ArrayList<Joueur> oldJoueurs = this.saveJoueurs(oldPlateau);
      ArrayList<Barriere> oldBarrieres = this.saveBarrieres();
      Plateau olderPlateau = this.savePlateau();
      ArrayList<Joueur> olderJoueurs = this.saveJoueurs(olderPlateau);
      ArrayList<Barriere> olderBarrieres = this.saveBarrieres();
      while (true) {
        for (int i = 0 ; i < this.joueurs.size() ; i++) {
          boolean error = false;
          olderPlateau = oldPlateau;
          olderJoueurs = oldJoueurs;
          olderBarrieres = oldBarrieres;
          oldPlateau = this.savePlateau();
          oldJoueurs = this.saveJoueurs(oldPlateau);
          oldBarrieres = this.saveBarrieres();
          System.out.println(this.plateau.toString(listePion,this.joueurs.get(i).getPion()));
          Barriere b = this.joueurs.get(i).jeu();
          if (b != null) {
            if (b.getCouleur().equalsIgnoreCase("back")) {
              this.plateau = olderPlateau;
              this.joueurs = olderJoueurs;
              this.barrieres = olderBarrieres;
              listePion = new ArrayList<Pion>();
              for (Joueur j : this.joueurs) {
                listePion.add(j.getPion());
              }
              i -= 2;
              if (i < 0) {
                i = this.joueurs.size() + i;
              }
            }
            else if (b.getCouleur().equalsIgnoreCase("error")) {
              this.plateau = oldPlateau;
              this.joueurs = oldJoueurs;
              this.barrieres = oldBarrieres;
              listePion = new ArrayList<Pion>();
              for (Joueur j : this.joueurs) {
                listePion.add(j.getPion());
              }
              i--;
            }
            else if (b.getCouleur().split(" ")[0].trim().equalsIgnoreCase("save")) {
              if (b.getCouleur().split(" ")[1].trim().equals("1") || b.getCouleur().split(" ")[1].trim().equals("2") || b.getCouleur().split(" ")[1].trim().equals("3")) {
                System.out.println("sauvegarde en cours");
                this.sauvegarder("sauvegarde" + b.getCouleur().split(" ")[1] , this.joueurs.get(i));
                i--;
              }
              else {
                System.err.println("Erreur lors de la sauvegarde de la partie, numero de fichier invalide");
              }
            }
            else if (b.getCouleur().split(" ")[0].trim().equalsIgnoreCase("load")) {
              if (b.getCouleur().split(" ")[1].trim().equals("1") || b.getCouleur().split(" ")[1].trim().equals("2") || b.getCouleur().split(" ")[1].trim().equals("3")) {
                System.out.println("chargement en cours");
                this.charger("sauvegarde" + b.getCouleur().split(" ")[1]);
                listePion = new ArrayList<Pion>();
                for (Joueur j : this.joueurs) {
                  listePion.add(j.getPion());
                }
                i = -1;
              }
              else {
                System.err.println("Erreur lors du chargement de la partie, numero de fichier invalide");
              }
            }
            else if (b.getCouleur().split(" ")[0].trim().equalsIgnoreCase("delete")) {
              if (b.getCouleur().split(" ")[1].trim().equals("1") || b.getCouleur().split(" ")[1].trim().equals("2") || b.getCouleur().split(" ")[1].trim().equals("3")) {
                System.out.println("effacement des donnees en cours");
                this.effacer("sauvegarde"+b.getCouleur().split(" ")[1]);
                i = -1;
              }
            }
            else {
              addBarriere(b);
            }
          }
          for (Joueur j2 : this.joueurs) {
            Graphe checkError = new Graphe(j2.getPion().getCoordonnee().getX1(),j2.getPion().getCoordonnee().getY1(),this.copieDamier());
            Noeud depart = null;
            for (Noeud n : checkError.getNoeuds()) {
              if (n.getNom().equals(String.valueOf(j2.getPion().getCoordonnee().getX1()) + " " + String.valueOf(j2.getPion().getCoordonnee().getY1()))) {
                depart = n;
              }
            }
            if (depart == null) {
              System.err.println("Erreur dans le déroulement de la partie");
            }
            else {
              checkError = Graphe.dijkstra(checkError,depart);
              int indice = 0;
              error = true;
              while (indice < checkError.getNoeuds().size() && error) {
                if (j2.getNumero() == 1) {
                  if (checkError.getNoeuds().get(indice).getNom().split(" ")[0].equals("16") && checkError.getNoeuds().get(indice).getDistance() < Integer.MAX_VALUE) {
                    error = false;
                  }
                }
                else if (j2.getNumero() == 2) {
                  if (checkError.getNoeuds().get(indice).getNom().split(" ")[0].equals("0") && checkError.getNoeuds().get(indice).getDistance() < Integer.MAX_VALUE) {
                    error = false;
                  }
                }
                else if (j2.getNumero() == 3) {
                  if (checkError.getNoeuds().get(indice).getNom().split(" ")[1].equals("16") && checkError.getNoeuds().get(indice).getDistance() < Integer.MAX_VALUE) {
                    error = false;
                  }
                }
                else if (j2.getNumero() == 4) {
                  if (checkError.getNoeuds().get(indice).getNom().split(" ")[0].equals("0") && checkError.getNoeuds().get(indice).getDistance() < Integer.MAX_VALUE) {
                    error = false;
                  }
                }
                indice++;
              }
            }
            if (error) {
              System.err.println("Impossible, " + j2.getNom() + " se retrouverait bloque");
              this.plateau = oldPlateau;
              this.joueurs = oldJoueurs;
              this.barrieres = oldBarrieres;
              listePion = new ArrayList<Pion>();
              for (Joueur j : this.joueurs) {
                listePion.add(j.getPion());
                if (!j.isHumain()) {
                  ((IA)(j)).forceMove();
                }
              }
              i--;
              if (i < 0) {
                i = this.joueurs.size() + i;
              }
            }
          }
          fin();
        }
        this.tour++;
      }
    }

    else {
      ArrayList<Pion> listePion = new ArrayList<Pion>();
      for (Joueur j : this.joueurs) {
        listePion.add(j.getPion());
      }
      boolean error = false;
      for (Joueur j2 : this.joueurs) {
        Graphe checkError = new Graphe(j2.getPion().getCoordonnee().getX1(),j2.getPion().getCoordonnee().getY1(),this.copieDamier());
        Noeud depart = null;
        for (Noeud n : checkError.getNoeuds()) {
          if (n.getNom().equals(String.valueOf(j2.getPion().getCoordonnee().getX1()) + " " + String.valueOf(j2.getPion().getCoordonnee().getY1()))) {
            depart = n;
          }
        }
        if (depart == null) {
          System.err.println("Erreur dans le déroulement de la partie");
        }
        else {
          checkError = Graphe.dijkstra(checkError,depart);
          int indice = 0;
          error = true;
          while (indice < checkError.getNoeuds().size() && error) {
            if (j2.getNumero() == 1) {
              if (checkError.getNoeuds().get(indice).getNom().split(" ")[0].equals("16") && checkError.getNoeuds().get(indice).getDistance() < Integer.MAX_VALUE) {
                error = false;
              }
            }
            else if (j2.getNumero() == 2) {
              if (checkError.getNoeuds().get(indice).getNom().split(" ")[0].equals("0") && checkError.getNoeuds().get(indice).getDistance() < Integer.MAX_VALUE) {
                error = false;
              }
            }
            else if (j2.getNumero() == 3) {
              if (checkError.getNoeuds().get(indice).getNom().split(" ")[1].equals("16") && checkError.getNoeuds().get(indice).getDistance() < Integer.MAX_VALUE) {
                error = false;
              }
            }
            else if (j2.getNumero() == 4) {
              if (checkError.getNoeuds().get(indice).getNom().split(" ")[0].equals("0") && checkError.getNoeuds().get(indice).getDistance() < Integer.MAX_VALUE) {
                error = false;
              }
            }
            indice++;
          }
        }
        if (error) {
          System.err.println("Impossible, " + j2.getNom() + " se retrouverait bloque");
          ret = false;
          listePion = new ArrayList<Pion>();
          for (Joueur j : this.joueurs) {
            listePion.add(j.getPion());
            if (!j.isHumain()) {
              ((IA)(j)).forceMove();
            }
          }
        }
      }
      fin();
      this.tour++;
    }
    return ret;
  }

/**
  * Termine la partie
  */
  public void fin() {
    try {
      ArrayList<Pion> listePion = new ArrayList<Pion>();
      for (Joueur j : this.joueurs) {
        listePion.add(j.getPion());
      }
      for (Joueur j : this.joueurs) {
        if (j.getNumero() == 1 && j.getPion().getCoordonnee().getX1() == 16) {
          System.out.println(this.joueurs.get(0).getNom() + " a gagne");
          System.out.println(this.plateau.toString(listePion,j.getPion()));
          System.exit(0);
        }
        else if (j.getNumero() == 2 && j.getPion().getCoordonnee().getX1() == 0) {
          System.out.println(this.joueurs.get(1).getNom() + " a gagne");
          System.out.println(this.plateau.toString(listePion,j.getPion()));
          System.exit(0);
        }
        else if (j.getNumero() == 3 && j.getPion().getCoordonnee().getY1() == 16) {
          System.out.println(this.joueurs.get(2).getNom() + " a gagne");
          System.out.println(this.plateau.toString(listePion,j.getPion()));
          System.exit(0);
        }
        else if (j.getNumero() == 4 && j.getPion().getCoordonnee().getY1() == 0) {
          System.out.println(this.joueurs.get(3).getNom() + " a gagne");
          System.out.println(this.plateau.toString(listePion,j.getPion()));
          System.exit(0);
        }
      }
    }
    catch (IndexOutOfBoundsException e) {

    }
  }

/**
  * Ajoute une barrière à la liste des barrières sur le plateau
  * @param barriere la barrière à ajouter à la liste
  */
  public void addBarriere (Barriere barriere) {
    try {
      if (barriere == null) {
        throw new Exception ("Erreur addBarriere(), parametre null");
      }
      else {
        this.barrieres.add(barriere);
      }
    }
    catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

/**
  * Retourne les barrières posées
  * @return la liste contenant ces barrières
  */
  public ArrayList<Barriere> getBarrieres() {
    return this.barrieres;
  }


/**
  * Retourne le numéro du tour actuel
  * @return le numéro du tour
  */
  public int getTour() {
    return this.tour;
  }

/**
  * Retourne le mode de jeu utilisé
  * @return le mode de jeu utilisé
  */
  public Mode getMode() {
    return this.mode;
  }

/**
  * Retourne le plateau de jeu
  * @return le plateau de jeu
  */
  public Plateau getPlateau() {
    return this.plateau;
  }

/**
  * Retourne la liste des joueurs présent dans la partie
  * @return la liste de joueurs de la partie
  */
  public ArrayList<Joueur> getJoueurs() {
    return this.joueurs;
  }

/**
  * Donne une copie du damier actuel sur laquelle travailler
  * @return une copie du damier
  */
  private int[][] copieDamier() {
    int[][] ret = new int[this.plateau.getDamier().length][this.plateau.getDamier().length];
    for (int i = 0 ; i < this.plateau.getDamier().length ; i++) {
      for (int j = 0 ; j < this.plateau.getDamier().length ; j++) {
        if (this.plateau.getDamier()[i][j]) {
          ret[i][j] = 1;
        }
        else if (i % 2 == 0 && j % 2 == 0) {
          ret[i][j] = 1;
        }
        else {
          ret[i][j] = 0;
        }
      }
    }
    return ret;
  }

/**
  * Vérifie qu'une case n'ai pas déjà été visitée auparavent
  * @param x la coordonnée X de la case
  * @param y la coordonnée Y de la case
  * @param damier la copie du damier sur laquelle on travaille
  * @return 1 si la case n'a pas déjà été visitée
  */
  private int checkVisite (int x , int y , int[][] damier) {
    int ret = 0;
    try {
      if (x < 0 || x >= damier.length || y < 0 || y >= damier.length) {
        throw new Exception ("Erreur, case en dehors du plateau");
      }
      else if (damier == null) {
        throw new NullPointerException("Erreur, damier null");
      }
      else {
        ret = damier[x][y];
      }
    }
    catch (NullPointerException e) {
      System.err.println(e.getMessage());
    }
    catch (Exception ex) {
      System.err.println(ex.getMessage());
    }
    finally {
      return ret;
    }
  }

/**
  * Donne les cases atteignables depuis la position actuelle
  * @param x la coordonnée X de la position actuelle
  * @param y la coordonnée Y de la position actuelle
  * @param damier le plateau sur lequel se déplacer
  * @return un tableau à deux dimensions contenant les différents déplacements possibles
  */
  private int[][] deplacementsSuivants (int x , int y , int[][] damier) {
    int[][] ret = null;
    try {
      if (x < 0 || x >= damier.length || y < 0 || y >= damier.length) {
        throw new Exception ("Erreur, indice en dehors du plateau");
      }
      else {
        boolean[][] damierBoolean = new boolean[damier.length][damier.length];


        for (int i = 0 ; i < damier.length ; i++) {
          for (int j = 0 ; j < damier.length ; j++) {
            if (damier[i][j] == 0) {
              damierBoolean[i][j] = false;
            }
            else {
              damierBoolean[i][j] = true;
            }
          }
        }

        Pion self = new Pion("X",new Coordonnee(x,y,-1,-1));
        ArrayList<int[]> listeCoupsPossibles = new ArrayList<int[]>();
        for (int[] coo : self.getDeplacementPossibles(damierBoolean)) {
          if (damier[coo[0]][coo[1]] == 1) {
            listeCoupsPossibles.add(coo);
          }
        }
        ret = new int[listeCoupsPossibles.size()][2];
        for (int i = 0 ; i < listeCoupsPossibles.size() ; i++) {
          ret[i] = listeCoupsPossibles.get(i);
        }
      }
    }
    catch (NullPointerException e) {
      System.err.println("Erreur, damier inexistant");
    }
    catch (Exception ex) {
      System.err.println(ex.getMessage());
    }
    finally {
      return ret;
    }
  }


/**
  * Sauvegarde l'état du plateau
  * @return le plateau sauvegardé
  */
  public Plateau savePlateau() {
    boolean[][] retDamier = new boolean[this.plateau.getDamier().length][this.plateau.getDamier().length];
    for (int i = 0 ; i < retDamier.length ; i++) {
      for (int j = 0 ; j < retDamier.length ; j++) {
        retDamier[i][j] = this.plateau.getDamier()[i][j];
      }
    }
    Plateau ret = new Plateau(retDamier);
    return ret;
  }

/**
  * Sauvegarde l'état des joueurs
  * @param plateau le plateau sur lequel se trouvent les joueurs
  * @return une ArrayList contenant les joueurs sauvegardés
  */
  public ArrayList<Joueur> saveJoueurs(Plateau plateau) {
    ArrayList<Joueur> ret = new ArrayList<Joueur>();
    Pion p;
    ArrayList<Barriere> listeBarriere;
    for (Joueur j : this.joueurs) {
      p = new Pion(j.getCouleur(),j.getPion().getCoordonnee());
      listeBarriere = new ArrayList<Barriere>();
      for (Barriere b  : j.getBarrieres()) {
        Barriere newB = new Barriere(j.getCouleur(),plateau);
        listeBarriere.add(newB);
      }
      if (j.isHumain()) {
        ret.add(new Humain(j.getNom(),j.getNumero(),j.getCouleur(),listeBarriere,p,plateau));
      }
      else {
        ret.add(new IA(j.getNom(),j.getNumero(),j.getCouleur(),listeBarriere,p,plateau,((IA)j).getDifficulte(), this.joueurs));
      }
    }
    return ret;
  }

/**
  * Sauvegarde l'état de l'attribut barrieres
  * @return une sauvegarde de cette attribut
  */
  public ArrayList<Barriere> saveBarrieres() {
    ArrayList<Barriere> ret = new ArrayList<Barriere>();
    for (Barriere b : this.barrieres) {
      ret.add(b);
    }
    return ret;
  }


}
