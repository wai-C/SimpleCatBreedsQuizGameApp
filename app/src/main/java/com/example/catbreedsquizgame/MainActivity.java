package com.example.catbreedsquizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.catbreedsquizgame.database.DatabaseCopyHelper;
import com.example.catbreedsquizgame.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        copyDb();
        mainBinding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iToQuiz = new Intent(MainActivity.this,QuizActivity.class);
                startActivity(iToQuiz);
                finish();
            }
        });
    }
    public void copyDb(){
        try {
            DatabaseCopyHelper helper = new DatabaseCopyHelper(MainActivity.this);
            helper.createDataBase();
            helper.openDataBase();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}