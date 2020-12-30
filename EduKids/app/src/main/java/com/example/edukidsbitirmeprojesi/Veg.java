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

public class Veg extends AppCompatActivity {
    private RecyclerView vegetablesRecycler;
    private List<ImageItem> imageItemList;
    private ImageAdapter adapter;
    ImageView Vegstar;
    private CenterZoomLayoutManager centerZoomLayoutManager;
    ProgressBar progressBar;
    private Button previous, play, next;
    private int counter = 0;
    private MediaPlayer mediaPlayer;
    private int[] sounds;
    TextView sayac2,durum;
    int sayac=0;
    SharedPreferences sharedPre;
    @Override
    protected void onPause() {
        SharedPreferences.Editor editor=sharedPre.edit();
        editor.putInt("saymaanahtari5",sayac);
        editor.commit();

        super.onPause();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_veg);
        avatarAl();
        progressBar = (ProgressBar)findViewById(R.id.progressBarVeg2);
        Vegstar=(ImageView)findViewById(R.id.Vegstar);
        durum=(TextView)findViewById(R.id.sayma);
        sharedPre= getSharedPreferences("saymaVeri5",MODE_PRIVATE);
        sayac=sharedPre.getInt("saymaanahtari5",0);
        durum.setText(sayac+"/10");
        sounds = new int[]{R.raw.artichoke, R.raw.asparagus, R.raw.beans, R.raw.beetroot, R.raw.blue_cabbage, R.raw.broccoli,
                R.raw.brussel_sprouts, R.raw.carrot, R.raw.cauliflower, R.raw.celery, R.raw.chili_peppers, R.raw.chinese_cabbage,
                R.raw.corn, R.raw.cucumbers, R.raw.dil, R.raw.eggplant, R.raw.garlic, R.raw.green_cabbage, R.raw.lemon, R.raw.lettuce,
                R.raw.onion, R.raw.parsley, R.raw.peppers, R.raw.potatoe, R.raw.radish, R.raw.red_cabbage, R.raw.squash,
                R.raw.tomatoe, R.raw.zuchinni};
        imageItemList = new ArrayList<>();
        ListVeg();
        adapter = new ImageAdapter(this, imageItemList);
        vegetablesRecycler = (RecyclerView) findViewById(R.id.recycler_vegetables);
        centerZoomLayoutManager = new CenterZoomLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        vegetablesRecycler.setLayoutManager(centerZoomLayoutManager);
        vegetablesRecycler.setItemAnimator(new DefaultItemAnimator());
        vegetablesRecycler.setAdapter(adapter);
        play = (Button) findViewById(R.id.play_vegetables);
        counter = Integer.MAX_VALUE / 2;
        vegetablesRecycler.scrollToPosition(counter);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(vegetablesRecycler);
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
                    showDialog(Veg.this);
                    progressBar.setProgress(0);
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent r=new Intent(Veg.this,Bolum2.class);
        startActivity(r);
        finish();
        overridePendingTransition(R.anim.smalltobig,0);
    }
    private void ListVeg() {
        imageItemList.add(new ImageItem("Artichokes", R.drawable.artichokes));
        imageItemList.add(new ImageItem("Asparagus", R.drawable.asparagus));
        imageItemList.add(new ImageItem("Beans & Peas", R.drawable.beansandpeas));
        imageItemList.add(new ImageItem("Beetroot", R.drawable.beet));
        imageItemList.add(new ImageItem("Blue Cabbage", R.drawable.bluecabbage));
        imageItemList.add(new ImageItem("Broccoli", R.drawable.broccolli));
        imageItemList.add(new ImageItem("Brussels Sprouts", R.drawable.brusselssprout));
        imageItemList.add(new ImageItem("Carrot", R.drawable.carrot));
        imageItemList.add(new ImageItem("Cauliflower", R.drawable.cauli));
        imageItemList.add(new ImageItem("Celery", R.drawable.celery));
        imageItemList.add(new ImageItem("Chili Peppers", R.drawable.chilipeppers));
        imageItemList.add(new ImageItem("Chinese Cabbage", R.drawable.chinesecabbage));
        imageItemList.add(new ImageItem("Corn", R.drawable.corn));
        imageItemList.add(new ImageItem("Cucumbers", R.drawable.cucumbers));
        imageItemList.add(new ImageItem("Dil", R.drawable.dil));
        imageItemList.add(new ImageItem("Eggplant", R.drawable.eggplant));
        imageItemList.add(new ImageItem("Garlic", R.drawable.garlic));
        imageItemList.add(new ImageItem("Green Cabbage", R.drawable.greencabbage));
        imageItemList.add(new ImageItem("Lemon", R.drawable.lemon));
        imageItemList.add(new ImageItem("Lettuce", R.drawable.lettuce));
        imageItemList.add(new ImageItem("Onions", R.drawable.onions));
        imageItemList.add(new ImageItem("Parsley", R.drawable.parseley));
        imageItemList.add(new ImageItem("Peppers", R.drawable.peppers));
        imageItemList.add(new ImageItem("Potatoes", R.drawable.potatoes));
        imageItemList.add(new ImageItem("Radish", R.drawable.radish));
        imageItemList.add(new ImageItem("Red Cabbage", R.drawable.redcabbage));
        imageItemList.add(new ImageItem("Squashes", R.drawable.squashes));
        imageItemList.add(new ImageItem("Tomatoes", R.drawable.tomatoes));
        imageItemList.add(new ImageItem("Zuccini", R.drawable.zuccini));
    }
    private void avatarAl() {
        SharedPreferences preferences = getSharedPreferences("myprefs",MODE_PRIVATE);
        ImageView avatarImg=(ImageView) findViewById(R.id.imageViewAv);
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
                bolumPuani();
                Vegstar.buildDrawingCache();
                Bitmap bitmap = Vegstar.getDrawingCache();
                ByteArrayOutputStream streamstar=new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, streamstar);
                byte[] image=streamstar.toByteArray();
                String img_str = Base64.encodeToString(image, 0);
                //decode string to image
                String base=img_str;
                byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);
                Vegstar.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes,0, imageAsBytes.length) );
                //save in sharedpreferences
                SharedPreferences preferences = getSharedPreferences("vgstar3",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("vgStar3",img_str);
                editor.commit();
                Intent quiz=new Intent(Veg.this, SebzelerOyun.class);
                startActivity(quiz);
                overridePendingTransition(R.anim.smalltobig,0);
            }
        });
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setProgress(0);
                dialog.dismiss();
                Intent quiz1=new Intent(Veg.this,Veg.class);
                startActivity(quiz1);
                overridePendingTransition(R.anim.smalltobig, 0);
            }
        });
        dialog.show();
    }
    public void bolumPuani(){
        int puan=33;
        SharedPreferences bPV=getSharedPreferences("vegPuan",MODE_PRIVATE);
        final SharedPreferences.Editor editor=bPV.edit();
        editor.putInt("veriVeg",puan);
        editor.apply();
    }
}
