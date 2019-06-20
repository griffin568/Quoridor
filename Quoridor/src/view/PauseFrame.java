package view;

import javax.swing.*;
import java.awt.*;
import controller.*;

public class PauseFrame extends JPanel {

  private MainPauseFrame mainF;
  private JPanel acccueil;

  private JButton reprendre,sauvegarder,charger,sauvegarderQuitter,menu;
  private JLabel titre;

  public PauseFrame(MainPauseFrame parent, JPanel acccueil) {
    try {
      if (parent == null) {
        throw new Exception("PauseFrame constructeur - La fenêtre de pause doit exister pour créer l'écran de pause.");
      }
      else if (acccueil == null) {
        throw new Exception("PauseFrame constructeur - L'écran d'acccueil doit exister pour créer l'écran de pause.");
      }
      else {
        this.mainF = parent;
        this.acccueil = acccueil;
        this.setBackground(Color.BLACK);
        initComponent();
      }
    }
    catch(Exception e) {
      System.err.println(e.getMessage());
    }
  }

  private void initComponent() {
    this.setLayout(new GridLayout(2,1));
    JPanel upContainer = new JPanel(new BorderLayout());
    JPanel centerContainer = new JPanel(new GridLayout(5,1));

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

    upContainer.add(this.titre,BorderLayout.CENTER);
    centerContainer.add(this.reprendre);
    centerContainer.add(this.sauvegarder);
    centerContainer.add(this.charger);
    centerContainer.add(this.sauvegarderQuitter);
    centerContainer.add(this.menu);

    this.add(upContainer);
    this.add(centerContainer);
  }
}
