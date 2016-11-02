/**
 * The Class Boite.
 */
public class Boite implements java.lang.Comparable {
	
	/** The numero. */
	private int numero;				// identificateur de la bo�te
	
	/** The capacite. */
	private int capacite;			// capacit� de la bo�te
	
	/** The place libre. */
	private int placeLibre;			// taille de l'espace restant (= capacit� lorsque la bo�te est vide)
	
	/** The liste. */
	private ListeElements liste;	// liste des �l�ments plac�s dans la bo�te
	
	/**
	 * Instantiates a new boite.
	 */
	// constructeur par d�faut (bo�te fictive)
	public Boite() {
		numero = 0;
		capacite = 0;
		placeLibre = 0;
		liste = new ListeElements();	// cr�ation d'une liste vide d'�l�ments	
	}

	/**
	 * Instantiates a new boite.
	 *
	 * @param numero the numero
	 * @param capacite the capacite
	 */
	// constructeur 2 (bo�te vide)
	public Boite(int numero, int capacite) {
		this.numero = numero;
		this.capacite = capacite;
		placeLibre = capacite;
		liste = new ListeElements();	// cr�ation d'une liste vide d'�l�ments	
	}

	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public int getNumero() {
		return numero;
	}
		
	/**
	 * Gets the capacite.
	 *
	 * @return the capacite
	 */
	public int getCapacite() {
		return capacite;
	}

	/**
	 * Gets the place libre.
	 *
	 * @return the place libre
	 */
	public int getPlaceLibre() {
		return placeLibre;
	}

	/**
	 * Sets the place libre.
	 *
	 * @param p the new place libre
	 */
	public void setPlaceLibre(int p) {
		placeLibre = p;
	}

	/**
	 * Gets the nb elements.
	 *
	 * @return the nb elements
	 */
	public int getNbElements() {
		return liste.getNbElements();
	}
		
	/**
	 * Gets the liste.
	 *
	 * @return the liste
	 */
	public ListeElements getListe() {
		return liste;
	}

	/**
	 * Sets the liste.
	 *
	 * @param l the new liste
	 */
	public void setListe(ListeElements l) {
		liste = l;
	}

	/**
	 * Gets the element.
	 *
	 * @param i the i
	 * @return the element
	 */
	// renvoie l'�l�ment d'indice i
	public Element getElement(int i) {
		return liste.getElement(i);
	}

	/**
	 * Supprimer element.
	 *
	 * @param n the n
	 */
	// supprime l'�l�ment de num�ro n
	public void supprimerElement(int n) {
		int ne = getNbElements();
		for (int i = 1; i <= ne; i++) {
			if (getElement(i).getNumero() == n) {
				placeLibre += getElement(i).getTaille();
				liste.supprimerElement(i);
				break;
			}
		}
	}

	/**
	 * Copie.
	 *
	 * @return the boite
	 */
	public Boite copie() {
		ListeElements l = liste.copie();
		Boite b = new Boite(numero, capacite);
		b.setListe(l);
		b.setPlaceLibre(placeLibre);
		return b;
	}
    
    /**
     * Afficher.
     */
    // affiche le contenu de la bo�te : num�ro (capacit� occuppation/capacit� bo�te) : num�ros des �l�ments plac�s
	public void afficher() { 	
    	System.out.print("Boite " + numero + " (" + (capacite-placeLibre) + "/" + capacite + ") : ");
    	int n = getNbElements();
        for (int i = 1; i <= n; i++) {
        	Element e = getElement(i);
        	System.out.print(e.getNumero() + " ");
        }
    }

    /* (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    /* on a besoin de d�finir un ordre pour utiliser les fonctions 
    * "sort()" et "reverseOrder()" de Java.
    * On utilise la place libre des bo�tes pour les comparer
    */
    public int compareTo(Object obj) {
    	int nombre1 = ((Boite) obj).getPlaceLibre();
    	int nombre2 = placeLibre;
    	if (nombre1 > nombre2) {
    		return -1;
    	}
    	else if (nombre1 == nombre2) {
    		return 0;
    	}
    	else {
    		return 1;
    	}
    }

    // EXO 1
	/**
     * Ajouter element.
     *
     * @param e the e
     */
    // Attention : on suppose qu'il y a la place pour mettre l'�l�ment
    public void ajouterElement(Element e) {
		this.getListe().ajouterElement(e);
		this.setPlaceLibre(this.getPlaceLibre()-e.getTaille());

    }


}
