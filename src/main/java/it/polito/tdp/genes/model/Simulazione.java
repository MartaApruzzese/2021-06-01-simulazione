package it.polito.tdp.genes.model;

import java.util.*;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;

public class Simulazione {
	
	//Dati in ingresso
	private int nIng;
	private Genes parteza;
	private Graph<Genes, DefaultWeightedEdge> grafo;
	private int nMesi=36;
	private double probRimanere= 0.3;
	
	//Coda degli eventi
	private PriorityQueue<Evento> queue;
	
	
	//Stato del mondo
	private List<Genes> genStudiato;
	
	
	public Simulazione(Genes partenza, Graph<Genes, DefaultWeightedEdge> grafo, int n) {
		this.parteza=partenza;
		this.grafo= grafo;
		this.nIng=n;
		
		if(this.grafo.degreeOf(partenza)==0) {
			throw new IllegalArgumentException("Vertice di partenza isolato");
			
		}
		
		this.queue= new PriorityQueue<>();
		for(int nI =0; nI<this.nIng; nI++) {
			this.queue.add(new Evento(0, nI));
		}
		
		this.genStudiato= new ArrayList<>();
		for(int i=0; i<this.nIng; i++) {
			this.genStudiato.add(this.parteza);
		}
		
	}
	
	public void run() {
		while(!this.queue.isEmpty()) {
			Evento e= queue.poll();
			
			int T= e.getMese();
			int n= e.getnIng();
			Genes g= this.genStudiato.get(n);
			
			
			if(T<this.nMesi) {
				double s= 0; //Totale peso archi
				for(DefaultWeightedEdge edge: this.grafo.edgesOf(g)) {
					s=s+this.grafo.getEdgeWeight(edge);
				}
				
				double rand= Math.random()*s;
				
				Genes nuovo= null;
				double somma=0;
				
				for(DefaultWeightedEdge edge: this.grafo.edgesOf(g)) {
					somma=somma+this.grafo.getEdgeWeight(edge);
					if(somma>rand) {
						nuovo= Graphs.getOppositeVertex(this.grafo,edge , g);
						break;
					}
				}
				
				this.genStudiato.set(n, nuovo);
				this.queue.add(new Evento(T+1, n));
			}
		}
	}
	
	//output
	public Map<Genes, Integer> geniStudiati(){
		Map<Genes, Integer> studiati= new HashMap<>();
		
		for(int n=0; n<this.nIng; n++) {
			Genes g= this.genStudiato.get(n);
			if(studiati.containsKey(g)) {
				studiati.put(g, studiati.get(g)+1);
			}else {
				studiati.put(g, 1);
			}
		}
		return studiati;	
	}
}
