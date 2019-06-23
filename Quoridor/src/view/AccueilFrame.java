package view;

import controller.*;
import javax.swing.*;
import java.awt.*;

/**
  * Cette classe gère la création de la page d'acccueil du jeu et de ses composants graphiques.
  * @author Drmarsupial35, griffin568
  * @version 1.0.0
  */
public class AccueilFrame extends JPanel {

  private MainFrame parent;
  private JButton PhotoButton, LancerBton, ChargerBton, QuitterBton;
  private JPanel chargement, choixNombre;
  private JLabel titre;

  /**
    * Créé une page d'acccueil vide. Ce constructeur est utilisé pour permettre son utilisation future dans d'autres classes.
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
        throw new Exception("L'écran principal doit exister pour créer l'Accueil.");
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
    JPanel rightContainer = new JPanel(new GridLayout(7,2));
    JPanel downContainer = new JPanel(new BorderLayout());
    JPanel upContainer = new JPanel(new GridLayout(1,5));
    JPanel centerContainer = new JPanel(new GridLayout(1,2));
    JPanel rightSecondContainer = new JPanel(new BorderLayout());
    JPanel newPanel;

    upContainer.setBackground(Color.BLACK);
    rightContainer.setBackground(Color.BLACK);
    downContainer.setBackground(Color.BLACK);
    leftContainer.setBackground(Color.BLACK);
    centerContainer.setBackground(Color.BLACK);

    this.titre = new JLabel("QUORIDOR");
    this.LancerBton = new JButton("Lancer une nouvelle partie");
    this.ChargerBton = new JButton("Charger une nouvelle partie");
    this.QuitterBton = new JButton("QUITTER");
    this.PhotoButton = new JButton(new ImageIcon("../data/img/Accueil_Photo.png"));

    this.QuitterBton.addActionListener(new DownButtonListener(this.parent, ""));
    this.LancerBton.addActionListener(new AccueilListener(this.parent, this.choixNombre));
    this.ChargerBton.addActionListener(new AccueilListener(this.parent, this.chargement));

    this.LancerBton.setPreferredSize(new Dimension(500, 50));
    this.ChargerBton.setPreferredSize(new Dimension(500, 50));
    this.QuitterBton.setPreferredSize(new Dimension(200, 50));
    this.titre.setForeground(Color.WHITE);

    this.PhotoButton.setOpaque(false);
    this.PhotoButton.setContentAreaFilled(false);
    this.PhotoButton.setBorderPainted(false);
    this.PhotoButton.setFocusable(false);

    JButton titreContainer = new JButton(this.titre.getText());
    titreContainer.setOpaque(false);
    titreContainer.setContentAreaFilled(false);
    titreContainer.setBorderPainted(false);
    titreContainer.setFocusable(false);
    titreContainer.setForeground(Color.WHITE);

    upContainer.add(titreContainer,BorderLayout.CENTER);

    leftContainer.add(this.PhotoButton,BorderLayout.CENTER);

    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    rightContainer.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    rightContainer.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    rightContainer.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    rightContainer.add(newPanel);
    rightContainer.add(this.LancerBton);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    rightContainer.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    rightContainer.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    rightContainer.add(newPanel);
    rightContainer.add(this.ChargerBton);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    rightContainer.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    rightContainer.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    rightContainer.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    rightContainer.add(newPanel);
    rightSecondContainer.add(rightContainer,BorderLayout.CENTER);
    centerContainer.add(leftContainer);
    centerContainer.add(rightContainer);
    downContainer.add(this.QuitterBton,BorderLayout.EAST);

    add(upContainer,BorderLayout.NORTH);
    add(centerContainer,BorderLayout.CENTER);
    add(downContainer,BorderLayout.SOUTH);
  }
}
