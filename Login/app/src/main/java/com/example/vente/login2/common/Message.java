package com.example.vente.login2.common;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Created by vente on 30/01/2019.
 */

public class Message {


    private Friend source;
    @SerializedName("message")
    private String message;

    public Message(Friend source, String message){
        this.source = source;
        this.message = message;
    }

    public Friend getSource(){
        return this.source;
    }

    public String getMessage() {
        return message;
    }


    public static Message getMessageFromJson(String json){
        return new Gson().fromJson(json, Message.class);
    }

    public static String setMessageToJson(Message message){
        return new Gson().toJson(message);
    }
}
