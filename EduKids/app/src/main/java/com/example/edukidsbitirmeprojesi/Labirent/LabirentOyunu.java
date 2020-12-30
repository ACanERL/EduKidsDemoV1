package com.example.edukidsbitirmeprojesi.Labirent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.edukidsbitirmeprojesi.Bolum3;
import com.example.edukidsbitirmeprojesi.ObjectVideo;
import com.example.edukidsbitirmeprojesi.R;

import java.util.concurrent.TimeUnit;

public class LabirentOyunu extends AppCompatActivity {
ImageView labAvatar;
    TextView gerisayim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.labirentoyunu);
        gerisayim=(TextView)findViewById(R.id.gerisayim2);
        labAvatar=(ImageView)findViewById(R.id.labAvatar);
        avatarAl();



        new CountDownTimer(150000, 1000) {
            public void onTick(long millisUntilFinished) {
                gerisayim.setText(""+String.format("%d \n" +
                                "Minute, %d Second",
                        TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                        toMinutes(millisUntilFinished))));

            }
            public void onFinish() {
                showDialog(LabirentOyunu.this);
            }
        }.start();

    }
    private void avatarAl() {
        SharedPreferences preferences = getSharedPreferences("myprefs",MODE_PRIVATE);
        ImageView ivUserPhoto=(ImageView) findViewById(R.id.labAvatar);
        ImageView ivUserSavedPhoto=(ImageView) findViewById(R.id.labAvatar);
        String img_str=preferences.getString("userphoto", "");
        if (!img_str.equals("")){
            String base=img_str;
            byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);
            ivUserPhoto.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );
            ivUserSavedPhoto.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );
        }
    }
    @Override
    public void onBackPressed() {
        Intent geri=new Intent(LabirentOyunu.this, Bolum3.class);
        startActivity(geri);
        overridePendingTransition(R.anim.smalltobig,0);
        finish();
    }

    public void showDialog(final Activity activity){
        final Dialog dialog = new Dialog(activity, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.vegalert);
        Button menuBtn=(Button)dialog.findViewById(R.id.menu);
        Button okey = (Button) dialog.findViewById(R.id.d1_btn1);
        okey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent draw=new Intent(LabirentOyunu.this, Bolum3.class);
                startActivity(draw);
                overridePendingTransition(R.anim.smalltobig,0);
            }
        });
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent quiz1=new Intent(LabirentOyunu.this,Object.class);
                startActivity(quiz1);
                overridePendingTransition(R.anim.smalltobig, 0);
            }
        });
        dialog.show();
    }
}
