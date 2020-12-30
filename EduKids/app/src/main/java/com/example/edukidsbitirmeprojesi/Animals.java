package com.example.edukidsbitirmeprojesi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.edukidsbitirmeprojesi.Adapter.ImageAdapter;
import com.example.edukidsbitirmeprojesi.Helper.CenterZoomLayoutManager;
import com.example.edukidsbitirmeprojesi.Object.ImageItem;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Animals extends AppCompatActivity {
    private RecyclerView animalRecycler;
    private List<ImageItem> imageItemList;
    private ImageAdapter adapter;
    ImageView animalstar;
    private CenterZoomLayoutManager centerZoomLayoutManager;
    ProgressBar progressBar;
    private Button play;
    private int counter = 0;
    private MediaPlayer mediaPlayer;
    private int[] sounds;

    TextView sayac2,durum;
    int sayac=0;
    SharedPreferences sharedPre;

    @Override
    protected void onPause() {
        SharedPreferences.Editor editor=sharedPre.edit();
        editor.putInt("saymaanahtari2",sayac);
        editor.commit();
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_animals_new);

        progressBar=(ProgressBar)findViewById(R.id.progressBarAnimals5);
        animalstar=(ImageView)findViewById(R.id.animalstar3);
        sounds = new int[]{R.raw.alligator, R.raw.bear, R.raw.elephant, R.raw.lion, R.raw.monkey, R.raw.panda, R.raw.rabbit,
                R.raw.snake, R.raw.squirrel, R.raw.tiger, R.raw.zebra};

        avatarAl();
        imageItemList = new ArrayList<>();
        ListHayvan();
        //initListTR();
        adapter = new ImageAdapter(this, imageItemList);

        animalRecycler = (RecyclerView) findViewById(R.id.recycler_animals);
        centerZoomLayoutManager = new CenterZoomLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        animalRecycler.setLayoutManager(centerZoomLayoutManager);
        animalRecycler.setItemAnimator(new DefaultItemAnimator());
        animalRecycler.setAdapter(adapter);

        play = (Button) findViewById(R.id.play_animals);

        durum=(TextView)findViewById(R.id.sayma);
        sharedPre= getSharedPreferences("saymaVeri2",MODE_PRIVATE);
        sayac=sharedPre.getInt("saymaanahtari2",0);
        durum.setText(sayac+"/10");


        counter = Integer.MAX_VALUE / 2;

        animalRecycler.scrollToPosition(counter);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(animalRecycler);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setProgress(progressBar.getProgress()+10);
                counter = centerZoomLayoutManager.findLastCompletelyVisibleItemPosition();
                int pos = counter % imageItemList.size();
                if (mediaPlayer != null) {
                    mediaPlayer.release();
                }
                mediaPlayer = MediaPlayer.create(getApplicationContext(), sounds[pos]);
                mediaPlayer.start();

                if(progressBar.getProgress()==100){
                    sayac++;
                    play.setEnabled(false);
                    showDialog(Animals.this);
                    progressBar.setProgress(0);
                }
            }
        });

    }
    private void ListHayvan() {
        imageItemList.add(new ImageItem("Alligator", R.drawable.alligator));
        imageItemList.add(new ImageItem("Bear", R.drawable.bear));
        imageItemList.add(new ImageItem("Elephant", R.drawable.elephant));
        imageItemList.add(new ImageItem("Lion", R.drawable.aslan));
        imageItemList.add(new ImageItem("Monkey", R.drawable.monkey));
        imageItemList.add(new ImageItem("Panda", R.drawable.panda));
        imageItemList.add(new ImageItem("Rabbit", R.drawable.tavsan));
        imageItemList.add(new ImageItem("Snake", R.drawable.snake));
        imageItemList.add(new ImageItem("Squirrel", R.drawable.squirrel));
        imageItemList.add(new ImageItem("Tiger", R.drawable.tiger));
        imageItemList.add(new ImageItem("Zebra", R.drawable.zebra));
    }
    private void avatarAl() {
        SharedPreferences preferences = getSharedPreferences("myprefs",MODE_PRIVATE);
        ImageView avatarImage=(ImageView) findViewById(R.id.imageViewAv5);
        ImageView avatarImage1=(ImageView) findViewById(R.id.imageViewAv5);
        String img_str=preferences.getString("userphoto", "");
        if (!img_str.equals("")){
            //decode string to image
            String base=img_str;
            byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);
            avatarImage.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );
            avatarImage1.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );
        }
    }
    public void bolumPuani(){
        int puan=33;
        SharedPreferences bP=getSharedPreferences("bP2",MODE_PRIVATE);
        final SharedPreferences.Editor editor=bP.edit();
        editor.putInt("veri2",puan);
        editor.apply();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
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
                progressBar.setProgress(0);
                dialog.dismiss();
                bolumPuani();

                animalstar.buildDrawingCache();
                Bitmap bitmap = animalstar.getDrawingCache();
                ByteArrayOutputStream streamstar=new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, streamstar);
                byte[] image=streamstar.toByteArray();
                String img_str = Base64.encodeToString(image, 0);
                //decode string to image
                String base=img_str;
                byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);

                animalstar.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes,0, imageAsBytes.length) );
                //save in sharedpreferences
                SharedPreferences preferences = getSharedPreferences("mystar3",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("starphoto3",img_str);
                editor.commit();

                Intent quiz=new Intent(Animals.this, PuzzleAnimals.class);
                startActivity(quiz);
                overridePendingTransition(R.anim.smalltobig,0);
            }
        });
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setProgress(0);
                progressBar.setProgress(0);
                dialog.dismiss();
                Intent quiz1=new Intent(Animals.this, Animals.class);
                startActivity(quiz1);
                overridePendingTransition(R.anim.smalltobig, 0);
            }
        });
        dialog.show();
    }
}
