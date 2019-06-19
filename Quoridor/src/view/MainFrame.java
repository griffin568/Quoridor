package view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {


  private JPanel switchablePanel;
  private CardLayout switchableCL;

  private AccueilFrame acccueil;
  private ChargementFrame chargement;
  private ChoixNombreFrame choixNombre;
  private PartieFrame partie;
  private Partie2JoueursFrame partie2Joueurs;
  private Partie4JoueursFrame partie4Joueurs;
  private PauseFrame pause;

  /**
    *
    */
  public MainFrame() {
    this.setIconImage(new ImageIcon("../data/Logo.jpg").getImage());
    this.getContentPane().setLayout(new BorderLayout());
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.initComponents();
    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    this.setSize(1300, 650);
    this.setUndecorated(true);
    this.setVisible(true);
    this.setResizable(false);
  }

  /**
    *
    */
  private void initComponents() {

    this.acccueil = new AccueilFrame(this);
    this.chargement = new ChargementFrame(this,this.acccueil);
    this.choixNombre = new ChoixNombreFrame(this,this.acccueil);
    this.pause = new Pause(this, this.acccueil);
    this.partie = new PartieFrame(this);
    this.partie2Joueurs = new Partie2JoueursFrame(this,this.choixNombre);
    this.partie4Joueurs = new Partie4JoueursFrame(this,this.choixNombre);

    this.switchablePanel = new JPanel(new CardLayout());

    this.switchablePanel.add(this.acccueil, "Accueuil");
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


}
