package com.androidbegin.parselogintutorial;


import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public  class FetchingData extends Activity {
    String str[] = new String[20];
    Integer cnt[] = new Integer[20];
    List<Integer> cnt1 = new ArrayList<Integer>();
    List<String> str1 = new ArrayList<String>();
    List<String> hello = new ArrayList<String>();
    List<ParseObject> ob;
    int wer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_fetching_data);
        ListView list11 = (ListView) findViewById(R.id.listView);

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

        for(i = 0; i<str1.size();i++)
        {
            if(str1!=null) {
                ParseQuery<ParseObject> query4 = ParseQuery.getQuery("votes");
                query4.whereEqualTo("boys_cr", str1.get(i));
                try {
                    cnt1.add(query4.count());
                } catch (com.parse.ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        ArrayAdapter<String> arrayAdapter3=new ArrayAdapter<String>(this,R.layout.listitem,str1);
        //list11.setAdapter(arrayAdapter3);

        ArrayAdapter<Integer> arrayAdapter4=new ArrayAdapter<Integer>(this,R.layout.listitem,cnt1);
        list11.setAdapter(arrayAdapter4);


    }
}
