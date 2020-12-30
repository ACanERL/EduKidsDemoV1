package com.example.edukidsbitirmeprojesi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.File;

public class Bolum1 extends AppCompatActivity {
   Button alpha;
   Button animal;
   Button numbers;
   Button boxNext,wheel;
   ImageView log_out;
   ProgressBar progressBar;
   int puan,puan1,puan2;
   ImageView ivUserPhoto;
   ImageView av1;
   Animation dondurme,zoom,sag,sol;
    ImageView avatarImage,avatarImage3,avatarImage4;

    public static int REWARD = 0;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_bolum1);

        boxNext=(Button)findViewById(R.id.box);
        alpha = ( Button) findViewById(R.id.alphabets);
        animal = ( Button) findViewById(R.id.animals);
        numbers = (Button) findViewById(R.id.numbers);
        // star = (ImageView)findViewById(R.id.starr);
         wheel=(Button)findViewById(R.id.whbutton) ;
         ivUserPhoto=(ImageView) findViewById(R.id.anaEkranAvatar_1);
         progressBar=(ProgressBar)findViewById(R.id.ChapterprogressBar1);
     //   reward.setText("\u20BF"+REWARD);
        AvatarAl();
        dondurme=AnimationUtils.loadAnimation(this,R.anim.anim_rotate);
        sag=AnimationUtils.loadAnimation(this,R.anim.right);
        alpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alpha.startAnimation(dondurme);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent();
                        intent.setClass(Bolum1.this, Alphabets.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.smalltobig,0);
                    }
                }, 1000);
            }
        });
        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // numbers.setBackgroundResource(R.drawable.t2);
                numbers.startAnimation(dondurme);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(Bolum1.this, Numbers.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.smalltobig,0);
                    }
                }, 1000);

            }
        });
        animal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animal.startAnimation(dondurme);
             //   animal.setBackgroundResource(R.drawable.t3);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(Bolum1.this, Animals.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.smalltobig,0);
                    }
                }, 1000);
            }
        });
       yıldızAl();
       yıldızAl_2();
       yıldızAl_3();
       bolumPuaniCek();
       Button tumverilerisil=(Button)findViewById(R.id.fulldelete);
       tumverilerisil.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               final AlertDialog.Builder builder = new AlertDialog.Builder(Bolum1.this);
               builder.setMessage("\n" +
                       "Warning...!");
               builder.setTitle("Care !");
               builder.setMessage("Do you want to delete this application data ?");
               builder.setCancelable(false);
               builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       ShpVeriler();
                       SharedPreferences genel = getSharedPreferences("First", Context.MODE_PRIVATE);// cache temizlemek
                       genel.edit().clear().commit();
                       SharedPreferences avatarsac = getSharedPreferences("First1", Context.MODE_PRIVATE);
                       avatarsac.edit().clear().commit();
                       SharedPreferences avatarust = getSharedPreferences("First2", Context.MODE_PRIVATE);
                       avatarust.edit().clear().commit();
                       Toast.makeText(Bolum1.this, "Deleted", Toast.LENGTH_SHORT).show();

                   }
               });
               builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       Toast.makeText(Bolum1.this, "Not Deleted", Toast.LENGTH_SHORT).show();
                       dialog.cancel();
                   }
               });
               AlertDialog alert = builder.create();
               alert.show();

           }
       });
        wheel.setVisibility(View.INVISIBLE);
       if(progressBar.getProgress()==100){
           boxNext.setVisibility(View.VISIBLE);
       }

       boxNext.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               boxNext.startAnimation(dondurme);

               final Handler handler = new Handler();
               handler.postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       boxNext.setBackgroundResource(R.drawable.twf_back);
                       Intent intent = new Intent(Bolum1.this, avatarsac.class);
                       startActivity(intent);
                       finish();
                       overridePendingTransition(R.anim.smalltobig,0);
                   }
               }, 1000);

           }
       });
        /*log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(Bolum1.this, v);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.mymenu, popup.getMenu());
                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        String a = item.getTitle().toString().trim();

                        if(a.equals("Language")){

                            Intent intent =new Intent(getApplicationContext(),Language.class);
                            intent.addCategory(Intent.CATEGORY_HOME);
                           // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
                            startActivity(intent);
                            //finish();
                           // System.exit(0);
                        }
                        if(a.equals("Exit"))
                        {
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
                            startActivity(intent);
                            finish();
                            System.exit(0);

                        }
                        return true;
                    }
                });

                popup.show(); //showing popup menu
            }
        });*/

    }//Logout function

    private Boolean exit = false;
    @Override
    public void onBackPressed() {
        if(exit)
        {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
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
    private void AvatarAl() {
        SharedPreferences preferences = getSharedPreferences("myprefs",MODE_PRIVATE);
       // ImageView ivUserPhoto=(ImageView) findViewById(R.id.anaEkranAvatar_1);
        ImageView avatarImage=(ImageView) findViewById(R.id.anaEkranAvatar_1);
        String img_str=preferences.getString("userphoto", "");
        if (!img_str.equals("")){
            //decode string to image
            String base=img_str;
            byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);
            ivUserPhoto.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );
            avatarImage.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );
        }
    }
