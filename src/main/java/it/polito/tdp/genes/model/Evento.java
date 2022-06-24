package it.polito.tdp.genes.model;

public class Evento implements Comparable<Evento>{

	private int mese;
	private int nIng;
	
	
	
	public Evento(int mese, int nIng) {
		super();
		this.mese = mese;
		this.nIng = nIng;
	}

	

	public int getMese() {
		return mese;
	}



	public void setMese(int mese) {
		this.mese = mese;
	}



	public int getnIng() {
		return nIng;
	}



	public void setnIng(int nIng) {
		this.nIng = nIng;
	}



	@Override
	public int compareTo(Evento o) {
		// TODO Auto-generated method stub
		return this.mese-o.mese;
	}
	
	
}
