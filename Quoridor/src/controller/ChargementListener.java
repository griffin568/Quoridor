package controller;

import javax.swing.event.*;
import java.awt.event.*;
import quoridor.*;
import javax.swing.*;
import view.*;

public class ChargementListener implements ActionListener {

  private MainFrame mainF;
  private boolean activer;
  private String fileName;

  /**
  *
  * @param mainF
  * @param suivant
  */
  public ChargementListener(MainFrame mainF, String fileName) {
    try {
      if (mainF == null) {
        throw new Exception("L'écran principal doit exister pour être utiliser dans le listener.");
      }
      else if (fileName == null) {
        throw new Exception("Le nom du fichier doit être valide pour pouvoir être lu.");
      }
      else {
        this.mainF = mainF;
        this.fileName = fileName;
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
    if (!activer) {
      Partie partie = new Partie(Mode.HH);
      if (source.getText().equalsIgnoreCase("Emplacement 1")) {
        partie = partie.charger("sauvegarde1");
      }
      else if (source.getText().equalsIgnoreCase("Emplacement 2")) {
        partie = partie.charger("sauvegarde2");
      }
      else if (source.getText().equalsIgnoreCase("Emplacement 3")) {
        partie = partie.charger("sauvegarde3");
      }

      this.mainF.setPartie(new PartieFrame(this.mainF, partie));
      this.mainF.getSwitchableCL().show(this.mainF.getSwitchablePanel(),"Partie");

    }
    else {
      if (source.getText().equalsIgnoreCase("Emplacement 1")) {
        RWFile.writeFile("sauvegarde1");
      }
      else if (source.getText().equalsIgnoreCase("Emplacement 2")) {
        RWFile.writeFile("sauvegarde2");
      }
      else if (source.getText().equalsIgnoreCase("Emplacement 3")) {
        RWFile.writeFile("sauvegarde3");
      }
    }
  }


/**
  *
  * @param activerPause
  */
  public void changeActiver(boolean activer) {
    this.activer = activer;
  }
}
