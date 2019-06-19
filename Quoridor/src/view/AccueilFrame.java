package view;

import javax.swing.*;
import java.awt.*;
import view.controller.*;

/**
  * Cette classe gère la création de la page d'acccueil du jeu et de ses composants graphiques.
  * @author Drmarsupial35, griffin568
  * @version 0.1.0
  */
public class AccueilFrame extends JPanel {

  private MainFrame parent;
  private JButton PhotoButton, LancerBton, ChargerBton, QuitterBton;
  private JPanel chargement, choixNombre;
  private JLabel titre;

  /**
    * Créer une page d'acccueil vide. Ce constructeur est utilisé pour permettre son utilisation future dans d'autres classes.
    */
  public AccueilFrame() {}

  /**
    * Créer la page d'acccueil de l'application. Cette méthode appele la méthode privée initComponent()
    * @param parent La fenetre principale de jeu qui est utilisée pour changer l'écran affiché
    * @param chargement L'écran permettant de choisir la partie que l'on souhaite charger
    * @param choixNombre L'écran permettant de créer une nouvelle partie et de choisir le nombre de joueurs de cette dernière
    */
  public AccueilFrame(MainFrame parent, JPanel chargement, JPanel choixNombre) {
    try {
      if (parent == null) {
        throw new Exception("L'écran principal * parent doit exister pour créer l'Accueil.");
      }
      else if (chargement == null) {
        throw new Exception("L'écran de chargement de partie doit exister pour créer l'Accueil.");
      }
      else if (choixNombre == null) {
        throw new Exception("L'écran de choix de nombre de joueurs doit exister pour créer l'Accueil.");
      }
      else {
        this.parent = parent;
        this.chargement = chargement;
        this.choixNombre = choixNombre;
        this.setBackground(Color.BLACK);
        initComponent();
      }
    }
    catch(Exception e) {
      System.err.println(e.getMessage());
    }
  }

  /**
    *Initialise les composants graphiques de la page d'acccueil, tel que les boutons cliquables ou encore l'image du jeu.
    */
  private void initComponent() {
    this.setLayout(new BorderLayout());
    JPanel leftContainer = new JPanel(new BorderLayout());
    JPanel rightContainer = new JPanel(new BorderLayout());
    JPanel downContainer = new JPanel(new BorderLayout());

    leftContainer.setBackground(Color.BLACK);
    rightContainer.setBackground(Color.BLACK);
    downContainer.setBackground(Color.BLACK);

    this.PhotoButton = new JButton(new ImageIcon("../data/Accueil_Photo.png"));
    this.LancerBton = new JButton("Lancer une nouvelle partie");
    this.ChargerBton = new JButton("Charger une nouvelle partie");
    this.QuitterBton = new JButton("QUITTER");

    this.QuitterBton.addActionListener(new DownButtonListener(this.parent, this));
    this.LancerBton.addActionListener(new AccueilListener(this.parent, this.choixNombre));
    this.ChargerBton.addActionListener(new AccueilListener(this.parent, this.chargement));

    this.LancerBton.setPreferredSize(new Dimension(500, 100));
    this.ChargerBton.setPreferredSize(new Dimension(500, 100));
    this.QuitterBton.setPreferredSize(new Dimension(200, 50));

    this.PhotoButton.setOpaque(false);
    this.PhotoButton.setContentAreaFilled(false);
    this.PhotoButton.setBorderPainted(false);
    this.PhotoButton.setFocusable(false);

    leftContainer.add(this.PhotoButton,BorderLayout.CENTER);
    rightContainer.add(new JLabel(" "),BorderLayout.WEST);
    rightContainer.add(new JLabel(" "),BorderLayout.EAST);
    rightContainer.add(this.LancerBton,BorderLayout.NORTH);
    rightContainer.add(this.ChargerBton,BorderLayout.SOUTH);
    downContainer.add(this.QuitterBton,BorderLayout.EAST);

    add(leftContainer,BorderLayout.WEST);
    add(rightContainer,BorderLayout.EAST);
    add(downContainer,BorderLayout.SOUTH);
  }
}
