package com.example.kokil.elektra;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

import static android.content.Context.WIFI_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 */
//This is to check gitHub
public class AddDevice extends Fragment {
    
    Switch wifiSwitch;
    WifiManager wifiManager;
    TextView textView;


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
        textView = (TextView)rootView.findViewById(R.id.connections);
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

        MyBroadCastReciever myBroadCastReciever = new MyBroadCastReciever();
        //register the broadcast reciever
        getActivity().registerReceiver(myBroadCastReciever, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));



        return rootView;
    }

    class MyBroadCastReciever extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            StringBuffer stringBuffer = new StringBuffer();
            List<ScanResult> list = wifiManager.getScanResults();
            for (ScanResult scanResult:list){
                stringBuffer.append(scanResult);
            }
            textView.setText(stringBuffer);
        }
    }

}
