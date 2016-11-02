/**
 * The Class Element.
 */
public class Element implements java.lang.Comparable {	
	/** The numero. */
	// on va utiliser les comparateurs Java 
	private int numero; // identificateur de l'ŽlŽment
	
	/** The taille. */
	private int taille;	// taille de l'ŽlŽment

	/**
	 * Instantiates a new element.
	 */
	// constructeur par dŽfaut (crŽe un ŽlŽment fictif)
	public Element() {
		numero = 0;
		taille = 0;
	}
	
	/**
	 * Instantiates a new element.
	 *
	 * @param numero the numero
	 * @param taille the taille
	 */
	// constructeur 2
	public Element(int numero, int taille) {
		this.numero = numero;
		this.taille = taille;
	}
	
	/**
	 * Instantiates a new element.
	 *
	 * @param e the e
	 */
	public Element(Element e){
		this.numero=e.getNumero();
		this.taille=e.getTaille();
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
	 * Gets the taille.
	 *
	 * @return the taille
	 */
	public int getTaille() {
		return taille;
	}

    /* (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    /* on a besoin de dŽfinir un ordre pour utiliser les fonctions 
    * "sort()" et "reverseOrder()" de Java.
    * On utilise la taille des ŽlŽments pour les comparer
    */
    public int compareTo(Object obj) {
    	int nombre1 = ((Element) obj).getTaille();
    	int nombre2 = taille;
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

}
