
package com.example.teamManager;

/*
 * Ronan Reilly 
 * DL131 3rd Year OOP CA1
 * December 2011
 * CA2 OOP
 * 
 * CLASS DESCRIPTION BELOW:
 * 
 * This class will be handle the iteration through
 * XML elements from view players PHP file the web server.
 * As each element is entered a boolean will be set to true. As the element 
 * is exited the boolean will be set to false. A boolean will also be 
 * set to true at the beginning of the document as player objects are 
 * inside players tags. this will be set to false as the last player tag
 * is exited.
 * 
 * 
 */


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class MyXMLHandler extends DefaultHandler {
	
	//	This variable is a list which will be used to store 
	//  all players parsed from the XML document.
	private AllPlayers myPlayers;
	// This variable will be used to store each player as it is 
	// is parsed for the XML document.
	private Player currentPlayer;
	// These boolean variables will be set to true
	// when the tag that corresponds to their name is entered
	// and set to false when the parser leaves the tag and enters the
	// next tag.
	private boolean inPlayers;
	private boolean inPlayer;
	private boolean inTeam;
	private boolean inFirstName;
	private boolean inLastName;
	private boolean inAge;
	private boolean inCountry_Origin;
	private boolean inPosition;
	private boolean inPref_Foot;

	public void startDocument() throws SAXException {
		// Sets all booleans to false at the start of the parsing,
		// creates a new list for player objects and sets current player
		// Variable to null.
		this.myPlayers = new AllPlayers();
		this.currentPlayer = null;
		this.inPlayer = false;
		this.inFirstName = false;
		this.inLastName = false;
		this.inAge = false;
		this.inCountry_Origin = false;
		this.inPosition = false;
		this.inPref_Foot = false;
	}

	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		
	}
	
	// As each XML tag/element is entered its boolean variable is set to true.
	public void startElement(String uri, String localName, String qName,
			Attributes atts) throws SAXException {
		// If the outer players tag is entered set the boolean inPlayers to true.
		if (localName.equalsIgnoreCase("players")) inPlayers = true;
		// Else if the player tag is entered set the inPlayer and current player 
		// booleans to true.
		else if (localName.equalsIgnoreCase("player")) {
			inPlayer = true;
			currentPlayer = new Player();
		}
		// This code sets the boolean to true for each element/tag inside
		// the player tag once they have been entered.
		else if (localName.equalsIgnoreCase("team")) inTeam = true;
		else if (localName.equalsIgnoreCase("firstName")) inFirstName = true;
		else if (localName.equalsIgnoreCase("lastName")) inLastName = true;
		else if (localName.equalsIgnoreCase("age")) inAge = true;
		else if (localName.equalsIgnoreCase("country_origin")) inCountry_Origin = true;
		else if (localName.equalsIgnoreCase("position")) inPosition = true;
		else if (localName.equalsIgnoreCase("pref_foot")) inPref_Foot = true;
	}

	// As each XML tag/element is exited its boolean variable is set to false.
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// If the outer players tag is exited set the boolean inPlayers to false
		// indicating these particular players have been parsed.
		if (localName.equalsIgnoreCase("players")) inPlayers = false;
		// Else if the inner player tag is exited set the boolean inPlayer to false
		// indicating this particular player has been parsed.
		else if (localName.equalsIgnoreCase("player")) {
			inPlayer = false;
			// The last read or current read player is added to the myPlayers list.
			myPlayers.addPlayer(currentPlayer);
			// This boolean is set to false to indicate that the player has been parsed and exited successfully.
			currentPlayer = null;
		}
		// If the tags are other that players or player as they are exited set their boolean to false
		// to indicate that they have been parsed and exited.
		else if (localName.equalsIgnoreCase("team")) inTeam = false;
		else if (localName.equalsIgnoreCase("firstName")) inFirstName = false;
		else if (localName.equalsIgnoreCase("lastName")) inLastName = false;
		else if (localName.equalsIgnoreCase("age")) inAge = false;
		else if (localName.equalsIgnoreCase("country_origin")) inCountry_Origin = false;
		else if (localName.equalsIgnoreCase("position")) inPosition = false;
		else if (localName.equalsIgnoreCase("pref_foot")) inPref_Foot = false;
	}

	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// A string is set up to store the content of the current parsed XML tag/element.
		
		String content = new String(ch, start, length);
		// If parser is in the tag then set the attribute for a player object
		// that is the currently being parsed player to that of what was in the 
		// tag/element and set it to the string "content".
		if (inTeam) currentPlayer.team = content;
		else if (inFirstName) currentPlayer.firstName = content;
		else if (inLastName) currentPlayer.lastName = content;
		else if (inAge) currentPlayer.age = content;
		else if (inCountry_Origin) currentPlayer.country_origin = content;
		else if (inPosition) currentPlayer.position = content;
		else if (inPref_Foot) currentPlayer.pref_foot = content;
	}
	
	// Calling the get players (list).
	public AllPlayers getPlayers() {
		return this.myPlayers;
	}
}
