package com.androidbegin.parselogintutorial;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseQuery;


public class AdminActivity extends Activity implements AdapterView.OnItemSelectedListener {
    String cr=null;
    String[] clas;
    String[] posi;
    String name1;
    String classenter="D12A";
    String posenter="boycr";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String mno="boycr";
        final ParseObject enter=new ParseObject(mno);
//position spinner
        final Spinner spinner1 = (Spinner) findViewById(R.id.spinner11);

        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(this, R.array.clas, R.layout.listitem);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(this);


        Resources res1 = getResources();
        clas = res1.getStringArray(R.array.clas);
  // class spinnner

        final Spinner spinner2 = (Spinner) findViewById(R.id.spinner12);

        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this, R.array.posi, R.layout.listitem);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);


        Resources res2 = getResources();
        posi = res2.getStringArray(R.array.posi);
        Button click=(Button) findViewById(R.id.click);
        final EditText crname=(EditText)findViewById(R.id.crname);
        name1=crname.getText().toString();
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cr=crname.getText().toString();
                if((!cr.equals(""))) {
                    ParseObject query2 =new ParseObject(posenter);
                    query2.put("class",classenter);
                    query2.put("name",cr);
                    query2.saveInBackground();
                }
                Toast.makeText(getApplicationContext(),cr,Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(AdminActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }



        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position,
        long id) {


            // TODO Auto-generated method stub
            TextView myText = (TextView) view;
            //Toast.makeText(this,"You selected "+myText.getText(), Toast.LENGTH_SHORT).show();

            if (parent.getId() == R.id.spinner11) {
                String itemClicked = clas[position];



                if (itemClicked.equals("D12A")) {
                   classenter="D12A";
                }
            }
            else
            if (parent.getId() == R.id.spinner12){
                String itemClicked = posi[position];
                if (itemClicked.equals("boycr")) {
                   posenter="boycr";


                }





        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
