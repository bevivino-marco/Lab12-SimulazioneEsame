package it.polito.tdp.model;

public class TestModel {

	public static void main(String[] args) {
		Model model = new Model();
		//System.out.println(model.getAnni().toString());
		//model.creaGrafo(2014);
		//model.getVicini();
		Simulatore s = new Simulatore(2014, 3 , 2, 4);
		s.init();
		s.run();
	}

}
