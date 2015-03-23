package com.example.user.myapplication;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.myapplication.Adapter.EventAdapter;
import com.example.user.myapplication.Model.Event;

import java.util.ArrayList;


public class Screen3Activity extends ListActivity {

    public final static String EVENT_MESSAGE = "com.example.user.myapplication.MESSAGE";
    ArrayList<Event>listEvent = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Event event = new Event(R.drawable.football,"Football","1 Januari 2015");
        Event event2 = new Event(R.drawable.basketball,"Basketball","15 Maret 2015");
        Event event3 = new Event(R.drawable.swimming,"Swimming","23 Juni 2015");
        Event event4 = new Event(R.drawable.running,"Running","30 September 2015");
        Event event5 = new Event(R.drawable.badminton,"Badminton","12 Oktober 2015");
        Event event6 = new Event(R.drawable.golf,"Golf","4 November 2015");

        listEvent.add(event);
        listEvent.add(event2);
        listEvent.add(event3);
        listEvent.add(event4);
        listEvent.add(event5);
        listEvent.add(event6);

        EventAdapter ea = new EventAdapter(this,listEvent);
//        ArrayAdapter<Event> aa = new ArrayAdapter<Event>(this,
//                	                android.R.layout.simple_list_item_1, );
        	        setListAdapter(ea);

    }

    @Override
    	    protected void onListItemClick(ListView l, View v, int position, long id) {
        	        // TODO Auto-generated method stub
        	        super.onListItemClick(l, v, position, id);
        	        Toast.makeText(Screen3Activity.this, "Choose  " + listEvent.get(position).getNama(),
                            Toast.LENGTH_SHORT).show();
                    Intent eventIntent = new Intent(Screen3Activity.this,Screen2Activity.class);
                    String nameEvent = listEvent.get(position).getNama();
                    eventIntent.putExtra("event", nameEvent);
                    startActivity(eventIntent);
       	    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_screen3, menu);
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
