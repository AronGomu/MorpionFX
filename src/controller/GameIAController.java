package controller;

import java.awt.Button;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.sun.javafx.geom.Rectangle;

import ia.Agent;
import ia.Coup;
import ia.LearningMlp;
import ia.MultiLayerPerceptron;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
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
public class GameIAController {
	@FXML public Label myMessage;
	
	@FXML private AnchorPane case0;
	@FXML private AnchorPane case1; @FXML private AnchorPane case2; @FXML private AnchorPane case3;
	@FXML private AnchorPane case4; @FXML private AnchorPane case5; @FXML private AnchorPane case6;
	@FXML private AnchorPane case7; @FXML private AnchorPane case8;
	
	@FXML private AnchorPane gamePane; 
	@FXML private AnchorPane endGamePane;
	
	@FXML private Button tryagainButton;
	@FXML private Button returnmenuButton;
	
	ArrayList<AnchorPane> listCase = new ArrayList<AnchorPane>();
	ArrayList<AnchorPane> caseToMakeVisible = new ArrayList<AnchorPane>();
	
	ArrayList<int[]> coupsPlayer0 = new ArrayList<int[]>();
	ArrayList<int[]> coupsPlayer1 = new ArrayList<int[]>();
	
	String urlRonds = "image/round.png";
	String urlCroix = "image/cross.png";
	
	public Morpion morpion;
	
	public MultiLayerPerceptron m;
	
	
	public boolean click = false;
	public int playerTurn = -1; // L'IA est le joueur 1
	public int nbTurn = 0;
	

