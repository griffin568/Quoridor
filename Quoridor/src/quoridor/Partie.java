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

        }
      }
      catch(FileNotFoundException e) {
        System.err.println("Partie construceur - Fichier non trouvé (" + filename + ")");
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

            for (int i = 0; i<= 10; i++) {
              BarriereJ1.add(new Barriere("O", this.plateau));
            }
            for (int i = 0; i <= 10; i++) {
              BarriereJ2.add(new Barriere("W", this.plateau));
            }

            Coordonnee c1 = new Coordonnee(Integer.parseInt(coord1[0]),Integer.parseInt(coord1[1]), -1, -1);
            Coordonnee c2 = new Coordonnee(Integer.parseInt(coord2[0]),Integer.parseInt(coord1[1]), -1, -1);

            Joueur J1, J2;
            if (mode.equals(Mode.HH)) {
              J1 = new Humain("Joueur1",1,"O",BarriereJ1,new Pion("O",c1), this.plateau);
              J2 = new Humain("Joueur2",1,"W",BarriereJ1,new Pion("W",c2), this.plateau);
            }
            else if (mode.equals(Mode.HI)) {
              J1 = new Humain("Joueur1",1,"O",BarriereJ1,new Pion("O",c1), this.plateau);
              J2 = new IA("IA1",1,"W",BarriereJ1,new Pion("W",c2), this.plateau,Difficulte.FACILE);
            }
            else {
              J1 = new IA("Joueur1",1,"O",BarriereJ1,new Pion("O",c1), this.plateau,Difficulte.FACILE);
              J2 = new IA("IA1",1,"W",BarriereJ1,new Pion("W",c2), this.plateau,Difficulte.FACILE);
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

            for (int i = 0; i<= 5; i++) {
              BarriereJ1.add(new Barriere("O", this.plateau));
            }
            for (int i = 0; i<= 5; i++) {
              BarriereJ2.add(new Barriere("W", this.plateau));
            }
            for (int i = 0; i<= 5; i++) {
              BarriereJ3.add(new Barriere("Z", this.plateau));
            }
            for (int i = 0; i<= 5; i++) {
              BarriereJ4.add(new Barriere("A", this.plateau));
            }

            Coordonnee c1 = new Coordonnee(Integer.parseInt(coord1[0]),Integer.parseInt(coord1[1]), -1, -1);
            Coordonnee c2 = new Coordonnee(Integer.parseInt(coord2[0]),Integer.parseInt(coord2[1]), -1, -1);
            Coordonnee c3 = new Coordonnee(Integer.parseInt(coord3[0]),Integer.parseInt(coord3[1]), -1, -1);
            Coordonnee c4 = new Coordonnee(Integer.parseInt(coord4[0]),Integer.parseInt(coord4[1]), -1, -1);

            Joueur J1 = new Humain("Joueur1",1,"O",BarriereJ1,new Pion("O",c1), this.plateau);
            Joueur J2 = new Humain("Joueur2",1,"W",BarriereJ1,new Pion("W",c1), this.plateau);
            Joueur J3 = new Humain("Joueur3",1,"Z",BarriereJ1,new Pion("Z",c1), this.plateau);
            Joueur J4 = new Humain("Joueur4",1,"A",BarriereJ1,new Pion("A",c1), this.plateau);

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
        System.out.println(this.plateau.toString(listePion));
        for (Joueur j : this.joueurs) {
          j.jeu();
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
}
