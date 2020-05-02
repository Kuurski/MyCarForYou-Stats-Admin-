package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Marque;
import model.Vehicule;
import model.Ville;

public class TestAdminDAO {
	
	
	public ObservableList<TestAdmin> tableauTest() {
		
		ObservableList<TestAdmin> listCaGlobal = FXCollections.observableArrayList();
		
		String sqlAdm;
		sqlAdm = " SELECT *, SUM(prix) AS prix_total "
				+ "FROM location ";
		
		System.out.println(sqlAdm);
		
		try { 
			
			Connection connexion = ConnectDB.initConnection();
			Statement st = connexion.createStatement();
			ResultSet rs = st.executeQuery(sqlAdm);
			System.out.print("Interieur Try UserDAO, tableauAdmin");
			
			while (rs.next()) {
				
				
				
				System.out.print("Interieur While Debut tableauAdmin");

				
				int idLocation = (1);
				String dateDebut = "De la Premiere Location";
				String dateFin = "A la derniere";
				Ville ville = new Ville(0,null);
				Marque marque = new Marque(0,null);
				int cA = rs.getInt("prix_total");
				
				TestAdmin tabCaGlobal = new TestAdmin(idLocation, dateDebut, dateFin, ville, marque, cA);
				listCaGlobal.add(tabCaGlobal);
				
				System.out.print("Fin While Fin tableauAdmin");
			}
			
			
		} catch (Exception e) {
			System.out.println("Si ce message s'affiche, c'est que tu as un probleme ! listTableTestCA(TestAdminDAO)");
			System.err.println(e.getMessage());
		}
		return listCaGlobal;
	}
	
	
	public ObservableList<TestAdmin> tableauTestSelect(Ville ville, Marque marque, String startDate, String endDate) {
		
		ObservableList<TestAdmin> listCaSelect = FXCollections.observableArrayList();
		
		String sqlAdm;
		sqlAdm = " SELECT  idLocation, libelleV, libelleM, YEAR(dateDebut), SUM(vehicule.tarif) "
				+ "FROM location "
				+ "INNER JOIN vehicule ON location.idVehicule=vehicule.idVehicule "
				+ "INNER JOIN ville ON vehicule.idVille=ville.idVille "
				+ "LEFT JOIN marque ON vehicule.idMarque=marque.idMarque "
			
				+ "WHERE dateDebut BETWEEN '" +startDate+ "' AND '"+endDate+"'"
				+ "AND vehicule.idVille = '"+ville.getIdVille().get()+ "'"
				+ "AND vehicule.idMarque = '"+marque.getIdMarque().get()+"'";
		
		System.out.println(sqlAdm);
		
		try {
			
			Connection connexion = ConnectDB.initConnection();
			Statement st = connexion.createStatement();
			ResultSet rs = st.executeQuery(sqlAdm);
			System.out.print("Interieur Try tableauTestSelect" );
			
			while (rs.next()) {
				
				
				
				System.out.print(" Interieur While Debut tableauTestSelect");

				int idLocation = rs.getInt("idLocation");
				System.out.print("Après Int");
				String dateDebut = startDate;
				String dateFin = endDate;
				System.out.print("Après dateFin");
				int tarif = rs.getInt("SUM(vehicule.tarif)");
				
				TestAdmin tabCaSelect = new TestAdmin(idLocation, dateDebut, dateFin, ville, marque, tarif);
				listCaSelect.add(tabCaSelect);
				
				System.out.print("Fin While");
			}
			
			
		} catch (Exception e) {
			System.out.println("Si ce message s'affiche, c'est que tu as un probleme ! listTableTestCA(TestAdminDAO)");
			System.err.println(e.getMessage());
		}
		return listCaSelect;
	}


}
