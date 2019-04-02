package controller;

import java.awt.Button;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sun.javafx.geom.Rectangle;

import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
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
import javafx.util.Duration;

@SuppressWarnings("restriction")
public class GameController {
	@FXML private Label myMessage;
	
	@FXML private AnchorPane case0;
	@FXML private AnchorPane case1; @FXML private AnchorPane case2; @FXML private AnchorPane case3;
	@FXML private AnchorPane case4; @FXML private AnchorPane case5; @FXML private AnchorPane case6;
	@FXML private AnchorPane case7; @FXML private AnchorPane case8;
	
	@FXML private AnchorPane gamePane; 
	@FXML private AnchorPane endGamePane;
	
	ArrayList<AnchorPane> listCase = new ArrayList<AnchorPane>();
	ArrayList<AnchorPane> caseToMakeVisible = new ArrayList<AnchorPane>();
	
	public Morpion morpion;
	
	
	public boolean click = false;
	public int playerTurn = 0;
	public int nbTurn = 0;
	

	public void initialize() {
		
		listCase.add(case0);
		listCase.add(case1);
		listCase.add(case2);
		listCase.add(case3);
		listCase.add(case4);
		listCase.add(case5);
		listCase.add(case6);
		listCase.add(case7);
		listCase.add(case8);
		
		morpion = new Morpion();
		
		for (int i = 0 ; i < morpion.taillePlat ; i++) {
			morpion.plateau[i].id = "case" + i;
		}

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
		AnchorPane apClicked = (AnchorPane) source;
		String idSource = apClicked.getId();
		
		if (playerTurn == 0) {
			switch(idSource) {
			case "case0" :
				morpion.plateau[0].contenu = "O";
				morpion.plateau[0].ivIsRound();
				break;
			case "case1" : 
				morpion.plateau[1].contenu = "O";
				morpion.plateau[1].ivIsRound();
				break;
			case "case2" : 
				morpion.plateau[2].contenu = "O";
				morpion.plateau[2].ivIsRound();
				break;
			case "case3" :
				morpion.plateau[3].contenu = "O";
				morpion.plateau[3].ivIsRound();
				break;
			case "case4" :
				morpion.plateau[4].contenu = "O";
				morpion.plateau[4].ivIsRound();
				break;
			case "case5" :
				morpion.plateau[5].contenu = "O";
				morpion.plateau[5].ivIsRound();
				break;
			case "case6" :
				morpion.plateau[6].contenu = "O";
				morpion.plateau[6].ivIsRound();
				break;
			case "case7" :
				morpion.plateau[7].contenu = "O";
				morpion.plateau[7].ivIsRound();
				break;
			case "case8" :
				morpion.plateau[8].contenu = "O";
				morpion.plateau[8].ivIsRound();
				break;
			}
			
		}
		else if(playerTurn == 1) {
			switch(idSource) {
			case "case0" :
				morpion.plateau[0].contenu = "X";
				morpion.plateau[0].ivIsCross();
				break;
			case "case1" :
				morpion.plateau[1].contenu = "X";
				morpion.plateau[1].ivIsCross();
				break;
			case "case2" :
				morpion.plateau[2].contenu = "X";
				morpion.plateau[2].ivIsCross();
				break;
			case "case3" :
				morpion.plateau[3].contenu = "X";
				morpion.plateau[3].ivIsCross();
				break;
			case "case4" :
				morpion.plateau[4].contenu = "X";
				morpion.plateau[4].ivIsCross();
				break;
			case "case5" : 
				morpion.plateau[5].contenu = "X";
				morpion.plateau[5].ivIsCross();
				break;
			case "case6" :
				morpion.plateau[6].contenu = "X";
				morpion.plateau[6].ivIsCross();
				break;
			case "case7" :
				morpion.plateau[7].contenu = "X";
				morpion.plateau[7].ivIsCross();
				break;
			case "case8" : 
				morpion.plateau[8].contenu = "X";
				morpion.plateau[8].ivIsCross();
				break;
			}
		}
		
		
		// Check if a player won
		if (morpion.checkWinCondition() == true) {
			myMessage.setText("Le joueur " + this.playerTurn + " a gagne !");
			endGameTransitions();
			
			
		}
		else if(this.nbTurn == 9) {
			myMessage.setText("Egalite...");
			endGameTransitions();
		}
		
		if (playerTurn == 0) playerTurn++;
		else playerTurn = 0;
		
		((AnchorPane) source).setDisable(true);
		
	}


