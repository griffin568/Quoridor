package controller;

import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.*;
import view.*;

/**
  * Listener gérant l'écrant de pause
  * @author Drmarsupial35 , griffin568
  * @version 1.0.0
  */
public class PauseListener implements ActionListener {

  private MainPauseFrame mainF;

  /**
  * Créé un nouvel objet PauseListener
  * @param mainF la fenêtre principale de l'application
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
  * Gère les différentes actions possibles après qu'un des boutons ai été selectionné
  * @param ev l'ActionEvent à écouter
  */
  public void actionPerformed(ActionEvent ev) {
    JButton source = (JButton)ev.getSource();
    if (source.getText().equalsIgnoreCase("REPRENDRE LA PARTIE")) {
      this.mainF.dispatchEvent(new WindowEvent(this.mainF, WindowEvent.WINDOW_CLOSING));
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
