package quoridor;

import java.util.ArrayList;
import java.io.FileNotFoundException;

/**
  * Cette classe gère les différents aspect de la partie
  * @author AlexM02 , Drmarsupial35 , Eclixal , griffin568
  * @version 0.1.0
  */
public class Partie {

    private int tour;
    private Mode mode;
    private Plateau plateau;
    private ArrayList<Joueur> joueurs;
    private ArrayList<Barriere> barrieres;
    private int profondeur;

    /**
      * Créé un nouvel objet Partie
      * @param fileName le nom du fichier de configuration
      * @param visuel le façon d'ont la partie sera jouée (Texte ou Visuel)
      */
    public Partie(Mode mode, String visuel) {
      try {
        if (mode == null) {
          throw new Exception("Partie constructeur - Le mode de jeu doit être valide pour pouvoir être utilisé.");
        }
        else if (visuel == null) {
          throw new Exception("Partie constructeur - Le nom du visuel doit être valide pour pouvoir être utilisé.");
        }
        else {
          initialisation(mode, visuel);
        }
      }
      catch(Exception e) {
        System.err.println(e.getMessage());
      }

    }


    /**
      * Sauvegarde la partie
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
          RWFile.writeFile(fileName, this.joueurs, this.barrieres, this.tour, leJoueur);
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
      */
    public void charger(String filename) {
      try {
        if (filename == null) {
          throw new Exception("Partie constructeur - Le nom du fichier doit être valide pour pouvoir être utilisé.");
        }
        else {
          this.joueurs = new ArrayList<Joueur>();
          ArrayList<String> lignes = RWFile.readFile(filename);
          String[] lesJoueurs = lignes.get(0).split(";"); // La liste des joueurs sous forme de String
          String[] lesPions = lignes.get(1).split(";"); // La liste des joueurs sous forme de String
          String[] lesBarrieres = lignes.get(2).split(";"); // La liste des barrieres sous forme de String
          String[] leReste = lignes.get(3).split(";"); // Le reste des informations (n° de tour & tour du premier joueur) sous forme de String
          ArrayList<String[]> desJoueurs = new ArrayList<String[]>(); // Liste de toutes les informations de chaque joueur
          int i = 0;

          for (String j: lesJoueurs) {
            desJoueurs.add(j.split(" "));
            i++;
          }
          this.tour = Integer.parseInt(leReste[0]);

          if (i == 2) {
            //Création des pions
            String[] pion1 = lesPions[0].split(" ");
            String[] pion2 = lesPions[1].split(" ");

            String couleur1 = pion1[0];
            String couleur2 = pion2[0];

            Coordonnee coord1 = new Coordonnee(Integer.parseInt(pion1[1]), Integer.parseInt(pion1[2]), -1, -1);
            Coordonnee coord2 = new Coordonnee(Integer.parseInt(pion2[1]), Integer.parseInt(pion2[2]), -1, -1);

            Coordonnee coord1B = new Coordonnee(0, 8, -1 ,-1);
            Coordonnee coord2B = new Coordonnee(16, 8, -1 ,-1);

            Pion p1 = new Pion(couleur1, coord1B);
            Pion p2 = new Pion(couleur2, coord2B);

            ArrayList<Barriere> barriere1 = new ArrayList<Barriere>();
            ArrayList<Barriere> barriere2 = new ArrayList<Barriere>();

            for (int j = 0; j < Integer.parseInt(lesBarrieres[0]); j++) {
              barriere1.add(new Barriere(couleur1, this.plateau));
            }
            for (int j = 0; j < Integer.parseInt(lesBarrieres[1]); j++) {
              barriere2.add(new Barriere(couleur2, this.plateau));
            }

            String[] joueur1 = desJoueurs.get(0);
            String[] joueur2 = desJoueurs.get(1);

            String nom1 = joueur1[0];
            String nom2 = joueur2[0];

            Joueur J1, J2;
            if (joueur1[1].equals("H") && joueur2[1].equals("H")) {
              this.mode = Mode.HH;
              J1 = new Humain(nom1, 1, couleur1, barriere1, p1, this.plateau);
              J2 = new Humain(nom2, 2, couleur2, barriere2, p2, this.plateau);
            }
            else if (joueur1[1].equals("H") && joueur2[1].equals("IA")) {
              this.mode = Mode.HI;
              J1 = new Humain(nom1, 1, couleur1, barriere1, p1, this.plateau);
              J2 = new IA(nom2, 2, couleur2, barriere2, p2, this.plateau, Difficulte.FACILE);
            }
            else {
              this.mode = Mode.II;
              J1 = new IA(nom1, 1, couleur1, barriere1, p1, this.plateau, Difficulte.FACILE);
              J2 = new IA(nom2, 2, couleur2, barriere2, p2, this.plateau, Difficulte.FACILE);
            }

            if (leReste[1].equals("1")) {
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

            Pion p1 = new Pion(couleur1, coord1);
            Pion p2 = new Pion(couleur2, coord2);
            Pion p3 = new Pion(couleur3, coord3);
            Pion p4 = new Pion(couleur4, coord4);

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
            if (joueur1[1].equals("H") && joueur2[1].equals("H") && joueur3[1].equals("H") && joueur4[1].equals("H")) {
              this.mode = Mode.HHHH;
              J1 = new Humain(nom1, 1, couleur1, barriere1, p1, this.plateau);
              J2 = new Humain(nom2, 2, couleur2, barriere2, p2, this.plateau);
              J3 = new Humain(nom3, 3, couleur3, barriere3, p3, this.plateau);
              J4 = new Humain(nom4, 4, couleur4, barriere4, p4, this.plateau);
            }
            else if (joueur1[1].equals("H") && joueur2[1].equals("H") && joueur3[1].equals("H") && joueur4[1].equals("IA")) {
              J1 = new Humain(nom1, 1, couleur1, barriere1, p1, this.plateau);
              J2 = new Humain(nom2, 2, couleur2, barriere2, p2, this.plateau);
              J3 = new Humain(nom3, 3, couleur3, barriere3, p3, this.plateau);
              J4 = new IA(nom4, 4, couleur4, barriere4, p4, this.plateau, Difficulte.FACILE);
            }
            else if (joueur1[1].equals("H") && joueur2[1].equals("H") && joueur3[1].equals("IA") && joueur4[1].equals("IA")) {
              J1 = new Humain(nom1, 1, couleur1, barriere1, p1, this.plateau);
              J2 = new Humain(nom2, 2, couleur2, barriere2, p2, this.plateau);
              J3 = new IA(nom3, 3, couleur3, barriere3, p3, this.plateau, Difficulte.FACILE);
              J4 = new IA(nom4, 4, couleur4, barriere4, p4, this.plateau, Difficulte.FACILE);
            }
            else if (joueur1[1].equals("H") && joueur2[1].equals("IA") && joueur3[1].equals("IA") && joueur4[1].equals("IA")) {
              J1 = new Humain(nom1, 1, couleur1, barriere1, p1, this.plateau);
              J2 = new IA(nom2, 2, couleur2, barriere2, p2, this.plateau, Difficulte.FACILE);
              J3 = new IA(nom3, 3, couleur3, barriere4, p3, this.plateau, Difficulte.FACILE);
              J4 = new IA(nom4, 4, couleur4, barriere4, p4, this.plateau, Difficulte.FACILE);
            }
            else {
              J1 = new IA(nom1, 1, couleur1, barriere1, p1, this.plateau, Difficulte.FACILE);
              J2 = new IA(nom2, 2, couleur2, barriere2, p2, this.plateau, Difficulte.FACILE);
              J3 = new IA(nom3, 3, couleur3, barriere4, p3, this.plateau, Difficulte.FACILE);
              J4 = new IA(nom4, 4, couleur4, barriere4, p4, this.plateau, Difficulte.FACILE);
            }

            if (leReste[1].equals("1")) {
              this.joueurs.add(J1);
              this.joueurs.add(J2);
              this.joueurs.add(J3);
              this.joueurs.add(J4);
            }
            else if (leReste[1].equals("2")) {
              this.joueurs.add(J2);
              this.joueurs.add(J3);
              this.joueurs.add(J4);
              this.joueurs.add(J1);
            }
            else if (leReste[1].equals("3")) {
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
              if (lesInfos[3].equals("FACILE")) {
                ((IA)this.joueurs.get(i)).setDifficulte(Difficulte.FACILE);
              }
              else if (lesInfos[3].equals("MOYEN")) {
                ((IA)this.joueurs.get(i)).setDifficulte(Difficulte.MOYEN);
              }
              else if (lesInfos[3].equals("DIFFICILE")) {
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
        System.err.println("Partie construceur - Fichier non trouvé (" + filename + ")");
      }
      catch (IndexOutOfBoundsException e) {
        System.err.println("IndexOutOfBoundsException");
      }
      catch(Exception e) {
        System.err.println(e.getMessage());
      }
    }

    /**
      * Initialise les différents éléments constants de la partie
      * @param mode le mode jeu de la partie à créer
      * @param visuel le façon d'ont la partie sera jouée (Texte ou Visuel)
      */
    private void initialisation(Mode mode, String visuel) {
          this.barrieres = new ArrayList<Barriere>();
          this.joueurs = new ArrayList<Joueur>();
          this.tour = 0;
          this.mode = mode;
          configuration(mode, visuel);
    }

    /**
      * Configure les éléments non constants de la partie à l'aide du fichier de configuration
      * @param mode le mode de jeu de la partie à créer
      * @param visuel le façon d'ont la partie sera jouée (Texte ou Visuel)
      */
    private void configuration(Mode mode, String visuel) {

      // Créer les joueurs
        if (visuel.equals("Texte")) {
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
              J1 = new Humain("Joueur1",1,"O",BarriereJ1,new Pion("O",c1), this.plateau);
              J2 = new Humain("Joueur2",1,"W",BarriereJ2,new Pion("W",c2), this.plateau);
            }
            else if (mode.equals(Mode.HI)) {
              J1 = new Humain("Joueur1",1,"O",BarriereJ1,new Pion("O",c1), this.plateau);
              J2 = new IA("IA1",1,"W",BarriereJ2,new Pion("W",c2), this.plateau,Difficulte.FACILE);
            }
            else {
              J1 = new IA("Joueur1",1,"O",BarriereJ1,new Pion("O",c1), this.plateau,Difficulte.FACILE);
              J2 = new IA("IA1",1,"W",BarriereJ2,new Pion("W",c2), this.plateau,Difficulte.FACILE);
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

            Joueur J1 = new Humain("Joueur1",1,"O",BarriereJ1,new Pion("O",c1), this.plateau);
            Joueur J2 = new Humain("Joueur2",1,"W",BarriereJ2,new Pion("W",c1), this.plateau);
            Joueur J3 = new Humain("Joueur3",1,"Z",BarriereJ3,new Pion("Z",c1), this.plateau);
            Joueur J4 = new Humain("Joueur4",1,"A",BarriereJ4,new Pion("A",c1), this.plateau);

            this.joueurs.add(J1);
            this.joueurs.add(J2);
            this.joueurs.add(J3);
            this.joueurs.add(J4);

          }
        }

        else if (visuel.equals("Visuel")) {
          if (mode.equals(Mode.HH) || mode.equals(Mode.HI)) {
            ArrayList<Barriere> BarriereJ1 = new ArrayList<Barriere>();
            ArrayList<Barriere> BarriereJ2 = new ArrayList<Barriere>();
            ArrayList<String> lignes = RWFile.readFile("config2");
            this.plateau = new Plateau(Integer.parseInt(lignes.get(0)));
            for (int i = 0; i<= 10; i++) {
              BarriereJ1.add(new Barriere("ROUGE", this.plateau));
            }
            for (int i = 0; i <= 10; i++) {
              BarriereJ2.add(new Barriere("VERT", this.plateau));
            }
          }
          else {
            ArrayList<Barriere> BarriereJ1 = new ArrayList<Barriere>();
            ArrayList<Barriere> BarriereJ2 = new ArrayList<Barriere>();
            ArrayList<Barriere> BarriereJ3 = new ArrayList<Barriere>();
            ArrayList<Barriere> BarriereJ4 = new ArrayList<Barriere>();
            ArrayList<String> lignes = RWFile.readFile("config4");
            this.plateau = new Plateau(Integer.parseInt(lignes.get(0)));

            for (int i = 0; i<= 5; i++) {
              BarriereJ1.add(new Barriere("ROUGE", this.plateau));
            }
            for (int i = 0; i<= 5; i++) {
              BarriereJ2.add(new Barriere("VERT", this.plateau));
            }
            for (int i = 0; i<= 5; i++) {
              BarriereJ3.add(new Barriere("JAUNE", this.plateau));
            }
            for (int i = 0; i<= 5; i++) {
              BarriereJ4.add(new Barriere("BLEU", this.plateau));
            }
          }
        }
      }

    /**
      * Lance la partie
      */
    public void start() {
      ArrayList<Pion> listePion = new ArrayList<Pion>();
      for (Joueur j : this.joueurs) {
        listePion.add(j.getPion());
      }
      while (true) {
        for (Joueur j : this.joueurs) {
          System.out.println(this.plateau.toString(listePion,j.getPion()));
          Barriere b = j.jeu();
          if (b != null) {
            addBarriere(b);
          }
          for (Joueur j2 : this.joueurs) {
            this.profondeur = 0;
            int[][] copieDamier = this.copieDamier();
            copieDamier[j2.getPion().getCoordonnee().getX1()][j2.getPion().getCoordonnee().getY1()] = 2;
            if (!checkChemins(j2.getPion().getCoordonnee().getX1(),j2.getPion().getCoordonnee().getY1(),j2.getNumero(),copieDamier)) {
              System.out.println("ERREUR PAS POSSIBLE");
              System.exit(0);
            }
            else if (this.profondeur >= 81) {
              System.out.println("ERREUR PAS POSSIBLE");
              System.exit(0);
            }
          }

          fin();
        }
      }
    }

    /**
      * Termine la partie
      */
    public void fin() {
      try {
        if (this.joueurs.get(0).getPion().getCoordonnee().getX1() == 16) {
          System.out.println(this.joueurs.get(0).getNom() + " a gagne");
          System.exit(0);
        }
        else if (this.joueurs.get(1).getPion().getCoordonnee().getX1() == 0) {
          System.out.println(this.joueurs.get(1).getNom() + " a gagne");
          System.exit(0);
        }
        else if (this.joueurs.get(2).getPion().getCoordonnee().getY1() == 16) {
          System.out.println(this.joueurs.get(2).getNom() + " a gagne");
          System.exit(0);
        }
        else if (this.joueurs.get(3).getPion().getCoordonnee().getY1() == 0) {
          System.out.println(this.joueurs.get(3).getNom() + " a gagne");
          System.exit(0);
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
    * Teste récursivement s'il existe un chemin possible vers une ligne d'arrivée
    * @param x la coordonnée X
    * @param y la coordonnée y
    * @param num le numéro du joueur
    * @param damier le damier à partir duquel on effectue le test
    * @return true si  le chemin est accessibles
    */
    private boolean checkChemins (int x , int y , int num , int[][] damier) {
      boolean ret = false;
      try {
        int[][] newPosition = deplacementsSuivants(x,y,damier);
        int i = 0;

        while (i < newPosition.length && !ret) {
          if (checkVisite(newPosition[i][0],newPosition[i][1],damier) == 1) {
            this.profondeur++;
            damier[newPosition[i][0]][newPosition[i][1]] = 2;
            if (num == 1) {
              if (newPosition[i][0] == 16) {
                ret = true;
              }
            }
            else if (num == 2) {
              if (newPosition[i][0] == 0) {
                ret = true;
              }
            }
            else if (num == 3) {
              if (newPosition[i][1] == 16) {
                ret = true;
              }
            }
            else if (num == 4) {
              if (newPosition[i][1] == 0) {
                ret = true;
              }
            }
            if (this.profondeur == 81) {
              ret = true;
            }
            else {
              if (checkChemins(newPosition[i][0],newPosition[i][1],num,damier)) {
                ret = true;
              }
              else {
                this.profondeur--;
                damier[newPosition[i][0]][newPosition[i][1]] = 1;
              }
            }
          }
          i++;
        }
      }
      catch (NullPointerException e) {
        System.err.println("Erreur checkChemins(), parametre null");
      }
      catch (IndexOutOfBoundsException ex) {
        System.err.println("Erreur checkChemins(), indice en dehors du plateau");
      }
      catch (Exception exc) {
        System.err.println("Erreur checkChemins(), cause inconnue");
      }
      finally {
        for (int k = 0 ; k < damier.length ; k++) {
          for (int l = 0 ; l < damier.length ; l++) {
            System.out.print(damier[k][l] + " ");
          }
          System.out.println();
        }
        System.out.println(ret);
        return ret;
      }

    }
}
