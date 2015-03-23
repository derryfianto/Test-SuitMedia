package com.example.user.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Screen2Activity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        String messageEvent = intent.getStringExtra("event");
        String messageGuest = intent.getStringExtra("guest");

        SharedPreferences sp = this.getSharedPreferences("namaInput", Context.MODE_PRIVATE);

        //Palindrom

        if(message!=null)
        {
            String reverse="";
            int msgLength=message.length();

            for ( int i = msgLength - 1; i >= 0; i-- )
            {
                reverse = reverse + message.charAt(i);
            }

            if (message.equals(reverse))
                Toast.makeText(Screen2Activity.this, "Nama Palindrom  ",
                        Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(Screen2Activity.this, "Nama Bukan Palindrom  ",
                        Toast.LENGTH_SHORT).show();
        }

        String namaGuest;
        namaGuest = sp.getString("namaInput","");
        System.out.println("NAMA PERSISTENCE : "+ namaGuest);

        if(message==null)
        {
            //Ambil dari persistence
            message=namaGuest;
        }
        //Save
        sp.edit().putString("namaInput",message).apply();

        TextView textViewName = (TextView) findViewById(R.id.textViewNama);
        textViewName.setText(message);
        System.out.println("MESSAGE: "+message);
        if(messageEvent!=(null))
        {
            Button buttonEvent = (Button) findViewById(R.id.buttonPilihEvent);
            buttonEvent.setText(messageEvent + " Event");
        }

        if(messageGuest!=(null))
        {
            Button buttonGuest = (Button) findViewById(R.id.buttonPilihGuest);
            buttonGuest.setText(messageGuest);
        }
    }

    public void getEvent(View view) {
        // Button Event
        Intent intentToEvent = new Intent(Screen2Activity.this,Screen3Activity.class);
        startActivity(intentToEvent);
    }

    public void getGuest(View view) {
        // Button Guest
        Intent intentToGuest = new Intent(Screen2Activity.this,Screen4Activity.class);
        startActivity(intentToGuest);
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
