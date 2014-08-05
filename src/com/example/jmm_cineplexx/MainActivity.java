package com.example.jmm_cineplexx;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

public class MainActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initializeMovieList();
	}
	
	public void initializeMovieList(){
		List<String> movies = getMovieTitles();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, movies);
		setListAdapter(adapter);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public List<String> getMovieTitles(){
		List<String> movies = new ArrayList<String>();
		
	
		    AsyncTask<Void, Void, List<String>> asyncTask = new AsyncTask<Void, Void, List<String>>(){
				@Override
				protected List<String> doInBackground(Void... params) {
					List<String> movies = new ArrayList<String>();
					
					try{
			        	HttpClient httpclient = new DefaultHttpClient(); // Create HTTP Client
						HttpGet httpget = new HttpGet("http://www.cineplexx.mk/filmovi/"); // Set the action you want to do
						HttpResponse response = httpclient.execute(httpget); // Execute
						InputStream is = response.getEntity().getContent(); // Create an InputStream with the response
						BufferedReader br = new BufferedReader(new InputStreamReader(is));
						
						String line1 = null;
						String line2 = null;
						while((line2 = br.readLine()) != null){
							if(line2.contains("|")){
								if(line1.length() > 6){
									line1 = line1.trim();
									  
									String startChars = line1.substring(0, 3);
									if(startChars.equals("<p>")){
										line1 = line1.substring(3, line1.length() - 4);
										movies.add(line1);
										Log.d("cineplexx", line1);
									}
								  }
							}
							line1 = line2;
						}
						
						
					}catch(Exception e){
			        		Log.d("cineplexx", e.getMessage());
			        	}
					return movies;
				}
		    };

		
		asyncTask.execute();
		
		try{
			movies = asyncTask.get();
		}catch(Exception e){
			Log.d("cineplexx", e.getMessage());
		}
		
		return movies;
	}
}
