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

public class Vehicles extends AppCompatActivity {
    private RecyclerView vehiclesRecycler;
    private List<ImageItem> imageItemList;
    private ImageAdapter adapter;
    private CenterZoomLayoutManager centerZoomLayoutManager;
    private Button previous, play, next;
    private int counter = 0;
    private MediaPlayer mediaPlayer;
    private int[] sounds;
    private ProgressBar progressBar;
    private ImageView araclaryildiz;
    TextView sayac2,durum;
    int sayac=0;
    SharedPreferences sharedPre;
    @Override
    protected void onPause() {
        SharedPreferences.Editor editor=sharedPre.edit();
        editor.putInt("saymaanahtari7",sayac);
        editor.commit();
        super.onPause();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_vehicles);
        progressBar=(ProgressBar)findViewById(R.id.progressBarAraclar8);
        araclaryildiz=(ImageView)findViewById(R.id.araclarstar);
        durum=(TextView)findViewById(R.id.sayma);
        sharedPre= getSharedPreferences("saymaVeri7",MODE_PRIVATE);
        sayac=sharedPre.getInt("saymaanahtari7",0);
        durum.setText(sayac+"/10");
        sounds = new int[]{R.raw.aeroplane, R.raw.autorickshaw, R.raw.cab, R.raw.camping_car, R.raw.car, R.raw.caravan, R.raw.cargotruck,
                R.raw.dliveryvan, R.raw.hotairbaloon, R.raw.icecreamtruck, R.raw.motorcycle, R.raw.scooter, R.raw.ship, R.raw.speedboat, R.raw.truck};
        imageItemList = new ArrayList<>();
        List();
        adapter = new ImageAdapter(this, imageItemList);
        vehiclesRecycler = (RecyclerView) findViewById(R.id.recycler_araclar);
        centerZoomLayoutManager = new CenterZoomLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        vehiclesRecycler.setLayoutManager(centerZoomLayoutManager);
        vehiclesRecycler.setItemAnimator(new DefaultItemAnimator());
        vehiclesRecycler.setAdapter(adapter);
        avatarAl();
        play = (Button) findViewById(R.id.play_araclar);
        counter = Integer.MAX_VALUE / 2;
        vehiclesRecycler.scrollToPosition(counter);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(vehiclesRecycler);
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
                    showDialog(Vehicles.this);
                    progressBar.setProgress(0);
                }
            }
        });

    }
    private void List() {
        imageItemList.add(new ImageItem("Aeroplane", R.drawable.aeroplane));
        imageItemList.add(new ImageItem("Auto Rickshaw", R.drawable.auto));
        imageItemList.add(new ImageItem("Cab", R.drawable.cab));
        imageItemList.add(new ImageItem("Camping Car", R.drawable.campingcar));
        imageItemList.add(new ImageItem("Car", R.drawable.car));
        imageItemList.add(new ImageItem("Caravan", R.drawable.caravan));
        imageItemList.add(new ImageItem("Cargo", R.drawable.cargo));
        imageItemList.add(new ImageItem("Delivery Van", R.drawable.deliveryvan));
        imageItemList.add(new ImageItem("Hot Air Balloon", R.drawable.hotairballoon));
        imageItemList.add(new ImageItem("IceCream Truck", R.drawable.icecream));
        imageItemList.add(new ImageItem("Motorcycle", R.drawable.motorcycle));
        imageItemList.add(new ImageItem("Scooter", R.drawable.scooter));
        imageItemList.add(new ImageItem("Ship", R.drawable.ship));
        imageItemList.add(new ImageItem("Speed Boat", R.drawable.speedboat));
        imageItemList.add(new ImageItem("Truck", R.drawable.truck));
    }
    private void avatarAl() {
        SharedPreferences preferences = getSharedPreferences("myprefs",MODE_PRIVATE);
        ImageView ivUserPhoto=(ImageView) findViewById(R.id.imageViewAvVeh);
        ImageView ivUserSavedPhoto=(ImageView) findViewById(R.id.imageViewAvVeh);
        String img_str=preferences.getString("userphoto", "");
        if (!img_str.equals("")){
            //decode string to image
            String base=img_str;
            byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);
            ivUserPhoto.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );
            ivUserSavedPhoto.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );
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
                araclaryildiz.buildDrawingCache();
                Bitmap bitmap = araclaryildiz.getDrawingCache();
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

                araclaryildiz.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes,0, imageAsBytes.length) );
                //save in sharedpreferences
                SharedPreferences preferences = getSharedPreferences("yildiz2",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("araclar",img_str);
                editor.commit();
                Intent draw=new Intent(Vehicles.this, Video.class);
                startActivity(draw);
                overridePendingTransition(R.anim.smalltobig,0);
            }
        });
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setProgress(0);
                dialog.dismiss();
                Intent quiz1=new Intent(Vehicles.this,Vehicles.class);
                startActivity(quiz1);
                overridePendingTransition(R.anim.smalltobig, 0);
            }
        });
        dialog.show();
    }
    public void bolumPuani(){
        int puan=33;
        SharedPreferences bPC=getSharedPreferences("vehiclePuan",MODE_PRIVATE);
        final SharedPreferences.Editor editor=bPC.edit();
        editor.putInt("veriVehicle",puan);
        editor.apply();
    }
    @Override
    public void onBackPressed() {
      Intent r=new Intent(Vehicles.this,Bolum3.class);
      startActivity(r);
        finish();
        overridePendingTransition(R.anim.smalltobig,0);
    }
}
