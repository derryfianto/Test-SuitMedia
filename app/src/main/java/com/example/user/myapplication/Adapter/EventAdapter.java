package com.example.user.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.myapplication.Model.Event;
import com.example.user.myapplication.R;

import java.util.ArrayList;

/**
 * Created by user on 22-Mar-15.
 */
public class EventAdapter extends ArrayAdapter<Event> {

    private final Context context;
    private final ArrayList<Event> listEvent;


    public EventAdapter(Context context, ArrayList<Event> listEvent) {
        super(context, R.layout.activity_screen3, listEvent);
        this.context=context;
        this.listEvent=listEvent;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
        	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.activity_screen3, parent, false);
        TextView tv = (TextView) rowView.findViewById(R.id.eventName);
        TextView tv2 = (TextView) rowView.findViewById(R.id.tglEvent);
        ImageView iv = (ImageView) rowView.findViewById(R.id.logo);

        String name = listEvent.get(position).getNama();
        String tggl = listEvent.get(position).getTanggal();
        int image = listEvent.get(position).getGambar();
        	        if (name.equals("Football")) {
            	            iv.setImageResource(image);
            	            tv.setText(name);
                            tv2.setText(tggl);
            	        } else if (name.equals("Basketball")) {
            	            iv.setImageResource(image);
            	            tv.setText(name);
                            tv2.setText(tggl);
            	        } else if (name.equals("Swimming")) {
            	            iv.setImageResource(image);
            	            tv.setText(name);
                            tv2.setText(tggl);
            	        } else if (name.equals("Running")) {
            	            iv.setImageResource(image);
            	            tv.setText(name);
                            tv2.setText(tggl);
            	        } else if (name.equals("Badminton")) {
                            iv.setImageResource(image);
                            tv.setText(name);
                            tv2.setText(tggl);
                        } else if (name.equals("Golf")) {
                            iv.setImageResource(image);
                            tv.setText(name);
                            tv2.setText(tggl);
                    }

        	        return rowView;
    }
}
