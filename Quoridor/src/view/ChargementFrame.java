package view;

import java.util.ArrayList;
import quoridor.RWFile;
import javax.swing.*;
import controller.*;
import java.awt.*;

/**
  * Cette classe gère la création de la page de chargement de parties, ainsi que ses composants graphiques.
  * @author Drmarsupial35, griffin568
  * @version 1.0.0
  */
public class ChargementFrame extends JPanel {

  private MainFrame mainF;
  private JPanel partie;

  private JButton save1, save2, save3, retour, corbeille;
  private JLabel titre;

  /**
    * Créer la page de chargement de partie. Cette méthode appele la méthode privée initComponent()
    * @param parent La fenetre principale de jeu qui est utilisée pour changer l'écran affiché
    * @param partie l'écran où la partie est affiché
    */
  public ChargementFrame(MainFrame parent, JPanel partie) {
    try {
      if (parent == null) {
        throw new Exception("ChargementFrame constructeur - L'écran principal doit exister pour créer l'écran de chargement de parties.");
      }
      else if (partie == null) {
        throw new Exception("ChargementFrame constructeur - L'écran de jeu doit exister pour créer l'écran de chargement de parties.");
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
    *Initialise les composants graphiques de la page de chargement (principalement les boutons cliquables).
    */
  private void initComponent() {
    ImageIcon corbeille = new ImageIcon("../data/img/CorbeilleBlanc.png");
    ImageIcon resizedCorbeille = new ImageIcon(corbeille.getImage().getScaledInstance(98, 142, java.awt.Image.SCALE_SMOOTH));

    this.setLayout(new BorderLayout());
    JPanel upContainer = new JPanel(new BorderLayout());
    JPanel downContainer = new JPanel(new BorderLayout());
    JPanel centerContainer = new JPanel(new GridLayout(5,3));
    JPanel newPanel;

    upContainer.setBackground(Color.BLACK);
    centerContainer.setBackground(Color.BLACK);
    downContainer.setBackground(Color.BLACK);

    this.titre = new JLabel("CHARGER UNE PARTIE");
    this.save1 = new JButton("EMPLACEMENT 1");
    this.save2 = new JButton("EMPLACEMENT 2");
    this.save3 = new JButton("EMPLACEMENT 3");
    this.retour = new JButton("RETOUR");
    this.corbeille = new JButton(resizedCorbeille);

    ArrayList<JButton> lesBoutons = new ArrayList<JButton>();
    lesBoutons.add(this.save1);
    lesBoutons.add(this.save2);
    lesBoutons.add(this.save3);

    for (JButton b : lesBoutons) {
      char num = b.getText().split(" ")[1].charAt(0);
      majBouton(b, num);
    }

    this.save1.addActionListener(new ChargementListener(this.mainF, "sauvegarde1", this, false));
    this.save2.addActionListener(new ChargementListener(this.mainF, "sauvegarde2", this, false));
    this.save3.addActionListener(new ChargementListener(this.mainF, "sauvegarde3", this, false));
    this.corbeille.addActionListener(new CorbeilleListener(lesBoutons, false, true));
    this.retour.addActionListener(new DownButtonListener(this.mainF, "Accueil"));

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


  /**
    * Met à jour le texte d'un bouton afin d'indiquer si son emplacement de sauvegarde est vide ou pas
    * @param leBouton le bouton à mettre à jour
    * @param num le numéro de l'emplacement de sauvegarde désigné par le bouton
    */
  public void majBouton(JButton leBouton, char num) {
    try {
      if (leBouton == null) {
        throw new Exception("ChargementFrame majBouton - Le bouton doit exister pour qu'il soit mis à jour.");
      }
      else {
        if (Character.isDigit(num)) {
          ArrayList<String> lignes = RWFile.readFile("sauvegarde" + num);
          char c1 =lignes.get(0).charAt(0);
          if (Character.toString(c1).equals(" ")) {
            leBouton.setText("EMPLACEMENT VIDE");
          }
        }
        else {
          leBouton.setText("EMPLACEMENT " + num);
        }
      }
    }
    catch(Exception e) {
      System.err.println(e.getMessage());
    }
  }
}
