package com.example.vente.login2.common;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.vente.login2.R;



public class MessageViewHolder extends RecyclerView.ViewHolder {

    TextView txtName;
    TextView txtMessage;

    public MessageViewHolder(View itemView) {
        super(itemView);

        txtName = itemView.findViewById(R.id.name);
        txtMessage = itemView.findViewById(R.id.message);
    }

    public void bind(Message message) {
        txtName.setText(message.getSource().getPrenom() + " " + message.getSource().getNom());
        txtMessage.setText(message.getMessage());
    }

}