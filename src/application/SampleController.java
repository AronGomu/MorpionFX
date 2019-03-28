package application;

import java.awt.Button;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SampleController {
	private Button vsPlayerButton; //1st button
    private Button J2inc; //2nd button
    private Button J1inc; //3rd button

    @FXML
    private ImageView Img1;

    private ImageView Img2;

    
    
    public void initialize() {
    	
    	vsPlayerButton = new Button();
    }

    
    
    void vsPlayerButtonEvent(ActionEvent event) {
        System.out.println("Hello boi");
    }

    void Inc1(ActionEvent event) {
        //nothing for the moment
    }

    void Nextimg(ActionEvent event) {
        //nothing for the moment
    }
}
