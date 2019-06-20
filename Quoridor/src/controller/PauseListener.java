package controller;

import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.*;
import view.*;

public class PauseListener implements ActionListener {

  private MainPauseFrame mainF;

  /**
  *
  * @param mainF
  * @param suivant
  */
  public PauseListener(MainPauseFrame mainF) {
    try {
      if (mainF == null) {
        throw new Exception("L'écran principal doit exister pour être utiliser dans le listener.");
      }
      else {
        this.mainF = mainF;
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
    if (source.getText().equalsIgnoreCase("REPRENDRE LA PARTIE")) {
      this.mainF.getSwitchableCL().show(this.mainF.getSwitchablePanel(),"Pause");
    }
    else if (source.getText().equalsIgnoreCase("SAUVEGARDER LA PARTIE")) {
      this.mainF.getSwitchableCL().show(this.mainF.getSwitchablePanel(),"Sauvegarder");
    }
    else if (source.getText().equalsIgnoreCase("CHARGER UNE PARTIE")) {
        this.mainF.getSwitchableCL().show(this.mainF.getSwitchablePanel(),"Charger");
    }
    else if (source.getText().equalsIgnoreCase("SAUVEGARDER ET QUITTER")) {
        this.mainF.getSwitchableCL().show(this.mainF.getSwitchablePanel(),"SauvegarderQuitter");
    }
    else if (source.getText().equalsIgnoreCase("MENU PRINCIPAL")) {
        this.mainF.getMainFrame().activerPause(false);
    }
  }

}
