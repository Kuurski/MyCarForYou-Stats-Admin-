package view;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controller.ConnectDB;
import controller.LocationDAO;
import controller.MainApp;
import controller.TestAdmin;
import controller.TestAdminDAO;
import controller.UtilisateurDAO;
import controller.VehiculeDAO;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.Ville;
import model.Location;
import model.Marque;
import model.Utilisateur;
import model.Vehicule;

public class MainOverviewController implements Initializable {

	
	
	@FXML
	private ComboBox<Ville> comboBoxVille;
	@FXML
	private DatePicker dateDepart;
	@FXML
	private DatePicker dateRetour;

	// TableView Vehicule
	@FXML
	private TableView<Vehicule> vehiculeTable;
	@FXML
	private TableColumn<Vehicule, Integer> idVehiculeColumn;
	@FXML
	private TableColumn<Vehicule, String> marqueColumn;
	@FXML
	private TableColumn<Vehicule, String> matriculeColumn;
	@FXML
	private TableColumn<Vehicule, String> anneeColumn;
	@FXML
	private TableColumn<Vehicule, Integer> kmColumn;
	@FXML
	private TableColumn<Vehicule, Integer> tarifColumn;
	@FXML 
	private TextField idVehiculeField;

	// NewVehicule
	@FXML
	private ComboBox<Marque> comboBoxMarque;
	@FXML
	private ComboBox<Ville> comboBoxVille2;
	@FXML
	private TextField anneeField;
	@FXML
	private TextField kmField;
	@FXML
	private TextField tarifField;
	@FXML
	private TextField matriculeField;

	// ListViewVehicule
	@FXML
	private TableView<Vehicule> vehiculeClientTable;
	@FXML
	private TableColumn<Vehicule, String> marqueClientColumn;
	@FXML
	private TableColumn<Vehicule, String> matriculeClientColumn;
	@FXML
	private TableColumn<Vehicule, String> anneeClientColumn;
	@FXML
	private TableColumn<Vehicule, Integer> kmClientColumn;
	@FXML
	private TableColumn<Vehicule, Integer> tarifClientColumn;
	
	// TableView Admin
	@FXML
	private TableView<Location> tableauAdmin;
	@FXML
	private TableColumn<Location, Integer> idLocationColumnAdm;
	@FXML
	private TableColumn<Location, Integer> idUserColumnAdm;
//	@FXML
//	private TableColumn<Location, Integer> matriculeLocColumn;
	@FXML
	private TableColumn<Location, Integer> idVehiculeColumnAdm;
	@FXML
	private TableColumn<Location, String> dateDebutLocColumnAdm;
	@FXML
	private TableColumn<Location, String> dateFinLocColumnAdm;
	
	@FXML
	private ComboBox comboBoxVehiculeLoc;
	
	//List TEST
	@FXML
	private TableView<TestAdmin> tableauTest;
	@FXML
	private TableColumn<TestAdmin, String> intervalDebutColumn;
	@FXML
	private TableColumn<TestAdmin, String> intervalFinColumn;
	@FXML
	private TableColumn<TestAdmin, String> libelleMarqueColumn;
	@FXML
	private TableColumn<TestAdmin, String> libelleVilleColumn;
	@FXML
	private TableColumn<TestAdmin, Integer> caColumn;
	
	//elements TeSt
	@FXML
	private DatePicker intervalDebut;
	@FXML
	private DatePicker intervalFin;
	@FXML
	private ComboBox <Marque>cbMarque;
	@FXML
	private ComboBox <Ville>cbVille;

	private MainApp mainApp;
	private MainOverviewController mainOver;

	public ObservableList<Vehicule> listTableVehicule = FXCollections.observableArrayList();
	public ObservableList<Location> listTableAdmin = FXCollections.observableArrayList();
	public ObservableList<TestAdmin> listTestCa = FXCollections.observableArrayList();

