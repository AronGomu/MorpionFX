package controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Case {

	String id = null;
	String contenu = null;
	ImageView iv = null;
	
	public Case() {
	
	}
	
	public void ivIsCross() {
		Image imgX = new Image("image/cross.png");
	    iv = new ImageView(imgX);
	    return;
	}
    
	public void ivIsRound() {
		Image imgO = new Image("image/round.png");
	    iv = new ImageView(imgO);
	    return;
	}
	
}
