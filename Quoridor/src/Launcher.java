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
      java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
          MainFrame gui = new MainFrame();
          gui.setVisible(true);
        }
      });
      /**
      Partie test = new Partie(Mode.II);
      ((IA)(test.getJoueurs().get(0))).setDifficulte(Difficulte.DIFFICILE);
      ((IA)(test.getJoueurs().get(1))).setDifficulte(Difficulte.DIFFICILE);
      //((IA)(test.getJoueurs().get(2))).setDifficulte(Difficulte.MOYEN);
      //((IA)(test.getJoueurs().get(3))).setDifficulte(Difficulte.MOYEN);
      //test.charger("sauvegarde2");
      test.start();
      */

    }
}
