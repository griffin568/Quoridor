package view;

import controller.*;
import javax.swing.*;
import java.awt.*;

/**
  * Cette classe gère la création de la page de chargement de parties, ainsi que ses composants graphiques.
  * @author Drmarsupial35, griffin568
  * @version 0.1.0
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
        this.mainF = mainF;
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
    this.setLayout(new BorderLayout());
    JPanel upContainer = new JPanel(new BorderLayout());
    JPanel downContainer = new JPanel(new BorderLayout());
    JPanel centerContainer = new JPanel(new GridLayout(3,1));

    upContainer.setBackground(Color.BLACK);
    centerContainer.setBackground(Color.BLACK);
    downContainer.setBackground(Color.BLACK);

    this.titre = new JLabel("CHARGER UNE PARTIE");
    this.save1 = new JButton("Emplacement 1");
    this.save2 = new JButton("Emplacement 2");
    this.save3 = new JButton("Emplacement 3");
    this.retour = new JButton("RETOUR");
    this.corbeille = new JButton(new ImageIcon("../data/img/Corbeille.png"));

    this.retour.addActionListener(new DownButtonListener(this.mainF, "Accueil"));

    this.corbeille.setOpaque(false);
    this.corbeille.setContentAreaFilled(false);
    this.corbeille.setBorderPainted(false);

    this.save1.setPreferredSize(new Dimension(500, 100));
    this.save2.setPreferredSize(new Dimension(500, 100));
    this.save3.setPreferredSize(new Dimension(500, 100));

    upContainer.add(this.titre, BorderLayout.CENTER);
    upContainer.add(this.corbeille, BorderLayout.EAST);
    centerContainer.add(this.save1);
    centerContainer.add(this.save2);
    centerContainer.add(this.save3);
    downContainer.add(this.retour, BorderLayout.EAST);

    add(upContainer,BorderLayout.NORTH);
    add(centerContainer,BorderLayout.CENTER);
    add(downContainer,BorderLayout.SOUTH);
  }
}
