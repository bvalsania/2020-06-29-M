package it.polito.tdp.imdb.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.imdb.db.ImdbDAO;

public class Model {

	private ImdbDAO dao;
	private Graph<Director, DefaultWeightedEdge> grafo;
	private Map<Integer, Director> idMap;
	
	public Model() {
		dao = new ImdbDAO();
		idMap = new HashMap<>();
		this.dao.listAllDirectors(idMap);
	}
	
	
	public String creag(int anno) {
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.grafo, this.dao.getv(anno, idMap));
		
		for(Coppia c : this.dao.getarchi(anno, idMap)) {
		Graphs.addEdge(this.grafo, c.getD1(), c.getD2(), c.getPeso());
		}
		
		return "Il grafo creato ha vertici e archi : "+this.grafo.vertexSet().size()+"  "+grafo.edgeSet().size();
	}
	
	
	public List<Director> getdire(){
		List<Director> dire = new ArrayList<>(grafo.vertexSet());
		
		Collections.sort(dire, new Comparator<Director> () {

			@Override
			public int compare(Director o1, Director o2) {
				// TODO Auto-generated method stub
				return o1.id.compareTo(o2.id);
			}
		});
		return dire;
	}
	
	
	public List<Adiacenza> getAdiacenti(Director d){
		List<Adiacenza> res = new ArrayList<>();
		
		for(Director i: Graphs.neighborListOf(this.grafo, d)) {
			res.add(new Adiacenza (i,(int) grafo.getEdgeWeight(grafo.getEdge(i, d))));
		}
		
		Collections.sort(res, new Comparator<Adiacenza>() {

			@Override
			public int compare(Adiacenza o1, Adiacenza o2) {
				// TODO Auto-generated method stub
				return o2.getPeso().compareTo(o1.getPeso());
			}
		});
		return res;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
/*	private ImdbDAO dao;
	private Graph<Director, DefaultWeightedEdge> grafo;
	private Map<Integer, Director> idMap;
	
	private List<Director> ottimo;
	private int lOttimo;
	
	
	public List<Director> cercaMAx(Director start, int c){
		List<Director> parziale = new ArrayList<>();
		ottimo = new ArrayList<>();
		
		parziale.add(start);
		cerca(parziale, 0, c);
		
		return ottimo;
		
	}
	
	private void cerca(List<Director> parziale, int l, int c) {

		//condizione di terminazione 
		if(l>lOttimo) {
			lOttimo = l;
			ottimo = new ArrayList<>(parziale);
			
		}
		
		
		Director last = parziale.get(parziale.size()-1);
		List<Director> vicini = Graphs.neighborListOf(this.grafo, last);
		
		for(Director d : vicini) {
			DefaultWeightedEdge e = this.grafo.getEdge(d, last);
			if(!parziale.contains(d) && c<= (l+=this.grafo.getEdgeWeight(e))) {
				parziale.add(d);
				l += this.grafo.getEdgeWeight(e);
				cerca(parziale, l, c);
				parziale.remove(d);
				l -= this.grafo.getEdgeWeight(e);
			}
			
		}
		
		
	}
	
	public int getlottimo() {
		return lOttimo;
	}

	public Model() {
		dao = new ImdbDAO();
		idMap = new HashMap<>();
		this.dao.listAllDirectors(idMap);
		
	}
	
	public void creaGrafo(int anno) {
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		//aggiungere i vertici
		Graphs.addAllVertices(grafo, this.dao.getVertici(anno, idMap));
		
		//aggiungo archi
		for(Coppia c: this.dao.getArchi(anno, idMap)) {
			Graphs.addEdgeWithVertices(grafo, c.getD1(), c.getD2(), anno);
		}
		
	}
	
	public List<Integer> getAnno(){
		List<Integer> anni = new ArrayList<>();
		for(Movie m : this.dao.listAllMovies() ) {
			int anno = 0;
			if(!anni.contains(m.getYear()) && m.getYear()<=2006) {
				anno = m.getYear();
				anni.add(anno);
			}
			
		}
		return anni;
	}
	
	public List<Director> getVertici(){
		return new ArrayList<>(this.grafo.vertexSet());
	}
	
	public List<Coppia> getArchi(Integer anno){
		return dao.getArchi(anno, idMap);
	}
	
	public int nVertici(){
		return this.grafo.vertexSet().size();
	}
	public int nArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public List<Adiacenza> getAdiacenza(Director d, Integer anno){
		
		List<Adiacenza> a = new ArrayList<>();
		
		for(Director e: Graphs.neighborListOf(grafo, d)) {
			for(Coppia c: this.getArchi(anno)) {
				if(c.getD1().equals(d) && c.getD2().equals(e)) {
					a.add(new Adiacenza (e, c.getPeso()));
				}else if(c.getD1().equals(e) && c.getD2().equals(d)) {
					a.add(new Adiacenza (e, c.getPeso()));
				}
					
			}
		}
		Collections.sort(a, new Comparator<Adiacenza>(){
			
			public int compare(Adiacenza o1, Adiacenza o2) {
				return -(o1.getPeso()-(o2.getPeso()));
			}
		});
		return a;
		
	}
	*/
}
