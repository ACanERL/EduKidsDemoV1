package com.example.edukidsbitirmeprojesi.Sorular;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.edukidsbitirmeprojesi.Bolum2;
import com.example.edukidsbitirmeprojesi.R;

public class Cevap1 extends AppCompatActivity {
    TextView textScreen, textQuestion, devam, textBtn;
    ImageView bigboss;
    Animation smalltobig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cevap1);
        smalltobig = AnimationUtils.loadAnimation(this, R.anim.smalltobig);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/FredokaOneRegular.ttf");
        textQuestion = (TextView) findViewById(R.id.textQuestion);
        textScreen = (TextView) findViewById(R.id.textScreen);
        devam = (TextView) findViewById(R.id.devam);
        textBtn = (TextView) findViewById(R.id.textBtn);
        bigboss = (ImageView) findViewById(R.id.bigboss);
        bigboss.startAnimation(smalltobig);
        textQuestion.setTypeface(typeface);
        textScreen.setTypeface(typeface);
        devam.setTypeface(typeface);
        textBtn.setTypeface(typeface);

        devam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ileri=new Intent(Cevap1.this,Main2Activity.class);
                startActivity(ileri);
            }
        });

        textBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent g=new Intent(Cevap1.this, Bolum2.class);
                startActivity(g);
            }
        });
    }
}
