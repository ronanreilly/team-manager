// Ronan Reilly 2012
package com.example.teamManager;

/*
 * Ronan Reilly N00090333
 * DL131 3rd Year OOP CA1
 * December 2011
 * CA2 OOP
 * 
 * CLASS DESCRIPTION BELOW:
 * 
 * This class will represent a football player object.
 * All of the variables in this class will correspond
 * with the columns that make up a player (row) in the
 * players database.
 * 
 */

public class Player {

	public int id; // id for row in the players database
	public String team; // team for a player in the players database
	public String firstName; // first name for a player in the players database
	public String lastName; // last name for a player in the players database
	public String age; // age for a player in the players database
	public String country_origin; // country origin for a player in the players database
	public String position; // position for a player in the players database
	public String pref_foot; // preferred foot of a player in the players database

	/*
	 * 
	 * Getters & Setters for the variables that correspond to
	 * column headers in the players database.
	 * 
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getCountry_origin() {
		return country_origin;
	}

	public void setCountry_origin(String country_origin) {
		this.country_origin = country_origin;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPref_foot() {
		return pref_foot;
	}

	public void setPref_foot(String pref_foot) {
		this.pref_foot = pref_foot;
	}

}
