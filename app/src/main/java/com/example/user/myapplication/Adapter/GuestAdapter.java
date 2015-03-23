package com.example.user.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.myapplication.Model.Guest;
import com.example.user.myapplication.R;

import java.util.ArrayList;

/**
 * Created by user on 23-Mar-15.
 */
public class GuestAdapter extends BaseAdapter{

    private Context mContext;
    private ArrayList<Guest> listGuest;
    private int layout;

    public GuestAdapter(Context c, int layout,ArrayList<Guest> listGuest)
    {
        mContext = c;
        this.layout=layout;
        this.listGuest=listGuest;
    }

    @Override
    public int getCount() {
        return listGuest.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;

        ImageView imageView;
        TextView textView;

        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rowView=inflater.inflate(R.layout.screen4_item, parent, false);

        imageView = (ImageView) rowView.findViewById(R.id.guestImage);
        textView = (TextView) rowView.findViewById(R.id.guestName);

        imageView.setImageResource(listGuest.get(position).getImage());
        textView.setText(listGuest.get(position).getNama());
        return rowView;
    }
}
