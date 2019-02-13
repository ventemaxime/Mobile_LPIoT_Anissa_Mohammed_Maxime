package com.example.vente.login2.common;

import java.util.ArrayList;

public class Question {
    private String question;
    private String reponse;
    private ArrayList<String> mauvaisesReponses;

    public Question(String question, String reponse, ArrayList<String> mauvaisesReponses){
        this.question = question;
        this.reponse = reponse;
        this.mauvaisesReponses = mauvaisesReponses;
    }

    public String getQuestion(){
        return this.question;
    }

    public String getReponse(){
        return this.reponse;
    }

    public ArrayList<String> getMauvaisesReponses(){
        return this.mauvaisesReponses;
    }

    public String toString(){
        return this.question +  " - " + this.reponse + " / " + this.mauvaisesReponses.get(0) + "," + mauvaisesReponses.get(1)+ "," + mauvaisesReponses.get(2);
    }
}
