package com.example.vente.login2.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.vente.login2.R;
import com.example.vente.login2.activities.OtherActivity;
import com.example.vente.login2.common.Friend;
import com.squareup.picasso.Picasso;


import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by vente on 30/01/2019.
 */

public class FriendAdapter extends ArrayAdapter<Friend> {


    public FriendAdapter(Context context, int resource, List<Friend> objects) {
        super(context, resource, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;

        Friend f = getItem(position);
        if(row == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.friend_list, null);
        }

        CircleImageView profileCV = row.findViewById(R.id.imageAmi);
        String profile = f.getImage();
        if(profile != null && !profile.isEmpty()){
            String profileUrl = getContext().getString(R.string.url) + "profiles/" + profile;
            Picasso.with(getContext()).load(profileUrl).into(profileCV);
            Log.d("photo", "getView: " + f.getPrenom());
        } else {
            TextView letterTV = row.findViewById(R.id.letterTV);
            Drawable color = new ColorDrawable(Color.parseColor(getItem(position).getRandomColor()));
            profileCV.setImageDrawable(color);
            letterTV.setText(f.getPrenom().charAt(0) + "");
            letterTV.setVisibility(View.VISIBLE);
            Log.d("photo", "getView: " + f.getPrenom());
        }
        if(f.getIsPresent() == 1){
            FrameLayout fm = row.findViewById(R.id.invite);
            fm.setVisibility(View.VISIBLE);
        }
        TextView pseudo = row.findViewById(R.id.pseudoAmi);
        pseudo.setText(getItem(position).getPrenom() + " " + getItem(position).getNom());
        TextView score = row.findViewById(R.id.scoreAmi);
        score.setText("Last score : " + getItem(position).getScore());
        return row;
    }


}

