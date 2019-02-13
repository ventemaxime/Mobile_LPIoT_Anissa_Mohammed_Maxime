package com.example.vente.login2.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.vente.login2.R;
import com.pedro.vlc.VlcListener;
import com.pedro.vlc.VlcVideoLibrary;

/**
 * Created by vente on 04/02/2019.
 */

public class CameraActivity extends AppCompatActivity implements VlcListener {

    private VlcVideoLibrary vlcPlayer;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        setTitle("Caméra");

        SurfaceView sf = findViewById(R.id.previewSV);
        vlcPlayer = new VlcVideoLibrary(this, this, sf);
        Button btn = findViewById(R.id.buttonStart);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mrl = "rtsp://184.72.239.149/vod/mp4:BigBuckBunny_175k.mov";
                vlcPlayer.play(mrl);
            }
        });
        Button btn2 = findViewById(R.id.buttonStop);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vlcPlayer.stop();
            }
        });

    }

    @Override
    public void onComplete() {
        Toast.makeText(getApplicationContext(), "Playing", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onError() {
        Toast.makeText(getApplicationContext(), "Incorrect MRL", Toast.LENGTH_LONG).show();
        vlcPlayer.stop();
    }
}
