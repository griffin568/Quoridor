package controller;

import java.util.ArrayList;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.*;
import view.*;

/**
  * Listener permettant de chager la couleur de la barrière sur les écrans de sauvegarde et de chargement
  * ainsi que d'activer les listeners d'effacement de sauvegarde
  * @author Drmarsupial35 , griffin568
  * @version 1.0.0
  */
public class CorbeilleListener implements ActionListener {

  private boolean activer, pause, chargement;
  ArrayList<JButton> lesBoutons;

  /**
  * Créé un nouvel objet CorbeilleListener
  * @param lesBoutons la liste des boutons
  * @param pause true si on est sur l'écran de pause
  * @param chargement true si on est sur un écran de chargement
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
  * Active les listeners d'effacment de sauvegarde et change la couleur de la corbeille
  * @param ev l'ActionEvent à écouter
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
