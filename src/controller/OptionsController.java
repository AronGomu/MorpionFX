package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class OptionsController {
	
	@FXML Button facileButton;
	@FXML Button moyenButton;
	@FXML Button difficileButton;
	@FXML Button retourButton;
	
	
	int nbHiddenLayers;
	double learningRate;
	int epochs;
	
	
	public void facileClickEvent(ActionEvent event) {
		System.out.println("facile");
	}
	
	public void moyenClickEvent(ActionEvent event) {
		System.out.println("moyen");
	}
	
	public void difficileClickEvent(ActionEvent event) {
		System.out.println("difficile");
	}
	
	
	public void retourClickEvent(ActionEvent event) {
		Parent loader = null;
		
		try {
			loader = FXMLLoader.load(getClass().getResource("/controller/Menu.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
		Scene scene = new Scene(loader);
		
		stage.setScene(scene);
		stage.show();
		
		return;
	}
}
