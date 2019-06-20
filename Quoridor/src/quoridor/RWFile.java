package quoridor;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class RWFile {

  /**
    * Lit et retourne toutes les informations contenues dans le fichier donné
    * @param fileName le nom du fichier que nous voulons lire
    * @return toutes les informations contenues dans le fichier sous la forme d'un tableau de String.
    */
  public static ArrayList<String> readFile(String fileName) {
    ArrayList<String> liste = new ArrayList<String>();
    try {
      fileName = "../data/" + fileName;
      FileReader reader = new FileReader(fileName);
      Scanner scan = new Scanner(reader);
      while (scan.hasNextLine()) {
        liste.add(scan.nextLine());
      }
      scan.close();
    }
    catch (FileNotFoundException e) {
      System.out.println("RWFile readFile() - Fichier non trouvé : " + fileName);
    }
    finally {
      return liste;
    }
  }

  /**
    * Ecrit toutes les informations utiles concernant la partie en cours dans le fichier donné.
    * @param fileName le nom du fichier dans lequel nous voulons écrire
    * @param joueurs la liste des joueurs dans la partie
    * @param barrieres la liste des barrières déjà jouées sur le plateau
    * @param tour le numéro du dernier tour joué
    * @param dernierJoueur le joueur ayant sauvegardé (Donc celui qui jouera lors de la reprise de la partie)
    */
  public static void writeFile(String fileName, ArrayList<Joueur> joueurs, ArrayList<Barriere> barrieres, int tour, Joueur dernierJoueur) {
    try {
      if (fileName == null) {
        throw new Exception("RWFile writeFile() - Le nom du fichier doit exister");
      }
      else if (joueurs == null) {
        throw new Exception("RWFile writeFile() - La liste des joueurs doit exister");
      }
      else if (tour < 0) {
        throw new Exception("RWFile writeFile() - Le numéro d'un tour doit être positif");
      }
      else if (dernierJoueur == null) {
        throw new Exception("RWFile writeFile() - Le dernier joueur ayant joué doit exister");
      }
      else {
        fileName = "../data/" + fileName /*+ ".txt"*/;
        PrintWriter out = new PrintWriter(fileName);
        for (Joueur j : joueurs) {
          out.print(j.getNom());
          if (j.isHumain()) {
            if (joueurs.indexOf(j) != joueurs.size() - 1) {
              out.print(" H ;");
            }
            else {
              out.print(" H  ");
            }

          }
          else {
            IA ia = (IA)j;
            if (joueurs.indexOf(j) != joueurs.size() - 1) {
              out.print(" IA " + ia.getDifficulte() + ";");
            }
            else {
              out.print(" IA " + ia.getDifficulte());
            }
          }
        }
        out.println();

        for (Joueur j : joueurs) {
          Pion p = j.getPion();
          Coordonnee coord = p.getCoordonnee();
          out.print(p.getCouleur() + " " + coord.getX1() + " " + coord.getY1() + ";");
        }
        out.println();

        for (Joueur j : joueurs) {
          ArrayList<Barriere> lesBarrieres = j.getBarrieres();
          out.print(String.valueOf(lesBarrieres.size()) + ";");
        }

        for (Barriere b : barrieres) {
          Coordonnee coord = b.getCoordonnee();
          if (barrieres.indexOf(b) != barrieres.size() - 1) {
            out.print(b.getCouleur() + " " + coord.getX1() + " " + coord.getY1() + " " + coord.getX2() + " " + coord.getY2() + ";");
          }
          else {
            out.print(b.getCouleur() + " " + coord.getX1() + " " + coord.getY1() + " " + coord.getX2() + " " + coord.getY2());
          }
        }
        out.println();

        out.print(tour + ";" + String.valueOf(dernierJoueur.getNumero()));
        out.close();
      }
    }
    catch(FileNotFoundException e) {
      System.err.println("WriteFile - Fichier non trouvé : " + fileName);
    }
    catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

}
