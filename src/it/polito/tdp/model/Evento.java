package it.polito.tdp.model;

import java.time.LocalDateTime;

import com.javadocmd.simplelatlng.LatLng;

public class Evento implements Comparable <Evento> {
	public enum eventTypeEnum {
		CRIMINE,
		AGENTE_SI_LIBERA;
	}
    private  eventTypeEnum etype ;
    private LatLng coord;
    private Agente a;
    private LocalDateTime data;
    private String type;
    
    
	public Evento(eventTypeEnum etype, LatLng coord, Agente a, LocalDateTime data, String type) {
		super();
		this.etype = etype;
		this.coord = coord;
		this.a = a;
		this.data = data;
		this.type=type;
	}
    


	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public eventTypeEnum getEtype() {
		return etype;
	}


	public void setEtype(eventTypeEnum etype) {
		this.etype = etype;
	}


	public LatLng getCoord() {
		return coord;
	}


	public void setCoord(LatLng coord) {
		this.coord = coord;
	}


	public Agente getA() {
		return a;
	}


	public void setA(Agente a) {
		this.a = a;
	}


	public LocalDateTime getData() {
		return data;
	}


	public void setData(LocalDateTime data) {
		this.data = data;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a == null) ? 0 : a.hashCode());
		result = prime * result + ((coord == null) ? 0 : coord.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((etype == null) ? 0 : etype.hashCode());
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
		Evento other = (Evento) obj;
		if (a == null) {
			if (other.a != null)
				return false;
		} else if (!a.equals(other.a))
			return false;
		if (coord == null) {
			if (other.coord != null)
				return false;
		} else if (!coord.equals(other.coord))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (etype != other.etype)
			return false;
		return true;
	}


	@Override
	public int compareTo(Evento o) {
		// TODO Auto-generated method stub
		return this.data.compareTo(o.getData());
	}

}
