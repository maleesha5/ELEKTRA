package com.example.kokil.elektra;


import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.ubidots.ApiClient;
import com.ubidots.Value;
import com.ubidots.Variable;

import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class Devices extends Fragment {
    private static double state = 0;
    private TextView out;
    private Switch toggle1;
    private ImageButton item_imageButton;
    private double c;
    private static boolean isOn;

    public Devices() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_devices, container, false);

        out = (TextView) rootView.findViewById(R.id.item_voltage);
        toggle1 = (Switch) rootView.findViewById(R.id.item_switch);
        toggle1.setChecked(false);

        if (isNetworkAvailable()) {
            new ApiUbidots2().execute();
        } else {
            Toast.makeText(getActivity(), "Device is not connected to the internet.",
                    Toast.LENGTH_LONG).show();
        }

        toggle1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isNetworkAvailable()) {
                    if (isChecked) {
                        isOn = true;
                        state = 1;
                    } else {
                        state = 0;
                        isOn = false;
                    }
                    new ApiUbidots().execute();
                } else {
                    Toast.makeText(getActivity(), "Device is not connected to the internet.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });


        return rootView;
    }

    private final String API_KEY = "cada20a3fbf7e42890cb2fa273db6f6bad7eb0b3";
    private final String VARIABLE_ID = "585e7f1176254273e64e6cd8";
    private final String VARIABLE_ID1 = "585e43fa76254273e4ae53ee";


    public class ApiUbidots extends AsyncTask<Integer, Void, Void> {

        @Override
        protected Void doInBackground(Integer... params) {
            ApiClient apiClient = new ApiClient(API_KEY);
            Variable batteryLevel = apiClient.getVariable(VARIABLE_ID);

            batteryLevel.saveValue(state);
            return null;
        }
    }


    public class ApiUbidots2 extends AsyncTask<Double, Void, Double> {


        @Override
        protected Double doInBackground(Double... params) {
            ApiClient apiClient = new ApiClient(API_KEY);
            Variable batteryLevel = apiClient.getVariable(VARIABLE_ID);
            Value[] x = batteryLevel.getValues();
            c = x[0].getValue();
            return c;
        }

        protected void onPostExecute(Double result) {
            if (result == 0) {
                isOn = false;
                toggle1.setChecked(false);
            } else if (result == 1) {
                isOn = true;
                toggle1.setChecked(true);
            }


            Timer timer = new Timer();

            TimerTask doAsynchronousTask = new TimerTask() {
                @Override
                public void run() {

                    new ApiUbidots3().execute();
                }
            };
            timer.schedule(doAsynchronousTask, 0, 2000); //execute in every 2000 ms
        }
    }

    public class ApiUbidots3 extends AsyncTask<Double, Void, Double> {


        @Override
        protected Double doInBackground(Double... params) {
            ApiClient apiClient = new ApiClient(API_KEY);
            Variable batteryLevel = apiClient.getVariable(VARIABLE_ID1);
            Value[] x = batteryLevel.getValues();
            double val = x[0].getValue();
            double roundOffVal = Math.round(val * 100.0) / 100.0;
            return roundOffVal;
        }

        protected void onPostExecute(Double result) {

            if (isOn) {
                out.setText("Output: " + Double.toString(result) + "A");
            } else {
                out.setText("Output: 0A");
            }
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
