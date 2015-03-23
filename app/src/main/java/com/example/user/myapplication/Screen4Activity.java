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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class Screen4Activity extends Activity implements AdapterView.OnItemClickListener, View.OnClickListener {

    public static ArrayList<Guest> listGuest = new ArrayList<>();

    private ProgressDialog pDialog;
    private static String url = "http://dry-sierra-6832.herokuapp.com/api/people";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_BIRTHDATE = "birthdate";
    JSONArray guests = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen4);

        new GetGuest().execute();

//        Guest guest = new Guest("Andi","2014-01-01", R.drawable.football);
//        Guest guest2 = new Guest("Budi","2014-02-02", R.drawable.basketball);
//        Guest guest3 = new Guest("Charlie","2014-03-03", R.drawable.running);
//        Guest guest4 = new Guest("Dede","2014-06-06", R.drawable.swimming);
//        Guest guest5 = new Guest("Joko","2014-02-12", R.drawable.basketball);
//
//        listGuest.add(guest);
//        listGuest.add(guest2);
//        listGuest.add(guest3);
//        listGuest.add(guest4);
//        listGuest.add(guest5);

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String tgl_lahir =listGuest.get(position).getBirthdate();
        System.out.println("TANGgAL"+tgl_lahir);
        String tggl_substring = tgl_lahir.substring(7);
        int tggl = Integer.valueOf(tggl_substring);
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
        Intent guestIntent = new Intent(Screen4Activity.this, Screen2Activity.class);
        guestIntent.putExtra("guest",listGuest.get(position).getNama());
        startActivity(guestIntent);
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
                    int id = jsonObject.getInt(TAG_ID);
                    String name = jsonObject.getString(TAG_NAME);
                    String birthdate = jsonObject.getString(TAG_BIRTHDATE);
                    listGuest.add(new Guest(id, R.drawable.pic1, name, birthdate));
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
            gridView.setAdapter(new GuestAdapter(this,R.layout.screen4_item,listGuest));
            gridView.setOnItemClickListener(this);

            gridView.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView<?> adapterView, View v, int position, long id) {
                    Guest g = (Guest) adapterView.getItemAtPosition(position);
                    String nama = g.getNama();
                    String birthdate = g.getBirthdate();
                    Intent intent = new Intent(Screen4.this,Screen2.class);
                    intent.putExtra("gNama", nama);
                    intent.putExtra("gBirthdate", birthdate);
                    startActivity(intent);
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
                Log.e(Screen4.class.toString(), "Failed to download file");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
