package com.example.edukidsbitirmeprojesi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class Avatarlar extends AppCompatActivity {
    ImageView avatar1,avatar2,avatar3,avatar4,kusresmi;
    Button Reset;
    ImageView log_out;
    ProgressBar loadingProgress;
    private int progresBarDurumu=0;
    private Handler handler = new Handler();
    TextView sayac,oyundurumu;
    Animation zoom;
    SharedPreferences sharedPre;
    int oyunyuzde;
    SharedPreferences preferences;//preferences referansı
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_avatarlar);
        firstTime();
        avatar1=(ImageView)findViewById(R.id.avatar1);
        avatar2=(ImageView)findViewById(R.id.avatar2);
        avatar3=(ImageView)findViewById(R.id.avatar3);
        avatar4=(ImageView)findViewById(R.id.avatar4);
       // oyundurumu=(TextView)findViewById(R.id.oyundurumu);





        sayac=(TextView)findViewById(R.id.sayac);
        zoom=AnimationUtils.loadAnimation(this,R.anim.zoom_in);
        Reset=(Button)findViewById(R.id.resett);
        loadingProgress=(ProgressBar)findViewById(R.id.loadingprogressBar2);
        kusresmi=(ImageView)findViewById(R.id.kusresmi);
      //  trenresmi=(ImageView)findViewById(R.id.trenresmi);
        log_out=(ImageView)findViewById(R.id.log_out);
        karakterSecim();
        logOut();
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(Avatarlar.this);
                builder.setMessage("\n" +
                        "Warning...!");
                builder.setTitle("Care !");
                builder.setMessage("Do you want to delete this application data ?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ShpVeriler();
                        clearApplicationData();
                        SharedPreferences avatarData = getSharedPreferences("prefsAna", Context.MODE_PRIVATE);// cache temizlemek
                        avatarData.edit().clear().commit();

                        Toast.makeText(Avatarlar.this, "Deleted", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(Avatarlar.this, "Not Deleted", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    private  void logOut(){
        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(Avatarlar.this, v);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.mymenu, popup.getMenu());
                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        String a = item.getTitle().toString().trim();
                     /*   if(a.equals("Language")){

                            Intent intent =new Intent(getApplicationContext(),Language.class);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
                            startActivity(intent);
                            //finish();
                            // System.exit(0);
                        }*/
                        if(a.equals("ClearSave"))
                        {
                          ShpVeriler();
                            SharedPreferences genel = getSharedPreferences("prefsAna", Context.MODE_PRIVATE);// cache temizlemek
                            genel.edit().clear().commit();

                        }
                        return true;
                    }
                });

                popup.show();
            }
        });
    }

    public void karakterSecim(){
                avatar1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sharedPre= getSharedPreferences("saymaVeri11",MODE_PRIVATE);
                        avatar1.startAnimation(zoom);
                        loadingProgress.setVisibility(View.VISIBLE);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                while (progresBarDurumu<=100){
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            loadingProgress.setProgress(progresBarDurumu);
                                            sayac.setText("Loading"+progresBarDurumu+"%");
                                            if(progresBarDurumu==100){
                                                Intent i = new Intent(Avatarlar.this, Bolum1.class);
                                                setAvatarImage1();
                                                startActivity(i);
                                                finish();
                                            }
                                        }
                                    });
                                    try {
                                        Thread.sleep(1000);
                                    }catch (InterruptedException e){
                                        e.printStackTrace();
                                    }
                                    progresBarDurumu+=25;
                                }

                            }
                        }).start();
                    }
                });
                avatar2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sharedPre= getSharedPreferences("saymaVeri22",MODE_PRIVATE);
                        avatar2.startAnimation(zoom);
                        loadingProgress.setVisibility(View.VISIBLE);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                while (progresBarDurumu<=100){
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            loadingProgress.setProgress(progresBarDurumu);
                                            sayac.setText("Loading"+progresBarDurumu+"%");
                                            if(progresBarDurumu==100){
                                                Intent i = new Intent(Avatarlar.this, Bolum1.class);
                                                setAvatarImage2();
                                                startActivity(i);
                                                finish();
                                            }
                                        }
                                    });
                                    try {
                                        Thread.sleep(1000);
                                    }catch (InterruptedException e){
                                        e.printStackTrace();
                                    }
                                    progresBarDurumu+=25;
                                }

                            }
                        }).start();
                    }
                });
                avatar3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sharedPre= getSharedPreferences("saymaVeri33",MODE_PRIVATE);
                        avatar3.startAnimation(zoom);
                        loadingProgress.setVisibility(View.VISIBLE);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                while (progresBarDurumu<=100){
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            loadingProgress.setProgress(progresBarDurumu);
                                            sayac.setText("Loading"+"   "+progresBarDurumu+"%");
                                            if(progresBarDurumu==100){
                                                Intent i = new Intent(Avatarlar.this, Bolum1.class);
                                                setAvatarImage3();
                                                startActivity(i);
                                                finish();
                                            }
                                        }
                                    });
                                    try {
                                        Thread.sleep(1000);
                                    }catch (InterruptedException e){
                                        e.printStackTrace();
                                    }
                                    progresBarDurumu+=25;

                                }

                            }
                        }).start();
                    }
                });
                avatar4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sharedPre= getSharedPreferences("saymaVeri44",MODE_PRIVATE);
                        avatar4.startAnimation(zoom);
                        loadingProgress.setVisibility(View.VISIBLE);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                while (progresBarDurumu<=100){
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            loadingProgress.setProgress(progresBarDurumu);
                                            sayac.setText("Loading"+progresBarDurumu+"%");
                                            if(progresBarDurumu==100){
                                                Intent i = new Intent(Avatarlar.this, Bolum1.class);
                                                setAvatarImage4();
                                                startActivity(i);
                                                finish();

                                            }
                                        }
                                    });
                                    try {
                                        Thread.sleep(1000);
                                    }catch (InterruptedException e){
                                        e.printStackTrace();
                                    }
                                    progresBarDurumu+=25;
                                }

                            }
                        }).start();
                    }
                });
            }
    private Boolean exit = false;
    @Override
    public void onBackPressed() {
        if(exit)
        {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//
            startActivity(intent);
            finish();
            System.exit(0);
        }
        else
        {
            Toast.makeText(this, "Press Again to Exit",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 2 * 1000);
        }
    }
    public void setAvatarImage1(){
        //resmi byte cevir
        avatar1.buildDrawingCache();
        Bitmap bitmap = avatar1.getDrawingCache();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream);
        byte[] image=stream.toByteArray();

        String img_str = Base64.encodeToString(image, 0);
        String base=img_str;
        byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);
        avatar1.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes,0, imageAsBytes.length) );

        SharedPreferences preferences = getSharedPreferences("myprefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("userphoto",img_str);
        editor.commit();
    }

    public  void setAvatarImage2(){
        //resmi cevirme
        avatar2.buildDrawingCache();
        Bitmap bitmap = avatar2.getDrawingCache();
        ByteArrayOutputStream stream1=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream1);
        byte[] image=stream1.toByteArray();

        String img_str = Base64.encodeToString(image, 0);
        String base=img_str;
        byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);
        avatar2.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes,0, imageAsBytes.length) );
        SharedPreferences preferences = getSharedPreferences("myprefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("userphoto",img_str);
        editor.commit();
    }
    public  void setAvatarImage3(){
        avatar3.buildDrawingCache();
        Bitmap bitmap = avatar3.getDrawingCache();
        ByteArrayOutputStream stream2=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream2);
        byte[] image=stream2.toByteArray();

        String img_str = Base64.encodeToString(image, 0);
        String base=img_str;
        byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);
        avatar3.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes,0, imageAsBytes.length) );
        SharedPreferences preferences = getSharedPreferences("myprefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("userphoto",img_str);
        editor.commit();
    }

    public  void setAvatarImage4(){
        avatar4.buildDrawingCache();
        Bitmap bitmap = avatar4.getDrawingCache();
        ByteArrayOutputStream stream3=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream3);
        byte[] image=stream3.toByteArray();
        String img_str = Base64.encodeToString(image, 0);
        String base=img_str;
        byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);
        avatar4.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes,0, imageAsBytes.length) );
        SharedPreferences preferences = getSharedPreferences("myprefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("userphoto",img_str);
        editor.commit();
    }

    public void ShpVeriler(){
        //Bolum 1 icinn-----------------------
        SharedPreferences settings1 = getSharedPreferences("mystar2", Context.MODE_PRIVATE);// cache temizlemek
        settings1.edit().clear().commit();

        SharedPreferences settings2 = getSharedPreferences("mystar", Context.MODE_PRIVATE);// cache temizlemek
        settings2.edit().clear().commit();

        SharedPreferences settings3 = getSharedPreferences("mystar3", Context.MODE_PRIVATE);// cache temizlemek
        settings3.edit().clear().commit();

        SharedPreferences settings4 = getSharedPreferences("bP", Context.MODE_PRIVATE);// cache temizlemek
        settings4.edit().clear().commit();

        SharedPreferences settings5= getSharedPreferences("bP1", Context.MODE_PRIVATE);// cache temizlemek
        settings5.edit().clear().commit();

        SharedPreferences settings6 = getSharedPreferences("bP2", Context.MODE_PRIVATE);// cache temizlemek
        settings6.edit().clear().commit();

        //Bolüm 2 icinnn-------------------------

        SharedPreferences settings7 = getSharedPreferences("colorstar", Context.MODE_PRIVATE);// cache temizlemek
        settings7.edit().clear().commit();

        SharedPreferences settings8 = getSharedPreferences("colorstar2", Context.MODE_PRIVATE);// cache temizlemek
        settings8.edit().clear().commit();

        SharedPreferences settings9 = getSharedPreferences("vgstar3", Context.MODE_PRIVATE);// cache temizlemek
        settings9.edit().clear().commit();

        SharedPreferences settings10 = getSharedPreferences("fruitPuan", Context.MODE_PRIVATE);// cache temizlemek
        settings10.edit().clear().commit();

        SharedPreferences settings11 = getSharedPreferences("colorPuan", Context.MODE_PRIVATE);// cache temizlemek
        settings11.edit().clear().commit();

        SharedPreferences settings12 = getSharedPreferences("vegPuan", Context.MODE_PRIVATE);// cache temizlemek
        settings12.edit().clear().commit();

        //Bolum 3 icinnn--------------

        SharedPreferences settings13 = getSharedPreferences("yildiz1", Context.MODE_PRIVATE);// cache temizlemek
        settings13.edit().clear().commit();

        SharedPreferences settings14 = getSharedPreferences("yildiz2", Context.MODE_PRIVATE);// cache temizlemek
        settings14.edit().clear().commit();

        SharedPreferences settings15 = getSharedPreferences("yildiz3", Context.MODE_PRIVATE);// cache temizlemek
        settings15.edit().clear().commit();


        SharedPreferences settings16 = getSharedPreferences("shapePuan", Context.MODE_PRIVATE);// cache temizlemek
        settings16.edit().clear().commit();

        SharedPreferences settings17 = getSharedPreferences("vehiclePuan", Context.MODE_PRIVATE);// cache temizlemek
        settings17.edit().clear().commit();

        SharedPreferences settings18 = getSharedPreferences("objePuan", Context.MODE_PRIVATE);// cache temizlemek
        settings18.edit().clear().commit();

        //eğitim icerikleri tekrar durumları silme
        SharedPreferences settings19= getSharedPreferences("saymaVeri", Context.MODE_PRIVATE);// cache temizlemek
        settings19.edit().clear().commit();
        SharedPreferences settings20= getSharedPreferences("saymaVeri1", Context.MODE_PRIVATE);// cache temizlemek
        settings20.edit().clear().commit();
        SharedPreferences settings21= getSharedPreferences("saymaVeri2", Context.MODE_PRIVATE);// cache temizlemek
        settings21.edit().clear().commit();
        SharedPreferences settings22= getSharedPreferences("saymaVeri3", Context.MODE_PRIVATE);// cache temizlemek
        settings22.edit().clear().commit();
        SharedPreferences settings23= getSharedPreferences("saymaVeri4", Context.MODE_PRIVATE);// cache temizlemek
        settings23.edit().clear().commit();
        SharedPreferences settings24= getSharedPreferences("saymaVeri5", Context.MODE_PRIVATE);// cache temizlemek
        settings24.edit().clear().commit();
        SharedPreferences settings25= getSharedPreferences("saymaVeri6", Context.MODE_PRIVATE);// cache temizlemek
        settings25.edit().clear().commit();
        SharedPreferences settings26= getSharedPreferences("saymaVeri7", Context.MODE_PRIVATE);// cache temizlemek
        settings26.edit().clear().commit();
        SharedPreferences settings27= getSharedPreferences("saymaVeri8", Context.MODE_PRIVATE);// cache temizlemek
        settings27.edit().clear().commit();
        SharedPreferences settings28 = getSharedPreferences("sonuc", Context.MODE_PRIVATE);// cache temizlemek
        settings28.edit().clear().commit();

        SharedPreferences fulldelete = getSharedPreferences("First", Context.MODE_PRIVATE);// cache temizlemek
        fulldelete.edit().clear().commit();

    }

    public  void  firstTime() {
        SharedPreferences sharedTime = getSharedPreferences("First", Context.MODE_PRIVATE);
        if (sharedTime.getBoolean("firstTime", true)) {
            sharedTime.edit().putBoolean("firstTime", false).apply();
        } else {
            Intent r=new Intent(Avatarlar.this,Bolum1.class);
            startActivity(r);
            finish();

        }
    }

    public void clearApplicationData() {
        File cache = getCacheDir();
        File appDir = new File(cache.getParent());
        if(appDir.exists()){
            String[] children = appDir.list();
            for(String s : children){
                if(!s.equals("lib")){
                    deleteDir(new File(appDir, s));
                    Log.i("TAG", "File /data/data/APP_PACKAGE/" + s +" DELETED");
                }
            }
        }
    }
    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

}
