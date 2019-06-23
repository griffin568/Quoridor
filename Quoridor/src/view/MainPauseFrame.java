package view;

import javax.swing.*;
import java.awt.*;
import controller.*;

/**
  * La fenêtre principale de l'écran de pause
  * @author Drmarsupial35 , griffin568
  * @version 1.0.0
  */
public class MainPauseFrame extends JFrame {

  private JPanel switchablePanel;
  private CardLayout switchableCL;

  private MainFrame parent;
  private AccueilFrame accueil;

  private PauseFrame pause;
  private ChargementPauseFrame chargementPause;
  private SauvegarderPauseFrame sauvegardePause;
  private SauvegarderQuitterPauseFrame sauvegardeQuitterPause;

  /**
    * Créé un nouvel objet MainPauseFrame
    * @param parent la fenêtre principale de l'application
    * @param accueil l'écran d'accueil
    */
  public MainPauseFrame(MainFrame parent, AccueilFrame accueil) {
    try {
      if (parent == null) {
        throw new Exception("La Frame parent doit exister pour créer l'écran de Pause.");
      }
      else if (accueil == null) {
        throw new Exception("La page d'accueil doit exister pour pouvoir être référencée par l'écran de pause.");
      }
      else {
        this.parent = parent;
        this.accueil = accueil;

        initComponent();

        this.setIconImage(new ImageIcon("../data/Logo.jpg").getImage());
        this.setTitle("Quoridor - Pause");
        this.setBackground(Color.BLACK);

        this.setSize(900, 650);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
      }
    }
    catch(Exception e) {
      System.err.println(e.getMessage());
    }
  }


  /**
    * Initialise les différents composants
    */
  private void initComponent() {
    this.pause = new PauseFrame(this, this.accueil);
    this.chargementPause = new ChargementPauseFrame(this,this.parent.getPartie(), this.parent);
    this.sauvegardePause = new SauvegarderPauseFrame(this, this.parent.getPartie(), this.parent);
    this.sauvegardeQuitterPause = new SauvegarderQuitterPauseFrame(this, this.parent.getPartie(), this.parent);

    this.switchablePanel = new JPanel(new CardLayout());
    this.switchablePanel.add(this.pause, "Pause");
    this.switchablePanel.add(this.chargementPause, "Charger");
    this.switchablePanel.add(this.sauvegardePause, "Sauvegarder");
    this.switchablePanel.add(this.sauvegardeQuitterPause, "SauvegarderQuitter");

    this.switchableCL = (CardLayout)(this.switchablePanel.getLayout());
    this.switchableCL.show(this.switchablePanel, "Pause");

    this.add(this.switchablePanel, BorderLayout.CENTER);
  }

  /**
    * Retourne la fenêtre principale
    * @return la fenêtre principale
    */
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
