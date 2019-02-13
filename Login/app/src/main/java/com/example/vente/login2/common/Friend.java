package com.example.vente.login2.common;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vente on 28/01/2019.
 */

public class Friend implements Serializable {

    private int id;
    @SerializedName("token")
    private String token;
    @SerializedName("first_name")
    private String prenom;
    @SerializedName("last_name")
    private String nom;
    @SerializedName("last_score")
    private int score;
    @SerializedName("profile_url")
    private String image;
    @SerializedName("random_color")
    private String randomColor;
    @SerializedName("is_present")
    private int isPresent;


    public Friend(int id, String token, String prenom, String nom, int score, String image, int isPresent, String randomColor){
        this.id = id;
        this.token = token;
        this.prenom = prenom;
        this.nom = nom;
        this.score = score;
        this.image = image;
        this.isPresent = isPresent;
        this.randomColor = randomColor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getRandomColor() {
        return randomColor;
    }

    public void setRandomColor(String randomColor) {
        this.randomColor = randomColor;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getIsPresent() {
        return isPresent;
    }

    public void setIsPresent(int isPresent) {
        this.isPresent = isPresent;
    }

    public boolean isMe(Friend f){
        if(f.getToken().equals(this.token)){
            return true;
        }
        return false;
    }

    public static Friend getFriendFromJson(String json){
        return new Gson().fromJson(json, Friend.class);
    }

    public static ArrayList<Friend> getListOfFriendsFromJson(String json){
        Type type = new TypeToken<List<Friend>>(){}.getType();
        return new Gson().fromJson(json, type);
    }

    @Override
    public String toString() {
        return "Friend{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", score=" + score +
                ", image='" + image + '\'' +
                ", randomColor='" + randomColor + '\'' +
                ", isPresent=" + isPresent +
                '}';
    }
}
