package com.example.vente.login2.activities;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.vente.login2.R;
import com.example.vente.login2.common.Friend;
import com.example.vente.login2.common.Question;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

import de.hdodenhof.circleimageview.CircleImageView;

public class QuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        setTitle(R.string.quiz_title);
        Bundle bundle = getIntent().getExtras();

        Friend me = (Friend) bundle.getSerializable("moi");
        Friend f = (Friend) bundle.getSerializable("ami");
        CircleImageView imageJoueur1 = findViewById(R.id.imageJoueur1);
        CircleImageView imageJoueur2 = findViewById(R.id.imageJoueur2);
        TextView lettreJ1 = findViewById(R.id.lettreJ1);
        TextView lettreJ2 = findViewById(R.id.lettreJ2);
        TextView pseudo1 = findViewById(R.id.quizPseudo1);
        TextView pseudo2 = findViewById(R.id.quizPseudo2);
        pseudo1.setText(me.getPrenom());
        pseudo2.setText(f.getPrenom());

        setImage(me, imageJoueur1, lettreJ1);
        setImage(f, imageJoueur2, lettreJ2);

        //Récupération de la question
        Question q = null;
        try {
            q = getQuestions().get(bundle.getInt("position"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        TextView question = findViewById(R.id.question);
        question.setText(q.getQuestion());

        //Récupération de toutes les réponses
        ArrayList<String> liste = q.getMauvaisesReponses();
        liste.add(q.getReponse());
        Collections.shuffle(liste);

        //Récupération des boutons
        final Button repA = findViewById(R.id.reponse1);
        final Button repB = findViewById(R.id.reponse2);
        final Button repC = findViewById(R.id.reponse3);
        final Button repD = findViewById(R.id.reponse4);
        //Mise en place des réponses
        repA.setText(liste.get(0));
        repB.setText(liste.get(1));
        repC.setText(liste.get(2));
        repD.setText(liste.get(3));

        //Préparation d'une liste de bouton pour éviter la redondance
        final ArrayList<Button> reponses = new ArrayList<>();
        reponses.add(repA);
        reponses.add(repB);
        reponses.add(repC);
        reponses.add(repD);

        //Mise en place de l'horloge
        final Integer[] seconde = new Integer[1];
        final Question finalQ = q;
        final CountDownTimer timer = new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long l) {
                ProgressBar time = findViewById(R.id.progressBar2);
                TextView horloge = findViewById(R.id.quizTimer);
                seconde[0] = ((int) l/1000);
                time.setProgress(seconde[0] *100/30);
                horloge.setText(seconde[0].toString());
            }

            @Override
            public void onFinish() {
                TextView text = findViewById(R.id.quizPseudo2);
                for(Button a: reponses){
                    if(a.getText().equals(finalQ.getReponse())){
                        a.setBackgroundColor(Color.GREEN);
                    }
                }
            }
        };

        //Mise en place du listener pour chaque bouton
        for(final Button rep : reponses) {
            rep.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (rep.getText().equals(finalQ.getReponse())) {
                        timer.cancel();
                        rep.setBackgroundColor(Color.GREEN);
                        TextView score = findViewById(R.id.quizScore1);
                        String sc = (String) score.getText();
                        Integer i = ((Integer.parseInt(sc) + 1 * seconde[0]) / 2) + 1;
                        score.setText(i.toString());
                    } else {
                        rep.setBackgroundColor(Color.RED);
                        for (Button a : reponses) {
                            if (a.getText().equals(finalQ.getReponse())) {
                                a.setBackgroundColor(Color.GREEN);
                                timer.cancel();
                            }
                        }
                    }
                }
            });
        }
        timer.start();
    }

    public ArrayList<Question> getQuestions() throws JSONException {

        ArrayList<Question> questions = new ArrayList<Question>();

        // On récupère le JSON complet
        JSONObject jsonObject = new JSONObject(loadJSONFromAsset(this.getApplicationContext()));
        // On récupère le tableau d'objets qui nous concernent
        JSONArray array = new JSONArray(jsonObject.getString("results"));
        // Pour tous les objets on récupère les infos
        for (int i = 0; i < array.length(); i++) {
            // On récupère un objet JSON du tableau
            JSONObject obj = new JSONObject(array.getString(i));
            ArrayList<String> mauvaises = new ArrayList<>();
            mauvaises.add(obj.getString("rep1"));
            mauvaises.add(obj.getString("rep2"));
            mauvaises.add(obj.getString("rep3"));
            Question q = new Question(obj.getString("question"), obj.getString("correct_answer"), mauvaises);
            // On fait le lien Personne - Objet JSON
            // On ajoute la personne à la liste
            questions.add(q);
        }

        // On retourne la liste des personnes
        return questions;
    }

    private static String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("quiz.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void setImage(Friend f, CircleImageView c, TextView l){
        String profile = f.getImage();
        if(profile != null && !profile.isEmpty()){
            String profileUrl = getString(R.string.url) + "profiles/" + profile;
            Picasso.with(this).load(profileUrl).into(c);
        } else {
            Drawable color = new ColorDrawable(Color.parseColor(f.getRandomColor()));
            c.setImageDrawable(color);
            l.setText(f.getPrenom().charAt(0) + "");
            l.setVisibility(View.VISIBLE);
        }
    }
}
