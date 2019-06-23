package view;

import javax.swing.*;
import java.awt.*;
import controller.*;

/**
  * L'écran affichant les boutons permettant de choisir le nombre de joueurs dans la partie
  * @author Drmarsupial35 , griffin568
  * @version 1.0.0
  */
public class ChoixNombreFrame extends JPanel {

  private MainFrame mainF;
  private JPanel partie2, partie4;
  private JButton retour, partie2Bton, partie4Bton;
  private JLabel titre;

  /**
    * Créé un nouvel objet ChoixNombreFrame
    * @param parent l'écran parent
    * @param partie2 l'écran permettant de configurer une partie à 2 joueurs
    * @param partie4 l'écran permettant de configurer une partie à 4 joueurs
    */
  public ChoixNombreFrame(MainFrame parent, JPanel partie2, JPanel partie4) {
    try {
      if (parent == null) {
        throw new Exception("L'écran principal doit exister pour créer l'écran de choix du nombre de joueurs.");
      }
      else if (partie2 == null) {
        throw new Exception("L'écran de sélection de joueurs (Partie à 2 joueurs) doit exister pour créer l'écran de choix du nombre de joueurs.");
      }
      else if (partie4 == null) {
        throw new Exception("L'écran de sélection de joueurs (Partie à 4 joueurs) doit exister pour créer l'écran de choix du nombre de joueurs.");
      }
      else {
        this.mainF = parent;
        this.partie2 = partie2;
        this.partie4 = partie4;
        this.setBackground(Color.BLACK);
        initComponent();
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
    this.setLayout(new BorderLayout());
    JPanel upContainer = new JPanel(new BorderLayout());
    JPanel centerContainer = new JPanel(new GridLayout(7,3));
    JPanel downContainer = new JPanel(new BorderLayout());
    JPanel newPanel;

    upContainer.setBackground(Color.BLACK);
    centerContainer.setBackground(Color.BLACK);
    downContainer.setBackground(Color.BLACK);

    this.titre = new JLabel("CREER UNE PARTIE");
    this.partie2Bton = new JButton("JOUER A 2 JOUEURS");
    this.partie4Bton = new JButton("JOUER A 4 JOUEURS");
    this.retour = new JButton("RETOUR");

    this.retour.addActionListener(new DownButtonListener(this.mainF, "Accueil"));
    this.partie2Bton.addActionListener(new ChoixNombreListener(this.mainF, this.partie2));
    this.partie4Bton.addActionListener(new ChoixNombreListener(this.mainF, this.partie4));

    this.partie2Bton.setPreferredSize(new Dimension(500, 100));
    this.partie4Bton.setPreferredSize(new Dimension(500, 100));
    this.retour.setPreferredSize(new Dimension(200, 50));
    this.titre.setForeground(Color.WHITE);

    JButton titreContainer = new JButton(this.titre.getText());
    titreContainer.setOpaque(false);
    titreContainer.setContentAreaFilled(false);
    titreContainer.setBorderPainted(false);
    titreContainer.setFocusable(false);
    titreContainer.setForeground(Color.WHITE);

    upContainer.add(titreContainer,BorderLayout.CENTER);

    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    centerContainer.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    centerContainer.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    centerContainer.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    centerContainer.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    centerContainer.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    centerContainer.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    centerContainer.add(newPanel);
    centerContainer.add(this.partie2Bton);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    centerContainer.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    centerContainer.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    centerContainer.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    centerContainer.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    centerContainer.add(newPanel);
    centerContainer.add(this.partie4Bton);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    centerContainer.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    centerContainer.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    centerContainer.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    centerContainer.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    centerContainer.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    centerContainer.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    centerContainer.add(newPanel);

    downContainer.add(this.retour, BorderLayout.EAST);

    this.add(upContainer, BorderLayout.NORTH);
    this.add(centerContainer, BorderLayout.CENTER);
    this.add(downContainer, BorderLayout.SOUTH);
  }
}
