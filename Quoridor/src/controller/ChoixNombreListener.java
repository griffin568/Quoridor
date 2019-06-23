package controller;

import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.*;
import view.*;

/**
  * Listener gérant le passage vers les écrans de choix du nombre de joueurs
  * @author Drmarsupial35 , griffin568
  * @version 1.0.0
  */
public class ChoixNombreListener implements ActionListener {

  private MainFrame mainF;
  private JPanel suivant;

  /**
  * Créé un nouvel objet ChoixNombreListener
  * @param mainF la fenêtre principale de l'application
  * @param suivant l'écran suivant
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
  * Change d'écran quand on appuie sur le bouton
  * @param ev l'ActionEvent à écouter 
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
