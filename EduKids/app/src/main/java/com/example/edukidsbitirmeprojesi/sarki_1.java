package com.example.edukidsbitirmeprojesi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class sarki_1 extends AppCompatActivity {
    ImageView img1,img2,img3,img4,home;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.sarki_1);

        img1=(ImageView)findViewById(R.id.img1);
        img2=(ImageView)findViewById(R.id.img2);
        img3=(ImageView)findViewById(R.id.img3);
        img4=(ImageView)findViewById(R.id.img4);

        RotateAnimation anim = new RotateAnimation(0f,350f,15f,15f);
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(Animation.INFINITE);
        anim.setDuration(1000);
        img1.startAnimation(anim);

        RotateAnimation anim2 = new RotateAnimation(0f,350f,15f,15f);
        anim2.setInterpolator(new LinearInterpolator());
        anim2.setRepeatCount(Animation.INFINITE);
        anim2.setDuration(1000);
        img2.startAnimation(anim2);

        RotateAnimation anim3 = new RotateAnimation(0f,350f,15f,15f);
        anim3.setInterpolator(new LinearInterpolator());
        anim3.setRepeatCount(Animation.INFINITE);
        anim3.setDuration(1000);
        img3.startAnimation(anim3);

        RotateAnimation anim4 = new RotateAnimation(0f,350f,15f,15f);
        anim4.setInterpolator(new LinearInterpolator());
        anim4.setRepeatCount(Animation.INFINITE);
        anim4.setDuration(1000);
        img4.startAnimation(anim4);

        mp=new MediaPlayer();
        mp=MediaPlayer.create(this,R.raw.ttls);
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mp.setLooping(true);
        mp.start();

        home = (ImageView) findViewById(R.id.home);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(sarki_1.this, sarkilar.class);
                startActivity(intent);
                stopMusic(v);
            }
        });
    }
    public void stopMusic(View view)
    {
        mp.stop();
        mp.release();
    }
    @Override
    public void onBackPressed() {
        finish();
        System.exit(0);
    }

}
