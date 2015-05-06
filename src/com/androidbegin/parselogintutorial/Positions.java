package com.androidbegin.parselogintutorial;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidbegin.parselogintutorial.R;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Positions extends Activity implements AdapterView.OnItemSelectedListener {
    Spinner sp1, sp2, sp3, sp4, sp5, sp6, sp7, sp8, sp9, sp10;
    String[] boys_cr,girls_cr,boys_si,girls_si;
    ParseUser currentUser = ParseUser.getCurrentUser();
    String class2 = currentUser.get("CLASS").toString();
    String class1=class2.toLowerCase();
    String email1=currentUser.get("email").toString();

    ParseObject votes;
    ParseQuery<ParseObject> query1=ParseQuery.getQuery(class1);
    List<ParseObject> ob;
    List<String> str1=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.positions);
        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1976d2")));
        Toast.makeText(getApplicationContext(), class1, Toast.LENGTH_SHORT).show();
        addListenerOnButton4();
        query1.whereEqualTo("email", email1);

        try {
            votes = query1.getFirst();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        final ParseQuery<ParseObject> query = ParseQuery.getQuery("boycr");
        query.whereEqualTo("class", class1);
        query.selectKeys(Arrays.asList("name"));
        try {
            ob = query.find();
        } catch (com.parse.ParseException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this, R.layout.listitem);
        for (ParseObject boycr : ob) {
            arrayAdapter1.add((String) boycr.get("name"));
        }
        int i = 0;
        //list11.setAdapter(arrayAdapter1);
        for (ParseObject boycr : ob) {
            if (boycr != null) {
                str1.add((String) boycr.get("name"));
            }
        }


        str1.add("Please Select");

        sp1 = (Spinner) findViewById(R.id.spin);
        sp1.setSelection(str1.size());
        ArrayAdapter<String> b = new ArrayAdapter<String>(this, R.layout.listitem, str1);
        int index1 = 0;
        for (int index = 0, count = b.getCount(); index < count; ++index) {
            if (b.getItem(index).equals("Please Select")) {
                index1 = index;
                break;
            }
        }


        sp1.setAdapter(b);
        sp1.setSelection(index1);
        sp1.setOnItemSelectedListener(this);


        sp2 = (Spinner) findViewById(R.id.gcr);
        ArrayAdapter g = ArrayAdapter.createFromResource(this, R.array.candies, R.layout.listitem);
        sp2.setAdapter(g);
        sp2.setOnItemSelectedListener(this);

        sp3 = (Spinner) findViewById(R.id.bsi);
        ArrayAdapter bs = ArrayAdapter.createFromResource(this, R.array.boys_si, R.layout.listitem);
        sp3.setAdapter(bs);
        sp3.setOnItemSelectedListener(this);

        sp4 = (Spinner) findViewById(R.id.gsi);
        ArrayAdapter gs = ArrayAdapter.createFromResource(this, R.array.girls_si, R.layout.listitem);
        sp4.setAdapter(gs);
        sp4.setOnItemSelectedListener(this);

        sp5 = (Spinner) findViewById(R.id.bci);
        ArrayAdapter bc = ArrayAdapter.createFromResource(this, R.array.boys_ci, R.layout.listitem);
        sp5.setAdapter(bc);
        sp5.setOnItemSelectedListener(this);

        sp6 = (Spinner) findViewById(R.id.gci);
        ArrayAdapter gc = ArrayAdapter.createFromResource(this, R.array.girls_ci, R.layout.listitem);
        sp6.setAdapter(gc);
        sp6.setOnItemSelectedListener(this);

        sp7 = (Spinner) findViewById(R.id.bsri);
        ArrayAdapter bsr = ArrayAdapter.createFromResource(this, R.array.boys_sri, R.layout.listitem);
        sp7.setAdapter(bsr);
        sp7.setOnItemSelectedListener(this);

        sp8 = (Spinner) findViewById(R.id.gsri);
        ArrayAdapter gsr = ArrayAdapter.createFromResource(this, R.array.girls_sri, R.layout.listitem);
        sp8.setAdapter(gsr);
        sp8.setOnItemSelectedListener(this);

        sp9 = (Spinner) findViewById(R.id.bmi);
        ArrayAdapter bm = ArrayAdapter.createFromResource(this, R.array.boys_mi, R.layout.listitem);
        sp9.setAdapter(bm);
        sp9.setOnItemSelectedListener(this);

        sp10 = (Spinner) findViewById(R.id.gmi);
        ArrayAdapter gm = ArrayAdapter.createFromResource(this, R.array.girls_mi, R.layout.listitem);
        sp10.setAdapter(gm);
        sp10.setOnItemSelectedListener(this);

        Resources res1 = getResources();
        boys_cr = res1.getStringArray(R.array.candidates);
        Resources res2 = getResources();
        girls_cr = res2.getStringArray(R.array.candies);
        Resources res3 = getResources();
        boys_si = res3.getStringArray(R.array.boys_si);
        Resources res4 = getResources();
        girls_si = res4.getStringArray(R.array.boys_si);


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            Log.d(getClass().getName(), "Ignoring selection of dummy list item...");
            votes.put("boycr", str1.get(position));
            votes.saveInBackground();


        } else {
            Log.d(getClass().getName(), "Handling selection of actual list item...");


            //TextView mt = (TextView) view;
           // Toast.makeText(this, "You voted for " + mt.getText(), Toast.LENGTH_SHORT).show();
            if (parent.getId() == R.id.spin) {
                votes.put("boycr",str1.get(position));
                votes.saveInBackground();
            }
            if (parent.getId() == R.id.gcr) {
                String itemClicked = girls_cr[position];

                if (itemClicked.equals("Aanchal D")) {

                    votes.put("girls_cr", "Aanchal D");
                    votes.saveInBackground();
                }
                if (itemClicked.equals("Harsha J")) {

                    votes.put("girls_cr", "Harsha J");
                    votes.saveInBackground();
                }

            }
            if (parent.getId() == R.id.bsi) {
                String itemClicked = boys_si[position];

                if (itemClicked.equals("Brijesh M")) {

                    votes.put("boys_si", "Brijesh M");
                    votes.saveInBackground();
                }
                if (itemClicked.equals("Sidharth N")) {

                    votes.put("boys_si", "Sidharth N");
                    votes.saveInBackground();
                }
                if (itemClicked.equals("Parimal A")) {

                    votes.put("boys_si", "Parimal A");
                    votes.saveInBackground();
                }
            }

            if (parent.getId() == R.id.gsi) {
                String itemClicked = girls_si[position];

                if (itemClicked.equals("Lakshmi A")) {

                    votes.put("girls_si", "Lakshmi A");
                    votes.saveInBackground();
                }
                if (itemClicked.equals("Sidharth N")) {

                    votes.put("girls_si", "Tanvi G");
                    votes.saveInBackground();
                }

            }





        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public void addListenerOnButton4(){

        Button  done = (Button) findViewById(R.id.button1);
        done.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent
                        (getApplicationContext(),ResultsDisplay.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in,R.anim.push_up_out);
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.back_in, R.anim.back_out);
    }
}
