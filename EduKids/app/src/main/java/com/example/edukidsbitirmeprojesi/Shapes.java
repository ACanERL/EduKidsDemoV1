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
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.edukidsbitirmeprojesi.Adapter.ShapesAdapter;
import com.example.edukidsbitirmeprojesi.Helper.CenterZoomLayoutManager;
import com.example.edukidsbitirmeprojesi.Labirent.LabirentOyunu;
import com.example.edukidsbitirmeprojesi.Object.ImageItem;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Shapes extends AppCompatActivity {

    private RecyclerView shapesRecycler;
    private List<ImageItem> imageItemList;
    private ShapesAdapter adapter;
    private CenterZoomLayoutManager centerZoomLayoutManager;
    private Button previous, play, next;
    private int counter = 0;
    private MediaPlayer mediaPlayer;
    private int[] sounds;
    ProgressBar progressBar;
    ImageView shapestar;
    TextView sayac2,durum;
    int sayac=0;
    SharedPreferences sharedPre;
    @Override
    protected void onPause() {
        SharedPreferences.Editor editor=sharedPre.edit();
        editor.putInt("saymaanahtari6",sayac);
        editor.commit();

        super.onPause();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_shapes);
        progressBar=(ProgressBar)findViewById(R.id.progressBarShape6);
        shapestar=(ImageView)findViewById(R.id.shapestar3);
        durum=(TextView)findViewById(R.id.sayma);
        sharedPre= getSharedPreferences("saymaVeri6",MODE_PRIVATE);
        sayac=sharedPre.getInt("saymaanahtari6",0);
        durum.setText(sayac+"/10");
        sounds = new int[]{R.raw.circle, R.raw.triangle, R.raw.sphere, R.raw.square, R.raw.rectangle, R.raw.hexagon,
                R.raw.pentagon, R.raw.cylinder, R.raw.cube, R.raw.pyramid, R.raw.cone};
        avatarAl();
        imageItemList = new ArrayList<>();
        ListSekill();
        adapter = new ShapesAdapter(this, imageItemList);
        shapesRecycler = (RecyclerView) findViewById(R.id.recycler_shape);
        centerZoomLayoutManager = new CenterZoomLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        shapesRecycler.setLayoutManager(centerZoomLayoutManager);
        shapesRecycler.setItemAnimator(new DefaultItemAnimator());
        shapesRecycler.setAdapter(adapter);
        play = (Button) findViewById(R.id.play_shape);
        counter = Integer.MAX_VALUE / 2;
        shapesRecycler.scrollToPosition(counter);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(shapesRecycler);
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
                    showDialog(Shapes.this);
                    progressBar.setProgress(0);
                }
            }
        });
    }
    private void ListSekill() {
        imageItemList.add(new ImageItem("Circle", R.drawable.circle));
        imageItemList.add(new ImageItem("Triangle", R.drawable.triangle));
        imageItemList.add(new ImageItem("Sphere", R.drawable.sphere));
        imageItemList.add(new ImageItem("Square", R.drawable.square));
        imageItemList.add(new ImageItem("Rectangle", R.drawable.rectangle));
        imageItemList.add(new ImageItem("Hexagon", R.drawable.hexagon));
        imageItemList.add(new ImageItem("Pentagon", R.drawable.pentagon));
        imageItemList.add(new ImageItem("Cylinder", R.drawable.cylinder));
        imageItemList.add(new ImageItem("Cube", R.drawable.cube));
        imageItemList.add(new ImageItem("Pyramid", R.drawable.pyramid));
        imageItemList.add(new ImageItem("Cone", R.drawable.cone));
    }
    private void avatarAl() {
        SharedPreferences preferences = getSharedPreferences("myprefs",MODE_PRIVATE);
        ImageView avatarImg=(ImageView) findViewById(R.id.imageViewAv8);
        String img_str=preferences.getString("userphoto", "");
        if (!img_str.equals("")){
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
                shapestar.buildDrawingCache();
                Bitmap bitmap = shapestar.getDrawingCache();
                ByteArrayOutputStream streamstar=new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, streamstar);
                byte[] image=streamstar.toByteArray();
                //System.out.println("byte array:"+image);
                //final String img_str = "data:image/png;base64,"+ Base64.encodeToString(image, 0);
                //System.out.println("string:"+img_str);
                String img_str = Base64.encodeToString(image, 0);
                String base=img_str;
                byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);

                shapestar.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes,0, imageAsBytes.length) );
                //save in sharedpreferences
                SharedPreferences preferences = getSharedPreferences("yildiz1",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("sekiller",img_str);
                editor.commit();
                Intent quiz=new Intent(Shapes.this, LabirentOyunu.class);
                startActivity(quiz);
                overridePendingTransition(R.anim.smalltobig,0);
            }
        });
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setProgress(0);
                dialog.dismiss();
                Intent quiz1=new Intent(Shapes.this,Shapes.class);
                startActivity(quiz1);
                overridePendingTransition(R.anim.smalltobig, 0);
            }
        });
        dialog.show();
    }
    public void bolumPuani(){
        int puan=33;
        SharedPreferences bP=getSharedPreferences("shapePuan",MODE_PRIVATE);
        final SharedPreferences.Editor editor=bP.edit();
        editor.putInt("shapeveri",puan);
        editor.apply();
    }
    @Override
    public void onBackPressed() {
      Intent r=new Intent(Shapes.this,Bolum3.class);
      startActivity(r);
      finish();
      overridePendingTransition(R.anim.smalltobig,0);
    }

}
