package com.example.jmm_cineplexx;


import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

public class MainActivity extends ListActivity {

	public static final String TAG_MOVIE_TITLE = "movieTitle";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);	
		initializeMovieList();
		setOnClickListener();
	}
	
	public void initializeMovieList(){
		List<String> movies = MovieTitlesFetcher.getMovieTitles();
		Log.d("cineplexx", "Movie titles are fetched.");
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, movies);
		setListAdapter(adapter);
	}
	
	private void setOnClickListener(){
		getListView().setClickable(true);
		getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String selectedMovieTitle = (String)getListView().getItemAtPosition(position);
				Log.d("cineplexx", "List item selected with movie title: " + selectedMovieTitle);
				
				Intent i=new Intent(MainActivity.this, LoadingScreenActivity.class);
				i.putExtra(TAG_MOVIE_TITLE, selectedMovieTitle);
				startActivity(i);
				Log.d("cineplexx", "Activity MovieTorrents is started");
			}
		});
	}
	
}
