package com.example.catbreedsquizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.catbreedsquizgame.R;

public class SplashScreenActivity extends AppCompatActivity {

    ImageView imageView_Splash;
    TextView textView_Splash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        imageView_Splash = findViewById(R.id.imageViewSplash);
        textView_Splash = findViewById(R.id.textViewSplash);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.splash_screen_anim);
        textView_Splash.startAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Open The main Act
                Intent iToMainAct = new Intent(SplashScreenActivity.this,MainActivity.class);
                startActivity(iToMainAct);
                finish();
            }
        }, 5000);
    }
}