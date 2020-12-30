package com.example.edukidsbitirmeprojesi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Base64;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.edukidsbitirmeprojesi.Sorular.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {
    static int SPLASH_TIME_OUT = 5000;
    MediaPlayer mp;
    TextView edutitle;
    Animation animation,ardisikanim;
    ImageView image;
    private TextView avuLogo;
    private TextView kidsLearning, from;
    private ImageView image1, image2, image3;
    private ConstraintLayout parent;
    SharedPreferences preferences;//preferences referansı
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        avuLogo = (TextView) findViewById(R.id.avulogo);
        kidsLearning = (TextView) findViewById(R.id.text_kids_learning);
        from = (TextView) findViewById(R.id.text_from_splash);
        image1 = (ImageView) findViewById(R.id.splash_image1);
        image2 = (ImageView) findViewById(R.id.splash_image2);
        image3 = (ImageView) findViewById(R.id.splash_image3);
        parent = (ConstraintLayout) findViewById(R.id.activity_splash);
        mp=new MediaPlayer();
        mp=MediaPlayer.create(this,R.raw.splashs1);
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mp.setLooping(false);
        mp.start();

        loadAnimation();
    }
    private void loadAnimation(){
        Typeface edo = Typeface.createFromAsset(getAssets(), "fonts/edosz.ttf");
        kidsLearning.setTypeface(edo);
        from.setTypeface(edo);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/poppins_extralight.ttf");
        avuLogo.setTypeface(typeface);
        Animation zoom0 = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
        Animation zoom1 = AnimationUtils.loadAnimation(this, R.anim.zoom1);
        Animation zoom2 = AnimationUtils.loadAnimation(this, R.anim.zoom2);
        Animation zoom3 = AnimationUtils.loadAnimation(this, R.anim.zoom3);
        Animation zoom4 = AnimationUtils.loadAnimation(this, R.anim.zoom4);
        Animation zoom5 = AnimationUtils.loadAnimation(this, R.anim.zoom5);
        zoom0.reset();
        kidsLearning.clearAnimation();
        kidsLearning.startAnimation(zoom0);

        zoom1.reset();
        image1.clearAnimation();
        image1.startAnimation(zoom1);

        zoom2.reset();
        image2.clearAnimation();
        image2.startAnimation(zoom2);

        zoom3.reset();
        image3.clearAnimation();
        image3.startAnimation(zoom3);

        zoom4.reset();
        from.clearAnimation();
        from.startAnimation(zoom4);

        zoom5.reset();
        avuLogo.clearAnimation();

        avuLogo.startAnimation(zoom5);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), Avatarlar.class));
                finish();
            }
        }, 4500);

    }

}