	private void initializeComboBoxVille() {

		String sqlSearchVilles = "SELECT * " + "FROM ville ";
		try {

			

			Connection connexion = ConnectDB.initConnection();
			Statement st = connexion.prepareStatement(sqlSearchVilles);
			ResultSet rs = st.executeQuery(sqlSearchVilles);

			ObservableList<Ville> listVilleFirst = FXCollections.observableArrayList();
			
			while (rs.next()) {

				int idVille = rs.getInt("idVille");
				String libelleVille = rs.getString("libelleV");
				Ville ville = new Ville(idVille, libelleVille);
				listVilleFirst.add(ville);

				
			}
			
			
			comboBoxVille.setItems(listVilleFirst);
			comboBoxVille.setConverter(new StringConverter<Ville>() {

				@Override
				public Ville fromString(String libelleVille) {
					// TODO Auto-generated method stub
					ObservableList<Ville> lstCombo = comboBoxVille.getItems();

					for (Ville villeInstance : lstCombo) {

						if (libelleVille.equals(villeInstance.getLibelleV())) {

							return villeInstance;
						}
					}
					return null;
				}

				@Override
				public String toString(Ville ville) {
					// TODO Auto-generated method stub
					return ville.getLibelleV().get();
				}

			});

		} catch (Exception e) {

			System.out.println(e.getMessage());
			// boolean isSelected =
		}
	}
	
	private void initializeComboBoxVille2() {

		String sqlSearchVilles = "SELECT * " + "FROM ville ";
		try {

			

			Connection connexion = ConnectDB.initConnection();
			Statement st = connexion.prepareStatement(sqlSearchVilles);
			ResultSet rs = st.executeQuery(sqlSearchVilles);

			ObservableList<Ville> listVilleFirst = FXCollections.observableArrayList();
			
			while (rs.next()) {

				int idVille = rs.getInt("idVille");
				String libelleVille = rs.getString("libelleV");
				Ville ville = new Ville(idVille, libelleVille);
				listVilleFirst.add(ville);

				
			}
			
			
			comboBoxVille2.setItems(listVilleFirst);
			comboBoxVille2.setConverter(new StringConverter<Ville>() {

				@Override
				public Ville fromString(String libelleVille) {
					// TODO Auto-generated method stub
					ObservableList<Ville> lstCombo = comboBoxVille2.getItems();

					for (Ville villeInstance : lstCombo) {

						if (libelleVille.equals(villeInstance.getLibelleV())) {

							return villeInstance;
						}
					}
					return null;
				}

				@Override
				public String toString(Ville ville) {
					// TODO Auto-generated method stub
					return ville.getLibelleV().get();
				}

			});

		} catch (Exception e) {

			System.out.println(e.getMessage());
			// boolean isSelected =
		}
	}

	private void initializeComboBoxVille3() {

		String sqlSearchVilles2 = "SELECT * " + "FROM ville ";
		try {

			

			Connection connexion = ConnectDB.initConnection();
			Statement st = connexion.prepareStatement(sqlSearchVilles2);
			ResultSet rs = st.executeQuery(sqlSearchVilles2);

			ObservableList<Ville> listVilleSecond = FXCollections.observableArrayList();
			
			while (rs.next()) {

				int idVille = rs.getInt("idVille");
				String libelleVille = rs.getString("libelleV");
				Ville ville = new Ville(idVille, libelleVille);
				listVilleSecond.add(ville);

				
			}
			
			
			cbVille.setItems(listVilleSecond);
			cbVille.setConverter(new StringConverter<Ville>() {

				@Override
				public Ville fromString(String libelleVille) {
					// TODO Auto-generated method stub
					ObservableList<Ville> lstCombo2 = cbVille.getItems();

					for (Ville villeI : lstCombo2) {

						if (libelleVille.equals(villeI.getLibelleV())) {

							return villeI;
						}
					}
					return null;
				}

				@Override
				public String toString(Ville ville) {
					// TODO Auto-generated method stub
					return ville.getLibelleV().get();
				}

			});

		} catch (Exception e) {

			System.out.println(e.getMessage());
			// boolean isSelected =
		}
	}
	
