package com.example.jmm_cineplexx;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;

public class LoadingScreenActivity extends Activity{
	 private final int WAIT_TIME = 2500;
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		System.out.println("LoadingScreenActivity  screen started");
		setContentView(R.layout.loading_screen);
		findViewById(R.id.mainSpinner1).setVisibility(View.VISIBLE);

		new Handler().postDelayed(new Runnable(){ 
		@Override 
		    public void run() { 
	              //Simulating a long running task
	         try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		     System.out.println("Going to Profile Data");
		  /* Create an Intent that will start the ProfileData-Activity. */
	        
		     Intent mainIntent = new Intent(LoadingScreenActivity.this, MovieTorrents.class); 
		     
		     String movieTitle = getIntent().getExtras().getString(MainActivity.TAG_MOVIE_TITLE);
		     mainIntent.putExtra(MainActivity.TAG_MOVIE_TITLE, movieTitle);

		     LoadingScreenActivity.this.startActivity(mainIntent); 
		     LoadingScreenActivity.this.finish(); 
		} 
		}, WAIT_TIME);
	      }
}
