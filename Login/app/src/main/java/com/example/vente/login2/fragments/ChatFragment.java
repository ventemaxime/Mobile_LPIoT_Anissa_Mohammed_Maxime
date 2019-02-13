package com.example.vente.login2.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.vente.login2.R;
import com.example.vente.login2.activities.OtherActivity;
import com.example.vente.login2.adapter.MessageAdapter;
import com.example.vente.login2.common.Friend;
import com.example.vente.login2.common.Message;

import java.util.ArrayList;

import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketException;
import de.tavendo.autobahn.WebSocketHandler;

public class ChatFragment extends Fragment {

    private WebSocketConnection mConnection;
    String serverIpAddress = "192.168.1.16";
    private static final String TAG = "ChatActivity";
    private MessageAdapter mAdapter;
    private ArrayList<Message> messagesRL = new ArrayList<>();
    EditText messageET;
    ImageView sendBtn;
    RecyclerView recyclerView;
    public Friend me;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_chat, container, false);
        getActivity().setTitle(R.string.chat_title);
        messageET = rootView.findViewById(R.id.editMessage);
        sendBtn = rootView.findViewById(R.id.sendIV);
        recyclerView = rootView.findViewById(R.id.messagesRL);
        me = OtherActivity.me;

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setStackFromEnd(true);

        recyclerView.setLayoutManager(layoutManager);

        final MessageAdapter mAdapter = new MessageAdapter(messagesRL);

        recyclerView.setAdapter(mAdapter);

        try {
            mConnection = new WebSocketConnection();
            final String wsUrl = "ws://" + serverIpAddress + ":8080";
            mConnection.connect(wsUrl, new WebSocketHandler() {

                @Override
                public void onOpen() {
                    Log.d(TAG, "Connexion réussie à : " + wsUrl);
                }

                @Override
                public void onClose(int code, String reason) {

                    Log.d(TAG, "Connexion perdue");
                }

                @Override
                public void onTextMessage(String msg) {
                    Log.d(TAG, "Message reçu : " + msg);

                    messagesRL.add(Message.getMessageFromJson(msg));
                    mAdapter.notifyDataSetChanged();

                    scrollToBottom();
                }

                @Override
                public void onRawTextMessage(byte[] msg) {
                }

                @Override
                public void onBinaryMessage(byte[] msg) {
                }
            });

        } catch (
                WebSocketException e) {
            e.printStackTrace();
        }


        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*
                 * On vérifie que notre edittext n'est pas vide
                 */
                if (!TextUtils.isEmpty(getMessage())) {

                    // On met "true" car c'est notre message
                    Message message = new Message(me, getMessage());

                    String json = Message.setMessageToJson(message);
                    Log.d("d", "onClick: " + json);

                    // On envoie notre message
                    mConnection.sendTextMessage(json);
//                    mConnection.sendBinaryMessage(new byte[]);
//                    mConnection.sendRawTextMessage(new byte[]);

                    // On notifie notre adapter
                    mAdapter.notifyDataSetChanged();

                    scrollToBottom();

                    // On efface !
                    messageET.setText("");
                }

            }
        });
        return rootView;
    }


    /**
     * Scroller notre recyclerView en bas
     */
    private void scrollToBottom() {
        recyclerView.scrollToPosition(messagesRL.size() - 1);
    }

    private String getMessage() {
        return messageET.getText().toString().trim();
    }
}
