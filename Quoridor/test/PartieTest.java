package test;

import org.junit.Test;
import quoridor.Mode;
import quoridor.Partie;
import java.util.ArrayList;
import static org.junit.Assert.*;


public class PartieTest {

    @Test()
    public void test() {
        ArrayList<String> noms = new ArrayList<String>();
        noms.add("Erwann");
        noms.add("Titouan");
        Partie partie = new Partie(Mode.HH,false,noms);
        assertEquals(Mode.HH, partie.getMode());

    }

}