//************************************Save Yıldızlar
    private void yıldızAl(){
        SharedPreferences preferences_yıldız = getSharedPreferences("mystar",MODE_PRIVATE);
        ImageView starPhoto=(ImageView) findViewById(R.id.starr);
        ImageView starSavedPhoto=(ImageView) findViewById(R.id.starr);
        String img_str=preferences_yıldız.getString("starphoto", "");
        if (!img_str.equals("")){
            //decode string to image
            String base=img_str;
            byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);
            starPhoto.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );
            starSavedPhoto.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );
        }
    }
    private void yıldızAl_2(){
        SharedPreferences preferences_yıldız = getSharedPreferences("mystar2",MODE_PRIVATE);
        ImageView star2Img=(ImageView) findViewById(R.id.starr2);
        ImageView star2Img1=(ImageView) findViewById(R.id.starr2);


        String img_str=preferences_yıldız.getString("userstarr2", "");
        if (!img_str.equals("")){
            numbers.setBackgroundResource(R.drawable.t2);
            String base=img_str;
            byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);
            star2Img.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );
            star2Img1.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );
        }
    }
    private  void yıldızAl_3(){
        SharedPreferences preferences_yıldız = getSharedPreferences("mystar3",MODE_PRIVATE);
        ImageView star3Image1=(ImageView) findViewById(R.id.star3);
        ImageView star3Image2=(ImageView) findViewById(R.id.star3);


        String img_str=preferences_yıldız.getString("starphoto3", "");
        if (!img_str.equals("")){
            animal.setBackgroundResource(R.drawable.t3);
            String base=img_str;
            byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);
            star3Image1.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );
            star3Image2.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );
        }
    }
    public void bolumPuaniCek(){
        SharedPreferences sharedPreferences=getSharedPreferences("bP",MODE_PRIVATE);
        puan=sharedPreferences.getInt("veri",MODE_PRIVATE);
        if(puan==33){
            numbers.setVisibility(View.VISIBLE);
            ivUserPhoto.setVisibility(View.INVISIBLE);
            avatarImage=(ImageView) findViewById(R.id.anaEkranAvatar_2);

            SharedPreferences preferences = getSharedPreferences("myprefs",MODE_PRIVATE);
            String img_str=preferences.getString("userphoto", "");
            if (!img_str.equals("")){
                //decode string to image
                String base=img_str;
                byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);
                avatarImage.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );
            }

        }

        SharedPreferences sharedPreferences1=getSharedPreferences("bP1",MODE_PRIVATE);
        puan1=sharedPreferences1.getInt("veri1",MODE_PRIVATE);
        if(puan1==33){
            animal.setVisibility(View.VISIBLE);
            avatarImage.setVisibility(View.INVISIBLE);
            avatarImage3=(ImageView)findViewById(R.id.anaEkranAvatar_3);
            SharedPreferences preferences = getSharedPreferences("myprefs",MODE_PRIVATE);
            String img_str=preferences.getString("userphoto", "");
            if (!img_str.equals("")){
                //decode string to image
                String base=img_str;
                byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);
                avatarImage3.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );
            }

        }

        SharedPreferences sharedPreferences2=getSharedPreferences("bP2",MODE_PRIVATE);
        puan2=sharedPreferences2.getInt("veri2",MODE_PRIVATE);
        progressBar.setProgress(puan+puan1+puan2+1);

        if(progressBar.getProgress()==100){
            avatarImage3.setVisibility(View.INVISIBLE);
            avatarImage4=(ImageView)findViewById(R.id.anaEkranAvatar_4);
            SharedPreferences preferences = getSharedPreferences("myprefs",MODE_PRIVATE);
            String img_str=preferences.getString("userphoto", "");
            if (!img_str.equals("")){
                //decode string to image
                String base=img_str;
                byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);
                avatarImage4.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );
            }
        }

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

    }


}
