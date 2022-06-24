package it.polito.tdp.genes.model;

public class Adiacente implements Comparable<Adiacente>{
	private Genes gene1;
	private double peso;
	
	
	public Adiacente(Genes gene1, double peso) {
		super();
		this.gene1 = gene1;
		this.peso = peso;
	}
	
	
	public Genes getGene1() {
		return gene1;
	}
	public double getPeso() {
		return peso;
	}
	
	public int compareTo(Adiacente other) {
		//return -( this.peso.compareTo(other.peso) );
		double diff= this.peso-other.peso;
		if(diff<1 && diff>0) {
			return -1;
		}
		if(diff>-1 && diff<0) {
			return 1;
		}
		return (int)(-1*diff);
		// return (int) (this.peso - other.peso) ;
		// 0.7-0.5= 0.2 --> 0
	}
}
