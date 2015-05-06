package com.androidbegin.parselogintutorial;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;



import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View.OnClickListener;

import com.parse.ParseUser;

public class Candidates extends Activity implements AdapterView.OnItemClickListener {
    ListView list;
    String[] memeTitles;
    String[] memeDescriptions;
    int[] images = {R.drawable.d1,R.drawable.d2,R.drawable.d3,R.drawable.d4,R.drawable.d5,R.drawable.d6,R.drawable.d7,R.drawable.d8,R.drawable.d9,R.drawable.d10,};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.candidates);
        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1976d2")));
        addListenerOnButton4();

        Resources res = getResources();
        memeTitles=res.getStringArray(R.array.titles);
        memeDescriptions=res.getStringArray(R.array.descriptions);

        list=(ListView) findViewById(R.id.listView);
        AakAdapter adapter=new AakAdapter(this, memeTitles, images, memeDescriptions);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
    }

    public void addListenerOnButton4() {
        Button vot = (Button) findViewById(R.id.vote);
        vot.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent
                        (getApplicationContext(),CRA.class);
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
    public void onItemClick(AdapterView<?> adapterView, View view, int i,
                            long l) {
        String itemClicked = memeTitles[i];


    }
}

class AakAdapter extends ArrayAdapter<String>
{
    Context context;
    int[] images;
    String[] titleArray;
    String[] descriptionArray;
    public AakAdapter(Context c,String[] titles,int imgs[],String[] desc) {
        super(c,R.layout.single_row,R.id.textView1,titles);
        this.context=c;
        this.images=imgs;
        this.titleArray=titles;
        this.descriptionArray=desc;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.single_row,parent,false);
        ImageView myImage =	(ImageView) row.findViewById(R.id.imageView1);
        TextView myTitle = (TextView) row.findViewById(R.id.textView1);
        TextView myDescription = (TextView) row.findViewById(R.id.textView2);

        myImage.setImageResource(images[position]);
        myTitle.setText(titleArray[position]);
        myDescription.setText(descriptionArray[position]);

        return row;
    }



/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_candidates, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/


}
