package controller;

import javax.swing.event.*;
import java.awt.event.*;
import quoridor.*;
import javax.swing.*;
import view.*;
import quoridor.*;
import java.util.ArrayList;
import java.lang.Runtime;

public class GraphiqueLauncherListener implements ActionListener {

  private JTextField j1TextField , j2TextField , j3TextField , j4TextField;
  private JComboBox j1CB , j2CB , j3CB , j4CB;
  private MainFrame main;
  private boolean ok2; // Je savais pas comment l'appeler mais son rôle est d'identifier si la partie possède 2 joueurs ou non
  private boolean visuel;

  /**
    * Créé un nouvel objet GraphiqueLauncherListener
    * Partie à 2 joueurs
    * @param j1TextField la zone de texte contenant le nom du joueur 1
    * @param j2TextField la zone de texte contenant le nom du joueur 2
    * @param j1CB la JComboBox contenant la nature du joueur 1
    * @param j2CB la JComboBox contenant la nature du joueur 2
    * @param main la MainFrame de l'application
    * @param visuel vrai si la partie doit être chargée en mode visuel, faux si elle c'est en mode texte
    */
    public GraphiqueLauncherListener (JTextField j1TextField , JTextField j2TextField , JComboBox j1CB , JComboBox j2CB , MainFrame main, boolean visuel) {
      try {
        if (j1TextField == null || j2TextField == null || j1CB == null || j2CB == null || main == null) {
          throw new Exception ("Erreur du constructeur GraphiqueLauncherListener(), parametre null");
        }
        else {
          this.j1TextField = j1TextField;
          this.j2TextField = j2TextField;
          this.j1CB = j1CB;
          this.j2CB = j2CB;
          this.main = main;
          this.visuel = visuel;
          this.ok2 = true;
        }
      }
      catch (Exception e) {
        System.err.println(e.getMessage());
      }
    }

  /**
    * Créé un nouvel objet GraphiqueLauncherListener
    * Partie à 4 joueurs
    * @param j1TextField la zone de texte contenant le nom du joueur 1
    * @param j2TextField la zone de texte contenant le nom du joueur 2
    * @param j3TextField la zone de texte contenant le nom du joueur 3
    * @param j4TextField la zone de texte contenant le nom du joueur 4
    * @param j1CB la JComboBox contenant la nature du joueur 1
    * @param j2CB la JComboBox contenant la nature du joueur 2
    * @param j3CB la JComboBox contenant la nature du joueur 3
    * @param j4CB la JComboBox contenant la nature du joueur 4
    * @param main la MainFrame de l'application
    * @param visuel vrai si la partie doit être chargée en mode visuel, faux si elle c'est en mode texte
    */
    public GraphiqueLauncherListener (JTextField j1TextField , JTextField j2TextField , JTextField j3TextField , JTextField j4TextField , JComboBox j1CB , JComboBox j2CB , JComboBox j3CB , JComboBox j4CB , MainFrame main, boolean visuel) {
      try {
        if (j1TextField == null || j2TextField == null || j3TextField == null || j4TextField == null || j1CB == null || j2CB == null || j3CB == null || j4CB == null || main == null) {
          throw new Exception ("Erreur du constructeur GraphiqueLauncherListener(), parametre null");
        }
        else {
          this.j1TextField = j1TextField;
          this.j2TextField = j2TextField;
          this.j3TextField = j3TextField;
          this.j4TextField = j4TextField;
          this.j1CB = j1CB;
          this.j2CB = j2CB;
          this.j3CB = j3CB;
          this.j4CB = j4CB;
          this.main = main;
          this.visuel = visuel;
          this.ok2 = false;
        }
      }
      catch (Exception e) {
        System.err.println(e.getMessage());
      }
    }

