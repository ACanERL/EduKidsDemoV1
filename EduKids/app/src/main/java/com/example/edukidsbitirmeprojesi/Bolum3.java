package com.example.edukidsbitirmeprojesi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.edukidsbitirmeprojesi.YeniBolumler.ComingSoon;

public class Bolum3 extends AppCompatActivity {
    Button sekil,araclar,objeler,kutu;
    ProgressBar progressBar,progressBargeri;
    private int progresBarDurumu=0;
    private Handler handler = new Handler();
    TextView sayac;
    ImageView avatarImage1,avatarImage2,avatarImage3,avatarImage4;
    Animation dondurme,zoom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_bolum3);

        sekil=(Button)findViewById(R.id.sekil);
        araclar=(Button)findViewById(R.id.araclar);
        objeler=(Button)findViewById(R.id.objeler);
        progressBar=(ProgressBar)findViewById(R.id.Chapter3progressBar);
        kutu=(Button)findViewById(R.id.kutu);
        progressBargeri=(ProgressBar)findViewById(R.id.progressBargeri);
        sayac=(TextView)findViewById(R.id.sayac);
        dondurme= AnimationUtils.loadAnimation(this,R.anim.anim_rotate);
        avatarAl();
        yildizAl();
        yildizAl2();
        yildizAl3();
        bolum3PuaniCek();
        sekil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sekil.startAnimation(dondurme);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i=new Intent(getApplicationContext(),Shapes.class);
                        startActivity(i);
                    }
                }, 1000);
            }
        });
        araclar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                araclar.startAnimation(dondurme);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent();
                        intent.setClass(Bolum3.this, Vehicles.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.smalltobig,0);
                    }
                }, 1000);
            }
        });
        objeler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objeler.startAnimation(dondurme);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent();
                        intent.setClass(Bolum3.this, Objects.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.smalltobig,0);

                    }
                }, 1000);
            }
        });
        if(progressBar.getProgress()==100){
            kutu.setVisibility(View.VISIBLE);

        }
        kutu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kutu.setBackgroundResource(R.drawable.acikkutu);
                Intent g=new Intent(Bolum3.this, ComingSoon.class);
                startActivity(g);
                finish();
                overridePendingTransition(R.anim.smalltobig,0);
            }
        });
    }
    private void avatarAl() {
        SharedPreferences preferences = getSharedPreferences("myprefs",MODE_PRIVATE);
        avatarImage1=(ImageView) findViewById(R.id.bolum3Avatar);
        String img_str=preferences.getString("userphoto", "");
        if (!img_str.equals("")){
            //decode string to image
            String base=img_str;
            byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);
            avatarImage1.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );
        }
    }
    public void yildizAl(){
        SharedPreferences preferences_yıldız = getSharedPreferences("yildiz1",MODE_PRIVATE);

        ImageView star1Img=(ImageView) findViewById(R.id.star1);
        ImageView star1Img2=(ImageView) findViewById(R.id.star1);
        String img_str=preferences_yıldız.getString("sekiller", "");
        if (!img_str.equals("")){
            //decode string to image
            String base=img_str;
            byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);
            star1Img.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );
            star1Img2.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );
        }
    }
    public void yildizAl2(){
        SharedPreferences preferences_yıldız = getSharedPreferences("yildiz2",MODE_PRIVATE);
        ImageView star2Img=(ImageView) findViewById(R.id.star2);
        String img_str=preferences_yıldız.getString("araclar", "");
        if (!img_str.equals("")){
            //decode string to image
            araclar.setBackgroundResource(R.drawable.t2);
            String base=img_str;
            byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);
            star2Img.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );
        }
    }
    public void yildizAl3(){
        SharedPreferences preferences_yıldız = getSharedPreferences("yildiz3",MODE_PRIVATE);
        ImageView star3Img=(ImageView) findViewById(R.id.star3);


        String img_str=preferences_yıldız.getString("objecler", "");
        if (!img_str.equals("")){
            //decode string to image
            objeler.setBackgroundResource(R.drawable.t3);
            String base=img_str;
            byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);
            star3Img.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );
        }
    }
    public void bolum3PuaniCek(){
        int puanS;
        SharedPreferences sharedPreferencesC=getSharedPreferences("shapePuan",MODE_PRIVATE);
        puanS=sharedPreferencesC.getInt("shapeveri",MODE_PRIVATE);
        if(puanS==33){
            araclar.setVisibility(View.VISIBLE);
            avatarImage1.setVisibility(View.INVISIBLE);

            SharedPreferences preferences = getSharedPreferences("myprefs",MODE_PRIVATE);
            avatarImage2=(ImageView)findViewById(R.id.anaEkranAvatar_2);
            String img_str=preferences.getString("userphoto", "");
            if (!img_str.equals("")){
                String base=img_str;
                byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);
                avatarImage2.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );

            }
        }

        int puanA;
        SharedPreferences sharedPreferencesF=getSharedPreferences("vehiclePuan",MODE_PRIVATE);
        puanA=sharedPreferencesF.getInt("veriVehicle",MODE_PRIVATE);
        if(puanA==33){
            objeler.setVisibility(View.VISIBLE);
            avatarImage2.setVisibility(View.INVISIBLE);
            SharedPreferences preferences = getSharedPreferences("myprefs",MODE_PRIVATE);
            avatarImage3=(ImageView)findViewById(R.id.anaEkranAvatar_3);
            String img_str=preferences.getString("userphoto", "");
            if (!img_str.equals("")){
                String base=img_str;
                byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);
                avatarImage3.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );

            }
        }

        int puanO;
        SharedPreferences sharedPreferencesV=getSharedPreferences("objePuan",MODE_PRIVATE);
        puanO=sharedPreferencesV.getInt("veriObje",MODE_PRIVATE);
        progressBar.setProgress(puanS+puanA+puanO+1);

        if(progressBar.getProgress()==100){
            avatarImage3.setVisibility(View.INVISIBLE);
            SharedPreferences preferences = getSharedPreferences("myprefs",MODE_PRIVATE);
            avatarImage4=(ImageView)findViewById(R.id.anaEkranAvatar_4);
            String img_str=preferences.getString("userphoto", "");
            if (!img_str.equals("")){
                String base=img_str;
                byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);
                avatarImage4.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );

            }
        }

    }
    @Override
    public void onBackPressed() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progresBarDurumu<=100){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                          progressBargeri.setProgress(progresBarDurumu);
                            sayac.setText("Loading"+""+progresBarDurumu+"%");
                            if(progresBarDurumu==100){
                                Intent i = new Intent(Bolum3.this, Bolum2.class);
                                startActivity(i);
                                finish();
                                overridePendingTransition(R.anim.sagdansola,0);
                            }
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    progresBarDurumu+=25;

                }

            }
        }).start();
    }
}
