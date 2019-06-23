package controller;

import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.*;
import view.*;

/**
  * Listener permettant de s'assurer qu'il n'y a aucun espace dans le nom d'un joueur
  * @author Drmarsupial35 , griffin568
  * @version 1.0.0
  */
  public class TextFieldListener implements FocusListener {


    /**
      * Créé un nouvel objet TextFieldListener
      */
      public TextFieldListener() {}

    /**
      * Si le contenu de la JTextField contient un espace, alors celui-ci est supprimé
      * @param e le FocusEvent à écouter
      */
      public void focusLost (FocusEvent e) {
        if (((JTextField)e.getSource()).getText().contains(" ")) {
          ((JTextField)e.getSource()).setText("J");
        }
        else if (((JTextField)e.getSource()).getText().equals("")) {
          ((JTextField)e.getSource()).setText("J");
        }
      }

      public void focusGained (FocusEvent e) {}

  }
