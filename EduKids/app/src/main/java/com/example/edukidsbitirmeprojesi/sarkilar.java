package com.example.edukidsbitirmeprojesi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class sarkilar extends AppCompatActivity {
    ImageView img1,img2,home,nextChapter;
    Animation dondurme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.sarkilar);
        home = (ImageView) findViewById(R.id.homebutton);
        img1 = (ImageView) findViewById(R.id.im1);
        img2 = (ImageView) findViewById(R.id.im2);
        nextChapter=(ImageView)findViewById(R.id.nextChapter);

        dondurme= AnimationUtils.loadAnimation(this,R.anim.anim_rotate);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(sarkilar.this, sarki_1.class);
                startActivity(intent);
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(sarkilar.this, sarki_2.class);
                startActivity(intent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(sarkilar.this, Bolum1.class);
                startActivity(intent);
                finish();
            }
        });
        nextChapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextChapter.startAnimation(dondurme);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent();
                        intent.setClass(sarkilar.this, Bolum2.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.smalltobig,0);
                        finish();
                    }
                }, 1000);
            }
        });

    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(sarkilar.this, Bolum1.class);
        startActivity(i);
        finish();
        overridePendingTransition(R.anim.sagdansola,0);
    }

}
