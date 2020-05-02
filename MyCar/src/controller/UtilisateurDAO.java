package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Marque;
import model.Utilisateur;
import model.Vehicule;

public class UtilisateurDAO {
	
	public Integer ajoutUtilisateur(Utilisateur utilisateur) {
		
		Connection connexion = ConnectDB.initConnection();
		
		int idUser = utilisateur.getIdUser().get();
		String nomUser = utilisateur.getNomUser().get();
		String prenomUser = utilisateur.getPrenomUser().get();
		int telUser = utilisateur.getTelUser().get();
		String mailUser = utilisateur.getMailUser().get();
		int pointFidel = utilisateur.getPointFidelite().get();
		String mdp = utilisateur.getMdp().get();
		
		System.out.println("Avant SQL ");
		try {
			System.out.println("Interieur Try");
			String sqlNew;
			sqlNew = "INSERT INTO utilisateur (nomUser,prenomUser,telUser,mailUser,pointFidelite,mdp) "
					+ "VALUES" + "('"+ nomUser +"','"+ prenomUser +"','"+ telUser +"','"+ mailUser+"','"
					+ pointFidel +"','"+ mdp +"' )";
			
			System.out.println(idUser);
			PreparedStatement st = connexion.prepareStatement(sqlNew, Statement.RETURN_GENERATED_KEYS);
			st.executeUpdate();
			System.out.println(sqlNew);
			System.out.println("Après SQL Avant GeneratedKeys");
			ResultSet rs = st.getGeneratedKeys();
			if (rs.next()) {
				int pk = rs.getInt(1);
				System.out.println("Generated PK = " + pk);

				return pk;
			}
			
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	


}