  /**
    * Créé une partie lorsqu'on appuis sur le bouton
    * @param e l'ActionEvent à écouter
    */
    public void actionPerformed(ActionEvent e) {
      Difficulte d1,d2,d3,d4;
      d1 = null;
      d2 = null;
      d3 = null;
      d4 = null;
      if (this.ok2) {
        Mode m;
        String j1Nom , j2Nom;
        String j1 = String.valueOf(this.j1CB.getSelectedItem());
        String j2 = String.valueOf(this.j2CB.getSelectedItem());
        if (j1.trim().equalsIgnoreCase("humain") && j2.trim().equalsIgnoreCase("humain")) {
          m = Mode.HH;
          j1Nom = this.j1TextField.getText().trim();
          j2Nom = this.j2TextField.getText().trim();
        }
        else if (j1.split("-")[0].trim().equalsIgnoreCase("ia") && j2.trim().equalsIgnoreCase("humain")) {
          m = Mode.HI;
          j1Nom = this.j2TextField.getText().trim();
          j2Nom = this.j1TextField.getText().trim();

          d1 = setDiff(j1);
        }
        else if (j1.trim().equalsIgnoreCase("humain") && j2.split("-")[0].trim().equalsIgnoreCase("ia")) {
          m = Mode.HI;
          j1Nom = this.j1TextField.getText().trim();
          j2Nom = this.j2TextField.getText().trim();

          d2 = setDiff(j2);
        }
        else {
          m = Mode.II;
          j1Nom = this.j1TextField.getText().trim();
          j2Nom = this.j2TextField.getText().trim();

          d1 = setDiff(j1);
          d2 = setDiff(j2);
        }
        ArrayList<String> noms = new ArrayList<String>();
        noms.add(j1Nom);
        noms.add(j2Nom);
        if (visuel) {
          this.main.setPartie(new PartieFrame(this.main,new Partie(m,true,noms)));
          this.main.getSwitchableCL().show(this.main.getSwitchablePanel(), "Partie");
        }
        else {
          modeTexte(new Partie(m,false,noms));
        }

        if (d1 != null) {
          IA ia = (IA)this.main.getPartie().getJoueurs().get(0);
          ia.setDifficulte(d1);
        }
        else if (d2 != null) {
          IA ia = (IA)this.main.getPartie().getJoueurs().get(1);
          ia.setDifficulte(d2);
        }
      }
      else {
        Mode m;
        String j1Nom , j2Nom , j3Nom , j4Nom;
        String j1 = String.valueOf(this.j1CB.getSelectedItem());
        String j2 = String.valueOf(this.j2CB.getSelectedItem());
        String j3 = String.valueOf(this.j3CB.getSelectedItem());
        String j4 = String.valueOf(this.j4CB.getSelectedItem());
        if (j1.trim().equalsIgnoreCase("humain") && j2.trim().equalsIgnoreCase("humain") && j3.trim().equalsIgnoreCase("humain") && j4.trim().equalsIgnoreCase("humain")) {
          m = Mode.HHHH;
          j1Nom = this.j1TextField.getText().trim();
          j2Nom = this.j2TextField.getText().trim();
          j3Nom = this.j3TextField.getText().trim();
          j4Nom = this.j4TextField.getText().trim();
        }
        else if (j1.split("-")[0].trim().equalsIgnoreCase("ia") && j2.trim().equalsIgnoreCase("humain") && j3.trim().equalsIgnoreCase("humain") && j4.trim().equalsIgnoreCase("humain")) {
          m = Mode.HHHI;
          j1Nom = this.j4TextField.getText().trim();
          j2Nom = this.j2TextField.getText().trim();
          j3Nom = this.j3TextField.getText().trim();
          j4Nom = this.j1TextField.getText().trim();

          d1 = setDiff(j1);
        }
        else if (j1.trim().equalsIgnoreCase("humain") && j2.split("-")[0].trim().equalsIgnoreCase("ia") && j3.trim().equalsIgnoreCase("humain") && j4.trim().equalsIgnoreCase("humain")) {
          m = Mode.HHHI;
          j1Nom = this.j1TextField.getText().trim();
          j2Nom = this.j4TextField.getText().trim();
          j3Nom = this.j3TextField.getText().trim();
          j4Nom = this.j2TextField.getText().trim();

          d2 = setDiff(j2);
        }
        else if (j1.trim().equalsIgnoreCase("humain") && j2.trim().equalsIgnoreCase("humain") && j3.split("-")[0].trim().equalsIgnoreCase("ia") && j4.trim().equalsIgnoreCase("humain")) {
          m = Mode.HHHI;
          j1Nom = this.j1TextField.getText().trim();
          j2Nom = this.j2TextField.getText().trim();
          j3Nom = this.j4TextField.getText().trim();
          j4Nom = this.j3TextField.getText().trim();

          d3 = setDiff(j3);
        }
        else if (j1.trim().equalsIgnoreCase("humain") && j2.trim().equalsIgnoreCase("humain") && j3.trim().equalsIgnoreCase("humain") && j4.split("-")[0].trim().equalsIgnoreCase("ia")) {
          m = Mode.HHHI;
          j1Nom = this.j1TextField.getText().trim();
          j2Nom = this.j2TextField.getText().trim();
          j3Nom = this.j3TextField.getText().trim();
          j4Nom = this.j4TextField.getText().trim();

          d4 = setDiff(j4);
        }
        else if (j1.trim().equalsIgnoreCase("humain") && j2.trim().equalsIgnoreCase("humain") && j3.split("-")[0].trim().equalsIgnoreCase("ia") && j4.split("-")[0].trim().equalsIgnoreCase("ia")) {
          m = Mode.HHII;
          j1Nom = this.j1TextField.getText().trim();
          j2Nom = this.j2TextField.getText().trim();
          j3Nom = this.j3TextField.getText().trim();
          j4Nom = this.j4TextField.getText().trim();

          d3 = setDiff(j3);
          d4 = setDiff(j4);
        }
        else if (j1.split("-")[0].trim().equalsIgnoreCase("ia") && j2.split("-")[0].trim().equalsIgnoreCase("ia") && j3.trim().equalsIgnoreCase("humain") && j4.trim().equalsIgnoreCase("humain")) {
          m = Mode.HHII;
          j1Nom = this.j3TextField.getText().trim();
          j2Nom = this.j4TextField.getText().trim();
          j3Nom = this.j1TextField.getText().trim();
          j4Nom = this.j2TextField.getText().trim();

          d1 = setDiff(j1);
          d2 = setDiff(j2);
        }
        else if (j1.trim().equalsIgnoreCase("humain") && j2.split("-")[0].trim().equalsIgnoreCase("ia") && j3.split("-")[0].trim().equalsIgnoreCase("ia") && j4.trim().equalsIgnoreCase("humain")) {
          m = Mode.HHII;
          j1Nom = this.j1TextField.getText().trim();
          j2Nom = this.j4TextField.getText().trim();
          j3Nom = this.j3TextField.getText().trim();
          j4Nom = this.j2TextField.getText().trim();

          d3 = setDiff(j3);
          d2 = setDiff(j2);
        }
        else if (j1.split("-")[0].trim().equalsIgnoreCase("ia") && j2.trim().equalsIgnoreCase("humain") && j3.split("-")[0].trim().equalsIgnoreCase("ia") && j4.trim().equalsIgnoreCase("humain")) {
          m = Mode.HHII;
          j1Nom = this.j2TextField.getText().trim();
          j2Nom = this.j4TextField.getText().trim();
          j3Nom = this.j3TextField.getText().trim();
          j4Nom = this.j1TextField.getText().trim();

          d3 = setDiff(j3);
          d1 = setDiff(j1);
        }
        else if (j1.trim().equalsIgnoreCase("humain") && j2.split("-")[0].trim().equalsIgnoreCase("ia") && j3.trim().equalsIgnoreCase("humain") && j4.split("-")[0].trim().equalsIgnoreCase("ia")) {
          m = Mode.HHII;
          j1Nom = this.j1TextField.getText().trim();
          j2Nom = this.j3TextField.getText().trim();
          j3Nom = this.j2TextField.getText().trim();
          j4Nom = this.j4TextField.getText().trim();

          d2 = setDiff(j2);
          d4 = setDiff(j4);
        }
        else if (j1.trim().equalsIgnoreCase("humain") && j2.split("-")[0].trim().equalsIgnoreCase("ia") && j3.split("-")[0].trim().equalsIgnoreCase("ia") && j4.split("-")[0].trim().equalsIgnoreCase("ia")) {
          m = Mode.HIII;
          j1Nom = this.j1TextField.getText().trim();
          j2Nom = this.j2TextField.getText().trim();
          j3Nom = this.j3TextField.getText().trim();
          j4Nom = this.j4TextField.getText().trim();

          d2 = setDiff(j2);
          d3 = setDiff(j3);
          d4 = setDiff(j4);
        }
        else if (j1.split("-")[0].trim().equalsIgnoreCase("ia") && j2.trim().equalsIgnoreCase("humain") && j3.split("-")[0].trim().equalsIgnoreCase("ia") && j4.split("-")[0].trim().equalsIgnoreCase("ia")) {
          m = Mode.HIII;
          j1Nom = this.j2TextField.getText().trim();
          j2Nom = this.j1TextField.getText().trim();
          j3Nom = this.j3TextField.getText().trim();
          j4Nom = this.j4TextField.getText().trim();

          d1 = setDiff(j1);
          d3 = setDiff(j3);
          d4 = setDiff(j4);
        }
        else if (j1.split("-")[0].trim().equalsIgnoreCase("ia") && j2.split("-")[0].trim().equalsIgnoreCase("ia") && j3.trim().equalsIgnoreCase("humain") && j4.split("-")[0].trim().equalsIgnoreCase("ia")) {
          m = Mode.HIII;
          j1Nom = this.j3TextField.getText().trim();
          j2Nom = this.j2TextField.getText().trim();
          j3Nom = this.j1TextField.getText().trim();
          j4Nom = this.j4TextField.getText().trim();

          d2 = setDiff(j2);
          d1 = setDiff(j1);
          d4 = setDiff(j4);
        }
        else if (j1.split("-")[0].trim().equalsIgnoreCase("ia") && j2.split("-")[0].trim().equalsIgnoreCase("ia") && j2.split("-")[0].trim().equalsIgnoreCase("ia") && j4.trim().equalsIgnoreCase("humain")) {
          m = Mode.HIII;
          j1Nom = this.j4TextField.getText().trim();
          j2Nom = this.j2TextField.getText().trim();
          j3Nom = this.j3TextField.getText().trim();
          j4Nom = this.j1TextField.getText().trim();

          d2 = setDiff(j2);
          d3 = setDiff(j3);
          d1 = setDiff(j1);
        }
        else {
          m = Mode.IIII;
          j1Nom = this.j1TextField.getText().trim();
          j2Nom = this.j2TextField.getText().trim();
          j3Nom = this.j3TextField.getText().trim();
          j4Nom = this.j4TextField.getText().trim();

          d1 = setDiff(j1);
          d2 = setDiff(j2);
          d3 = setDiff(j3);
          d4 = setDiff(j4);
        }
        ArrayList<String> noms = new ArrayList<String>();
        noms.add(j1Nom);
        noms.add(j2Nom);
        noms.add(j3Nom);
        noms.add(j4Nom);
        if (visuel) {
          this.main.setPartie(new PartieFrame(this.main,new Partie(m,true,noms)));
          this.main.getSwitchableCL().show(this.main.getSwitchablePanel(), "Partie");
        }
        else {
          modeTexte(new Partie(m,false,noms));
        }

        if (d1 != null) {
          IA ia = (IA)this.main.getPartie().getJoueurs().get(0);
          ia.setDifficulte(d1);
        }
        else if (d2 != null) {
          IA ia = (IA)this.main.getPartie().getJoueurs().get(1);
          ia.setDifficulte(d2);
        }
        else if (d3 != null) {
          IA ia = (IA)this.main.getPartie().getJoueurs().get(2);
          ia.setDifficulte(d3);
        }
        else if (d4 != null) {
          IA ia = (IA)this.main.getPartie().getJoueurs().get(3);
          ia.setDifficulte(d4);
        }
      }
    }

    /**
      * Attribut une valeur à la difficulté d'une IA à partir de son label
      * @param j la chaine de caractère décrivant l'IA
      * @return la valeur de la difficulté
      */
      public Difficulte setDiff (String j) {
        Difficulte ret = null;
        if (j.split("-")[1].trim().equalsIgnoreCase("facile")) {
          ret = Difficulte.FACILE;
        }
        else if (j.split("-")[1].trim().equalsIgnoreCase("moyenne")) {
          ret = Difficulte.MOYEN;
        }
        else if (j.split("-")[1].trim().equalsIgnoreCase("difficile")) {
          ret = Difficulte.DIFFICILE;
        }
        return ret;
      }

    public void modeTexte(Partie laPartie) {
      try {
        RWFile.writeFile("sauvegardeTMP", laPartie.getJoueurs(), laPartie.getBarrieres(), 1, laPartie.getJoueurs().get(0), false);
        this.main.getSwitchableCL().show(this.main.getSwitchablePanel(), "Instructions");
      }
      catch(Exception e) {
        System.out.println(e.getMessage());
      }
    }
}
