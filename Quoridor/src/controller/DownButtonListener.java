package controller;

import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.*;
import view.*;

public class DownButtonListener implements ActionListener {

  private MainFrame mainF;
  private String preced;

  /**
    * Listener qui écoute les boutons présents en bas des différentes pages de l'application
    * (Les boutons 'QUITTER' et 'RETOUR').
    * @param mainF La fenêtre principale sur laquelle le bouton est placé
    * @param preced La page que nous devrons afficher lors de l'appuie sur un bouton 'RETOUR'
    */
  public DownButtonListener(MainFrame mainF, String preced) {
    try {
      if (mainF == null) {
        throw new Exception("DownButtonListener constructeur - L'écran principal doit exister pour être utilisé dans le listener.");
      }
      else if (preced == null) {
        throw new Exception("DownButtonListener constructeur - Le nom de la fenêtre précédente doit exister pour être utilisé dans le listener.");
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
    * Ecoute les actions sur des boutons
    * @param ev l'action réalisé (Clique sur le bouton)
    */
  public void actionPerformed(ActionEvent ev) {
    JButton source = (JButton)ev.getSource();
    if (source.getText().equalsIgnoreCase("QUITTER")) {
      System.exit(0);
    }
    else if (source.getText().equalsIgnoreCase("RETOUR")) {
      if (this.preced.equalsIgnoreCase("Accueil")) {
        this.mainF.getSwitchableCL().show(this.mainF.getSwitchablePanel(),"Accueil");
      }
      else if (this.preced.equalsIgnoreCase("choixNombre")) {
        this.mainF.getSwitchableCL().show(this.mainF.getSwitchablePanel(),"choixNombre");
      }
      else if (this.preced.equalsIgnoreCase("Pause")) {
        this.mainF.getSwitchableCL().show(this.mainF.getSwitchablePanel(),"Pause");
      }
    }
  }
}
