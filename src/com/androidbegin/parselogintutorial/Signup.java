package com.androidbegin.parselogintutorial;

import android.app.ActionBar;
import android.app.Activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.parse.CountCallback;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.List;

public class Signup extends Activity implements OnItemSelectedListener {

    private static final String D11A = null;
    // Declare Variables
    EditText rollno;

    Button loginbutton1;
    Button signup1;
    String usernametxt1;
    String passwordtxt1;
    String email1;
    EditText password1;
    EditText username1;
    EditText editemail1;
    private boolean doubletap;
    String count1="0";
    String roll;
    String[] clas;
    String clas1 = D11A;
    ParseUser user = new ParseUser();
    boolean temp;
    int count;
    String classenter="d12b";
    ParseQuery<ParseObject> query=ParseQuery.getQuery(classenter);
    /**
     * Called when the activity is first created.
     */

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // Get the view from main.xml
        setContentView(R.layout.signup);
        ActionBar bar= getActionBar();
        if (bar != null) {
            bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1976d2")));
        }
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        final Spinner spinner1 = (Spinner) findViewById(R.id.spinner2);

        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(this, R.array.clas, R.layout.listitem);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(this);


        Resources res1 = getResources();
        clas = res1.getStringArray(R.array.clas);

        // Locate EditTexts in main.xml
        rollno=(EditText)findViewById(R.id.rollno);
        username1 = (EditText) findViewById(R.id.username1);
        password1 = (EditText) findViewById(R.id.password1);
        editemail1 = (EditText) findViewById(R.id.editemail1);
        // Locate Buttons in main.xml
        loginbutton1 = (Button) findViewById(R.id.login1);
        signup1 = (Button) findViewById(R.id.signup1);

        loginbutton1.setOnClickListener(new OnClickListener() {

            public void onClick(View arg0) {


                Intent intent = new Intent(
                        Signup.this,
                        LoginSignupActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in,R.anim.push_up_out);
                finish();


            }
        });

        // Sign up Button Click Listener
        signup1.setOnClickListener(new OnClickListener() {

            public void onClick(View arg0) {

                password1.setEnabled(false);
                editemail1.setEnabled(false);
                username1.setEnabled(false);
                loginbutton1.setEnabled(false);
                signup1.setEnabled(false);
                spinner1.setEnabled(false);


                // Retrieve the text entered from the EditText
                usernametxt1 = username1.getText().toString();
                passwordtxt1 = password1.getText().toString();
                email1 = editemail1.getText().toString();
                roll=rollno.getText().toString();
                Integer rolln = Integer.parseInt(roll);
                query.whereEqualTo("email",email1);
                query.whereEqualTo("roll_no",rolln);
                query.getFirstInBackground(new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject parseObject, ParseException e) {

                            if(parseObject==null){
                                Toast.makeText(getApplicationContext(),"notthereinvaliduser",Toast.LENGTH_SHORT).show();
                                count=0;
                                 count1="0";

                                Toast.makeText(getApplicationContext(),count1,Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"found1matching",Toast.LENGTH_SHORT).show();
                                count=1;
                                count1="1";
                                Toast.makeText(getApplicationContext(),count1,Toast.LENGTH_SHORT).show();
                            }



                    }
                });

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {

                    }
                }, 5000);

                Toast.makeText(getApplicationContext(),email1,Toast.LENGTH_SHORT).show();
                // Force user to fill up the form
                if (usernametxt1.equals("")&& passwordtxt1.equals("") && email1.equals("") && count1.equals("0") ) {
                    Toast.makeText(getApplicationContext(),
                            "Please complete the sign up form",
                            Toast.LENGTH_LONG).show();
                    if(count==0)
                        Toast.makeText(getApplicationContext(),
                                "You don't belong to the particular class entered",
                                Toast.LENGTH_LONG).show();
                    signup1.setEnabled(true);
                    loginbutton1.setEnabled(true);
                    password1.setEnabled(true);
                    editemail1.setEnabled(true);
                    username1.setEnabled(true);
                    spinner1.setEnabled(true);



                }

                    // Save new user data into Parse.com Data Storage

                    else {

                        user.setUsername(usernametxt1);
                        user.setPassword(passwordtxt1);
                        user.setEmail(email1);
                        user.put("rollno",rolln);
                        user.signUpInBackground(new SignUpCallback() {
                            public void done(ParseException e) {
                                if (e == null) {

                                   Toast.makeText(getApplicationContext(),
                                            "Successfully Signed up, please log in.",
                                            Toast.LENGTH_LONG).show();
                                    signup1.setEnabled(true);
                                    loginbutton1.setEnabled(true);
                                    password1.setEnabled(true);
                                    editemail1.setEnabled(true);
                                    username1.setEnabled(true);
                                    spinner1.setEnabled(true);


                                } else {
                                    Toast.makeText(getApplicationContext(),
                                            "Sign up Error", Toast.LENGTH_LONG)
                                            .show();
                                    signup1.setEnabled(true);
                                    loginbutton1.setEnabled(true);
                                    password1.setEnabled(true);
                                    editemail1.setEnabled(true);
                                    username1.setEnabled(true);
                                    spinner1.setEnabled(true);

                                }
                            }
                        });
                    }


            }
        });

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {


        // TODO Auto-generated method stub
        TextView myText = (TextView) view;
        //Toast.makeText(this,"You selected "+myText.getText(), Toast.LENGTH_SHORT).show();

  if (parent.getId() == R.id.spinner2) {
            String itemClicked = clas[position];

            if (itemClicked.equals("D11A")) {

                user.put("CLASS", "D11A");
                user.saveInBackground();
            }

            if (itemClicked.equals("D11B")) {

                user.put("CLASS", "D11B");
                user.saveInBackground();
            }

            if (itemClicked.equals("D12A")) {

                user.put("CLASS", "D12A");
                classenter="D12ba";
                user.saveInBackground();
            }

            if (itemClicked.equals("D12B")) {

                classenter="D12b";
                user.put("CLASS", "D12B");
                user.saveInBackground();
            }

            if (itemClicked.equals("D12C")) {

                user.put("CLASS", "D12C");
                user.saveInBackground();
            }

            if (itemClicked.equals("D13")) {

                user.put("CLASS", "D13");
                user.saveInBackground();
            }

            if (itemClicked.equals("D14A")) {

                user.put("CLASS", "D14A");
                user.saveInBackground();
            }

            if (itemClicked.equals("D14B")) {

                user.put("CLASS", "D14B");
                user.saveInBackground();
            }

            if (itemClicked.equals("D14C")) {

                user.put("CLASS", "D14C");
                user.saveInBackground();
            }

            if (itemClicked.equals("D15")) {

                user.put("CLASS", "D15");
                user.saveInBackground();
            }

        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub}
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