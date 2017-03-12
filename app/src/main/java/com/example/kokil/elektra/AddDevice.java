package com.example.kokil.elektra;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
//This is to check gitHub
public class AddDevice extends Fragment {


    public AddDevice() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.wifi, container, false);

        {
           // Intent intent = new Intent(container,Wifidemo.class);
           // startActivity(intent);
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_device, container, false);
    }

}
