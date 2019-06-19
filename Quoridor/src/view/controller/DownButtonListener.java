package view.controller;

import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.*;
import view.*;

public class DownButtonListener implements ActionListener {

  private MainFrame mainF;
  private JPanel preced;

  /**
    *
    * @param mainF
    * @param preced
    */
  public DownButtonListener(MainFrame mainF, JPanel preced) {
    try {
      if (mainF == null) {
        throw new Exception("L'écran principal doit exister pour être utiliser dans le listener.");
      }
      else if (preced == null) {
        throw new Exception("La fenêtre précédente doit exister pour être utiliser dans le listener.");
      }
      else {
        this.mainF = mainF;
        this.preced = preced;
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
    if (source.getText().equalsIgnoreCase("QUITTER")) {
      System.exit(0);
    }
    else if (source.getText().equalsIgnoreCase("RETOUR")) {
      
    }
  }
}
