package view;

import javax.swing.*;
import java.awt.*;
import controller.*;

public class ChoixNombreFrame extends JPanel {

  private MainFrame mainF;
  private JPanel partie2, partie4;
  private JButton retour, partie2Bton, partie4Bton;
  private JLabel titre;

  /**
    *
    * @param parent
    * @param partie2
    * @param partie4
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
    *
    */
  private void initComponent() {
    this.setLayout(new BorderLayout());
    JPanel upContainer = new JPanel(new BorderLayout());
    JPanel centerContainer = new JPanel(new GridLayout(2,1));
    JPanel downContainer = new JPanel(new BorderLayout());

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

    upContainer.add(this.titre,BorderLayout.CENTER);
    centerContainer.add(this.partie2Bton);
    centerContainer.add(this.partie4Bton);
    downContainer.add(this.retour, BorderLayout.EAST);

    this.add(upContainer, BorderLayout.NORTH);
    this.add(centerContainer, BorderLayout.CENTER);
    this.add(downContainer, BorderLayout.SOUTH);
  }
}
