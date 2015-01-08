package com.example.jmm_cineplexx;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MovieTorrents extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movie_torrents);
		
		String movieTitle = getIntent().getExtras().getString(MainActivity.TAG_MOVIE_TITLE);
		
		TextView tvMovieTitle = (TextView)findViewById(R.id.textViewMovieTitle);
		//tvMovieTitle.setText(TorrentsFetcher.getTorrents());
	}
}