	public void initialize() throws NumberFormatException, IOException {
		
		m = MultiLayerPerceptron.load("agent.txt");
		
		listCase.add(case0);
		listCase.add(case1);
		listCase.add(case2);
		listCase.add(case3);
		listCase.add(case4);
		listCase.add(case5);
		listCase.add(case6);
		listCase.add(case7);
		listCase.add(case8);
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("save_options")));
		String line = "" ;
		int count = 0;
		while ( (line=br.readLine()) != null ) {
			count++;
			System.out.println("line : " + line);
			if (count == 4) {
				if (line != "") {
					File tempFile = new File(line);
					if (tempFile.exists()) {
						urlRonds = line;
					}
					else {
						System.out.println("error");
					}
					
				}
			}
			if (count == 5) {
				if (line != "") {
					File tempFile = new File(line);
					if (tempFile.exists()) {
						urlCroix = line;
					}
					else {
						System.out.println("error");
					}
					
				}
			}
		}
		br.close();
		
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
            
            Image imgX = new Image(urlCroix);
	        ImageView imgViewX = new ImageView(imgX);
	        
	        Image imgO = new Image(urlRonds);
	        ImageView imgViewO = new ImageView(imgO);
	        
	        switch (this.playerTurn) {
	        case -1 : ((AnchorPane) source).getChildren().add(imgViewO); break;
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
	
	public void caseMouseClicked(Event e) throws IOException {
		
		
		click = true;
		nbTurn++;
		
		Object source = e.getSource();
		AnchorPane apClicked = (AnchorPane) source;
		String idSource = apClicked.getId();
		
		((AnchorPane) source).setDisable(true);
		
		saveCoup(playerTurn);
		
		
		if (playerTurn == -1) {
			
			// Modify content in case
			switch(idSource) {
			case "case0" :
				morpion.plateau[0].contenu = -1;
				morpion.plateau[0].ivIsRound();
				break;
			case "case1" : 
				morpion.plateau[1].contenu = -1;
				morpion.plateau[1].ivIsRound();
				break;
			case "case2" : 
				morpion.plateau[2].contenu = -1;
				morpion.plateau[2].ivIsRound();
				break;
			case "case3" :
				morpion.plateau[3].contenu = -1;
				morpion.plateau[3].ivIsRound();
				break;
			case "case4" :
				morpion.plateau[4].contenu = -1;
				morpion.plateau[4].ivIsRound();
				break;
			case "case5" :
				morpion.plateau[5].contenu = -1;
				morpion.plateau[5].ivIsRound();
				break;
			case "case6" :
				morpion.plateau[6].contenu = -1;
				morpion.plateau[6].ivIsRound();
				break;
			case "case7" :
				morpion.plateau[7].contenu = -1;
				morpion.plateau[7].ivIsRound();
				break;
			case "case8" :
				morpion.plateau[8].contenu = -1;
				morpion.plateau[8].ivIsRound();
				break;
			}
			
		}
		else if(playerTurn == 1) {
			switch(idSource) {
			case "case0" :
				morpion.plateau[0].contenu = 1;
				morpion.plateau[0].ivIsCross();
				break;
			case "case1" :
				morpion.plateau[1].contenu = 1;
				morpion.plateau[1].ivIsCross();
				break;
			case "case2" :
				morpion.plateau[2].contenu = 1;
				morpion.plateau[2].ivIsCross();
				break;
			case "case3" :
				morpion.plateau[3].contenu = 1;
				morpion.plateau[3].ivIsCross();
				break;
			case "case4" :
				morpion.plateau[4].contenu = 1;
				morpion.plateau[4].ivIsCross();
				break;
			case "case5" : 
				morpion.plateau[5].contenu = 1;
				morpion.plateau[5].ivIsCross();
				break;
			case "case6" :
				morpion.plateau[6].contenu = 1;
				morpion.plateau[6].ivIsCross();
				break;
			case "case7" :
				morpion.plateau[7].contenu = 1;
				morpion.plateau[7].ivIsCross();
				break;
			case "case8" : 
				morpion.plateau[8].contenu = 1;
				morpion.plateau[8].ivIsCross();
				break;
			}
			
		}
		
		saveCoup(playerTurn);
		
		
		// Check if a player won
		if (morpion.checkWinCondition() == true) {
			myMessage.setText("Le joueur " + this.playerTurn + " a gagne !");
			
			saveGame(playerTurn);
			
			endGameTransitions();
		}
		
		
		else if(this.nbTurn == 9) {
			myMessage.setText("Egalite...");
			endGameTransitions();
		}
		// IA PLAY
		else if (this.nbTurn < 9) {
			
			
			playerTurn = playerTurn*-1;
			
			double[] plateau = new double[9];
			for (int i = 0 ; i < morpion.taillePlat ; i++) {
				plateau[i] = morpion.plateau[i].contenu;
			}
			double[] pourcentage = m.forwardPropagation(plateau);
			ArrayList<Integer> valeursImpossible = new ArrayList<Integer>();
			int indice = agentPlay(pourcentage, valeursImpossible);
			morpion.plateau[indice].contenu = 1;
			morpion.plateau[indice].ivIsCross();
			
	        
	        switch (this.playerTurn) {
	        case -1 :
	        	Image imgO = new Image(urlRonds);
		        ImageView imgViewO = new ImageView(imgO);
		        listCase.get(indice).getChildren().add(imgViewO); 
	        	break;
	        case 1 :
	        	Image imgX = new Image(urlCroix);
		        ImageView imgViewX = new ImageView(imgX);
		        listCase.get(indice).getChildren().add(imgViewX); 
	        	break;
    		}
	        
	        listCase.get(indice).setDisable(true);
	        
	        nbTurn++;
	        playerTurn = playerTurn*-1;
	        
	     // Check if a player won
			if (morpion.checkWinCondition() == true) {
				myMessage.setText("Le joueur " + this.playerTurn + " a gagne !");
				
				saveGame(playerTurn);
				
				endGameTransitions();
			}
	        
	        
	        
			
		}
		
		
	}
	
	private void checkWin() throws IOException {
		if (morpion.checkWinCondition() == true) {
			myMessage.setText("Le joueur " + this.playerTurn + " a gagne !");
			
			saveGame(playerTurn);
			
			endGameTransitions();
			
			
		}
	}
	
	private int agentPlay(double[] pourcentage, ArrayList<Integer> valeursImpossible) {
		double maxP = 0;
		int indice = 0;
		boolean canBeIndice = true;
		for (int i = 0 ; i < morpion.taillePlat ; i++) {
			canBeIndice = true;
			if (pourcentage[i] > maxP) {
				for (int j = 0 ; j < valeursImpossible.size() ; j++) {
					if (i == valeursImpossible.get(j)) canBeIndice = false;
				}
				if (canBeIndice == true) {
					maxP = pourcentage[i];
					indice = i;
				}
			}
		}
		if (morpion.plateau[indice].contenu != 0) {
			valeursImpossible.add(indice);
			return agentPlay(pourcentage, valeursImpossible);
		}
		else return indice;
	}
	
	
	private void saveGame(int joueur) throws IOException {
		if (joueur == -1) {
			/*
			HashSet<Coup> toLearnFrom = new HashSet<>();
			double[] in = new double[morpion.taillePlat];
			double[] out = new double[morpion.taillePlat];
			for (int i = 0 ;  i < coupsPlayer0.size() ;  i++) {
				for (int j = 0 ; j < morpion.taillePlat ; j++) {
					coupsPlayer0.get(i)[j] = coupsPlayer0.get(i)[j]*this.playerTurn;
				}
			}
			
			for (int i = 0 ;  i < coupsPlayer0.size() && i < coupsPlayer0.size() ;  i++) {
				for (int j = 0 ; j < morpion.taillePlat ; j++) {
					in[i] = coupsPlayer0.get(i)[j];
				}
				i++;
				for (int j = 0 ; j < morpion.taillePlat && i < coupsPlayer0.size() ; j++)
				{
					out[i] = coupsPlayer0.get(i)[j];
				}
				toLearnFrom.add(new Coup(in, out));
			}
			LearningMlp.learnDuringGame(toLearnFrom, agent.net, agent.epochs);
			*/
			
			BufferedWriter writer = new BufferedWriter(new FileWriter("save.txt",true));
			String lineToWrite;
			for (int i = 0 ;  i < coupsPlayer0.size() ; i++) {
				lineToWrite = "";
				for (int j = 0 ; j < morpion.taillePlat ; j++) {
					if (j+1 != morpion.taillePlat) 
						lineToWrite += coupsPlayer0.get(i)[j] + ",";
					else lineToWrite += coupsPlayer0.get(i)[j] + "\t";
				}
				
				i++;
				
				for (int j = 0 ; j < morpion.taillePlat ; j++) {
					if (j+1 != morpion.taillePlat) 
						lineToWrite += coupsPlayer0.get(i)[j] + ",";
					else lineToWrite += coupsPlayer0.get(i)[j] + "\n";
				}
				writer.write(lineToWrite);
				
			}
			writer.close();
		}
		else {
			for (int i = 0 ;  i < coupsPlayer1.size() ;  i++) {
				for (int j = 0 ; j < morpion.taillePlat ; j++) {
					coupsPlayer1.get(i)[j] = coupsPlayer1.get(i)[j]*this.playerTurn;
				}
			}
			
			BufferedWriter writer = new BufferedWriter(new FileWriter("save.txt",true));
			String lineToWrite;
			for (int i = 0 ;  i < coupsPlayer1.size() ; i++) {
				lineToWrite = "";
				for (int j = 0 ; j < morpion.taillePlat ; j++) {
					if (j+1 != morpion.taillePlat) 
						lineToWrite += coupsPlayer1.get(i)[j] + ",";
					else lineToWrite += coupsPlayer1.get(i)[j] + "\t";
				}
				
				i++;
				
				for (int j = 0 ; j < morpion.taillePlat ; j++) {
					if (j+1 != morpion.taillePlat) 
						lineToWrite += coupsPlayer1.get(i)[j] + ",";
					else lineToWrite += coupsPlayer1.get(i)[j] + "\n";
				}
				writer.write(lineToWrite);
				
			}
			writer.close();
		}
		coupsPlayer0.clear();
		coupsPlayer1.clear();
	}


	private void saveCoup(int joueur) {
		// Enregistre le tableau avant le coup
		int[] coup = new int[morpion.taillePlat];
		for (int i = 0 ;  i < morpion.taillePlat ; i++) {
			coup[i] = morpion.plateau[i].contenu;
		}
		
		if (joueur == -1) coupsPlayer0.add(coup);
		else coupsPlayer1.add(coup);
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
		startGameTransitions();
		endGamePane.setDisable(true);
		gamePane.setDisable(false);
		newGame();
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
			morpion.plateau[i].contenu = 0;
			morpion.plateau[i].iv = null;
			listCase.get(i).getChildren().clear();
		}
	}

}
