package com.example.edukidsbitirmeprojesi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class SebzlerOyunEkrani extends AppCompatActivity {

    private ConstraintLayout cl;
    private TextView textViewSkor;
    private TextView textViewOyunaBasla;
    private ImageView anakarakter;
    private ImageView uzum;
    private ImageView maymun;
    private ImageView muz;

    //Pozisyonlar
    private int anakarakterX;
    private int anakarakterY;
    private int uzumX;
    private int uzumY;
    private int maymunX;
    private int maymunY;
    private int muzX;
    private int muzY;

    //Boyutlara
    private int ekranGenisligi;
    private int ekranYukseligi;
    private int anakarakterGenisligi;
    private int anakarakterYuksekligi;

    //Hızlar
    private int anakarakterHiz;
    private int uzumHiz;
    private int maymunHiz;
    private int muzHiz;

    //Kontroller
    private boolean dokunmaKontrol = false;
    private boolean baslangicKontrol = false;

    private int skor = 0 ;

    private Timer timer = new Timer();
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sebzler_oyun_ekrani);

        cl = findViewById(R.id.cl);
        textViewSkor = findViewById(R.id.textViewSkor);
        textViewOyunaBasla = findViewById(R.id.textViewOyunaBasla);
        anakarakter = findViewById(R.id.anakarakter);
        uzum = findViewById(R.id.uzum);
        maymun = findViewById(R.id.maymun);
        muz = findViewById(R.id.muz);

        //Cisimleri ekranın dışına çıkrma
        maymun.setX(-80);
        maymun.setY(-80);
        uzum.setX(-80);
        uzum.setY(-80);
        muz.setX(-80);
        muz.setY(-80);

        avatarAl();

        cl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (baslangicKontrol) {

                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                        Log.e("MotionEvent","Ekrana dokunuldu");
                        dokunmaKontrol = true;

                    }

                    if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                        Log.e("MotionEvent","Ekranı bıraktı");
                        dokunmaKontrol = false;
                    }

                }else{

                    baslangicKontrol = true ;

                    textViewOyunaBasla.setVisibility(View.INVISIBLE);

                    anakarakterX = (int) anakarakter.getX();
                    anakarakterY = (int) anakarakter.getY();

                    anakarakterGenisligi = anakarakter.getWidth();
                    anakarakterYuksekligi = anakarakter.getHeight();
                    ekranGenisligi = cl.getWidth();
                    ekranYukseligi = cl.getHeight();

                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    anakarakterHareketEttirme();
                                    cisimlerinHareketEttir();
                                    carpismaKontrol();
                                }
                            });
                        }
                    },0,20);



                }

                return true;
            }
        });
    }

    public void anakarakterHareketEttirme(){

        anakarakterHiz = Math.round(ekranYukseligi/60);//1280 / 60  = 20

        if(dokunmaKontrol){
            anakarakterY-=anakarakterHiz;
        }else{
            anakarakterY+=anakarakterHiz;
        }

        if(anakarakterY <= 0){
            anakarakterY = 0 ;
        }

        if(anakarakterY >= ekranYukseligi - anakarakterYuksekligi){
            anakarakterY = ekranYukseligi - anakarakterYuksekligi;
        }

        anakarakter.setY(anakarakterY);
    }

    public void cisimlerinHareketEttir(){

        uzumHiz = Math.round(ekranGenisligi/60);//760 / 60  = 13
        maymunHiz = Math.round(ekranGenisligi/60);//760 / 60  = 13
        muzHiz = Math.round(ekranGenisligi/30);//760 / 30  = 26

        maymunX -= maymunHiz;

        if (maymunX < 0 ){
            maymunX = ekranGenisligi + 20 ;
            maymunY = (int) Math.floor(Math.random() * ekranYukseligi);
        }

        maymun.setX(maymunX);
        maymun.setY(maymunY);


        uzumX -= uzumHiz;

        if (uzumX < 0 ){
            uzumX = ekranGenisligi + 20 ;
            uzumY = (int) Math.floor(Math.random() * ekranYukseligi);
        }

        uzum.setX(uzumX);
        uzum.setY(uzumY);

        muzX -= muzHiz;

        if (muzX < 0 ){
            muzX = ekranGenisligi + 20 ;
            muzY = (int) Math.floor(Math.random() * ekranYukseligi);
        }

        muz.setX(muzX);
        muz.setY(muzY);

    }

    public void carpismaKontrol(){

        int saridaireMerkezX = uzumX + uzum.getWidth()/2;
        int saridaireMerkezY = uzumY + uzum.getHeight()/2;

        if (0 <= saridaireMerkezX && saridaireMerkezX <= anakarakterGenisligi
                && anakarakterY <= saridaireMerkezY && saridaireMerkezY <= anakarakterY+anakarakterYuksekligi){

            skor+=20;
            uzumX = -10;

        }

        int kirmziucgenMerkezX = muzX + muz.getWidth()/2;
        int kirmziucgenMerkezY = muzY + muz.getHeight()/2;

        if (0 <= kirmziucgenMerkezX && kirmziucgenMerkezX <= anakarakterGenisligi
                && anakarakterY <= kirmziucgenMerkezY && kirmziucgenMerkezY <= anakarakterY+anakarakterYuksekligi){

            skor+=50;
            muzX = -10;

        }

        int siyahkareMerkezX = maymunX + maymun.getWidth()/2;
        int siyahkareMerkezY = maymunY + maymun.getHeight()/2;

        if (0 <= siyahkareMerkezX && siyahkareMerkezX <= anakarakterGenisligi
                && anakarakterY <= siyahkareMerkezY && siyahkareMerkezY <= anakarakterY+anakarakterYuksekligi){

            maymunX = -10;

            //Timer durdur.
            timer.cancel();
            timer=null;

            Intent intent = new Intent(SebzlerOyunEkrani.this, SebzeOyunuSonuc.class);
            intent.putExtra("skor",skor);
            startActivity(intent);


        }

        textViewSkor.setText(String.valueOf(skor));

    }


    @Override
    public void onBackPressed() {
        Intent r=new Intent(SebzlerOyunEkrani.this,SebzelerOyun.class);
        startActivity(r);
        finish();
    }



    private void avatarAl() {
        SharedPreferences preferences = getSharedPreferences("myprefs",MODE_PRIVATE);

        anakarakter=(ImageView) findViewById(R.id.anakarakter);
        anakarakter=(ImageView) findViewById(R.id.anakarakter);

        String img_str=preferences.getString("userphoto", "");
        if (!img_str.equals("")){
            //decode string to image
            String base=img_str;
            byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);
            anakarakter.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );
            anakarakter.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );
        }
    }
}
