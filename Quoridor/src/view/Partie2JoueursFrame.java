package view;

import javax.swing.*;
import java.awt.*;
import controller.*;

public class Partie2JoueursFrame extends JPanel {

  private MainFrame mainF;
  private JPanel partie;

  private JButton texteBton, visuelBton, retour;
  private JLabel titre, j1Label, j2Label;
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
        throw new Exception("L'écran de jeu) doit exister pour créer l'écran de sélection de joueurs (Partie à 2 joueurs).");
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
    JPanel leftCenterContainer = new JPanel(new GridLayout(2,1));
    JPanel rightCenterContainer = new JPanel(new GridLayout(2,2));
    JPanel centerContainer = new JPanel(new GridLayout(1,2));
    JPanel downContainer = new JPanel(new BorderLayout());

    upContainer.setBackground(Color.BLACK);
    leftCenterContainer.setBackground(Color.BLACK);
    rightCenterContainer.setBackground(Color.BLACK);
    centerContainer.setBackground(Color.BLACK);
    downContainer.setBackground(Color.BLACK);

    String[] lesChoix = {"HUMAIN","IA - FACILE","IA - MOYENNE","IA - DIFFICILE","IA - IMPOSSIBLE"};

    this.titre = new JLabel("CREER UNE PARTIE - 2 JOUEURS");
    this.j1Label = new JLabel("JOUEUR 1");
    this.j2Label = new JLabel("JOUEUR 2");
    this.texteBton = new JButton("JOUER EN MODE TEXTE");
    this.visuelBton = new JButton("JOUER EN MODE GRAPHIQUE");
    this.retour = new JButton("RETOUR");
    this.j1CB = new JComboBox(lesChoix);
    this.j2CB = new JComboBox(lesChoix);

    this.retour.addActionListener(new DownButtonListener(this.mainF, "choixNombre"));

    this.texteBton.setPreferredSize(new Dimension(500, 100));
    this.visuelBton.setPreferredSize(new Dimension(500, 100));
    this.retour.setPreferredSize(new Dimension(200, 50));
    this.titre.setForeground(Color.WHITE);

    upContainer.add(this.titre,BorderLayout.CENTER);
    leftCenterContainer.add(this.texteBton);
    leftCenterContainer.add(this.visuelBton);
    rightCenterContainer.add(this.j1Label);
    rightCenterContainer.add(this.j2Label);
    rightCenterContainer.add(this.j1CB);
    rightCenterContainer.add(this.j2CB);
    centerContainer.add(leftCenterContainer);
    centerContainer.add(rightCenterContainer);
    downContainer.add(this.retour, BorderLayout.EAST);

    this.add(upContainer, BorderLayout.NORTH);
    this.add(centerContainer, BorderLayout.CENTER);
    this.add(downContainer, BorderLayout.SOUTH);
  }
}
