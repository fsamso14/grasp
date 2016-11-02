/*
 * @author Fabian SAMSON
 */

import java.util.*;

// TODO: Auto-generated Javadoc
/**
 * The Class ListeElements.
 */
public class ListeElements {
	
	/** The liste. */
	private List<Element> liste;	// liste d'éléments
	
	/**
	 * Instantiates a new liste elements.
	 */
	// constructeur par défaut (liste vide)
	public ListeElements() {
		liste = new ArrayList<Element>();	// création d'une liste vide	
	}
	
	/**
	 * Gets the nb elements.
	 *
	 * @return the nb elements
	 */
	public int getNbElements() {
		return liste.size();
	}

	/**
	 * Gets the element.
	 *
	 * @param i the i
	 * @return the element
	 */
	// renvoie l'élément d'indice i
	public Element getElement(int i) {
		return liste.get(i-1);	// les listes démarrent à l'indice 0 mais les éléments à l'indice 1
	}
	
	/**
	 * Ajouter element.
	 *
	 * @param e the e
	 */
	public void ajouterElement(Element e) {
		liste.add(e);
	}
	
	/**
	 * Supprimer element.
	 *
	 * @param i the i
	 */
	// supprime l'élément d'indice i
	public void supprimerElement(int i) {
		liste.remove(i-1);	// les indices commencent à 0 dans une liste
	}
	
	/**
	 * Supprimer element.
	 *
	 * @param e the e
	 */
	public void supprimerElement(Element e){
		boolean test=true;
		for(int i = 0; i < this.getNbElements() && test; i++){
			if(liste.get(i).getNumero() == e.getNumero()){
				liste.remove(i);
				test = false;
			}
		}
	}
	
	/**
	 * Copie.
	 *
	 * @return the liste elements
	 */
	public ListeElements copie() {
		ListeElements l = new ListeElements();
		int n = getNbElements();
		for (int i = 1; i <= n; i++) {
			Element e = getElement(i);
			Element e2 = new Element(e.getNumero(), e.getTaille());
			l.ajouterElement(e2);
		}
		return l;
	}
	
	/**
	 * Trier tailles decroissantes.
	 */
	// trie la liste selon les tailles décroissantes
    public void trierTaillesDecroissantes() {
        Collections.sort(liste, Collections.reverseOrder());
    }

	/**
	 * Afficher.
	 */
	public void afficher() {
    	System.out.print("( ");
    	int n = getNbElements();
        for (int i = 1; i <= n; i++) {
        	Element e = getElement(i);
        	System.out.print(e.getNumero() + ",");
        }
    	System.out.print(")");
	}
}
