/*
 * @author Fabian SAMSON
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The Class Grasp.
 */
/*
 * class Grasp
 * Classe d'éxécution de la méthode Grasp implémentée pour le cours de RO2.
 */
public class Grasp {
	
	/** The bp. */
	public BinPacking bp;
	
	/** The borne inf. */
	public int borneInf;
	
	/** The soluce. */
	public int soluce;
	
	/** The capacite. */
	public int capacite;
	
	/**
	 * Instantiates a new grasp.
	 */
	public Grasp(){}
	
	/** The count. */
	public int count;
	
	/**
	 * Instantiates a new grasp.
	 *
	 * @param bp the bp
	 */
	public Grasp(BinPacking bp){
		this.bp = bp;
		this.borneInf = bp.borneInf(new ListeBoites(),bp.getElements());
		this.bp.setSolution(this.bp.algoFirstFit(this.bp.getElements()));
		this.soluce = this.bp.getNbBoites();	
		this.capacite = this.bp.getCapacite();
	}
	
	/**
	 * Grasp.
	 *
	 * @param alpha the alpha
	 */
	/*
	 * grasp() fait appel à la méta-heuristique Grasp
	 * @param alpha : Critère de sélection
	 */
	public void grasp(double alpha) {
		count = 0;
		bp.setSolution(bp.algoBestFit(bp.getElements()));
		long debut = System.currentTimeMillis(); 
		long fin = System.currentTimeMillis();
		while (fin < debut + 60000) {
		ListeBoites lb = this.construction(alpha);
		this.recherche_local1(lb);
		this.recherche_local2(lb);
		this.recherche_local3(lb);
		this.recherche_local1(lb);
		this.recherche_local2(lb);
		this.recherche_local3(lb);
		this.recherche_local1(lb);
		this.recherche_local2(lb);
		this.recherche_local3(lb);
		this.recherche_local1(lb);
		this.recherche_local2(lb);
		this.recherche_local3(lb);
		lb.trierPlacesLibresDecroissantes();
		if(lb.getNbBoites()<=this.soluce){
			if(lb.getBoite(1).getNbElements() < this.bp.getSolution().getBoite(1).getNbElements())
			this.soluce=lb.getNbBoites();
			this.bp.setSolution(lb);
		}
		fin = System.currentTimeMillis();
		}
	}
	
	/**
	 * Construction.
	 *
	 * @param alpha the alpha
	 * @return the liste boites
	 */
	/*
	 * Permet de construire une solution
	 * @param alpha : Critère de sélection
	 * @return lb : liste de boites solution
	 * 
	 */
	public ListeBoites construction(double alpha){
		ListeElements le = bp.getElements().copie();
		ListeElements le2 = le.copie();
		ListeBoites lb = new ListeBoites();
		lb.ajouterBoite(capacite);
		List<Element> rcl = new ArrayList<Element>();
		/*List<Integer> index = new ArrayList<Integer>();
		for(int i = 1; i < le.getNbElements()+1;i++){
			index.add(i);
		}
		Collections.shuffle(index);*/
		int nb = 0;
		le2.trierTaillesDecroissantes();
		for(int i = 1; i < le.getNbElements(); i++){
			lb.getBoite(1).ajouterElement(le2.getElement(i));
			Element e = le2.getElement(i);
			le.supprimerElement(e);
			if(e.getTaille() >= le2.getElement(1).getTaille() - alpha*(le2.getElement(1).getTaille() - le2.getElement(le2.getNbElements()).getTaille())&& nb < 70){
				nb++;
				rcl.add(e);			
			}
			le.ajouterElement(e);
			lb.getBoite(1).supprimerElement(e.getNumero());
		}
		int random = (int) (Math.random() * rcl.size());
		Element retenu = rcl.get(random);		
		le.supprimerElement(retenu);
		lb.getBoite(1).ajouterElement(retenu);
		count++;
		this.construction_recursif(lb,le,alpha);
		le=le2;
		return lb;
	}
	
