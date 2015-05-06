package com.androidbegin.parselogintutorial;

import java.util.Timer;
import java.util.TimerTask;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

public class SplashScreenActivity1 extends Activity {

	// Set Duration of the Splash Screen
	long Delay = 7000;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Remove the Title Bar
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		// Get the view from splash_screen.xml
		setContentView(R.layout.splash_screen_activity1);

		// Create a Timer
		Timer RunSplash = new Timer();

		// Task to do when the timer ends
		TimerTask ShowSplash = new TimerTask() {
			@Override
			public void run() {
				// Close SplashScreenActivity.class
				finish();

				// Start MainActivity.class
				Intent myIntent = new Intent(SplashScreenActivity1.this,
						Signup.class);
				startActivity(myIntent);
			}
		};

		// Start the timer
		RunSplash.schedule(ShowSplash, Delay);
	}
}

