package it.polito.tdp.model;

import java.time.*;
import java.util.*;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;


public class Simulatore {
	private LocalDate data;
	private Map <Integer, Agente> agenti ;
	private Queue<Evento> eventList;
	private Model m;
	private Random rand;
	// parametri iniziali
	int TEMPO_MAX;
	Centrale c;
	int numeroAgenti;
	
	
	
public Simulatore(int anno, int mese , int giorno, int numeroAgenti) {
	TEMPO_MAX = 15*60;
	m = new Model();
	//m.creaGrafo(anno);
	agenti = new HashMap <Integer, Agente>();
	this.data = LocalDate.of(anno, mese, giorno);
	this.numeroAgenti=numeroAgenti;

}
public void init () {
	eventList = new PriorityQueue <Evento>();
	for (Event e : m.getEventiGiorno(data)) {
		eventList.add(new Evento (Evento.eventTypeEnum.CRIMINE,new LatLng (e.getGeo_lat(),e.getGeo_lon()),null,e.getReported_date() ,e.getOffense_category_id()));
	}
	System.out.println(data);
	System.out.println(eventList.size());
	c = m.getCentrale(data);
	for (int i=1;i<=numeroAgenti;i++) {
		agenti.put(i, new Agente (i, false , c.getCoord() ));
	}
	System.out.println(agenti.toString()+"\nSimulazione");
	
}
   public void run () {
	   Evento e;
	   System.out.println(eventList.size());
	  while (eventList.isEmpty()) {
		  e = eventList.poll();
			switch (e.getEtype()) {
			
			case CRIMINE:
				/*if (trovaAgenteLibero(e.getCoord())==null) {
					e.setData(e.getData().plusMinutes(15));
					eventList.add(e);
					//Stats.malGestito();
					System.out.println("++MAL GESTITO\n");
					
				}else {*/
				
				int id = trovaAgenteLibero(e.getCoord());
				if (id==11) {
					System.out.println("++MAL GESTITO\n");
					break;
				}else {
				Agente a = agenti.get(id);
				e.setA(a);
				a.setOccupato(true);
				a.setCoord(e.getCoord());
				if ((getTempo(e)-TEMPO_MAX>0)) {
					//Stats.malGestito();
					System.out.println("MAL GESTITO\n");
					}
				    // aggiungo che l agente � occupato
					
					if (e.getType()=="all_other_crimes") {
					        eventList.add(new Evento (Evento.eventTypeEnum.AGENTE_SI_LIBERA,
							e.getCoord(),
							null,e.getData().plusHours(rand.nextInt(2)+1),e.getType()));
					
				    }else {
					        eventList.add(new Evento (Evento.eventTypeEnum.AGENTE_SI_LIBERA,
							e.getCoord(),
							null,e.getData().plusHours(2),e.getType()));
				    }
					
			break;
				}
			case AGENTE_SI_LIBERA:
				Agente a1 =e.getA();
				a1.setOccupato(false);
				
		    break;
			
			
			}  
			  
			  System.out.println(e.getEtype()+", "+e.getData()+",\n");
		  
	  
		  }
	  } 
	   
public int trovaAgenteLibero(LatLng coord) {
	double distanzaMin = Double.MAX_VALUE;
	double d=0;
	int a = 11;
	for (Agente agente : agenti.values()) {
		d = LatLngTool.distance(agente.getCoord(), coord, LengthUnit.KILOMETER);
		if (agente.getOccupato()==false && d<distanzaMin) {
			distanzaMin= d;
			a = agente.getId();
			
		}
		
	}
	/*if (a==11) {
		return null;
	}
	Agente a1 = agenti.get(a);
    a1.setOccupato(true);
	//System.out.println("\n"+a.toString());*/
	return a;
}
   
public int getTempo (Evento e) {
	int tempo;
	LatLng agenteLL = e.getA().getCoord();
	LatLng eLL = e.getCoord();
	long distanza = (long) LatLngTool.distance(agenteLL, eLL, LengthUnit.KILOMETER);
	//tempo = Duration.ofMinutes(distanza/60);
	tempo = (int) ((distanza/60)*60*60);
	return tempo;
	
}  
   
}

