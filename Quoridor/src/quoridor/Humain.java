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
    public Barriere jeu() {
      Barriere ret = null;
      try {
        int[][] deplacementsPossibles = this.pion.getDeplacementPossibles(this.plateau.getDamier());
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
            ArrayList<String> help = RWFile.readFile("README");
            for (String line : help) {
              System.out.println(line);
            }
          }
          else if (nPosition.split(" ")[0].equalsIgnoreCase("save")) {
            String nb = nPosition.split(" ")[1].trim();
            if (nb.equalsIgnoreCase("1") || nb.equalsIgnoreCase("2") || nb.equalsIgnoreCase("3")) {
              ret = new Barriere(nPosition,this.plateau);
            }
          }
          else if (nPosition.trim().equalsIgnoreCase("showme")) {
            System.out.println("Pion : " + this.pion.getCouleur().charAt(0));
            System.out.println("Barrieres restantes : " + this.barrieres.size());
          }
          else if (nPosition.split(" ")[0].trim().equalsIgnoreCase("move")) {
            for (int[] deplacement : deplacementsPossibles) {
              int x1 = (letters.indexOf(nPosition.split(" ")[1].split(",")[1].trim().split("")[0])) * 2;
              int y1 = (Integer.parseInt(nPosition.split(" ")[1].split(",")[0].trim().split("")[1]) - 1) * 2;
              if (deplacement[0] == x1) {
                if (deplacement[1] == y1) {
                  deplacerPion(new Coordonnee(x1,y1,-1,-1),this.plateau.getDamier());
                  ok = true;
                }
              }
            }
            if (!ok) {
              System.out.println("Impossible de déplacer le pion à cet endroit");
            }
          }
          else if (nPosition.split(" ")[0].trim().equalsIgnoreCase("wall")) {
            if (letters.contains(nPosition.split(" ")[1].split(",")[0].trim().split("")[1])) {
              int x1 = (letters.indexOf(nPosition.split(" ")[1].split(",")[1].trim().split("")[0])*2)-1;
              int y1 = (Integer.parseInt(nPosition.split(" ")[2].split(",")[0].trim().split("")[1])-1)*2;
              int x2 = (letters.indexOf(nPosition.split(" ")[1].split(",")[1].trim().split("")[0])*2)-1;
              int y2 = (Integer.parseInt(nPosition.split(" ")[2].split(",")[1].trim().split("")[0])-1)*2;
              Barriere tmp = placerBarriere(new Coordonnee(x1,y1,x2,y2));
              if (tmp != null) {
                ok = true;
                ret = tmp;
              }

            }
            else {
              int x1 = (int)((Integer.parseInt(nPosition.split(" ")[1].split(",")[1].trim().split("")[0])-1)*2 + (Integer.parseInt(nPosition.split(" ")[1].split(",")[0].trim().split("")[1])-1)*2)/2;
              int y1 = (letters.indexOf(nPosition.split(" ")[2].split(",")[0].trim().split("")[1]) * 2);
              int x2 = (int)((Integer.parseInt(nPosition.split(" ")[1].split(",")[1].trim().split("")[0])-1)*2 + (Integer.parseInt(nPosition.split(" ")[1].split(",")[0].trim().split("")[1])-1)*2)/2;
              int y2 = (letters.indexOf(nPosition.split(" ")[2].split(",")[1].trim().split("")[0]) * 2);
              System.out.println(x1);
              Barriere tmp = placerBarriere(new Coordonnee(y1,x1,y2,x2));
               if (tmp != null) {
                 ok = true;
                 ret = tmp;
               }
            }

          }
          else {
            nPosition = this.scanner.nextLine();
          }
          if (!ok) {
            nPosition = this.scanner.nextLine();
          }
        }
      }
      catch (NumberFormatException e) {
        System.err.println("Erreur dans le format des coordonnees, tapez 'help' pour plus d'informations");
      }
      catch (IndexOutOfBoundsException ex) {
        System.err.println("Erreur, indice en dehors du tableau");
      }
      finally {
        return ret;
      }

    }
}
