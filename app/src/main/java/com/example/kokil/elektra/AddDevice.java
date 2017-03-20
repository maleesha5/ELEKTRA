package com.example.kokil.elektra;


import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import static android.content.Context.WIFI_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 */
//This is to check gitHub
public class AddDevice extends Fragment {

    Switch wifiSwitch;
    WifiManager wifiManager;


    public AddDevice() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_device, container, false);
        wifiSwitch = (Switch) rootView.findViewById(R.id.wifiSwitch);
        wifiManager = (WifiManager) getActivity().getSystemService(WIFI_SERVICE);
        wifiSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // To on Wifi
                if (isChecked && !wifiManager.isWifiEnabled()) {
                    wifiManager.setWifiEnabled(true);
                }
                //To off Wifi
                else if (!isChecked && wifiManager.isWifiEnabled()){
                    wifiManager.setWifiEnabled(false);
                }
            }
        });


        return rootView;
    }

}