	private void initializeComboBoxMarque() {

		String sqlSearchMarques = "SELECT * " + "FROM marque ";
		try {

			

			Connection connexion = ConnectDB.initConnection();
			Statement st = connexion.prepareStatement(sqlSearchMarques);
			ResultSet rs = st.executeQuery(sqlSearchMarques);

			ObservableList<Marque> listMarqueFirst = FXCollections.observableArrayList();
			
			while (rs.next()) {

				int idMarque = rs.getInt("idMarque");
				String libelleMarque = rs.getString("libelleM");
				Marque marque = new Marque(idMarque, libelleMarque);
				listMarqueFirst.add(marque);

				
			}
			
			
			comboBoxMarque.setItems(listMarqueFirst);
			comboBoxMarque.setConverter(new StringConverter<Marque>() {

				@Override
				public Marque fromString(String libelleMarque) {
					// TODO Auto-generated method stub
					ObservableList<Marque> lstCombo = comboBoxMarque.getItems();

					for (Marque villeInstance : lstCombo) {

						if (libelleMarque.equals(villeInstance.getLibelleM())) {

							return villeInstance;
						}
					}
					return null;
				}

				@Override
				public String toString(Marque marque) {
					// TODO Auto-generated method stub
					return marque.getLibelleM().get();
				}

			});

		} catch (Exception e) {

			System.out.println(e.getMessage());
			// boolean isSelected =
		}
	}

	private void initializeComboBoxMarque2() {

		String sqlSearchMarques2 = "SELECT * " + "FROM marque ";
		try {

			

			Connection connexion = ConnectDB.initConnection();
			Statement st = connexion.prepareStatement(sqlSearchMarques2);
			ResultSet rs = st.executeQuery(sqlSearchMarques2);

			ObservableList<Marque> listMarqueSecond = FXCollections.observableArrayList();
			
			while (rs.next()) {

				int idMarque = rs.getInt("idMarque");
				String libelleMarque = rs.getString("libelleM");
				Marque marque = new Marque(idMarque, libelleMarque);
				listMarqueSecond.add(marque);

				
			}
			
			
			cbMarque.setItems(listMarqueSecond);
			cbMarque.setConverter(new StringConverter<Marque>() {

				@Override
				public Marque fromString(String libelleMarque) {
					// TODO Auto-generated method stub
					ObservableList<Marque> lstCombo2 = cbMarque.getItems();

					for (Marque marqueI: lstCombo2) {

						if (libelleMarque.equals(marqueI.getLibelleM())) {

							return marqueI;
						}
					}
					return null;
				}

				@Override
				public String toString(Marque marque) {
					// TODO Auto-generated method stub
					return marque.getLibelleM().get();
				}

			});

		} catch (Exception e) {

			System.out.println(e.getMessage());
			// boolean isSelected =
		}
	}

	@FXML // Ajout d'un Client dans la base de données
	private void newvehicule(ActionEvent event) throws SQLException {

		VehiculeDAO nouveauVehicule = new VehiculeDAO();
		Utilisateur user = mainApp.getUser();
		Ville ville = (Ville) comboBoxVille2.getValue();
		Marque marque = (Marque) comboBoxMarque.getValue();
		String anneeMeS = anneeField.getText();
		int kmVehicule = Integer.parseInt(kmField.getText());
		int tarif = Integer.parseInt(tarifField.getText());
		String matricule = matriculeField.getText();
		Vehicule leVehicule = new Vehicule(0, ville, marque, matricule, anneeMeS, tarif, kmVehicule);

		int idVehicule = nouveauVehicule.newVehicule(user, leVehicule);
		leVehicule.setIdVehicule(idVehicule);

	}

	@FXML
	private void rechercheVille(ActionEvent event) {

		System.out.println("Teest debut Recherche");

		ObservableList<Vehicule> listTableVehicule = FXCollections.observableArrayList();
		Ville ville = comboBoxVille.getValue();
		LocalDate startDate = this.dateDepart.getValue();
		System.out.println(startDate);
		LocalDate endDate = this.dateRetour.getValue();
		VehiculeDAO vlDAO = new VehiculeDAO();
		listTableVehicule = vlDAO.findByVille(ville,startDate,endDate);
		vehiculeTable.setItems(listTableVehicule);

		System.out.println("Teest fin Recherche");

	}

	private String initializeDateDepart() {

		dateDepart.setOnAction(event -> {
			LocalDate date = dateDepart.getValue();
			System.out.println("Selected date:" + date);
		});
		return null;

	}

