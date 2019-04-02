package controller;

import java.util.ArrayList;

import javafx.scene.layout.AnchorPane;

public class Morpion {

	public int taillePlat = 9;
	public ArrayList<Case> winnerCase = new ArrayList<Case>();

	public Case[] plateau = new Case[taillePlat];

	public Morpion() {
		for (int i = 0; i < taillePlat; i++) {
			plateau[i] = new Case();
		}
	}

	public boolean checkWinCondition() {
		
		// ligne
		if (this.plateau[0].contenu == this.plateau[1].contenu && this.plateau[1].contenu == this.plateau[2].contenu) {
			if (this.plateau[0].contenu != null) {
				winnerCase.add(this.plateau[0]);
				winnerCase.add(this.plateau[1]);
				winnerCase.add(this.plateau[2]);
				return true;
			}
		}
		if (this.plateau[3].contenu == this.plateau[4].contenu && this.plateau[4].contenu == this.plateau[5].contenu) {
			if (this.plateau[3].contenu != null) {
				winnerCase.add(this.plateau[3]);
				winnerCase.add(this.plateau[4]);
				winnerCase.add(this.plateau[5]);
				return true;
			}
		}
		if (this.plateau[6].contenu == this.plateau[7].contenu && this.plateau[7].contenu == this.plateau[8].contenu) {
			if (this.plateau[6].contenu != null) {
				winnerCase.add(this.plateau[6]);
				winnerCase.add(this.plateau[7]);
				winnerCase.add(this.plateau[8]);
				return true;
			}
		}

		// colonne
		if (this.plateau[0].contenu == this.plateau[3].contenu && this.plateau[3].contenu == this.plateau[6].contenu) {
			if (this.plateau[0].contenu != null) {
				winnerCase.add(this.plateau[0]);
				winnerCase.add(this.plateau[3]);
				winnerCase.add(this.plateau[6]);
				return true;
			}
		}
		if (this.plateau[1].contenu == this.plateau[4].contenu && this.plateau[4].contenu == this.plateau[7].contenu) {
			if (this.plateau[1].contenu != null) {
				winnerCase.add(this.plateau[1]);
				winnerCase.add(this.plateau[4]);
				winnerCase.add(this.plateau[7]);
				return true;
			}
		}
		if (this.plateau[2].contenu == this.plateau[5].contenu && this.plateau[5].contenu == this.plateau[8].contenu) {
			if (this.plateau[2].contenu != null) {
				winnerCase.add(this.plateau[2]);
				winnerCase.add(this.plateau[5]);
				winnerCase.add(this.plateau[8]);
				return true;
			}
		}
		// diag
		if (this.plateau[0].contenu == this.plateau[4].contenu && this.plateau[4].contenu == this.plateau[8].contenu) {
			if (this.plateau[0].contenu != null) {
				winnerCase.add(this.plateau[0]);
				winnerCase.add(this.plateau[4]);
				winnerCase.add(this.plateau[8]);
				return true;
			}
		}
		if (this.plateau[2].contenu == this.plateau[4].contenu && this.plateau[4].contenu == this.plateau[6].contenu) {
			if (this.plateau[2].contenu != null) {
				winnerCase.add(this.plateau[2]);
				winnerCase.add(this.plateau[4]);
				winnerCase.add(this.plateau[6]);
				return true;
			}
		}

		return false;
	}

}
