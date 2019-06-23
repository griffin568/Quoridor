package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import quoridor.*;
import java.util.ArrayList;

public class InstructionFrame extends JPanel {

  private JTextArea instructions;

  /**
    * Créé un nouvel objet InstructionFrame
    */
    public InstructionFrame () {
        this.instructions = new JTextArea("Parametre enregistrés\nPour ouvrir l'applications en mode texte, ouvrez un terminal et tapez\n\njava -jar quoridor.jar 0\ndans le dossier contenant le jar");
        this.instructions.setBackground(Color.BLACK);
        this.instructions.setForeground(Color.WHITE);

        this.setBackground(Color.BLACK);

        this.add(this.instructions);
    }
}