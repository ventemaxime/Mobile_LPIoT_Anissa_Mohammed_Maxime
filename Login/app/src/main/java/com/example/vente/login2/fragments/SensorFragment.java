package com.example.vente.login2.fragments;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vente.login2.R;
import com.example.vente.login2.adapter.MyPagerAdapter;
import com.example.vente.login2.common.RetrieveDataTask;
import com.example.vente.login2.common.TemperatureSensorData;

/**
 * Created by vente on 28/01/2019.
 */

public class SensorFragment extends Fragment {


    public SensorFragment(){
        super();
    }

    public static TemperatureSensorData datas;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        getActivity().setTitle(R.string.sensors_title);

        View rootView = inflater.inflate(R.layout.fragment_sensor, container, false);

        ViewPager pagesVP = rootView.findViewById(R.id.pagesVP);
        TabLayout slidingTL = rootView.findViewById(R.id.slidingTL);

        MyPagerAdapter adapter = new MyPagerAdapter(getContext(), getActivity().getSupportFragmentManager());
        pagesVP.setAdapter(adapter);
        slidingTL.setupWithViewPager(pagesVP);

        return rootView;
    }

}
