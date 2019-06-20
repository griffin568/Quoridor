package view;

import javax.swing.*;
import java.awt.*;
import controller.*;

public class MainPauseFrame extends JFrame {

  private JPanel switchablePanel;
  private CardLayout switchableCL;

  private MainFrame parent;
  private AccueilFrame acccueil;

  private PauseFrame pause;
  private ChargementPauseFrame chargementPause;
  private SauvegarderPauseFrame sauvegardePause;
  private SauvegarderQuitterPauseFrame sauvegardeQuitterPause;

  public MainPauseFrame(MainFrame parent, AccueilFrame acccueil) {
    try {
      if (parent == null) {
        throw new Exception("La Frame parent doit exister pour créer l'écran de Pause.");
      }
      else if (acccueil == null) {
        throw new Exception("La page d'acccueil doit exister pour pouvoir être référencée par l'écran de pause.");
      }
      else {
        this.parent = parent;
        this.acccueil = acccueil;

        initComponent();

        this.setIconImage(new ImageIcon("../data/Logo.jpg").getImage());
        this.setTitle("Quoridor - Pause");
        this.setBackground(Color.BLACK);

        this.setSize(1300, 650);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
      }
    }
    catch(Exception e) {
      System.err.println(e.getMessage());
    }
  }



  private void initComponent() {
    this.pause = new PauseFrame(this, this.acccueil);
    this.chargementPause = new ChargementPauseFrame(this,this.parent.getPartie());
    this.sauvegardePause = new SauvegarderPauseFrame(this);
    this.sauvegardeQuitterPause = new SauvegarderQuitterPauseFrame(this);

    this.switchablePanel = new JPanel(new CardLayout());
    this.switchablePanel.add(this.pause, "Pause");
    this.switchablePanel.add(this.chargementPause, "Charger");
    this.switchablePanel.add(this.sauvegardePause, "Sauvegarder");
    this.switchablePanel.add(this.sauvegardeQuitterPause, "SauvegarderQuitter");

    this.switchableCL = (CardLayout)(this.switchablePanel.getLayout());
    this.switchableCL.show(this.switchablePanel, "Pause");

    this.add(this.switchablePanel, BorderLayout.CENTER);
  }

  public MainFrame getMainFrame() {
    return this.parent;
  }

  /**
    * Retourne le panel échangeable
    * @return le panel échangeable
  */
  public JPanel getSwitchablePanel() {
    return this.switchablePanel;
  }

  /**
    * Rend le panel qui est actuellement affiché à l'écran
    * @return le panel actuel
  */
  public CardLayout getSwitchableCL() {
    return this.switchableCL;
  }
}