	private String initializeDateRetour() {

		dateRetour.setOnAction(event -> {
			LocalDate date = dateRetour.getValue();
			System.out.println("Selected date:" + date);
		});
		return null;

	}

	@FXML
	private void newLocationVehicule(ActionEvent event) throws SQLException {

		LocationDAO locDAO = new LocationDAO();


		int idVehicule = vehiculeTable.getSelectionModel().getSelectedItem().getIdVehicule().get();

		Utilisateur user = mainApp.getUser();
		String dateDebut = dateDepart.getValue().toString();
		String dateFin = dateRetour.getValue().toString();


		Location laLocation = new Location(0, 0, idVehicule, dateDebut, dateFin);
		int idLocation = locDAO.ajoutLocation(user, laLocation);
		System.out.println(idLocation);
		System.out.println(laLocation);
		laLocation.setIdLocation(idLocation);
		

		try {

		} catch (Exception e) {

			
			System.out.println("Interieur Catch NewLocationVehicule");
			System.out.println(e.getMessage());

		}

	}

	public void setMain(MainApp mainApp) {
		// TODO Auto-generated method stub

		this.mainApp = mainApp;
		vehiculeTable.setItems(mainApp.getListTableVehicule());
		vehiculeClientTable.setItems(mainApp.getListTableVehiculeClient());
		tableauAdmin.setItems(mainApp.getListTableAdmin());
		

	}

	public void setDialogStage(Stage dialogStage) {
		// TODO Auto-generated method stub

	}

	public boolean isOkClicked() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@FXML
	private void btnChoixCombo(ActionEvent event) {

		System.out.println("Teest debut Recherche");

		ObservableList<Vehicule> listTableVehicule = FXCollections.observableArrayList();
		Ville ville = comboBoxVille.getValue();
		LocalDate startDate = this.dateDepart.getValue();
		System.out.println(startDate);
		LocalDate endDate = this.dateRetour.getValue();
		VehiculeDAO vlDAO = new VehiculeDAO();
		listTableVehicule = vlDAO.findByVille(ville,startDate,endDate);
		vehiculeTable.setItems(listTableVehicule);

		System.out.println("Teest fin Recherche");

	}
	
	@FXML
	private void displayAdmin(ActionEvent event) {

		System.out.println("Teest debut DisplayAdmin");
		

		ObservableList<Location> listAdminTableAdmin = FXCollections.observableArrayList();

		
		if(mainApp.getUser().getPointFidelite().getValue().equals(1)) {
			System.out.println("TEST1");
		LocationDAO locDAO = new LocationDAO();
		System.out.println("TEST2");
		listTableAdmin = locDAO.tableauAdmin();
		System.out.println("TEST3");
		tableauAdmin.setItems(listTableAdmin);

		System.out.println("Teest fin Recherche");
		} else {
			System.out.println(mainApp.getUser().getPointFidelite());
			System.out.println("Interieur else displayAdmin");
		}

	}
	
	@FXML
	private void tableCaGlobal(ActionEvent event) {

		
		//RESTE A FAIRE (Verif l'implementation des élements dans le tableau)
		
		System.out.println("Teest debut testTEST");
		

		ObservableList<TestAdmin> listCaGlobal = FXCollections.observableArrayList();

		
		if(mainApp.getUser().getPointFidelite().getValue().equals(1)) {
			System.out.println("TEST1");
		TestAdminDAO tADAO = new TestAdminDAO();
		System.out.println("TEST2");
		listCaGlobal = tADAO.tableauTest();
		System.out.println("TEST3");
		tableauTest.setItems(listCaGlobal);

		System.out.println("Teest fin Recherche");
		} else {
			System.out.println(mainApp.getUser().getPointFidelite());
			System.out.println("Interieur else displayAdmin");
		}

	}
	
	
	@FXML
	private void tableCaSelect(ActionEvent event) {
		
		//RESTE A FAIRE 

		System.out.println("Interieur tableCaSelect Debut, avant if");
		

		ObservableList<TestAdmin> listCaSelect = FXCollections.observableArrayList();

		
		if(mainApp.getUser().getPointFidelite().getValue().equals(1)) {

			TestAdminDAO tADAO = new TestAdminDAO();

			Ville ville = cbVille.getValue();
			Marque marque = cbMarque.getValue();
			String startDate = this.intervalDebut.getValue().toString();
			System.out.println(startDate);
			String endDate = this.intervalFin.getValue().toString();
			
			listCaSelect = tADAO.tableauTestSelect(ville,marque,startDate,endDate);
			tableauTest.setItems(listCaSelect);

			System.out.println("Teest fin Recherche");

		} else {
			System.out.println(mainApp.getUser().getPointFidelite());
			System.out.println("Interieur else displayAdmin");
		}

	}
	
