package controller;

import javax.swing.event.*;
import java.awt.event.*;
import quoridor.*;
import javax.swing.*;
import view.*;
import quoridor.*;
import java.util.ArrayList;

public class JeuIAListener implements ActionListener {

  private Controleur controleur;
  private PartieFrame frame;

  /**
    * Créé un nouvel objet JeuIAListener
    * @param controleur le controleur de la partie
    * @param frame la fenêtre de jeu
    */
    public JeuIAListener (Controleur controleur , PartieFrame frame) {
      try {
        if (controleur == null || frame == null) {
          throw new Exception ("Erreur du constructeur JeuIAListener(), parametre null");
        }
        else {
          this.controleur = controleur;
          this.frame = frame;
        }
      }
      catch (Exception e) {
        System.err.println(e.getMessage());
      }
    }

  /**
    * Permet à un joueur IA de jouer
    * @param e l'ActionEvent à écouter
    */
    public void actionPerformed (ActionEvent e) {
      if (!this.controleur.getJoueurActif().isHumain()) {
        this.controleur.jeuAuto();
        this.frame.updateFrame();
      }
    }
}
