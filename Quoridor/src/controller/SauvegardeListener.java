package controller;

import java.util.ArrayList;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.*;
import quoridor.*;
import view.*;

/**
  * Listener gérant l'éran de sauvegarde
  * @author Drmarsupial35 , griffin568
  * @version 1.0.0
  */
public class SauvegardeListener implements ActionListener {

  private JPanel parent, partie;
  private boolean activer, quitter;
  private String fileName;

  /**
  * Créé un nouvel objet SauvegardeListener
  * @param fileName le nom du fichier de sauvegarde à utiliser
  * @param parent l'écran parent
  * @param partie l'écran de partie
  * @param quitter true si l'on souhaite quitter après la sauvegarde
  */
  public SauvegardeListener(String fileName, JPanel parent, JPanel partie, boolean quitter) {
    try {
      if (fileName == null) {
        throw new Exception("Le nom du fichier doit être valide pour pouvoir être lu.");
      }
      else if (parent == null) {
        throw new Exception("L'écran parent doit exister pour créer le listener.");
      }
      else if (partie == null) {
        throw new Exception("L'écran de partie doit exister pour créer le listener.");
      }
      else {
        this.fileName = fileName;
        this.quitter = quitter;
        this.partie = partie;
        this.parent = parent;
        this.activer = false;
      }
    }
    catch(Exception e) {
      System.err.println(e.getMessage());
    }
  }

  /**
  * Gère les événements de sauvegarde après selection d'un bouton
  * @param ev l'ActionEvent à écouter
  */
  public void actionPerformed(ActionEvent ev) {
    JButton source = (JButton)ev.getSource();
    if (!this.activer) {
      if (this.quitter) {
        SauvegarderQuitterPauseFrame f = (SauvegarderQuitterPauseFrame) this.parent;
        PartieFrame pf = (PartieFrame) this.partie;
        ArrayList<Joueur> lesJoueurs = pf.getJoueurs();
        ArrayList<Barriere> lesBarrieres = pf.getBarrieres();
        int tour = pf.getPartie().getTour();
        Joueur jActif = pf.getControleur().getJoueurActif();

        if (this.fileName.equalsIgnoreCase("sauvegarde1")) {
          RWFile.writeFile(this.fileName, lesJoueurs, lesBarrieres, tour, jActif, true);
          f.majBouton(source, '1');
        }
        else if (this.fileName.equalsIgnoreCase("sauvegarde2")) {
          RWFile.writeFile(this.fileName, lesJoueurs, lesBarrieres, tour, jActif, true);
          f.majBouton(source, '2');
        }
        else if (this.fileName.equalsIgnoreCase("sauvegarde3")) {
          RWFile.writeFile(this.fileName, lesJoueurs, lesBarrieres, tour, jActif, true);
          f.majBouton(source, '3');
        }
        System.exit(0);
      }
      else {
        SauvegarderPauseFrame f = (SauvegarderPauseFrame) this.parent;
        PartieFrame pf = (PartieFrame) this.partie;
        ArrayList<Joueur> lesJoueurs = pf.getJoueurs();
        ArrayList<Barriere> lesBarrieres = pf.getBarrieres();
        int tour = pf.getPartie().getTour();
        Joueur jActif = pf.getControleur().getJoueurActif();

        if (this.fileName.equalsIgnoreCase("sauvegarde1")) {
          RWFile.writeFile(this.fileName, lesJoueurs, lesBarrieres, tour, jActif, true);
          f.majBouton(source, '1');
        }
        else if (this.fileName.equalsIgnoreCase("sauvegarde2")) {
          RWFile.writeFile(this.fileName, lesJoueurs, lesBarrieres, tour, jActif, true);
          f.majBouton(source, '2');
        }
        else if (this.fileName.equalsIgnoreCase("sauvegarde3")) {
          RWFile.writeFile(this.fileName, lesJoueurs, lesBarrieres, tour, jActif, true);
          f.majBouton(source, '3');
        }
      }
    }
    else {
      if (this.quitter) {
        SauvegarderQuitterPauseFrame f = (SauvegarderQuitterPauseFrame) this.parent;
        if (this.fileName.equalsIgnoreCase("sauvegarde1")) {
          RWFile.writeFile(fileName);
          f.majBouton(source, 'a');
        }
        else if (this.fileName.equalsIgnoreCase("sauvegarde2")) {
          RWFile.writeFile(fileName);
          f.majBouton(source, 'a');
        }
        else if (this.fileName.equalsIgnoreCase("sauvegarde3")) {
          RWFile.writeFile(fileName);
          f.majBouton(source, 'a');
        }
      }
      else {
        SauvegarderPauseFrame f = (SauvegarderPauseFrame) this.parent;
        if (this.fileName.equalsIgnoreCase("sauvegarde1")) {
          RWFile.writeFile(fileName);
          f.majBouton(source, 'a');
        }
        else if (this.fileName.equalsIgnoreCase("sauvegarde2")) {
          RWFile.writeFile(fileName);
          f.majBouton(source, 'a');
        }
        else if (this.fileName.equalsIgnoreCase("sauvegarde3")) {
          RWFile.writeFile(fileName);
          f.majBouton(source, 'a');
        }
      }
    }
  }


/**
  * Change l'état de l'attribut activer
  * @param activer le nouvel de l'attribut
  */
  public void changeActiver(boolean activer) {
    this.activer = activer;
  }
}
