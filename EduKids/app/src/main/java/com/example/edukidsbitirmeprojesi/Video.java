package com.example.edukidsbitirmeprojesi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.io.ByteArrayOutputStream;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class Video extends AppCompatActivity {
    Button kalp1,kalp2,kalp3;
    int zaman=60;
    TextView gerisayim;
    Button geridon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video);
        gerisayim=(TextView)findViewById(R.id.gerisayim);
        geridon=(Button)findViewById(R.id.grdon);
        AvatarAl();
        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);
        new CountDownTimer(120000, 1000) {
            public void onTick(long millisUntilFinished) {
                gerisayim.setText(""+String.format("%d \n" +
                                "Minute, %d Second",
                        TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                        toMinutes(millisUntilFinished))));
            }
            public void onFinish() {
                showDialog(Video.this);
            }
        }.start();
        kalp1=(Button)findViewById(R.id.kalp1);
        kalp3=(Button)findViewById(R.id.kalp3);
        kalp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kalp1.setBackgroundResource(R.drawable.kalp1);
            }
        });
        kalp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kalp3.setBackgroundResource(R.drawable.kalp1);
            }
        });
        geridon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gr=new Intent(Video.this,Bolum3.class);
                startActivity(gr);
                finish();
            }
        });
    }
    private void AvatarAl() {
        SharedPreferences preferences = getSharedPreferences("myprefs",MODE_PRIVATE);
        ImageView avatarImg=(ImageView) findViewById(R.id.imageViewVideo);
        String img_str=preferences.getString("userphoto", "");
        if (!img_str.equals("")){
            //decode string to image
            String base=img_str;
            byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);
            avatarImg.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );
        }
    }
    public void showDialog(final Activity activity){
        final Dialog dialog = new Dialog(activity, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.alertdialog);
        Button menuBtn=(Button)dialog.findViewById(R.id.menu);
        Button okey = (Button) dialog.findViewById(R.id.d1_btn1);
        okey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                Intent draw=new Intent(Video.this, Bolum3.class);
                startActivity(draw);
                overridePendingTransition(R.anim.smalltobig,0);
            }
        });
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent quiz1=new Intent(Video.this,Vehicles.class);
                startActivity(quiz1);
                overridePendingTransition(R.anim.smalltobig, 0);
            }
        });
        dialog.show();
    }
    @Override
    public void onBackPressed() {
        Intent r=new Intent(Video.this,Bolum3.class);
        startActivity(r);
        finish();
        overridePendingTransition(R.anim.smalltobig,0);
    }
}
