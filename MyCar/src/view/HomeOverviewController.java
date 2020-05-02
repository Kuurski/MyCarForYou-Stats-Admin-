package view;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import controller.ConnectDB;
import controller.MainApp;
import controller.PropUtilDAO;
import controller.UtilisateurDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import model.Utilisateur;

public class HomeOverviewController {

	//LogIN
	@FXML 
	private Label lblStatus;
	@FXML
	private TextField loginField;
	@FXML
	private TextField mdpField;
	@FXML
	private DatePicker dateDepart;
	@FXML
	private DatePicker dateRetour;
	
	//SignIN
	@FXML
	private Label lblStatusSign;
	@FXML
	private TextField nomField;
	@FXML
	private TextField prenomField;
	@FXML
	private TextField telField;
	@FXML
	private TextField mailField;
	@FXML
	private PasswordField mdpFirstField;
	@FXML
	private PasswordField mdpVerifField;
	

	
	
	
	//Reference to the main application
	private MainApp mainApp;
	private Utilisateur propUtil;
	private AnchorPane rootPane;
	
	Connection con = ConnectDB.initConnection();
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	
	@FXML
	private void logIn(ActionEvent event) {
		
		PropUtilDAO pud = new PropUtilDAO();
		String email = loginField.getText().toString();
		String password = mdpField.getText().toString();
		System.out.println("Entre Login");
		String sqlLog;
		sqlLog = "SELECT * "
				+ "FROM utilisateur "
				+ "WHERE mailUser = ? "
				+ "AND mdp = ? ";
		System.out.println("Avant Try");

		try {
			System.out.println("debut try");
			preparedStatement = con.prepareStatement(sqlLog);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);			
			System.out.println("try aprés password");
			resultSet = preparedStatement.executeQuery();
			System.out.println("fin try");

			if(email.isEmpty() || (password.isEmpty())) {
				lblStatus.setTextFill(Color.TOMATO);
				lblStatus.setText("Field is Empty ");
				System.out.println("Empty");
				
			} else if(resultSet.next()) {
				lblStatus.setTextFill(Color.GREEN);
				lblStatus.setText("Login Successful ... Redirecting");
				System.out.println("Login OK");
				Utilisateur user = new Utilisateur(resultSet.getInt("idUser"),resultSet.getString("nomUser"),resultSet.getString("prenomUser"),resultSet.getInt("telUser"),resultSet.getString("mailUser"),resultSet.getInt("valeur"),resultSet.getString("mdp"));
				mainApp.showMainOverview(user);
				
				
				
			} else {
				lblStatus.setTextFill(Color.TOMATO);
				lblStatus.setText("Enter Correct Email/Password");
				System.out.println("Incorrect");
			}

		} catch (Exception e) {
			System.out.println("Interieur Catch");
			
		}
	
	}
	
	@FXML
	private void signIn(ActionEvent event) throws SQLException {
		
		UtilisateurDAO utilDAO = new UtilisateurDAO();
		
		String nom = nomField.getText();
		String prenom = prenomField.getText();
		int tel = Integer.parseInt(telField.getText());
		String mail = mailField.getText();
		int point = 0;
		String mdp = mdpFirstField.getText();
		String mdpVerif = mdpVerifField.getText();
		
		
		if(mdp.isEmpty() || (mdpVerif.isEmpty())) {
			lblStatusSign.setTextFill(Color.TOMATO);
			lblStatusSign.setText("Field is Empty ");
			System.out.println("Empty");
		
			
				}if(nom.isEmpty() || (prenom.isEmpty())) {
					lblStatusSign.setTextFill(Color.TOMATO);
					lblStatusSign.setText("Field is Empty ");
					System.out.println("Empty");
					
					}if(mail.isEmpty()) {
						lblStatusSign.setTextFill(Color.TOMATO);
						lblStatusSign.setText("Field is Empty ");
						System.out.println("Empty");
								
						} if(mdp.equals(mdpVerif)) { 
							lblStatusSign.setTextFill(Color.GREEN);
							lblStatusSign.setText("Création OK ! Vous pouvez entrer vos Identifiants !");
							System.out.println("Signin OK");
							
							Utilisateur unUtilisateur = new Utilisateur(0,nom,prenom,tel,mail,point,mdp);
							System.out.println(unUtilisateur);
							int addUser = utilDAO.ajoutUtilisateur(unUtilisateur);
								
			} else {
				lblStatusSign.setTextFill(Color.TOMATO);
				lblStatusSign.setText("Enter Correct Field");
				System.out.println("Incorrect");
			}


			
		}
		

	
	private void showDialog(String info, String header, String title) {
		
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setContentText(info);
		alert.setHeaderText(header);
		alert.showAndWait();
	}

	public void setMain(MainApp mainApp) {
		// TODO Auto-generated method stub
		
		this.mainApp = mainApp;
	}
}





