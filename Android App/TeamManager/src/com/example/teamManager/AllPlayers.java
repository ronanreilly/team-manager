package com.example.teamManager;

/*
 * Ronan Reilly 
 * DL131 3rd Year OOP CA1
 * December 2011
 * CA2 OOP
 * 
 * CLASS DESCRIPTION BELOW:
 * 
 * This class will be used to create a list for player
 * objects to be stored in, when they have been parsed from 
 * the web application (footballPlayers).
 * 
 */

// Import needed for the array list to store players
import java.util.ArrayList;
import java.util.List;



public class AllPlayers {
	// private list variable declared and given name players.
	private List<Player> players;
	
	public AllPlayers() {
		
		// New Array List is set up and made equal to the list players
		this.players = new ArrayList<Player>();
	}
	
	// Method to add Player objects to the list "players"
	public void addPlayer(Player p) { 
		this.players.add(p);
	}
	
	// Public method to get list of players
	// will be called form the MyXmlHandler & ListPlayersActivity class
	public List<Player> getPlayers() {
		return this.players; 
	}
}
