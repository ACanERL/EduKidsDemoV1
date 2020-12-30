package com.example.edukidsbitirmeprojesi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SebzeOyunuSonuc extends AppCompatActivity {
  private  TextView enyuksekscore,toplamscore;
  private Button tekrar,geridon;
  private  ImageView avatar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veg_game_sonuc);
        enyuksekscore=(TextView)findViewById(R.id.enyuksek);
        toplamscore=(TextView)findViewById(R.id.toplamscore);
        geridon=(Button)findViewById(R.id.geridon);
        tekrar=(Button)findViewById(R.id.tekrar);
        avatar=(ImageView)findViewById(R.id.avatarr);
        avatarAl();
        int skor=getIntent().getIntExtra("skor",0);
        toplamscore.setText(String.valueOf(skor));
        SharedPreferences Sp=getSharedPreferences("sonuc", Context.MODE_PRIVATE);
        int enyuksekskor=Sp.getInt("enYuksek",0);
        if(skor>enyuksekskor){
            SharedPreferences.Editor editor=Sp.edit();
            editor.putInt("enYuksek",skor);
            editor.commit();
            enyuksekscore.setText(String.valueOf(skor));

        }else{
            enyuksekscore.setText(String.valueOf(enyuksekskor));
        }

        geridon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent geridon=new Intent(SebzeOyunuSonuc.this,Bolum2.class);
                startActivity(geridon);
                finish();
            }
        });

        tekrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tekrar=new Intent(SebzeOyunuSonuc.this,SebzelerOyun.class);
                startActivity(tekrar);
                finish();
            }
        });

    }
    @Override
    public void onBackPressed() {
        Intent r=new Intent(SebzeOyunuSonuc.this,SebzelerOyun.class);
        startActivity(r);
        finish();
    }
    private void avatarAl() {
        SharedPreferences preferences = getSharedPreferences("myprefs",MODE_PRIVATE);
        avatar=(ImageView) findViewById(R.id.avatarr);
        avatar=(ImageView) findViewById(R.id.avatarr);
        String img_str=preferences.getString("userphoto", "");
        if (!img_str.equals("")){
            //decode string to image
            String base=img_str;
            byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);
            avatar.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );
            avatar.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );
        }
    }
}
