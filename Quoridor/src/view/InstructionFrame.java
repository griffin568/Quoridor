package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import quoridor.*;
import controller.*;
import java.util.ArrayList;

/**
  * L'écran affichant les instructions pour redémarrer en mode texte
  * @author Drmarsupial35 , griffin568
  * @version 1.0.0
  */
public class InstructionFrame extends JPanel {

  private JTextArea instructions;
  private JButton quitter;

  /**
    * Créé un nouvel objet InstructionFrame
    * @param mainF la fenêtre principale de l'application
    */
    public InstructionFrame (MainFrame mainF) {
        this.instructions = new JTextArea("Parametre enregistrés\nPour ouvrir l'applications en mode texte, ouvrez un terminal et tapez\n\njava -jar quoridor.jar 0\ndans le dossier contenant le jar");
        this.instructions.setBackground(Color.BLACK);
        this.instructions.setForeground(Color.WHITE);

        this.quitter = new JButton("QUITTER");
        this.quitter.addActionListener(new DownButtonListener(mainF,""));

        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);

        this.add(this.instructions,BorderLayout.CENTER);
        this.add(this.quitter,BorderLayout.SOUTH);
    }
}