	private String initializeIntervalDebut() {

		intervalDebut.setOnAction(event -> {
			LocalDate date = intervalDebut.getValue();
			System.out.println("Selected date:" + date);
		});
		return null;

	}

	private String initializeIntervalFin() {

		intervalFin.setOnAction(event -> {
			LocalDate date = intervalFin.getValue();
			System.out.println("Selected date:" + date);
		});
		return null;

	}



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		initializeComboBoxVille();
		initializeComboBoxVille2();
		initializeComboBoxVille3();
		initializeComboBoxMarque();
		initializeComboBoxMarque2();
		initializeDateDepart();
		initializeDateRetour();

		// Vehicule
		VehiculeDAO vlDAO = new VehiculeDAO();
		listTableVehicule.addAll(vlDAO.tableauVehicule());

		// TableViewVehicule

		marqueColumn.setCellValueFactory(cellData -> cellData.getValue().getMarque().getValue().getLibelleM());
		matriculeColumn.setCellValueFactory(cellData -> cellData.getValue().getMatricule());
		anneeColumn.setCellValueFactory(cellData -> cellData.getValue().getAnneeMeS());
		kmColumn.setCellValueFactory(cellData -> cellData.getValue().getKmVehicule().asObject());
		tarifColumn.setCellValueFactory(cellData -> cellData.getValue().getTarif().asObject());

		// TableViewVehicule Client

		matriculeClientColumn.setCellValueFactory(cellData -> cellData.getValue().getMatricule());
		marqueClientColumn.setCellValueFactory(cellData -> cellData.getValue().getMarque().getValue().getLibelleM());
		anneeClientColumn.setCellValueFactory(cellData -> cellData.getValue().getAnneeMeS());
		kmClientColumn.setCellValueFactory(cellData -> cellData.getValue().getKmVehicule().asObject());
		tarifClientColumn.setCellValueFactory(cellData -> cellData.getValue().getTarif().asObject());


//		///Table Admin
		
		LocationDAO locDAO = new LocationDAO();
		listTableAdmin.addAll(locDAO.tableauAdmin());
		
		idLocationColumnAdm.setCellValueFactory(cellData -> cellData.getValue().getIdLocation().asObject());
		idUserColumnAdm.setCellValueFactory(cellData -> cellData.getValue().getIdUser().asObject());
		idVehiculeColumnAdm.setCellValueFactory(cellData -> cellData.getValue().getIdVehicule().asObject());
		dateDebutLocColumnAdm.setCellValueFactory(cellData -> cellData.getValue().getDateDebut());
		dateFinLocColumnAdm.setCellValueFactory(cellData -> cellData.getValue().getDateFin());
		

		//Table TESTCaSelect
		
		TestAdminDAO tADAO = new TestAdminDAO();
		listTestCa.addAll(tADAO.tableauTest());
		

		intervalDebutColumn.setCellValueFactory(cellData -> cellData.getValue().getDateDebutTest());
		intervalFinColumn.setCellValueFactory(cellData -> cellData.getValue().getDateFinTest());
		libelleMarqueColumn.setCellValueFactory(cellData -> cellData.getValue().getMarqueTest().getValue().getLibelleM());
		libelleVilleColumn.setCellValueFactory(cellData -> cellData.getValue().getVilleTest().getValue().getLibelleV());
		caColumn.setCellValueFactory(cellData -> cellData.getValue().getTarifTest().asObject());
		

		
}
}
