package it.polito.tdp.genes.model;

import java.util.*;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.genes.db.GenesDao;

public class Model {
	
	private GenesDao dao;
	private Graph<Genes, DefaultWeightedEdge> grafo;
	private Map<String, Genes> idMap;
	private List<Genes> vertici;
	public List<Interaction> interazioni;
	
	
	public Model() {
		this.dao= new GenesDao();
	}
	
	public void creaGrafo() {
		this.grafo= new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		this.idMap= new HashMap<>();
		this.vertici= new ArrayList<>();
		this.interazioni= new ArrayList<>();
		
		//popolo idMap
		for(Genes g: this.dao.getAllGenes()) {
			idMap.put(g.getGeneId(), g);
		}
		
		//Popolo lista interazioni
		this.interazioni.addAll(this.dao.getAllInteraction(idMap));
		
		//Creo i vertici
		for(String s: this.dao.getIdGenesEssential()) {
			vertici.add(idMap.get(s));
		}
		Graphs.addAllVertices(this.grafo, vertici);
		
		
		//Creo gli archi
			//--> esiste arco se esiste interazione tra i due geni
			//PESO= se geni hanno stesso cromosoma--> 2*abs(corr)
				// se geni non hanno stesso cromosoma --> abs(corr);
		
		for(Interaction i: this.interazioni) {
			if(this.grafo.containsVertex(i.getGene1()) && this.grafo.containsVertex(i.getGene2()) && !i.getGene1().equals(i.getGene2())) {
				//Verifico cromosoma
				double peso;
				if(i.getGene1().getChromosome()==i.getGene2().getChromosome()) {
					 peso= Math.abs(2*i.getCorr());
				}else {
					peso= Math.abs(i.getCorr());
				}
				if(!this.grafo.containsEdge(i.getGene1(), i.getGene2()) || !this.grafo.containsEdge(i.getGene2(), i.getGene1())) {
					//Aggiungo arco
					Graphs.addEdgeWithVertices(this.grafo, i.getGene1(), i.getGene2(), peso);
				}
			}
		}
		
	}
	
	
	
	public int numVertici() {
		return this.vertici.size();
	}
	
	public int numArchi() {
		return this.grafo.edgeSet().size();
	}
}
