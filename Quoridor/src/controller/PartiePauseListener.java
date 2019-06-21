package controller;

import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.*;
import view.*;

public class PartiePauseListener implements KeyListener {

  private MainFrame mainF;

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

  public void keyTyped(KeyEvent e) {
      if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
        this.mainF.activerPause(true);
      }
    }

  public void keyPressed(KeyEvent e) {}
  public void keyReleased(KeyEvent e) {}
}
