package it.polito.tdp.model;

import com.javadocmd.simplelatlng.LatLng;

public class Centrale {

	private int distretto;
	private LatLng coord;
	

	public Centrale(double lat, double lon, int distretto) {
		coord = new LatLng (lat, lon);
		this.distretto=distretto;
	}


	public int getDistretto() {
		return distretto;
	}


	public void setDistretto(int distretto) {
		this.distretto = distretto;
	}


	public LatLng getCoord() {
		return coord;
	}


	public void setCoord(LatLng coord) {
		this.coord = coord;
	}

}
