package view;

import javax.swing.*;
import java.awt.*;
import controller.*;

/**
  * L'écran de pause
  * @author Drmarsupial35 , griffin568
  * @version 1.0.0
  */
public class PauseFrame extends JPanel {

  private MainPauseFrame mainF;
  private JPanel accueil;

  private JButton reprendre,sauvegarder,charger,sauvegarderQuitter,menu;
  private JLabel titre;

  /**
    * Créé un nouvel objet PauseFrame
    * @param parent la fenêtre de pause
    * @param accueil l'écran d'accueil
    */
  public PauseFrame(MainPauseFrame parent, JPanel accueil) {
    try {
      if (parent == null) {
        throw new Exception("PauseFrame constructeur - La fenêtre de pause doit exister pour créer l'écran de pause.");
      }
      else if (accueil == null) {
        throw new Exception("PauseFrame constructeur - L'écran d'accueil doit exister pour créer l'écran de pause.");
      }
      else {
        this.mainF = parent;
        this.accueil = accueil;
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
    JPanel centerContainer = new JPanel(new GridLayout(9,1));
    JPanel newPanel;
    JPanel buttonContainer;
    JLabel newLabel;

    upContainer.setBackground(Color.BLACK);
    centerContainer.setBackground(Color.BLACK);

    this.titre = new JLabel("PAUSE");
    this.reprendre = new JButton("REPRENDRE LA PARTIE");
    this.sauvegarder = new JButton("SAUVEGARDER LA PARTIE");
    this.charger = new JButton("CHARGER UNE PARTIE");
    this.sauvegarderQuitter = new JButton("SAUVEGARDER ET QUITTER");
    this.menu = new JButton("MENU PRINCIPAL");

    this.reprendre.addActionListener(new PauseListener(this.mainF));
    this.sauvegarder.addActionListener(new PauseListener(this.mainF));
    this.charger.addActionListener(new PauseListener(this.mainF));
    this.sauvegarderQuitter.addActionListener(new PauseListener(this.mainF));
    this.menu.addActionListener(new PauseListener(this.mainF));


    this.reprendre.setPreferredSize(new Dimension(500, 100));
    this.sauvegarder.setPreferredSize(new Dimension(500, 100));
    this.charger.setPreferredSize(new Dimension(500, 100));
    this.sauvegarderQuitter.setPreferredSize(new Dimension(500, 100));
    this.menu.setPreferredSize(new Dimension(500, 100));
    this.titre.setForeground(Color.WHITE);

    JButton titreContainer = new JButton(this.titre.getText());
    titreContainer.setOpaque(false);
    titreContainer.setContentAreaFilled(false);
    titreContainer.setBorderPainted(false);
    titreContainer.setFocusable(false);
    titreContainer.setForeground(Color.WHITE);
    upContainer.add(titreContainer,BorderLayout.CENTER);

    buttonContainer = new JPanel(new BorderLayout());
    newLabel = new JLabel("                    ");
    newLabel.setBackground(Color.BLACK);
    newLabel.setForeground(Color.BLACK);
    buttonContainer.add(newLabel,BorderLayout.WEST);
    newLabel = new JLabel("                    ");
    newLabel.setBackground(Color.BLACK);
    newLabel.setForeground(Color.BLACK);
    buttonContainer.add(newLabel,BorderLayout.EAST);
    buttonContainer.add(this.reprendre);
    buttonContainer.setBackground(Color.BLACK);
    centerContainer.add(buttonContainer);

    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    centerContainer.add(newPanel);

    buttonContainer = new JPanel(new BorderLayout());
    newLabel = new JLabel("                    ");
    newLabel.setBackground(Color.BLACK);
    newLabel.setForeground(Color.BLACK);
    buttonContainer.add(newLabel,BorderLayout.WEST);
    newLabel = new JLabel("                    ");
    newLabel.setBackground(Color.BLACK);
    newLabel.setForeground(Color.BLACK);
    buttonContainer.add(newLabel,BorderLayout.EAST);
    buttonContainer.add(this.sauvegarder);
    buttonContainer.setBackground(Color.BLACK);
    centerContainer.add(buttonContainer);

    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    centerContainer.add(newPanel);

    buttonContainer = new JPanel(new BorderLayout());
    newLabel = new JLabel("                    ");
    newLabel.setBackground(Color.BLACK);
    newLabel.setForeground(Color.BLACK);
    buttonContainer.add(newLabel,BorderLayout.WEST);
    newLabel = new JLabel("                    ");
    newLabel.setBackground(Color.BLACK);
    newLabel.setForeground(Color.BLACK);
    buttonContainer.add(newLabel,BorderLayout.EAST);
    buttonContainer.add(this.charger);
    buttonContainer.setBackground(Color.BLACK);
    centerContainer.add(buttonContainer);

    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    centerContainer.add(newPanel);

    buttonContainer = new JPanel(new BorderLayout());
    newLabel = new JLabel("                    ");
    newLabel.setBackground(Color.BLACK);
    newLabel.setForeground(Color.BLACK);
    buttonContainer.add(newLabel,BorderLayout.WEST);
    newLabel = new JLabel("                    ");
    newLabel.setBackground(Color.BLACK);
    newLabel.setForeground(Color.BLACK);
    buttonContainer.add(newLabel,BorderLayout.EAST);
    buttonContainer.add(this.sauvegarderQuitter);
    buttonContainer.setBackground(Color.BLACK);
    centerContainer.add(buttonContainer);

    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    centerContainer.add(newPanel);

    buttonContainer = new JPanel(new BorderLayout());
    newLabel = new JLabel("                    ");
    newLabel.setBackground(Color.BLACK);
    newLabel.setForeground(Color.BLACK);
    buttonContainer.add(newLabel,BorderLayout.WEST);
    newLabel = new JLabel("                    ");
    newLabel.setBackground(Color.BLACK);
    newLabel.setForeground(Color.BLACK);
    buttonContainer.add(newLabel,BorderLayout.EAST);
    buttonContainer.add(this.menu);
    buttonContainer.setBackground(Color.BLACK);
    centerContainer.add(buttonContainer);


    this.add(upContainer,BorderLayout.NORTH);
    this.add(centerContainer,BorderLayout.CENTER);
  }
}
