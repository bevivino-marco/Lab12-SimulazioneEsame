package it.polito.tdp.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.db.EventsDao;

public class Model {
	private EventsDao dao;
	private Map < Integer , Event > idMapEventi;
	private Map < Integer , Distretto > idMapDistretti;
	private List <Event> listaEventi;
	private List <Distretto> listaDistretti;
	private SimpleWeightedGraph<Distretto, DefaultWeightedEdge> grafo;
	
	
	
	public Model() {
		dao = new EventsDao();
		Map < Integer , Event > idMapEventi = new HashMap < Integer , Event > ();
		Map < Integer , Distretto > idMapDistretti = new HashMap < Integer , Distretto > ();
	    List <Event> listaEventi = new LinkedList <Event>();
		
		
	}
	public void creaGrafo (int anno) {
		this.grafo =new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		idMapDistretti = dao.getDistretti(idMapDistretti);
		//System.out.println(idMapDistretti.values().toString());
		List <Distretto> listaDistretti = new LinkedList <Distretto>(idMapDistretti.values());
		for (Distretto d : listaDistretti) {
			d.setEventi(dao.listAllEvents(anno, d.getId(), idMapEventi));
           // System.out.println(d.getEventi().toString());
		}
		Graphs.addAllVertices(this.grafo, listaDistretti);
		for (Distretto d1 : listaDistretti) {
			for (Distretto d2 : listaDistretti) {
				if (!d1.equals(d2) && !grafo.containsEdge(d1, d2) && !grafo.containsEdge(d2, d1) ) {
					Graphs.addEdge(grafo, d1, d2, (LatLngTool.distance(d1.getCoord(), d2.getCoord(), LengthUnit.KILOMETER)));
					
				}
				
				
			}
		}
		//System.out.println(grafo.vertexSet());
		//System.out.println(grafo.edgeSet());
		System.out.println(grafo.vertexSet().size());
		System.out.println(grafo.edgeSet().size()+"\n");
	}

	
	public Map <Distretto, List <Distretto>> getVicini (){
	
			
		Map <Distretto, List <Distretto>> mappa =  new HashMap <Distretto, List <Distretto>>();
		/**/
		for (Distretto d : grafo.vertexSet()) {
			List <Connessioni> lC = new LinkedList <Connessioni>();
			List <Distretto> distr = new LinkedList <Distretto>();
			List <Distretto> vicini = new LinkedList <Distretto>();
			vicini = Graphs.neighborListOf(grafo, d);
			for (Distretto d2 : vicini) {
				Connessioni c = new Connessioni (d, d2, (LatLngTool.distance(d.getCoord(), d2.getCoord(), LengthUnit.KILOMETER)));
                lC.add(c);
			}
			Collections.sort(lC);
			
			for (Connessioni cL : lC) {
				distr.add(cL.getDistretto2());
			}
			mappa.put(d, distr);
			
		}
		
			
		
		System.out.println(mappa.toString());
		return mappa;
	}
	
	
	public List <Integer> getAnni(){
		return dao.getAnni();
	}
}
