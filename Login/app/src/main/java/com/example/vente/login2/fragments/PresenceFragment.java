package com.example.vente.login2.fragments;


import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.vente.login2.R;
import com.example.vente.login2.activities.OtherActivity;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import android.Manifest;
import android.widget.FrameLayout;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import me.dm7.barcodescanner.zbar.BarcodeFormat;
import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

/**
 * Created by vente on 05/02/2019.
 */

public class PresenceFragment extends Fragment implements ZBarScannerView.ResultHandler {

    private ZBarScannerView scannerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle(R.string.presence_title);
        View rootView = inflater.inflate(R.layout.fragment_presence, container, false);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && getActivity().checkSelfPermission(Manifest.permission.CAMERA) !=
        PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{
                    Manifest.permission.CAMERA}, 99);
        } else {
            scannerView = new ZBarScannerView(getActivity());
            List<BarcodeFormat> formats = new ArrayList<>();
            formats.add(BarcodeFormat.QRCODE);

            FrameLayout contentQR = rootView.findViewById(R.id.contentQR);
            contentQR.addView(scannerView);
        }
        return rootView;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
       if(requestCode == 99 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

       }
    }

    private boolean checkCameraHardware(Context ctx){
        if(ctx.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            return true;
        } else {
            return false;
        }
    }
    public void remoteSign(String token, String qrcode){
        String urlString = getString(R.string.url) + "/api/qrcode-sign.php?key=iot1235&token=" + token + "&qrcode=" + qrcode;
        Ion.with(this).load(urlString).asJsonObject().withResponse().setCallback(new FutureCallback<Response<JsonObject>>() {
            @Override
            public void onCompleted(Exception e, Response<JsonObject> result) {
                if(result.getHeaders().code() == 200){

                }
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();

        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }

    @Override
    public void onPause(){
        super.onPause();
        scannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        String qrcode = result.getContents();
        Log.d("qrcode", "handleResult: " + qrcode);
        Toast.makeText(getContext(), qrcode, Toast.LENGTH_LONG).show();
        remoteSign(OtherActivity.me.getToken(), qrcode);
    }
}
