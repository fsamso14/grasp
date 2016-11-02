/*
 * @author Fabian SAMSON
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class ListeBoites.
 */
public class ListeBoites {
	
	/** The liste. */
	private List<Boite> liste;	// liste de boîtes
	
	/**
	 * Instantiates a new liste boites.
	 */
	// constructeur par défaut (liste vide)
	public ListeBoites() {
		liste = new ArrayList<Boite>();
	}
	
	/**
	 * Gets the nb boites.
	 *
	 * @return the nb boites
	 */
	public int getNbBoites() {
		return liste.size();
	}

	/**
	 * Gets the boite.
	 *
	 * @param i the i
	 * @return the boite
	 */
	// renvoie la boîte d'indice i
	public Boite getBoite(int i) {
		return liste.get(i-1);	// les listes démarrent à l'indice 0 mais les Boites à l'indice 1
	}
	
	/**
	 * Gets the liste.
	 *
	 * @return the liste
	 */
	public List<Boite> getListe(){
		return this.liste;
	}
	
	/**
	 * Sets the boite.
	 *
	 * @param b the new boite
	 */
	public void setBoite(Boite b) {
		liste.add(b);
	}
	
	/**
	 * Copie.
	 *
	 * @return the liste boites
	 */
	public ListeBoites copie() {
		ListeBoites l = new ListeBoites();
		int n = getNbBoites();
		for (int i = 1; i <= n; i++) {
			Boite b = getBoite(i).copie();
			l.setBoite(b);
		}
		return l;
	}

	/**
	 * Trier places libres croissantes.
	 */
	// trie la liste selon les places libres croissantes
    public void trierPlacesLibresCroissantes() {
        Collections.sort(liste);
    }
    
    /**
     * Trier places libres decroissantes.
     */
    public void trierPlacesLibresDecroissantes(){
    	this.trierPlacesLibresCroissantes();
    	Collections.reverse(liste);
    }
    

    /**
     * Afficher.
     */
    public void afficher() {
    	int n = getNbBoites();
        for (int i = 1; i <= n; i++) {
        	Boite b = getBoite(i);
        	b.afficher();
        	System.out.println();
        }
	}

	// EXO 2
	/**
	 * Ajouter boite.
	 *
	 * @param capacite the capacite
	 */
	// ajoute une boîte vide
	public void ajouterBoite(int capacite) {
		this.setBoite(new Boite(this.getNbBoites()+1,capacite));
	}
	
}
