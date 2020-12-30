package com.example.edukidsbitirmeprojesi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

public class Bolum2 extends AppCompatActivity {
    Button veg;
    Button fruit;
    Button colors;
    ImageView log_out, avatarImg,avatarImage2,avatarImage3,avatarImage4;
    Button box;
    ProgressBar progressBar2,progressBargeri2;
    private int progresBarDurumu=0;
    private Handler handler = new Handler();
    TextView sayac2;
    Animation dondurme,zoom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_bolum2);
        veg=(Button)findViewById(R.id.veg);
        fruit=(Button)findViewById(R.id.fruits);
        colors=(Button)findViewById(R.id.colors);
        box=(Button)findViewById(R.id.box) ;
        progressBargeri2=(ProgressBar)findViewById(R.id.progressBargeri2);
        sayac2=(TextView)findViewById(R.id.sayac2);
        log_out=(ImageView)findViewById(R.id.log_out);
        progressBar2=(ProgressBar)findViewById(R.id.Chapter2progressBar);
        dondurme= AnimationUtils.loadAnimation(this,R.anim.anim_rotate);
        AvatarAl();
        colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colors.startAnimation(dondurme);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent();
                        intent.setClass(Bolum2.this, Colors.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.smalltobig,0);
                    }
                }, 1000);

            }
        });
        fruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fruit.startAnimation(dondurme);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fruit.setBackgroundResource(R.drawable.t2);
                        Intent intent = new Intent();
                        intent.setClass(Bolum2.this, Fruit.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.smalltobig,0);
                    }
                }, 1000);
            }
        });
        veg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                veg.startAnimation(dondurme);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        veg.setBackgroundResource(R.drawable.t3);
                        Intent intent = new Intent();
                        intent.setClass(Bolum2.this, Veg.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.smalltobig,0);
                    }
                }, 1000);

            }
        });
        yildizAl();
        yildizAl2();
        yildizAl3();
       bolum2PuaniCek();

        if(progressBar2.getProgress()==100){
            box.setVisibility(View.VISIBLE);
        }
        box.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        box.startAnimation(dondurme);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                box.setBackgroundResource(R.drawable.twf_back);
                Intent intent = new Intent();
                intent.setClass(Bolum2.this, Avatarustu.class);
                startActivity(intent);
                overridePendingTransition(R.anim.smalltobig,0);
            }
        }, 1000);
    }
});
        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(Bolum2.this, v);
                popup.getMenuInflater()
                        .inflate(R.menu.mymenu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        String a = item.getTitle().toString().trim();
                        if(a.equals("Language")){

                            //Intent intent =new Intent(getApplicationContext(),Language.class);
                         //   intent.addCategory(Intent.CATEGORY_HOME);
                            // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
                          //  startActivity(intent);
                            //finish();
                            // System.exit(0);
                        }
                        if(a.equals("Exit"))
                        {
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
                            startActivity(intent);
                            finish();
                            System.exit(0);

                        }
                        return true;
                    }
                });

                popup.show();
            }
        });

    }
    private Boolean exit = false;
    @Override
    public void onBackPressed() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progresBarDurumu<=100){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBargeri2.setProgress(progresBarDurumu);
                            sayac2.setText("Loading"+""+progresBarDurumu+"%");
                            if(progresBarDurumu==100){
                                Intent i = new Intent(Bolum2.this, sarkilar.class);
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
    private void AvatarAl() {
        SharedPreferences preferences = getSharedPreferences("myprefs",MODE_PRIVATE);
         avatarImg=(ImageView) findViewById(R.id.anaEkranAvatar_1);
        String img_str=preferences.getString("userphoto", "");
        if (!img_str.equals("")){
            String base=img_str;
            byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);
            avatarImg.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );

        }
    }
public void yildizAl(){
    SharedPreferences preferences_yıldız = getSharedPreferences("colorstar",MODE_PRIVATE);
    ImageView star1=(ImageView) findViewById(R.id.star1);
    ImageView starImg=(ImageView) findViewById(R.id.star1);
    String img_str=preferences_yıldız.getString("colorStar", "");
    if (!img_str.equals("")){
        //decode string to image
        String base=img_str;
        byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);
        star1.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );
        starImg.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );
    }
}
    public void yildizAl2(){
        SharedPreferences preferences_yıldız = getSharedPreferences("colorstar2",MODE_PRIVATE);
        ImageView star2Img=(ImageView) findViewById(R.id.star2);
        ImageView star2Img2=(ImageView) findViewById(R.id.star2);
        String img_str=preferences_yıldız.getString("colorStar2", "");
        if (!img_str.equals("")){
            //decode string to image
            fruit.setBackgroundResource(R.drawable.t2);
            String base=img_str;
            byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);
            star2Img.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );
            star2Img2.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );
        }
    }
    public void yildizAl3(){
        SharedPreferences preferences_yıldız = getSharedPreferences("vgstar3",MODE_PRIVATE);
        ImageView star3Img=(ImageView) findViewById(R.id.star3);
        ImageView star3Img2=(ImageView) findViewById(R.id.star3);
        String img_str=preferences_yıldız.getString("vgStar3", "");
        if (!img_str.equals("")){
            //decode string to image
            veg.setBackgroundResource(R.drawable.t3);
            String base=img_str;
            byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);
            star3Img.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );
            star3Img2.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );
        }
    }
    public void bolum2PuaniCek(){

        int puanC;
        SharedPreferences sharedPreferencesC=getSharedPreferences("colorPuan",MODE_PRIVATE);
        puanC=sharedPreferencesC.getInt("veriColor",MODE_PRIVATE);
        if(puanC==33){
            fruit.setVisibility(View.VISIBLE);
            avatarImg.setVisibility(View.INVISIBLE);
            SharedPreferences preferences = getSharedPreferences("myprefs",MODE_PRIVATE);
             avatarImage2=(ImageView)findViewById(R.id.anaEkranAvatar_2);
            String img_str=preferences.getString("userphoto", "");
            if (!img_str.equals("")){
                String base=img_str;
                byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);
                avatarImage2.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );

            }
        }
       int puanF;
       SharedPreferences sharedPreferencesF=getSharedPreferences("fruitPuan",MODE_PRIVATE);
       puanF=sharedPreferencesF.getInt("veriFruit",MODE_PRIVATE);
       if(puanF==33){
           veg.setVisibility(View.VISIBLE);
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
      int puanV;
       SharedPreferences sharedPreferencesV=getSharedPreferences("vegPuan",MODE_PRIVATE);
        puanV=sharedPreferencesV.getInt("veriVeg",MODE_PRIVATE);
        progressBar2.setProgress(puanC+puanF+puanV+1);

        if(progressBar2.getProgress()==100){
            avatarImage3.setVisibility(View.INVISIBLE);
            avatarImage4=(ImageView)findViewById(R.id.anaEkranAvatar_4);
            SharedPreferences preferences = getSharedPreferences("myprefs",MODE_PRIVATE);
            String img_str=preferences.getString("userphoto", "");
            if (!img_str.equals("")){
                //decode string to image
                String base=img_str;
                byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);
                avatarImage4.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );
            }
        }

    }

}
