package controller;

import java.awt.Button;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MenuController {
	
	@FXML
	private Button vsPlayerButton; //1st button
	
	
    private Button vsIAButtonEvent; //2nd button
    private Button optionsButtonEvent; //3rd button

    private ImageView Img1;
    private ImageView Img2;

    
    
    public void initialize() {
    	
    	//vsPlayerButton = new Button();
    }

    
    
    public void vsPlayerButtonEvent(ActionEvent event) {
    	
    	Parent loader = null;
		try {
			loader = FXMLLoader.load(getClass().getResource("/controller/Game.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
    	
    	Scene scene = new Scene(loader);
    	
    	//scene.getStylesheets().add("fxml/prettierGame.css");
    	stage.setScene(scene);
    	
		stage.show();
    }

    public void vsIAButtonEvent(ActionEvent event) {
        //nothing for the moment
    }

    public void optionsButtonEvent(ActionEvent event) {
        //nothing for the moment
    }
}
