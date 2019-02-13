package com.example.vente.login2.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vente.login2.common.Friend;
import com.example.vente.login2.R;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle(R.string.login_title);
        Button auth = findViewById(R.id.auth);
        auth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText ed = findViewById(R.id.editText3);
                String id = ed.getText().toString();
                ed = findViewById(R.id.editText4);
                String mp = ed.getText().toString();
                remoteLogin(id, mp);
            }
        });
    }

    public void remoteLogin(String username, String password) {
        String urlString = getString(R.string.url) + "api/login.php?key=iot1235&username=" + username + "&password=" + password;
        Ion.with(this).load(urlString).asJsonObject().setCallback(new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                String s = null;
                if(result.get("token") != null){
                    Intent i = new Intent(getApplicationContext(), OtherActivity.class);
                    Friend me = Friend.getFriendFromJson(result.toString());
                    Bundle bnd = new Bundle();
                    bnd.putSerializable("moi",me);
                    i.putExtras(bnd);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), "Identifiant ou mot de passe incorrect !", Toast.LENGTH_LONG).show();
                }
            }
        });
       /**if(username.equals("mvente") && password.equals("tralala")){
            Intent i = new Intent(getApplicationContext(), OtherActivity.class);
            Friend me = new Friend(1, "token", "Maxime", "Vente", 0, null, 1, "#121212");
            Bundle bnd = new Bundle();
            bnd.putSerializable("moi",me);
            i.putExtras(bnd);
            startActivity(i);
        }*/
    }
}
