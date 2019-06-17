import quoridor.*;
import java.util.ArrayList;

/**
  * Cette classe permet de lancer l'application
  * @author AlexM02 , Drmarsupial35 , Eclixal , griffin568
  */
public class Launcher {

    public static void main(String[] args) {
      Partie test = new Partie(Mode.HH);
      ArrayList<Pion> lesPions = new ArrayList<Pion>();
      System.out.println(test.getPlateau().toString(lesPions));
    }
}
