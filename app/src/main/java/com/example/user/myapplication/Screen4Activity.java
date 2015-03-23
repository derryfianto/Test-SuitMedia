package com.example.user.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.user.myapplication.Adapter.GuestAdapter;
import com.example.user.myapplication.Model.Guest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Screen4Activity extends Activity implements AdapterView.OnItemClickListener, View.OnClickListener {

    ArrayList<Guest> listGuest = new ArrayList<>();

    private ProgressDialog pDialog;
    private static String url = "http://dry-sierra-6832.herokuapp.com/api/people";
    private static final String data_ID = "id";
    private static final String data_name = "name";
    private static final String data_birthdate = "birthdate";
    JSONArray guests = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen4);

        new GetGuest().execute();

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    private class GetGuest extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(Screen4Activity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            String readTwitterFeed = readGuest();
            try {
                JSONArray jsonArray = new JSONArray(readTwitterFeed);
                Log.i(Screen4Activity.class.getName(),
                        "Number of entries " + jsonArray.length());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    int id = jsonObject.getInt(data_ID);
                    String name = jsonObject.getString(data_name);
                    String birthdate = jsonObject.getString(data_birthdate);
                    listGuest.add(new Guest(id, name, birthdate, R.drawable.running));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing()) {
                pDialog.dismiss();
            }

//            gridView = (GridView) findViewById(R.id.gridView);
//            customGridAdapter = new GridViewAdapter(Screen4.this, R.layout.grid_view_item, guestList);
//            gridView.setAdapter(customGridAdapter);

            GridView gridView = (GridView) findViewById(R.id.gridviewGuest);
            gridView.setAdapter(new GuestAdapter(Screen4Activity.this,R.layout.screen4_item,listGuest));
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String tgl_lahir =listGuest.get(position).getBirthdate();
                    String tggl_substring = tgl_lahir.substring(7);
                    String bulan_substring = tgl_lahir.substring(4,7);

                    int tggl = Integer.valueOf(tggl_substring);
                    int bulan = Integer.valueOf(bulan_substring);

                    String hasil="";
                    if(tggl%2==0 && tggl%3==0)
                    {
                        hasil="iOS";
                    }
                    else if(tggl%2==0)
                    {
                        hasil="blackberry";
                    }
                    else if(tggl%3==0)
                    {
                        hasil="android";
                    }
                    else
                    {
                        hasil="feature phone";
                    }

                    Toast.makeText(Screen4Activity.this, hasil, Toast.LENGTH_SHORT).show();

                    //Cek Prima
                    int counter =0;
                    for(int i=1;i<=bulan;i++)
                    {
                        if(bulan%i==0)
                        {
                            counter = counter + 1;
                        }
                    }

                    if(counter==2)
                    {
                        Toast.makeText(Screen4Activity.this, "Bulan adalah Prima", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(Screen4Activity.this, "Bulan bukan Prima", Toast.LENGTH_SHORT).show();
                    }

                    Intent guestIntent = new Intent(Screen4Activity.this, Screen2Activity.class);
                    guestIntent.putExtra("guest",listGuest.get(position).getNama());
                    startActivity(guestIntent);
                }
            });
        }

    }

    public String readGuest() {
        StringBuilder builder = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse response = client.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
            } else {
                Log.e(Screen4Activity.class.toString(), "Failed to download file");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
