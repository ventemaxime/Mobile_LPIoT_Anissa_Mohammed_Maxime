package com.example.vente.login2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vente.login2.R;
import com.example.vente.login2.common.Arrondissement;

public class ArrondissementAdapter extends BaseAdapter {

    private final Context mContext;
    private final Arrondissement[] arrondissements;

    public ArrondissementAdapter(Context context, Arrondissement[] arrondissements){
        this.mContext = context;
        this.arrondissements = arrondissements;
    }

    @Override
    public int getCount() {
        return this.arrondissements.length;
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 1
        final Arrondissement ar = this.arrondissements[position];

        // 2
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.grid_arrondissement, null);
        }

        // 3
        final ImageView imageView = (ImageView)convertView.findViewById(R.id.imageview_cover_art);
        final TextView nameTextView = (TextView)convertView.findViewById(R.id.textview_book_name);

        // 4
        imageView.setImageResource(ar.getImageResource());
        nameTextView.setText(mContext.getString(ar.getName()));

        return convertView;
    }
}