	/**
	 * Recherche local 1.
	 *
	 * @param lb the lb
	 */
	public void recherche_local1(ListeBoites lb){
		lb.trierPlacesLibresDecroissantes();
		for(int i=2;i<lb.getNbBoites()-1;i++){// je prends une boite i différente de la boite 1
			for(int j=1;j<=lb.getBoite(i).getNbElements();j++){// pour chacun de ses éléments, je commence par en sélectionner un
				for(int m=i+1;m<=lb.getNbBoites();m++){// je regarde dans les autres boites que la 1 et que la i -> modifier les conditions de la boucle
					if(j < lb.getBoite(i).getNbElements() && lb.getBoite(i).getElement(j).getTaille()<=lb.getBoite(m).getPlaceLibre()){ // si je peux ajouter cet element
						lb.getBoite(m).ajouterElement(lb.getBoite(i).getElement(j));
						lb.getBoite(i).supprimerElement(lb.getBoite(i).getElement(j).getNumero());
					}
				}
			}
			for(int k=1;k<lb.getBoite(1).getNbElements();k++){// une fois ajouter je regarde si je peux vider un des éléments de la boite 1 dans la boite i
				if(lb.getBoite(1).getElement(k).getTaille()<=lb.getBoite(i).getPlaceLibre()){
					lb.getBoite(i).ajouterElement(lb.getBoite(1).getElement(k));
					lb.getBoite(1).supprimerElement(lb.getBoite(1).getElement(k).getNumero());
				}
			}
		}		
	}
	
	/**
	 * Recherche local 2.
	 *
	 * @param lb the lb
	 */
	public void recherche_local2(ListeBoites lb){
		lb.trierPlacesLibresDecroissantes();
		for(int i = 2; i < lb.getNbBoites() - 1 ; i++){
			for(int j = 1; j <= lb.getBoite(i).getNbElements(); j++){
				for(int m = i+1; m <= lb.getNbBoites();m++){
					for( int n = 1; n <= lb.getBoite(m).getNbElements();n++){
						if(lb.getBoite(i).getElement(j).getTaille() > lb.getBoite(m).getElement(n).getTaille() && (lb.getBoite(m).getPlaceLibre() + lb.getBoite(m).getElement(n).getTaille()) > lb.getBoite(i).getElement(j).getTaille()){
							lb.getBoite(i).ajouterElement(lb.getBoite(m).getElement(n));
							lb.getBoite(m).ajouterElement(lb.getBoite(i).getElement(j));
							lb.getBoite(i).supprimerElement(lb.getBoite(i).getElement(j).getNumero());
							lb.getBoite(m).supprimerElement(lb.getBoite(m).getElement(n).getNumero());
						}
					}
				}
			}
			for(int k=1;k<lb.getBoite(1).getNbElements();k++){// une fois ajouter je regarde si je peux vider un des éléments de la boite 1 dans la boite i
				if(lb.getBoite(1).getElement(k).getTaille()<=lb.getBoite(i).getPlaceLibre()){
					lb.getBoite(i).ajouterElement(lb.getBoite(1).getElement(k));
					lb.getBoite(1).supprimerElement(lb.getBoite(1).getElement(k).getNumero());
				}
			}
		}
	}
	
	/**
	 * Recherche local 3.
	 *
	 * @param lb the lb
	 */
	public void recherche_local3(ListeBoites lb){
		lb.trierPlacesLibresDecroissantes();
		boolean test =true;
		int i = 2;
		while(test){
			lb.trierPlacesLibresDecroissantes();
			test = lb.getBoite(i).getPlaceLibre() > lb.getBoite(1).getElement(1).getTaille();
			if(test){
				lb.getBoite(i).ajouterElement(lb.getBoite(1).getElement(1));
				lb.getBoite(1).supprimerElement(lb.getBoite(1).getElement(1).getNumero());
				//i++;
				if(lb.getBoite(1).getNbElements() == 0){
					lb.getListe().remove(0);
				}
			}
		}
	}
	
	/**
	 * Construction recursif.
	 *
	 * @param lb the lb
	 * @param le the le
	 * @param alpha the alpha
	 */
	public void construction_recursif(ListeBoites lb, ListeElements le,double alpha){
		ListeElements le2 = le.copie();
		List<Element> rcl = new ArrayList<Element>();
		/*List<Integer> index = new ArrayList<Integer>();
		/*for(int i = 1; i < le.getNbElements()+1;i++){
			index.add(i);
		}
		Collections.shuffle(index);*/
		int nb = 0;
		le2.trierTaillesDecroissantes();
		for(int i =1; i < le2.getNbElements()+1; i++){
			Element e = le2.getElement(i);
			if(e.getTaille() >= le2.getElement(1).getTaille() - alpha*(le2.getElement(1).getTaille() - le2.getElement(le2.getNbElements()).getTaille())&& nb < 70){
				nb++;
				rcl.add(e);
			}
		}
		int random = (int) (Math.random() * rcl.size());
		Element retenu = rcl.get(random);
		le.supprimerElement(retenu);
		count++;
		this.ajouter_best_fit(retenu,lb);
		if(le.getNbElements()!=1){
			this.construction_recursif(lb,le,alpha);
		}
		else{
			le.trierTaillesDecroissantes();
			this.ajouter_best_fit(le.getElement(1), lb);
		}
	}
	
