package com.example.edukidsbitirmeprojesi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class sarki_2 extends AppCompatActivity {
    MediaPlayer mp;
    ImageView home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.sarki_2);

        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube1);
        getLifecycle().addObserver(youTubePlayerView);
     //   mp=new MediaPlayer();
     //   mp=MediaPlayer.create(this,R.raw.bbbs);
      //  mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
     //   mp.setLooping(true);
     //   mp.start();

        home = (ImageView) findViewById(R.id.back1);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(sarki_2.this, sarkilar.class);
                startActivity(intent);
               // stopMusic(v);
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
