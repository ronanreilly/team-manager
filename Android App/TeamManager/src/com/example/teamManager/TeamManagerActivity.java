// Ronan Reilly 2012
package com.example.teamManager;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.example.teamManager.AllPlayers;



import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

/*
 * Ronan Reilly N00090333
 * DL131 3rd Year OOP CA1
 * December 2011
 * CA2 OOP
 * 
 * CLASS DESCRIPTION BELOW:
 * 
 * This class or activity will be used send a request to the web server in a 
 * background thread (AsyncTask). It is important that this request 
 * is performed in the background and not on the main UI thread.
 * This is so that any other IO events that occur whilst this 
 * request/Async task is being carried can be executed and the request 
 * to the server will be carried out also in the background.
 * 
 */

public class TeamManagerActivity extends ListActivity {

	// This inner class is the class that carries out the async task of downloading the list from the web app on the 
	// server and publishes it to the UI thread.
	private class DownloadWebPageTask extends AsyncTask<String, Void, AllPlayers> {
		
		// more than one url can be passed here  but one is only needed for this activity.
		@Override
		protected AllPlayers doInBackground(String... urls) {
			// 
			AllPlayers result;
			String myUrl = urls[0];
			result = getPlayerListFromServer(myUrl);
			return result;
		}
		
		@Override
		protected void onPostExecute(AllPlayers result) {
			setListAdapter(
					new PlayerListAdapter(
							TeamManagerActivity.this, result.getPlayers()));
		}
		
	}
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		// Sets the activitys view to the main layout xml file that has a list view inside it.
        setContentView(R.layout.main);
		// Creates a new list adapter.
        setListAdapter(new PlayerListAdapter(this, null));   
		// The download web page task (async task) is called and stored inside myBackgroundTask.
        DownloadWebPageTask myBackgroundTask = new DownloadWebPageTask();
		//  the DownloadWebPageTask is executed on this URL.
        myBackgroundTask.execute("http://127.0.0.1/~ronanseanreilly/footballPlayers/xml/view_players.php");
        
    }

	// This method is used to connect to the url, set up a data input stream so data can be read 
	// from the web application on the web server, parse the XML using a SAX parser factory.
	private AllPlayers getPlayerListFromServer(String urlString) {
		AllPlayers result = null;
		try {
            URL url = new URL(urlString);

            /* Open a connection to that URL. */
            URLConnection ucon = url.openConnection();
            InputStream is = ucon.getInputStream();
            
            /** Handling XML */
			// This factory object is created to use the interface for SAX, 
			// From this a parser object is created.
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
            XMLReader xr = sp.getXMLReader();

            /** Create handler to handle XML Tags ( extends DefaultHandler ) */
			// A new XMLHandler is created.
            MyXMLHandler myXMLHandler = new MyXMLHandler();
			// The XML reader is the assigned  the XMLHadnler
            xr.setContentHandler(myXMLHandler);
			// The XML reader is used on the input stream from the server
            xr.parse(new InputSource(is));
			// Result is set to the list of players parsed from the XML handler that were added to the players lis
            result = myXMLHandler.getPlayers();

	    } catch (IOException e) {
	    	Log.v("ListPlayersActivity", "Error downloading player list: " + e.getMessage());
	    	//result = "Error downloading player list: " + e.getMessage();
	    }
		catch (SAXException e) {			
	    	Log.v("ListPlayersActivity", "Error parsing player list: " + e.getMessage());
		}
		catch (ParserConfigurationException e) {
	    	Log.v("ListPlayersActivity", "Error building XML parser: " + e.getMessage());
		}
		return result;
	}
}