package com.example.edukidsbitirmeprojesi.FR;

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
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.edukidsbitirmeprojesi.Adapter.NumbersAdapter;
import com.example.edukidsbitirmeprojesi.Bolum1;
import com.example.edukidsbitirmeprojesi.Helper.CenterZoomLayoutManager;
import com.example.edukidsbitirmeprojesi.Numbers;
import com.example.edukidsbitirmeprojesi.Object.ImageItem;
import com.example.edukidsbitirmeprojesi.R;
import com.example.edukidsbitirmeprojesi.quiznumbergiris;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Frnumbers extends AppCompatActivity {
    private RecyclerView numRecycler;
    private List<ImageItem> imageItemList;
    private NumbersAdapter adapter;
    ProgressBar progressBar;
    private CenterZoomLayoutManager centerZoomLayoutManager;
    ImageView numstar;
    private Button previous, play, next;
    private int counter = 0;
    private MediaPlayer mediaPlayer;
    private int[] sounds;
    TextView sayac2,durum;
    int sayac=0;
    SharedPreferences sharedPre;
    Button eng;
    @Override
    protected void onPause() {
        SharedPreferences.Editor editor=sharedPre.edit();
        editor.putInt("saymaanahtariFr1",sayac);
        editor.commit();

        super.onPause();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_fr_numbers);

        eng=(Button)findViewById(R.id.frfalg3);

        numstar=(ImageView)findViewById(R.id.numstar);
        avatarAl();
        progressBar = (ProgressBar)findViewById(R.id.progressBarNum4);
        sounds = new int[]{R.raw.fr1, R.raw.fr2, R.raw.fr3, R.raw.fr4, R.raw.fr5, R.raw.fr6,
                R.raw.fr7, R.raw.fr8, R.raw.fr9, R.raw.fr10,};
        imageItemList = new ArrayList<>();
        ListSayi();
        adapter = new NumbersAdapter(this, imageItemList);
        numRecycler = (RecyclerView) findViewById(R.id.recycler_num);
        durum=(TextView)findViewById(R.id.sayma);
        sharedPre= getSharedPreferences("saymaVeriFr1",MODE_PRIVATE);
        sayac=sharedPre.getInt("saymaanahtariFr1",0);
        durum.setText(sayac+"/10");
        centerZoomLayoutManager = new CenterZoomLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        numRecycler.setLayoutManager(centerZoomLayoutManager);
        numRecycler.setItemAnimator(new DefaultItemAnimator());
        numRecycler.setAdapter(adapter);
        play = (Button) findViewById(R.id.play_num);
        counter = Integer.MAX_VALUE / 2;
        numRecycler.scrollToPosition(counter);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(numRecycler);


        eng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent eng=new Intent(Frnumbers.this,Numbers.class);
                startActivity(eng);
                finish();
            }
        });

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
                    showDialog(Frnumbers.this);
                    progressBar.setProgress(0);
                }
            }
        });
    }
    private void ListSayi() {
        imageItemList.add(new ImageItem("UN", R.drawable.one));
        imageItemList.add(new ImageItem("DEUX", R.drawable.two));
        imageItemList.add(new ImageItem("TROIS", R.drawable.three));
        imageItemList.add(new ImageItem("QUATRE", R.drawable.four));
        imageItemList.add(new ImageItem("CINQ", R.drawable.five));
        imageItemList.add(new ImageItem("SIX", R.drawable.six));
        imageItemList.add(new ImageItem("SEPT", R.drawable.seven));
        imageItemList.add(new ImageItem("HUIT", R.drawable.eight));
        imageItemList.add(new ImageItem("NEUF", R.drawable.nine));
        imageItemList.add(new ImageItem("DIX", R.drawable.ten));

    }

    private void ListTR() {
        imageItemList.add(new ImageItem("Bir", R.drawable.one));
        imageItemList.add(new ImageItem("İki", R.drawable.two));
        imageItemList.add(new ImageItem("Üç", R.drawable.three));
        imageItemList.add(new ImageItem("Dört", R.drawable.four));
        imageItemList.add(new ImageItem("Beş", R.drawable.five));
        imageItemList.add(new ImageItem("Altı", R.drawable.six));
        imageItemList.add(new ImageItem("Yedi", R.drawable.seven));
        imageItemList.add(new ImageItem("Sekiz", R.drawable.eight));
        imageItemList.add(new ImageItem("Dokuz", R.drawable.nine));
        imageItemList.add(new ImageItem("On", R.drawable.ten));

    }
    private void avatarAl() {
        SharedPreferences preferences = getSharedPreferences("myprefs",MODE_PRIVATE);
        ImageView avartarImg=(ImageView) findViewById(R.id.imageViewAv4);
        String img_str=preferences.getString("userphoto", "");
        if (!img_str.equals("")){
            //decode string to image
            String base=img_str;
            byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);
            avartarImg.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );

        }
    }
    public void bolumPuani(){
        int puan=33;
        SharedPreferences bP=getSharedPreferences("bP1",MODE_PRIVATE);
        final SharedPreferences.Editor editor=bP.edit();
        editor.putInt("veri1",puan);
        editor.apply();
    }
    public void setStarr2(){
        //code image to string
        numstar.buildDrawingCache();
        Bitmap bitmap = numstar.getDrawingCache();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream);
        byte[] image=stream.toByteArray();
        //System.out.println("byte array:"+image);
        //final String img_str = "data:image/png;base64,"+ Base64.encodeToString(image, 0);
        //System.out.println("string:"+img_str);
        String img_str = Base64.encodeToString(image, 0);
        //decode string to image
        String base=img_str;
        byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);
        numstar.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes,0, imageAsBytes.length) );
        //save in sharedpreferences
        SharedPreferences preferences = getSharedPreferences("mystar2",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("userstarr2",img_str);
        editor.commit();
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
                setStarr2();
                bolumPuani();
                Intent quiz=new Intent(Frnumbers.this, quiznumbergiris.class);
                startActivity(quiz);
                overridePendingTransition(R.anim.smalltobig,0);

            }
        });
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setProgress(0);
                dialog.dismiss();
                Intent nm=new Intent(getApplicationContext(), Numbers.class);
                startActivity(nm);
            }
        });
        dialog.show();

    }
    @Override
    public void onBackPressed() {
        Intent r=new Intent(Frnumbers.this, Bolum1.class);
        startActivity(r);
        finish();
        overridePendingTransition(R.anim.smalltobig,0);
    }
}
