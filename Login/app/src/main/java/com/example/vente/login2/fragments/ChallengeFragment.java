package com.example.vente.login2.fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.vente.login2.R;
import com.example.vente.login2.activities.ArrondissementActivity;
import com.example.vente.login2.activities.OtherActivity;
import com.example.vente.login2.activities.PrivateChatActivity;
import com.example.vente.login2.adapter.FriendAdapter;
import com.example.vente.login2.common.Arrondissement;
import com.example.vente.login2.common.Friend;

/**
 * Created by vente on 28/01/2019.
 */

public class ChallengeFragment extends Fragment {

    public ChallengeFragment(){super();}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle(R.string.challenge_title);
        View rootView = inflater.inflate(R.layout.fragment_challenge, container, false);
        ListView lv = rootView.findViewById(R.id.listAmis);
        Log.d("friens", "onCreateView: " + OtherActivity.friends);
        FriendAdapter fa = new FriendAdapter(getActivity().getApplicationContext(), R.layout.friend_list, OtherActivity.friends);
        lv.setAdapter(fa);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(OtherActivity.friends.get(i).getIsPresent() == 1) {
                    Bundle bnd = new Bundle();
                    Friend f = OtherActivity.friends.get(i);
                    bnd.putSerializable("moi", OtherActivity.me);
                    bnd.putSerializable("ami", f);
                    Intent in = new Intent(getContext(), ArrondissementActivity.class);
                    in.putExtras(bnd);
                    startActivity(in);
                }
            }
        });
        return rootView;
    }
}
