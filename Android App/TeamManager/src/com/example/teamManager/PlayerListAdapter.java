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
 * This class is used to populate the list elements in the
 *  layout file players_list_row.xml file.
 * 
 */


import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;



public class PlayerListAdapter extends ArrayAdapter<Player> {

	private List<Player> myPlayers;
	
	public PlayerListAdapter(Context context, List<Player> objects) {
		super(context, R.layout.player_list_row, objects);
		
		myPlayers = objects;
	}
	
	// Thia method checks to see if the list of players is empty and returns 0 if it is.
	// the size of the list is returned if it is not empty.
	public int getCount() {
		if (myPlayers == null) return 0;
		else return myPlayers.size();
	}
	

	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) getContext().getSystemService(
					Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.player_list_row, null);
		}
		
		// Sets up the text Views of the players_list _rows and assigns them to variables finding them by their id tags.
		TextView teamView = (TextView)v.findViewById(R.id.team);
		TextView firstNameView = (TextView)v.findViewById(R.id.firstName);
		TextView lastNameView = (TextView)v.findViewById(R.id.lastName);
		TextView ageView = (TextView)v.findViewById(R.id.age);
		TextView positionView = (TextView)v.findViewById(R.id.pos);
		
		// Gets the position of a player object in the players list and assigns a reference to it in player.
		Player player = myPlayers.get(position);
		
		// The text views are set to the content of the player according to the associated attributes.
		teamView.setText("Team:" + " " + player.team);
		firstNameView.setText("First Name:" + " " + player.firstName);
		lastNameView.setText("Last Name:" + " " + player.lastName);
		ageView.setText("Age:" + " " + player.age);
		positionView.setText("Preferred Position:" + " " + player.position);
		
		// views are returned
		return v;
	}
}
