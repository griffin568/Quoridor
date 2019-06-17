package quoridor;

import java.util.ArrayList;
import java.util.Scanner;

/**
  * Cette classe gère les joueurs humains
  * @author AlexM02 , Drmarsupial35 , Eclixal , griffin568
  * @version 0.1.0
  */
public class Humain extends Joueur {

    private Scanner scanner;

    /**
      * Créé un nouvel objet Humain
      * @param nom le nom du joueur
      * @param numero le numéro du joueur defini selon l'ordre de création (ex joueur 1 , joueur 2 ...)
      * @param couleur la couleur du joueur (indique la forme du pion en mode texte)
      * @param barrieres liste contenant les barrières restantes du joueur
      * @param pion le pion utilisé par le joueur
      * @param plateau le plateau de jeu
      */
    public Humain(String nom, int numero, String couleur, ArrayList<Barriere> barrieres, Pion pion, Plateau plateau) {
      super(nom, numero, couleur, barrieres, pion, plateau);
      this.scanner = new Scanner(System.in);
      this.humain = true;

    }

  /**
    * Permet au joueur de jouer à partir du terminal
    */
    public void jeu() {
      try {
        int[][] deplacementsPossibles = this.pion.getDeplacementPossibles();
        String nPosition = this.scanner.nextLine();
        boolean ok = false;
        ArrayList<String> letters = new ArrayList<String>();
        letters.add("A");
        letters.add("B");
        letters.add("C");
        letters.add("D");
        letters.add("E");
        letters.add("F");
        letters.add("G");
        letters.add("H");
        letters.add("I");
        while (!nPosition.trim().equalsIgnoreCase("pass") && !ok) {
          if (nPosition.trim().equalsIgnoreCase("help")) {

          }
          else if (nPosition.split(" ")[0].trim().equalsIgnoreCase("move")) {
            for (int[] deplacement : this.pion.getDeplacementPossibles()) {
              if (deplacement[0] == Integer.parseInt(nPosition.split(" ")[1].split(",")[0].split("(")[1].trim())) {
                if (deplacement[1] == letters.indexOf(nPosition.split(" ")[1].split(",")[1].split(")")[0].trim())) {
                  deplacerPion(new Coordonnee(Integer.parseInt(nPosition.split(" ")[1].split(",")[0].split("(")[1].trim())*2,letters.indexOf(nPosition.split(" ")[1].split(",")[1].split(")")[0].trim())*2,-1,-1),this.plateau.getDamier());
                }
              }
            }
          }
          else if (nPosition.split(" ")[0].trim().equalsIgnoreCase("wall")) {

          }
          else {
            nPosition = this.scanner.nextLine();
          }
        }
      }
      catch (NumberFormatException e) {
        System.err.println("Erreur dans le format des coordonnees, tapez 'help' pour plus d'informations");
      }

    }
}
