package view;

import javax.swing.*;
import java.awt.*;
import controller.*;

/**
  * L'écran de configuration d'une partie à 4 joueurs
  * @author Drmarsupial35 , griffin568
  * @version 1.0.0
  */
public class Partie4JoueursFrame extends JPanel {

  private MainFrame mainF;
  private JPanel partie;

  private JButton texteBton, visuelBton, retour;
  private JLabel titre;
  private JTextField j1TextField, j2TextField, j3TextField, j4TextField;
  private JComboBox j1CB, j2CB, j3CB, j4CB;

  /**
    * Créé un nouvel objet Partie4JoueursFrame
    * @param parent la fenêtre principale de l'application
    * @param partie l'écran de partie
    */
  public Partie4JoueursFrame(MainFrame parent, JPanel partie) {
    try {
      if (parent == null) {
        throw new Exception("L'écran principal doit exister pour créer l'écran de sélection de joueurs (Partie à 2 joueurs).");
      }
      else if (partie == null) {
        throw new Exception("L'écran de jeu doit exister pour créer l'écran de sélection de joueurs (Partie à 2 joueurs).");
      }
      else {
        this.mainF = parent;
        this.partie = partie;
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
    JPanel leftCenterContainer = new JPanel(new GridLayout(7,3));
    JPanel rightCenterContainer = new JPanel(new GridLayout(2,2));
    JPanel centerContainer = new JPanel(new GridLayout(1,2));
    JPanel downContainer = new JPanel(new BorderLayout());
    JPanel playerTextFieldPanel;
    JPanel playerComboBoxPanel;
    JPanel newPanel;
    JPanel playerPanel;

    upContainer.setBackground(Color.BLACK);
    leftCenterContainer.setBackground(Color.BLACK);
    rightCenterContainer.setBackground(Color.BLACK);
    centerContainer.setBackground(Color.BLACK);
    downContainer.setBackground(Color.BLACK);

    String[] lesChoix = {"HUMAIN","IA - FACILE","IA - MOYENNE"};

    this.titre = new JLabel("CREER UNE PARTIE - 4 JOUEURS");
    this.j1TextField = new JTextField("    JOUEUR 1");
    this.j2TextField = new JTextField("    JOUEUR 2");
    this.j3TextField = new JTextField("    JOUEUR 3");
    this.j4TextField = new JTextField("    JOUEUR 4");
    this.texteBton = new JButton("JOUER EN MODE TEXTE");
    this.visuelBton = new JButton("JOUER EN MODE GRAPHIQUE");
    this.retour = new JButton("RETOUR");
    this.j1CB = new JComboBox(lesChoix);
    this.j2CB = new JComboBox(lesChoix);
    this.j3CB = new JComboBox(lesChoix);
    this.j4CB = new JComboBox(lesChoix);

    this.retour.addActionListener(new DownButtonListener(this.mainF, "choixNombre"));

    this.visuelBton.addActionListener(new GraphiqueLauncherListener(this.j1TextField,this.j2TextField,this.j3TextField,this.j4TextField,this.j1CB,this.j2CB,this.j3CB,this.j4CB,this.mainF,true));
    this.texteBton.addActionListener(new GraphiqueLauncherListener(this.j1TextField,this.j2TextField,this.j3TextField,this.j4TextField,this.j1CB,this.j2CB,this.j3CB,this.j4CB,this.mainF,false));

    this.texteBton.setPreferredSize(new Dimension(500, 100));
    this.visuelBton.setPreferredSize(new Dimension(500, 100));
    this.retour.setPreferredSize(new Dimension(200, 50));
    this.titre.setForeground(Color.WHITE);
    this.j1TextField.setBackground(Color.BLACK);
    this.j2TextField.setBackground(Color.BLACK);
    this.j3TextField.setBackground(Color.BLACK);
    this.j4TextField.setBackground(Color.BLACK);
    this.j1TextField.setForeground(Color.WHITE);
    this.j2TextField.setForeground(Color.WHITE);
    this.j3TextField.setForeground(Color.WHITE);
    this.j4TextField.setForeground(Color.WHITE);


    JButton titreContainer = new JButton(this.titre.getText());
    titreContainer.setOpaque(false);
    titreContainer.setContentAreaFilled(false);
    titreContainer.setBorderPainted(false);
    titreContainer.setFocusable(false);
    titreContainer.setForeground(Color.WHITE);

    upContainer.add(titreContainer,BorderLayout.CENTER);


    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    leftCenterContainer.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    leftCenterContainer.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    leftCenterContainer.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    leftCenterContainer.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    leftCenterContainer.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    leftCenterContainer.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    leftCenterContainer.add(newPanel);
    leftCenterContainer.add(this.texteBton);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    leftCenterContainer.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    leftCenterContainer.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    leftCenterContainer.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    leftCenterContainer.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    leftCenterContainer.add(newPanel);
    leftCenterContainer.add(this.visuelBton);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    leftCenterContainer.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    leftCenterContainer.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    leftCenterContainer.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    leftCenterContainer.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    leftCenterContainer.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    leftCenterContainer.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    leftCenterContainer.add(newPanel);


    playerTextFieldPanel = new JPanel(new GridLayout(2,1));
    playerComboBoxPanel = new JPanel(new GridLayout(2,1));

    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    playerTextFieldPanel.add(newPanel);
    playerTextFieldPanel.add(j1TextField);

    playerComboBoxPanel.add(j1CB);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    playerComboBoxPanel.add(newPanel);


    playerPanel = new JPanel(new GridLayout(3,3));
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    playerPanel.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    playerPanel.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    playerPanel.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    playerPanel.add(newPanel);
    playerPanel.add(playerTextFieldPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    playerPanel.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    playerPanel.add(newPanel);
    playerPanel.add(playerComboBoxPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    playerPanel.add(newPanel);

    rightCenterContainer.add(playerPanel);

    playerTextFieldPanel = new JPanel(new GridLayout(2,1));
    playerComboBoxPanel = new JPanel(new GridLayout(2,1));

    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    playerTextFieldPanel.add(newPanel);
    playerTextFieldPanel.add(j2TextField);

    playerComboBoxPanel.add(j2CB);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    playerComboBoxPanel.add(newPanel);

    playerPanel = new JPanel(new GridLayout(3,3));
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    playerPanel.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    playerPanel.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    playerPanel.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    playerPanel.add(newPanel);
    playerPanel.add(playerTextFieldPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    playerPanel.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    playerPanel.add(newPanel);
    playerPanel.add(playerComboBoxPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    playerPanel.add(newPanel);

    rightCenterContainer.add(playerPanel);


    playerTextFieldPanel = new JPanel(new GridLayout(2,1));
    playerComboBoxPanel = new JPanel(new GridLayout(2,1));

    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    playerTextFieldPanel.add(newPanel);
    playerTextFieldPanel.add(j3TextField);

    playerComboBoxPanel.add(j3CB);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    playerComboBoxPanel.add(newPanel);

    playerPanel = new JPanel(new GridLayout(3,3));
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    playerPanel.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    playerPanel.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    playerPanel.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    playerPanel.add(newPanel);
    playerPanel.add(playerTextFieldPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    playerPanel.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    playerPanel.add(newPanel);
    playerPanel.add(playerComboBoxPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    playerPanel.add(newPanel);

    rightCenterContainer.add(playerPanel);


    playerTextFieldPanel = new JPanel(new GridLayout(2,1));
    playerComboBoxPanel = new JPanel(new GridLayout(2,1));

    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    playerTextFieldPanel.add(newPanel);
    playerTextFieldPanel.add(j4TextField);

    playerComboBoxPanel.add(j4CB);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    playerComboBoxPanel.add(newPanel);

    playerPanel = new JPanel(new GridLayout(3,3));
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    playerPanel.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    playerPanel.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    playerPanel.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    playerPanel.add(newPanel);
    playerPanel.add(playerTextFieldPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    playerPanel.add(newPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    playerPanel.add(newPanel);
    playerPanel.add(playerComboBoxPanel);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    playerPanel.add(newPanel);

    rightCenterContainer.add(playerPanel);


    centerContainer.add(leftCenterContainer);
    centerContainer.add(rightCenterContainer);
    downContainer.add(this.retour, BorderLayout.EAST);

    this.add(upContainer, BorderLayout.NORTH);
    this.add(centerContainer, BorderLayout.CENTER);
    this.add(downContainer, BorderLayout.SOUTH);
  }
}
