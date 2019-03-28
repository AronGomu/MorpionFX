package controller;

import java.awt.Button;
import java.io.IOException;
import java.util.List;

import com.sun.javafx.geom.Rectangle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

@SuppressWarnings("restriction")
public class GameController {
	@FXML private Label myMessage;
	@FXML private AnchorPane case1; @FXML private ImageView XImage1; @FXML private ImageView OImage1;
	@FXML private AnchorPane case2; @FXML private ImageView XImage2; @FXML private ImageView OImage2;
	
	public boolean click = false;
	public int playerTurn = 0;
	public int nbTurn = 0;
	public String[] matrice = new String[9];
	

	public void initialize() {

		case1 = new AnchorPane();

	}
	

	public void quitButtonEvent(ActionEvent event) {

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
	}

	public void caseMouseEntered(Event e) {
		Object source = e.getSource();
		if (source instanceof AnchorPane) {  //check that the source is really an AnchorPane
            ObservableList<Node> childList = ((AnchorPane) source).getChildren(); //cast the source to a button
            
            Image imgX = new Image("image/cross.png");
	        ImageView imgViewX = new ImageView(imgX);
	        
	        Image imgO = new Image("image/round.png");
	        ImageView imgViewO = new ImageView(imgO);
	        
	        switch (this.playerTurn) {
	        case 0 : ((AnchorPane) source).getChildren().add(imgViewO); break;
	        case 1 : ((AnchorPane) source).getChildren().add(imgViewX); break;
    		}
        }
	}
	
	public void caseMouseExited(Event e) {
		Object source = e.getSource();
		if (click == false) {
			((AnchorPane) source).getChildren().remove(0);
			
		}
		else {
			click = false;
		}
	}
	
	public void caseMouseClicked(Event e) {
		
		click = true;
		nbTurn++;
		
		Object source = e.getSource();
		String idSource = ((AnchorPane) source).getId();
		if (playerTurn == 0) {
			switch(idSource) {
			case "case1" : matrice[0] = "O"; break;
			case "case2" : matrice[1] = "O"; break;
			case "case3" : matrice[2] = "O"; break;
			case "case4" : matrice[3] = "O"; break;
			case "case5" : matrice[4] = "O"; break;
			case "case6" : matrice[5] = "O"; break;
			case "case7" : matrice[6] = "O"; break;
			case "case8" : matrice[7] = "O"; break;
			case "case9" : matrice[8] = "O"; break;
			}
			
		}
		else if(playerTurn == 1) {
			switch(idSource) {
			case "case1" : matrice[0] = "X"; break;
			case "case2" : matrice[1] = "X"; break;
			case "case3" : matrice[2] = "X"; break;
			case "case4" : matrice[3] = "X"; break;
			case "case5" : matrice[4] = "X"; break;
			case "case6" : matrice[5] = "X"; break;
			case "case7" : matrice[6] = "X"; break;
			case "case8" : matrice[7] = "X"; break;
			case "case9" : matrice[8] = "X"; break;
			}
		}
		
		if (this.checkWinCondition() == true) {
			myMessage.setText("Le joueur " + this.playerTurn + " a gagné !");
		}
		else if(this.nbTurn == 9) {
			myMessage.setText("Egalité...");
		}
		
		if (playerTurn == 0) playerTurn++;
		else playerTurn = 0;
		
		((AnchorPane) source).setDisable(true);
		
		System.out.println("click : " + click);
	}
	
	public void replayButtonClickEvent(Event e) {
		System.out.println("Button is being clicked");
	}
	
	public void getClickedDuh(Event e) {
		System.out.println("Button is being clicked");
	}
	

	public void optionsButtonEvent(ActionEvent event) {
		// nothing for the moment
	}
	
	
	public boolean checkWinCondition() {
		
		printMatrice();
		
		// ligne
		if (this.matrice[0] == this.matrice[1] && this.matrice[1] == this.matrice[2]) {
			if (this.matrice[0] != null) return true;
		}
		if (this.matrice[3] == this.matrice[4] && this.matrice[4] == this.matrice[5]) {
			if (this.matrice[3] != null) return true;
		}
		if (this.matrice[6] == this.matrice[7] && this.matrice[7] == this.matrice[8]) {
				if (this.matrice[6] != null) return true;
		}
		
		// colonne
		if (this.matrice[0] == this.matrice[3] && this.matrice[3] == this.matrice[6]) {
			if (this.matrice[0] != null) return true;
		}
		if (this.matrice[1] == this.matrice[4] && this.matrice[4] == this.matrice[7]) {
			if (this.matrice[1] != null) return true;
		}
		if (this.matrice[2] == this.matrice[5] && this.matrice[5] == this.matrice[8]) {
			if (this.matrice[2] != null) return true;
		}
		// diag
		if (this.matrice[0] == this.matrice[4] && this.matrice[4] == this.matrice[8]) {
			if (this.matrice[0] != null) return true;
		}
		if (this.matrice[2] == this.matrice[4] && this.matrice[4] == this.matrice[6]) {
			if (this.matrice[2] != null) return true;
		}
		
		return false;
	}
	
	public void resetMatrice() {
		for (int i = 0 ; i < 9 ; i++) {
			matrice[i] = null;
		}
	}
	
	public void printMatrice() {
		System.out.println(matrice[0] + matrice[1] + matrice[2]);
		System.out.println(matrice[3] + matrice[4] + matrice[5]);
		System.out.println(matrice[6] + matrice[7] + matrice[8]);
	}

}
