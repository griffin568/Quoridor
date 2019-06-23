package controller;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import view.*;

public class PartieListener extends MouseAdapter {

  private int x;
  private int y;
  private Controleur controleur;
  private PartieFrame frame;
  private JLabel click;

  /**
    * Créé un nouvel objet PartieListener
    * @param x la coordonnée X de la case
    * @param y la coordonnée Y de la case
    * @param controleur le Controleur de la partie
    * @param frame la fenêtre de jeu
    * @param click le label indiquant si l'on effectue le premier ou le second click
    */
    public PartieListener (int x , int y, Controleur controleur , PartieFrame frame , JLabel click) {
      try {
        if ((x < 0) || (x > 16) || (y < 0) || (y > 16) || controleur == null || frame == null || click == null) {
          throw new Exception ("Erreur du constructeur PartieListener(), parametres invalides");
        }
        else {
          this.x = x;
          this.y = y;
          this.controleur = controleur;
          this.frame = frame;
          this.click = click;
        }
      }
      catch (Exception e) {
        System.err.println(e.getMessage());
      }
    }

  /**
    * Met à jour le controleur après un clic sur une case
    * @param e l'événement à écouter
    */
    public void mouseClicked (MouseEvent e) {
      if (this.click.getText().equals("Cliquez")) {
        this.click.setText("Placez");
      }
      else {
        this.click.setText("Cliquez");
      }
      if (this.controleur.getX1() == -1 && this.controleur.getY1() == -1) {
        this.controleur.setX1(this.x);
        this.controleur.setY1(this.y);
      }
      else {
        this.controleur.setX2(this.x);
        this.controleur.setY2(this.y);
        if (this.controleur.controle()) {
          this.controleur.changeActif();        
        }
        this.frame.updateFrame();
      }
    }
}
