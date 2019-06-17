package quoridor;
import java.util.ArrayList;
import java.io.*;

public class RWFile {

  /**
    * Lit et retourne toutes les informations contenues dans le fichier donné
    * @param fileName le nom du fichier que nous voulons lire
    * @return toutes les informations contenues dans le fichier sous la forme d'un tableau de String.
    */
  public static ArrayList<String> readFile(String fileName) {
    ArrayList<String> liste = new ArrayList<String>();
    try {
      DataInputStream dataIn = new DataInputStream(new FileInputStream("../data/" + fileName));
      while (dataIn.available() > 0) {
        liste.add(dataIn.readLine());
      }
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
        DataOutputStream dataOut = new DataOutputStream(new FileOutputStream ("../data/" + fileName));

        for (Joueur j : joueurs) {
          dataOut.writeUTF(j.getNom());
          if (j.isHumain()) {
            dataOut.writeUTF(" H ; ");
          }
          else {
            IA ia = (IA)j;
            dataOut.writeUTF(" IA " + ia.getDifficulte() + " ; ");
          }
        }
        dataOut.writeUTF("\n");

        for (Joueur j : joueurs) {
          Pion p = j.getPion();
          Coordonnee coord = p.getCoordonnee();
          dataOut.writeUTF(p.getCouleur() + " " + coord.getX1() + " " + coord.getY1() + " ; ");
        }
        dataOut.writeUTF("\n");

        for (Joueur j : joueurs) {
          ArrayList<Barriere> lesBarrieres = j.getBarrieres();
          dataOut.writeUTF(String.valueOf(lesBarrieres.size()));
        }

        for (Barriere b : barrieres) {
          Coordonnee coord = b.getCoordonnee();
          dataOut.writeUTF(b.getCouleur() + " " + coord.getX1() + " " + coord.getY1() + " " + coord.getX2() + " " + coord.getY2());
        }
        dataOut.writeUTF("\n");

        dataOut.writeUTF(tour + " ; " + String.valueOf(dernierJoueur.getNumero()));
        dataOut.close();
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
