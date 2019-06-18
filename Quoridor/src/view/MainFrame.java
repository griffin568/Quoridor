package view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {


  private JPanel switchablePanel;
  private CardLayout switchableCL;

  private AccueuilFrame accueuil;
  private ChargementFrame chargement;
  private ChoixNombreFrame choixNombre;
  private PartieFrame partie;
  private Partie2JoueursFrame partie2Joueurs;
  private Partie4JoueursFrame partie4Joueurs;

  /**
    *
    */
  public MainFrame() {
    super("Quoridor");

    this.setIconImage(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/data/Logo.png"))).getImage());
    this.getContentPane().setLayout(new BorderLayout());
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.initComponents();

    this.pack();
    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    this.setUndecorated(true);
    this.setVisible(true);
    this.setResizable(false);

  }

  /**
    *
    */
  public void initComponents() {

    this.accueuil = new AccueuilFrame();
    this.chargement = new ChargementFrame();
    this.choixNombre = new ChoixNombreFrame();
    this.partie = new PartieFrame();
    this.partie2Joueurs = new Partie2JoueursFrame();
    this.partie4Joueurs = new Partie4JoueursFrame();

    this.switchablePanel = new JPanel(new CardLayout());

    this.switchablePanel.add(this.accueuil, "Accueuil");
    this.switchablePanel.add(this.chargement, "Chargement");
    this.switchablePanel.add(this.choixNombre, "choixNombre");
    this.switchablePanel.add(this.partie, "Partie");
    this.switchablePanel.add(this.partie2Joueurs, "Partie 2 Joueurs");
    this.switchablePanel.add(this.partie4Joueurs, "Partie 4 Joueurs");

    this.switchableCL = (CardLayout)(this.switchablePanel.getLayout());
  this.switchableCL.show(this.switchablePanel, "Accueuil");
  }


  /**
    * Returns the switchable panel.
    * @return the switchable panel
  */
  public JPanel getSwitchablePanel() {
    return this.switchablePanel;
  }

  /**
    * Returns the switchable panel's CardLayout, needed to actually change the panels.
    * @return the switchable panel's CardLayout
  */
  public CardLayout getSwitchableCL() {
    return this.switchableCL;
  }


}
