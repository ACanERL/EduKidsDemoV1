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

import com.example.edukidsbitirmeprojesi.Adapter.ColorsAdapter;
import com.example.edukidsbitirmeprojesi.Helper.CenterZoomLayoutManager;

import java.io.ByteArrayOutputStream;

public class Colors extends AppCompatActivity {
    private RecyclerView colorRecycler;
    private ColorsAdapter adapter;
    private CenterZoomLayoutManager centerZoomLayoutManager;
    private ImageView colorstar;
    private Button previous, play, next;
    private int counter = 0;
    private int[] sounds;
    private MediaPlayer mediaPlayer;
    private ProgressBar progressBar;
    TextView sayac2,durum;
    int sayac=0;
    SharedPreferences sharedPre;
    @Override
    protected void onPause() {
        SharedPreferences.Editor editor=sharedPre.edit();
        editor.putInt("saymaanahtari3",sayac);
        editor.commit();

        super.onPause();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_colors_new);
        AvatarAl();
        progressBar=(ProgressBar)findViewById(R.id.progressBarColors6);
        sounds = new int[]{R.raw.red, R.raw.pink, R.raw.purple, R.raw.indigo, R.raw.blue, R.raw.sky_blue, R.raw.cyan, R.raw.teal,
                R.raw.green, R.raw.lime, R.raw.yellow, R.raw.amber, R.raw.orange, R.raw.brown, R.raw.grey, R.raw.black, R.raw.white};

        final int[] colors = getApplicationContext().getResources().getIntArray(R.array.colors);
        adapter = new ColorsAdapter(getApplicationContext());
        centerZoomLayoutManager = new CenterZoomLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        colorRecycler = (RecyclerView) findViewById(R.id.recycler_colors);
        play = (Button) findViewById(R.id.play_colors);
        durum=(TextView)findViewById(R.id.sayma);
        sharedPre= getSharedPreferences("saymaVeri3",MODE_PRIVATE);
        sayac=sharedPre.getInt("saymaanahtari3",0);
        durum.setText(sayac+"/10");


        colorRecycler.setLayoutManager(centerZoomLayoutManager);
        colorRecycler.setItemAnimator(new DefaultItemAnimator());
        colorRecycler.setAdapter(adapter);
        counter = Integer.MAX_VALUE / 2;
        colorstar=(ImageView)findViewById(R.id.colorsstar);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(colorRecycler);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setProgress(progressBar.getProgress()+10);
                counter = centerZoomLayoutManager.findLastCompletelyVisibleItemPosition();
                int pos = counter % colors.length;
                if (mediaPlayer != null) {
                    mediaPlayer.release();
                }
                mediaPlayer = MediaPlayer.create(getApplicationContext(), sounds[pos]);
                mediaPlayer.start();

                if(progressBar.getProgress()==100){
                    sayac++;
                    play.setEnabled(false);
                    showDialog(Colors.this);
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
    private void AvatarAl() {
        SharedPreferences preferences = getSharedPreferences("myprefs",MODE_PRIVATE);
        ImageView avatarImg=(ImageView) findViewById(R.id.imageViewAv6);
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
                colorstar.buildDrawingCache();
                Bitmap bitmap = colorstar.getDrawingCache();
                ByteArrayOutputStream streamstar=new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, streamstar);
                byte[] image=streamstar.toByteArray();
                String img_str = Base64.encodeToString(image, 0);
                //decode string to image
                String base=img_str;
                byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);

               colorstar.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes,0, imageAsBytes.length) );
                //save in sharedpreferences
                SharedPreferences preferences = getSharedPreferences("colorstar",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("colorStar",img_str);
                editor.commit();
                Intent draw=new Intent(Colors.this, Drawing.class);
                startActivity(draw);
                overridePendingTransition(R.anim.smalltobig,0);
            }
        });
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setProgress(0);
                dialog.dismiss();
                Intent quiz1=new Intent(Colors.this, Colors.class);
                startActivity(quiz1);
                overridePendingTransition(R.anim.smalltobig, 0);
            }
        });
        dialog.show();
    }

    public void bolumPuani(){
        int puan=33;
        SharedPreferences bPC=getSharedPreferences("colorPuan",MODE_PRIVATE);
        final SharedPreferences.Editor editor=bPC.edit();
        editor.putInt("veriColor",puan);
        editor.apply();
    }
}