	public void endGameTransitions() {
		
		gamePane.setDisable(true);
		
		if (morpion.winnerCase.size() != 0) {
			for (int i = 0 ; i < morpion.taillePlat ; i++) {
				if ( (morpion.plateau[i] != morpion.winnerCase.get(0) && morpion.plateau[i] != morpion.winnerCase.get(1) && morpion.plateau[i] != morpion.winnerCase.get(2)) == true) {
					for (int j = 0 ; j < listCase.size() ; j++) {
						if (listCase.get(j).getId().equals(morpion.plateau[i].id)) {
							caseToMakeVisible.add(listCase.get(j));
							FadeTransition caseOutTransition = new FadeTransition(Duration.millis(1000), listCase.get(j));
							caseOutTransition.setToValue(0.0);
							caseOutTransition.play();
						}
					}
					
				}
			}
		}
		
		
		FadeTransition gamePaneTransition = new FadeTransition(Duration.millis(1500), gamePane);
		gamePaneTransition.setFromValue(1.0);
		gamePaneTransition.setToValue(0.5);
		gamePaneTransition.play();
		
		endGamePane.setVisible(true);
		endGamePane.setDisable(false);
		FadeTransition endGamePaneTransition = new FadeTransition(Duration.millis(1500), endGamePane);
		endGamePaneTransition.setFromValue(0.0);
		endGamePaneTransition.setToValue(0.9);
		endGamePaneTransition.play();
	}
	
	
	public void startGameTransitions() {
		gamePane.setDisable(true);
		
		for (int i = 0 ; i < caseToMakeVisible.size() ; i++) {
			FadeTransition caseInTransition = new FadeTransition(Duration.millis(1500), caseToMakeVisible.get(i));
			caseInTransition.setToValue(1.0);
			caseInTransition.play();
		}
		caseToMakeVisible.clear();
		
		FadeTransition gamePaneTransition = new FadeTransition(Duration.millis(1500), gamePane);
		gamePaneTransition.setFromValue(0.5);
		gamePaneTransition.setToValue(1.0);
		gamePaneTransition.play();
		
		
		endGamePane.setVisible(false);
		endGamePane.setDisable(false);
		
		/*
		FadeTransition endGamePaneTransition = new FadeTransition(Duration.millis(1500), endGamePane);
		endGamePaneTransition.setFromValue(0.9);
		endGamePaneTransition.setToValue(0.0);
		endGamePaneTransition.play();
		*/
	}
	
	public void resetButtonClickEvent(Event e) {
		newGame();
	}


	private void newGame() {
		setMorpionToEnable();
		resetMorpion();
		this.nbTurn = 0;
		myMessage.setText("");
	}
	
	public void playAgainClickEvent(Event e) {
		System.out.println("damn");
		startGameTransitions();
		endGamePane.setDisable(true);
		gamePane.setDisable(false);
		//Thread.sleep(1500);
		newGame();
	}
	
	public void getClickedDuh(Event e) {
		System.out.println("getClickedDuh");
	}
	

	public void optionsButtonEvent(ActionEvent event) {
		// nothing for the moment
	}
	
	
	
	public void setMorpionToDisable() {
		for (int i = 0 ; i < listCase.size() ; i++) {
			listCase.get(i).setDisable(true);
		}
	}
	
	public void setMorpionToEnable() {
		for (int i = 0 ; i < listCase.size() ; i++) {
			listCase.get(i).setDisable(false);
		}
	}
	
	public void resetMorpion() {
		morpion.winnerCase.clear();
		for (int i = 0 ; i < 9 ; i++) {
			morpion.plateau[i].contenu = null;
			morpion.plateau[i].iv = null;
			listCase.get(i).getChildren().clear();
		}
	}
	
	public void printMatrice() {
		System.out.println(morpion.plateau[0].contenu + morpion.plateau[1].contenu + morpion.plateau[2].contenu);
		System.out.println(morpion.plateau[3].contenu + morpion.plateau[4].contenu + morpion.plateau[5].contenu);
		System.out.println(morpion.plateau[6].contenu + morpion.plateau[7].contenu + morpion.plateau[8].contenu);
	}

}
