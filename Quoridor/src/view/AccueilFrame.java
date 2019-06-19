package view;

import javax.swing.*;
import java.awt.*;
import view.controller.*;

public class AccueilFrame extends JPanel {

  private MainFrame parent;
  private JButton PhotoButton, LancerBton, ChargerBton, QuitterBton;

  public AccueilFrame(MainFrame parent) {
    try {
      if (parent == null) {
        throw new Exception("La Frame parent doit exister pour créer l'Accueil.");
      }
      this.parent = parent;
      this.setBackground(Color.BLACK);
      initComponent();
    }
    catch(Exception e) {
      System.err.println(e.getMessage());
    }
  }

  private void initComponent() {
    this.setLayout(new BorderLayout());
    JPanel leftContainer = new JPanel(new BorderLayout());
    JPanel rightContainer = new JPanel(new BorderLayout());
    JPanel downContainer = new JPanel(new BorderLayout());

    leftContainer.setBackground(Color.BLACK);
    rightContainer.setBackground(Color.BLACK);
    downContainer.setBackground(Color.BLACK);

    this.PhotoButton = new JButton(new ImageIcon("../data/Accueil_Photo.png"));
    this.LancerBton = new JButton("Lancer une nouvelle partie");
    this.ChargerBton = new JButton("Charger une nouvelle partie");
    this.QuitterBton = new JButton("QUITTER");

    this.QuitterBton.addActionListener(new DownButtonListener(this.parent, this));

    this.LancerBton.setPreferredSize(new Dimension(500, 100));
    this.ChargerBton.setPreferredSize(new Dimension(500, 100));
    this.QuitterBton.setPreferredSize(new Dimension(200, 50));

    this.PhotoButton.setOpaque(false);
    this.PhotoButton.setContentAreaFilled(false);
    this.PhotoButton.setBorderPainted(false);
    this.PhotoButton.setFocusable(false);

    leftContainer.add(this.PhotoButton,BorderLayout.CENTER);
    rightContainer.add(new JLabel(" "),BorderLayout.WEST);
    rightContainer.add(new JLabel(" "),BorderLayout.EAST);
    rightContainer.add(this.LancerBton,BorderLayout.NORTH);
    rightContainer.add(this.ChargerBton,BorderLayout.SOUTH);
    downContainer.add(this.QuitterBton,BorderLayout.EAST);

    add(leftContainer,BorderLayout.WEST);
    add(rightContainer,BorderLayout.EAST);
    add(downContainer,BorderLayout.SOUTH);
  }
}
