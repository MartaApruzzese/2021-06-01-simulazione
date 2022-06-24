package it.polito.tdp.genes.model;

import java.util.Objects;

public class Interaction {
	private Genes gene1;
	private Genes gene2;
	private double corr;
	
	public Interaction(Genes gene1, Genes gene2, double corr) {
		super();
		this.gene1 = gene1;
		this.gene2 = gene2;
		this.corr = corr;
	}
	
	public Genes getGene1() {
		return gene1;
	}
	public Genes getGene2() {
		return gene2;
	}
	public double getCorr() {
		return corr;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(gene1, gene2);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Interaction other = (Interaction) obj;
		return Objects.equals(gene1, other.gene1) && Objects.equals(gene2, other.gene2);
	}
	
	
}
