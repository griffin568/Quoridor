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

    }

  /**
    * Permet au joueur de jouer à partir du terminal
    */
    public void jeu() {
      try {
        int[][] deplacementsPossibles = this.pion.getDeplacementPossibles();
        String nPosition = this.scanner.nextLine();
        boolean ok = false;
        while (!nPosition.trim().equalsIgnoreCase("pass") && !ok) {
          if (nPosition.trim().equalsIgnoreCase("help")) {

          }
          else {
            for (int i = 0 ; i < deplacementsPossibles.length ; i++) {
              if (deplacementsPossibles[i][0] == Integer.parseInt(nPosition.split(" ")[0].trim()) && deplacementsPossibles[i][1] == Integer.parseInt(nPosition.split(" ")[1].trim())) {
                ok = true;
                this.deplacerPion(new Coordonnee(Integer.parseInt(nPosition.split(" ")[0].trim()),Integer.parseInt(nPosition.split(" ")[1].trim()),-1,-1));
              }
            }
          }
          nPosition = this.scanner.nextLine();
        }
      }
      catch (NumberFormatException e) {
        System.err.println("Erreur dans le format des coordonnees, tapez 'help' pour plus d'informations");
      }

    }
}
