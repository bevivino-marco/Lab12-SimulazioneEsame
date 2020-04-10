package it.polito.tdp.model;

import com.javadocmd.simplelatlng.LatLng;

public class Agente {
   int id;
   boolean occupato ;
   LatLng coord;
public Agente(int id, boolean occupato, LatLng coord) {
	super();
	this.id = id;
	this.occupato = occupato;
	this.coord = coord;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public boolean getOccupato() {
	return occupato;
}
public void setOccupato(boolean occupato) {
	this.occupato = occupato;
}
public LatLng getCoord() {
	return coord;
}
public void setCoord(LatLng coord) {
	this.coord = coord;
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
	Agente other = (Agente) obj;
	if (id != other.id)
		return false;
	return true;
}
@Override
public String toString() {
	return String.format("\nAgente id=%s, occupato=%s, coord=%s", id, occupato, coord);
}
   
   
}
