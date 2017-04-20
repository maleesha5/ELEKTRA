package com.example.kokil.elektra;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;

import static com.google.android.gms.internal.zzs.TAG;

/**
 * Created by Hemal on 4/7/2017.
 */

class WeatherStatus extends AsyncTask<Void, Void, Void> {
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //  Toast.makeText(MainActivity.this,"Json Data is
        //          downloading",Toast.LENGTH_LONG).show();

    }

    @Override
    protected Void doInBackground(Void... arg0) {
        HttpHandler sh = new HttpHandler();
        // Making a request to url and getting response
        String url = "http://api.openweathermap.org/data/2.5/weather?q=Kurunegala&appid=6b305548a0cfd94c7fa2bcbc99037db8";
        String jsonStr = sh.makeServiceCall(url);

        Log.e(TAG, "Response from url: " + jsonStr);
        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);

                // Getting JSON Array node
                JSONArray weather = jsonObj.getJSONArray("weather");

                // looping through All Contacts
                for (int i = 0; i < weather.length(); i++) {
                    JSONObject c = weather.getJSONObject(i);
                    String id = c.getString("id");
                    String main = c.getString("main");
                    String descrip = c.getString("description");
                    String icon = c.getString("icon");
                    // String gender = c.getString("gender");

                    // Phone node is JSON Object
                     /*   JSONObject phone = c.getJSONObject("phone");
                        String mobile = phone.getString("mobile");
                        String home = phone.getString("home");
                        String office = phone.getString("office");*/

                    // tmp hash map for single contact
                    HashMap<String, String> info = new HashMap<>();

                    // adding each child node to HashMap key => value
                    info.put("id", id);
                    info.put("main", main);
                    info.put("description", descrip);
                    info.put("icon", icon);
                    Log.e(TAG, "Array" + info);
                    // adding contact to contact list
                    // contactList.add(info);
                }
            } catch (final JSONException e) {
                 /*   Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });*/

            }

        } else {
            Log.e(TAG, "Couldn't get json from server.");
            //  runOnUiThread(new Runnable() {
               /*     @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }*/
            // });
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
         /*   super.onPostExecute(result);
            ListAdapter adapter = new SimpleAdapter(MainActivity.this, contactList,
                    R.layout.list_item, new String[]{ "email","mobile"},
                    new int[]{R.id.email, R.id.mobile});
            lv.setAdapter(adapter);
        }*/
    }
}

