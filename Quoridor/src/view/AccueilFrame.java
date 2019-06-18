package view;

import javax.swing.*;
import java.awt.*;

public class AccueilFrame extends JPanel {

  private JButton PhotoButton;

  public AccueuilFrame() {
    initComponent();
  }

  private void initComponent() {
    this.setLayout(new BorderLayout());
    JPanel leftContainer = new JPanel(new BorderLayout());

    this.PhotoButton = new JButton();
    this.PhotoButton.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/Logo.png"))));
    this.PhotoButton.setOpaque(false);
    this.PhotoButton.setContentAreaFilled(false);
    this.PhotoButton.setBorderPainted(false);

    leftContainer.add(this.PhotoButton,BorderLayout.CENTER);
    add(leftContainer,BorderLayout.WEST);
  }
}
