package controller;

import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.*;
import view.*;

/**
  * Listener gérant l'apparition de l'écran de pause
  * @author Drmarsupial35 , griffin568
  * @version 0.9.0
  */
public class PartiePauseListener implements KeyListener {

  private MainFrame mainF;

  /**
    * Créé un nouvel objet PartiePauseListener
    * @param mainF la fenêtre principale de l'application
    */
  public PartiePauseListener(MainFrame mainF) {
    try {
      if (mainF == null) {
        throw new Exception("PartiePauseListener constructeur - L'écran principal doit exister pour être utilisé dans le listener.");
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
    * Permet d'afficher l'écran de pause après une pression sur la touche 'ECHAP'
    * @param e le KeyEvent à écouter
    */
  public void keyTyped(KeyEvent e) {
      if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
        this.mainF.activerPause(true);
      }
    }

  public void keyPressed(KeyEvent e) {}
  public void keyReleased(KeyEvent e) {}
}
