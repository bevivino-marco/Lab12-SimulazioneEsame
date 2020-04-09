package it.polito.tdp.model;

public class TestModel {

	public static void main(String[] args) {
		Model model = new Model();
		System.out.println(model.getAnni().toString());
		model.creaGrafo(2014);
		model.getVicini();
	}

}
