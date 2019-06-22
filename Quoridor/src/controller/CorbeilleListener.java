package controller;

import java.util.ArrayList;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.*;
import view.*;

public class CorbeilleListener implements ActionListener {

  private boolean activer, pause, chargement;
  ArrayList<JButton> lesBoutons;

  /**
  *
  * @param mainF
  * @param suivant
  */
  public CorbeilleListener(ArrayList<JButton> lesBoutons, boolean pause, boolean chargement) {
    try {
      if (lesBoutons == null) {
        throw new Exception("Les Boutons doivent exister pour pouvoir créer le Listener.");
      }
      else {
        this.lesBoutons = lesBoutons;
        this.chargement = chargement;
        this.pause = pause;
        this.activer = false;
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
    ImageIcon corbeille,resizedCorbeille;
    if (!activer) {
      this.activer = true;
      corbeille = new ImageIcon("../data/img/CorbeilleRouge.png");
      }
    else {
      this.activer = false;
      corbeille = new ImageIcon("../data/img/CorbeilleBlanc.png");
    }
    if (!this.pause) {
      resizedCorbeille = new ImageIcon(corbeille.getImage().getScaledInstance(98, 142, java.awt.Image.SCALE_SMOOTH));
    }
    else {
      resizedCorbeille = new ImageIcon(corbeille.getImage().getScaledInstance(59, 71, java.awt.Image.SCALE_SMOOTH));
    }
    source.setIcon(resizedCorbeille);
    for (JButton j : this.lesBoutons) {
      for (ActionListener action : j.getActionListeners()) {
        if (chargement) {
          ChargementListener c = (ChargementListener) action;
          c.changeActiver(activer);
        }
        else {
          SauvegardeListener c = (SauvegardeListener) action;
          c.changeActiver(activer);
        }
      }
    }
  }
}
