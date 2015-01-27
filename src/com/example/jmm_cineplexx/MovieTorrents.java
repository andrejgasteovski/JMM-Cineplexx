package com.example.jmm_cineplexx;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

public class MovieTorrents extends ListActivity{
	Button btnMovieDetails;
	String movieTitle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
 		setContentView(R.layout.activity_movie_torrents);
 		
		movieTitle = getIntent().getExtras().getString(MainActivity.TAG_MOVIE_TITLE);
		
		List<Torrent> torrents = TorrentsFetcher.getTorrents(getSearchTorrentUrl(movieTitle));
		ArrayAdapter<Torrent> itemsAdapter = new ArrayAdapter<Torrent>(this, android.R.layout.simple_list_item_1, torrents);
		setListAdapter(itemsAdapter);
		setOnClickListener();
		initializeMovieDetailsButton();
	}
	
	private String getSearchTorrentUrl(String movieTitle){
		String baseUrl = "http://katproxy.com/usearch/";
		
		String [] parts = movieTitle.split(" ");
		String query = "";
		for(int i = 0; i < parts.length; i++){
			query += parts[i] + "%20";
		}
		query = query.substring(0, query.length() - 3);
		
		String result = baseUrl + query + "/";
		
		Log.d("cineplexx", "Search url for " + movieTitle + ": " + result);
		return result;
	}
	
	private String getMovieDetailsUrl(String movieTitle){
		String baseUrl = "http://www.cineplexx.mk/movie/";
		movieTitle = movieTitle.toLowerCase();
		String [] parts = movieTitle.split(" ");
		String query = "";
		for(int i = 0; i < parts.length; i++){
			query += parts[i] + "-";
		}
		query = query.substring(0, query.length() - 1);
		String result = baseUrl + query + "/";
		
		Log.d("cineplexx", "Movie details url for " + movieTitle + ": " + result);
		return result;
	}
	
	private void setOnClickListener(){
		getListView().setClickable(true);
		getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Torrent selectedTorrent = (Torrent)getListView().getItemAtPosition(position);
				String downloadLink = selectedTorrent.getTorrentDownloadLink();
				
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(downloadLink));
				startActivity(browserIntent);
			}
		});
	}
	
	private void initializeMovieDetailsButton(){
		this.btnMovieDetails = (Button)findViewById(R.id.buttonMovieDetails);
		btnMovieDetails.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String movieDetailsUrl = getMovieDetailsUrl(movieTitle);
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(movieDetailsUrl));
				startActivity(browserIntent);
			}
		});
	}
}
