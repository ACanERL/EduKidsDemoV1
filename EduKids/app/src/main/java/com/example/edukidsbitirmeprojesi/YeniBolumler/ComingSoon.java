package com.example.edukidsbitirmeprojesi.YeniBolumler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.edukidsbitirmeprojesi.Bolum3;
import com.example.edukidsbitirmeprojesi.R;

public class ComingSoon extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coming_soon);



    }


    @Override
    public void onBackPressed() {
        Intent r=new Intent(ComingSoon.this, Bolum3.class);
        startActivity(r);
        finish();
        overridePendingTransition(R.anim.downup,0);
    }
}
