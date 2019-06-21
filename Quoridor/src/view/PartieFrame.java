package view;

import quoridor.*;
import controller.*;
import javax.swing.*;
import java.awt.*;

public class PartieFrame extends JPanel {

  private MainFrame mainF;
  private Partie laPartie;

  /**
    * Créé une page de jeu vide. Ce constructeur est utilisé pour permettre son utilisation future dans d'autres classes.
    */
  public PartieFrame() {}

  public PartieFrame(MainFrame parent, Mode leMode) {
    try {
      if (parent == null) {
        throw new Exception("L'écran principal doit exister pour créer l'écran de de jeu");
      }
      else if (leMode == null) {
        throw new Exception("La difficulté doit être valide pour pouvoir créer l'écran de jeu.");
      }
      else {
        this.mainF = parent;
        this.laPartie = new Partie(leMode);
        this.setBackground(Color.BLACK);
        initComponent();
      }
    }
    catch(Exception e) {
      System.err.println(e.getMessage());
    }
  }

  public PartieFrame(MainFrame parent, Partie laPartie) {
    try {
      if (parent == null) {
        throw new Exception("L'écran principal doit exister pour créer l'écran de de jeu");
      }
      else if (laPartie == null) {
        throw new Exception("La partie doit exister pour créer l'écran de jeu.");
      }
      else {
        this.mainF = parent;
        this.setBackground(Color.BLACK);
        initComponent();
      }
    }
    catch(Exception e) {
      System.err.println(e.getMessage());
    }
  }

    private void initComponent() {
      this.setLayout(new GridLayout(17,17));
      Plateau plateau = this.laPartie.getPlateau();
      boolean[][] damier = plateau.getDamier();
      JButton[][] damierB = new JButton[17][17];
      final String PATH = "../data/img/";

      //Création des différents buttons
      for (int i = 0; i < damier.length - 1; i++) {
        for (int j = 0; j < damier[i].length - 1; j++) {
          if ((i % 2 == 1) || (j % 2 == 1)) {
            //JButton des murs
            damierB[i][j] = new JButton(new ImageIcon(PATH + "brick.jpg"));
          }
          else {
            //JButton des cases
            damierB[i][j] = new JButton(new ImageIcon(PATH + "brick2.jpg"));
          }
          damierB[i][j].addKeyListener(new PartiePauseListener(this.mainF));
          damierB[i][j].setOpaque(false);
          damierB[i][j].setContentAreaFilled(false);
          damierB[i][j].setBorderPainted(false);
          this.add(damierB[i][j]);
        }
      }
    }

    /**
     * GridTableModel : the graphical table
    public class GridTableModel extends AbstractTableModel {

      private int noOfRows, noOfCols;
      private Square[][] grid;
      private static final String PATH = "../data/";    // the folder
      // the image are directly created from the files
      private String imageFree= "blue.png";
      private String imageWhite= "pion-blanc.png";
      private String imageBlack= "pion-noir.png";
      private String imageNone="gris.png";
      */

     /*
      * Constructor
      * @param grid : the table to display
      public GridTableModel(Square[][] grid) {
        this.grid = grid;
        noOfRows = this.grid.length;
        noOfCols = this.grid[0].length;
      }

    // Implementing the tree inherited abstract methods:

       public int getRowCount() {
        return(noOfRows);
      }
      public int getColumnCount() {
        return(noOfCols);
      }

      public Object getValueAt(int r,int c) {
        Object result = new Object();
        Square sq = grid[r][c];
        if (sq.isFree()) result= new ImageIcon(PATH + imageFree);
        else if (sq.isForbidden()) result= new ImageIcon(PATH + imageNone);
        else if (sq.getColor() ==CoinColor.WHITE) result= new ImageIcon(PATH + imageWhite); // couleur
        else result= new ImageIcon(PATH + imageBlack);
        return result;
      }

      */

      /**
       * get the name of the column
       * @param c : the number of the column
       * @return a String for the name of the column
      public String getColumnName(int c){
        return (new Integer(c).toString());
      }
      */

       /**
       * get the class of the object at column c
       * @param c : the number of the column
       * @return the class of the object at column c
       public Class getColumnClass(int c) {
          return this.getValueAt(0, c).getClass();
       }
    }


    */
}
