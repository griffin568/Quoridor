package view;

import javax.swing.*;
import java.awt.*;
import controller.*;

public class ChargementPauseFrame extends JPanel {

  private MainPauseFrame mainF;
  private MainFrame parent;
  private JPanel partie;

  private JButton save1, save2, save3, retour, corbeille;
  private JLabel titre;

  /**
    * Créer la page de chargement de partie (Dans la fenêtre de pause). Cette méthode appele la méthode privée initComponent()
    * @param parent La fenetre principale de jeu qui est utilisée pour changer l'écran affiché
    * @param partie l'écran où la partie est affiché
    */
  public ChargementPauseFrame(MainPauseFrame mainF, JPanel partie, MainFrame parent) {
    try {
      if (parent == null) {
        throw new Exception("ChargementPauseFrame constructeur - L'écran principal doit exister pour créer l'écran de chargement de parties.");
      }
      else if (mainF == null) {
        throw new Exception("ChargementPauseFrame constructeur - L'écran principal de pause doit exister pour créer l'écran de chargement de parties.");
      }
      else if (partie == null) {
        throw new Exception("ChargementPauseFrame constructeur - L'écran de jeu doit exister pour créer l'écran de chargement de parties.");
      }
      else {
        this.mainF = mainF;
        this.partie = partie;
        this.parent = parent;
        this.setBackground(Color.BLACK);
        initComponent();
      }
    }
    catch(Exception e) {
      System.err.println(e.getMessage());
    }
  }

  /**
    *Initialise les composants graphiques de la page de chargement (principalement les boutons cliquables).
    */
  private void initComponent() {
    ImageIcon corbeille = new ImageIcon("../data/img/CorbeilleBlanc.png");
    ImageIcon resizedCorbeille = new ImageIcon(corbeille.getImage().getScaledInstance(59, 71, java.awt.Image.SCALE_SMOOTH));

    this.setLayout(new BorderLayout());
    JPanel upContainer = new JPanel(new BorderLayout());
    JPanel downContainer = new JPanel(new BorderLayout());
    JPanel centerContainer = new JPanel(new GridLayout(5,3));
    JPanel newPanel;

    upContainer.setBackground(Color.BLACK);
    centerContainer.setBackground(Color.BLACK);
    downContainer.setBackground(Color.BLACK);

    this.titre = new JLabel("CHARGER UNE PARTIE");
    this.save1 = new JButton("Emplacement 1");
    this.save2 = new JButton("Emplacement 2");
    this.save3 = new JButton("Emplacement 3");
    this.retour = new JButton("RETOUR");
    this.corbeille = new JButton(resizedCorbeille);

    this.retour.addActionListener(new DownButtonListener(this.mainF, "Pause"));

    this.corbeille.setOpaque(false);
    this.corbeille.setContentAreaFilled(false);
    this.corbeille.setBorderPainted(false);

    this.save1.setPreferredSize(new Dimension(500, 100));
    this.save2.setPreferredSize(new Dimension(500, 100));
    this.save3.setPreferredSize(new Dimension(500, 100));
    this.retour.setPreferredSize(new Dimension(200, 50));
    this.titre.setForeground(Color.WHITE);

    JButton titreContainer = new JButton(this.titre.getText());
    titreContainer.setOpaque(false);
    titreContainer.setContentAreaFilled(false);
    titreContainer.setBorderPainted(false);
    titreContainer.setFocusable(false);
    titreContainer.setForeground(Color.WHITE);
    upContainer.add(titreContainer, BorderLayout.CENTER);
    upContainer.add(this.corbeille, BorderLayout.EAST);

    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    centerContainer.add(newPanel);
    centerContainer.add(this.save1);
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
    centerContainer.add(this.save2);
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
    centerContainer.add(this.save3);
    newPanel = new JPanel();
    newPanel.setBackground(Color.BLACK);
    centerContainer.add(newPanel);


    downContainer.add(this.retour, BorderLayout.EAST);

    add(upContainer,BorderLayout.NORTH);
    add(centerContainer,BorderLayout.CENTER);
    add(downContainer,BorderLayout.SOUTH);
  }
}
