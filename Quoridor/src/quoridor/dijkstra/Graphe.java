package quoridor.dijkstra;
import quoridor.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

/**
  * La classe Graphe nous permet de créer un graphe
  * A partir d'un tableau de booléens
  * @author griffin568 , Drmarsupial35
  * @version 0.1.0
  */
public class Graphe {

  private ArrayList<Noeud> noeuds;

  /**
    * Créé un nouvel objet de type Graphe
    */
    public Graphe() {
      noeuds = new ArrayList<Noeud>();
    }

  /**
    * Ajoute un noeud au graphe
    * @param noeud le noeud à ajouter
    */
    public void addNoeud (Noeud noeud) {
      try {
        if (noeud == null) {
          throw new Exception("Erreur addNoeud(), parametre null");
        }
        else {
          noeuds.add(noeud);
        }
      }
      catch (Exception e) {
        System.err.println(e.getMessage());
      }
    }

  /**
    * Donne la liste des noeuds du graphe
    * @return l'attribut noeuds
    */
    public ArrayList<Noeud> getNoeuds() {
      return this.noeuds;
    }

  /**
    * Donne à l'attribut noeuds une nouvelle valeur
    * @param noeuds la nouvelle valeur
    */
    public void setNoeuds (ArrayList<Noeud> noeuds) {
      try {
        if (noeuds == null) {
          throw new Exception ("Erreur setNoeuds(), parametre null");
        }
        else {
          this.noeuds = noeuds;
        }
      }
      catch (Exception e) {
        System.err.println(e.getMessage());
      }
    }

  /**
    * Calcule le plus court chemin à partir d'un noeud source
    * en utilisant l'algorithme de Dijkstra
    * @param graphe le Graphe à utiliser
    * @param source le noeud source
    */
    public static Graphe dijkstra (Graphe graphe , Noeud source) {
      source.setDistance(0);
      ArrayList<Noeud> marque = new ArrayList<Noeud>();
      ArrayList<Noeud> nonMarque = new ArrayList<Noeud>();
      nonMarque.add(source);
      while (nonMarque.size() != 0) {
        Noeud actuel = getPlusProche(nonMarque);
        nonMarque.remove(actuel);
        for (Entry<Noeud,Integer> adj : actuel.getAdjacents().entrySet()) {
          Noeud noeudAdjacent = adj.getKey();
          Integer d = adj.getValue();
          if (!marque.contains(noeudAdjacent)) {
            calculMinDistance(noeudAdjacent,d,actuel);
            nonMarque.add(noeudAdjacent);
          }
        }
        marque.add(actuel);
      }
      return graphe;
    }

  /**
    * Donne le noeud le plus proche parmi les noeuds non marqués
    * @param nonMarque la liste des noeuds non marqués
    */
    private static Noeud getPlusProche (ArrayList<Noeud> nonMarque) {
      Noeud ret = null;
      int minDistance = Integer.MAX_VALUE;
      for (Noeud noeud : nonMarque) {
        int distance = noeud.getDistance();
        if (distance < minDistance) {
          minDistance = distance;
          ret = noeud;
        }
      }
      return ret;
    }

  /**
    * Compare une la distance actuelle avec celle nouvellement calculée
    * @param noeudEvalue le noeud évalué
    * @param d la distance vers ce noeud
    * @param source le noeud source
    */
    private static void calculMinDistance (Noeud noeudEvalue , Integer d , Noeud source) {
      Integer distanceSource = source.getDistance();
      if (distanceSource + d < noeudEvalue.getDistance()) {
        noeudEvalue.setDistance(distanceSource + d);
        ArrayList<Noeud> plusCourtChemin = new ArrayList<Noeud>();
        for (Noeud n : source.getPlusCourtChemin()) {
          plusCourtChemin.add(n);
        }
        plusCourtChemin.add(source);
        noeudEvalue.setPlusCourtChemin(plusCourtChemin);
      }
    }

  public static void main(String[] args) {
    Noeud noeudA = new Noeud("A");
    Noeud noeudB = new Noeud("B");
    Noeud noeudC = new Noeud("C");
    Noeud noeudD = new Noeud("D");
    Noeud noeudE = new Noeud("E");

    noeudA.addNoeudAdjacent(noeudB,12);
    noeudA.addNoeudAdjacent(noeudD,2);

    noeudB.addNoeudAdjacent(noeudA,12);
    noeudB.addNoeudAdjacent(noeudC,4);

    noeudC.addNoeudAdjacent(noeudB,4);
    noeudC.addNoeudAdjacent(noeudE,6);

    noeudD.addNoeudAdjacent(noeudE,10);
    noeudD.addNoeudAdjacent(noeudA,2);

    noeudE.addNoeudAdjacent(noeudC,6);
    noeudE.addNoeudAdjacent(noeudD,10);


    Graphe graphe = new Graphe();

    graphe.addNoeud(noeudA);
    graphe.addNoeud(noeudB);
    graphe.addNoeud(noeudC);
    graphe.addNoeud(noeudD);
    graphe.addNoeud(noeudE);

    graphe = Graphe.dijkstra(graphe,noeudA);
    for (Noeud n : graphe.getNoeuds()) {
      for (Noeud n2 : n.getPlusCourtChemin()) {
        System.out.print(n2.getNom() + "   " + n2.getDistance() + " | ");
      }
      System.out.print(n.getNom() + "   " + n.getDistance());
      System.out.println();
    }
  }
}
