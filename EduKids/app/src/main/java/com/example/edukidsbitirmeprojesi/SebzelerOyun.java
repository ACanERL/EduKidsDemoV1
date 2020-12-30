package com.example.edukidsbitirmeprojesi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SebzelerOyun extends AppCompatActivity {
    ImageView img1,img2,img3,imgAvatar;
    Button startButton;
    TextView textView;
    Animation anim1;
    ProgressBar loadingProgress;
    private int progresBarDurumu=0;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sebzeler_oyun);
        img1=(ImageView)findViewById(R.id.muzz);
        img2=(ImageView)findViewById(R.id.uzum);
        img3=(ImageView)findViewById(R.id.monkey);
        imgAvatar=(ImageView)findViewById(R.id.anakarakter);
        startButton=(Button)findViewById(R.id.button2);
        avatarAl();
        anim1= AnimationUtils.loadAnimation(this,R.anim.downup);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startButton.startAnimation(anim1);
                Intent geçiş=new Intent(SebzelerOyun.this,SebzlerOyunEkrani.class);
                startActivity(geçiş);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent r=new Intent(SebzelerOyun.this,Bolum2.class);
        startActivity(r);
        finish();
    }
    private void avatarAl() {
        SharedPreferences preferences = getSharedPreferences("myprefs",MODE_PRIVATE);
        imgAvatar=(ImageView) findViewById(R.id.anakarakter);
        imgAvatar=(ImageView) findViewById(R.id.anakarakter);
        String img_str=preferences.getString("userphoto", "");
        if (!img_str.equals("")){
            //decode string to image
            String base=img_str;
            byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);
            imgAvatar.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );
            imgAvatar.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );
        }
    }
}
