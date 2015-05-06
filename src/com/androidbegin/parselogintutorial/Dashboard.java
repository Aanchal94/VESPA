package com.androidbegin.parselogintutorial;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;


public class Dashboard extends Activity implements AdapterView.OnItemClickListener {

    ListView l;
    String[] position={"Class Representative","Music Incharge","SORT","Sports Incharge","Cultural Incharge","Google Champs"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1976d2")));
        l=(ListView)findViewById(R.id.listView);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.listitem,position);
        l.setAdapter(adapter);
        l.setOnItemClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.back_in, R.anim.back_out);
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
            Intent intent=new Intent(this,LoginSignupActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.push_left_in,R.anim.push_up_out);
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
    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {

        String itemClicked = position[pos];
       if(itemClicked.equals("Class Representative")){
            Intent intent = new Intent(this,CRA.class);
            startActivity(intent);
           overridePendingTransition(R.anim.push_left_in,R.anim.push_up_out);}

        if(itemClicked.equals("Music Incharge")){
            Intent intent = new Intent(this,Candidates.class);
            startActivity(intent);
            overridePendingTransition(R.anim.push_left_in,R.anim.push_up_out);}

        if(itemClicked.equals("SORT")){
            Intent intent = new Intent(this,Candidates.class);
            startActivity(intent);
            overridePendingTransition(R.anim.push_left_in,R.anim.push_up_out);}

        if(itemClicked.equals("Cultural Incharge")){
            Intent intent = new Intent(this,Candidates.class);
            startActivity(intent);
            overridePendingTransition(R.anim.push_left_in,R.anim.push_up_out);}

        if(itemClicked.equals("Sports Incharge")){
            Intent intent = new Intent(this,Candidates.class);
            startActivity(intent);
            overridePendingTransition(R.anim.push_left_in,R.anim.push_up_out);}

    }


}


