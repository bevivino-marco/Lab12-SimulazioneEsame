package it.polito.tdp.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.javadocmd.simplelatlng.LatLng;

import it.polito.tdp.model.Distretto;
import it.polito.tdp.model.Event;

public class EventsDao {
	// mi faccio dare tutti gli eventi in quel distretto nell anno selezionato
	public Map <Integer, Event> listAllEvents(int anno, int codD, Map <Integer, Event> idMapEventi){
		String sql = "SELECT * FROM events WHERE district_id=? AND YEAR(reported_date)=?" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, codD);
			st.setInt(2, anno);
			Map <Integer, Event> mappaE = new HashMap <Integer, Event>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					/*if (idMapEventi.containsKey(res.getInt("offense_code"))) {
						mappaE.put(res.getInt("offense_code"),idMapEventi.get(res.getInt("offense_code")));
						
					}else {*/
					mappaE.put(res.getInt("offense_code"),new Event(res.getLong("incident_id"),
							res.getInt("offense_code"),
							res.getInt("offense_code_extension"), 
							res.getString("offense_type_id"), 
							res.getString("offense_category_id"),
							res.getTimestamp("reported_date").toLocalDateTime(),
							res.getString("incident_address"),
							res.getDouble("geo_lon"),
							res.getDouble("geo_lat"),
							res.getInt("district_id"),
							res.getInt("precinct_id"), 
							res.getString("neighborhood_id"),
							res.getInt("is_crime"),
							res.getInt("is_traffic")));//}
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}
			
			conn.close();
			return mappaE ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	public List <Integer> getAnni (){
		String sql = "SELECT  reported_date FROM events GROUP BY YEAR (reported_date)" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Integer> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
		
			while(res.next()) {
				try {
					int anno;
					anno = res.getDate("reported_date").toLocalDate().getYear();
					list.add(anno);
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("errore nel getAnni del dao"));
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	
	}
	
	public Map <Integer, Distretto> getDistretti(Map <Integer, Distretto> idMapDistretti , int anno){
		String sql = "SELECT district_id,AVG(geo_lat),AVG(geo_lon) " + 
				"FROM EVENTS  " + 
				"WHERE YEAR(reported_date)=? " + 
				"GROUP BY district_id" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, anno);
			//List<Integer> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			Map <Integer, Distretto> mappaD = new HashMap <Integer, Distretto>() ;
			while(res.next()) {
				try {
					LatLng coord = new LatLng (res.getDouble("AVG(geo_lat)"),res.getDouble("AVG(geo_lon)"));
					
					int id = res.getInt("district_id");
				   /* if (!idMapDistretti.isEmpty() && idMapDistretti.containsKey(id)) {
				    	mappaD.put (id, idMapDistretti.get(id));
				    }else {*/
				    	mappaD.put (id, new Distretto (id, coord));
				    //}
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("errore nel getAnni del dao"));
				}
			}
			
			conn.close();
			return mappaD ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	
	}
	
	
	
	
}
