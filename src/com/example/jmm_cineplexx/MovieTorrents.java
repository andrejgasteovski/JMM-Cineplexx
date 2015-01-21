package com.example.jmm_cineplexx;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

public class MovieTorrents extends ListActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movie_torrents);
		String movieTitle = getIntent().getExtras().getString(MainActivity.TAG_MOVIE_TITLE);
		
		List<Torrent> torrents = TorrentsFetcher.getTorrents(getSearchUrl(movieTitle));
		ArrayAdapter<Torrent> itemsAdapter = new ArrayAdapter<Torrent>(this, android.R.layout.simple_list_item_1, torrents);
		setListAdapter(itemsAdapter);
		setOnClickListener();
	}
	
	private String getSearchUrl(String movieTitle){
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
}
