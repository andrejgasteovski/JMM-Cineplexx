package com.example.jmm_cineplexx;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;

public class MovieTitlesFetcher {
	public static final String URL_CINEPLEXX_MOVIES = "http://www.cineplexx.mk/filmovi/";
	
	public static List<String> getMovieTitles(){
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
