package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class OptionsController {
	
	@FXML Button facileButton;
	@FXML Button moyenButton;
	@FXML Button difficileButton;
	@FXML Button retourButton;
	
	@FXML Spinner<Integer> hSpinner;
	@FXML Spinner<Integer> eSpinner;
	@FXML TextField aText;
	@FXML Label errorLabel;
	
	
	int nbHiddenLayers;
	double learningRate;
	int epochs;
	
	public void initialize() {
		hSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 999));
		eSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 999999));
		aText.setText("0");
    }
	
	
	public void facileClickEvent(ActionEvent event) {
		hSpinner.getValueFactory().setValue(3);
		eSpinner.getValueFactory().setValue(50);
		aText.setText("0.9");
	}
	
	public void moyenClickEvent(ActionEvent event) {
		hSpinner.getValueFactory().setValue(10);
		eSpinner.getValueFactory().setValue(500);
		aText.setText("0.5");
	}
	
	public void difficileClickEvent(ActionEvent event) {
		hSpinner.getValueFactory().setValue(15);
		eSpinner.getValueFactory().setValue(1000);
		aText.setText("0.1");
	}
	
	
	public void validerClickEvent(ActionEvent event) throws IOException {
		
		try
	    {
	      Double valueOfaText = Double.parseDouble(aText.getText());
	      BufferedWriter writer = new BufferedWriter(new FileWriter("save_options"));
		  writer.write(hSpinner.getValue() + "\n" + eSpinner.getValue() + "\n" + valueOfaText);
		  writer.close();
	    }
	    catch (NumberFormatException nfe)
	    {
	      errorLabel.setText("Veuillez rentrez un nombre");
	    }
		
		
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
