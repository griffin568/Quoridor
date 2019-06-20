package quoridor.dijkstra;
import quoridor.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
  * La classe Noeud permet de transformer une case de notre damier en Noeud
  * @author griffin568 , Drmarsupial35
  * @version 0.1.0
  */
public class Noeud {

  private String nom;
  private ArrayList<Noeud> plusCourtChemin;
  private Integer distance = Integer.MAX_VALUE;
  private HashMap<Noeud , Integer> adjacents;

  /**
    * Créé un nouveau Noeud sans paramètres autres que son nom
    * @param nom le nom du noeud
    */
    public Noeud (String nom) {
      try {
        if (nom == null) {
          throw new Exception ("Erreur du constructeur Noeud(), parametre null");
        }
        else {
          this.nom = nom;
          this.plusCourtChemin = new ArrayList<Noeud>();
          this.adjacents = new HashMap<Noeud , Integer>();
        }
      }
      catch (Exception e) {
        System.err.println(e.getMessage());
      }
    }

  /**
    * Créé un nouveau Noeud en passant les noeuds adjacents en paramètre
    * @param nom le nom du noeud
    * @param adjacents la carte des noeuds adjacents
    */
    public Noeud (String nom , HashMap<Noeud , Integer> adjacents) {
      try {
        if (nom == null || adjacents == null) {
          throw new Exception ("Erreur du constructeur Noeud(), parametre null");
        }
        else {
          this.nom = nom;
          this.plusCourtChemin = new ArrayList<Noeud>();
          this.adjacents = adjacents;
        }
      }
      catch (Exception e) {
        System.err.println(e.getMessage());
      }
    }

  /**
    * Ajoute un nouveau noeud adjacent
    * @param noeud le noeud adjacent
    * @param distance sa distance par rapport à ce noeud
    */
    public void addNoeudAdjacent (Noeud noeud , int distance) {
      try {
        if (noeud == null || distance < 0) {
          throw new Exception ("Erreur addNoeudAdjacent(), parametre invalide");
        }
        else {
          this.adjacents.put(noeud,distance);
        }
      }
      catch (Exception e) {
        System.err.println(e.getMessage());
      }
    }

  /**
    * Donne le nom du noeud
    * @return le nom du noeud
    */
    public String getNom() {
      return this.nom;
    }

  /**
    * Donne le plus court chemin identifié depuis ce noeud
    * @return le plus court chemin
    */
    public ArrayList<Noeud> getPlusCourtChemin() {
      return this.plusCourtChemin;
    }

  /**
    * Donne la distance du noeud par rapport à la source
    * @return la distance par rapport à la source
    */
    public Integer getDistance() {
      return this.distance;
    }

  /**
    * Donne la carte des noeuds adjacents à celui-ci
    * @return la carte des noeuds adjacents
    */
    public HashMap<Noeud,Integer> getAdjacents() {
      return this.adjacents;
    }

  /**
    * Donne un nouveau nom au noeud
    * @param nom le nouveau nom
    */
    public void setNom (String nom) {
      try {
        if (nom == null) {
          throw new Exception ("Erreur setNom(), parametre null");
        }
        else {
          this.nom = nom;
        }
      }
      catch (Exception e) {
        System.err.println(e.getMessage());
      }
    }

  /**
    * Remplace la liste contenant le plus court chemin par une nouvelle
    * @param plusCourtChemin la nouvelle liste
    */
    public void setPlusCourtChemin (ArrayList<Noeud> plusCourtChemin) {
      try {
        if (plusCourtChemin == null) {
          throw new Exception("Erreur setPlusCourtChemin(), parametre null");
        }
        else {
          this.plusCourtChemin = plusCourtChemin;
        }
      }
      catch (Exception e) {
        System.err.println(e.getMessage());
      }
    }

  /**
    * Donne une nouvelle valeur à la distance
    * @param distance la nouvelle distance
    */
    public void setDistance (int distance) {
      try {
        if (distance < 0) {
          throw new Exception ("Erreur setDistance(), parametre invalide");
        }
        else {
          this.distance = distance;
        }
      }
      catch (Exception e) {
        System.err.println(e.getMessage());
      }
    }

  /**
    * Remplace la carte des noeuds adjacents par une nouvelle
    * @param adjacents la nouvelle carte
    */
    public void setAdjacents (HashMap<Noeud,Integer> adjacents) {
      try {
        if (adjacents == null) {
          throw new Exception("Erreur setAdjacents(), parametre null");
        }
        else {
          this.adjacents = adjacents;
        }
      }
      catch (Exception e) {
        System.err.println(e.getMessage());
      }
    }

}
