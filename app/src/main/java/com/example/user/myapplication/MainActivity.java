package com.example.user.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {

    public final static String EXTRA_MESSAGE = "com.example.user.myapplication.MESSAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Button btnNext =(Button)findViewById(R.id.button);
//        btnNext.setOnClickListener(new View.OnClickListener() {
//
//                    	            @Override
//            	            public void onClick(View arg0) {
//                	                // TODO Auto-generated method stub
//                	                Intent keli = new Intent(MainActivity.this,Screen2Activity.class);
//                	                startActivity(keli);
//
//                	            }
//            	        });
    }

    public void sendMessage(View view) {
        // Do something in response to button
        Intent intent = new Intent(MainActivity.this,Screen2Activity.class);
        EditText editText = (EditText) findViewById(R.id.editTextName);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    }
}
