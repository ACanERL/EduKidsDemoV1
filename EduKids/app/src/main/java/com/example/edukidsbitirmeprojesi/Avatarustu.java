package com.example.edukidsbitirmeprojesi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class Avatarustu extends AppCompatActivity {
    ImageView img1,img2,img3,img4;
    CardView card1,card2,card3,card4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatarustu);

        img1=(ImageView)findViewById(R.id.image1);
        img2=(ImageView)findViewById(R.id.image2);
        img3=(ImageView)findViewById(R.id.image3);
        img4=(ImageView)findViewById(R.id.image4);

        //cardviewlar
        card1=(CardView)findViewById(R.id.cardView);
        card2=(CardView)findViewById(R.id.cardView2);
        card3=(CardView)findViewById(R.id.cardView3);
        card4=(CardView)findViewById(R.id.cardView4);



        firstTime();
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setKarakter1();
                card1.setCardBackgroundColor(Color.GREEN);
                final Handler handler = new Handler();
                // showDialog(VegGame.this);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent x=new Intent(Avatarustu.this,Bolum3.class);
                        startActivity(x);
                        finish();
                    }
                }, 2000);
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setKarakter2();
                card2.setCardBackgroundColor(Color.BLUE);
                final Handler handler = new Handler();
                // showDialog(VegGame.this);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent x=new Intent(Avatarustu.this,Bolum3.class);
                        startActivity(x);
                        finish();
                    }
                }, 2000);

            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setKarakter3();
                card3.setCardBackgroundColor(Color.RED);
                final Handler handler = new Handler();
                // showDialog(VegGame.this);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent x=new Intent(Avatarustu.this,Bolum3.class);
                        startActivity(x);
                        finish();
                    }
                }, 2000);

            }
        });

        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setKarakter4();
                card4.setCardBackgroundColor(Color.RED);
                final Handler handler = new Handler();
                // showDialog(VegGame.this);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        card4.setCardBackgroundColor(Color.GRAY);
                        Intent x=new Intent(Avatarustu.this,Bolum3.class);
                        startActivity(x);
                        finish();
                    }
                }, 2000);

            }
        });
    }

    public void setKarakter1(){
        //resmi string çeviriyoruz
        img1.buildDrawingCache();
        Bitmap bitmap = img1.getDrawingCache();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream);
        byte[] image=stream.toByteArray();

        String img_str = Base64.encodeToString(image, 0);
        String base=img_str;
        byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);

        img1.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes,0, imageAsBytes.length) );
        SharedPreferences preferences = getSharedPreferences("myprefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("userphoto",img_str);
        editor.commit();
    }

    public void setKarakter2(){
        //resmi string çeviriyoruz
        img2.buildDrawingCache();
        Bitmap bitmap = img2.getDrawingCache();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream);
        byte[] image=stream.toByteArray();

        String img_str = Base64.encodeToString(image, 0);
        String base=img_str;
        byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);

        img2.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes,0, imageAsBytes.length) );
        SharedPreferences preferences = getSharedPreferences("myprefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("userphoto",img_str);
        editor.commit();
    }

    public void setKarakter3(){
        //resmi string çeviriyoruz
        img3.buildDrawingCache();
        Bitmap bitmap = img3.getDrawingCache();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream);
        byte[] image=stream.toByteArray();

        String img_str = Base64.encodeToString(image, 0);
        String base=img_str;
        byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);

        img3.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes,0, imageAsBytes.length) );
        SharedPreferences preferences = getSharedPreferences("myprefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("userphoto",img_str);
        editor.commit();
    }
    public void setKarakter4(){
        //resmi  çeviriyoruz
        img4.buildDrawingCache();
        Bitmap bitmap = img4.getDrawingCache();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream);
        byte[] image=stream.toByteArray();

        String img_str = Base64.encodeToString(image, 0);
        String base=img_str;
        byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);

        img4.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes,0, imageAsBytes.length) );
        SharedPreferences preferences = getSharedPreferences("myprefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("userphoto",img_str);
        editor.commit();
    }

    public  void  firstTime() {
        SharedPreferences sharedTime = getSharedPreferences("First2", Context.MODE_PRIVATE);
        if (sharedTime.getBoolean("firstTime2", true)) {
            sharedTime.edit().putBoolean("firstTime2", false).apply();
        } else {
            Intent r=new Intent(Avatarustu.this,Bolum3.class);
            startActivity(r);
            finish();

        }
    }

    @Override
    public void onBackPressed() {

    }
}
