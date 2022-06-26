package com.example.catbreedsquizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.catbreedsquizgame.databinding.ActivityResultBinding;

public class ResultActivity extends AppCompatActivity {

    private int correct, wrong, empty, rate;
    private ActivityResultBinding resultBinding;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resultBinding = ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(resultBinding.getRoot());

        correct = getIntent().getIntExtra("correct",0);
        wrong = getIntent().getIntExtra("wrong",0);
        empty = getIntent().getIntExtra("empty",0);
        rate = getIntent().getIntExtra("rate",0);

        String textC =  resultBinding.textViewTotalCorrectAns.getText().toString();
        String textW =  resultBinding.textViewTotalWrongAns.getText().toString();
        String textE =  resultBinding.textViewTotalEmptyAns.getText().toString();
        String textR =  resultBinding.textViewTotalSuccessRate.getText().toString();
        resultBinding.textViewTotalCorrectAns.setText(textC+" "+correct);
        resultBinding.textViewTotalWrongAns.setText(textW+" "+wrong);
        resultBinding.textViewTotalEmptyAns.setText(textE+" "+empty);
        resultBinding.textViewTotalSuccessRate.setText(textR+" "+rate);

        resultBinding.btnAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iTAgain = new Intent(ResultActivity.this,QuizActivity.class);
                startActivity(iTAgain);
                finish();
            }
        });
        resultBinding.btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iClose = new Intent(Intent.ACTION_MAIN);
                iClose.addCategory(Intent.CATEGORY_HOME);
                iClose.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(iClose);
                finish();
            }
        });
    }
}