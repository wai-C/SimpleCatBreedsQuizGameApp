package com.example.catbreedsquizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.catbreedsquizgame.dao.BreedDAO;
import com.example.catbreedsquizgame.database.BreedsDatabase;
import com.example.catbreedsquizgame.model.BreedsModel;
import com.example.catbreedsquizgame.databinding.ActivityQuizBinding;

import java.util.ArrayList;
import java.util.HashSet;

public class QuizActivity extends AppCompatActivity {

    private boolean chose;
    private int correct = 0;
    private int wrong = 0;
    private int empty = 0;
    private int questionNb = 0;

    private BreedsModel breedsModelAns;
    private BreedsDatabase breedsDatabase;
    private ArrayList<BreedsModel> wrongAns;
    private ArrayList<BreedsModel> questions;
    private ActivityQuizBinding quizBinding;
    private HashSet<BreedsModel> mixOptions = new HashSet<>();
    private ArrayList<BreedsModel> options = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        quizBinding = ActivityQuizBinding.inflate(getLayoutInflater());
        setContentView(quizBinding.getRoot());
        breedsDatabase = new BreedsDatabase(QuizActivity.this); //create db when Quiz is running
        questions = new BreedDAO().getRandom5Question(breedsDatabase); //get questions
        loadQuestions();
        quizBinding.btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAns(quizBinding.btnA);
            }
        });
        quizBinding.btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAns(quizBinding.btnB);
            }
        });
        quizBinding.btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAns(quizBinding.btnC);
            }
        });
        quizBinding.btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAns(quizBinding.btnD);
            }
        });
        quizBinding.imageBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionNb++;
                checkAction();
            }
        });
    }

    public void loadQuestions() {
        quizBinding.textViewQuestion.setText("Question: " + (questionNb + 1)); //with (questionNb +1) => will show 1 instead of 01
        breedsModelAns = questions.get(questionNb); // start from 1st Q
        //get image according the name in db, because they have the same names;
        quizBinding.imageViewCat.setImageResource(getResources().getIdentifier(breedsModelAns.getImage(), "drawable", getPackageName()));
        wrongAns = new BreedDAO().getRandom3WrongAns(breedsDatabase, breedsModelAns.getId());

        mixOptions.clear();
        //Total 4 options, 1 Correct Ans, 3 Wrong Ans
        mixOptions.add(breedsModelAns);
        mixOptions.add(wrongAns.get(0));
        mixOptions.add(wrongAns.get(1));
        mixOptions.add(wrongAns.get(2));

        //pass the mixed options to the options list
        options.clear();
        for (BreedsModel flagsModel : mixOptions) {
            options.add(flagsModel);
        }
        quizBinding.btnA.setText(options.get(0).getName());
        quizBinding.btnB.setText(options.get(1).getName());
        quizBinding.btnC.setText(options.get(2).getName());
        quizBinding.btnD.setText(options.get(3).getName());
    }

    private void checkAns(Button button) {
        String text = button.getText().toString();
        String correctAns = breedsModelAns.getName();
        if (text.equals(correctAns)) {
            correct++;
            button.setBackgroundColor(Color.GREEN);
        } else {
            wrong++;
            button.setBackgroundColor(Color.RED);
            if (quizBinding.btnA.getText().toString().equals(correctAns)) {
                quizBinding.btnA.setBackgroundColor(Color.GREEN);
            }
            if (quizBinding.btnB.getText().toString().equals(correctAns)) {
                quizBinding.btnB.setBackgroundColor(Color.GREEN);
            }
            if (quizBinding.btnC.getText().toString().equals(correctAns)) {
                quizBinding.btnC.setBackgroundColor(Color.GREEN);
            }
            if (quizBinding.btnD.getText().toString().equals(correctAns)) {
                quizBinding.btnD.setBackgroundColor(Color.GREEN);
            }
        }
        quizBinding.btnA.setClickable(false);
        quizBinding.btnB.setClickable(false);
        quizBinding.btnC.setClickable(false);
        quizBinding.btnD.setClickable(false);
        quizBinding.textViewCorrect.setText("Correct: " + correct);
        quizBinding.textViewWrong.setText("Wrong: " + wrong);

        chose = true;
    }
    private void checkAction(){
        if(!chose&&questionNb<5){
            empty++;
            quizBinding.textViewEmpty.setText("Empty: "+empty);
            loadQuestions();
        }
        else if(chose && questionNb < 5){
            loadQuestions();
            quizBinding.btnA.setBackgroundColor(Color.BLACK);
            quizBinding.btnB.setBackgroundColor(Color.BLACK);
            quizBinding.btnC.setBackgroundColor(Color.BLACK);
            quizBinding.btnD.setBackgroundColor(Color.BLACK);

            quizBinding.btnA.setClickable(true);
            quizBinding.btnB.setClickable(true);
            quizBinding.btnC.setClickable(true);
            quizBinding.btnD.setClickable(true);
        }
        else if(questionNb == 5){
            Intent iTRes = new Intent(QuizActivity.this,ResultActivity.class);
            iTRes.putExtra("correct",correct);
            iTRes.putExtra("wrong",wrong);
            iTRes.putExtra("empty",empty);
            iTRes.putExtra("rate",(correct/questionNb)*100);
            startActivity(iTRes);
            finish();
        }
        chose = false;
    }
}