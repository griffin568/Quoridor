package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {


  private JPanel switchablePanel;
  private CardLayout switchableCL;

  private AccueilFrame acccueil;
  private ChargementFrame chargement;
  private ChoixNombreFrame choixNombre;
  private PartieFrame partie;
  private Partie2JoueursFrame partie2Joueurs;
  private Partie4JoueursFrame partie4Joueurs;
  private MainPauseFrame pause;

  /**
    *
    */
  public MainFrame() {
    this.setIconImage(new ImageIcon("../data/img/Logo.jpg").getImage());
    this.setTitle("Quoridor");
    this.getContentPane().setLayout(new BorderLayout());
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.initComponents();
    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    this.setUndecorated(true);
    this.setResizable(false);
    this.setVisible(true);
  }

  /**
    *
    */
  private void initComponents() {
    this.acccueil = new AccueilFrame();
    this.partie = new PartieFrame(this, this.acccueil);
    this.chargement = new ChargementFrame(this, this.partie);
    this.partie2Joueurs = new Partie2JoueursFrame(this, this.partie);
    this.partie4Joueurs = new Partie4JoueursFrame(this, this.partie);
    this.choixNombre = new ChoixNombreFrame(this, this.partie2Joueurs, this.partie4Joueurs);
    this.acccueil = new AccueilFrame(this, this.chargement, this.choixNombre);

    this.switchablePanel = new JPanel(new CardLayout());

    this.switchablePanel.add(this.acccueil, "Accueil");
    this.switchablePanel.add(this.chargement, "Chargement");
    this.switchablePanel.add(this.choixNombre, "choixNombre");
    this.switchablePanel.add(this.partie, "Partie");
    this.switchablePanel.add(this.partie2Joueurs, "Partie 2 Joueurs");
    this.switchablePanel.add(this.partie4Joueurs, "Partie 4 Joueurs");

    this.switchableCL = (CardLayout)(this.switchablePanel.getLayout());
    this.switchableCL.show(this.switchablePanel, "Accueuil");

    this.add(this.switchablePanel, BorderLayout.CENTER);
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


  /**
    * Active ou désactive le menu pause. Cette méthode sera appelée par le listener de la classe PartieFrame
    * @param b vrai si on active le menu pause, faux si on souhaite le désactiver.
    */
  public void activerPause(boolean b) {
    if (b) {
      this.pause = new MainPauseFrame(this, this.acccueil);
    }
    else {
      this.pause.dispatchEvent(new WindowEvent(this.pause, WindowEvent.WINDOW_CLOSING));
    }
  }
}
