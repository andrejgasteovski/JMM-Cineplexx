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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.os.AsyncTask;
import android.util.Log;

public class TorrentsFetcher {
	
	public static List<Torrent> getTorrents(String url){
		List<Torrent> torrentList = new ArrayList<Torrent>();
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
			        Log.d("cineplexx", "Exception: " + e.getMessage());
			    }
				
				return result;
				}
		 };

		asyncTask.execute(url);
		
		try{
			html = asyncTask.get();
		}catch(Exception e){
			Log.d("cineplexx", "Exception: " + e.getMessage());
		}
		
		String torrentNames = "";
		
		Document doc = Jsoup.parse(html);
		Elements rows = doc.getElementsByTag("tr"); //get all rows (tr)
		
		for(Element row : rows){
			Torrent torrent = new Torrent();
			
			//get the torrent name
			Element torrentNameElement = row.getElementsByClass("cellMainLink").first(); //get the first element in the row that matches cellMainLink class	
			if(torrentNameElement != null){ //it is a movie row
				String torrentName = Jsoup.parse(torrentNameElement.html()).text(); //get pure torrent name
				torrent.setFullTorrentName(torrentName);
				//torrentNames += torrentName + "\n\n";
			}else{
				Log.d("cineplexx", "Torrent name element is null");
				continue; //it is not a movie row
			}
			
			//get download link
			Element downloadLinkElement = row.getElementsByAttributeValueStarting("title", "Download").first();
			if(downloadLinkElement != null){
				String downloadLink = downloadLinkElement.attr("href");
				torrent.setTorrentDownloadLink(downloadLink);
			}
			
			//get torrent size
			Element torrentSizeElement = row.getElementsByClass("nobr").first();
			if(torrentSizeElement != null){
				String torrentSize = Jsoup.parse(torrentSizeElement.html()).text(); //get the pure text from the tag
				
				if(Character.isDigit(torrentSize.charAt(0))){ //if it starts with a digit -> it is size
					torrent.setTorrentSize(torrentSize);	
				}
			}
		
			//get number of seeds
			Element seedsNumberElement = row.getElementsByClass("green").first();
			if(seedsNumberElement != null){
				String seedNumber = seedsNumberElement.html();	
				if(Character.isDigit(seedNumber.charAt(0))){ //if it starts with a digit
					torrent.setSeedNumber(seedNumber);
				}
			}
		
			//get number of seeds
			Element leechNumberElement = row.getElementsByClass("lasttd").first();
			if(leechNumberElement != null){
				String leechNumber = leechNumberElement.html();	
				if(Character.isDigit(leechNumber.charAt(0))){ //if it starts with a digit
					torrent.setLeechNumber(leechNumber);
				}
			}
		
			if(!torrent.getTorrentSize().equals("")){
				torrentList.add(torrent);
			}
		}
	
		return torrentList;
	}	
}
