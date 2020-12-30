package com.example.edukidsbitirmeprojesi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.example.edukidsbitirmeprojesi.Adapter.SpellingAdapter;
import com.example.edukidsbitirmeprojesi.Helper.CenterZoomLayoutManager;
import com.example.edukidsbitirmeprojesi.Object.AlphabetItem;

import java.util.ArrayList;
import java.util.List;

public class HecelemeBolumu extends AppCompatActivity {
    private RecyclerView spellingsRecycler;
    private List<AlphabetItem> alphabetItemList;
    private SpellingAdapter adapter;

    private CenterZoomLayoutManager centerZoomLayoutManager;

    private Button previous, play, next;
    private int counter = 0;


    private MediaPlayer mediaPlayer;
    private int[] sounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.hecelemebolumu);
        avatarAl();
        sounds = new int[]{R.raw.all, R.raw.bat, R.raw.cat, R.raw.dad, R.raw.dog,  R.raw.hello,
                R.raw.mom, R.raw.one, R.raw.papa, R.raw.pet, R.raw.rat, R.raw.sun, R.raw.toy, R.raw.yes};
        alphabetItemList = new ArrayList<>();
        List();
        adapter = new SpellingAdapter(this, alphabetItemList);
        spellingsRecycler = (RecyclerView) findViewById(R.id.recycler_spellings);
        centerZoomLayoutManager = new CenterZoomLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        spellingsRecycler.setLayoutManager(centerZoomLayoutManager);
        spellingsRecycler.setItemAnimator(new DefaultItemAnimator());
        spellingsRecycler.setAdapter(adapter);
        play = (Button) findViewById(R.id.play_spelling);
        counter = Integer.MAX_VALUE / 2;
        spellingsRecycler.scrollToPosition(counter);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(spellingsRecycler);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter = centerZoomLayoutManager.findLastCompletelyVisibleItemPosition();
                int pos = counter % alphabetItemList.size();
                if (mediaPlayer != null) {
                    mediaPlayer.release();
                }
                mediaPlayer = MediaPlayer.create(getApplicationContext(), sounds[pos]);
                mediaPlayer.start();
                adapter.notifyDataSetChanged();
            }
        });
    }
    private void List() {
        alphabetItemList.add(new AlphabetItem("All"));
        alphabetItemList.add(new AlphabetItem("Bat"));
        alphabetItemList.add(new AlphabetItem("Cat"));
        alphabetItemList.add(new AlphabetItem("Dad"));
        alphabetItemList.add(new AlphabetItem("Dog"));
        alphabetItemList.add(new AlphabetItem("Hello"));
        alphabetItemList.add(new AlphabetItem("Mom"));
        alphabetItemList.add(new AlphabetItem("One"));
        alphabetItemList.add(new AlphabetItem("Papa"));
        alphabetItemList.add(new AlphabetItem("Pet"));
        alphabetItemList.add(new AlphabetItem("Rat"));
        alphabetItemList.add(new AlphabetItem("Sun"));
        alphabetItemList.add(new AlphabetItem("Toy"));
        alphabetItemList.add(new AlphabetItem("Yes"));
    }
    @Override
    public void onBackPressed() {
        Intent r=new Intent(HecelemeBolumu.this,Bolum3.class);
        startActivity(r);
        finish();
        overridePendingTransition(R.anim.smalltobig,0);
    }
    private void avatarAl() {
        SharedPreferences preferences = getSharedPreferences("myprefs",MODE_PRIVATE);
        ImageView ivUserPhoto=(ImageView) findViewById(R.id.spellingAvatar);
        ImageView ivUserSavedPhoto=(ImageView) findViewById(R.id.spellingAvatar);
        String img_str=preferences.getString("userphoto", "");
        if (!img_str.equals("")){
            //decode string to image
            String base=img_str;
            byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);
            ivUserPhoto.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );
            ivUserSavedPhoto.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );
        }
    }
}
