package com.example.vente.login2.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vente.login2.R;

/**
 * Created by vente on 28/01/2019.
 */

public class DoorFragment extends Fragment {

    public DoorFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sensor_door, container, false);

        return rootView;
    }
}
