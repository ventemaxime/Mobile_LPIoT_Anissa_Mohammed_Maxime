package com.example.vente.login2.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.vente.login2.common.Friend;
import com.example.vente.login2.R;
import com.example.vente.login2.fragments.ChallengeFragment;
import com.example.vente.login2.fragments.ChatFragment;
import com.example.vente.login2.fragments.ParametreFragment;
import com.example.vente.login2.fragments.PresenceFragment;
import com.example.vente.login2.fragments.SensorFragment;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by vente on 07/12/2018.
 */

public class OtherActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static Friend me;
    public static ArrayList<Friend> friends;
    public ActionBarDrawerToggle toggle;
    public FragmentManager fm;
    public DrawerLayout drawer;
    public NavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        getRemoteFriends();
        drawer = findViewById(R.id.drawer);
        nav = findViewById(R.id.nav_view);
        fm = getSupportFragmentManager();
        View header = nav.getHeaderView(0);

        nav.setNavigationItemSelectedListener(this);
        nav.getMenu().performIdentifierAction(R.id.nav_first, 0);

        toggle = new ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close);

        drawer.setDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        Bundle b = i.getExtras();
        me = (Friend) b.getSerializable("moi");
        String profile = me.getImage();
        CircleImageView profileCVH = header.findViewById(R.id.profile);
        if(profile != null && !profile.isEmpty()){
            String profileUrl = getString(R.string.url) + "iot-server/profiles/" + profile;
            Picasso.with(getApplicationContext()).load(profileUrl).into(profileCVH);
        } else {
            TextView letterTV = header.findViewById(R.id.letterH);
            Drawable color = new ColorDrawable(Color.parseColor(OtherActivity.me.getRandomColor()));
            profileCVH.setImageDrawable(color);
            letterTV.setText(OtherActivity.me.getPrenom().charAt(0) + "");
            letterTV.setVisibility(View.VISIBLE);
        }
        String p = me.getPrenom();
        String n = me.getNom();
        TextView t = header.findViewById(R.id.nameTV);
        t.setText(p + " " + n);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        return toggle.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.nav_first: fm.beginTransaction().replace(R.id.contentFL, new PresenceFragment()).commit(); drawer.closeDrawers(); break;
            case R.id.nav_second: fm.beginTransaction().replace(R.id.contentFL, new ChallengeFragment()).commit(); drawer.closeDrawers(); break;
            case R.id.nav_third: fm.beginTransaction().replace(R.id.contentFL, new SensorFragment()).commit(); drawer.closeDrawers(); break;
            case R.id.nav_fourth: drawer.closeDrawers(); Intent i = new Intent(getApplication(), CameraActivity.class); startActivity(i); break;
            case R.id.nav_fifth: fm.beginTransaction().replace(R.id.contentFL, new ParametreFragment()).commit(); drawer.closeDrawers(); break;
            case R.id.nav_sixth: drawer.closeDrawers(); Intent j = new Intent(getApplication(), LoginActivity.class); startActivity(j); break;
            case R.id.nav_seventh: fm.beginTransaction().replace(R.id.contentFL, new ChatFragment()).commit(); drawer.closeDrawers(); break;
        }
        return false;
    }

    public void getRemoteFriends(){
        String urlString = getString(R.string.url) + "api/getFriends.php?key=iot1235";
        Ion.with(this).load(urlString).asString().setCallback(new FutureCallback<String>() {
            @Override
            public void onCompleted(Exception e, String result) {
                friends = Friend.getListOfFriendsFromJson(result);
            }
        });
    }
}
