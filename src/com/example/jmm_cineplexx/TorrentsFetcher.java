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

public class TorrentsFetcher {
	
	public static String getTorrents(String url){
		String html = "";
		
		AsyncTask<String, Void, String> asyncTask = new AsyncTask<String, Void, String>(){
			@Override
			protected String doInBackground(String... params) {
				String result = null;
				
				try{
			        HttpClient httpclient = new DefaultHttpClient(); // Create HTTP Client
					HttpGet httpget = new HttpGet(params[0]); // Set the action you want to do
					HttpResponse response = httpclient.execute(httpget); // Execute
					InputStream is = response.getEntity().getContent(); // Create an InputStream with the response
					BufferedReader br = new BufferedReader(new InputStreamReader(is));
						
					String line = null;
					while((line = br.readLine()) != null){
						result += line;
					}
					
				}catch(Exception e){
			        Log.d("cineplexx", e.getMessage());
			    }
				
				return result;
				}
		 };

		asyncTask.execute(url);
		
		try{
			html = asyncTask.get();
		}catch(Exception e){
			Log.d("cineplexx", e.getMessage());
		}
		
		return html;
	}
}
