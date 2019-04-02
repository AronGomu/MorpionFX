package controller;

import java.awt.Button;
import java.io.IOException;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MenuController {
	
	@FXML
	private AnchorPane bgPane; //1st button
	
	FadeTransition bgPaneTransition;

    
    
    public void initialize() {
    	
    }

    
    
    public void vsPlayerButtonEvent(ActionEvent event) {
    	
    	endGameTransitions();
    	
    	Parent loader = null;
		
    	try {
			loader = FXMLLoader.load(getClass().getResource("/controller/Game.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
    	Scene scene = new Scene(loader);
    	
    	
    	bgPaneTransition.setOnFinished(e -> {
    		stage.setScene(scene);
    		stage.show();
    	});
    }

    public void vsIAButtonEvent(ActionEvent event) {
        System.out.println("vsIAButtonEvent");
    }

    public void optionsButtonEvent(ActionEvent event) {
    	
    	endGameTransitions();
    	
    	Parent loader = null;
		
    	try {
			loader = FXMLLoader.load(getClass().getResource("/controller/Options.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
    	Scene scene = new Scene(loader);
    	
    	
    	stage.setScene(scene);
    	stage.show();
    }
    
    
    public void endGameTransitions() {
		
    	bgPane.setDisable(true);
    	bgPaneTransition = new FadeTransition(Duration.millis(1000), bgPane);
		
    	bgPaneTransition.setFromValue(1.0);
    	bgPaneTransition.setToValue(0.0);
    	bgPaneTransition.play();
		
		
		/*
		bgPane.setVisible(true);
		bgPane.setDisable(false);
		FadeTransition endGamePaneTransition = new FadeTransition(Duration.millis(1500), endGamePane);
		endGamePaneTransition.setFromValue(0.0);
		endGamePaneTransition.setToValue(1.0);
		endGamePaneTransition.play();
		*/
	}
}