	/**
	 * Ajouter best fit.
	 *
	 * @param e the e
	 * @param lb the lb
	 */
	public void ajouter_best_fit(Element e, ListeBoites lb){
		double occupation=0;
		double bestOccupation=0;
		int numBoite=0;
		boolean test=true;
		int index=1;
		do{
			occupation= ((double)e.getTaille())/((double)lb.getBoite(index).getPlaceLibre());
			if(occupation <=1 && occupation > bestOccupation){
				bestOccupation = occupation;
				numBoite=index;
				if(index == lb.getNbBoites()){
					test=false;
					lb.getBoite(index).ajouterElement(e);
				}
				else{
					index++;
				}
			}
			else{
				if(index==lb.getNbBoites()){
					if(numBoite==0){
						test=false;
						lb.ajouterBoite(capacite);
						lb.getBoite(index+1).ajouterElement(e);
					}
					else{
						lb.getBoite(numBoite).ajouterElement(e);
						test=false;
						
					}
				}
				else{
					index++;
				}
			}
		}while (test);
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		Grasp n1w1b1r0 = new Grasp(new BinPacking("N1W1B1R0.txt"));
		Grasp n2w1b1r0 = new Grasp(new BinPacking("N2W1B1R0.txt"));
		Grasp n3w1b1r0 = new Grasp(new BinPacking("N3W1B1R0.txt"));
		Grasp n3w1b2r0 = new Grasp(new BinPacking("N3W1B2R0.txt"));
		Grasp n3w2b1r0 = new Grasp(new BinPacking("N3W2B1R0.txt"));
		Grasp n4w1b1r6 = new Grasp(new BinPacking("N4W1B1R6.txt"));
		Grasp n4w1b1r9 = new Grasp(new BinPacking("N4W1B1R9.txt"));
		Grasp n4w1b2r0 = new Grasp(new BinPacking("N4W1B2R0.txt"));
		Grasp n4w2b1r0 = new Grasp(new BinPacking("N4W2B1R0.txt"));
		Grasp n4w2b1r3 = new Grasp(new BinPacking("N4W2B1R3.txt"));
		/*n1w1b1r0.grasp(0.3);
		n1w1b1r0.bp.afficherSolution();
		System.out.println();
		System.out.println("Solution du Grasp n1w1b1r0 après 10 minutes : "+n1w1b1r0.soluce);
		
		//--------------
		n2w1b1r0.grasp(0.3);
		n2w1b1r0.bp.afficherSolution();
		System.out.println();
		System.out.println("Solution du Grasp n2w1b1r0 après 10 minutes : "+n2w1b1r0.soluce);
		//--------------
		n3w1b1r0.grasp(0.3);
		n3w1b1r0.bp.afficherSolution();
		System.out.println();
		System.out.println("Solution du Grasp n3w1b1r0 après 10 minutes : "+n3w1b1r0.soluce);		
		//--------------
		n3w1b2r0.grasp(0.3);
		n3w1b2r0.bp.afficherSolution();
		System.out.println();
		System.out.println("Solution du Grasp n3w1b2r0 après 10 minutes : "+n3w1b2r0.soluce);
		//--------------
		n3w2b1r0.grasp(0.3);
		n3w2b1r0.bp.afficherSolution();
		System.out.println();
		System.out.println("Solution du Grasp n3w2b1r0 après 10 minutes : "+n3w2b1r0.soluce);
		//--------------*/
		n4w1b1r6.grasp(0.1);
		n4w1b1r6.bp.afficherSolution();
		System.out.println();
		System.out.println("Solution du Grasp n4w1b1r6 après 10 minutes : "+n4w1b1r6.soluce);
		//--------------
		n4w1b1r9.grasp(0.1);
		n4w1b1r9.bp.afficherSolution();
		System.out.println();
		System.out.println("Solution du Grasp n4w1b1r9 après 10 minutes : "+n4w1b1r9.soluce);
		//--------------
		n4w1b2r0.grasp(0.1);
		n4w1b2r0.bp.afficherSolution();
		System.out.println();
		System.out.println("Solution du Grasp n4w1b2r0 après 10 minutes : "+n4w1b2r0.soluce);
		//--------------
		n4w2b1r0.grasp(0.1);
		n4w2b1r0.bp.afficherSolution();
		System.out.println();
		System.out.println("Solution du Grasp n4w2b1r0 après 10 minutes : "+n4w2b1r0.soluce);
		//--------------*/
		n4w2b1r3.grasp(0.1);
		n4w2b1r3.bp.afficherSolution();
		System.out.println();
		System.out.println("Solution du Grasp n4w2b1r3 après 10 minutes : "+n4w2b1r3.soluce);//*/
		
	}
}
