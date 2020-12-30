package com.example.edukidsbitirmeprojesi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.edukidsbitirmeprojesi.Adapter.AlphabetAdapter;
import com.example.edukidsbitirmeprojesi.FrAlpha.FrAlpha;
import com.example.edukidsbitirmeprojesi.Helper.CenterZoomLayoutManager;
import com.example.edukidsbitirmeprojesi.Object.AlphabetItem;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Alphabets extends AppCompatActivity {
    private List<AlphabetItem> alphabetsList;
    private RecyclerView alphabetRecycler;
    private AlphabetAdapter adapter;
    private CenterZoomLayoutManager centerZoomLayoutManager;
    private int progresBarDurumu=0;
    private Handler handler = new Handler();
    TextView sayac2,durum;
    int sayac=0;
    private Button previous, play, next;
    private int counter = 0;
    ImageView star;
    private int[] sounds;
    private MediaPlayer mediaPlayer;
    ProgressBar progressBar;
    SharedPreferences sharedPre;
    Button fr,eng;
    @Override
    protected void onPause() {
        SharedPreferences.Editor editor=sharedPre.edit();
        editor.putInt("saymaanahtari",sayac);
        editor.commit();
        super.onPause();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_alphabets);
        star=(ImageView)findViewById(R.id.alphastar);
        progressBar = (ProgressBar)findViewById(R.id.progressBarAlpha3);

        fr=(Button)findViewById(R.id.frfalg);



        sounds = new int[]{R.raw.p, R.raw.q, R.raw.r, R.raw.s, R.raw.t, R.raw.u, R.raw.v, R.raw.w,
                R.raw.x, R.raw.y, R.raw.z, R.raw.a, R.raw.b, R.raw.c, R.raw.d, R.raw.e, R.raw.f, R.raw.g, R.raw.h, R.raw.i, R.raw.j, R.raw.k,
                R.raw.l, R.raw.m, R.raw.n, R.raw.o};



        avatarAl();
        alphabetsList = new ArrayList<>();
        ListHarf();
        adapter = new AlphabetAdapter(this, alphabetsList);
        centerZoomLayoutManager = new CenterZoomLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        alphabetRecycler = (RecyclerView) findViewById(R.id.recycler_alphabets);
        play = (Button) findViewById(R.id.play_alphabets);
        sayac2=(TextView)findViewById(R.id.alphasayac);
        durum=(TextView)findViewById(R.id.sayma);
        alphabetRecycler.setLayoutManager(centerZoomLayoutManager);
        alphabetRecycler.setItemAnimator(new DefaultItemAnimator());
        alphabetRecycler.setAdapter(adapter);
        counter = Integer.MAX_VALUE / 2;
        //-----------------------------------------------------------
        sharedPre= getSharedPreferences("saymaVeri",MODE_PRIVATE);
        sayac=sharedPre.getInt("saymaanahtari",0);
        durum.setText(sayac+"/10");
        //-----------------------------------------------------------
        alphabetRecycler.scrollToPosition(counter);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(alphabetRecycler);
     /*   fr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fr=new Intent(Alphabets.this, FrAlpha.class);
                startActivity(fr);
                finish();
            }
        });*/
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setProgress(progressBar.getProgress()+5);
                counter = centerZoomLayoutManager.findLastCompletelyVisibleItemPosition();
                int pos = counter % alphabetsList.size();
                if (mediaPlayer != null) {
                    mediaPlayer.release();
                }
                mediaPlayer = MediaPlayer.create(getApplicationContext(), sounds[pos]);
                mediaPlayer.start();
                        if(progressBar.getProgress()==100){
                            sayac++;
                            play.setEnabled(false);
                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Do something after 4s = 5000ms
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            while (progresBarDurumu<=100){
                                                handler.post(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        sayac2.setText("Loading"+""+progresBarDurumu+"%");
                                                        if(progresBarDurumu==100){
                                                            showDialog(Alphabets.this);
                                                        }
                                                    }
                                                });
                                                try {
                                                    Thread.sleep(500);
                                                }catch (InterruptedException e){
                                                    e.printStackTrace();
                                                }
                                                progresBarDurumu+=50;

                                            }

                                        }
                                    }).start();

                                }
                            }, 2000);
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
    private void ListHarf() {
        alphabetsList.add(new AlphabetItem("P p"));
        alphabetsList.add(new AlphabetItem("Q q"));
        alphabetsList.add(new AlphabetItem("R r"));
        alphabetsList.add(new AlphabetItem("S s"));
        alphabetsList.add(new AlphabetItem("T t"));
        alphabetsList.add(new AlphabetItem("U u"));
        alphabetsList.add(new AlphabetItem("V v"));
        alphabetsList.add(new AlphabetItem("W w"));
        alphabetsList.add(new AlphabetItem("X x"));
        alphabetsList.add(new AlphabetItem("Y y"));
        alphabetsList.add(new AlphabetItem("Z z"));
        alphabetsList.add(new AlphabetItem("A a"));
        alphabetsList.add(new AlphabetItem("B b"));
        alphabetsList.add(new AlphabetItem("C c"));
        alphabetsList.add(new AlphabetItem("D d"));
        alphabetsList.add(new AlphabetItem("E e"));
        alphabetsList.add(new AlphabetItem("F f"));
        alphabetsList.add(new AlphabetItem("G g"));
        alphabetsList.add(new AlphabetItem("H h"));
        alphabetsList.add(new AlphabetItem("I i"));
        alphabetsList.add(new AlphabetItem("J j"));
        alphabetsList.add(new AlphabetItem("K k"));
        alphabetsList.add(new AlphabetItem("L l"));
        alphabetsList.add(new AlphabetItem("M m"));
        alphabetsList.add(new AlphabetItem("N n"));
        alphabetsList.add(new AlphabetItem("O o"));
    }

    private void avatarAl() {
        SharedPreferences preferences = getSharedPreferences("myprefs",MODE_PRIVATE);
        ImageView avatarImage=(ImageView) findViewById(R.id.imageViewAv3);
        ImageView avatarImage1=(ImageView) findViewById(R.id.imageViewAv3);
        String img_str=preferences.getString("userphoto", "");
        if (!img_str.equals("")){
            //decode string to image
            String base=img_str;
            byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);
            avatarImage.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );
            avatarImage1.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );
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
                star.buildDrawingCache();
                Bitmap bitmap = star.getDrawingCache();
                ByteArrayOutputStream streamstar=new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, streamstar);
                byte[] image=streamstar.toByteArray();
                String img_str = Base64.encodeToString(image, 0);
                //decode string to image
                String base=img_str;
                byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);

                star.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes,0, imageAsBytes.length) );
                //save in sharedpreferences
                SharedPreferences preferences = getSharedPreferences("mystar",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("starphoto",img_str);
                editor.commit();
                Intent quiz=new Intent(Alphabets.this, alphaquizgiris.class);
                startActivity(quiz);
                overridePendingTransition(R.anim.smalltobig,0);
            }
        });
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setProgress(0);
                dialog.dismiss();
                Intent quiz1=new Intent(Alphabets.this,Alphabets.class);
                startActivity(quiz1);
                overridePendingTransition(R.anim.smalltobig, 0);
            }
        });
        dialog.show();
    }
    public void bolumPuani(){
        int puan=33;
        SharedPreferences bP=getSharedPreferences("bP",MODE_PRIVATE);
        final SharedPreferences.Editor editor=bP.edit();
        editor.putInt("veri",puan);
        editor.apply();
    }

}
