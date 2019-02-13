package com.example.vente.login2.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vente.login2.R;

/**
 * Created by vente on 05/02/2019.
 */

public class ParametreFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle(R.string.parametre_title);
        View rootView = inflater.inflate(R.layout.fragment_parametre, container, false);

        return rootView;
    }
}
