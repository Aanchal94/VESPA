package com.androidbegin.parselogintutorial;

import com.parse.CountCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ResultsDisplay extends Activity {

    List<ParseObject> ob;
    List<String> str1=new ArrayList<String>();
    List<Integer> cnt1=new ArrayList<Integer>();
    int max;
    ParseUser currentUser = ParseUser.getCurrentUser();
    String class2 = currentUser.get("CLASS").toString();
    String class1=class2.toLowerCase();
    ParseQuery<ParseObject> winner_put=ParseQuery.getQuery("boycr");

    ParseObject results;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.results_display);
        ActionBar bar = getActionBar();
        assert bar != null;
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1976d2")));

        final ParseQuery<ParseObject> query = ParseQuery.getQuery("boycr");
        query.whereEqualTo("class",class1);
        winner_put.whereEqualTo("class",class1);

        query.selectKeys(Arrays.asList("name"));
        try {
            ob = query.find();
        } catch (com.parse.ParseException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this,R.layout.listitem);
        for(ParseObject boycr: ob){
            arrayAdapter1.add((String) boycr.get("name"));
        }
        int i=0;
        //list11.setAdapter(arrayAdapter1);
        for(ParseObject boycr: ob ){
            if(boycr!=null){
                str1.add((String) boycr.get("name"));}
        }

        for(i = 0; i<str1.size();i++)
        {
            if(str1!=null) {
                ParseQuery<ParseObject> query4 = ParseQuery.getQuery(class1);
                query4.whereEqualTo("boycr", str1.get(i));
                try {
                    cnt1.add(query4.count());
                } catch (com.parse.ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        max=cnt1.get(0);
        for(i = 0; i<cnt1.size();i++)
        {
            max=Math.max(max,cnt1.get(i));


        }
        max=cnt1.indexOf(max);
        String hello=str1.get(max);
        winner_put.whereEqualTo("name",hello);
        Toast.makeText(getApplicationContext(),str1.get(max),Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(),class1,Toast.LENGTH_SHORT).show();
        try {
            results=winner_put.getFirst();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        results.put("winner",123);
        results.saveInBackground();
        TextView textView=(TextView) findViewById(R.id.boycr);
        textView.setText("BOYS CR :   " +str1.get(max)+"    Count :  "+cnt1.get(max));

    }

}

