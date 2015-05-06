package com.androidbegin.parselogintutorial;



import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Voters extends Activity implements OnItemSelectedListener {
Spinner spinner;
String[] bcr;
List<ParseObject> ob;
    List<String> str1=new ArrayList<String>();
ParseObject votes = new ParseObject("votes");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voters);
        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1976d2")));
        addListenerOnButton();
        spinner=(Spinner) findViewById(R.id.spinner1);
        final ParseQuery<ParseObject> query = ParseQuery.getQuery("boycr");
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

        ArrayAdapter adapter= ArrayAdapter.createFromResource(this, R.array.candidates,R.layout.listitem);
       spinner.setAdapter(adapter);
       spinner.setOnItemSelectedListener(this);

       Resources res = getResources();
		bcr=res.getStringArray(R.array.candidates);
    }
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
        votes.put("boys_cr",str1.get(position));
        votes.saveInBackground();
		
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
	public void onNothingSelected(AdapterView<?> parent) {
		
	}

public void addListenerOnButton() {
    Button  c = (Button) findViewById(R.id.go);
    c.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View arg0) {

            Intent intent = new Intent
                    (getApplicationContext(),ResultsDisplay.class);
            startActivity(intent);
            overridePendingTransition(R.anim.push_left_in,R.anim.push_up_out);
        }
    });}
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.back_in, R.anim.back_out);
    }

}