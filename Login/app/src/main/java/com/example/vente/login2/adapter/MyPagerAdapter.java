package com.example.vente.login2.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.vente.login2.R;
import com.example.vente.login2.fragments.DoorFragment;
import com.example.vente.login2.fragments.LightFragment;
import com.example.vente.login2.fragments.TemperatureFragment;


/**
 * Created by vente on 04/02/2019.
 */

public class MyPagerAdapter extends FragmentPagerAdapter {
    private Context ctx;

    public MyPagerAdapter(Context ctx, FragmentManager fm){super(fm); this.ctx=ctx;}

    @Override
    public Fragment getItem(int position){
        switch(position) {
            case 0: return new TemperatureFragment();
            case 1: return new DoorFragment();
            case 2: return new LightFragment();
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position){
        switch(position){
            case 0: return ctx.getString(R.string.temperature_title);
            case 1: return ctx.getString(R.string.door_title);
            case 2: return ctx.getString(R.string.light_title);
        }
        return null;
    }

    @Override
    public int getCount(){
        return 3;
    }


}
