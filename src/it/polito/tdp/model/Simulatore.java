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
	// parametri iniziali
	Duration TEMPO_MAX;
	Centrale c;
	int numeroAgenti;
	
	
public Simulatore(int anno, int mese , int giorno, int numeroAgenti) {
	TEMPO_MAX = Duration.ofMinutes(15);
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
	//System.out.println(data);
	//System.out.println(eventList.toString());
	c = m.getCentrale(data);
	for (int i=1;i<=numeroAgenti;i++) {
		agenti.put(i, new Agente (i, false , c.getCoord() ));
	}
	
}
   public void run () {
	   Evento e = eventList.poll();
	  while (!eventList.isEmpty()) {
		  
			switch (e.getEtype()) {
			
			case CRIMINE:
				Agente a = trovaAgenteLibero(e.getCoord());
				if (getTempo(e).compareTo(TEMPO_MAX)>0) {
				//	Stats.malGestito();
					}
				if (e.getType()=="all_other_crimes") {
					// aggiungo che l agente è occupato
				}else {
					
				}
				
			break;
			
			case AGENTE_SI_LIBERA:
				
		    break;
			
			
			}  
			  
			  
	  
		  }
		  
	  } 
	   
public Agente trovaAgenteLibero(LatLng coord) {
	double distanzaMin = 4000.00;
	double d=0;
	Agente a=null;
	for (Agente agente : agenti.values()) {
		d = LatLngTool.distance(agente.getCoord(), coord, LengthUnit.KILOMETER);
		if (d<distanzaMin) {
			distanzaMin= d;
			a = agente;
		}
	}
	return a;
}
   
public Duration getTempo (Evento e) {
	Duration tempo;
	LatLng agenteLL = e.getA().getCoord();
	LatLng eLL = e.getCoord();
	long distanza = (long) LatLngTool.distance(agenteLL, eLL, LengthUnit.KILOMETER);
	tempo = Duration.ofMinutes(distanza/60);
	return tempo;
	
}  
   
}

