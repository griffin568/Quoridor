package controller;

import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.*;
import view.*;

public class ChoixNombreListener implements ActionListener {

  private MainFrame mainF;
  private JPanel suivant;

  /**
  *
  * @param mainF
  * @param suivant
  */
  public ChoixNombreListener(MainFrame mainF, JPanel suivant) {
    try {
      if (mainF == null) {
        throw new Exception("L'écran principal doit exister pour être utiliser dans le listener.");
      }
      else if (suivant == null) {
        throw new Exception("L'écran suivant doit exister pour être utiliser dans le listener.");
      }
      else {
        this.mainF = mainF;
        this.suivant = suivant;
      }
    }
    catch(Exception e) {
      System.err.println(e.getMessage());
    }
  }

  /**
  *
  * @param mainF
  */
  public void actionPerformed(ActionEvent ev) {
    JButton source = (JButton)ev.getSource();
    if (source.getText().equalsIgnoreCase("JOUER A 2 JOUEURS")) {
      this.mainF.getSwitchableCL().show(this.mainF.getSwitchablePanel(),"Partie 2 Joueurs");
    }
    else if (source.getText().equalsIgnoreCase("JOUER A 4 JOUEURS")) {
      this.mainF.getSwitchableCL().show(this.mainF.getSwitchablePanel(),"Partie 4 Joueurs");
    }
  }

}
