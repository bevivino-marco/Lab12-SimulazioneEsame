package it.polito.tdp.model;

import java.util.HashMap;
import java.util.Map;

import com.javadocmd.simplelatlng.LatLng;

public class Distretto {
	private LatLng coord;
	private int id ;
	private Map <Integer,Event> eventi;
	
	
	public Distretto(int id, LatLng coord) {
		this.coord=coord;
		this.id=id;
		eventi = new HashMap <Integer, Event>();
		
	}
	
	
	@Override
	public String toString() {
		return String.format("  id=%s ,", id);
	}


	public Map<Integer, Event> getEventi() {
		return eventi;
	}


	public void setEventi(Map<Integer, Event> eventi) {
		this.eventi = eventi;
	}


	public LatLng getCoord() {
		return coord;
	}

	public void setCoord(LatLng coord) {
		this.coord = coord;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Distretto other = (Distretto) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
