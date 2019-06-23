import java.awt.*;
import quoridor.*;
import view.*;
import controller.*;
import java.util.ArrayList;

/**
  * Cette classe permet de lancer l'application
  * @author AlexM02 , Drmarsupial35 , Eclixal , griffin568
  */
public class Launcher {

    public static void main(String[] args) {
      if (args.length > 0) {
        Difficulte d1 = null;
        Difficulte d2 = null;
        Difficulte d3 = null;
        Difficulte d4 = null;
        Partie p = null;
        if (args[0].equals("0")) {
          ArrayList<String> noms = new ArrayList<String>();
          ArrayList<String> lines = RWFile.readFile("sauvegardeTMP");
          if (lines.get(0).split(";").length == 2) {
            String j1 = lines.get(0).split(";")[0].split(" ")[1].trim();
            String j2 = lines.get(0).split(";")[1].split(" ")[1].trim();
            String j1Nom = lines.get(0).split(";")[0].split(" ")[0].trim();
            String j2Nom = lines.get(0).split(";")[1].split(" ")[0].trim();
            noms.add(j1Nom);
            noms.add(j2Nom);
            if (j1.equals("H") && j2.equals("H")) {
              p = new Partie(Mode.HH,false,noms);
            }
            else if (j1.equals("H") && j2.equals("IA")) {
              p = new Partie(Mode.HI,false,noms);
              d2 = setDiff(lines.get(0).split(";")[1].split(" ")[2].trim());
            }
            else if (j1.equals("IA") && j2.equals("IA")) {
              p = new Partie(Mode.II,false,noms);
              d1 = setDiff(lines.get(0).split(";")[0].split(" ")[2].trim());
              d2 = setDiff(lines.get(0).split(";")[1].split(" ")[2].trim());
            }
          }
          else if (lines.get(0).split(";").length == 4) {
            String j1 = lines.get(0).split(";")[0].split(" ")[1].trim();
            String j2 = lines.get(0).split(";")[1].split(" ")[1].trim();
            String j3 = lines.get(0).split(";")[2].split(" ")[1].trim();
            String j4 = lines.get(0).split(";")[3].split(" ")[1].trim();
            String j1Nom = lines.get(0).split(";")[0].split(" ")[0].trim();
            String j2Nom = lines.get(0).split(";")[1].split(" ")[0].trim();
            String j3Nom = lines.get(0).split(";")[2].split(" ")[0].trim();
            String j4Nom = lines.get(0).split(";")[3].split(" ")[0].trim();
            noms.add(j1Nom);
            noms.add(j2Nom);
            noms.add(j3Nom);
            noms.add(j4Nom);
            if (j1.equals("H") && j2.equals("H") && j3.equals("H") && j4.equals("H")) {
              p = new Partie(Mode.HHHH,false,noms);
            }
            else if (j1.equals("H") && j2.equals("H") && j3.equals("H") && j4.equals("IA")) {
              p = new Partie(Mode.HHHI,false,noms);
              d4 = setDiff(lines.get(0).split(";")[3].split(" ")[2].trim());
            }
            else if (j1.equals("H") && j2.equals("H") && j3.equals("IA") && j4.equals("IA")) {
              p = new Partie(Mode.HHII,false,noms);
              d3 = setDiff(lines.get(0).split(";")[2].split(" ")[2].trim());
              d4 = setDiff(lines.get(0).split(";")[3].split(" ")[2].trim());
            }
            else if (j1.equals("H") && j2.equals("IA") && j3.equals("IA") && j4.equals("IA")) {
              p = new Partie(Mode.HIII,false,noms);
              d2 = setDiff(lines.get(0).split(";")[1].split(" ")[2].trim());
              d3 = setDiff(lines.get(0).split(";")[2].split(" ")[2].trim());
              d4 = setDiff(lines.get(0).split(";")[3].split(" ")[2].trim());
            }
            else if (j1.equals("IA") && j2.equals("IA") && j3.equals("IA") && j4.equals("IA")) {
              p = new Partie(Mode.IIII,false,noms);
              d1 = setDiff(lines.get(0).split(";")[0].split(" ")[2].trim());
              d2 = setDiff(lines.get(0).split(";")[1].split(" ")[2].trim());
              d3 = setDiff(lines.get(0).split(";")[2].split(" ")[2].trim());
              d4 = setDiff(lines.get(0).split(";")[3].split(" ")[2].trim());
            }
          }
          if (d1 != null) {
            IA ia = (IA)p.getJoueurs().get(0);
            ia.setDifficulte(d1);
          }
          else if (d2 != null) {
            IA ia = (IA)p.getJoueurs().get(1);
            ia.setDifficulte(d2);
          }
          else if (d3 != null) {
            IA ia = (IA)p.getJoueurs().get(2);
            ia.setDifficulte(d3);
          }
          else if (d4 != null) {
            IA ia = (IA)p.getJoueurs().get(3);
            ia.setDifficulte(d4);
          }
          p.charger("sauvegardeTMP");
          p.start();
        }
        else {
          String fileName = args[0];
          System.out.println(fileName);

          ArrayList<String> noms = new ArrayList<String>();
          noms.add("moi");
          noms.add("billy");

          Partie partie = new Partie(Mode.HH,false,noms);
          partie.charger("sauvegarde1");
          partie.start();
        }
      }
      else {
        java.awt.EventQueue.invokeLater(new Runnable() {
          public void run() {
            MainFrame gui = new MainFrame();
            gui.setVisible(true);
          }
        });
      }
    }

    /**
      * Attribut une valeur à la difficulté d'une IA à partir de son label
      * @param j la chaine de caractère décrivant l'IA
      * @return la valeur de la difficulté
      */
      public static Difficulte setDiff (String j) {
        Difficulte ret = null;
        if (j.equalsIgnoreCase("facile")) {
          ret = Difficulte.FACILE;
        }
        else if (j.equalsIgnoreCase("moyenne")) {
          ret = Difficulte.MOYEN;
        }
        else if (j.equalsIgnoreCase("difficile")) {
          ret = Difficulte.DIFFICILE;
        }
        return ret;
      }
}
