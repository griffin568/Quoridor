import java.awt.*;
import quoridor.*;
import view.*;
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
        }
      });
      /*Partie test = new Partie(Mode.HH, "Texte");
      test.charger("sauvegarde1");
      test.start();*/
    }
}
