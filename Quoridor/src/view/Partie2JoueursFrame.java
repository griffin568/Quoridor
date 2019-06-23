package view;

import javax.swing.*;
import java.awt.*;
import controller.*;

public class Partie2JoueursFrame extends JPanel {

  private MainFrame mainF;
  private JPanel partie;

  private JButton texteBton, visuelBton, retour;
  private JLabel titre;
  private JTextField j1TextField, j2TextField;
  private JComboBox j1CB, j2CB;

  /**
    *
    * @param parent
    * @param partie
    */
  public Partie2JoueursFrame(MainFrame parent, JPanel partie) {
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

    String[] lesChoix = {"HUMAIN","IA - FACILE","IA - MOYENNE","IA - DIFFICILE"};

    this.titre = new JLabel("CREER UNE PARTIE - 2 JOUEURS");
    this.j1TextField = new JTextField("\t    JOUEUR 1");
    this.j2TextField = new JTextField("\t    JOUEUR 2");
    this.texteBton = new JButton("JOUER EN MODE TEXTE");
    this.visuelBton = new JButton("JOUER EN MODE GRAPHIQUE");
    this.retour = new JButton("RETOUR");
    this.j1CB = new JComboBox(lesChoix);
    this.j2CB = new JComboBox(lesChoix);

    this.retour.addActionListener(new DownButtonListener(this.mainF, "choixNombre"));

    this.texteBton.setPreferredSize(new Dimension(500, 100));
    this.visuelBton.setPreferredSize(new Dimension(500, 100));
    this.retour.setPreferredSize(new Dimension(200, 50));
    this.j1TextField.setPreferredSize(new Dimension(100,100));
    this.j2TextField.setPreferredSize(new Dimension(100,100));
    this.j1CB.setPreferredSize(new Dimension(100,100));
    this.j2CB.setPreferredSize(new Dimension(100,100));
    this.titre.setForeground(Color.WHITE);
    this.j1TextField.setBackground(Color.BLACK);
    this.j2TextField.setBackground(Color.BLACK);
    this.j1TextField.setForeground(Color.WHITE);
    this.j2TextField.setForeground(Color.WHITE);

    this.visuelBton.addActionListener(new GraphiqueLauncherListener(this.j1TextField,this.j2TextField,this.j1CB,this.j2CB,this.mainF));


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

    centerContainer.add(leftCenterContainer);
    centerContainer.add(rightCenterContainer);
    downContainer.add(this.retour, BorderLayout.EAST);

    this.add(upContainer, BorderLayout.NORTH);
    this.add(centerContainer, BorderLayout.CENTER);
    this.add(downContainer, BorderLayout.SOUTH);
  }
}
