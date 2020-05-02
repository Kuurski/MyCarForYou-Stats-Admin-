package controller;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Marque;
import model.Ville;

public class TestAdmin {
	
	private IntegerProperty idLocationTest = new SimpleIntegerProperty();
	private StringProperty dateDebutTest = new SimpleStringProperty();
	private StringProperty dateFinTest = new SimpleStringProperty();
	private ObjectProperty<Ville> villeTest = new SimpleObjectProperty<>();
	private ObjectProperty<Marque> marqueTest = new SimpleObjectProperty<>();
	private IntegerProperty tarifTest = new SimpleIntegerProperty();
	
	
	public TestAdmin() {
		super();
	}


	public TestAdmin(int idLocationTest, String dateDebutTest, String dateFinTest,
			Ville villeTest, Marque marqueTest, int tarifTest) {
		super();
		this.idLocationTest.set(idLocationTest);
		this.dateDebutTest.set(dateDebutTest);
		this.dateFinTest.set(dateFinTest);
		this.villeTest.set(villeTest);
		this.marqueTest.set(marqueTest);
		this.tarifTest.set(tarifTest);
		
	}
	
	public TestAdmin(int prix) {
		this.tarifTest.set(prix);
	}


	public IntegerProperty getIdLocationTest() {
		return idLocationTest;
	}


	public void setIdLocationTest(IntegerProperty idLocationTest) {
		this.idLocationTest = idLocationTest;
	}


	public StringProperty getDateDebutTest() {
		return dateDebutTest;
	}


	public void setDateDebutTest(StringProperty dateDebutTest) {
		this.dateDebutTest = dateDebutTest;
	}


	public StringProperty getDateFinTest() {
		return dateFinTest;
	}


	public void setDateFinTest(StringProperty dateFinTest) {
		this.dateFinTest = dateFinTest;
	}


	public ObjectProperty<Ville> getVilleTest() {
		return villeTest;
	}


	public void setVilleTest(ObjectProperty<Ville> villeTest) {
		this.villeTest = villeTest;
	}


	public ObjectProperty<Marque> getMarqueTest() {
		return marqueTest;
	}


	public void setMarqueTest(ObjectProperty<Marque> marqueTest) {
		this.marqueTest = marqueTest;
	}


	public IntegerProperty getTarifTest() {
		return tarifTest;
	}


	public void setTarifTest(IntegerProperty tarifTest) {
		this.tarifTest = tarifTest;
	}
	
	
	
	
}
