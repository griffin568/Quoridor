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
        if (args[0].equals("0")) {
          Partie p = new Partie();
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
}
