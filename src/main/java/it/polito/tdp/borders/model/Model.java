package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {

	private Map<String, Country> countries;
	private BordersDAO dao;
	private Graph<Country, DefaultEdge> grafo; 
	
	
	public Model() {
		countries = new HashMap<>();
		dao = new BordersDAO();
	}
	
	public void createGraph(int anno) {
		grafo = new SimpleGraph<>(DefaultEdge.class);
		dao.loadAllCountries(countries);
		Graphs.addAllVertices(grafo, countries.values());
		
		for(Border b : this.dao.getCountryPairs(anno)) {
			if(!this.grafo.containsEdge(countries.get(b.getCountry1()), countries.get(b.getCountry2())) && countries.get(b.getCountry1())!=null && countries.get(b.getCountry2())!=null) {
				this.grafo.addEdge(countries.get(b.getCountry1()), countries.get(b.getCountry2()));
			}
		}
		
	}
	
	public List<Country> visitaGrafoIt(Country stato) {
		GraphIterator<Country, DefaultEdge> visita = new BreadthFirstIterator<>(this.grafo,stato);
		List<Country> lista = new ArrayList<>();
		while(visita.hasNext()) {
			lista.add(visita.next());
		}
		return lista;
	}
	
	public Map<Country, Country> visitaGrafoRic(Country stato) {
		return null;
	}
	
	public List<Country> getAllCountry(){
		List<Country> stmp = new LinkedList<Country>(countries.values());
		Collections.sort(stmp);
		return stmp;
	}
	
	public String stampaVertici() {
		String search="";
		for(Country c : grafo.vertexSet()) {
			search += c.getStateAbb()+" "+c.getCountryName()+" "+grafo.degreeOf(c)+"\n";
		}
		ConnectivityInspector<Country, DefaultEdge> ins = new ConnectivityInspector<>(grafo);
		search += "Il numero di componenti connesse è: "+ins.connectedSets().size();
		return search;
	}

}
