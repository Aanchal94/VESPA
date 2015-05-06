package com.androidbegin.parselogintutorial;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class LoginSignupActivity extends Activity {
	// Declare Variables
	Button loginbutton;
	String usernametxt;
	String passwordtxt;
	EditText password;
	EditText username;
    private boolean doubletap;

    	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from main.xml
		setContentView(R.layout.loginsignup);
        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1976d2")));
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		// Locate EditTexts in main.xml
		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);

		// Locate Buttons in main.xml
		loginbutton = (Button) findViewById(R.id.Login);
		final TextView signup = (TextView) findViewById(R.id.signup);

		signup.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				
				
									
									Intent intent = new Intent(
											LoginSignupActivity.this,
											Signup.class);
									startActivity(intent);
                                    overridePendingTransition(R.anim.push_left_in,R.anim.push_up_out);
									finish();

			}
		});

		
		// Login Button Click Listener
		loginbutton.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
                loginbutton.setEnabled(false);
                signup.setEnabled(false);
                username.setEnabled(false);
                password.setEnabled(false);
				// Retrieve the text entered from the EditText
				usernametxt = username.getText().toString();
				passwordtxt = password.getText().toString();

				// Send data to Parse.com for verification
				ParseUser.logInInBackground(usernametxt, passwordtxt,
						new LogInCallback() {
							public void done(ParseUser user, ParseException e) {
								if (user != null) {
									// If user exist and authenticated, send user to Welcome.class
									Intent intent = new Intent(
											LoginSignupActivity.this,
											Welcome.class);
									startActivity(intent);
                                    overridePendingTransition(R.anim.push_left_in,R.anim.push_up_out);
									Toast.makeText(getApplicationContext(),
											"Successfully Logged in",
											Toast.LENGTH_LONG).show();
									finish();
								} else {
									Toast.makeText(
											getApplicationContext(),
											"No such user exist, please signup",
											Toast.LENGTH_LONG).show();
                                    loginbutton.setEnabled(true);
                                    loginbutton.setEnabled(true);
                                    signup.setEnabled(true);
                                    username.setEnabled(true);
                                    password.setEnabled(true);
								}
							}
						});
			}
		});


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
            Intent intent = new Intent(LoginSignupActivity.this,LoginSignupActivity.class);
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
    @Override
    public void onBackPressed()
    {
        if(doubletap)
        {
            super.onBackPressed();
            return;
        }
        this.doubletap=true;
        Toast.makeText(this,"Press again to exit",Toast.LENGTH_SHORT).show();
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubletap = false;
            }
        }, 2000);
    }
	}