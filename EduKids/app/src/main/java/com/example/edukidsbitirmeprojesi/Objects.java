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

public class Objects extends AppCompatActivity {

    private RecyclerView objectRecycler;
    private List<ImageItem> imageItemList;
    private ImageAdapter adapter;
    private CenterZoomLayoutManager centerZoomLayoutManager;
    private Button previous, play, next;
    private int counter = 0;
    private MediaPlayer mediaPlayer;
    private int[] sounds;
    private ProgressBar progressBar;
    private ImageView objeyildiz;
    TextView sayac2,durum;
    int sayac=0;
    SharedPreferences sharedPre;
    @Override
    protected void onPause() {
        SharedPreferences.Editor editor=sharedPre.edit();
        editor.putInt("saymaanahtari8",sayac);
        editor.commit();

        super.onPause();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_objects);
        progressBar=(ProgressBar)findViewById(R.id.progressBarObje9);
        objeyildiz=(ImageView)findViewById(R.id.objestar);
        durum=(TextView)findViewById(R.id.sayma);
        sharedPre= getSharedPreferences("saymaVeri8",MODE_PRIVATE);
        sayac=sharedPre.getInt("saymaanahtari8",0);
        durum.setText(sayac+"/10");
        sounds = new int[]{R.raw.air_conditioner, R.raw.briefcase, R.raw.bucket, R.raw.clock, R.raw.cricket_bat, R.raw.cricket_ball,
                R.raw.chair, R.raw.pencil, R.raw.fan, R.raw.ladder, R.raw.laptop, R.raw.pen, R.raw.scissor, R.raw.smartphone,
                R.raw.toothbrush, R.raw.uchiwa};
        imageItemList = new ArrayList<>();
        ListObje();
        adapter = new ImageAdapter(this, imageItemList);
        objectRecycler = (RecyclerView) findViewById(R.id.recycler_obje);
        centerZoomLayoutManager = new CenterZoomLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        objectRecycler.setLayoutManager(centerZoomLayoutManager);
        objectRecycler.setItemAnimator(new DefaultItemAnimator());
        objectRecycler.setAdapter(adapter);
        play = (Button) findViewById(R.id.play_obje);
        counter = Integer.MAX_VALUE / 2;
        avatarAl();
        objectRecycler.scrollToPosition(counter);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(objectRecycler);
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
                    showDialog(Objects.this);
                    progressBar.setProgress(0);
                }
            }
        });
    }
    private void ListObje() {
        imageItemList.add(new ImageItem("Air Conditioner", R.drawable.air_conditioner));
        imageItemList.add(new ImageItem("Briefcase", R.drawable.briefcase));
        imageItemList.add(new ImageItem("Bucket", R.drawable.bucket));
        imageItemList.add(new ImageItem("Clock", R.drawable.clock));
        imageItemList.add(new ImageItem("Cricket Bat", R.drawable.cricket_bat));
        imageItemList.add(new ImageItem("Cricket Ball", R.drawable.cricket_ball));
        imageItemList.add(new ImageItem("Chair", R.drawable.chair));
        imageItemList.add(new ImageItem("Pencil", R.drawable.pencil));
        imageItemList.add(new ImageItem("Fan", R.drawable.fan));
        imageItemList.add(new ImageItem("Ladder", R.drawable.ladder));
        imageItemList.add(new ImageItem("Laptop", R.drawable.laptop));
        imageItemList.add(new ImageItem("Pen", R.drawable.pen));
        imageItemList.add(new ImageItem("Scissors", R.drawable.scissors));
        imageItemList.add(new ImageItem("SmartPhone", R.drawable.smartphone));
        imageItemList.add(new ImageItem("Toothbrush", R.drawable.toothbrush));
    }
    private void avatarAl() {
        SharedPreferences preferences = getSharedPreferences("myprefs",MODE_PRIVATE);
        ImageView avatarImg=(ImageView) findViewById(R.id.imageViewAv9);
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
                objeyildiz.buildDrawingCache();
                Bitmap bitmap = objeyildiz.getDrawingCache();
                ByteArrayOutputStream streamstar=new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, streamstar);
                byte[] image=streamstar.toByteArray();
                //System.out.println("byte array:"+image);
                //final String img_str = "data:image/png;base64,"+ Base64.encodeToString(image, 0);
                //System.out.println("string:"+img_str);
                String img_str = Base64.encodeToString(image, 0);
                //decode string to image
                String base=img_str;
                byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);

                objeyildiz.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes,0, imageAsBytes.length) );
                //save in sharedpreferences
                SharedPreferences preferences = getSharedPreferences("yildiz3",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("objecler",img_str);
                editor.commit();
                Intent draw=new Intent(Objects.this, ObjectVideo.class);
                startActivity(draw);
                overridePendingTransition(R.anim.smalltobig,0);
            }
        });
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setProgress(0);
                dialog.dismiss();
                Intent quiz1=new Intent(Objects.this,Bolum3.class);
                startActivity(quiz1);
                overridePendingTransition(R.anim.smalltobig, 0);
            }
        });
        dialog.show();
    }
    public void bolumPuani(){
        int puan=33;
        SharedPreferences bPC=getSharedPreferences("objePuan",MODE_PRIVATE);
        final SharedPreferences.Editor editor=bPC.edit();
        editor.putInt("veriObje",puan);
        editor.apply();
    }
    @Override
    public void onBackPressed() {
        Intent r=new Intent(Objects.this,Bolum3.class);
        startActivity(r);
        finish();
        overridePendingTransition(R.anim.smalltobig,0);
    }
}
