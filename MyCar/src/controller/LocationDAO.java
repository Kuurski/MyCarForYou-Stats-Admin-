package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Location;
import model.Marque;
import model.Utilisateur;
import model.Vehicule;
import view.HomeOverviewController;

public class LocationDAO {

	public Integer ajoutLocation(Utilisateur locataire, Location newLocation) throws SQLException {
		
		Connection connexion = ConnectDB.initConnection();
		
		
		int idUser = locataire.getIdUser().get();
		int idLocation = newLocation.getIdLocation().get();
		int idVehicule = newLocation.getIdVehicule().get();
		String dateDebut = newLocation.getDateDebut().get();
		String dateFin = newLocation.getDateFin().get();


		try {

			
			String sqlNew;
			sqlNew = "INSERT INTO location (idUser, idVehicule, dateDebut, dateFin) "
					+ "VALUES" + "('" + idUser + "','"
					+ idVehicule + "','" + dateDebut + "','" + dateFin + "')";


			
			PreparedStatement st = connexion.prepareStatement(sqlNew, Statement.RETURN_GENERATED_KEYS);

			System.out.print("Interieur LocationDAO : " + sqlNew);
			st.executeUpdate();
			System.out.println("Interieur Catch NewLocationVehicule");

			ResultSet rs = st.getGeneratedKeys();
			if (rs.next()) {
				int pk = rs.getInt(1);
				System.out.println("Generated PK = " + pk);

				return pk;
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	public ObservableList<Location> tableauAdmin() {
		
		ObservableList<Location> listTableAdmin = FXCollections.observableArrayList();
		
		String sqlAdm;
		sqlAdm = " SELECT * "
				+ "FROM location,utilisateur,vehicule "
				+ "WHERE location.idVehicule=vehicule.idVehicule "
				+ "AND location.idUser=utilisateur.idUser "
				+ "AND utilisateur.idUser=vehicule.idUser ";
		
		System.out.println(sqlAdm);
		
		try {
			
			Connection connexion = ConnectDB.initConnection();
			Statement st = connexion.createStatement();
			ResultSet rs = st.executeQuery(sqlAdm);
			System.out.print("Interieur Try UserDAO, tableauAdmin");
			
			while (rs.next()) {
				
				
				
				System.out.print("Interieur While Debut tableauAdmin");

				int idLocation = rs.getInt("idLocation");
				int idUser = rs.getInt("idUser");
				int idVehicule = rs.getInt("idVehicule");
				String dateD = rs.getString("dateDebut");
				String dateF = rs.getString("dateFin");

				Location tabAdmin = new Location(idLocation, idUser, idVehicule, dateD, dateF);
				listTableAdmin.add(tabAdmin);
			}
			
			
		} catch (Exception e) {
			System.out.println("Si ce message s'affiche, c'est que tu as un probleme ! listTableAdmin (LocationDAO)");
			System.err.println(e.getMessage());
		}
		return listTableAdmin;
	}
	

}
