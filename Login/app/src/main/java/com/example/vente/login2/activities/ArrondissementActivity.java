package com.example.vente.login2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.vente.login2.R;
import com.example.vente.login2.adapter.ArrondissementAdapter;
import com.example.vente.login2.common.Arrondissement;

public class ArrondissementActivity extends AppCompatActivity {
    private Arrondissement[] arrondissements = {
            new Arrondissement(R.string.Paris_1_er_Arrondissement, R.drawable.img_district1),
            new Arrondissement(R.string.Paris_2_éme_Arrondissement, R.drawable.img_district2),
            new Arrondissement(R.string.Paris_3_éme_Arrondissement, R.drawable.img_district3),
            new Arrondissement(R.string.Paris_4_éme_Arrondissement, R.drawable.img_district4),
            new Arrondissement(R.string.Paris_5_éme_Arrondissement, R.drawable.img_district5),
            new Arrondissement(R.string.Paris_6_éme_Arrondissement, R.drawable.img_district6),
            new Arrondissement(R.string.Paris_7_éme_Arrondissement, R.drawable.img_district7),
            new Arrondissement(R.string.Paris_8_éme_Arrondissement, R.drawable.img_district8),
            new Arrondissement(R.string.Paris_9_éme_Arrondissement, R.drawable.img_district9),
            new Arrondissement(R.string.Paris_10_éme_Arrondissement, R.drawable.img_district10),
            new Arrondissement(R.string.Paris_11_éme_Arrondissement, R.drawable.img_district11),
            new Arrondissement(R.string.Paris_12_éme_Arrondissement, R.drawable.img_district12),
            new Arrondissement(R.string.Paris_13_éme_Arrondissement, R.drawable.img_district13),
            new Arrondissement(R.string.Paris_14_éme_Arrondissement, R.drawable.img_district14),
            new Arrondissement(R.string.Paris_15_éme_Arrondissement, R.drawable.img_district15),
            new Arrondissement(R.string.Paris_16_éme_Arrondissement, R.drawable.img_district16),
            new Arrondissement(R.string.Paris_17_éme_Arrondissement, R.drawable.img_district17),
            new Arrondissement(R.string.Paris_18_éme_Arrondissement, R.drawable.img_district18),
            new Arrondissement(R.string.Paris_19_éme_Arrondissement, R.drawable.img_district19),
            new Arrondissement(R.string.Paris_20_éme_Arrondissement, R.drawable.img_district20),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrondissement);
        GridView gridView = (GridView) findViewById(R.id.gridview);
        ArrondissementAdapter adapter = new ArrondissementAdapter(this, arrondissements);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                //Arrondissement arr = arro[position];
                Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                Bundle bundle = getIntent().getExtras();
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView parent, View view, int position, long id) {
                Toast.makeText(ArrondissementActivity.this, arrondissements[position].getName(), Toast.LENGTH_SHORT).show();
                return true;
            }


        });

    }
}
