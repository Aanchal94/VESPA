package com.androidbegin.parselogintutorial;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

import android.app.Application;
 
public class Monish extends Application {

	@Override
    public void onCreate() {
        super.onCreate();
 
        // Add your initialization code here
        Parse.initialize(this, "AhBUgO4jZAnxXIHxVDFdzj4MspstnoDyiDVlk6hN", "N8KCwxvEtW4S7l6g8Owla6pXgPUqyajBaAVfxu6v");
 
       ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
 
        // If you would like all objects to be private by default, remove this
        // line.
 
      //  ParseACL.setDefaultACL(defaultACL, true);
    }
}
