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
      try {
        if (nom == null || couleur == null || barrieres == null || pion == null || plateau == null) {
          throw new Exception ("Erreur Humain(), parametre null");
        }
        else if (numero < 1 || numero > 4) {
          throw new Exception("Erreur Humain(), parametre null");
        }
        else {
          super(nom, numero, couleur, barrieres, pion, plateau);
          this.scanner = new Scanner(System.in);
        }
      }
      catch (Exception e) {
        System.err.println(e.getMessage());
      }

    }
}
