package controller;

import javax.swing.event.*;
import java.awt.event.*;
import quoridor.*;
import javax.swing.*;
import view.*;


/**
  * Cette classe permet de gérer les actions de chargement de la partie du GUI
  * @author Drmarsupial35 , griffin568
  * @version 1.0.0
  */
public class ChargementListener implements ActionListener {

  private MainFrame mainF;
  private JPanel parent;
  private boolean activer, pause;
  private String fileName;

  /**
  * Créé un nouvel objet ChargementListener
  * @param mainF la fenêtre principale de l'application
  * @param fileName le nom du fichier à charger
  * @param parent l'écran parent
  * @param pause booléen permettant de vérifier si l'on est sur l'écran de pause ou non
  */
  public ChargementListener(MainFrame mainF, String fileName, JPanel parent, boolean pause) {
    try {
      if (mainF == null) {
        throw new Exception("L'écran principal doit exister pour être utiliser dans le listener.");
      }
      else if (fileName == null) {
        throw new Exception("Le nom du fichier doit être valide pour pouvoir être lu.");
      }
      else if (parent == null) {
        throw new Exception("L'écran parent doit exister pour créer le listener.");
      }
      else {
        this.mainF = mainF;
        this.fileName = fileName;
        this.pause = pause;
        this.parent = parent;
        this.activer = false;
      }
    }
    catch(Exception e) {
      System.err.println(e.getMessage());
    }
  }

  /**
  * Charge la partie indiquée sur le bouton
  * @param ev l'ActionEvent à écouter
  */
  public void actionPerformed(ActionEvent ev) {
    JButton source = (JButton)ev.getSource();
    boolean ok = false;
    if (!activer) {
      Partie partie = new Partie();
      if (source.getText().equalsIgnoreCase("Emplacement 1")) {
        partie = partie.charger("sauvegarde1");
        ok = true;
      }
      else if (source.getText().equalsIgnoreCase("Emplacement 2")) {
        partie = partie.charger("sauvegarde2");
        ok = true;
      }
      else if (source.getText().equalsIgnoreCase("Emplacement 3")) {
        partie = partie.charger("sauvegarde3");
        ok = true;
      }

      if (ok) {
        this.mainF.setPartie(new PartieFrame(this.mainF, partie));
        this.mainF.getSwitchableCL().show(this.mainF.getSwitchablePanel(),"Partie");
      }

      if (pause) {
        ChargementPauseFrame cp = (ChargementPauseFrame) this.parent;
        cp.getMainF().dispatchEvent(new WindowEvent(this.mainF, WindowEvent.WINDOW_CLOSING));
      }

    }
    else {
      if (pause) {
        ChargementPauseFrame f = (ChargementPauseFrame) this.parent;
        if (source.getText().equalsIgnoreCase("Emplacement 1")) {
          RWFile.writeFile("sauvegarde1");
          f.majBouton(source, '1');
        }
        else if (source.getText().equalsIgnoreCase("Emplacement 2")) {
          RWFile.writeFile("sauvegarde2");
          f.majBouton(source, '2');
        }
        else if (source.getText().equalsIgnoreCase("Emplacement 3")) {
          RWFile.writeFile("sauvegarde3");
          f.majBouton(source, '3');
        }
      }
      else {
        ChargementFrame f = (ChargementFrame) this.parent;
        if (source.getText().equalsIgnoreCase("Emplacement 1")) {
          RWFile.writeFile("sauvegarde1");
          f.majBouton(source, '1');
        }
        else if (source.getText().equalsIgnoreCase("Emplacement 2")) {
          RWFile.writeFile("sauvegarde2");
          f.majBouton(source, '2');
        }
        else if (source.getText().equalsIgnoreCase("Emplacement 3")) {
          RWFile.writeFile("sauvegarde3");
          f.majBouton(source, '3');
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
