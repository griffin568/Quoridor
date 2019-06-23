package controller;

import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.*;
import view.*;

/**
  * Listener gérant les possiblilités de déplacement depuis la page d'accueil
  * @author Drmarsupial35 , griffin568
  * @version 1.0.0
  */
public class AccueilListener implements ActionListener {

  private MainFrame mainF;
  private JPanel suivant;

  /**
  * Créé un nouvel objet AccueilListener
  * @param mainF la fenêtre principale de l'application
  * @param suivant l'écran suivant
  */
  public AccueilListener(MainFrame mainF, JPanel suivant) {
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
  * Permet de changer d'écran après un clic sur le bouton
  * @param ev l'ActionEvent à écouter
  */
  public void actionPerformed(ActionEvent ev) {
    JButton source = (JButton)ev.getSource();
    if (source.getText().equalsIgnoreCase("Lancer une nouvelle partie")) {
      this.mainF.getSwitchableCL().show(this.mainF.getSwitchablePanel(),"choixNombre");
    }
    else if (source.getText().equalsIgnoreCase("Charger une nouvelle partie")) {
      this.mainF.getSwitchableCL().show(this.mainF.getSwitchablePanel(),"Chargement");
    }
  }

}
