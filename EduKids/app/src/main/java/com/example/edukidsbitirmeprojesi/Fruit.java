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
import com.example.edukidsbitirmeprojesi.Sorular.MainActivity;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Fruit extends AppCompatActivity {
    private RecyclerView fruitsRecycler;
    private List<ImageItem> imageItemList;
    private ImageAdapter adapter;
    private CenterZoomLayoutManager centerZoomLayoutManager;
    private Button previous, play, next;
    private int counter = 0;
    private MediaPlayer mediaPlayer;
    private int[] sounds;
    ProgressBar progressBar;
    ImageView fruitstar;
    TextView sayac2,durum;
    int sayac=0;
    SharedPreferences sharedPre;
    @Override
    protected void onPause() {
        SharedPreferences.Editor editor=sharedPre.edit();
        editor.putInt("saymaanahtari4",sayac);
        editor.commit();

        super.onPause();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_fruit_new);

        avatarAl();
        fruitstar = (ImageView)findViewById(R.id.fruitstar);
        progressBar=(ProgressBar)findViewById(R.id.progressBarFruit7);
        sounds = new int[]{R.raw.banana, R.raw.cherry, R.raw.coconut, R.raw.fig, R.raw.grape, R.raw.green_apple, R.raw.kiwi,
                R.raw.papaya, R.raw.peach, R.raw.pineapple, R.raw.pomegranate, R.raw.strawberry, R.raw.watermelon};
        durum=(TextView)findViewById(R.id.sayma);
        sharedPre=getSharedPreferences("saymaVeri4",MODE_PRIVATE);
        sayac=sharedPre.getInt("saymaanahtari4",0);
        durum.setText(sayac+"/10");
        imageItemList = new ArrayList<>();
        initList();
        adapter = new ImageAdapter(this, imageItemList);
        fruitsRecycler = (RecyclerView) findViewById(R.id.recycler_fruit);
        centerZoomLayoutManager = new CenterZoomLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        fruitsRecycler.setLayoutManager(centerZoomLayoutManager);
        fruitsRecycler.setItemAnimator(new DefaultItemAnimator());
        fruitsRecycler.setAdapter(adapter);
        play = (Button) findViewById(R.id.play_fruit);
        counter = Integer.MAX_VALUE / 2;
        fruitsRecycler.scrollToPosition(counter);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(fruitsRecycler);

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
                    showDialog(Fruit.this);
                    progressBar.setProgress(0);
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    private void initList() {
        imageItemList.add(new ImageItem("Banana", R.drawable.banana));
        imageItemList.add(new ImageItem("Cherry", R.drawable.cherry));
        imageItemList.add(new ImageItem("Coconut", R.drawable.coconut));
        imageItemList.add(new ImageItem("Fig", R.drawable.dragon));
        imageItemList.add(new ImageItem("Grapes", R.drawable.grape));
        imageItemList.add(new ImageItem("Green Apple", R.drawable.greenapple));
        imageItemList.add(new ImageItem("Kiwi", R.drawable.kiwi));
        imageItemList.add(new ImageItem("Papaya", R.drawable.papaya));
        imageItemList.add(new ImageItem("Peach", R.drawable.peach));
        imageItemList.add(new ImageItem("Pineapple", R.drawable.pine));
        imageItemList.add(new ImageItem("Pomegranate", R.drawable.pomegranate));
        imageItemList.add(new ImageItem("Strawberry", R.drawable.strawberry));
        imageItemList.add(new ImageItem("Watermelon", R.drawable.watermelon));
    }
    private void avatarAl() {
        SharedPreferences preferences = getSharedPreferences("myprefs",MODE_PRIVATE);
        ImageView avatarImg=(ImageView) findViewById(R.id.imageViewAv7);
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
                progressBar.setProgress(0);
                bolumPuani();
                fruitstar.buildDrawingCache();
                Bitmap bitmap = fruitstar.getDrawingCache();
                ByteArrayOutputStream streamstar=new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, streamstar);
                byte[] image=streamstar.toByteArray();
                String img_str = Base64.encodeToString(image, 0);
                //decode string to image
                String base=img_str;
                byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);
                fruitstar.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes,0, imageAsBytes.length) );
                SharedPreferences preferences = getSharedPreferences("colorstar2",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("colorStar2",img_str);
                editor.commit();
                Intent golge=new Intent(Fruit.this, MainActivity.class);
                startActivity(golge);
                overridePendingTransition(R.anim.smalltobig,0);
            }
        });
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setProgress(0);
                dialog.dismiss();
                Intent quiz1=new Intent(Fruit.this, MainActivity.class);
                startActivity(quiz1);
                overridePendingTransition(R.anim.smalltobig, 0);
            }
        });
        dialog.show();
    }
    public void bolumPuani(){
        int puan=33;
        SharedPreferences bPF=getSharedPreferences("fruitPuan",MODE_PRIVATE);
        final SharedPreferences.Editor editor=bPF.edit();
        editor.putInt("veriFruit",puan);
        editor.apply();
    }


}
