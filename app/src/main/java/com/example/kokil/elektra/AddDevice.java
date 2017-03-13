package com.example.kokil.elektra;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 */
//This is to check gitHub
public class AddDevice extends Fragment implements  View.OnClickListener{


    public AddDevice() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_connect_device, container, false);
        Button gotIt = (Button) rootView.findViewById(R.id.gotItBut);
        gotIt.setOnClickListener(this);

      /*  gotIt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.gotItBut:
                        //what to put here
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.fragment_schedule, new Schedule() ,"fragment_screen");
                        ft.commit();
                        break;
                }
            }
        });*/



        return rootView;
    }
    @Override
    public void onClick(View v) {
        Context context = getContext();
        CharSequence text = "Hello toast!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

      /*  Fragment fragment = null;
        switch (v.getId()) {
            case R.id.gotItBut:
                fragment = new Schedule();
                replaceFragment(fragment);
                break;
        }*/
    }
    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_add_devices, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
