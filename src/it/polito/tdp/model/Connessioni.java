package it.polito.tdp.model;

public class Connessioni implements Comparable <Connessioni> {
   
   private Distretto distretto1;
   private Distretto distretto2;
   private double distanza;
public Connessioni(Distretto distretto1, Distretto distretto2, double distanza) {
	super();
	
	this.distretto1 = distretto1;
	this.distretto2 = distretto2;
	this.distanza = distanza;
}

public Distretto getDistretto1() {
	return distretto1;
}
public void setDistretto1(Distretto distretto1) {
	this.distretto1 = distretto1;
}
public Distretto getDistretto2() {
	return distretto2;
}
public void setDistretto2(Distretto distretto2) {
	this.distretto2 = distretto2;
}
public double getDistanza() {
	return distanza;
}
public void setDistanza(double distanza) {
	this.distanza = distanza;
}


@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	long temp;
	temp = Double.doubleToLongBits(distanza);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	result = prime * result + ((distretto1 == null) ? 0 : distretto1.hashCode());
	result = prime * result + ((distretto2 == null) ? 0 : distretto2.hashCode());
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
	Connessioni other = (Connessioni) obj;
	if (Double.doubleToLongBits(distanza) != Double.doubleToLongBits(other.distanza))
		return false;
	if (distretto1 == null) {
		if (other.distretto1 != null)
			return false;
	} else if (!distretto1.equals(other.distretto1))
		return false;
	if (distretto2 == null) {
		if (other.distretto2 != null)
			return false;
	} else if (!distretto2.equals(other.distretto2))
		return false;
	return true;
}

@Override
public int compareTo(Connessioni d) {
	
	return (int) (this.distanza-d.getDistanza());
}

@Override
public String toString() {
	return String.format("distretto2=%s, distanza=%s ", distretto2, distanza);
}

   
}
