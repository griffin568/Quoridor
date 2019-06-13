package quoridor;

/**
  * Cette classe gère les barrières utilisées par les joueurs
  * @author AlexM02 , Drmarsupial35 , Eclixal , griffin568
  * @version 0.1.0
  */
public class Barriere {

    private final String COULEUR;
    private Coordonnee coordonnee;

    /**
      * Créé un nouvel objet Barriere
      * @param couleur la couleur du joueur
      * @param coordonnee les coordonnées de la barrière sur le plateau (null si non posée)
      */
    public Barriere(String couleur, Coordonnee coordonnee) {
      try {
        if (couleur == null) {
          throw new Exception("Barriere constructeur - La couleur de la barrière doit exister.")
        }
        else if (coordonnee == null) {
          throw new Exception("Barriere constructeur - La barrière doit posséder des coordonnées valides.")
        }
        else {
          this.COULEUR = couleur;
          this.coordonnee = coordonnee;
        }
      }
      catch(Exception e) {
        System.err.println(e.getMessage());
      }
    }

    /**
      * Retourne la couleur de la barrière
      * @return la couleur de la barrière
      */
    public String getCouleur() {
        return COULEUR;
    }


    /**
      * Retourne les coordonnées de la barrière
      * @return les coordonnées de la barrière sous la forme d'un objet Coordonnee
      */
    public Coordonnee getCoordonnee() {
        return coordonnee;
    }

    /**
      * Modifie les coordonnées de la barrière
      * @param coordonnee les nouvelles coordonnées de la barrière
      */
    public void setCoordonnee(Coordonnee coordonnee) {
      try {
        if (coordonnee == null) {
          throw new Exception("Barriere setCoordonnee() - Les coordonnees a changer doivent exister.");
        }
        else {
          this.coordonnee = coordonnee;
        }
      }
      catch(Exception e) {
        System.err.println();
      }
    }
}
