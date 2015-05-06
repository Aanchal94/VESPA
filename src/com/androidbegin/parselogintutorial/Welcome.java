package com.androidbegin.parselogintutorial;

import com.parse.ParseUser;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Welcome extends Activity {
	
	// Declare Variable
	Button logout;
    private boolean doubletap;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from singleitemview.xml
		setContentView(R.layout.welcome);
        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1976d2")));
        addListenerOnButton();
		// Retrieve current user from Parse.com
		ParseUser currentUser = ParseUser.getCurrentUser();
		
		// Convert currentUser into String
		String struser = currentUser.getUsername().toString();
		
		// Locate TextView in welcome.xml
		TextView txtuser = (TextView) findViewById(R.id.txtuser);

		// Set the currentUser String into TextView
		txtuser.setText("You are logged in as " + struser);
		
		// Locate Button in welcome.xml
		logout = (Button) findViewById(R.id.logout);

		// Logout Button Click Listener
		logout.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// Logout current user
				ParseUser.logOut();
				finish();
			}
		});

	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            ParseUser.logOut();
            Intent intent = new Intent(Welcome.this,LoginSignupActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        int id1 = item.getItemId();
        if (id1 == R.id.action_results) {
            return true;
        }
        int id2 = item.getItemId();
        if (id2 == R.id.action_Aboutus) {
            return true;
        }



        return super.onOptionsItemSelected(item);
    }

    public void addListenerOnButton() {
        Button  c = (Button) findViewById(R.id.cont);
        c.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent
                        (getApplicationContext(),Dashboard.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in,R.anim.push_up_out);
            }
        });
    }
    @Override
    public void onBackPressed()
    {
        if(doubletap)
        {
            super.onBackPressed();
            return;
        }
        this.doubletap=true;
        Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubletap = false;
            }
        }, 2000);
    }
}